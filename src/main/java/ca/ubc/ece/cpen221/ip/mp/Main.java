package ca.ubc.ece.cpen221.ip.mp;

import ca.ubc.ece.cpen221.ip.core.Image;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {


        Image img3 = new Image("resources/tests/textRect38.png");

        ImageTransformer t2 = new ImageTransformer(img3);

        Image outImg = t2.alignTextImage();
        outImg.save("resources/rotatedImg.png");

        int i = 0;

    }
}
