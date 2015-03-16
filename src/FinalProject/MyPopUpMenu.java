/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package FinalProject;

import java.awt.PopupMenu;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;


public class MyPopUpMenu extends PopupMenu{

    public JMenuItem getDontQuitMenuItem() {
        return dontQuitMenuItem;
    }

    public void setDontQuitMenuItem(JMenuItem dontQuitMenuItem) {
        this.dontQuitMenuItem = dontQuitMenuItem;
    }

    public JPopupMenu getPopupMenu() {
        return popupMenu;
    }

    public void setPopupMenu(JPopupMenu popupMenu) {
        this.popupMenu = popupMenu;
    }

    public JMenuItem getQuitAndSaveMenuItem() {
        return quitAndSaveMenuItem;
    }

    public void setQuitAndSaveMenuItem(JMenuItem quitAndSaveMenuItem) {
        this.quitAndSaveMenuItem = quitAndSaveMenuItem;
    }

    public JMenuItem getQuitWithoutSaveMenuItem() {
        return quitWithoutSaveMenuItem;
    }

    public void setQuitWithoutSaveMenuItem(JMenuItem quitWithoutSaveMenuItem) {
        this.quitWithoutSaveMenuItem = quitWithoutSaveMenuItem;
    }
    
    private JPopupMenu popupMenu;
    private JMenuItem quitAndSaveMenuItem,quitWithoutSaveMenuItem,dontQuitMenuItem;
    
    MyPopUpMenu()
    { 
       popupMenu = new JPopupMenu();
       quitAndSaveMenuItem = new JMenuItem("Quit And Save?");
       quitWithoutSaveMenuItem = new JMenuItem("Quit Without saving?");
       dontQuitMenuItem = new JMenuItem("Don't Quit!");
       popupMenu.add(quitAndSaveMenuItem);
       popupMenu.add(quitWithoutSaveMenuItem);
       popupMenu.add(dontQuitMenuItem);
       
    }

   
  
}
