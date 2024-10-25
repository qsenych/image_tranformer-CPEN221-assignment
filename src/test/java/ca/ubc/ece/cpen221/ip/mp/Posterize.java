package ca.ubc.ece.cpen221.ip.mp;

import ca.ubc.ece.cpen221.ip.core.Image;
import java.awt.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Posterize {

    @Test
    public void testPosterize_simpleImage() {
        Image originalImage = new Image("resources/posterize-original.png");
        Image expectedImage = new Image("resources/posterize-expected.png");
        ImageTransformer t = new ImageTransformer(originalImage);
        Image outputImage = t.posterize();
        assertEquals(expectedImage, outputImage);
    }

    @Test
    public void testPosterize_default500x500() {
        Color expectedColor = new Color(32, 32, 32);
        Image originalImage = new Image(500, 500);
        Image expectedImage = new Image(500, 500);
        for (int col = 0; col < expectedImage.width(); col++) {
            for (int row = 0; row < expectedImage.height(); row++) {
                expectedImage.set(col, row, expectedColor);
            }
        }
        ImageTransformer t = new ImageTransformer(originalImage);
        Image outputImage = t.posterize();
        assertEquals(expectedImage, outputImage);
    }

    @Test
    public void testPosterize_original64() {
        Color originalColor = new Color(64, 64, 64);
        Color expectedColor = new Color(32, 32, 32);
        Image originalImage = new Image(500, 500);
        Image expectedImage = new Image(500, 500);
        for (int col = 0; col < expectedImage.width(); col++) {
            for (int row = 0; row < expectedImage.height(); row++) {
                originalImage.set(col, row, originalColor);
                expectedImage.set(col, row, expectedColor);
            }
        }
        ImageTransformer t = new ImageTransformer(originalImage);
        Image outputImage = t.posterize();
        assertEquals(expectedImage, outputImage);
    }

    @Test
    public void testPosterize_original65() {
        Color originalColor = new Color(65, 65, 65);
        Color expectedColor = new Color(96, 96, 96);
        Image originalImage = new Image(500, 500);
        Image expectedImage = new Image(500, 500);
        for (int col = 0; col < expectedImage.width(); col++) {
            for (int row = 0; row < expectedImage.height(); row++) {
                originalImage.set(col, row, originalColor);
                expectedImage.set(col, row, expectedColor);
            }
        }
        ImageTransformer t = new ImageTransformer(originalImage);
        Image outputImage = t.posterize();
        assertEquals(expectedImage, outputImage);
    }

    @Test
    public void testPosterize_original128() {
        Color originalColor = new Color(128, 128, 128);
        Color expectedColor = new Color(96, 96, 96);
        Image originalImage = new Image(500, 500);
        Image expectedImage = new Image(500, 500);
        for (int col = 0; col < expectedImage.width(); col++) {
            for (int row = 0; row < expectedImage.height(); row++) {
                originalImage.set(col, row, originalColor);
                expectedImage.set(col, row, expectedColor);
            }
        }
        ImageTransformer t = new ImageTransformer(originalImage);
        Image outputImage = t.posterize();
        assertEquals(expectedImage, outputImage);
    }

    @Test
    public void testPosterize_original129() {
        Color originalColor = new Color(129, 129, 129);
        Color expectedColor = new Color(222, 222, 222);
        Image originalImage = new Image(500, 500);
        Image expectedImage = new Image(500, 500);
        for (int col = 0; col < expectedImage.width(); col++) {
            for (int row = 0; row < expectedImage.height(); row++) {
                originalImage.set(col, row, originalColor);
                expectedImage.set(col, row, expectedColor);
            }
        }
        ImageTransformer t = new ImageTransformer(originalImage);
        Image outputImage = t.posterize();
        assertEquals(expectedImage, outputImage);
    }

    @Test
    public void testPosterize_original255() {
        Color originalColor = new Color(255, 255, 255);
        Color expectedColor = new Color(222, 222, 222);
        Image originalImage = new Image(500, 500);
        Image expectedImage = new Image(500, 500);
        for (int col = 0; col < expectedImage.width(); col++) {
            for (int row = 0; row < expectedImage.height(); row++) {
                originalImage.set(col, row, originalColor);
                expectedImage.set(col, row, expectedColor);
            }
        }
        ImageTransformer t = new ImageTransformer(originalImage);
        Image outputImage = t.posterize();
        assertEquals(expectedImage, outputImage);
    }

}
