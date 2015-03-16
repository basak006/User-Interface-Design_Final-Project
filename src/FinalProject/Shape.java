/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package FinalProject;

import java.awt.Color;
import java.awt.Rectangle;


public interface Shape {
   
   
   public void setColor(Color color);
   public Color getColor();
   public void setBounds(Rectangle rectangle);
   public Rectangle getBounds();
   public void setFillStatus(boolean status);
   public boolean getFillStatus();
  // public void setSize(int x,int y);
}
