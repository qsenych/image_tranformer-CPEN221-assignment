package ca.ubc.ece.cpen221.ip.mp;

import ca.ubc.ece.cpen221.ip.core.Image;

import java.awt.Color;


public class ImageDFT {
    final static double DFT_THRESHOLD = 120.0;
    final static int IGNORE_ANGLE_THRESHOLD = 6;
    final static int ANGLE_RES = 360;

    private Complex[][] complexImg;
    private double[][] imgMagnitude;

    /**
     *
      * @param img
     */
    public ImageDFT (Image img) {
        int maxDim = Math.max(img.width(), img.height());
        int newSize = maxDim == 1 ? 1 : Integer.highestOneBit(maxDim - 1) * 2;

        complexImg = preProcessing(img, newSize, newSize, img.width(), img.height());
        imgMagnitude = new double[newSize][newSize];

        fft2D();
        fftShift();
        postprocess();
    }

    /**
     * @return a copy of the complex representation of the image
     */
    public Complex[][] getComplexImg () { return complexImg.clone(); }

    /**
     * @return a copy of the imgMagnitude matrix
     */
    public double[][] getImgMagnitude () { return imgMagnitude.clone(); }

    /**
     * Obtain the black/white version of the image and pad with black until the next power of 2 size.
     * The image is first grayscaled then rounded to black or white values
     *
     * @return the black/white version of the instance.
     */
    private Complex[][] preProcessing(Image image, int newWidth, int newHeight, int oldWidth, int oldHeight) {
        Image bwImage = new Image(newWidth, newHeight);
        Complex[][] imageMatrix = new Complex[newWidth][newHeight];
        for (int col = 0; col < newWidth; col++) {
            for (int row = 0; row < newHeight; row++) {
                if (col < oldWidth && row < oldHeight) {
                    Color color = image.get(col, row);
                    Color gray = Image.toGray(color);
                    int colour = gray.getRGB() & 0xFF;
                    if (colour > 128) {
                        imageMatrix[col][row] = Complex.realToComplex(1.0); //represents white
                        bwImage.set(col, row, Color.WHITE);
                    } else {
                        imageMatrix[col][row] = Complex.realToComplex(0.0);
                        bwImage.set(col, row, Color.BLACK);
                    }
                } else {
                    imageMatrix[col][row] = Complex.realToComplex(0.0);
                    bwImage.set(col, row, Color.WHITE);
                }
            }
        }
        //bwImage.save("resources/dftImgs/smallGreenBW.png");
        return imageMatrix;
    }

    /**
     * Object must be initalized with a s
     *
     * @return
     */
    public double findDominantAngle() {
        //complexMatrixToImage(complexImg, complexImg.length, complexImg[0].length, "resources/unFilteredOutput.png");
        //this.imgMagnitude = rotate90();
        //doubleMatrixToImage(this.imgMagnitude, imgMagnitude.length, imgMagnitude[0].length, "resources/threshOutput.png");
        //doubleMatrixToImage(normalizeToGray(hough, hough.length, hough[0].length), hough.length, hough[0].length, "resources/houghOutput.png");

        double[][] hough = houghTranform();

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
     *
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
     *
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

    /**
     *
     * @return
     */
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
