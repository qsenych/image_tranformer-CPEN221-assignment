package ca.ubc.ece.cpen221.ip.mp;

import ca.ubc.ece.cpen221.ip.core.Image;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.awt.*;

public class Mirroring {
    @Test
    public void testMirror_singlePixel() {
        /*
         * Tests mirror with an image of 1 pixel
         */

        Image originalImage = new Image(1, 1);
        Image expectedImage = new Image(1, 1);
        ImageTransformer t = new ImageTransformer(originalImage);
        Image outputImage = t.mirror();
        assertEquals(expectedImage, outputImage);
    }

    @Test
    public void testMirror_simpleImage() {
        Color whitePixel = new Color(255, 255, 255);
        Image originalImage = new Image(1000, 1000);
        originalImage.set(0, 0, whitePixel);
        Image expectedImage = new Image(1000, 1000);
        expectedImage.set(999, 0, whitePixel);
        ImageTransformer t = new ImageTransformer(originalImage);
        Image outputImage = t.mirror();
        assertEquals(expectedImage, outputImage);
    }

    @Test
    public void testMirror_centeredLine_oddWidth() {
        /*
         * Tests mirror of an image with a vertical white line about the center, when the image
         * width is odd.
         */

        Color whitePixel = new Color(255, 255, 255);
        Image originalImage = new Image(501, 501);
        Image expectedImage = new Image(501, 501);
        for (int row = 0; row < originalImage.height(); row++) {
            originalImage.set(250, row, whitePixel);
            expectedImage.set(250, row, whitePixel);
        }
        ImageTransformer t = new ImageTransformer(originalImage);
        Image outputImage = t.mirror();
        assertEquals(expectedImage, outputImage);

    }

    @Test
    public void testMirror_centeredLine_evenWidth() {
        /*
         * Tests mirror of an image with a vertical white line about the center, when the image
         * width is even.
         */

        Color whitePixel = new Color(255, 255, 255);
        Image originalImage = new Image(500, 500);
        Image expectedImage = new Image(500, 500);
        for (int row = 0; row < originalImage.height(); row++) {
            originalImage.set(249, row, whitePixel);
            expectedImage.set(250, row, whitePixel);
        }
        ImageTransformer t = new ImageTransformer(originalImage);
        Image outputImage = t.mirror();
        assertEquals(expectedImage, outputImage);
    }

    @Test
    public void testMirror_grayscale() {
        /*
         * Both  the original and expected images are subjected to a grayscale to circumvent any
         * errors due to transparency with PNG image types.
         */

        Image originalImage =
                new Image("resources/mirror-asymmetrical-arrows-original.png");
        Image expectedImage =
                new Image("resources/mirror-asymmetrical-arrows-expected.png");
        ImageTransformer t1 = new ImageTransformer(originalImage);
        ImageTransformer t2 = new ImageTransformer(expectedImage);
        expectedImage = TestUtils.grayscale(expectedImage);
        Image outputImage = TestUtils.grayscale(originalImage);
        ImageTransformer t3 = new ImageTransformer(outputImage);
        outputImage = t3.mirror();
        assertEquals(expectedImage, outputImage);
    }
}
