
package colorslidershapeview;

/**
 *
 * @author John
 */
import javax.swing.JFrame;
public class ColorSliderShapeView
{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        JFrame frameViewer = new MyColorChooser();
        frameViewer.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frameViewer.setVisible(true);
    }
    
}
