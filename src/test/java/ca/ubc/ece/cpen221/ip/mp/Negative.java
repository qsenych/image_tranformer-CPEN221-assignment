package ca.ubc.ece.cpen221.ip.mp;

import java.awt.*;
import ca.ubc.ece.cpen221.ip.core.Image;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Negative {
    @Test
    public void testNegative1() {
        Image originalImage = new Image(500, 500);
        ImageTransformer t = new ImageTransformer(originalImage);
        Image expectedImage = new Image(500, 500);
        Color whitePixel = new Color(255, 255, 255, 255);
        for (int col = 0; col < originalImage.width(); col++) {
            for (int row = 0; row < originalImage.height(); row++) {
                expectedImage.set(col, row, whitePixel);
            }
        }
        Image outputImage = t.negative();
        assertEquals(expectedImage, outputImage);
    }

    @Test
    public void testNegative2() {
        Image originalImage = new Image(500, 500);
        Color whitePixel = new Color(255, 255, 255, 255);
        for (int col = 0; col < originalImage.width(); col++) {
            for (int row = 0; row < originalImage.height(); row++) {
                originalImage.set(col, row, whitePixel);
            }
        }
        ImageTransformer t = new ImageTransformer(originalImage);
        Image expectedImage = new Image(500, 500);
        Image outputImage = t.negative();
        assertEquals(expectedImage, outputImage);
    }

    @Test
    public void testNegative_colorPalette() {
        /*
         * Uses an image of a colour palette as original image,
         * and used online negative converter to create expected output.
         */

        Image originalImage = new Image("resources/colour-palette.png");
        Image expectedImage = new Image("resources/colour-palette-negative.png");
        ImageTransformer t = new ImageTransformer(originalImage);
        Image outputImage = t.negative();
        assertEquals(expectedImage, outputImage);
    }

}
