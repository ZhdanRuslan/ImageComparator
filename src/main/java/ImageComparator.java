import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ImageComparator {

    /**
     * Getting result image with mark differences
     * @param img1 - first image to compare
     * @param img2 - second image to compare
     * @return - result image with mark differences
     * */
    public BufferedImage getResultImage(BufferedImage img1, BufferedImage img2) throws IOException {
        if ((img1.getWidth() != img2.getWidth()) || img1.getHeight() != img2.getHeight()) {
            System.out.println("Wrong comparison. Images have different dimensions!");
            return null;
        }
        BufferedImage different = new BufferedImage(img2.getColorModel(), img2.getRaster(), img2.isAlphaPremultiplied(), null);
        return drawRedRectangle(different, groupPixels(getDifferentPixels(img1, img2)));
    }

    /**
    * Method which compare two pixels
    * @param firstPixel - first pixel
    * @param secondPixel - second pixel
     * @see /stackoverflow.com/questions/2615522/java-bufferedimage-getting-red-green-and-blue-individually
     * */
    private boolean pixelsDifference(int firstPixel, int secondPixel) {
        int red1 = (firstPixel >> 16) & 255; //0xFF
        int green1 = (firstPixel >> 8) & 255; //0xFF
        int blue1 = (firstPixel) & 255; //0xFF
        int red2 = (secondPixel >> 16) & 255; //0xFF
        int green2 = (secondPixel >> 8) & 255; //0xFF
        int blue2 = (secondPixel) & 255; //0xFF
        int difference = (Math.abs(red1 - red2) + Math.abs(green1 - green2) + Math.abs(blue1 - blue2)) / 3 * 100;
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

    /**Draw red rectangle which show differences on the picture
     * @parem differentImage
     * @param groupsOfPixels
     * @return image with marks of differences*/

    private BufferedImage drawRedRectangle(BufferedImage differentImage, List<List<Integer[]>> groupsOfPixels) throws IOException {

        BufferedImage bufferedImage = new BufferedImage(differentImage.getColorModel(), differentImage.getRaster(), differentImage.isAlphaPremultiplied(), null);

        Graphics2D graphics2D = (Graphics2D) bufferedImage.getGraphics();

        for (Collection<Integer[]> group : groupsOfPixels) {
            int minX = Integer.MAX_VALUE;
            int minY = Integer.MAX_VALUE;
            int maxX = Integer.MIN_VALUE;
            int maxY = Integer.MIN_VALUE;

            for (Integer[] integers : group) {

                if (integers[0] < minX) {
                    minX = integers[0];
                }
                if (integers[1] < minY) {
                    minY = integers[1];
                }
                if (integers[0] > maxX) {
                    maxX = integers[0];
                }
                if (integers[1] > maxY) {
                    maxY = integers[1];
                }
            }
            int width = maxX - minX;
            int height = maxY - minY;
            graphics2D.setColor(Color.red);
            graphics2D.drawRect(minX, minY, width, height);
        }
        return bufferedImage;
    }

    /**Group pixels. Pixels divide in 2 group
     * the 2nd group begin from value 250
     * @param diffpixels - list of different pixels*/
    private List<List<Integer[]>> groupPixels(List<Integer[]> diffpixels) {

        List<List<Integer[]>> groups = new ArrayList();
        List<Integer[]> groupOne = new ArrayList();
        List<Integer[]> groupTwo = new ArrayList();

        for (Integer[] coordinates : diffpixels) {
            if ((coordinates[1] > 0) && (coordinates[0] < 250)) {
                groupOne.add(coordinates);
            } else if (coordinates[0] > 250) {
                groupTwo.add(coordinates);
            }
        }
        groups.add(groupOne);
        groups.add(groupTwo);
        return groups;
    }
}