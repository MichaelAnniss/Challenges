package me.mikey.challenges.week7;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by Michael on 28/11/2016.
 */
public class Week7Static {
    public static void main(String[] args) {
        try {
            BufferedImage img = ImageIO.read(new File("circles.png"));
            Sobel.SobelResult res = Sobel.applyMultithreaded(img);
            img = res.getImg();
            ImageIO.write(img, "PNG", new File("sobel.png"));
            img = new HoughTransform(res.getData(), img.getWidth(), img.getHeight()).getImg();
            ImageIO.write(img, "PNG", new File("hough.png"));
        } catch (IOException e) {

        } finally {
            Sobel.close();
        }
    }
}
