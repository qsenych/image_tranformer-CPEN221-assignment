# MiniProject: Image Processing FTW

This project is about detecting and fixing rotated images automatically. The core idea is to figure out how much an image is tilted and then rotate it back so it looks straight.

The way I approached it was to first transform the image into the frequency domain using a Fast Fourier Transform (FFT). I used the Cooley–Tukey algorithm to do this efficiently. The FFT reveals patterns in the image that are hard to see in the normal pixel space, especially the dominant directions of lines and edges.

After getting the frequency spectrum, I applied a threshold to isolate the strongest signals and filter out noise. This helps focus only on the main directions that represent the image’s skew.

To find the angle of rotation, I ran a Hough transform on the thresholded frequency data. The Hough transform detects lines and their orientations, so by analyzing the output, I could estimate the global rotation angle of the image.



### Original Assignment description
The other tasks have been removed from this repo for code readability.
 [![Miniproject Description](img/Cover-MP-ImageProcessing.png)](https://ubc-ece.craft.me/mp-ImageProcessing)