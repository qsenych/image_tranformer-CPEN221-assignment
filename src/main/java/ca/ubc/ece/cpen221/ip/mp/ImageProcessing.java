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
     * @param img1 32 bit ARGB or 24 bit RGB image with the same height * width as img2
     * @param img2 32 bit ARGB or 24 bit RGB image with the same height * width as img2
     * @return a double of the cosine similarity
     */
    public static double cosineSimilarity(Image img1, Image img2) {
        int width1 = img1.width();
        int height1 = img1.height();
        int width2 = img2.width();
        int height2 = img2.height();

        int size1 = width1 * height1;
        int size2 = width2 * height2;

        if (size1 != size2) {
            throw new IllegalArgumentException();
        }

        long[] v1 = new long[size1];
        long[] v2 = new long[size1];

        boolean img1SolidColour = true;
        boolean img2SolidColour = true;

        ImageTransformer t1 = new ImageTransformer(img1);
        ImageTransformer t2 = new ImageTransformer(img2);

        Image img1g = t1.grayscale();
        Image img2g = t2.grayscale();

        int prevGrayVal1 = img1g.getRGB(0, 0) & 0xFF;
        int prevGrayVal2 = img2g.getRGB(0, 0) & 0xFF;
        int grayVal1 = 0;
        int grayVal2 = 0;

        BigInteger numSum = BigInteger.valueOf(0);
        BigInteger denomSum1 = BigInteger.valueOf(0);
        BigInteger denomSum2 = BigInteger.valueOf(0);

        int index1 = 0;
        for (int row = 0; row < height1; row++) {
            for (int col = 0; col < width1; col++) {
                v1[index1++] = grayVal1 = img1g.getRGB(col, row) & 0xFF;
                if(grayVal1 != prevGrayVal1) img1SolidColour = false;
            }
        }
        int index2 = 0;
        for (int row = 0; row < height2; row++) {
            for (int col = 0; col < width2; col++) {
                v2[index2++] = grayVal2 = img2g.getRGB(col, row) & 0xFF;
                if(grayVal2 != prevGrayVal2) img2SolidColour = false;
            }
        }
        for (int i = 0; i < size1; i++) {
            numSum = numSum.add(BigInteger.valueOf(v1[i]).multiply(BigInteger.valueOf(v2[i])));
            denomSum1 = denomSum1.add(BigInteger.valueOf(v1[i]).multiply(BigInteger.valueOf(v1[i])));
            denomSum2 = denomSum2.add(BigInteger.valueOf(v2[i]).multiply(BigInteger.valueOf(v2[i])));
        }

        double cSim = numSum.doubleValue() / ( Math.sqrt(denomSum1.doubleValue()) * Math.sqrt(denomSum2.doubleValue()) );
        if (img1SolidColour && img2SolidColour) return 1.0;
        else if (denomSum1.equals(BigInteger.ZERO) || denomSum2.equals(BigInteger.ZERO)) return 0.0;
        else return cSim;
    }

    /**
     * Compares a List of images for the best match to the original image
     *
     * @param img A non empty image with the same width * height as every image in matchingCandidates
     * @param matchingCandidates A non-null not empty List of images with the same width * height as img
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
