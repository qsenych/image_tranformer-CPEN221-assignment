package ca.ubc.ece.cpen221.ip.mp;

import ca.ubc.ece.cpen221.ip.core.Image;

public class ImageSimilarity {
    private Image img;
    private double similarity;

    public ImageSimilarity(Image img, double similarity) {
        this.img = img;
        this.similarity = similarity;
    }

    public Image getImg() {
        return this.img;
    }
    public Double getSimilarity() {
        return this.similarity;
    }

}
