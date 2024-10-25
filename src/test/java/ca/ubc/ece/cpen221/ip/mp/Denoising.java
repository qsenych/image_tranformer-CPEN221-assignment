package ca.ubc.ece.cpen221.ip.mp;

import ca.ubc.ece.cpen221.ip.core.Image;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Denoising {
    /*
     * Unit Tests for denoise() in ImageTransformer.java
     */
    @Test
    public void testDenoise_blackNoise() {
        Image originalImage = new Image("resources/blackNoise.png");
        Image expectedImage = new Image("resources/blackDenoise.png");
        ImageTransformer t = new ImageTransformer(originalImage);
        Image outputImage = t.denoise();
        assertEquals(expectedImage, outputImage);
    }

    @Test
    public void testDenoise_redNoise() {
        Image originalImage = new Image("resources/redNoise.png");
        Image expectedImage = new Image("resources/redDenoise.png");
        ImageTransformer t = new ImageTransformer(originalImage);
        Image outputImage = t.denoise();
        assertEquals(expectedImage, outputImage);
    }

    @Test
    public void testDenoise_blueNoise() {
        Image originalImage = new Image("resources/blueNoise.png");
        Image expectedImage = new Image("resources/blueDenoise.png");
        ImageTransformer t = new ImageTransformer(originalImage);
        Image outputImage = t.denoise();
        assertEquals(expectedImage, outputImage);
    }

}
