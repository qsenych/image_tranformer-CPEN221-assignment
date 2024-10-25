package ca.ubc.ece.cpen221.ip.mp;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.Color;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

import ca.ubc.ece.cpen221.ip.core.Image;

public class GreenScreen {
    private final Color GREEN = Color.GREEN;
    private final Color RED = Color.RED;
    private final Color BLUE = Color.BLUE;
    private final Color BLACK = Color.BLACK;
    private final Color WHITE = Color.WHITE;

    private static final int DEFAULT_TIMEOUT = 400;  // in milliseconds
    private static final int LENGTHY_TIMEOUT = 2000; // in milliseconds

    // Utility method to create an image filled with a single color
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
     * Test case 1: Simple image with a large green region in the center.
     * Tests that the green region is correctly replaced by the background, while
     * the regions outside the green screen remain unchanged.
     */
    @Test
    @Timeout(value = DEFAULT_TIMEOUT, unit = TimeUnit.MILLISECONDS)
    public void testLargeGreenRegion() {
        Image img = createImage(25, 25, BLUE);
        ImageTransformer transformer = new ImageTransformer(img);

        for (int x = 5; x < 20; x++) {
            for (int y = 5; y < 20; y++) {
                img.set(x, y, GREEN);
            }
        }

        Image background = createImage(5, 5, WHITE);
        Image result = transformer.greenScreen(GREEN, background);

        for (int x = 5; x < 20; x++) {
            for (int y = 5; y < 20; y++) {
                assertNotEquals(GREEN, result.get(x, y));
            }
        }

        for (int x = 0; x < 5; x++) {
            for (int y = 0; y < 25; y++) {
                assertEquals(BLUE, result.get(x, y));
            }
        }
        for (int x = 20; x < 25; x++) {
            for (int y = 0; y < 25; y++) {
                assertEquals(BLUE, result.get(x, y));
            }
        }
    }

    /*
     * Test case 2: Disconnected green regions.
     * Verifies that only the largest connected green region is replaced, while
     * regions outside the green screen remain unchanged.
     */
    @Test
    @Timeout(value = DEFAULT_TIMEOUT, unit = TimeUnit.MILLISECONDS)
    public void testDisconnectedGreenRegions() {
        Image img = createImage(25, 25, BLUE);
        ImageTransformer transformer = new ImageTransformer(img);

        for (int x = 2; x < 5; x++) {
            for (int y = 2; y < 5; y++) {
                img.set(x, y, GREEN);
            }
        }

        for (int x = 15; x < 20; x++) {
            for (int y = 15; y < 20; y++) {
                img.set(x, y, GREEN);
            }
        }

        Image background = createImage(3, 3, WHITE);
        Image result = transformer.greenScreen(GREEN, background);

        for (int x = 15; x < 20; x++) {
            for (int y = 15; y < 20; y++) {
                assertEquals(WHITE, result.get(x, y));
            }
        }

        for (int x = 0; x < 15; x++) {
            for (int y = 0; y < 25; y++) {
                if (x >= 2 && x <= 4 && y >= 2 && y <= 4) {
                    assertEquals(GREEN, result.get(x, y));
                } else {
                    assertEquals(BLUE, result.get(x, y));
                }
            }
        }
        for (int x = 20; x < 25; x++) {
            for (int y = 0; y < 25; y++) {
                assertEquals(BLUE, result.get(x, y));
            }
        }
    }

    /*
     * Test case 3: Irregular shaped green region.
     * Tests the correct handling of an irregular green shape, ensuring that only
     * the green pixels in the irregular region are replaced, while other regions remain unchanged.
     */
    @Test
    @Timeout(value = DEFAULT_TIMEOUT, unit = TimeUnit.MILLISECONDS)
    public void testIrregularShapedRegion() {
        Image img = createImage(25, 25, BLUE);
        ImageTransformer transformer = new ImageTransformer(img);

        for (int x = 5; x < 15; x++) {
            img.set(x, 10, GREEN);
            if (x % 2 == 0) {
                img.set(x, 11, GREEN);
            }
        }

        Image background = createImage(2, 2, BLACK);
        Image result = transformer.greenScreen(GREEN, background);

        for (int x = 5; x < 15; x++) {
            assertNotEquals(GREEN, result.get(x, 10));
            if (x % 2 == 0) {
                assertNotEquals(GREEN, result.get(x, 11));
            }
        }

        for (int x = 0; x < 5; x++) {
            for (int y = 0; y < 25; y++) {
                assertEquals(BLUE, result.get(x, y));
            }
        }
        for (int x = 15; x < 25; x++) {
            for (int y = 0; y < 25; y++) {
                assertEquals(BLUE, result.get(x, y));
            }
        }
    }

