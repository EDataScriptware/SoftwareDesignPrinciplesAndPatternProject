/*
 * Listener class for buttons that change the thermometer in the current
 * mode by amount degrees.
 */

package controller ;

import java.awt.event.ActionEvent ;
import java.awt.event.ActionListener ;

import model.Thermometer ;

public class TemperatureChangeListener implements ActionListener {
    private Thermometer therm ; // thermometer to change
    private double amount ;     // amount of change

    public TemperatureChangeListener(Thermometer therm, double amount) {
        this.therm = therm ;
        this.amount = amount ;
    }

    public void actionPerformed(ActionEvent e) {
        therm.setTemp( therm.getTemp() + amount ) ;
    }
}
