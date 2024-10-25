package ca.ubc.ece.cpen221.ip.mp;

import ca.ubc.ece.cpen221.ip.core.Image;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BlockPainting {
    /*
     * Unit Tests for blocKPaint() in ImageTransformer.java
     */
    @Test
    public void testBlockPaint_8x8() {
        Image originalImage = new Image(8, 8);
        Image expectedImage = new Image(8, 8);
        Color expectedPixel = new Color(127, 127, 127, 255);
        Color newPixel;
        Color c1 = new Color(191, 64, 191, 255);
        Color c2 = new Color(64, 191, 64, 255);
        for (int col = 0; col < originalImage.width(); col++) {
            for (int row = 0; row < originalImage.height(); row++) {
                switch ((col + row) % 2) {
                    case 0:
                        newPixel = c1;
                        break;
                    default:
                        newPixel = c2;
                        break;
                }
                originalImage.set(col, row, newPixel);
            }
        }
        for (int col = 0; col < expectedImage.width(); col++) {
            for (int row = 0; row < expectedImage.height(); row++) {
                expectedImage.set(col, row, expectedPixel);
            }
        }
        ImageTransformer t = new ImageTransformer(originalImage);
        Image outputImage = t.blockPaint(2);
        assertEquals(expectedImage, outputImage);
    }

    @Test
    public void testBlockPaint_7x7() {
        Image originalImage = new Image(7, 7);
        Image expectedImage = new Image(7, 7);
        Color expectedPixel, newPixel;
        Color c1 = new Color(191, 64, 191, 255);
        Color c2 = new Color(64, 191, 64, 255);
        Color c3 = new Color(127, 127, 127, 255);
        for (int col = 0; col < originalImage.width(); col++) {
            for (int row = 0; row < originalImage.height(); row++) {
                switch ((col + row) % 2) {
                    case 0:
                        newPixel = c1;
                        break;
                    default:
                        newPixel = c2;
                        break;
                }
                originalImage.set(col, row, newPixel);
            }
        }
        for (int col = 0; col < expectedImage.width(); col++) {
            for (int row = 0; row < expectedImage.height(); row++) {
                if (row == 6 && col == 6) {
                    expectedPixel = c1;
                } else {
                    expectedPixel = c3;
                }
                expectedImage.set(col, row, expectedPixel);
            }
        }
        ImageTransformer t = new ImageTransformer(originalImage);
        Image outputImage = t.blockPaint(2);
        assertEquals(expectedImage, outputImage);
    }

    @Test
    public void testBlockPaint_3x3() {
        Image originalImage = new Image(3, 3);
        Image expectedImage = new Image(3, 3);
        Color newPixel;
        Color c1 = new Color(191, 64, 191, 255);
        Color c2 = new Color(64, 191, 64, 255);
        Color expectedPixel = new Color(134, 120, 134, 255);
        for (int col = 0; col < originalImage.width(); col++) {
            for (int row = 0; row < originalImage.height(); row++) {
                switch ((col + row) % 2) {
                    case 0:
                        newPixel = c1;
                        break;
                    default:
                        newPixel = c2;
                        break;
                }
                originalImage.set(col, row, newPixel);
            }
        }
        for (int col = 0; col < expectedImage.width(); col++) {
            for (int row = 0; row < expectedImage.height(); row++) {
                expectedImage.set(col, row, expectedPixel);
            }
        }
        ImageTransformer t = new ImageTransformer(originalImage);
        Image outputImage = t.blockPaint(3);
        assertEquals(expectedImage, outputImage);
    }

}
