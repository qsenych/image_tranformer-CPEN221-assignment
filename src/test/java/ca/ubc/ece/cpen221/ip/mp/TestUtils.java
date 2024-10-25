package ca.ubc.ece.cpen221.ip.mp;

import ca.ubc.ece.cpen221.ip.core.Image;
import java.awt.*;

public class TestUtils {
    public static Image grayscale(Image image) {
        Image gsImage = new Image(image.width(), image.height());
        for (int col = 0; col < image.width(); col++) {
            for (int row = 0; row < image.height(); row++) {
                Color color = image.get(col, row);
                Color gray = Image.toGray(color);
                gsImage.set(col, row, gray);
            }
        }
        return gsImage;
    }
}
