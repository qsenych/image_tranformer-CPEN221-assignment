package ca.ubc.ece.cpen221.ip.mp;

import ca.ubc.ece.cpen221.ip.core.Image;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {


        Image img1 = new Image("resources/tests/smallwordsBlackRotate45.png");
        Image img2 = new Image("resources/tests/textRect38.png");
        Image img3 = new Image("resources/tests/squareWordsRotate23.png");
        Image img4 = new Image("resources/tests/squareLineRotate60.png");
        Image img5 = new Image("resources/tests/exampleText.png");
        Image img6 = new Image("resources/tests/testRotate45.png");
        Image img7 = new Image("resources/tests/12003-r30.png");
        Image img8 = new Image("resources/tests/12003-r75.png");
        Image img9 = new Image("resources/tests/rotate-15.png");
        Image img = new Image("resources/tests/blockRotate-37.png");

        ImageTransformer t2 = new ImageTransformer(img7);

        Image outImg = t2.alignTextImage();
        outImg.save("resources/tests/12003-r30result.png");

        int i = 0;

    }
}
