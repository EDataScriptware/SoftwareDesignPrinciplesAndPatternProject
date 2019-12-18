/*
 * The panel that holds the radio buttons to change the mode
 * in which temperature is reported.
 */
package view ;

import java.awt.GridLayout ;

import javax.swing.ButtonGroup ;
import javax.swing.JPanel ;
import javax.swing.JRadioButton ;

import controller.ModeChangeListener ;

import model.Thermometer ;

public class ModeChangePanel extends JPanel {
    public ModeChangePanel(Thermometer therm) {
        /*
         * The one column panel for the mode buttons.
         */
        setLayout(new GridLayout(0,1)) ;

        /*
         * Group the radio buttons so only one is selected
         * at a time.
         */
        ButtonGroup group = new ButtonGroup() ;

        /*
         * Celsius mode button - initial selection.
         */
        JRadioButton button ;

        button = modeButton("Celsius", therm, Thermometer.Mode.CELSIUS) ;
        button.setSelected(true) ;
        group.add(button) ;
        this.add(button) ;

        /*
         * Fahrenheit mode button
         */
        button = modeButton("Fahrenheit", therm, Thermometer.Mode.FAHRENHEIT) ;
        group.add(button) ;
        this.add(button) ;
    }

    /*
     * Create a Radio Button with the given label and attach a listener to it
     * that has a reference to the thermometer and the mode to which it should
     * be set.
     */
    private JRadioButton modeButton(String label,
                                    Thermometer therm,
                                    Thermometer.Mode mode) {
        JRadioButton button = new JRadioButton(label) ;
        button.addActionListener(new ModeChangeListener(therm, mode)) ;
        return button ;
    }
}
