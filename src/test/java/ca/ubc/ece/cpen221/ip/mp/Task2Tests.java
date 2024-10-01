package ca.ubc.ece.cpen221.ip.mp;

import ca.ubc.ece.cpen221.ip.core.Image;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Task2Tests {
    @Test
    public void test_Denoising() {
        Image originalImg = new Image("resources/noised.png");
        Image expectedImg = new Image("resources/denoised.png");
        ImageTransformer t = new ImageTransformer(originalImg);
        Image outputImage = t.denoise();
        assertEquals(expectedImg, outputImage);
    }

    @Test
    public void test_Weathering() {
        Image originalImg = new Image("resources/95006.jpg");
        Image expectedImg = new Image("resources/tests/95006-weathered.png");
        ImageTransformer t = new ImageTransformer(originalImg);
        Image outputImage = t.weather();
        assertEquals(expectedImg, outputImage);
    }
}
