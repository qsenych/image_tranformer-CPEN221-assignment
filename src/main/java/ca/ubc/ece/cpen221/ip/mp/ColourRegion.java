package ca.ubc.ece.cpen221.ip.mp;

import ca.ubc.ece.cpen221.ip.core.Rectangle;

/**
 * A mutable rectangle in positive quadrant of the 2D plane.
 * Also tracks the number of pixels associated with some parameter
 * across the region.
 * Allows for regions of zero area.
 */
public class ColourRegion {
    public int xTopLeft, yTopLeft;
    public int xBottomRight, yBottomRight;
    public int pixelCount;
    public boolean[][] pixels;

    public final int ogWidth;
    public final int ogHeight;

    /*
        Uses same abstraction as a Rectangle instance

        Abstraction Function: Represents a rectangle using its top-left corner and bottom-right
        corner. xTopLeft is the x-coordinate of the top-left corner and yTopLeft is the y-coordinate
        of the top-left corner. xBottomRight and yBottom right are the x- and y- coordinates
        of the bottom-right corner.

        Representation Invariant:
            0 <= xTopLeft < xBottomRight
            0 <= yTopLeft < yBottomRight

        pixels is a 2d boolean array representing each pixel in the image this instance was based on
        a pixel can be set to "true" if it meets any criteria the implementer intends.
    */


    /**
     * Create a new ColourRegion.
     *
     * @param _xTopLeft: is the x coordinate of the top-left corner and should be >= 0
     * @param _yTopLeft: is the y coordinate of the top-left corner and should be >= 0
     * @param _xBottomRight: is the x coordinate of the bottom-right corner and should be >= _xTopLeft
     * @param _yBottomRight: is the y coordinate of the bottom-right corner and should be >= _yTopLeft
     * @param width: is the width of the original image that this ColourRegion is representative of
     * @param height: is the height of the original image that this ColourRegion is representative of
     */
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

    /**
     * Checks if this ColourRegion can be successfully converted to a Rectangle
     *
     * @return true if it can be converted, false otherwise
     */
    public boolean isValidRect() {
        return xTopLeft >= 0 && yTopLeft >= 0
                && xBottomRight > xTopLeft && yBottomRight > yTopLeft;
    }
}
