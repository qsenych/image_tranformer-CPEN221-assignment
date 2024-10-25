package ca.ubc.ece.cpen221.ip.mp;

import ca.ubc.ece.cpen221.ip.core.Image;
import org.junit.jupiter.api.Test;

import java.awt.Color;
import java.util.List;
import java.util.Arrays;

import static ca.ubc.ece.cpen221.ip.mp.ImageProcessing.cosineSimilarity;
import static org.junit.jupiter.api.Assertions.*;

public class Similarity {
    /*
     * Unit Tests for cosineSimilarity()
     */

    @Test
    public void testCosineSimilarity_allWhite() {

        /*
         * Tests two white images
         */

        Image img1 = new Image(500, 500);
        Image img2 = new Image(500, 500);
        Color whitePixel = new Color(255, 255, 255);
        for (int col = 0; col < img1.width(); col++) {
            for (int row = 0; row < img1.height(); row++) {
                img1.set(col, row, whitePixel);
                img2.set(col, row, whitePixel);
            }
        }
        double dotProduct = 500 * Math.pow(255, 2);
        double vectorLength = Math.sqrt(dotProduct);
        double expectedCS = dotProduct / (Math.pow(vectorLength, 2));
        try {
            assertEquals(expectedCS, cosineSimilarity(img1, img2), 0.01);
        }
        catch (Exception e) {
            fail("Exception unexpected for valid images.");
        }
    }

    @Test
    public void testCosineSimilarity_allBlack() {

        /*
         * Tests two black images
         */

        Image img1 = new Image(500, 500);
        Image img2 = new Image(500, 500);
        try {
            cosineSimilarity(img1, img2);
        }
        catch (Exception e) {
            fail("Exception unexpected for valid images.");
        }
        assertTrue(true);
    }

    @Test
    public void testCosineSimilarity_oneBlack() {

        /*
         * Tests one black image, but not two
         */

        Image img1 = new Image(500, 500);
        Image img2 = new Image(500, 500);
        img2.set(0, 0, new Color(255, 255, 255));
        try {
            double cosineCS = cosineSimilarity(img1, img2);
        }
        catch (Exception e) {
            fail("Exception unexpected for valid images.");
        }
        assertTrue(true);
    }

    @Test
    public void testCosineSimilarity_3x3() {

        /*
         * Tests identical 3x3 images
         */
        Image img1 = new Image(3, 3);
        Color redPixel = new Color(255, 0, 0);
        Color greenPixel = new Color(0, 255, 0);
        Color bluePixel = new Color(0, 0, 255);
        img1.set(0, 0, redPixel);
        img1.set(1, 0, greenPixel);
        img1.set(2, 0, bluePixel);
        img1.set(0, 1, bluePixel);
        img1.set(1, 1, redPixel);
        img1.set(2, 1, greenPixel);
        img1.set(0, 2, greenPixel);
        img1.set(1, 2, bluePixel);
        img1.set(2, 2, redPixel);
        Image img2 = new Image(img1);
        try {
            assertEquals(1, cosineSimilarity(img1, img2), 0.01);
        }
        catch (Exception e) {
            fail("Exception unexpected for valid images.");
        }
    }

    @Test
    public void testCosineSimilarity_15088() {

        /*
         * Tests two identical images
         */

        Image img1 = new Image("resources/15088.jpg");
        Image img2 = new Image(img1);
        try {
            assertEquals(1, cosineSimilarity(img1, img2), 0.01);
        }
        catch (Exception e) {
            fail("Exception unexpected for valid images.");
        }
    }

    public void testCosineSimilarity_differingSizes() {

        /*
         * Tests for IllegalArgumentException
         */

        Image img1 = new Image(500, 500);
        Image img2 = new Image(499, 500);
        double cs = 0.0;
        try {
            cs = cosineSimilarity(img1, img2);
        }
        catch (IllegalArgumentException iae) {
            assertTrue(true);
        }
        catch (Exception e) {
            fail("IllegalArgumentException should be the choice of exception.");
        }
        assertEquals(0, cs, 0.01, "Maybe 0.0 as cosine similarity is okay.");
    }

    @Test
    public void testCosineSimilarity_74() {
        Image img1 = new Image(3, 3);
        Color red = new Color(255, 0, 0);
        Color green = new Color(0, 255, 0);
        Color blue = new Color(0, 0, 255);
        img1.set(0, 0, red);
        img1.set(1, 0, green);
        img1.set(2, 0, blue);
        img1.set(0, 1, blue);
        img1.set(1, 1, red);
        img1.set(2, 1, green);
        img1.set(0, 2, green);
        img1.set(1, 2, blue);
        img1.set(2, 2, red);
        ImageTransformer t = new ImageTransformer(img1);
        Image img2 = t.mirror();
        try {
            assertEquals(0.74441, cosineSimilarity(img1, img2), 0.01);
        }
        catch (Exception e) {
            fail("Exception unexpected for valid images.");
        }
    }
}
