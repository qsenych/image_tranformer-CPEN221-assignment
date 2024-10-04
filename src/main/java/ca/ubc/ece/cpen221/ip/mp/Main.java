package ca.ubc.ece.cpen221.ip.mp;

import ca.ubc.ece.cpen221.ip.core.Image;

public class Main {

    public static void main(String[] args) {
        Image originalImg = new Image("resources/15088.jpg");
        Image expectedImg = new Image("resources/tests/15088-mirror.png");
        ImageTransformer t = new ImageTransformer(originalImg);
        Image outputImage = t.mirror();


        Image img1 = new Image("resources/cosImg1.png");
        Image img2 = new Image("resources/cosImg2.png");

        double cs = ImageProcessing.cosineSimilarity(img1, img2);



        int i = 0;

    }
}
