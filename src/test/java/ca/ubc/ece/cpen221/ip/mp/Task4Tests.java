package ca.ubc.ece.cpen221.ip.mp;

import ca.ubc.ece.cpen221.ip.core.Image;

import ca.ubc.ece.cpen221.ip.core.Rectangle;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Task4Tests {
    @Test
    public void greenTest1() {
        Image smallGreen = new Image("resources/smallGreen.png");
        Image pink = new Image("resources/pink16x16.png");
        Image expected = new Image("resources/smallGreenResult.png");

        ImageTransformer t1 = new ImageTransformer(smallGreen);
        Image result = t1.greenScreen(Color.GREEN, pink);
        assertEquals(expected, result);

    }

    /* test no matching colour */
    @Test
    public void greenTest2() {
        Image smallGreen = new Image("resources/smallGreen.png");
        Image pink = new Image("resources/pink16x16.png");
        Image expected = new Image("resources/smallGreen.png");

        ImageTransformer t1 = new ImageTransformer(smallGreen);
        Image result = t1.greenScreen(Color.RED, pink);
        assertEquals(expected, result);
    }

    /* test tiling */
    @Test
    public void greenTest3() {
        Image img1 = new Image("resources/clapper-green.jpg");
        Image tiger = new Image("resources/187039.jpg");
        Image expected = new Image("resources/clapper187039.png");
        Color greenish = new Color (0x0D, 0xBE, 0x0E);

        ImageTransformer t1 = new ImageTransformer(img1);
        Image result = t1.greenScreen(greenish, tiger);
        assertEquals(expected, result);
    }

    /* test 2 single pixels */
    @Test
    public void greenTest4() {
        Image img1 = new Image("resources/tests/2-small-identicle-green.png");
        Image background = new Image("resources/tests/2-small-identicle-green.png");
        Image expected = new Image("resources/tests/2-small-identicle-greenredresult.png");

        ImageTransformer t1 = new ImageTransformer(img1);
        Image result = t1.greenScreen(Color.GREEN, background);
        assertEquals(expected, result);
    }

}
