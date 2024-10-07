package ca.ubc.ece.cpen221.ip.mp;

import ca.ubc.ece.cpen221.ip.core.Image;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        /*
        Image img1 = new Image("resources/clapper-green.jpg");
        Image img2 = new Image("resources/187039.jpg");

        ImageTransformer t1 = new ImageTransformer(img1);
        Color greenish = new Color(13, 190, 14);
        Image result = t1.greenScreen(greenish, img2);
        result.save("resources/clapper187039.png"); */



        Image img3 = new Image("resources/dftImgs/textRect38.png");

        ImageTransformer t2 = new ImageTransformer(img3);
        //Complex[] arr = {Complex.realToComplex(255.0), Complex.realToComplex(255.0), Complex.realToComplex(255.0), Complex.realToComplex(0), Complex.realToComplex(255.0), Complex.realToComplex(255.0), Complex.realToComplex(255.0), Complex.realToComplex(255.0)};

        //Complex[] result = t2.fft(arr);



        t2.alignTextImage();








        int i = 0;

    }
}
