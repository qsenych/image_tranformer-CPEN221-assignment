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

}
