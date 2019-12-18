/*
 * When invoked, changes the temperature mode to the one
 * it is handed at construction.
 */

package controller ;

import java.awt.event.ActionEvent ;
import java.awt.event.ActionListener ;

import model.Thermometer ;

public class ModeChangeListener implements ActionListener {
    private Thermometer therm ;     // thermometer to change
    private Thermometer.Mode mode ; // mode to change to

    public ModeChangeListener(Thermometer therm, Thermometer.Mode mode) {
        this.therm = therm ;
        this.mode = mode ;
    }

    public void actionPerformed(ActionEvent e) {
        therm.setMode(mode) ;
    }
}
