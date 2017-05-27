
package colorslidershapeview;

/**
 *
 * @author John
 */
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.event.MouseInputAdapter;
import javax.swing.event.MouseInputListener;
public class colorPanel extends JPanel
{
    private int red = 0;
    private int green = 0;
    private int blue = 0;
    private int side;
    private boolean fill = false;
    private int shapeNum = 2;
    private double x1;
    private double y1;
    private double x2;
    private double y2;
    
    public colorPanel()
    {
        mouseAdapter handler = new mouseAdapter();
        addMouseListener(handler);
        addMouseMotionListener(handler);
    }
    
    public class mouseAdapter extends MouseInputAdapter
    {
        public void mousePressed(MouseEvent e)
        {
            x1 = e.getX();
            y1 = e.getY();
            x2 = x1;
            y2 = y1;
        }
        
        public void mouseDragged(MouseEvent e)
        {
            setX(e.getX());
            setY(e.getY());
            repaint();
        }
    }
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        g.setColor(new Color(red, green, blue));
        setBackground(Color.WHITE);
        int x = (int) (Math.min(x1,x2));
        int y = (int)(Math.min(y1,y2));
        int width = (int) Math.abs(x1-x2);
        int height = (int) Math.abs(y1-y2);
        
        if(shapeNum == 1 && fill == false)
            g.drawOval(x, y, width, height);
        else if(shapeNum == 1 && fill == true)
            g.fillOval(x, y, width, height);
        else if(shapeNum == 2 && fill == false)
            g.drawRect(x, y, width, height);
        else if(shapeNum == 2 && fill == true)
            g.fillRect(x, y, width, height);
        else if(shapeNum == 3)
            g.drawLine((int)x1, (int) y1, (int) x2, (int) y2);
        
        repaint(); 
    }

    public void setRed(int red)
    {
        this.red = red;
        repaint();
    }
    
    public void setGreen(int green)
    {
        this.green = green;
        repaint();
    }
    
    public void setBlue(int blue)
    {
        this.blue = blue;
        repaint();
    }
    
    public void setShape(int numofShape)
    {
        shapeNum = numofShape;
        repaint();
    }
    
    public void isFilled(boolean f)
    {
        if(f)
            fill = true;
        else
            fill = false;
        repaint();
    }
    
    public void setX(double num)
    {
        if(num < 0)
            num = 0;
        if (num > getWidth())
            num = getWidth()-1;
        x2 = num;
    }
    
    public void setY(double num)
    {
         if(num < 0)
            num = 0;
        if (num > getHeight())
            num = getHeight()-1;
        y2 = num;       
    }
}
