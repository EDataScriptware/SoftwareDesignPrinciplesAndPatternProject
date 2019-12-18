/*
 * The panel that holds buttons to increment or decrement the
 * temperature by 1 or 5 degrees in the current mode.
 */
package view ;

import java.awt.GridLayout ;

import javax.swing.JButton ;
import javax.swing.JPanel ;

import controller.TemperatureChangeListener ;

import model.Thermometer ;

public class TemperatureChangePanel extends JPanel {
    public TemperatureChangePanel(Thermometer therm) {
        /*
         * The two by two panel populated with temperature
         * change buttons.
         */
        setLayout( new GridLayout(2,2) ) ;

        this.add( changeButton("+1", therm) ) ;
        this.add( changeButton("+5", therm) ) ;
        this.add( changeButton("-1", therm) ) ;
        this.add( changeButton("-5", therm) ) ;
    }
    
    /*
     * Create a button that, when selected, changes the temperature
     * in the current mode by the given amount.
     */
    private static JButton changeButton(String label,
                                        Thermometer therm) {
        JButton button = new JButton(label) ;
        double amount = Double.valueOf(label) ;
        button.addActionListener( new TemperatureChangeListener(therm, amount) ) ;
        return button ;
    }
}
