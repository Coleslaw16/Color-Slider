
package colorslidershapeview;

/*
 * @author John Stone
 */
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.border.EtchedBorder;
import javax.swing.JTextField;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ButtonGroup;
import javax.swing.JRadioButton;
import javax.swing.JCheckBox;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class MyColorChooser extends JFrame
{
    private static final int FRAME_WIDTH = 800;
    private static final int FRAME_HEIGHT = 600;
    
    private colorPanel cPanel;
    private JPanel SliderPanel;
    private JSlider redSlider;
    private JSlider blueSlider;
    private JSlider greenSlider;
    private JTextField redText;
    private JTextField greenText;
    private JTextField blueText;
    private JRadioButton circleButton;
    private JRadioButton squareButton;
    private JRadioButton lineButton;
    private JCheckBox filled;
    private ActionListener listener;
    
    public MyColorChooser()
    {
         listener = new buttonListener();
        cPanel = new colorPanel();
        SliderPanel = new JPanel();
        
        JMenuBar menu = new JMenuBar();
        setJMenuBar(menu);
        JMenu file = new JMenu("File");
        file.add(createExit("Close"));
        menu.add(file);
        
        JPanel shapeType = createButtons();
        
        SliderPanel.setBorder(new EtchedBorder());
        
        setSize(FRAME_WIDTH, FRAME_HEIGHT);
        SliderPanel.setLayout(new GridLayout(3,3));
        
        add(SliderPanel, BorderLayout.SOUTH);
        add(cPanel, BorderLayout.CENTER);
        add(shapeType, BorderLayout.NORTH);
       
        int FIELDWIDTH = 7;
        redText = new JTextField(FIELDWIDTH);
        redText.setText("" + 0);
        redText.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                int value = Integer.parseInt(redText.getText());
                redSlider.setValue(value);
                cPanel.setRed(value);
            }
        });
        
        greenText = new JTextField(FIELDWIDTH);
        greenText.setText("" + 0);
        greenText.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                int value = Integer.parseInt(greenText.getText());
                greenSlider.setValue(value);
                cPanel.setGreen(value);
            }
        });
        blueText = new JTextField(FIELDWIDTH);
        blueText.setText("" + 0);
        blueText.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                int value = Integer.parseInt(blueText.getText());
                blueSlider.setValue(value);
                cPanel.setBlue(value);
            }
        });
        createSliderPanel();
    }
    
    class buttonListener implements ActionListener
    {
        public void actionPerformed(ActionEvent event)
        {
            if(event.getSource() == circleButton) {
                cPanel.setShape(1);
                System.out.print("it works");
            }
            else if(event.getSource() == squareButton)
                cPanel.setShape(2);
            else if(event.getSource() == lineButton)
                cPanel.setShape(3);
            if(filled.isSelected())
                cPanel.isFilled(true);
            else
                cPanel.isFilled(false);
        }
    }
    
    class SliderListener implements ChangeListener
    {
        public void stateChanged(ChangeEvent event)
        {
            if(event.getSource() == redSlider)
            {
                redText.setText("" + redSlider.getValue());
                cPanel.setRed(redSlider.getValue());
            }
            if(event.getSource() == greenSlider)
            {
                greenText.setText("" + greenSlider.getValue());
                cPanel.setGreen(greenSlider.getValue());
            }
            if(event.getSource() == blueSlider)
            {
                blueText.setText("" + blueSlider.getValue());
                cPanel.setBlue(blueSlider.getValue());
            }
        }
    }
    
    public void createSliderPanel()
    {
        SliderListener listener = new SliderListener();
        
        redSlider = new JSlider(0, 255, 0);
        redSlider.addChangeListener(listener);
        SliderPanel.add(new JLabel("Red"));
        SliderPanel.add(redSlider);
        SliderPanel.add(redText);
        
        greenSlider = new JSlider(0, 255, 0);
        greenSlider.addChangeListener(listener);
        SliderPanel.add(new JLabel("Green"));
        SliderPanel.add(greenSlider);
        SliderPanel.add(greenText);
        
        blueSlider = new JSlider(0, 255, 0);
        blueSlider.addChangeListener(listener);
        SliderPanel.add(new JLabel("Blue"));
        SliderPanel.add(blueSlider);
        SliderPanel.add(blueText);
        
        putTicks(redSlider);
        putTicks(greenSlider);
        putTicks(blueSlider);
    }
    
    public JPanel createButtons()
    {
        circleButton = new JRadioButton("Oval");
        circleButton.addActionListener(listener);
        
        squareButton = new JRadioButton("Square");
        squareButton.addActionListener(listener);
        squareButton.setSelected(true);
        
        lineButton = new JRadioButton("Line");
        lineButton.addActionListener(listener);
        
        ButtonGroup group = new ButtonGroup();
        group.add(circleButton);
        group.add(squareButton);
        group.add(lineButton);
        
        filled = new JCheckBox("Filled");
        filled.addActionListener(listener);
        
        JPanel panel = new JPanel();
        panel.add(circleButton);
        panel.add(squareButton);
        panel.add(lineButton);
        panel.add(filled);
        return panel;
    }
    
    //The tick marks are just for cool effect
    public void putTicks(JSlider slider)
    {
        slider.setMajorTickSpacing(100);
        slider.setMinorTickSpacing(50);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
    }
    
    public JMenuItem createExit(final String name)
    {
        class FaceItemListener implements ActionListener
        {
            public void actionPerformed(ActionEvent event)
            {
                System.exit(0);
            }
        }
        JMenuItem closer = new JMenuItem(name);
        ActionListener listener = new FaceItemListener();
        closer.addActionListener(listener);
        return closer;
    }
    
}
