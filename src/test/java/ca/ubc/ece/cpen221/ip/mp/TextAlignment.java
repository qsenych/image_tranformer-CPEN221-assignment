package ca.ubc.ece.cpen221.ip.mp;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import ca.ubc.ece.cpen221.ip.core.Image;

import java.awt.Color;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

public class TextAlignment {
    private final Color WHITE = Color.WHITE;
    private final Color BLACK = Color.BLACK;
    private final int PI = 180;
    private static final int DEFAULT_TIMEOUT = 8000; // milliseconds
    private static final int DELTA = 5; // tolerance for angle error

    /*
     * Utility method to create an image filled with a single color.
     */
    private Image createImage(int width, int height, Color color) {
        Image img = new Image(width, height);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                img.set(x, y, color);
            }
        }
        return img;
    }

    /*
     * Utility method to create a simple text-like pattern with horizontal lines.
     * This pattern simulates horizontal text for testing purposes.
     */
    private void drawSimpleTextPattern(Image img) {
        for (int y = 10; y < img.height(); y += 20) {
            for (int x = 0; x < img.width(); x++) {
                img.set(x, y, BLACK);
            }
        }
    }

    /*
     * Test case 1: Perfectly aligned horizontal text (0 degrees).
     * The method should return an angle close to 0 degrees, as the text is already horizontally aligned.
     */
    @Test
    @Timeout(value = DEFAULT_TIMEOUT, unit = TimeUnit.MILLISECONDS)
    public void testPerfectlyAlignedText() {
        Image img = createImage(100, 100, WHITE);
        drawSimpleTextPattern(img);

        ImageTransformer transformer = new ImageTransformer(img);
        int angle = ((int)Math.round(transformer.getTextAlignmentAngle()) % PI);
        int rotationAngle = 0;

        assertTrue((int)(Math.abs(rotationAngle - angle)) % PI <= DELTA || ((int)Math.abs(angle + rotationAngle)) % PI <= DELTA, String.format("Expected angle was %d but got %d", rotationAngle, angle));
    }

    /*
     * Test case 2: Slightly rotated text (-5 degrees).
     * The method should return an angle close to -5 degrees for counterclockwise rotation.
     */
    @Test
    @Timeout(value = DEFAULT_TIMEOUT, unit = TimeUnit.MILLISECONDS)
    public void testSlightlyRotatedText() {
        Image img = createImage(100, 100, WHITE);
        drawSimpleTextPattern(img);
        int rotationAngle = -5;
        Image rotatedImage = rotateImage(img, rotationAngle);

        ImageTransformer transformer = new ImageTransformer(rotatedImage);
        int angle = (int) Math.round(transformer.getTextAlignmentAngle());

        int delta1 = Math.abs(rotationAngle - angle) % PI;
        int delta2 = Math.abs(rotationAngle + angle) % PI;

        assertTrue(delta1 <= DELTA || delta2 <= DELTA, String.format("Expected angle was %d but got %d", rotationAngle, angle));
    }

    /*
     * Test case 3: Severely rotated text (-45 degrees).
     * The method should return an angle close to -45 degrees for counterclockwise rotation.
     */
    @Test
    @Timeout(value = DEFAULT_TIMEOUT, unit = TimeUnit.MILLISECONDS)
    public void testSeverelyRotatedText() {
        Image img = createImage(100, 100, WHITE);
        drawSimpleTextPattern(img);
        int rotationAngle = -45;
        Image rotatedImage = rotateImage(img, rotationAngle);

        ImageTransformer transformer = new ImageTransformer(rotatedImage);
        int angle = (int) Math.round(transformer.getTextAlignmentAngle());

        int delta1 = Math.abs(rotationAngle - angle) % PI;
        int delta2 = Math.abs(rotationAngle + angle) % PI;

        assertTrue(delta1 <= DELTA || delta2 <= DELTA, String.format("Expected angle was %d but got %d", rotationAngle, angle));
    }

    /*
     * Test case 4: Image with random noise.
     * This test ensures that random noise produces an arbitrary angle.
     */
    @Test
    @Timeout(value = DEFAULT_TIMEOUT, unit = TimeUnit.MILLISECONDS)
    public void testRandomNoiseImage() {
        Image img = createImage(100, 100, WHITE);

        for (int x = 0; x < img.width(); x++) {
            for (int y = 0; y < img.height(); y++) {
                if (Math.random() < 0.5) {
                    img.set(x, y, BLACK);
                }
            }
        }

        ImageTransformer transformer = new ImageTransformer(img);
        int angle = ((int)Math.round(transformer.getTextAlignmentAngle()) % PI);

        assertTrue(angle >= -90.0 && angle <= 90.0);
    }

    /*
     * Test case 5: Image with slight noise.
     * The method should return an angle close to 0 degrees, even with slight noise in the image.
     */
    @Test
    @Timeout(value = DEFAULT_TIMEOUT, unit = TimeUnit.MILLISECONDS)
    public void testImageWithSlightNoise() {
        Image img = createImage(100, 100, WHITE);
        drawSimpleTextPattern(img);

        for (int x = 0; x < img.width(); x++) {
            for (int y = 0; y < img.height(); y++) {
                if (Math.random() < 0.05) {
                    img.set(x, y, BLACK);
                }
            }
        }

        ImageTransformer transformer = new ImageTransformer(img);
        int angle = ((int)Math.round(transformer.getTextAlignmentAngle()) % PI);
        int rotationAngle = 0;

        int delta1 = Math.abs(rotationAngle - angle) % PI;
        int delta2 = Math.abs(rotationAngle + angle) % PI;

        assertTrue(delta1 <= DELTA || delta2 <= DELTA, String.format("Expected angle was %d but got %d", rotationAngle, angle));
    }

    /*
     * Test case 6: Empty image (solid background).
     * The method should return 0 degrees for an empty image.
     */
    @Test
    @Timeout(value = DEFAULT_TIMEOUT, unit = TimeUnit.MILLISECONDS)
    public void testEmptyImage() {
        Image img = createImage(100, 100, WHITE);

        ImageTransformer transformer = new ImageTransformer(img);
        int angle = ((int)Math.round(transformer.getTextAlignmentAngle()) % PI);

        int rotationAngle = 0;

        int delta1 = Math.abs(rotationAngle - angle) % PI;
        int delta2 = Math.abs(rotationAngle + angle) % PI;

        assertTrue(delta1 <= DELTA || delta2 <= DELTA, String.format("Expected angle was %d but got %d", rotationAngle, angle));
    }

    /*
     * Test case: Image rotated by 33 degrees.
     * The method should return an angle close to 33 degrees for clockwise rotation.
     */
    @Test
    @Timeout(value = DEFAULT_TIMEOUT, unit = TimeUnit.MILLISECONDS)
    public void testRotatedImageBy33Degrees() {
        Image img = createImage(100, 100, WHITE);
        drawSimpleTextPattern(img);
        int rotationAngle = 33;
        Image rotatedImage = rotateImage(img, rotationAngle);

        ImageTransformer transformer = new ImageTransformer(rotatedImage);
        int angle = (int)Math.round(transformer.getTextAlignmentAngle());

        int delta1 = Math.abs(rotationAngle - angle) % PI;
        int delta2 = Math.abs(rotationAngle + angle) % PI;

        assertTrue(delta1 <= DELTA || delta2 <= DELTA, String.format("Expected angle was %d but got %d", rotationAngle, angle));
    }

    /*
     * Test case: Image rotated by 15 degrees.
     * The method should return an angle close to 15 degrees for clockwise rotation.
     */
    @Test
    @Timeout(value = DEFAULT_TIMEOUT, unit = TimeUnit.MILLISECONDS)
    public void testRotatedImageBy15Degrees() {
        Image img = createImage(100, 100, WHITE);
        drawSimpleTextPattern(img);
        int rotationAngle = 15;
        Image rotatedImage = rotateImage(img, rotationAngle);

        ImageTransformer transformer = new ImageTransformer(rotatedImage);
        int angle = (int)Math.round(transformer.getTextAlignmentAngle());

        int delta1 = Math.abs(rotationAngle - angle) % PI;
        int delta2 = Math.abs(rotationAngle + angle) % PI;

        assertTrue(delta1 <= DELTA || delta2 <= DELTA, String.format("Expected angle was %d but got %d", rotationAngle, angle));
    }

    /*
     * Utility method to simulate rotating an image by a specified angle.
     * Supports both clockwise and counterclockwise rotations.
     */
    private Image rotateImage(Image img, double degrees) {
        int width = img.width();
        int height = img.height();

        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                bufferedImage.setRGB(x, y, img.getRGB(x, y));
            }
        }

        AffineTransform transform = new AffineTransform();
        transform.rotate(Math.toRadians(degrees), width / 2, height / 2);

        AffineTransformOp op = new AffineTransformOp(transform, AffineTransformOp.TYPE_BILINEAR);
        BufferedImage rotatedBufferedImage = op.filter(bufferedImage, null);

        Image rotatedImage = new Image(width, height);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                rotatedImage.set(x, y, new Color(rotatedBufferedImage.getRGB(x, y)));
            }
        }

        return rotatedImage;
    }
}