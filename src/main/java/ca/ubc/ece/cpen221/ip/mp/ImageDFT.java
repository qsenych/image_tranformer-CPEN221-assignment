package ca.ubc.ece.cpen221.ip.mp;

import ca.ubc.ece.cpen221.ip.core.Image;

public class ImageDFT {
    final static double DFT_THRESHOLD = 120.0;
    final static int IGNORE_ANGLE_THRESHOLD = 8;
    final static int ANGLE_RES = 360;

    private Complex[][] complexImg;
    private double[][] imgMagnitude;

    public ImageDFT (Complex[][] _complexImg) {
        complexImg = _complexImg.clone();
        imgMagnitude = new double[_complexImg.length][_complexImg[0].length];

    }

    public Complex[][] getComplexImg () { return complexImg.clone(); }
    public double[][] getImgMagnitude () { return imgMagnitude.clone(); }

    public double findDominantAngle() {
        fft2D();
        fftShift();
        complexMatrixToImage(complexImg, complexImg.length, complexImg[0].length, "resources/unFilteredOutput.png");
        postprocess();
        //this.imgMagnitude = rotate90();
        doubleMatrixToImage(this.imgMagnitude, imgMagnitude.length, imgMagnitude[0].length, "resources/threshOutput.png");
        double[][] hough = houghTranform();
        doubleMatrixToImage(normalizeToGray(hough, hough.length, hough[0].length), hough.length, hough[0].length, "resources/houghOutput.png");

        int width = hough.length;
        int height = hough[0].length;

        double maxMag = 0;
        int domCol = 0;

        for (int col = IGNORE_ANGLE_THRESHOLD; col < width - IGNORE_ANGLE_THRESHOLD; col++ ) {
            for (int row = 0; row < height; row++ ) {
                if (col > width / 2 - IGNORE_ANGLE_THRESHOLD && col < width / 2 + IGNORE_ANGLE_THRESHOLD) col = 190;
                if (hough[col][row] > maxMag ) {
                    maxMag = hough[col][row];
                    domCol = col;
                }
            }
        }
        double angle = domCol / 2.0;
        return angle - 90;


    }

    /**
     *
     * @return
     */
    private void fft2D() {
        int numCols = complexImg.length;
        int numRows = complexImg[0].length;

        for (int col = 0; col < numCols; col++) {
            complexImg[col] = fft(complexImg[col]);
        }

        for (int row = 0; row < numCols; row++) {
            Complex[] fullRow = new Complex[numCols];
            for (int col = 0; col < numCols; col++) {
                fullRow[col] = complexImg[col][row];
            }

            Complex[] fftRow = fft(fullRow);

            for (int col = 0; col < numCols; col++) {
                complexImg[col][row] = fftRow[col];
            }

        }
    }



    /**
     *  Recursively implements the Cooley-Turkey FFT algorithm
     *
     * @param intensities a power of 2 sized array containing grayscale intensities
     * @return
     */
    private Complex[] fft(Complex[] intensities) {

        int length = intensities.length;

        if (length == 1) return new Complex[]{intensities[0]};

        //if ((int)(Math.ceil((Math.log(length) / Math.log(2)))) == (int)(Math.floor(((Math.log(length) / Math.log(2)))))) {
        if (length % 2 != 0) {
            throw new IllegalArgumentException("Input array must be power of 2");
        }

        Complex[] even = new Complex[length / 2];
        for (int i = 0; i < length / 2; i++)  {
            even[i] = intensities[i * 2];
        }
        Complex[] evenResult = fft(even);

        Complex[] odd = new Complex[length / 2];
        for (int i = 0; i < length / 2; i++)  {
            odd[i] = intensities[i * 2 + 1];
        }
        Complex[] oddResult = fft(odd);

        Complex[] result = new Complex[length];
        for (int i = 0; i < length / 2; i++) {
            double rotation = -2.0 * i * Math.PI / length;
            Complex complexValue =  new Complex(Math.cos(rotation), Math.sin(rotation));
            result[i] = evenResult[i].add(complexValue.times(oddResult[i]));
            result[i + length / 2] = evenResult[i].subtract(complexValue.times(oddResult[i]));
        }
        return result;
    }

