package ca.ubc.ece.cpen221.ip.mp;

import ca.ubc.ece.cpen221.ip.core.Image;
import ca.ubc.ece.cpen221.ip.core.Rectangle;

import java.awt.Color;
import java.math.BigInteger;
import java.util.Collections;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;


/**
 * This datatype (or class) provides operations for transforming an image.
 *
 * <p>The operations supported are:
 * <ul>
 *     <li>The {@code ImageTransformer} constructor generates an instance of an image that
 *     we would like to transform;</li>
 *     <li></li>
 * </ul>
 * </p>
 */

public class ImageTransformer {
    private Image image;
    private int width;
    private int height;
    ImageDFT dft;

    /**
     * Creates an ImageTransformer with an image. The provided image is
     * <strong>never</strong> changed by any of the operations.
     *
     * @param img is not null
     */
    public ImageTransformer(Image img) {

        this.image = img;
        this.width = img.width();
        this.height = img.height();
    }

    /**
     * Obtain the grayscale version of the image.
     *
     * @return the grayscale version of the instance.
     */
    public Image grayscale() {
        Image gsImage = new Image(width, height);
        for (int col = 0; col < width; col++) {
            for (int row = 0; row < height; row++) {
                Color color = image.get(col, row);
                Color gray = Image.toGray(color);
                gsImage.set(col, row, gray);
            }
        }
        return gsImage;
    }

    /**
     * Obtain a version of the image with only the red colours.
     *
     * @return a reds-only version of the instance.
     */
    public Image red() {
        Image redImage = new Image(width, height);
        for (int col = 0; col < width; col++) {
            for (int row = 0; row < height; row++) {
                int originalPixel = image.getRGB(col, row);
                int alpha = (originalPixel >> 24) & 0xFF;
                int red = (originalPixel >> 16) & 0xFF;
                int desiredColor = (alpha << 24) | (red << 16) | (0 << 8) | (0);
                redImage.setRGB(col, row, desiredColor);
            }
        }
        return redImage;
    }


    /* ===== TASK 1 ===== */

    /**
     * Returns the mirror image of an instance.
     *
     * @return the mirror image of the instance.
     */
    public Image mirror() {
        Image mirrorImage = new Image(this.width, this.height);
        for(int col = 0; col < this.width; col++) {
            for (int row = 0; row < this.height; row++) {
                int originalPixel = image.getRGB(col, row);
                mirrorImage.setRGB(this.width - col - 1, row, originalPixel);
            }
        }

        return mirrorImage;
    }

    /**
     * <p>Returns the negative version of an instance.<br />
     * If the colour of a pixel is (r, g, b) then the colours of the same pixel
     * in the negative of the image are (255-r, 255-g, 255-b).</p>
     *
     * @return the negative of the instance.
     */
    public Image negative() {
        Image negativeImage = new Image(this.width, this.height);
        for (int col = 0; col < this.width; col++) {
            for (int row = 0; row < this.height; row++) {
                int originalPixel = image.getRGB(col, row);

                int alpha = (originalPixel >> 24) & 0xFF;
                int red = (originalPixel >> 16) & 0xFF;
                int green = (originalPixel >> 8) & 0xFF;
                int blue = originalPixel & 0xFF;

                int negativePixel = (alpha << 24) | ((255 - red) << 16) | ((255 - green) << 8) | (255 - blue);

                negativeImage.setRGB(col, row, negativePixel);
            }
        }
        return negativeImage;
    }

    /**
     *  Produces an image the uses a restricted number of colours.
     *  If colour value of pixel 0 <= colour <= 64, colour value is set to 32.
     *  If colour value of pixel 64 < colour <= 128, colour value is set to 96.
     *  If colour value of pixel 128 < colour <= 255, colour value is set to 222.
     *
     * @return a posterized version of the image
     */
    public Image posterize() {
        Image posterizedImage = new Image(this.width, this.height);
        for (int col = 0; col < this.width; col++) {
            for (int row = 0; row < this.height; row++) {
                int originalPixel = image.getRGB(col, row);

                int alpha = (originalPixel >> 24) & 0xFF;
                int red = (originalPixel >> 16) & 0xFF;
                int green = (originalPixel >> 8) & 0xFF;
                int blue = originalPixel & 0xFF;

                if(red <= 64) {
                    red = 32;
                } else if (red <= 128) {
                    red = 96;
                } else {
                    red = 222;
                }

                if(green <= 64) {
                    green = 32;
                } else if (green <= 128) {
                    green = 96;
                } else {
                    green = 222;
                }

                if(blue <= 64) {
                    blue = 32;
                } else if (blue <= 128) {
                    blue = 96;
                } else {
                    blue = 222;
                }

                int posterizedPixel = (alpha << 24) | (red << 16) | (green << 8) | blue;

                posterizedImage.setRGB(col, row, posterizedPixel);
            }
        }
        return posterizedImage;
    }

