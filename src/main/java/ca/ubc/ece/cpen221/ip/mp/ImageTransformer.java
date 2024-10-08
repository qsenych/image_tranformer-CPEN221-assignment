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

    public static final double NORMALIZATION_MAX = 254.0;

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

    /**
     * Clips a rectangle of the image
     *
     * @param rect: A non-null rectangle instance that fits within the image
     * @return A smaller image described by the Rectangle
     */
    public Image clip(Rectangle rect) {
        int newWidth = rect.xBottomRight - rect.xTopLeft;
        int newHeight = rect.yBottomRight - rect.yTopLeft;

        if (newHeight > height || newWidth > width) {
            throw new IllegalArgumentException("The rectangle must fit within the image");
        }

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
     * Performs simple denoising by replacing each colour's value on a pixel by the median value of that and its neighbours
     *
     * @return a cleaned up Image.
     */
    public Image denoise() {
        Image cleanImage = new Image(this.width, this.height);
        for (int col = 0; col < this.width; col++) {
            for (int row = 0; row < this.height; row++) {
                int originalPixel = image.getRGB(col, row);

                ArrayList<Integer> reds = new ArrayList<>();
                ArrayList<Integer> greens = new ArrayList<>();
                ArrayList<Integer> blues = new ArrayList<>();

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

                ArrayList<Integer> reds = new ArrayList<>();
                ArrayList<Integer> greens = new ArrayList<>();
                ArrayList<Integer> blues = new ArrayList<>();

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
     * @param rect: The rectangle to be averaged over
     * @return An integer describing the average RGB colour value in the rectangle
     */
    private int averageRectColour(Rectangle rect) {
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


    /**
     * Replaces the largest continuous region of exactly screenColour with the background image
     * the background image will tile if it is not big enough.
     *
     * @param screenColour a non-null Color instance to be matched exactly
     * @param backgroundImage An image to overlay over the largest region of screenColor
     * @return The original image with background image overlayed over a rectangle of the largest
     *      continuous region of screenColour. If exactly screenColour is not on the image,
     *      an unchanged image is returned.
     */
    public Image greenScreen(Color screenColour, Image backgroundImage) {
        Image keyedImg = this.image;
        ColourRegion region = findLargestRegion(screenColour);

        if (!region.isValidRect()) {
            return keyedImg;
        }


        for (int col = region.xTopLeft, bgCol = 0; col <= region.xBottomRight; col++, bgCol++) {
            for (int row = region.yTopLeft, bgRow = 0; row <= region.yBottomRight; row++, bgRow++) {

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

    /**
     * A method to find the largest region of exactly screenColour by number of pixels on the image
     *
     * @param screenColour the exact colour to find the largest region of.
     * @return A ColourRegion object containing information such as the matching pixel count,
     *      the enclosing rectangle of the region, and a matrix of booleans corresponding to the
     *      matching pixels on the original image.
     */
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
     * An implementation of breadth first search for finding the size of a contiguous region of screenColour pixels
     *
     * @param col the column of the first matching pixel in the new region
     * @param row the row of the first matching pixel in the new region
     * @param screenColour the exact colour of the pixel to be matched
     * @param visited a boolean matrix keeping track of which pixels have been visited or not
     * @return A colour region describing pixel count of the colour found, the rectangle enclosing it, and the
     *      exact matching pixels on the original image
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
     * Determines the angle by which the image should be rotated in order to align the text.
     * If the image does not need aligning, the image will be rotated off axis by +- ~3 degrees
     *
     * @return a double between -90 and 90 to rotate the image by
     */
    public double getTextAlignmentAngle() {
        this.dft = new ImageDFT(this.image);
        return this.dft.findDominantAngle();
    }

    /**
     * Aligns the text in an image to the horizontal.
     * The image must need aligning or the text will be misaligned +- ~3 degrees
     *
     * @return An image with the corrected text angle, its size is the same or larger to the original image
     */
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


    /* ===== TASK 5 DEBUG METHODS ===== */

    /**
     * debug method to turn a complex matrix into an image for visualization
     *
     * @param matrixImg A non-empty matrix representation of an image
     * @param width the width of the matrixImg
     * @param height the height of the matrixImg
     * @param name a string for the name of the image to save as (is passed into a save method)
     * @return A grayscale image of the matrixImg representation
     */
    public static Image complexMatrixToImage(Complex[][] matrixImg, int width, int height, String name) {
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
     * debug method to turn a double matrix into an image for visualization.
     * The matrixImg should be passed through normalizeToGray to see actual contrast
     * Otherwise, most values will be very nearly black.
     * Also saves the image as "name"
     *
     * @param matrixImg A non-empty matrix representation of an image
     * @param width the width of the matrixImg
     * @param height the height of the matrixImg
     * @param name a string for the name of the image to save as (is passed into Image method save)
     * @return A grayscale image of the matrixImg representation
     */
    public static Image doubleMatrixToImage(double[][] matrixImg, int width, int height, String name) {
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

    /**
     * A debug method intended for use with doubleMatrixToImage but not necessary.
     *
     * @param matrixImg A non-empty matrix representation of an image
     * @param width the width of the matrixImg
     * @param height the height of the matrixImg
     * @return A double matrix normalized between NORMALIZATION_MAX and 0.0
     */
    public static double[][] normalizeToGray(double[][] matrixImg, int width, int height) {
        double max = 0.0;
        for(int col = 0; col < width; col++) {
            for(int row = 0; row < height; row++) {
                if (matrixImg[col][row] > max) max = matrixImg[col][row];
            }
        }
        for(int col = 0; col < width; col++) {
            for(int row = 0; row < height; row++) {
                matrixImg[col][row] = matrixImg[col][row] * NORMALIZATION_MAX / max;
            }
        }
        return matrixImg;
    }
}
