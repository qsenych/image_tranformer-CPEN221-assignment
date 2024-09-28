package ca.ubc.ece.cpen221.ip.mp;

import ca.ubc.ece.cpen221.ip.core.Image;

public class Main {

    public static void main(String[] args) {
        Image originalImg = new Image("resources/15088.jpg");
        Image expectedImg = new Image("resources/tests/15088-mirror.png");
        ImageTransformer t = new ImageTransformer(originalImg);
        Image outputImage = t.mirror();

        int i = 0;

    }
}
