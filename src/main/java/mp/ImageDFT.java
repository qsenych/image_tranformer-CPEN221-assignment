package mp;

import core.Image;

import java.awt.Color;

/**
 * This datatype calculates and holds the output of a 2D Discrete Fourier Transform (DSF).
 * Can also Calculate specific characteristics, such as the dominant off-axis angles of the image.
 */
public class ImageDFT {
    final static double DFT_THRESHOLD = 120.0;
    final static double ANGLE_OFFSET = 90.0;

    final static int IGNORE_ANGLE_THRESHOLD = 6;
    final static int WHITE_THRESHOLD = 128;
    final static int ANGLE_RES = 360;

    private boolean processed;

    private Complex[][] complexImg;
    private double[][] imgMagnitude;

    /**
     * Creates a new ImageDFT instance.
     *
     * @param img: A non-null Image to be represented as a DSF.
     */
    public ImageDFT (Image img) {
        if (img == null) {
            throw new IllegalArgumentException("Image cannot be null.");
        }

        /* Get the next power of 2 as the new size to reduce time complexity of the processing */
        int maxDim = Math.max(img.width(), img.height());
        int newSize = maxDim == 1 ? 1 : Integer.highestOneBit(maxDim - 1) * 2;

        complexImg = preProcessing(img, newSize, newSize, img.width(), img.height());
        imgMagnitude = new double[newSize][newSize];

        processed = false;
    }

    /**
     * Calculates the DFT of the initial image, shifts, then
     * applies a thresholding filter to the calculated image data.
     */
    public void processImg() {
        fft2D(); /* Calculates the DFT of complexImg into complexImg */
        fftShift(); /* shifts DFT to the centre of the matrix for better visualization */
        postprocess(); /* applies a thresholding filter, calculates magnitudes and rotates for easier calculation */
        processed = true;
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
     * The image is first grayscaled then rounded to black or white values based on the WHITE_THRESHOLD.
     *
     * @param image A non-null image to be processed, is unaffected at the end.
     * @param newWidth The new width of the image representation - pixels that don't transfer will be padded with black
     * @param newHeight The new height of the image representation - pixels that don't transfer will be padded with black
     * @param oldWidth The old width of the image - all pixels will be transferred to the new representation
     * @param oldHeight The old height of the image - all pixels will be transferred to the new representation
     * @return the black/white version of the instance padded with black until newWidth and newHeight
     */
    private Complex[][] preProcessing(Image image, int newWidth, int newHeight, int oldWidth, int oldHeight) {
        //Image bwImage = new Image(newWidth, newHeight);
        Complex[][] imageMatrix = new Complex[newWidth][newHeight];
        for (int col = 0; col < newWidth; col++) {
            for (int row = 0; row < newHeight; row++) {
                if (col < oldWidth && row < oldHeight) {
                    Color color = image.get(col, row);
                    Color gray = Image.toGray(color);
                    int colour = gray.getRGB() & 0xFF;
                    if (colour > WHITE_THRESHOLD) {
                        imageMatrix[col][row] = Complex.realToComplex(1.0); /* represents white */
                    } else {
                        imageMatrix[col][row] = Complex.realToComplex(0.0);/* represents black */
                    }
                } else {
                    imageMatrix[col][row] = Complex.realToComplex(0.0);
                }
            }
        }
        return imageMatrix;
    }

    /**
     * Finds the "strongest" off axis (not 0, 90, or 180 degrees) angle.
     * The image represented by this instance must have an off axis dominant angle
     * or the result will be +- IGNORE_ANGLE_THRESHOLD degrees off
     *
     * @return an angle between 0 and 180 degrees (not 0, 90, or 180)
     */
    public double findDominantAngle() {
         if (!processed) { processImg(); }

        double[][] hough = houghTransform();

        int width = hough.length;
        int height = hough[0].length;

        double maxMag = 0;
        int domCol = 0;

        for (int col = IGNORE_ANGLE_THRESHOLD; col < width - IGNORE_ANGLE_THRESHOLD; col++ ) {
            for (int row = 0; row < height; row++ ) {
                if (col > width / 2 - IGNORE_ANGLE_THRESHOLD && col < width / 2 + IGNORE_ANGLE_THRESHOLD) {
                        col = ANGLE_RES / 2 + IGNORE_ANGLE_THRESHOLD;
                }
                if (hough[col][row] > maxMag ) {
                    maxMag = hough[col][row];
                    domCol = col;
                }
            }
        }
        return domCol / 2.0 - ANGLE_OFFSET;
    }

    /**
     * Calculates the 2d fast fourier transform of the image data.
     * complexImg must have been preProcessed before this method is called.
     */
    private void fft2D() {
        /* this method calculates the 2D fft in O(n log(n)) time with a preprocessed dataset */

        int numCols = complexImg.length;
        int numRows = complexImg[0].length;

        for (int col = 0; col < numCols; col++) {
            complexImg[col] = fft(complexImg[col]);
        }

        for (int row = 0; row < numRows; row++) {
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
     * @return A Complex[] of the fourier transform of intensities
     */
    private Complex[] fft(Complex[] intensities) {
        int length = intensities.length;

        /* Base case */
        if (length == 1) return new Complex[]{intensities[0]};

        if (length % 2 != 0) {
            throw new IllegalArgumentException("Input array must be even");
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
     * Shifts fft data to centre of image for easier calculation and visualization.
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
     * Performs post-processing - calculating magnitudes, applying a threshold filter and rotating matrix -
     * on the image representation after the FFD has been calculated
     *  and the data has been shifted to the centre
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
     * Implements the hough transform to identify the dominant lines on an image
     * Intended for use after calculating the DFT of an image and processing that data
     * Where a
     *
     * @return a counting matrix: double[ angle ][ radius ], of the polar representation of each point on complexImg
     *      Each "hotspot" on the resulting matrix corresponds to a "strong" line on complexImg.
     */
    private double[][] houghTransform() {
        int houghHeight = (int) (Math.sqrt(2) * Math.max(imgMagnitude.length, imgMagnitude[0].length));

        double[][] houghSpace = new double[ANGLE_RES][houghHeight * 2];

        for (int i = 0; i < imgMagnitude.length; i++) {
            for (int j = 0; j < imgMagnitude[0].length; j++) {
                if (imgMagnitude[i][j] > 0.0) {
                    for (int angle = 0; angle < ANGLE_RES; angle++) {
                        int x = i - imgMagnitude.length / 2;
                        int y = j - imgMagnitude[0].length / 2;
                        int r = (int) (x * Math.cos(Math.toRadians(angle / 2.0)) + y * Math.sin(Math.toRadians(angle / 2.0)));
                        r += houghHeight;
                        houghSpace[angle][r]++;
                    }
                }
            }
        }
        return houghSpace;
    }
}
