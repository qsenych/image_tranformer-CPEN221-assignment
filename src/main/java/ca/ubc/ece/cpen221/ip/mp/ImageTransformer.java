package ca.ubc.ece.cpen221.ip.mp;

import ca.ubc.ece.cpen221.ip.core.Image;
import ca.ubc.ece.cpen221.ip.core.ImageProcessingException;
import ca.ubc.ece.cpen221.ip.core.Rectangle;

import java.awt.Color;
import java.math.BigInteger;
import java.util.Collections;
import java.util.ArrayList;

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

    /**
     * Creates an ImageTransformer with an image. The provided image is
     * <strong>never</strong> changed by any of the operations.
     *
     * @param img is not null
     */
    public ImageTransformer(Image img) {
        // TODO: Implement this method

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
        // TODO: Implement this method
        return null;
    }

    /* ===== TASK 5 ===== */

    public Image alignTextImage() {
        // TODO: Implement this method
        return null;
    }
}