    public Image clip(Rectangle rect) {
        int newWidth = rect.xBottomRight - rect.xTopLeft;
        int newHeight = rect.yBottomRight - rect.yTopLeft;

        Image clippedImg = new Image(newWidth + 1, newHeight + 1);
        for (int col = 0; col <= newWidth; col++) {
            for (int row = 0; row <= newHeight; row++) {
                int oldColour = this.image.getRGB(rect.xTopLeft + col, rect.yTopLeft + row);
                clippedImg.setRGB(col, row, oldColour);
            }
        }

        return clippedImg;
    }


    /* ===== TASK 2 ===== */

    /**
     *  Performs simple denoising by replacing each colour's value on a pixel by the median value of that and its neighbours
     *
     * @return a cleaned up Image.
     */
    public Image denoise() {
        Image cleanImage = new Image(this.width, this.height);
        for (int col = 0; col < this.width; col++) {
            for (int row = 0; row < this.height; row++) {
                int originalPixel = image.getRGB(col, row);
                //(col-1, row+1), (col, row+1),  (col+1, row+1)
                //(col-1, row)       pixel       (col+1, row)
                //(col-1, row-1)  (col, row-1),  (col+1, row-1)

                int redSum = 0;
                ArrayList<Integer> reds = new ArrayList<Integer>();
                ArrayList<Integer> greens = new ArrayList<Integer>();
                ArrayList<Integer> blues = new ArrayList<Integer>();

                for(int i = -1; i <= 1; i++) {
                    for(int j = -1; j <= 1; j++) {
                        if(col + i >= 0 && col + i < this.width && row + j >= 0 && row + j < this.height) {
                            reds.add((image.getRGB(col + i, row + j) >> 16) & 0xFF);
                            greens.add((image.getRGB(col + i, row + j) >> 8) & 0xFF);
                            blues.add(image.getRGB(col + i, row + j) & 0xFF);
                        }
                    }
                }

                int alpha = (originalPixel >> 24) & 0xFF;
                int red = listMedian(reds);
                int green = listMedian(greens);
                int blue = listMedian(blues);

                int cleanPixel = (alpha << 24) | (red << 16) | (green << 8) | blue;

                cleanImage.setRGB(col, row, cleanPixel);
            }
        }
        return cleanImage;
    }

    /**
     * Calculates and returns the median of a list
     *
     * @param list: an unsorted ArrayList of integers
     * @return the median of the ArrayList as an integer
     */
    private int listMedian(ArrayList<Integer> list) {
        Collections.sort(list);

        int middle;

        if(list.size() % 2 == 1) {
            middle = list.get(list.size() / 2);
        } else {
            middle = (list.get(list.size() / 2) + list.get(list.size() / 2 - 1)) / 2;
        }

        return middle;
    }

    /**
     *  Weathers an image by replacing the colour value of a pixel with the minimum of that pixel and its neighbours.
     *
     * @return a weathered version of the image.
     */
    public Image weather() {
        Image weatheredImage = new Image(this.width, this.height);
        for (int col = 0; col < this.width; col++) {
            for (int row = 0; row < this.height; row++) {
                int originalPixel = image.getRGB(col, row);
                //(col-1, row+1), (col, row+1),  (col+1, row+1)
                //(col-1, row)       pixel       (col+1, row)
                //(col-1, row-1)  (col, row-1),  (col+1, row-1)

                int redSum = 0;
                ArrayList<Integer> reds = new ArrayList<Integer>();
                ArrayList<Integer> greens = new ArrayList<Integer>();
                ArrayList<Integer> blues = new ArrayList<Integer>();

                for (int i = -1; i <= 1; i++) {
                    for (int j = -1; j <= 1; j++) {
                        if (col + i >= 0 && col + i < this.width && row + j >= 0 && row + j < this.height) {
                            reds.add((image.getRGB(col + i, row + j) >> 16) & 0xFF);
                            greens.add((image.getRGB(col + i, row + j) >> 8) & 0xFF);
                            blues.add(image.getRGB(col + i, row + j) & 0xFF);
                        }
                    }
                }

                int alpha = (originalPixel >> 24) & 0xFF;
                int red = Collections.min(reds);
                int green = Collections.min(greens);
                int blue = Collections.min(blues);

                int weatheredPixel = (alpha << 24) | (red << 16) | (green << 8) | blue;
                weatheredImage.setRGB(col, row, weatheredPixel);
            }
        }
        return weatheredImage;
    }

