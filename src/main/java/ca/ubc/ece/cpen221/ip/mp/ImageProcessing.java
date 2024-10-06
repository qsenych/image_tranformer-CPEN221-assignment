package ca.ubc.ece.cpen221.ip.mp;

import ca.ubc.ece.cpen221.ip.core.Image;

import java.math.BigInteger;
import java.util.*;
import java.lang.Math;

/**
 * This class provides some simple operations involving
 * more than one image.
 */
public class ImageProcessing {

    /* ===== TASK 3 ===== */

    /**
     * Computes the cosine similarity of 2 images of the same size.
     * Black images have a similarity of 1.0 with other uniform coloured images.
     * Black images have a similarity of 0.0 with other images.
     *
     * @param img1 32 bit ARGB or 24 bit RGB image with the same height AND width as img2
     * @param img2 32 bit ARGB or 24 bit RGB image with the same height AND width as img2
     * @return a double of the cosine similarity
     */
    public static double cosineSimilarity(Image img1, Image img2) {
        if (img1.width() != img2.width() || img1.height() != img2.height()) {
            throw new IllegalArgumentException();
        }

        int width = img1.width();
        int height = img1.height();

        boolean img1SolidColour = true;
        boolean img2SolidColour = true;

        ImageTransformer t1 = new ImageTransformer(img1);
        ImageTransformer t2 = new ImageTransformer(img2);

        Image img1g = t1.grayscale();
        Image img2g = t2.grayscale();

        int prevGrayVal1 = img1g.getRGB(0, 0) & 0xFF;
        int prevGrayVal2 = img2g.getRGB(0, 0) & 0xFF;
        int grayVal1;
        int grayVal2;

        BigInteger numSum = BigInteger.valueOf(0);
        BigInteger denomSum1 = BigInteger.valueOf(0);
        BigInteger denomSum2 = BigInteger.valueOf(0);

        for (int col = 0; col < width; col++) {
            for (int row = 0; row < height; row++) {
                grayVal1 = img1g.getRGB(col, row) & 0xFF;
                grayVal2 = img2g.getRGB(col, row) & 0xFF;

                if(grayVal1 != prevGrayVal1) img1SolidColour = false;
                if(grayVal2 != prevGrayVal2) img2SolidColour = false;

                numSum = numSum.add(BigInteger.valueOf(grayVal1).multiply(BigInteger.valueOf(grayVal2)));
                denomSum1 = denomSum1.add(BigInteger.valueOf(grayVal1).multiply(BigInteger.valueOf(grayVal1)));
                denomSum2 = denomSum2.add(BigInteger.valueOf(grayVal2).multiply(BigInteger.valueOf(grayVal2)));
            }
        }

        double cSim = numSum.doubleValue() / ( Math.sqrt(denomSum1.doubleValue()) * Math.sqrt(denomSum2.doubleValue()) );
        if (img1SolidColour && img2SolidColour) return 1.0;
        else if (denomSum1.equals(BigInteger.ZERO) || denomSum2.equals(BigInteger.ZERO)) return 0.0;
        else return cSim;
    }

    /**
     * @param img A non empty image with the same width and height as every image in matchingCandidates
     * @param matchingCandidates A non-null not empty List of images with the same width and height as img
     * @return A list including all images in matchingCandidates, sorted using cosine similarity, with best match appearing first
     */
    public static List<Image> bestMatch(Image img, List<Image> matchingCandidates) {
        if(matchingCandidates == null || matchingCandidates.isEmpty()) {
            throw new IllegalArgumentException();
        }

        List<ImageSimilarity> simList = new ArrayList<>();

        for(Image candidate : matchingCandidates) {
            double similarity = ImageProcessing.cosineSimilarity(img, candidate);
            simList.add(new ImageSimilarity(candidate, similarity));
        }

        simList.sort((img1, img2) -> Double.compare(img2.getSimilarity(), img1.getSimilarity()));

        List<Image> sortedImgs = new ArrayList<>();
        for (ImageSimilarity iSim : simList) {
            sortedImgs.add(iSim.getImg());
        }

        return sortedImgs;
    }
}
