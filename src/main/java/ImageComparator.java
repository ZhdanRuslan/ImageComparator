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

}