    /**
     * creates new Complex[][] which shifts the attached image
     * @param spectrum
     * @return
     */
    private void fftShift() {
        int numCols = complexImg.length;
        int numRows = complexImg[0].length;
        Complex[][] shifted = new Complex[numCols][numRows];

        for (int col = 0; col < numCols; col++) {
            for (int row = 0; row < numRows; row++) {
                int newRow = (row + numRows / 2) % numRows;
                int newCol = (col + numCols / 2) % numCols;
                shifted[newCol][newRow] = complexImg[col][row];
            }
        }
        this.complexImg = shifted;

    }


    /**
     * Probably unnecessary
     * @param frequencies
     * @param threshold
     * @return
     */
    private void postprocess() {
        int numCols = complexImg.length;
        int numRows = complexImg[1].length;
        imgMagnitude = new double[numCols][numRows];

        for (int col = 0; col < numCols; col++) {
            for (int row = 0; row < numCols; row++) {
                if (complexImg[col][row].magnitude() < DFT_THRESHOLD) {
                    imgMagnitude[row][numCols - 1 - col] = 0.0;
                } else {
                    imgMagnitude[row][numCols - 1 - col] = complexImg[col][row].magnitude();
                }
            }
        }
    }


    private double[][] houghTranform() {

        int houghHeight = (int) (Math.sqrt(2) * Math.max(imgMagnitude.length, imgMagnitude[0].length));

        double[][] houghSpace = new double[ANGLE_RES][houghHeight * 2];

        for (int i = 0; i < imgMagnitude.length; i++) {
            for (int j = 0; j < imgMagnitude[0].length; j++) {
                if (imgMagnitude[i][j] > 0.0) {
                    for (int angle = 0; angle < ANGLE_RES; angle++) {
                        int x = i - imgMagnitude.length / 2;
                        int y = j - imgMagnitude[0].length / 2;
                        //forcing angles between [0, 180]
                        int r = (int) (x * Math.cos(Math.toRadians(angle / 2.0)) + y * Math.sin(Math.toRadians(angle / 2.0)));
                        r += houghHeight;
                        houghSpace[angle][r]++;
                    }
                }
            }
        }
        return houghSpace;
    }

    private double[][] rotate90() {
        int numCols = this.imgMagnitude.length;
        int numRows = this.imgMagnitude[1].length;

        double[][] rotated= new double[numRows][numCols];

        for (int col = 0; col < numCols; col++) {
            for (int row = 0; row < numRows; row++) {
                rotated[row][numCols - 1 - col] = imgMagnitude[col][row];
            }
        }
        return rotated;
    }




    /**
     * debug method
     * @param matrixImg
     * @param width
     * @param height
     * @return
     */
    private Image complexMatrixToImage(Complex[][] matrixImg, int width, int height, String name) {
        Image img = new Image(width, height);
        for(int col = 0; col < width; col++) {
            for(int row = 0; row < height; row++) {
                int value = (int) matrixImg[col][row].magnitude();
                img.setRGB(col, row, ((value & 0xFF) << 16) | ((value & 0xFF) << 8) | value & 0xFF);
            }
        }
        img.save(name);
        return img;
    }

    /**
     * debug method
     * @param matrixImg
     * @param width
     * @param height
     * @return
     */
    private Image doubleMatrixToImage(double[][] matrixImg, int width, int height, String name) {
        Image img = new Image(width, height);
        for(int col = 0; col < width; col++) {
            for(int row = 0; row < height; row++) {
                int value = (int) matrixImg[col][row];
                img.setRGB(col, row, ((value & 0xFF) << 16) | ((value & 0xFF) << 8) | value & 0xFF);
            }
        }
        img.save(name);
        return img;
    }

    private double[][] normalizeToGray(double[][] matrixImg, int width, int height) {
        double max = 0.0;
        for(int col = 0; col < width; col++) {
            for(int row = 0; row < height; row++) {
                if (matrixImg[col][row] > max) max = matrixImg[col][row];
            }
        }
        for(int col = 0; col < width; col++) {
            for(int row = 0; row < height; row++) {
                matrixImg[col][row] = matrixImg[col][row] * 254.0 / max;
            }
        }
        return matrixImg;
    }



}
