Temperature Change and Display Application Using MVC
	* Radio buttons to switch between Celsius and Fahrenheit modes.
	* Buttons to increment or decrement the temperature by 1 or 5
	  degrees in the current mode.
	* Bar graph on top showing temperature over the past few
	  changes (Celsius 0 to 100).

Temperature.java and three directories (model, view, controller) holding
the respective classes.

To try it out
	javac Temperature.java
	java Temperature


MORE DETAILS

The doc directory
=================
Contains design documentation in Word and PDF formats.

The "default" package
=====================
Temperature.java:
	* Holds the main program.
	* Creates the thermometer and the GUI.
	* Starts the application by displaying the GUI.

The "model" package
===================
Thermometer.java:
	* Records the temperature (internally in celsius).
	* Records the current mode (F or C).
	* Provides method to change the temperature in the current mode.
	* Provides method to change the mode.
	* As observable, broadcasts updates when temp. or mode changes.
		+ Second argument is an *enum* specifying the type of
		  change: ChangeType.TEMPERATURE and ChangeType.MODE.

The "view" package
==================
ModeChangePanel.java:
	* Panel holding the mode change (radio) buttons.
	* The buttons themselves are created in the ModeChangePanel
          constructor and attached to their ModeChangeListener objects.

TemperatureChangePanel.java:
	* Handles temperature change buttons analogously to ModeChangePanel.
	* Buttons are attached to their TemperatureChangeListener objects.

TextViewPanel.java
	* Sets up the TextView (with its label to hold the temperature).
	* An Observer, so changes the temperature display on update.

BarGraphCanvas.java:
	* An Observer / Canvas to display the bar chart.
	* Ignores mode change updates.

The "controller" package
========================
ModeChangeListener.java:
	* Listener for mode changes
	* Parameterized by the mode change it represents.

TemperatureChangeListener.java
	* Listener for temperature changes.
	* Parameterized by the temperature change it represents.
