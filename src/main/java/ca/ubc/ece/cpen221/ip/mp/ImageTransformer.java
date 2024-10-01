package ca.ubc.ece.cpen221.ip.mp;

import ca.ubc.ece.cpen221.ip.core.Image;
import ca.ubc.ece.cpen221.ip.core.ImageProcessingException;
import ca.ubc.ece.cpen221.ip.core.Rectangle;

import java.awt.Color;
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
        // TODO: Implement this method
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
        // TODO: Implement this method
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

    public Image posterize() {
        // TODO: Implement this method
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

    public Image denoise() {
        // TODO: Implement this method
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
                    for(int j = 1; j >= -1; j--) {
                        if(col + i >= 0 && col + i < this.width && row + j >= 0 && row + j < this.height) {
                            reds.add((image.getRGB(col + i, row + j) >> 16) & 0xFF);
                            greens.add((image.getRGB(col + i, row + j) >> 8) & 0xFF);
                            blues.add(image.getRGB(col + i, row + j) & 0xFF);
                            /*
                            redSum += (image.getRGB(col + i, row + j) >> 16) & 0xFF;
                            greenSum += (image.getRGB(col + i, row + j) >> 8) & 0xFF;
                            blueSum += image.getRGB(col + i, row + j) & 0xFF;
                            neighbourCount++;

                             */
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
     * @param an unsorted ArrayList of integers
     * @return the median of the ArrayList as an integer
     */
    private int listMedian(ArrayList<Integer> list) {
        Collections.sort(list);

        int middle;

        if(list.size() % 2 == 1) {
            middle = (list.get(list.size() / 2) + list.get(list.size() / 2 - 1)) / 2;
        } else {
            middle = list.get(list.size() / 2);
        }

        return middle;
    }

    /**
     * @return a weathered version of the image.
     */
    public Image weather() {
        // TODO: Implement this method
        return null;
    }

    public Image blockPaint(int blockSize) {
        // TODO: Implement this method
        return null;
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