    /**
     * Replaces each blocksize x blocksize area of pixels in the image by the average pixel colour
     *
     * @param blockSize: An integer greater than 0.
     * @return a non-null image with the colour set
     */
    public Image blockPaint(int blockSize) {
        Image blockedImage = new Image(this.width, this.height);
        for (int col = 0; col < this.width; col += blockSize) {
            for (int row = 0; row < this.height; row += blockSize) {
                int xBottomRight;
                int yBottomRight;
                //check for limits
                xBottomRight = Math.min(col + blockSize, this.width);
                yBottomRight = Math.min(row + blockSize, this.height);

                Rectangle block = new Rectangle(col, row, xBottomRight, yBottomRight);
                int colour = averageRectColour(block);
                setRectColour(block, colour, blockedImage);
            }
        }

        return blockedImage;
    }

    /**
     * Helper function to calculate average colour over a rectangle
     *
     * @param rect: A non-0 the rectangle to be averaged over
     * @return An integer describing the average RGB colour value in the rectangle
     */
    private int averageRectColour(Rectangle rect) {
        /*int redSum = 0;
        int greenSum = 0;
        int blueSum = 0;
         */
        long pixelCount = 0;

        BigInteger redSum = BigInteger.ZERO;
        BigInteger greenSum = BigInteger.ZERO;
        BigInteger blueSum = BigInteger.ZERO;

        for(int col = rect.xTopLeft; col < rect.xBottomRight; col++) {
            for(int row = rect.yTopLeft; row < rect.yBottomRight; row++) {
                int red = (image.getRGB(col, row) >> 16) & 0xFF;
                int green = (image.getRGB(col, row) >> 8) & 0xFF;
                int blue = image.getRGB(col, row) & 0xFF;

                redSum = redSum.add(BigInteger.valueOf(red));
                greenSum = greenSum.add(BigInteger.valueOf(green));
                blueSum = blueSum.add(BigInteger.valueOf(blue));

                pixelCount++;
            }
        }

        int red = redSum.divide(BigInteger.valueOf(pixelCount)).intValue();
        int green = greenSum.divide(BigInteger.valueOf(pixelCount)).intValue();
        int blue = blueSum.divide(BigInteger.valueOf(pixelCount)).intValue();

        return (0xFF /* Alpha */ << 24) | (red << 16) | (green << 8) | blue;
    }

    /**
     *  Sets an entire rectangle of an image to a specific colour
     *
     * @param rect A rectangle which is contained entirely within the image height and width
     * @param colour A 24-bit colour integer detailing the RGB colour to set the block
     * @param img the image to be modified. Must be non-null and contain the pixels described in rect
     */
    private void setRectColour(Rectangle rect, int colour, Image img) {
        for (int col = rect.xTopLeft; col < rect.xBottomRight; col++) {
            for (int row = rect.yTopLeft; row < rect.yBottomRight; row++) {
                img.setRGB(col, row, colour);
            }
        }
    }


    /* ===== TASK 4 ===== */


    public Image greenScreen(Color screenColour, Image backgroundImage) {
        Image keyedImg = this.image;
        ColourRegion region = findLargestRegion(screenColour);

        if (!region.isValidRect()) return keyedImg;

        Rectangle rect = region.toRectangle();

        for (int col = rect.xTopLeft, bgCol = 0; col <= rect.xBottomRight; col++, bgCol++) {
            for (int row = rect.yTopLeft, bgRow = 0; row <= rect.yBottomRight; row++, bgRow++) {

                //for tiling the background image
                if (bgCol >= backgroundImage.width()) {
                    bgCol = 0;
                }
                //for tiling the background image
                if (bgRow >= backgroundImage.height()) {
                    bgRow = 0;
                }

                if (region.pixels[col][row]) {
                    keyedImg.setRGB(col, row, backgroundImage.getRGB(bgCol, bgRow));
                }
            }
        }
        return keyedImg;
    }

