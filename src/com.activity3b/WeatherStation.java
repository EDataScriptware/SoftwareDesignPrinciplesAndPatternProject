/*
 * Initial Author
 *      Michael J. Lutz
 *
 * Other Contributers
 *
 * Acknowledgements
 */

/*
 * Class for a simple computer based weather station that reports the current
 * temperature (in Celsius) every second. The station is attached to a
 * sensor that reports the temperature as a 16-bit number (0 to 65535)
 * representing the Kelvin temperature to the nearest 1/100th of a degree.
 *
 * This class is implements Runnable so that it can be embedded in a Thread
 * which runs the periodic sensing.
 *
 * The class also extends Observable so that it can notify registered
 * objects whenever its state changes. Convenience functions are provided
 * to access the temperature in different schemes (Celsius, Kelvin, etc.)
 */
package com.activity3b; 
 
import java.util.Observable;

public class WeatherStation extends Observable implements Runnable 
{

    // Temperature sensor.
         // Kelvin to Celsius conversion.
   private final long PERIOD = 1000;
   private int currentReading ;
   private double currentPressure;
   ITempSensor sensors;
   IBarometer bars;

   /*
    * When a WeatherStation object is created, it in turn creates the sensor
    * object it will use.
    */
   public WeatherStation(IBarometer ibObj, ITempSensor kObj) {
      
      sensors = kObj;
      
      bars = ibObj;
      
   }
   /*
    * The "run" method called by the enclosing Thread object when started.
    * Repeatedly sleeps a second, acquires the current temperature from its
    * sensor, and notifies registered Observers of the change.
    */
   public void run() 
   {
      while( true ) 
      {
         try {
            Thread.sleep(PERIOD) ;
         } catch (Exception e) {}    // ignore exceptions
      
         /*
          * Get next reading and notify any Observers.
          */
         synchronized(this) 
         {
            bars.pressure();
         }
         
         setChanged();
         notifyObservers();
      }
   }
   
   public double showCelsius()
   {
      return sensors.getCelsius();
   }
   
   public double showKelvin()
   {
      return sensors.getKelvin();
   }
   
   public double showFahrenheit()
   {
      return sensors.getFahrenheit();
   }
   
   public double showInches()
   {
      return bars.getInches();
   }
   
   public double showMillibars()
   {
      return bars.getMillibars();
   }
}
