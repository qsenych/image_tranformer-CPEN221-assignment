package ca.ubc.ece.cpen221.ip.mp;

import ca.ubc.ece.cpen221.ip.core.Image;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class Task3Tests {
    @Test
    public void TestCS () {
        Image img1 = new Image("resources/cosImg1.png");
        Image img2 = new Image("resources/cosImg2.png");

        double cs = ImageProcessing.cosineSimilarity(img1, img2);
        assertEquals(cs, 0.578947, 0.001);
    }

    @Test
    public void TestCS2 () {
        Image img1 = new Image("resources/cosImg3.png");
        Image img2 = new Image("resources/cosImg4.png");

        double cs = ImageProcessing.cosineSimilarity(img1, img2);
        assertEquals(cs, 1.0, 0.001);
    }
}
