package ca.ubc.ece.cpen221.ip.mp;

import ca.ubc.ece.cpen221.ip.core.Image;

/**
 * A data type to store an image and a similarity value with reference to another image
 */
public class ImageSimilarity {
    private final Image img;
    private final double similarity;

    /**
     * Creates an image of an image and its similarity to another image
     *
     * @param img A non-null image
     * @param similarity a double between 0.0 and 1.0
     */
    public ImageSimilarity(Image img, double similarity) {
        this.img = img;
        this.similarity = similarity;
    }

    /**
     * @return this image
     */
    public Image getImg() {
        return this.img;
    }

    /**
     * @return this similarity
     */
    public Double getSimilarity() {
        return this.similarity;
    }

}
