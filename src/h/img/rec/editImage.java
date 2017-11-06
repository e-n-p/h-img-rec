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
int h, w;
    public editImage(BufferedImage newImg){
        this.image = newImg;
        this.h = image.getHeight();
        this.w = image.getWidth();
        System.out.print("img width:"+image.getWidth());
        System.out.print(" img height:"+image.getHeight());
        System.out.println();
    }
    
    public Set readColour() {
        Set<Integer> colors = new HashSet<Integer>();
        for (int y = 0; y < h; y++) {
            for (int x = 0; x < w; x++) {
                int pixel = image.getRGB(x, y);
                colors.add(pixel);
            }
        }
        System.out.println("There are " + colors.size() + " colors");
        return colors;
    }
    
    
}
