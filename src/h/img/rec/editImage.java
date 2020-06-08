package h.img.rec;
import java.awt.image.BufferedImage;
import java.util.HashSet;
import java.util.Set;
import java.awt.Color;

/**
 *
 * @author nick
 */
public class editImage {
    private BufferedImage image;
    private int h, w;

    public editImage(BufferedImage newImg){
        this.image = newImg;
        this.h = image.getHeight();
        this.w = image.getWidth();
        System.out.print("img width: " + image.getWidth());
        System.out.print("img height: " + image.getHeight());
        System.out.println();
    }
    
    Set readColour() {
        Set<Color> uniqueColors = new HashSet<>();
        for (int y = 0; y < h; y++) {
            for (int x = 0; x < w; x++) {
                Color pixelColor = new Color(image.getRGB(x, y));
                uniqueColors.add(pixelColor);
            }
        }
        System.out.println("There are " + uniqueColors.size() + " colors");
        return uniqueColors;
    }
    
    
}
