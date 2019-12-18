package com.activity3b;

import java.util.*;
   
public class KelvinTempSensorAdapter implements ITempSensor
{
   private KelvinTempSensor kts = new KelvinTempSensor();
   
   public KelvinTempSensorAdapter(KelvinTempSensor _kts)
   {
      this.kts = _kts;
   }
   final int KTOC = -27315; 
   
   public synchronized double getCelsius() {
      return (kts.reading() + KTOC) / 100.0 ;
   }
   /*
    * Return the current reading in degrees Kelvin as a
    * double precision number.
    */
   public synchronized double getKelvin() {
      return kts.reading() / 100.0 ;
   } 
    /*
     * Return the current reading in degrees Fahrenheit as 
     * double precision number.
     */
   public synchronized double getFahrenheit(){
      return getCelsius() + 32.0D;
   }
   
}