package ca.ubc.ece.cpen221.ip.mp;

import ca.ubc.ece.cpen221.ip.core.Image;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class BestMatch {
    Image baselineImage;
    Image alteredImage1;
    Image alteredImage2;

    public BestMatch() {
        baselineImage = new Image("resources/15004.jpg");

        alteredImage1 = new Image("resources/15004.jpg");
        alteredImage1.set(0, 0, new Color(255, 255, 255));
        alteredImage1.set(1, 0, new Color(0, 255, 255));
        alteredImage1.set(2, 0, new Color(255, 255, 255));
        alteredImage1.set(0, 1, new Color(0, 255, 255));

        alteredImage2 = new Image("resources/15004.jpg");
        alteredImage2.set(0, 0, new Color(255, 255, 255));
        alteredImage2.set(1, 0, new Color(0, 255, 255));
        alteredImage2.set(2, 0, new Color(255, 255, 255));
        alteredImage2.set(0, 1, new Color(0, 255, 255));
        alteredImage2.set(1, 1, new Color(255, 255, 255));
        alteredImage2.set(3, 3, new Color(200, 255, 200));
    }

    @Test
    public void testBestMatch_identicalImage1() {
        List<Image> potentialMatches = Arrays.asList(
                baselineImage
        );
        Image bestMatch = null;
        try {
            bestMatch = ImageProcessing.bestMatch(baselineImage, potentialMatches).get(0);
        }
        catch (Exception e) {
            fail("bestMatch: exception unexpected!");
        }
        assertEquals(baselineImage, bestMatch, "bestMatch: incorrect match");
    }
    @Test
    public void testBestMatch_identicalImage2() {
        List<Image> potentialMatches = Arrays.asList(
                new Image("resources/12074.jpg"),
                new Image("resources/12003.jpg"),
                new Image("resources/8143.jpg"),
                baselineImage,
                new Image("resources/104022.jpg")
        );
        Image bestMatch = null;
        try {
            bestMatch = ImageProcessing.bestMatch(baselineImage, potentialMatches).get(0);
        }
        catch (Exception e) {
            fail("bestMatch: exception unexpected!");
        }
        assertEquals(baselineImage, bestMatch, "bestMatch: incorrect match");
    }

    @Test
    public void testBestMatch_goodMatch1() {
        Image baselineImage = new Image("resources/15004.jpg");



        List<Image> potentialMatches = Arrays.asList(
                new Image("resources/12074.jpg"),
                new Image("resources/12003.jpg"),
                new Image("resources/8143.jpg"),
                alteredImage1,
                new Image("resources/104022.jpg")
        );
        Image bestMatch = null;
        try {
            bestMatch = ImageProcessing.bestMatch(baselineImage, potentialMatches).get(0);
        }
        catch (Exception e) {
            fail("bestMatch: exception unexpected!");
        }
        assertEquals(alteredImage1, bestMatch, "bestMatch: incorrect match");
    }

    @Test
    public void testBestMatch_goodMatch2() {
        Image baselineImage = new Image("resources/15004.jpg");

        List<Image> potentialMatches = Arrays.asList(
                new Image("resources/12074.jpg"),
                new Image("resources/12003.jpg"),
                new Image("resources/8143.jpg"),
                alteredImage2,
                alteredImage1,
                new Image("resources/104022.jpg")
        );
        Image bestMatch = null;
        Image secondBestMatch = null;
        try {
            List<Image> matchOrder = ImageProcessing.bestMatch(baselineImage, potentialMatches);
            bestMatch = matchOrder.get(0);
            secondBestMatch = matchOrder.get(1);
        }
        catch (Exception e) {
            fail("bestMatch: exception unexpected!");
        }
        assertEquals(alteredImage1, bestMatch, "bestMatch: incorrect match");
        assertEquals(alteredImage2, secondBestMatch, "bestMatch: incorrect second best match");
    }

    @Test
    public void testBestMatch_listsize() {
        Image baselineImage = new Image("resources/15004.jpg");

        List<Image> potentialMatches = Arrays.asList(
                new Image("resources/12074.jpg"),
                new Image("resources/12003.jpg"),
                new Image("resources/8143.jpg"),
                alteredImage2,
                alteredImage1,
                new Image("resources/104022.jpg")
        );
        List<Image> matchOrder = null;
        try {
            matchOrder = ImageProcessing.bestMatch(baselineImage, potentialMatches);
        }
        catch (Exception e) {
            fail("bestMatch: exception unexpected!");
        }
        assertEquals(potentialMatches.size(), matchOrder.size());
    }
}
