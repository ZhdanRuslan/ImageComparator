import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class TestDrive {
    public static void main(String[] args) throws IOException {
        ImageComparator comparator = new ImageComparator();
        BufferedImage img1 = ImageIO.read(TestDrive.class.getResource("/image1.png"));
        BufferedImage img2 = ImageIO.read(TestDrive.class.getResource("/image2.png"));

        BufferedImage result = comparator.getResultImage(img1, img2);

        ImageIO.write(result, "png", new File("E:/result.png"));
    }
}
