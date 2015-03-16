/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FinalProject;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Path2D;

class CoordinateSystem extends JFrame {


    public static void main(String args[]) {
        JFrame t = new JFrame();
        
        t.add(new JComponent() {
            public void paintComponent(Graphics g) {
                Path2D p = new Path2D.Double();
                p.moveTo(15, 15);
                p.lineTo(150, 75);
                p.lineTo(100, 10);
                p.lineTo(10, 100);

                ((Graphics2D) g).draw(p);
            }
        });

        t.setDefaultCloseOperation(EXIT_ON_CLOSE);
        t.setSize(200, 200);
        t.setVisible(true);
    }
}