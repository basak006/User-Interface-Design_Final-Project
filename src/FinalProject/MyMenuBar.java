/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package FinalProject;

import java.awt.Dimension;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JToolBar;


public class MyMenuBar extends JMenuBar{

    
    private JMenu fileMenu , shapeMenu , colorMenu ,helpMenu;
    private JMenuItem loadMenuItem, saveMenuItem, exitMenuItem , helpMenuItem;
    private JToolBar toolbar;

    public JToolBar getToolbar() {
        return toolbar;
    }

    public void setToolbar(JToolBar toolbar) {
        this.toolbar = toolbar;
    }
  /**
   * @param  none
   * The MyMenuBar() constructor creates an object adding Menu's fileMenu, shapeMenu, colorMenu
   * helpMenu and the MenuItems loadMenuItem, helpMenuItem, saveMenuItem, exitMenuItem to the menu fileMenu
   */  
    MyMenuBar()
    {
        fileMenu = new JMenu("File");
        shapeMenu = new JMenu("Shape");
        colorMenu = new JMenu("Color");
        helpMenu = new JMenu("Help");
        loadMenuItem = new JMenuItem("Load");
        saveMenuItem = new JMenuItem("Save");
        exitMenuItem = new JMenuItem("Exit");
        helpMenuItem = new JMenuItem("Help");
        toolbar = new JToolBar("Toolbar", JToolBar.VERTICAL);
        toolbar.setPreferredSize(new Dimension(250,700));
        fileMenu.add(loadMenuItem);
        fileMenu.add(saveMenuItem);
        fileMenu.addSeparator();
        fileMenu.add(exitMenuItem);
        helpMenu.add(helpMenuItem);
        this.add(fileMenu);
        this.add(shapeMenu);
        this.add(colorMenu);
        this.add(helpMenu);
       
    }
/**
 * 
 * @param none
 * @return colorMenu object of type JMenu
 */
    public JMenu getColorMenu() {
        return colorMenu;
    }
/**
 * 
 * @param colorMenu
 * @return none
 */
    public void setColorMenu(JMenu colorMenu) {
        this.colorMenu = colorMenu;
    }
/**
 * @param none
 * @return exitMenuItem of type JMenuItem 
 */
    public JMenuItem getExitMenuItem() {
        return exitMenuItem;
    }
/**
 * 
 * @param exitMenuItem 
 */
    public void setExitMenuItem(JMenuItem exitMenuItem) {
        this.exitMenuItem = exitMenuItem;
    }
/**
 * 
 * @return fileMenu
 */
    public JMenu getFileMenu() {
        return fileMenu;
    }

    public void setFileMenu(JMenu fileMenu) {
        this.fileMenu = fileMenu;
    }

    public JMenuItem getLoadMenuItem() {
        return loadMenuItem;
    }

    public void setLoadMenuItem(JMenuItem loadMenuItem) {
        this.loadMenuItem = loadMenuItem;
    }

    public JMenuItem getSaveMenuItem() {
        return saveMenuItem;
    }

    public void setSaveMenuItem(JMenuItem saveMenuItem) {
        this.saveMenuItem = saveMenuItem;
    }

    public JMenu getShapeMenu() {
        return shapeMenu;
    }

    public void setShapeMenu(JMenu shapeMenu) {
        this.shapeMenu = shapeMenu;
    }
    public JMenu getHelp()
    {
        return helpMenu;
    }
    public void setHelp(JMenu helpMenu)
    {
        this.helpMenu = helpMenu;
    }
   }
