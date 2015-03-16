/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package FinalProject;

// import static FinalProject.SelectShape.changeZoom;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.event.MouseInputAdapter;
import java.awt.TexturePaint;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Arc2D;
import java.awt.geom.GeneralPath;
import java.awt.geom.*;
import javax.swing.JFormattedTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.JComponent;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


/*
Final Project 
11-12-2014
Priyankana Basak
Mounika Alla
TA : Saketh
*/

public class MySelectionFrame  extends JFrame{
    JLabel label1;
    MyMenuBar menuBar;
    ImageIcon circleIcon, rectangleIcon, handIcon, arcIcon, polyIcon;
    JRadioButtonMenuItem redMenuItem, greenMenuItem, blueMenuItem, polkaMenuItem,
                         rectangleMenuItem, ellipseMenuItem, arcMenuItem, polyMenuItem;
     private JRadioButton redRadioButton,blueRadioButton,greenRadioButton,polkaRadioButton, fillRadioButton,unfillRadioButton;
    JMenu filledMenu;
    JButton circleIconButton,rectangleIconButton ,arcIconButton,polyIconButton, clearGraphics ,colorButton, handIconButton;
    JRadioButtonMenuItem filledMenuItem, unFilledMenuItem;
    ButtonGroup colorButtonGroup, shapeButtonGroup , fillStatusButtonGroup , toolbarColorGroup, toolbarFillUnfillGroup;
    SelectionArea selectionArea;
    MyPopUpMenu popupMenu;
    JFileChooser filechooser;
    Action greenbuttonaction,bluebuttonaction,redbuttonaction,polkabuttonaction, fillbuttonaction,unfillbuttonaction
            ,ellipsebuttonaction , arcbuttonaction, polybuttonaction, rectanglebuttonaction, colorchooseraction, handbuttonaction, zoomaction;
    Color shapecolor;
    boolean dragSelected;
    Vector<Shape> shapevectorlist;
    JMenuItem colormenuitem;
     JPanel zoomPanel;
     JSpinner zoomSelecter;
         
       
    
    
    /**
     * MyselectionFrame extended from JFrame
     * Constructors MySelectionFrame adds several components to its content pane
     * 
     */
    MySelectionFrame()
    {
        
        
        selectionArea = new SelectionArea(this);
        popupMenu = new MyPopUpMenu();
        MouseListener popupListener = selectionArea.new PopupListener(popupMenu.getPopupMenu());
        selectionArea.addMouseListener(popupListener);
        
        menuBar = new MyMenuBar();
        
        shapevectorlist = new Vector<>();
        
        redMenuItem = new JRadioButtonMenuItem("RED");
        colormenuitem = new JMenuItem("Other");
        
        dragSelected = false;
        filechooser = new JFileChooser();
        greenMenuItem = new JRadioButtonMenuItem("GREEN");
        blueMenuItem = new JRadioButtonMenuItem("BLUE");
        polkaMenuItem = new JRadioButtonMenuItem("POLKA PATTERN");
        filledMenuItem = new JRadioButtonMenuItem("FILLED");
        unFilledMenuItem = new JRadioButtonMenuItem("UNFILLED");
        rectangleMenuItem = new JRadioButtonMenuItem("RECTANGLE");
        ellipseMenuItem = new JRadioButtonMenuItem("ELLIPSE");
        arcMenuItem = new JRadioButtonMenuItem("ARC");
        polyMenuItem = new JRadioButtonMenuItem("POLY LINES");
        filledMenu = new JMenu("Filled?");
        
        //creating radiobuttons which are added to the toolbar
        redRadioButton = new JRadioButton("RED");
        blueRadioButton = new JRadioButton("BLUE");
        greenRadioButton = new JRadioButton("GREEN");
        polkaRadioButton = new JRadioButton("FILL WITH POLKA PATTERN");
        fillRadioButton = new JRadioButton("FILL");
        unfillRadioButton = new JRadioButton("UNFILL");
        colorButton = new JButton("choose color");
        //colorButton.setBackground(Color.RED);
        
        //creating the buttons with icons;
        circleIcon = createNavigationIcon("Circle");
        Image circle = circleIcon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        circleIcon.setImage(circle);
        circleIconButton = new JButton(circleIcon);
        
        
        arcIcon = createNavigationIcon("Arc");
        Image arc = arcIcon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        arcIcon.setImage(arc);
        arcIconButton = new JButton(arcIcon);
        
        polyIcon = createNavigationIcon("PolyLines");
        Image poly = polyIcon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        polyIcon.setImage(poly);
        polyIconButton = new JButton(polyIcon);
        
        rectangleIcon = createNavigationIcon("Rectangle");
        Image rectangle = rectangleIcon.getImage().getScaledInstance(50,50,Image.SCALE_SMOOTH);
        rectangleIcon.setImage(rectangle);
        //circleIconButton.setPreferredSize(new Dimension(100,100));
        rectangleIconButton = new JButton(rectangleIcon);
        clearGraphics = new JButton("Clear Graphics");
        
        zoomPanel = new JPanel();
        zoomSelecter = new JSpinner();
        
        handIcon = createNavigationIcon("hand");
        Image hand = handIcon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        handIcon.setImage(hand);
        handIconButton = new JButton(handIcon);
        
        label1 = new JLabel("Drag within panel");
        label1.setLabelFor(selectionArea);
        
        
        
        greenbuttonaction = selectionArea.new GreenButtonListener("Green");
        redbuttonaction = selectionArea.new RedButtonListener("Red");
        bluebuttonaction = selectionArea.new BlueButtonListener("Blue");
        polkabuttonaction = selectionArea.new PolkaButtonListener("Polka");
        fillbuttonaction = selectionArea.new FillButtonListener("Fill");
        unfillbuttonaction = selectionArea.new UnfillButtonListener("Unfill");
        ellipsebuttonaction = selectionArea.new EllipseButtonListener("Ellipse", circleIcon);
        arcbuttonaction = selectionArea.new ArcButtonListener("Arc", arcIcon);
        polybuttonaction = selectionArea.new PolyButtonListener("Arc", polyIcon);
        rectanglebuttonaction = selectionArea.new RectangleButtonListener("Rectangle", rectangleIcon);
        colorchooseraction = selectionArea.new ColorChooserListener(selectionArea,"Other");
       
        colorButtonGroup = new ButtonGroup();
        shapeButtonGroup = new ButtonGroup();
        fillStatusButtonGroup = new ButtonGroup();
        toolbarColorGroup = new ButtonGroup();
        toolbarFillUnfillGroup = new ButtonGroup();
        addMenuMnemonics();
        addListeners();
    }
    
     
    final void addListeners()
    {
        popupMenu.getDontQuitMenuItem().addActionListener(selectionArea.new PopupMenuItemListener());
        popupMenu.getQuitAndSaveMenuItem().addActionListener(selectionArea.new PopupMenuItemListener());
        popupMenu.getQuitWithoutSaveMenuItem().addActionListener(selectionArea.new PopupMenuItemListener());
        addWindowListener(selectionArea.new MyWindowListener());
        menuBar.getExitMenuItem().addActionListener(selectionArea.new PopupMenuItemListener());
        menuBar.getLoadMenuItem().addActionListener(selectionArea.new LoadAndSaveListener());
        menuBar.getSaveMenuItem().addActionListener(selectionArea.new LoadAndSaveListener());
        //adding buttons to the listeners
       
       redMenuItem.setAction(redbuttonaction);
       greenMenuItem.setAction(greenbuttonaction);
       blueMenuItem.setAction(bluebuttonaction);
       polkaMenuItem.setAction(polkabuttonaction);
       rectangleMenuItem.setAction(rectanglebuttonaction);
       rectangleMenuItem.setIcon(null);
       ellipseMenuItem.setAction(ellipsebuttonaction);
       ellipseMenuItem.setIcon(null);
       arcMenuItem.setAction(arcbuttonaction);
       arcMenuItem.setIcon(null);
       polyMenuItem.setAction(polybuttonaction);
       polyMenuItem.setIcon(null);
       
       filledMenuItem.setAction(fillbuttonaction);
       unFilledMenuItem.setAction(unfillbuttonaction); 
       redRadioButton.setAction(redbuttonaction);
       blueRadioButton.setAction(bluebuttonaction);
       greenRadioButton.setAction(greenbuttonaction);
       polkaRadioButton.setAction(polkabuttonaction);
         
       colorButton.setAction(colorchooseraction);
       colormenuitem.setAction(colorchooseraction);
       fillRadioButton.setAction(fillbuttonaction); 
       unfillRadioButton.setAction(unfillbuttonaction);
       
       circleIconButton.setAction(ellipsebuttonaction);
       circleIconButton.setText(null);
       
        Integer value = new Integer(100);
        Integer min = new Integer(100);
        Integer max = new Integer(1000);
        Integer step = new Integer(25);
        SpinnerNumberModel newmodel = new SpinnerNumberModel(value, min, max, step);
        
       zoomSelecter.setModel(newmodel);
       zoomSelecter.addChangeListener(selectionArea.new zoomChangeListener());
       arcIconButton.setAction(arcbuttonaction);
       arcIconButton.setText(null);
       polyIconButton.setAction(polybuttonaction);
       polyIconButton.setText(null);
       rectangleIconButton.setAction(rectanglebuttonaction);
       rectangleIconButton.setText(null);
       handIconButton.addActionListener(selectionArea.new DragButtonListener());
           
       clearGraphics.addActionListener(selectionArea);
       
     
    }
    final void addMenuMnemonics()
    {
        redMenuItem.setMnemonic(KeyEvent.VK_R);
        redMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_1,ActionEvent.ALT_MASK));
        greenMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_2,ActionEvent.ALT_MASK));
        greenMenuItem.setMnemonic(KeyEvent.VK_G);
        blueMenuItem.setMnemonic(KeyEvent.VK_B);
        blueMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_3,ActionEvent.ALT_MASK));
        filledMenuItem.setMnemonic(KeyEvent.VK_F);
        filledMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_6,ActionEvent.ALT_MASK));
        unFilledMenuItem.setMnemonic(KeyEvent.VK_U);
        unFilledMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_7,ActionEvent.ALT_MASK));
        rectangleMenuItem.setMnemonic(KeyEvent.VK_R);
        rectangleMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_5,ActionEvent.ALT_MASK));
        ellipseMenuItem.setMnemonic(KeyEvent.VK_E);
        ellipseMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_4,ActionEvent.ALT_MASK));
        arcMenuItem.setMnemonic(KeyEvent.VK_A);
        arcMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_4,ActionEvent.ALT_MASK));
        polyMenuItem.setMnemonic(KeyEvent.VK_P);
        polyMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_4,ActionEvent.ALT_MASK));
        
        menuBar.getColorMenu().setMnemonic(KeyEvent.VK_C);
        menuBar.getLoadMenuItem().setMnemonic(KeyEvent.VK_L);
        menuBar.getLoadMenuItem().setToolTipText("Load Menu Item");
        menuBar.getSaveMenuItem().setMnemonic(KeyEvent.VK_S);
        menuBar.getSaveMenuItem().setToolTipText("Save Menu Item");
        menuBar.getExitMenuItem().setMnemonic(KeyEvent.VK_E);
        menuBar.getExitMenuItem().setToolTipText("Exit Menu Item");
        menuBar.getFileMenu().setMnemonic(KeyEvent.VK_F);
        menuBar.getShapeMenu().setMnemonic(KeyEvent.VK_S);
        menuBar.getShapeMenu().setToolTipText("select a shape");
        menuBar.getColorMenu().setToolTipText("set a color value to the shape");
        
       
    }
    
    void createFrame()
    {
       
       setTitle("Select a shape");
       setJMenuBar(menuBar);
       setSize(800,550);
       setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       setVisible(true);
       
       //adding the buttons to the button group
       shapeButtonGroup.add(rectangleMenuItem);
       shapeButtonGroup.add(ellipseMenuItem);
       shapeButtonGroup.add(arcMenuItem);
       shapeButtonGroup.add(polyMenuItem);
       colorButtonGroup.add(redMenuItem);
       colorButtonGroup.add(blueMenuItem);
       colorButtonGroup.add(greenMenuItem);
       colorButtonGroup.add(polkaMenuItem);
       
       fillStatusButtonGroup.add(filledMenuItem);
       fillStatusButtonGroup.add(unFilledMenuItem);
       
       
       rectangleMenuItem.setSelected(true);
       redMenuItem.setSelected(true);
       unFilledMenuItem.setSelected(true);
       
       toolbarColorGroup.add(redRadioButton);
       toolbarColorGroup.add(blueRadioButton);
       toolbarColorGroup.add(greenRadioButton);
       toolbarColorGroup.add(polkaRadioButton);
       toolbarFillUnfillGroup.add(fillRadioButton);
       toolbarFillUnfillGroup.add(unfillRadioButton);
       

       //setting the selection for the radiobuttons
 
       redRadioButton.setSelected(true);
       unfillRadioButton.setSelected(true);
       
       menuBar.getToolbar().add(circleIconButton);
       menuBar.getToolbar().add(arcIconButton);
       menuBar.getToolbar().add(polyIconButton);
       menuBar.getToolbar().add(rectangleIconButton);
       menuBar.getToolbar().addSeparator();
       menuBar.getToolbar().add(redRadioButton);  
       menuBar.getToolbar().add(blueRadioButton);
       menuBar.getToolbar().add(greenRadioButton);
       menuBar.getToolbar().add(polkaRadioButton);
       menuBar.getToolbar().addSeparator();
       menuBar.getToolbar().add(fillRadioButton);
       menuBar.getToolbar().addSeparator();
       menuBar.getToolbar().add(colorButton);
       menuBar.getToolbar().add(unfillRadioButton);
       menuBar.getToolbar().addSeparator();
       menuBar.getToolbar().add(clearGraphics);
       menuBar.getToolbar().addSeparator();
       menuBar.getToolbar().add(colorButton);
       menuBar.getToolbar().addSeparator();
       menuBar.getToolbar().add(handIconButton);
       menuBar.getToolbar().add(label1);
       menuBar.getToolbar().add(zoomSelecter);
       

        //adding the menuItems(radioButtonMenuItems) to the menubar
       menuBar.getColorMenu().add(redMenuItem);
       menuBar.getColorMenu().add(blueMenuItem);
       menuBar.getColorMenu().add(greenMenuItem);
       menuBar.getColorMenu().add(polkaMenuItem);
       menuBar.getColorMenu().add(colormenuitem);
       menuBar.getShapeMenu().add(ellipseMenuItem);
       menuBar.getShapeMenu().add(rectangleMenuItem);
       menuBar.getShapeMenu().add(arcMenuItem);
       menuBar.getShapeMenu().add(polyMenuItem);
       menuBar.getShapeMenu().addSeparator();
       
       //menuBar.getToolbar().add(redMenuItem);
       filledMenu.add(filledMenuItem);
       filledMenu.add(unFilledMenuItem);
       menuBar.getShapeMenu().add(filledMenu);
       getContentPane().add(menuBar.getToolbar(),BorderLayout.WEST);
       getContentPane().add(selectionArea,BorderLayout.CENTER);
       
       
   }
    
        protected static ImageIcon createNavigationIcon(String imageName) {
        String imgLocation = "/images/"
                             + imageName
                             + ".jpg";
        java.net.URL imageURL = MySelectionFrame.class.getResource(imgLocation);

        if (imageURL == null) {
            System.err.println("Resource not found: "
                               + imgLocation);
            return null;
        } else {
            return new ImageIcon(imageURL);
        }
    }

       private class SelectionArea extends JPanel implements ActionListener{
        Rectangle currentRect = null;
        Rectangle rectToDraw = null;
        Rectangle nullRectangle =  new Rectangle(0,0,0,0);
        Rectangle previousRectDrawn = new Rectangle();
        Rectangle panelRectangle;
        boolean fillShape = false;
        Shape shape;
        Shape shapeToDrag;
        Color shapecolor;
        int numberOfRectangles = 0, numberOfEllipses = 0;
        RectangleShape rectangle = new RectangleShape();
        OvalShape  ellipse = new OvalShape();
        ArcShape  arc = new ArcShape();
        PolyShape  poly = new PolyShape();
        MySelectionFrame frameObject;
               
       
       public void setScale(double s)
          {
            double scale = s;
            //revalidate(); // update the scroll pane
            repaint();
          }
        public Graphics changeGraphicsRendering(Graphics g)
        {
            Graphics2D graphics2d = (Graphics2D)g;
            Stroke stroke = new BasicStroke(1, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0,
            new float[] { 100, 0 }, 0);
            graphics2d.setStroke(stroke);

                      
            return graphics2d;
        }
        
        public Graphics changeFilling(Graphics g)
        {
                Graphics2D graphics2d = (Graphics2D)g;
                GradientPaint redtowhite = new GradientPaint(0,0,graphics2d.getColor(),100, 0,graphics2d.getColor());
                graphics2d.setPaint(redtowhite);
                return graphics2d;
               
        }
        public void clearAllGraphics(Graphics g)
        {
            super.paintComponent(g);
        }
        
       /* public void paint(Graphics g) 
        {
        Graphics2D g2 = (Graphics2D)g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
        RenderingHints.VALUE_ANTIALIAS_ON);
        Font font = new Font("Serif", Font.PLAIN, 96);
        g2.setFont(font);
        g2.drawString("Text", 40, 120);
        } */
        
        
        public SelectionArea(MySelectionFrame frameObject) {
            this.frameObject = frameObject;
            shape = rectangle;
            shapecolor = Color.red;
            rectangle.setColor(shapecolor);
            
            
            panelRectangle = new Rectangle(0,0,getWidth(),getHeight());
            setOpaque(true);
            setMinimumSize(new Dimension(10,10)); //don't hog space
    
            MyListener myListener = new MyListener();
            MyMotionListener myMotionListener = new MyMotionListener(this);
            addMouseListener(myMotionListener);
            addMouseMotionListener(myMotionListener);
            addMouseListener(myListener);
            addMouseMotionListener(myListener);
        }

        
       
        @Override
        public void actionPerformed(ActionEvent e) {
                shapevectorlist.clear();
                rectToDraw = nullRectangle;
                currentRect = null;
                repaint();
                                
        }
        
        class LoadAndSaveListener implements ActionListener
        {

            @Override
            public void actionPerformed(ActionEvent e) {
                
                FileOutputStream outputstream;
                ObjectOutputStream objectoutputstream;
                FileInputStream inputstream;
                ObjectInputStream objectinputstream;
                if (e.getSource() == menuBar.getSaveMenuItem()) {
            int returnVal = filechooser.showSaveDialog(MySelectionFrame.this);

            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File file = filechooser.getSelectedFile();
                        try {
                            outputstream =  new FileOutputStream(file);
                            objectoutputstream = new ObjectOutputStream(outputstream);
                            
                              objectoutputstream.writeObject(shapevectorlist);
                                
                            
                            objectoutputstream.flush();
                            objectoutputstream.close();
                            
                        } catch (FileNotFoundException ex) {
                            Logger.getLogger(MySelectionFrame.class.getName()).log(Level.SEVERE, null, ex);
                        }
                         catch (IOException ex) {
                                Logger.getLogger(MySelectionFrame.class.getName()).log(Level.SEVERE, null, ex);
                         }
                         
                        
            } else {
             
            }
           

        //Handle save button action.
        } else if (e.getSource() == menuBar.getLoadMenuItem()) {
            int returnVal = filechooser.showOpenDialog(MySelectionFrame.this);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File file = filechooser.getSelectedFile();
                        try {
                            inputstream = new FileInputStream(file);
                            objectinputstream = new ObjectInputStream(inputstream);
                           
                            shapevectorlist = (Vector<Shape>)objectinputstream.readObject();
                            
                                        
                            } 
                            
                           
                         catch (FileNotFoundException ex) {
                            Logger.getLogger(MySelectionFrame.class.getName()).log(Level.SEVERE, null, ex);
                            
                        }
                         catch (IOException ex) {
                               Logger.getLogger(MySelectionFrame.class.getName()).log(Level.SEVERE, null, ex);
                             
                         }
                         catch (ClassNotFoundException ex) {
                                Logger.getLogger(MySelectionFrame.class.getName()).log(Level.SEVERE, null, ex);
                            }
            } else {
                
            }
            
        }
    }

    /** Returns an ImageIcon, or null if the path was invalid. */
    
            
   }
        class PopupListener extends MouseAdapter {
        JPopupMenu popup;
           
        PopupListener(JPopupMenu popupMenu) {
            popup = popupMenu;
        }

            @Override
        public void mousePressed(MouseEvent e) {
            maybeShowPopup(e);
        }

            @Override
        public void mouseReleased(MouseEvent e) {
            maybeShowPopup(e);
        }

        private void maybeShowPopup(MouseEvent e) {
            if (e.isPopupTrigger()) {
                popup.show(e.getComponent(),
                           e.getX(), e.getY());
            }
        }
    }
       
      
        class PopupMenuItemListener implements ActionListener
        {

            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource().equals(popupMenu.getQuitAndSaveMenuItem()))
                {
                    System.exit(0);
                }
                else if(e.getSource().equals(popupMenu.getQuitWithoutSaveMenuItem()))
                {
                    System.exit(0);
                           
                }
                else if(e.getSource().equals(menuBar.getExitMenuItem()))
                {
                    System.exit(0);
                }
                else
                {
                    System.out.println("don't quit");
                }
                
            }
            
        }
        private class MyMotionListener extends MouseInputAdapter
        {
            JPanel mypanel;
            int xfactor , yfactor;
            MyMotionListener(JPanel j)
            {
                mypanel = j;
            }
            int i;
            @Override
            public void mousePressed(MouseEvent e)
            {
                i = isInRect(e);
                if(dragSelected)
                {
                    System.out.println("hello mouse pressed");
                     
                     if(i > -1)
                     {
                         
                         System.out.println("in rectangle");
                         shapeToDrag = shapevectorlist.get(i);
                         yfactor = e.getY() - shapeToDrag.getBounds().y;
                         xfactor = e.getX() - shapeToDrag.getBounds().x;
                         System.out.println("shape to drag "+shapeToDrag.getBounds());
                         shapevectorlist.remove(i);
                         rectToDraw = new Rectangle(0,0,0,0);
                         
                     }
                }
            }
                        
               private void paintRemaingGrapics(Graphics g) {
                
               for(int i = 0 ; i<shapevectorlist.size();i++)
                {
                 
                    drawShapeAtIndex(shapevectorlist.get(i),g);
                    
                }
                
            }
            
            @Override
            public void mouseDragged(MouseEvent e)
            {
                
                if(dragSelected && (shapeToDrag != null))
                {
                    if(i > -1)
                    {
                        mypanel.getGraphics().clearRect(shapeToDrag.getBounds().x,shapeToDrag.getBounds().y,shapeToDrag.getBounds().width,shapeToDrag.getBounds().height);
                        paintRemaingGrapics(mypanel.getGraphics());
                        int newx = e.getX() - xfactor;
                        int newy = e.getY() - yfactor;
                        shapeToDrag.setBounds(new Rectangle(newx, newy ,shapeToDrag.getBounds().width,shapeToDrag.getBounds().height));
                        drawShapeAtIndex(shapeToDrag, mypanel.getGraphics());
                        
                    }
                }
            }
            
            @Override
            public void mouseReleased(MouseEvent e)
            {
                if(dragSelected && (shapeToDrag != null))
                {
                   mypanel.getGraphics().clearRect(shapeToDrag.getBounds().x,shapeToDrag.getBounds().y,shapeToDrag.getBounds().width,shapeToDrag.getBounds().height);
                   
                   mypanel.repaint();
                   shapevectorlist.add(shapeToDrag);
                   shapeToDrag = null;
                }
            }
            public int isInRect(MouseEvent e)
            {
                for(int i = shapevectorlist.size()-1 ; i >=0 ;i--)
                {
                    if(shapevectorlist.get(i).getBounds().contains(e.getX(),e.getY()))
                    {
                        return i;
                    }
                    
                }
                return -1;
            }
            
        }
        private class MyListener extends MouseInputAdapter {
            @Override
            public void mousePressed(MouseEvent e) {
                int x = e.getX();
                int y = e.getY();
                currentRect = new Rectangle(x, y, 0, 0);
                if(!dragSelected)
                {
                
                System.out.println("mouse pressed");
                updateDrawableRect(getWidth(), getHeight());
                if(shape.equals(rectangle))
                  numberOfRectangles++;
                else
                  numberOfEllipses++;
                }
            }
    
            @Override
            public void mouseDragged(MouseEvent e) {
                if(!dragSelected)
                {
                updateSize(e);
                System.out.println("mouseDragged");
                System.out.println(e.getX()+" "+e.getY());
                }
            }
    
            @Override
            public void mouseReleased(MouseEvent e) {
                if(!dragSelected)
                {
                updateSize(e);
                System.out.println("mouseReleased");
                
                shape.setBounds(rectToDraw.union(previousRectDrawn));
                shape.setFillStatus(fillShape);
                if(shape instanceof RectangleShape)
                {
                    RectangleShape rect = new RectangleShape();
                    rect.setColor(shape.getColor());
                    rect.setBounds(shape.getBounds());
                    rect.setFillStatus(shape.getFillStatus());
                    shapevectorlist.add(rect);
                }
                else if(shape instanceof ArcShape)
                {
                    ArcShape arc1 = new ArcShape();
                    arc1.setColor(shape.getColor());
                    arc1.setBounds(shape.getBounds());
                    arc1.setFillStatus(shape.getFillStatus());
                    shapevectorlist.add(arc1);
                                  
                }
                else if(shape instanceof PolyShape)
                {
                    PolyShape poly1 = new PolyShape();
                    poly1.setColor(shape.getColor());
                    poly1.setBounds(shape.getBounds());
                    poly1.setFillStatus(shape.getFillStatus());
                    shapevectorlist.add(poly1);
                                  
                }
                else if(shape instanceof OvalShape)
                {
                    OvalShape oval = new OvalShape();
                    oval.setColor(shape.getColor());
                    oval.setBounds(shape.getBounds());
                    oval.setFillStatus(shape.getFillStatus());
                    shapevectorlist.add(oval);
                }
            
           }   
                
        }
             
            /* 
             * Update the size of the current rectangle
             * and call repaint.  Because currentRect
             * always has the same origin, translate it
             * if the width or height is negative.
             * 
             * For efficiency (though
             * that isn't an issue for this program),
             * specify the painting region using arguments
             * to the repaint() call.
             * 
             */
            void updateSize(MouseEvent e) {
                int x = e.getX();
                int y = e.getY();
                currentRect.setSize(x - currentRect.x,
                                    y - currentRect.y);
                updateDrawableRect(getWidth(), getHeight());
                Rectangle totalRepaint = rectToDraw.union(previousRectDrawn);
                repaint(totalRepaint.x, totalRepaint.y,
                        totalRepaint.width, totalRepaint.height);
            }

            
        
            
        }
        class ColorChooserListener extends AbstractAction
        {
            JPanel panel;
            public ColorChooserListener(JPanel panel,String name) {
                super(name); 
                this.panel = panel;
                  
            }
            
            @Override
            public void actionPerformed(ActionEvent e) {
                
                   shapecolor = JColorChooser.showDialog(panel, "Color Chooser", shapecolor);
                   colorButton.setBackground(shapecolor);
                   System.out.println("now the shapecolor is "+shapecolor.toString());           
                   shape.setColor(shapecolor);
               
            }
        }
        
       class MyWindowListener extends WindowAdapter
       {
            @Override
                 public void windowClosing(WindowEvent windowevent)
                 {
                      JOptionPane.showMessageDialog(null, "Hello! you have drawn \n"
                +(numberOfRectangles+numberOfEllipses)+" objects \n");
                 }
       }
       
            private void paintRemaingGrapics(Graphics g) {
                
                for(int i = 0 ; i<shapevectorlist.size();i++)
                {                 
                    drawShapeAtIndex(shapevectorlist.get(i),g);                  
                }
                
            }
        
        
        private void drawShapeAtIndex(Shape shape,Graphics g)
        {
                    g.setColor(shape.getColor());
                    if(shape instanceof RectangleShape)
                    {   
                            if(shape.getFillStatus())
                            {
                                  g.fillRect(shape.getBounds().x,shape.getBounds().y,
                                             shape.getBounds().width -1,shape.getBounds().height -1);
                            }
                            else
                            {
                                  g.drawRect(shape.getBounds().x,shape.getBounds().y,
                                             shape.getBounds().width -1,shape.getBounds().height -1);
                            }
                            
                    }
                    else if(shape instanceof ArcShape)
                    {
                        
                            Graphics2D g2 = (Graphics2D) g;
                            if(shape.getFillStatus())
                            {
                                  g2.fill(new Arc2D.Double(shape.getBounds().x,shape.getBounds().y,
                                            shape.getBounds().width -1,shape.getBounds().height -1,90,180,Arc2D.CHORD));
                                                                    
                            }
                            else
                            {
                                  g2.draw(new Arc2D.Double(shape.getBounds().x,shape.getBounds().y,
                                            shape.getBounds().width -1,shape.getBounds().height -1,90,180,Arc2D.CHORD));
                                  
                            } 
                    }
                    
                    else if(shape instanceof PolyShape)
                    {
                        Graphics2D g2 = (Graphics2D) g;
                      }
                    
                    
                    else if(shape instanceof OvalShape)
          
                    {
                          if(shape.getFillStatus())
                            {
                                  g.fillOval(shape.getBounds().x,shape.getBounds().y,
                                             shape.getBounds().width -1,shape.getBounds().height -1);
                            }
                            else
                            {
                                  g.drawOval(shape.getBounds().x,shape.getBounds().y,
                                             shape.getBounds().width -1,shape.getBounds().height -1);
                            }
                        
                    }
        }
        
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g); //paints the background and image
              
            //If currentRect exists, paint a box on top.
            g = changeGraphicsRendering(g);
            if (currentRect != null) {
                
                paintRemaingGrapics(g);
                if(shape.equals(rectangle))
                {
                    
                    shapecolor = shape.getColor();
                    g.setColor(shapecolor);  
                    if(fillShape)
                    {
                       g = changeFilling(g);
                    g.fillRect(rectToDraw.x, rectToDraw.y, 
                           rectToDraw.width - 1, rectToDraw.height - 1);
                    }
                    else
                        g.drawRect(rectToDraw.x, rectToDraw.y, 
                           rectToDraw.width - 1, rectToDraw.height - 1);
                    
                   
                }
                else
                {
                    shapecolor = shape.getColor();
                    g.setColor(shapecolor); 
                    if(fillShape)
                    {
                      g = changeFilling(g);
                      g.fillOval(rectToDraw.x, rectToDraw.y, 
                           rectToDraw.width - 1, rectToDraw.height - 1);
                    }
                    else
                    g.drawOval(rectToDraw.x, rectToDraw.y, 
                           rectToDraw.width - 1, rectToDraw.height - 1);
                    
                }
                
            }
        }
        
        
        class RadioButtonListener implements ItemListener{

            
            @Override
            public void itemStateChanged(ItemEvent e) {
            
             
                if(rectangleMenuItem.isSelected())
                {
                    shape = rectangle;
                }
                else
                {
                    shape = ellipse;
                }
                 if(redMenuItem.isSelected())
                 {
                    shapecolor = Color.red;
                    shape.setColor(shapecolor);
                    
                        
                 }
                 else if(greenMenuItem.isSelected())
                 {
                     shapecolor = Color.green;
                     shape.setColor(shapecolor);
                 }
                 else if(polkaMenuItem.isSelected())
                 {
                     shapecolor = Color.black;
                     shape.setColor(shapecolor);
                     
                 }
                 
                 else
                 {
                     shapecolor = Color.blue;
                     shape.setColor(shapecolor);
                 }
                
                repaint();
                
            }
            
        }
        //Creating all the individual listeners..........
        
        class DragButtonListener implements ActionListener
        {

            
            
            @Override
            public void actionPerformed(ActionEvent e) {
                
                if(dragSelected)
                {
                    dragSelected = false;
                }
                else
                {
                    dragSelected = true;
                }
                System.out.println("dragselected = "+dragSelected);
               
            }
            
        }
        
         class zoomChangeListener implements ChangeListener
        { 
             // @Override     
            public void stateChanged(ChangeEvent e) {
            JSpinner mySpinner = (JSpinner)(e.getSource());
            changeZoom(mySpinner);
            }   
          public void changeZoom(JSpinner zoomSelecter)
            {
             SelectionArea selArea = new SelectionArea(new MySelectionFrame()); 
             selArea.setScale(((Integer)zoomSelecter.getValue()).intValue()/100.0);
            } 
          
         
        }
        class RedButtonListener extends AbstractAction
        {

            public RedButtonListener(String name) {
                super(name);
            }

            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource().equals(redMenuItem))
                {
                    redRadioButton.setSelected(true);
                }
                else
                {
                    redMenuItem.setSelected(true);
                }
                shapecolor = Color.red;
                shape.setColor(shapecolor);
               
            }
            
        }
       
        class GreenButtonListener extends AbstractAction
        {

            public GreenButtonListener(String string) {
                super(string);
            }
                
            @Override
            public void actionPerformed(ActionEvent e) {
                 System.out.println("You have selected green button");
                if(e.getSource().equals(greenMenuItem))
                {
                    greenRadioButton.setSelected(true);
                }
                else
                {
                    greenMenuItem.setSelected(true);
                }
                shapecolor = Color.green;
                shape.setColor(shapecolor);
                 }
            
        }
        
        class BlueButtonListener extends AbstractAction
        {

            public BlueButtonListener(String name) {
                super(name);
            }

            @Override
            public void actionPerformed(ActionEvent e) {
               
                if(e.getSource().equals(blueMenuItem))
                {
                    blueRadioButton.setSelected(true);
                }
                else
                {
                    blueMenuItem.setSelected(true);
                }
                shapecolor = Color.blue;
                shape.setColor(shapecolor);
               }
            
        }
        
        class PolkaButtonListener extends AbstractAction
        {

            public PolkaButtonListener(String string) {
                super(string);
            }
                
            @Override
            public void actionPerformed(ActionEvent e) {
                  System.out.println("You have selected polka pattern button");
                if(e.getSource().equals(polkaMenuItem))
                {
                    polkaRadioButton.setSelected(true);
                }
                else
                {
                    polkaMenuItem.setSelected(true);
                }
                 }
            
        }
        class FillButtonListener extends AbstractAction
        {

            public FillButtonListener(String name) {
                super(name);
            }

            @Override
            public void actionPerformed(ActionEvent e) {
              
                if(e.getSource().equals(fillRadioButton))
                {
                    filledMenuItem.setSelected(true);
                }
                else
                {
                    fillRadioButton.setSelected(true);
                }
                fillShape = true;
            }
            
        }
        
        
       class UnfillButtonListener extends AbstractAction
       {

            public UnfillButtonListener(String name) {
                super(name);
            }        

            @Override
            public void actionPerformed(ActionEvent e) {
                
                if(e.getSource().equals(unFilledMenuItem))
                {
                    unfillRadioButton.setSelected(true);
                }
                else
                {
                    unFilledMenuItem.setSelected(true);
                }
                fillShape = false;
                
            }
           
       }
       
       class EllipseButtonListener extends AbstractAction
        {
            
            public EllipseButtonListener(String name, Icon icon) {
                super(name, icon);
            }

            @Override
            public void actionPerformed(ActionEvent e) {
               
                shape = ellipse;
                shape.setColor(shapecolor);
                if(e.getSource().equals(circleIconButton))
                {
                    ellipseMenuItem.setSelected(true);
                }
              
            }
            
        }
        
       class ArcButtonListener extends AbstractAction
        {
            
            public ArcButtonListener(String name, Icon icon) {
                super(name, icon);
            }

            @Override
            public void actionPerformed(ActionEvent e) {
               
                shape = arc;
                shape.setColor(shapecolor);
                if(e.getSource().equals(arcIconButton))
                {
                    arcMenuItem.setSelected(true);
                }
              
            }
            
        }
       
       class PolyButtonListener extends AbstractAction
        {
            
            public PolyButtonListener(String name, Icon icon) {
                super(name, icon);
            }

            @Override
            public void actionPerformed(ActionEvent e) {
               
                //shape = arc ;               
                shape.setColor(shapecolor);
                if(e.getSource().equals(polyIconButton))
                {
                    polyMenuItem.setSelected(true);
                }
              
            }
            
        }
       class  RectangleButtonListener extends AbstractAction
       {

            public RectangleButtonListener(String name, Icon icon) {
                super(name, icon);
            }

            @Override
            public void actionPerformed(ActionEvent e) {
               
                shape = rectangle;
                shape.setColor(shapecolor);
                if(e.getSource().equals(rectangleIconButton))
                {
                    rectangleMenuItem.setSelected(true);
                }
              
            }
           
       }
       
       
        class FillStatusListener implements ItemListener
        {

            @Override
            public void itemStateChanged(ItemEvent e) {
               
                if(filledMenuItem.isSelected())
                    fillShape = true;
                else
                    fillShape = false;
                repaint();
            }
            
        }
       
        
        /*public class TexturePaint extends JFrame
        {
    
            public TexturePaint(BufferedImage txtr, Rectangle2D rect2d) 
            {
                
            }
        }*/
        

        private void updateDrawableRect(int compWidth, int compHeight) {
            int x = currentRect.x;
            int y = currentRect.y;
            int width = currentRect.width;
            int height = currentRect.height;
    
            //Make the width and height positive, if necessary.
            if (width < 0) {
                width = 0 - width;
                x = x - width + 1; 
                if (x < 0) {
                    width += x; 
                    x = 0;
                }
            }
            if (height < 0) {
                height = 0 - height;
                y = y - height + 1; 
                if (y < 0) {
                    height += y; 
                    y = 0;
                }
            }
    
            //The rectangle shouldn't extend past the drawing area.
            if ((x + width) > compWidth) {
                width = compWidth - x;
            }
            if ((y + height) > compHeight) {
                height = compHeight - y;
            }
          
            //Update rectToDraw after saving old value.
            if (rectToDraw != null) {
                previousRectDrawn.setBounds(
                            rectToDraw.x, rectToDraw.y, 
                            rectToDraw.width, rectToDraw.height);
                rectToDraw.setBounds(x, y, width, height);
            } else {
                rectToDraw = new Rectangle(x, y, width, height);
            }
            
            label1.setText("Shape goes from ("
                      + x + ", " + y + ") to ("
                      + (x + width - 1) + ", "
                      + (y + height - 1) + ")."); 
            
            
        }


    } 
     
}
