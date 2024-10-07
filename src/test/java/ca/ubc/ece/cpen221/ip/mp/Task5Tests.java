package ca.ubc.ece.cpen221.ip.mp;

import ca.ubc.ece.cpen221.ip.core.Image;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Task5Tests {
    @Test
    public void angleTest1() {
        Image smallGreen = new Image("resources/smallGreen.png");
        double expected = 23.0;

        ImageTransformer t1 = new ImageTransformer(smallGreen);

    }
}
