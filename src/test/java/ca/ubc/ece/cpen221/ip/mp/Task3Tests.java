package ca.ubc.ece.cpen221.ip.mp;

import ca.ubc.ece.cpen221.ip.core.Image;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class Task3Tests {
    @Test
    public void TestCS () {
        Image img1 = new Image("resources/cosImg1.png");
        Image img2 = new Image("resources/cosImg2.png");

        double cs = ImageProcessing.cosineSimilarity(img1, img2);
        assertEquals(cs, 0.578947, 0.001);
    }

    @Test
    public void TestCS2 () {
        Image img1 = new Image("resources/cosImg3.png");
        Image img2 = new Image("resources/cosImg4.png");

        double cs = ImageProcessing.cosineSimilarity(img1, img2);
        assertEquals(cs, 1.0, 0.001);
    }

    /* test black image with image of non-constant colour */
    @Test
    public void TestCS3 () {
        Image img1 = new Image("resources/cosImg1.png");
        Image img2 = new Image("resources/cosImg5.png");

        double cs = ImageProcessing.cosineSimilarity(img1, img2);
        assertEquals(cs, 0.0, 0.001);
    }

    /* test black image with image of constant colour */
    @Test
    public void TestCS4 () {
        Image img1 = new Image("resources/cosImg4.png");
        Image img2 = new Image("resources/cosImg5.png");

        double cs = ImageProcessing.cosineSimilarity(img1, img2);
        assertEquals(cs, 1.0, 0.001);
    }

    /* test large, disimelar images*/
    @Test
    public void TestCS5 () {
        Image img1 = new Image("resources/95006.jpg");
        Image img2 = new Image("resources/28096.jpg");

        double cs = ImageProcessing.cosineSimilarity(img1, img2);
        assertEquals(cs, 0.86814833, 0.001);
    }

    /* List test with 3x3 custom images */
    @Test
    public void TestCSList1 () {
        Image img1 = new Image("resources/cosImg1.png");
        Image img2 = new Image("resources/cosImg2.png");
        Image img3 = new Image("resources/cosImg3.png");
        Image img4 = new Image("resources/cosImg4.png");
        Image img5 = new Image("resources/cosImg5.png");

        List<Image> comparables = new ArrayList<Image>();
        List<Image> expectedComparables = new ArrayList<Image>();
        List<Image> results = new ArrayList<Image>();

        comparables.add(img1);
        comparables.add(img2);
        comparables.add(img3);
        comparables.add(img4);
        comparables.add(img5);

        expectedComparables.add(img1);
        expectedComparables.add(img3);
        expectedComparables.add(img4);
        expectedComparables.add(img2);
        expectedComparables.add(img5);

        results = ImageProcessing.bestMatch(img1, comparables);
        assertEquals(results, expectedComparables);
    }

    @Test
    public void TestCSListBig () {
        Image img1 = new Image("resources/95006.jpg");
        Image img2 = new Image("resources/tests/95006-seurat-4x4.png");
        Image img3 = new Image("resources/tests/95006-weathered.png");
        Image img4 = new Image("resources/28096.jpg");
        Image img5 = new Image("resources/247085.jpg");

        List<Image> comparables = new ArrayList<Image>();
        List<Image> expectedComparables = new ArrayList<Image>();
        List<Image> results = new ArrayList<Image>();

        comparables.add(img2);
        comparables.add(img5);
        comparables.add(img1);
        comparables.add(img4);
        comparables.add(img3);

        results = ImageProcessing.bestMatch(img1, comparables);

        expectedComparables.add(img1);
        expectedComparables.add(img2);
        expectedComparables.add(img3);
        expectedComparables.add(img4);
        expectedComparables.add(img5);

        assertEquals(results, expectedComparables);
    }

    @Test
    public void TestCSListShort () {
        Image img1 = new Image("resources/95006.jpg");
        Image img5 = new Image("resources/247085.jpg");

        List<Image> comparables = new ArrayList<Image>();
        List<Image> expectedComparables = new ArrayList<Image>();
        List<Image> results = new ArrayList<Image>();

        comparables.add(img5);

        results = ImageProcessing.bestMatch(img1, comparables);

        expectedComparables.add(img5);

        assertEquals(results, expectedComparables);
    }
}
