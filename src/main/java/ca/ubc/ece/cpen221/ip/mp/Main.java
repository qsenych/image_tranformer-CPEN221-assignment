package ca.ubc.ece.cpen221.ip.mp;

import ca.ubc.ece.cpen221.ip.core.Image;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        Image img1 = new Image("resources/95006.jpg");
        Image img2 = new Image("resources/tests/95006-seurat-4x4.png");
        Image img3 = new Image("resources/tests/95006-weathered.png");
        Image img4 = new Image("resources/28096.jpg");
        Image img5 = new Image("resources/247085.jpg");

        List<Image> comparables = new ArrayList<Image>();
        List<Image> expectedComparables = new ArrayList<Image>();
        List<Image> results = new ArrayList<Image>();

        comparables.add(img1);
        comparables.add(img2);
        comparables.add(img3);
        comparables.add(img4);
        comparables.add(img5);

        results = ImageProcessing.bestMatch(img1, comparables);

        expectedComparables.add(img1);
        expectedComparables.add(img2);
        expectedComparables.add(img3);
        expectedComparables.add(img4);
        expectedComparables.add(img5);



        Image originalImg = new Image("resources/15088.jpg");
        Image expectedImg = new Image("resources/tests/15088-mirror.png");
        ImageTransformer t = new ImageTransformer(originalImg);
        Image outputImage = t.mirror();


        double cs = ImageProcessing.cosineSimilarity(img1, img2);



        int i = 0;

    }
}
