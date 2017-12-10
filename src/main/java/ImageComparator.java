import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ImageComparator {

    /**
    * Method which compare two pixels
    * @param firstPixel - first pixel
    * @param secondPixel - second pixel
     * */
    private boolean pixelsDifference(int firstPixel, int secondPixel) {
        int r1 = (firstPixel >> 16) & 255;
        int g1 = (firstPixel >> 8) & 255;
        int b1 = (firstPixel) & 255;
        int r2 = (secondPixel >> 16) & 255;
        int g2 = (secondPixel >> 8) & 255;
        int b2 = (secondPixel) & 255;
        int difference = (Math.abs(r1 - r2) + Math.abs(g1 - g2) + Math.abs(b1 - b2)) / 3 * 100;
        return difference > 10;
    }

    /**
     * Method for storing all different pixels
     * @param image1 the first image
     * @param image2 the second image
     * @return list of different pixels
     * */
    private List<Integer[]> getDifferentPixels(BufferedImage image1, BufferedImage image2) {

        List<Integer[]> diffPix = new ArrayList();

        for (int i = 0; i < image2.getWidth(); i++) {
            for (int j = 0; j < image2.getHeight(); j++) {
                if (pixelsDifference(image1.getRGB(i, j), image2.getRGB(i, j))) {
                    diffPix.add(new Integer[]{i, j});
                }
            }
        }
        return diffPix;
    }
}