    /*
     * Test case 4: Complex background with a small green region.
     * Verifies the handling of a small green region over a complex black and white background,
     * ensuring that only the green pixels are replaced and the rest remain unchanged.
     */
    @Test
    @Timeout(value = DEFAULT_TIMEOUT, unit = TimeUnit.MILLISECONDS)
    public void testBackgroundWithGreenRegion() {
        Image img = createImage(25, 25, BLACK);
        ImageTransformer transformer = new ImageTransformer(img);

        for (int x = 0; x < img.width(); x++) {
            for (int y = 0; y < img.height(); y++) {
                if ((x + y) % 2 == 0) {
                    img.set(x, y, WHITE);
                }
            }
        }

        for (int x = 10; x < 15; x++) {
            for (int y = 10; y < 15; y++) {
                img.set(x, y, GREEN);
            }
        }

        Image background = createImage(3, 3, BLUE);
        Image result = transformer.greenScreen(GREEN, background);

        for (int x = 10; x < 15; x++) {
            for (int y = 10; y < 15; y++) {
                assertNotEquals(GREEN, result.get(x, y));
            }
        }

        for (int x = 0; x < 10; x++) {
            for (int y = 0; y < 25; y++) {
                if ((x + y) % 2 == 0) {
                    assertEquals(WHITE, result.get(x, y));
                } else {
                    assertEquals(BLACK, result.get(x, y));
                }
            }
        }
        for (int x = 15; x < 25; x++) {
            for (int y = 0; y < 25; y++) {
                if ((x + y) % 2 == 0) {
                    assertEquals(WHITE, result.get(x, y));
                } else {
                    assertEquals(BLACK, result.get(x, y));
                }
            }
        }
    }

    /*
     * Test case 5: No green region.
     * Tests the scenario where no green region exists, ensuring that no changes are made to the image.
     */
    @Test
    @Timeout(value = DEFAULT_TIMEOUT, unit = TimeUnit.MILLISECONDS)
    public void testNoGreenRegion() {
        Image img = createImage(25, 25, BLUE);
        ImageTransformer transformer = new ImageTransformer(img);

        Image background = createImage(5, 5, WHITE);
        Image result = transformer.greenScreen(GREEN, background);

        for (int x = 0; x < img.width(); x++) {
            for (int y = 0; y < img.height(); y++) {
                assertEquals(img.get(x, y), result.get(x, y));
            }
        }
    }

    /*
     * Test case 6: Large image with two red regions.
     * Tests the replacement of the largest connected red region in a 600x600 image,
     * ensuring that regions outside the red screen remain unchanged.
     */
    @Test
    @Timeout(value = LENGTHY_TIMEOUT, unit = TimeUnit.MILLISECONDS)
    public void testLargeImageWithPreemptiveTimeout() {
        Image img = createImage(600, 600, BLUE);
        ImageTransformer transformer = new ImageTransformer(img);

        for (int x = 100; x < 200; x++) {
            for (int y = 100; y < 200; y++) {
                if ((y == 100 || y == 199) && (x >= 190 && x < 200)) {
                    continue;
                }
                img.set(x, y, RED);
            }
        }

        for (int x = 300; x < 390; x++) {
            for (int y = 300; y < 390; y++) {
                img.set(x, y, RED);
            }
        }

        Image background = createImage(10, 10, WHITE);
        Image result = transformer.greenScreen(RED, background);

        assertTimeoutPreemptively(Duration.ofMillis(LENGTHY_TIMEOUT - 10), () -> {
            for (int x = 100; x < 200; x++) {
                for (int y = 100; y < 200; y++) {
                    if ((y == 100 || y == 199) && (x >= 190 && x < 200)) {
                        assertEquals(BLUE, result.get(x, y));
                    } else {
                        assertNotEquals(RED, result.get(x, y));
                    }
                }
            }

            for (int x = 0; x < 100; x++) {
                for (int y = 0; y < 600; y++) {
                    assertEquals(BLUE, result.get(x, y));
                }
            }
            for (int x = 200; x < 300; x++) {
                for (int y = 0; y < 600; y++) {
                    assertEquals(BLUE, result.get(x, y));
                }
            }
            for (int x = 390; x < 600; x++) {
                for (int y = 0; y < 600; y++) {
                    assertEquals(BLUE, result.get(x, y));
                }
            }
        });
    }

    /*
     * Test case 7: Green region with a white center.
     * Verifies that only the green pixels in a 50x50 green region (with a 5x5 white center)
     * are replaced, while the white center and other regions remain unchanged.
     */
    @Test
    @Timeout(value = DEFAULT_TIMEOUT, unit = TimeUnit.MILLISECONDS)
    public void testGreenRegionWithWhiteCenter() {
        Image img = createImage(200, 200, BLUE);
        ImageTransformer transformer = new ImageTransformer(img);

        for (int x = 75; x < 125; x++) {
            for (int y = 75; y < 125; y++) {
                img.set(x, y, GREEN);
            }
        }

        for (int x = 97; x < 102; x++) {
            for (int y = 97; y < 102; y++) {
                img.set(x, y, WHITE);
            }
        }

        Image background = createImage(10, 10, BLACK);
        Image result = transformer.greenScreen(GREEN, background);

        for (int x = 75; x < 125; x++) {
            for (int y = 75; y < 125; y++) {
                if (x >= 97 && x < 102 && y >= 97 && y < 102) {
                    assertEquals(WHITE, result.get(x, y));
                } else {
                    assertNotEquals(GREEN, result.get(x, y));
                }
            }
        }

        for (int x = 0; x < 75; x++) {
            for (int y = 0; y < 200; y++) {
                assertEquals(BLUE, result.get(x, y));
            }
        }
        for (int x = 125; x < 200; x++) {
            for (int y = 0; y < 200; y++) {
                assertEquals(BLUE, result.get(x, y));
            }
        }
    }
}