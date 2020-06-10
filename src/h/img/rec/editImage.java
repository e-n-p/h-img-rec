package h.img.rec;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author nick
 */
class editImage {
    
    Set readColour(int h, int w, BufferedImage anImage) {
        Set<Color> uniqueColors = new HashSet<>();
        for (int y = 0; y < h; y++) {
            for (int x = 0; x < w; x++) {
                Color pixelColor = new Color(anImage.getRGB(x, y));
                uniqueColors.add(pixelColor);
            }
        }
        System.out.println("There are " + uniqueColors.size() + " colors");
        return uniqueColors;
    }

    void setBackGround(BufferedImage anImg){
        Graphics2D fill = anImg.createGraphics();
        fill.setColor(Color.WHITE);
        fill.fillRect(0, 0, anImg.getWidth()-1, anImg.getHeight()-1);
        fill.dispose();
    }

    int[] lengthControl(int h, int w, utilities util){
        double length = Double.MAX_VALUE;
        int[] pos = new int[4];
        while(length > w/4){
            pos[0] = util.rng(w);
            pos[1] = util.rng(h);
            pos[2] = util.rng(w);
            pos[3] = util.rng(h);
            length = Math.sqrt(((pos[2]-pos[0])*(pos[2]-pos[0]))+((pos[3]-pos[1])*(pos[3]-pos[1])));
        }
        return pos;
    }

    Color getRNGColor(Set<Color> colours, utilities util){
        Color[] palette = colours.toArray(new Color[0]);
        int randomPick = util.rng(palette.length);
        return palette[randomPick];
    }

}
