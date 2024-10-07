package ca.ubc.ece.cpen221.ip.mp;

import ca.ubc.ece.cpen221.ip.core.Image;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Task5Tests {
    @Test
    public void angleTest0() {
        Image img = new Image("resources/tests/testRotate45.png");
        double expected = 45.0;

        ImageTransformer t1 = new ImageTransformer(img);
        double result = t1.getTextAlignmentAngle();
        assertEquals(expected, result);
    }

    @Test
    public void angleTest1() {
        Image img = new Image("resources/tests/squareWordsRotate23.png");
        double expected = 23.0;

        ImageTransformer t1 = new ImageTransformer(img);
        double result = t1.getTextAlignmentAngle();
        assertEquals(expected, result);
    }

    @Test
    public void angleTest2() {
        Image img = new Image("resources/tests/textRect38.png");
        double expected = -37.0;

        ImageTransformer t1 = new ImageTransformer(img);
        double result = t1.getTextAlignmentAngle();
        assertEquals(expected, result);
    }

    @Test
    public void angleTest4() {
        Image img = new Image("resources/tests/12003-r75.png");
        double expected = 75.0;

        ImageTransformer t1 = new ImageTransformer(img);
        double result = t1.getTextAlignmentAngle();
        assertEquals(expected, result);
    }

    @Test
    public void angleTest5() {
        Image img = new Image("resources/tests/rotate-15.png");
        double expected = -15.0;

        ImageTransformer t1 = new ImageTransformer(img);
        double result = t1.getTextAlignmentAngle();
        assertEquals(expected, result);
    }

    @Test
    public void imgTest1() {
        Image img = new Image("resources/tests/textRect38.png");
        Image expected = new Image("resources/tests/textRect38result.png");
        ImageTransformer t1 = new ImageTransformer(img);
        Image result = t1.alignTextImage();

        double sim = ImageProcessing.cosineSimilarity(expected, result);
        assertEquals(sim > 0.9, true);
    }

    @Test
    public void imgTest2() {
        Image img = new Image("resources/tests/12003-r30.png");
        Image expected = new Image("resources/tests/12003-r30result.png");
        ImageTransformer t1 = new ImageTransformer(img);
        Image result = t1.alignTextImage();

        double sim = ImageProcessing.cosineSimilarity(expected, result);
        assertEquals(sim > 0.9, true);
    }

    @Test
    public void imgTest3() {
        Image img = new Image("resources/tests/Descartes-50.png");
        Image expected = new Image("resources/tests/Descartes-50result.png");
        ImageTransformer t1 = new ImageTransformer(img);
        Image result = t1.alignTextImage();

        double sim = ImageProcessing.cosineSimilarity(expected, result);
        assertEquals(sim > 0.9, true);
    }

    @Test
    public void imgTest4() {
        Image img = new Image("resources/tests/Williamson-45.png");
        Image expected = new Image("resources/tests/Williamson-45result.png");
        ImageTransformer t1 = new ImageTransformer(img);
        Image result = t1.alignTextImage();

        double sim = ImageProcessing.cosineSimilarity(expected, result);
        assertEquals(sim > 0.9, true);
    }
}
