package me.mikey.challenges.week7;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.concurrent.TimeUnit;

/**
 * Created by Michael on 28/11/2016.
 */
public class HoughTransform {
    private static final double MAX_THRESHOLD = 80;
    private int[] data;
    private int width;
    private int height;

    private int[][] houghSpace;
    private int maxRadius;

    private BufferedImage img;

    public HoughTransform(int[] data, int width, int height) {
        this.data = data;
        this.width = width;
        this.height = height;
        this.maxRadius = (int) Math.sqrt(width * width + height * height);
        process();
    }

    private void process() {
        this.houghSpace = new int[180][maxRadius * 2];

        Benchmarker b = new Benchmarker("process");
        for(int x = 0; x < width; x++) {
            for(int y = 0; y < height; y++) {
                if(data[(y * width) + x] == Color.BLACK.getRGB()) {
                    processPixel(x, y);
                }
            }
        }
        b.stop();

        b = new Benchmarker("max");

        double max = getMax();

        b.stop();

        b = new Benchmarker("draw");

        int[] newData = new int[width * height];

        for(int t = 0; t < houghSpace.length; t++) {
            for(int r = 0; r < houghSpace[t].length; r++) {
                double v = houghSpace[t][r];

                if((v / max) > (MAX_THRESHOLD / 100D)) {
                    drawLine(r, t, newData);
                }
            }
        }

        b.stop();

        b = new Benchmarker("drawtoimg");
        this.img = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        this.img.setRGB(0, 0, width, height, newData, 0, width);
        b.stop();
    }

    private void drawLine(int r, int t, int[] newData) {
        r = r - maxRadius;

        double x1 = r * TrigCache.cos(t);
        double y1 = r * TrigCache.sin(t);

        double x2 = r * TrigCache.cos( t + 30);
        double y2 = r * TrigCache.sin( t + 30);

        double m = (y2 - y1) / (x1 - x2);
        double c = y2 - m * x2;

        // draw a line between x = 0 and x = img.getWidth

        int pos1x = 0;
        int pos1y = (int) (c * -1);
        int pos2x = width;
        int pos2y = (int) ((m * (double) height) + c) * -1;

        line(pos1x, pos1y, pos2x, pos2y, Color.RED.getRGB(), newData);
    }

    /*
     * Bresenham's line algorithm from
     * http://tech-algorithm.com/articles/drawing-line-using-bresenham-algorithm/
     */
    public void line(int x,int y,int x2, int y2, int color, int[] newData) {
        int w = x2 - x ;
        int h = y2 - y ;
        double m = h/(double)w ;
        double j = y ;
        for (int i = x; i <= x2;i++) {
            if((j * width) + i > 0 && (j * width) + i < newData.length) {
                newData[((int) j * width) + i] = color;
            }

            j += m;
        }
    }

    private void processPixel(int x, int y) {
        for(int t = 0; t < 180; t++) {
            int r = (int) ((x * TrigCache.cos(t)) + (y * TrigCache.sin(t)) + maxRadius);
            houghSpace[t][r]++;
        }
    }

    public int getMax() {
        int max = 0;

        for(int t = 0; t < houghSpace.length; t++) {
            for(int r = 0; r < houghSpace[t].length; r++) {
                if(houghSpace[t][r] > max) {
                    max = houghSpace[t][r];
                }
            }
        }

        return max;
    }

    public BufferedImage getImg() {
        return this.img;
    }
}
