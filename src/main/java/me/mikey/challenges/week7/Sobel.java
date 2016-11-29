package me.mikey.challenges.week7;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

/**
 * Created by Michael on 15/11/16.
 */
public class Sobel {
    public static final int PIXELS_PER_THREAD = 2000;
    public static ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

    public static long[][] gX = {
            { -1,   0, +1},
            { -2,   0, +2},
            { -1,   0, +1}
    };

    public static long[][] gY = {
            { -1,  -2, -1},
            {  0,   0,  0},
            { +1,  +2, +1}
    };

    public static void apply(int width, int height, int part, int[] pixelArray, int[] sobel) {
        for(int x = 1; x < width - 1; x++) {
            for(int y = Math.max(part * PIXELS_PER_THREAD, 1); y < Math.min(part * PIXELS_PER_THREAD + PIXELS_PER_THREAD, height - 1); y++) {
                //cache these values...
                long minusMinus = getPixel(x - 1, y - 1, width, pixelArray);
                long normalMinus = getPixel(x, y - 1, width, pixelArray);
                long plusMinus = getPixel(x + 1, y - 1, width, pixelArray);

                long minusNormal = getPixel(x - 1, y, width, pixelArray);
                long normalNormal = getPixel(x, y, width, pixelArray);
                long plusNormal = getPixel(x + 1, y, width, pixelArray);

                long minusPlus = getPixel(x - 1, y + 1, width, pixelArray);
                long normalPlus = getPixel(x, y + 1, width, pixelArray);
                long plusPlus = getPixel(x + 1, y + 1, width, pixelArray);

                long sumX =
                        (gX[0][0] * minusMinus)      +
                        (gX[0][1] * normalMinus)     +
                        (gX[0][2] * plusMinus)       +

                        (gX[1][0] * minusNormal)     +
                        (gX[1][1] * normalNormal)    +
                        (gX[1][2] * plusNormal)      +

                        (gX[2][0] * minusPlus)       +
                        (gX[2][1] * normalPlus)      +
                        (gX[2][2] * plusPlus);

                long sumY =
                        (gY[0][0] * minusMinus)      +
                        (gY[0][1] * normalMinus)     +
                        (gY[0][2] * plusMinus)       +

                        (gY[1][0] * minusNormal)     +
                        (gY[1][1] * normalNormal)    +
                        (gY[1][2] * plusNormal)      +

                        (gY[2][0] * minusPlus)       +
                        (gY[2][1] * normalPlus)      +
                        (gY[2][2] * plusPlus);

                long mag = (sumX * sumX) + (sumY * sumY);

                //3.2 million squared, saves having to sqrt the magnitude
                //sobel[(y * width) + x] = pixelArray[(y * width) + x];

                if(mag < 2.5e12) {
                    sobel[(y * width) + x] = Color.WHITE.getRGB();
                } else {
                    sobel[(y * width) + x] = Color.BLACK.getRGB();
                }
            }
        }
    }

    private static long getPixel(int x, int y, int width, int[] pixels) {
        return (long) pixels[(y * width) + x];
    }

    public static SobelResult applyMultithreaded(BufferedImage img) {
        BufferedImage sobel = new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_BYTE_GRAY);

        List<Callable<Object>> list = new ArrayList<>();

        //split by height
        int parts = (int) Math.ceil((double) img.getHeight() / PIXELS_PER_THREAD);
        int width = img.getWidth();
        int height = img.getHeight();

        int[] pixelArray = new int[width * height];
        int[] pixelArrayCopy = new int[width * height];

        Arrays.fill(pixelArrayCopy, Color.WHITE.getRGB());

        img.getRGB(0, 0, width, height, pixelArray, 0, width);

        for(int i = 0; i < parts; i++) {
            int finalI = i;
            list.add(() -> {
                apply(width, height, finalI, pixelArray, pixelArrayCopy);
                return null;
            });
        }

        if(!executorService.isShutdown()) {
            try {
                executorService.invokeAll(list);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            sobel.setRGB(0, 0, width, height, pixelArrayCopy, 0, width);
        }

        //Week7.save(sobel, output);
        return new SobelResult(sobel, pixelArrayCopy);
    }

    public static void close() {
        executorService.shutdown();
    }

    public static class SobelResult {
        private BufferedImage img;
        private int[] data;

        public SobelResult(BufferedImage img, int[] data) {
            this.img = img;
            this.data = data;
        }

        public BufferedImage getImg() {
            return img;
        }

        public int[] getData() {
            return data;
        }
    }
}
