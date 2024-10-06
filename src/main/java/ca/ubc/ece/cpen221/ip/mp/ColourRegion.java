package ca.ubc.ece.cpen221.ip.mp;

import ca.ubc.ece.cpen221.ip.core.Rectangle;

/**
 * A mutable rectangle in positive quadrant of the 2D plane.
 * Also tracks the number of pixels associated with the region.
 * Allows for regions of zero area
 *
 *
 */
public class ColourRegion {
    public int xTopLeft, yTopLeft;
    public int xBottomRight, yBottomRight;
    public int pixelCount;
    public boolean[][] pixels;

    public final int ogWidth;
    public final int ogHeight;

    public ColourRegion(int _xTopLeft, int _yTopLeft, int _xBottomRight, int _yBottomRight, int width, int height) {
        if (_xTopLeft < 0 || _yTopLeft < 0 ||
                _xBottomRight < _xTopLeft || _yBottomRight < _yTopLeft) {
            throw new IllegalArgumentException("Invalid rectangle specified.");
        }
        ogWidth = width;
        ogHeight = height;


        pixelCount = 0;
        pixels = new boolean[ogWidth][ogHeight];
    }

    public Rectangle toRectangle() {
        return new Rectangle(xTopLeft, yTopLeft, xBottomRight, yBottomRight);
    }

    public boolean isValidRect() {
        return xTopLeft >= 0 && yTopLeft >= 0
                && xBottomRight > xTopLeft && yBottomRight > yTopLeft;
    }
}
