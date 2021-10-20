package shapes;

import java.awt.*;
import java.awt.geom.*; 


/**
 * A circle that can be manipulated and that draws itself on a canvas.
 * 
 * @author  Michael Kolling and David J. Barnes
 * @version 1.0.  (15 July 2000) 
 */

public class Circle extends shape{

    public static final double PI=3.1416;
    
    private int diameter;

    public Circle(){
        super();
        diameter = 50;
    }

    @Override
    public void draw(){
        if(isVisible) {
            Canvas canvas = Canvas.getCanvas();
            canvas.draw(this, color, 
                new Ellipse2D.Double(xPosition, yPosition, 
                diameter, diameter));
            canvas.wait(10);
        }
    }
    
    @Override
    public void erase(){
        if(isVisible) {
            Canvas canvas = Canvas.getCanvas();
            canvas.erase(this);
        }
    }
    
    
    /**
     * Move the circle horizontally.
     * @param distance the desired distance in pixels
     */
    public void changePosition(int newx, int newy){
        erase();
        xPosition = newx;
        yPosition = newy;
        draw();
    }

    /**
     * Change the size.
     * @param newDiameter the new size (in pixels). Size must be >=0.
     */
    public void changeSize(int newDiameter){
        erase();
        diameter = newDiameter;
        draw();
    }
    
    //GET Y SET
    public int getDiameter(){
        return this.diameter;
    }
    
    public int getXposition (){
        return xPosition;
    }
    
    public int getYposition (){
        return yPosition;
    }
    
    public void setY (int y){
        this.yPosition = y;
    }
    
    public void setX (int x){
        this.xPosition = x; 
    }

}
