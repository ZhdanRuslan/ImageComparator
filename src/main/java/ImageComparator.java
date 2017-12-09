import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class ImageComparator {

    private BufferedImage img1 = null;
    private BufferedImage img2 = null;

    /*Storage for compound rectangle*/
    private Rectangle resultRectangle;

    /*Storage for pixels which have differences*/
    private List<Pixel> pixels;

    private int amountOfDiffPixels = 0;

    public ImageComparator(BufferedImage img1, BufferedImage img2) {
        this.img1 = img1;
        this.img2 = img2;
        resultRectangle = createCompoundRectangle(img1, img2);
        pixels = new ArrayList<Pixel>();
    }

    /**
    * Method which create composite rectangle
    * using max height
    * and width from each image
    * @param image1 - first image
    * @param image2 - second image
    * @return rectangle
     * */
    private Rectangle createCompoundRectangle(BufferedImage image1, BufferedImage image2) {

        int maxX = Math.max(image1.getWidth(), image2.getWidth());
        int maxY = Math.max(image1.getHeight(), image2.getHeight());

        return new Rectangle(maxX, maxY);
    }

    /**Getting amount of different pixels in both images
    * @return amount - amount of different pixels*/
    public int getDifferentPixelsAmount(){

        for (int y = 0; y < img1.getHeight() && y < img2.getHeight(); y++) {

            for (int x = 0; x < img1.getWidth() && x < img2.getWidth(); x++) {

                if (img1.getRGB(x, y) != img2.getRGB(x, y)) {
                    Pixel pixel = new Pixel(x, y);
                    pixels.add(pixel);
                    amountOfDiffPixels++;
                }
            }
        }
        amountOfDiffPixels = amountOfDiffPixels + (getWidthDiff() * getHeightDiff());
        return amountOfDiffPixels;
    }

    /**
     * Getting the width differences in images.
     * @return the width diff
     */

    private int getWidthDiff() {
        return Math.abs(img1.getWidth() - img2.getWidth());
    }

    /**
     * Getting the width differences in images.
     * @return the width diff
     */
    private int getHeightDiff() {
        return Math.abs(img1.getHeight() - img2.getHeight());
    }
}
