package ca.ubc.ece.cpen221.ip.mp;

import ca.ubc.ece.cpen221.ip.core.Image;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Weathering {
    /*
     * Unit Tests for weather() in ImageTransformer.java
     */
    @Test
    public void testWeathering_simple() {
        Image originalImg = new Image("resources/weather-original.png");
        Image expectedImg = new Image("resources/weather-expected.png");
        ImageTransformer t = new ImageTransformer(originalImg);
        Image outputImage = t.weather();
        assertEquals(expectedImg, outputImage);
    }

    @Test
    public void testWeathering_default500x500() {
        Image originalImage = new Image(500, 500);
        Image expectedImage = new Image(500, 500);
        ImageTransformer t = new ImageTransformer(originalImage);
        Image outputImage = t.weather();
        assertEquals(expectedImage, outputImage);
    }

    @Test
    public void testWeathering_allWhite() {
        Color whitePixel = new Color(255, 255, 255);
        Image originalImage = new Image(500, 500);
        Image expectedImage = new Image(500, 500);
        for (int col = 0; col < originalImage.width(); col++) {
            for (int row = 0; row < originalImage.height(); row++) {
                originalImage.set(col, row, whitePixel);
                expectedImage.set(col, row, whitePixel);
            }
        }
        ImageTransformer t = new ImageTransformer(originalImage);
        Image outputImage = t.weather();
        assertEquals(expectedImage, outputImage);
    }

    @Test
    public void testWeathering_r32g96b222() {
        Color whitePixel = new Color(32, 96, 222);
        Image originalImage = new Image(500, 500);
        Image expectedImage = new Image(500, 500);
        for (int col = 0; col < originalImage.width(); col++) {
            for (int row = 0; row < originalImage.height(); row++) {
                originalImage.set(col, row, whitePixel);
                expectedImage.set(col, row, whitePixel);
            }
        }
        ImageTransformer t = new ImageTransformer(originalImage);
        Image outputImage = t.weather();
        assertEquals(expectedImage, outputImage);
    }

    @Test
    public void testWeathering_test5() {
        Image originalImage = new Image("resources/weather-test5.png");
        Image expectedImage = new Image("resources/weather-test5-expected.png");
        ImageTransformer t = new ImageTransformer(originalImage);
        Image outputImage = t.weather();
        assertEquals(expectedImage, outputImage);
    }
}
