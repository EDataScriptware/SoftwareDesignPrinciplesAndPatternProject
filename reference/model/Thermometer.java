/*
 * Simple MODEL class that tracks temperature.
 *
 * Clients can request either Fahrenheit or Celsius mode and from that point
 * on the temperature is reported in terms of the mode until the mode is
 * changed again.
 *
 * For clients who want a "stable" temperature, they can always
 * request it in a specific form (asCelsius or asFahrenheit).
 *
 * This is observable so that those who are interested can be notified
 * whenever anything changes.
 */
package model ;

import java.util.Observable ;

public class Thermometer extends Observable {
    /*
     * The two current modes. We could add more (e.g., Kelvin).
     * Two change types (passed as the optional second parameter in
     * notifyObservers and thence to the Observer's update().
     */
    public static enum Mode { FAHRENHEIT, CELSIUS } ;
    public static enum ChangeType { TEMPERATURE, MODE } ;

    /*
     * The initial mode is Celsius.
     * The initial temperature (in Celsius) is 0.0. Note that
     * internally we use celsius, though we could use Fahrenheit
     * without a change to the interface.
     */
    private Mode mode = Mode.CELSIUS ;
    private double celsius = 0.0 ;

    /*
     * Create a new Thermometer.
     */
    public Thermometer() {
        super() ;
    }

    /*
     * Get the temperature in Celsius.
     */
    public double asCelsius() {
        return celsius ;
    }

    /*
     * Get the temperature in Fahrenheit.
     */
    public double asFahrenheit() {
        return celsius * 9.0 / 5.0 + 32.0 ;
    }

    /*
     * Set the thermometer mode - and notify
     * observers that the mode changed..
     */
    public void setMode(Mode mode) {
        this.mode = mode ;
        setChanged() ;
        notifyObservers(ChangeType.MODE) ;
    }

    /*
     * Get the current mode.
     */
    public Mode getMode() {
        return mode ;
    }

    /*
     * Get the temperature in terms of the current mode.
     */
    public double getTemp() {
        return (mode == Mode.CELSIUS) ?
            asCelsius() :
            asFahrenheit() ;
    }

    /*
     * Set the thermometer's internal celsius temperature.
     * Note that the provided 'temp' is in the
     * current mode, either F or C.
     * Notify observers of the temperature change.
     */
    public void setTemp(double temp) {
        if( mode == Mode.CELSIUS ) {
           celsius = temp ;
        } else {
          celsius = fromFahrenheit(temp) ;
        }
        setChanged() ;
        notifyObservers(ChangeType.TEMPERATURE) ;
    }

    /*
     * Private helper to convert from Fahrenheit to
     * Celsius.
     */
    private double fromFahrenheit(double temp) {
        return (temp - 32.0) * 5.0 / 9.0 ;
    }
}
