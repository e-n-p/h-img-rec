package h.img.rec;
import java.awt.image.BufferedImage;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author nick
 */
public class editImage {
BufferedImage image;
    public editImage(BufferedImage newImg){
        this.image = newImg;
    }
    
    public Set readColour() {
        Set<Integer> colors = new HashSet<Integer>();
        int w = image.getWidth();
        int h = image.getHeight();
        for (int y = 0; y < h; y++) {
            for (int x = 0; x < w; x++) {
                int pixel = image.getRGB(x, y);
                colors.add(pixel);
            }
        }
        //System.out.println("There are " + colors.size() + " colors");
        return colors;
    }
}
