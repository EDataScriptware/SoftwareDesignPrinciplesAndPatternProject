/*
 * VIEW panel to display the temperature numerically.
 */
package view ;

import java.awt.GridLayout ;

import java.text.DecimalFormat ;

import java.util.Observer ;
import java.util.Observable ;

import javax.swing.JPanel ;
import javax.swing.JLabel ;

import model.Thermometer ;

public class TextViewPanel extends JPanel implements Observer {
    /*
     * The label holding the current temperature, the thermometer,
     * and the formatter used to create a temperature string.
     */
    private JLabel temperatureDisplay ;
    private Thermometer therm ;
    private DecimalFormat formatter = new DecimalFormat("##0.00  ") ;

    public TextViewPanel(Thermometer therm) {
        this.therm = therm ;
        this.setLayout(new GridLayout(2,0)) ;

        JLabel label ;

        /*
         * Create the constant Temperature label and place it in
         * the first row.
         */
        label = new JLabel("  Temperature  ") ;
        label.setHorizontalAlignment(JLabel.CENTER) ;
        label.setVerticalAlignment(JLabel.BOTTOM) ;
        this.add(label) ;

        /*
         * Create the label to hold the temperature and place it
         * in the second row. We remember this for later use.
         */
        label = new JLabel() ;
        label.setHorizontalAlignment(JLabel.RIGHT) ;
        label.setVerticalAlignment(JLabel.TOP) ;
        this.add(label) ;

        temperatureDisplay = label ;

        /*
         * Observe the thermomemter.
         * Simulate an update to initialize the text label.
         */
        therm.addObserver(this) ;
        this.update(therm, Thermometer.ChangeType.MODE) ;
    }

    /*
     * Update received. Throw away spurious ones (if somehow we
     * are attached to something other than our thermometer).
     * Convert the current temperature in whatever base to a
     * string and change the label.
     */
    public void update(Observable obs, Object o) {
        /*
         * If received from other than the thermometer we're
         * observing, just return.
         */
        if( obs != therm ) {
            return ;
        }

        /*
         * Format and display the temperature.
         * We have to do this for either a temperature OR a mode
         * change.
         */
        String display = formatter.format( therm.getTemp() ) ;
        temperatureDisplay.setText(display) ;
    }
}
