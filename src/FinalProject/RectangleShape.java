/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package FinalProject;

import java.awt.Color;
import java.awt.Rectangle;
import java.io.Serializable;


public class RectangleShape implements Shape,Serializable{

    
    private Color color;
    private Rectangle rectangle;
    private boolean isfilled;
    @Override
    public void setColor(Color color) {
        //throw new UnsupportedOperationException("Not supported yet.");
        this.color = color;
    }

    @Override
    public Color getColor() {
        //throw new UnsupportedOperationException("Not supported yet.");
        return color;
    }

    @Override
    public void setBounds(Rectangle rectangle) {
       // throw new UnsupportedOperationException("Not supported yet.");
        this.rectangle = rectangle;
    }

    @Override
    public Rectangle getBounds() {
       // throw new UnsupportedOperationException("Not supported yet.");
        return rectangle;
    }

    @Override
    public void setFillStatus(boolean fillstatus) {
        //throw new UnsupportedOperationException("Not supported yet
        isfilled = fillstatus;
    }

    @Override
    public boolean getFillStatus() {
        //throw new UnsupportedOperationException("Not supported yet.");
            return isfilled;
    }
    
}
