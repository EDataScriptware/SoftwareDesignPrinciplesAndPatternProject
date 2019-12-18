/*
 * The main class - simply creates the thermometer and the GUI frame, populates
 * the frame, and starts the application by displaying the frame.
 */

import java.awt.BorderLayout ;

import javax.swing.JFrame ;

import model.Thermometer ;

import view.BarGraphCanvas ;
import view.ModeChangePanel ;
import view.TemperatureChangePanel ;
import view.TextViewPanel ;

public class Temperature {
  public static void main(String[] args) {
    Thermometer therm ;     // The model
    JFrame frame ;          // Holds GUI elements

    /*
     * Create the thermometer and the GUI frame.
     */
    therm = new Thermometer() ;
    frame = new JFrame("Temperature") ;

    /*
     * Add panels (and from them GUI elements)
     *
     */
    frame.add( new ModeChangePanel(therm), BorderLayout.LINE_START ) ;
    frame.add( new TemperatureChangePanel(therm), BorderLayout.CENTER ) ;
    frame.add( new TextViewPanel(therm), BorderLayout.LINE_END ) ;
    frame.add( new BarGraphCanvas(therm), BorderLayout.PAGE_START ) ;

    /*
     * Set up the frame and make it visible.
     */
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE) ;
    frame.pack() ;
    frame.setVisible(true) ;
  }
}
