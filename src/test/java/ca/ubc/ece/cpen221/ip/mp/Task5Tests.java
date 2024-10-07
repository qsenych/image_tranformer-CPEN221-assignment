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
    public void angleTest3() {
        Image img = new Image("resources/tests/12003-r30.png");
        double expected = 30.0;

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
    public void angleTest6() {
        Image img = new Image("resources/tests/rotate-15.png");
        double expected = -15.0;

        ImageTransformer t1 = new ImageTransformer(img);
        double result = t1.getTextAlignmentAngle();
        assertEquals(expected, result);
    }
}
