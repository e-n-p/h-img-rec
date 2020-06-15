package h.img.rec;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author nick
 */
class EditImage {
    private Utilities util;

    EditImage(Utilities aUtil){
        this.util = aUtil;
    }

    enum drawStyle{
        LINE,
        THICK_LINE,
        CIRCLE,
        ARC
    }

    Set<Color> readColour(int h, int w, BufferedImage anImage) {
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
        fill.fillRect(0, 0, anImg.getWidth(), anImg.getHeight());
        fill.dispose();
    }

    private int[] positioning(int h, int w){
        double length = Double.MAX_VALUE;
        int[] pos = new int[4];
        while(length > w/8){
            pos[0] = util.rng(w);
            pos[1] = util.rng(h);
            pos[2] = util.rng(w);
            pos[3] = util.rng(h);
            length = Math.sqrt(((pos[2]-pos[0])*(pos[2]-pos[0]))+((pos[3]-pos[1])*(pos[3]-pos[1])));
        }
        return pos;
    }

    private int lengthControl(){
        return 1;
    }

    private Color getRNGColor(Set<Color> colours){
        Color[] palette = colours.toArray(new Color[0]);
        int randomPick = util.rng(palette.length);
        return palette[randomPick];
    }

    void draw(BufferedImage anImg, drawStyle style, Set<Color> totalColors){

        Graphics2D graphics = anImg.createGraphics();
        Color rngColor = getRNGColor(totalColors);
        graphics.setColor(rngColor);
        int[] positions = positioning(anImg.getHeight(), anImg.getWidth());
        switch (style) {
            case LINE:
                // x1, y1      x2 y2
                graphics.drawLine(positions[0], positions[1], positions[2], positions[3]);
                break;
            case THICK_LINE:
                //thick_line
                graphics.fillRect(positions[0], positions[1], 3, 60);
                break;
            case CIRCLE:
                graphics.fillOval(positions[0], positions[1], 15, 15);
                break;
            case ARC:
                //arc
                graphics.fillArc(positions[0], positions[1],33,50, 25, 30);
                break;
        }

        graphics.dispose();

    }

}
