package com.activity3b;

import java.util.*;

public interface ITempSensor
{
   final int MINREADING = 23315;
   final int MAXREADING = 32315;
   final int DEFAULT = 29315;
   
   //public int reading();
   
   public double getCelsius();
   
   public double getKelvin();
   
   public double getFahrenheit();
}