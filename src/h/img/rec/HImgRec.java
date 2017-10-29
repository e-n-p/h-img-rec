/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package h.img.rec;

import java.io.IOException;
import java.awt.image.BufferedImage;
import java.util.HashSet;
import java.util.Set;


/**
 *
 * @author nick
 * loads image and displays file in Jframe - 23/10/17
 * --To add
 * --drawing on blank image
 * --way to compare images
 */
public class HImgRec{
    
    public static void main(String[] args) throws IOException {
        BufferedImage image;
        Set<Integer> colours = new HashSet<Integer>();

        
        //set image to edit with full file path
        String path = "/home/nick/NetBeansProjects/gitProjects/h-img-rec/res/Hannibal.jpg";
        //create image and place it in jFrame
        makeImage make = new makeImage();
        image = make.makeImage(path);
        make.makeSubject();
        make.makeCanvas();
        
        editImage edit = new editImage(image);
        colours = edit.readColour(image);
    }
    
    
    
}
