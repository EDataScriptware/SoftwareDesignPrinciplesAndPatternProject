/*
 * Specialized VIEW canvas that observes the thermometer and
 * displays a bar graph of the last few observations.
 */
package view ;

import java.awt.Canvas ;
import java.awt.Color ;
import java.awt.Dimension ;
import java.awt.Graphics ;
import java.awt.Rectangle ;

import java.util.Observer ;
import java.util.Observable ;

import model.Thermometer ;

public class BarGraphCanvas extends Canvas implements Observer {
    /*
     * The thermomenter we monitor and the last few samples.
     */
    private Thermometer therm ;
    private final int samples[] = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 } ;

    public BarGraphCanvas(Thermometer therm) {
        /*
         * Set preferred and minimum sizes.
         */
        this.setPreferredSize( new Dimension(samples.length * 10, 300) );
        this.setMinimumSize( new Dimension(samples.length * 2, 100) );

        /*
         * Remember the thermometer.
         * Observer the thermometer.
         * Simulate an update to initialize the graph.
         */
        this.therm = therm ;
        therm.addObserver(this) ;
        this.update(therm, Thermometer.ChangeType.TEMPERATURE) ;
    }

    /*
     * Update received.
     * Check that this update is one we are interested in.
     * Convert the current temperature in whatever base to a
     * string and change the label.
     */
    public void update(Observable obs, Object o) {
        /*
         * Return on spurious update from unknown observable.
         */
        if( obs != therm ) {
            return ;
        }

        /*
         * Return on anything but temperature changes.
         * This is because our graph is always in celsius.
         */
        Thermometer.ChangeType ct = (Thermometer.ChangeType) o ;
        if (ct != Thermometer.ChangeType.TEMPERATURE) {
            return ;
        }

        /*
         * Get the temp (celsius) - truncate to the 0 .. 100 range.
         */
        int celsius = (int) Math.min(100.0, Math.max(0.0, therm.asCelsius())) ;

        /*
         * Shift the samples down one position, add the current
         * observation to the end, and repaint the canvas.
         */
        for( int i = 1 ; i < samples.length ; i++ ) {
            samples[i-1] = samples[i] ;
        }
        samples[ samples.length - 1 ] = celsius ;

        this.repaint() ;
    }

    /*
     * Paint the canvas with a bar graph of the last few samples.
     * This is called as an indirect consequence of "repaint"
     * which it turn is called in "update".
     */
    public void paint(Graphics g) {
        /*
         * Determine the height and width of the canvas.
         */
        Rectangle bounds = g.getClipBounds() ;
        int w = (int) bounds.getWidth() ;
        int h = (int) bounds.getHeight() ;

        /*
         * Determine the width of each bar in the sample.
         */
        int barWidth = w / samples.length ;

        g.setColor(Color.DARK_GRAY) ;
        g.setPaintMode() ;

        /*
         * Draw the bar graph, translating the y coordinate so that
         * the bars grow "up".
         */
        for( int i = 0 ; i < samples.length ; i++ ) {
            int height = h * samples[i] / 100 ;
            g.fillRect(i*barWidth, h-height, barWidth, height) ;
        }
    }
}
