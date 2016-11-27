package me.mikey.challenges.week7;

import com.github.sarxos.webcam.Webcam;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.TimerTask;

/**
 * Created by Michael on 15/11/16.
 */
public class Week7 extends JFrame {
    private Webcam webcam = Webcam.getDefault();
    private java.util.Timer timer;

    public Week7() {
        setVisible(true);
        setSize(800, 600);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.out.println("Window closing");
                Sobel.close();
                webcam.close();
                timer.cancel();
            }
        });

        this.timer = new java.util.Timer(true);

        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                paint(getGraphics());
            }
        }, 0, 1000 / 30);

        Webcam.getDefault().close();

        webcam.setViewSize(new Dimension(640, 480));
        webcam.open(false);
    }

    /*public BufferedImage toGreyscale(BufferedImage img) {
        //convert to greyscale
        BufferedImage curImage = new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_BYTE_GRAY);
        Graphics g = curImage.getGraphics();
        g.drawImage(img, 0, 0, null);
        g.dispose();
        return curImage;
    }*/

    @Override
    public void paint(Graphics g) {
        if(webcam != null && webcam.isOpen() && webcam.isImageNew()) {
            BufferedImage img = webcam.getImage();

            if(img != null) {  
                img = Sobel.applyMultithreaded(img);
                g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), null);
            }
        }
    }

    public static void main(String[] args) {
        new Week7();
    }
}
