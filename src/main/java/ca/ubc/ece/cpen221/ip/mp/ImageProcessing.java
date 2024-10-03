package ca.ubc.ece.cpen221.ip.mp;

import ca.ubc.ece.cpen221.ip.core.Image;

import java.util.List;
import java.lang.Math;

/**
 * This class provides some simple operations involving
 * more than one image.
 */
public class ImageProcessing {

    /* ===== TASK 3 ===== */

    /**
     * Computes the cosine similarity of 2 images of the same size
     *
     * @param img1 32 bit ARGB or 16 bit RGB image with the same height AND width as img2
     * @param img2 32 bit ARGB or 16 bit RGB image with the same height AND width as img2
     * @return a double of the cosine similarity
     */
    public static double cosineSimilarity(Image img1, Image img2) {
        if (img1.width() != img2.width()) {
            throw new IllegalArgumentException();
        }
        if (img1.height() != img2.height()) {
            throw new IllegalArgumentException();
        }

        int width = img1.width();
        int height = img1.height();

        ImageTransformer t1 = new ImageTransformer(img1);
        ImageTransformer t2 = new ImageTransformer(img2);

        Image img1g = t1.grayscale();
        Image img2g = t2.grayscale();

        int numSum = 0;
        int denomSum1 = 0;
        int denomSum2 = 0;

        for (int col = 0; col < width; col++) {
            for (int row = 0; row < height; row++) {
                int grayVal1 = img1g.getRGB(col, row) & 0xFF;
                int grayVal2 = img2g.getRGB(col, row) & 0xFF;

                numSum += grayVal1 * grayVal2;
                denomSum1 += grayVal1 * grayVal1;
                denomSum2 += grayVal2 * grayVal2;

            }
        }

        double cSim = (double) numSum / ( Math.sqrt( (double) denomSum1) * Math.sqrt( (double) denomSum2));
        return cSim;
    }

    public static List<Image> bestMatch(Image img, List<Image> matchingCandidates) {
        // TODO: Implement this method


        return null;
    }

}