    private ColourRegion findLargestRegion(Color screenColour) {
        ColourRegion region = new ColourRegion(0,0,0,0, this.width, this.height);
        boolean[][] visited = new boolean[this.width][this.height];
        int biggestPixelCount = 0;



        for (int col = 0; col < this.width; ++col) {
            for (int row = 0; row < this.height; ++row) {
                if (!visited[col][row] && image.get(col, row).equals(screenColour)) {
                    ColourRegion currentRegion = bfs(col, row, screenColour, visited);

                    if (currentRegion.pixelCount > biggestPixelCount) {
                        biggestPixelCount = currentRegion.pixelCount;
                        region = currentRegion;
                    }
                }
            }
        }

        return region;
    }

    /**
     *
     *
     * @param col
     * @param row
     * @param screenColour
     * @param visited
     * @return pixel count of colours
     */
    private ColourRegion bfs (int col, int row, Color screenColour, boolean[][] visited) {
        Queue<int[]> queue = new LinkedList<>();
        ColourRegion currRegion = new ColourRegion(col, row, col, row, this.width, this.height);

        final int[][] directions = {
                {-1, -1}, {-1, 0}, {-1, 1},
                { 0, -1},          { 0, 1},
                { 1, -1}, { 1, 0}, { 1, 1}
        };

        queue.offer(new int[]{col, row});
        visited[col][row] = true;
        currRegion.pixels[col][row] = true;

        currRegion.pixelCount = 0;

        while (!queue.isEmpty()) {
            int[] pixel = queue.poll();
            int curCol = pixel[0], curRow = pixel[1];
            currRegion.pixelCount++;

            currRegion.xTopLeft     = Math.min(currRegion.xTopLeft,     curCol);
            currRegion.yTopLeft     = Math.min(currRegion.yTopLeft,     curRow);
            currRegion.xBottomRight = Math.max(currRegion.xBottomRight, curCol);
            currRegion.yBottomRight = Math.max(currRegion.yBottomRight, curRow);

            for (int[] direction : directions) {
                int newCol = curCol + direction[1];
                int newRow = curRow + direction[0];

                if (   newCol >= 0 && newCol < this.width
                    && newRow >= 0 && newRow < this.height
                    && !visited[newCol][newRow]
                    && image.get(newCol, newRow).equals(screenColour)
                ) {
                    queue.offer(new int[]{newCol, newRow});
                    visited[newCol][newRow] = true;
                    currRegion.pixels[newCol][newRow] = true;
                }
            }
        }
        return currRegion;
    }

    /* ===== TASK 5 ===== */

    /**
     * determines the angle by which the image should be rotated
     * to get the text aligned
     * @return
     */
    public double getTextAlignmentAngle() {
        int maxDim = Math.max(this.width, this.height);
        int newSize = maxDim == 1 ? 1 : Integer.highestOneBit(maxDim - 1) * 2;
        /*Preprocess
            black and white
            Turn into power of 2 square image (pad with zeros) */
        Complex[][] bwImg = preProcessing(newSize, newSize);
        this.dft = new ImageDFT(bwImg);

        return this.dft.findDominantAngle();
    }

    public Image alignTextImage() {
        double angle = getTextAlignmentAngle();
        double radians = -1.0 * Math.toRadians(angle);

        int newWidth = (int) (Math.abs(width * Math.cos(radians))+ Math.abs(height * Math.sin(radians)));
        int newHeight = (int) (Math.abs(width * Math.sin(radians))+ Math.abs(height * Math.cos(radians)));
        Image rotatedImg = new Image(newWidth, newHeight);

        for(int col = 0; col < newWidth; col++) {
            for(int row = 0; row < newHeight; row++) {
                int original_x = (int) ((col - newWidth / 2.0) * Math.cos(radians) +
                        (row - newHeight / 2.0) * Math.sin(radians) + width / 2.0);
                int original_y = (int) (-(col - newWidth / 2.0) * Math.sin(radians) +
                        (row - newHeight / 2.0) * Math.cos(radians) + height / 2.0);
                if (original_x >= 0 && original_y >= 0 &&
                        original_x < width &&
                        original_y < height ) {
                    rotatedImg.set(col, row, image.get(original_x, original_y));
                } else {
                    rotatedImg.set(col, row, Color.WHITE);
                }
            }
        }

        return rotatedImg;
    }

    /**
     * Obtain the black/white version of the image and pad with black until the next power of 2 size.
     * The image is first grayscaled then rounded to black or white values
     *
     * @return the black/white version of the instance.
     */
    private Complex[][] preProcessing(int newWidth, int newHeight) {
        Image bwImage = new Image(newWidth, newHeight);
        Complex[][] imageMatrix = new Complex[newWidth][newHeight];
        for (int col = 0; col < newWidth; col++) {
            for (int row = 0; row < newHeight; row++) {
                if (col < width && row < height) {
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
}
