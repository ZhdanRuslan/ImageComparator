import java.awt.*;
import java.awt.image.BufferedImage;

public class ImageComparator {

    private BufferedImage img1 = null;
    private BufferedImage img2 = null;

    public ImageComparator(BufferedImage img1, BufferedImage img2) {
        this.img1 = img1;
        this.img2 = img2;
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
}
