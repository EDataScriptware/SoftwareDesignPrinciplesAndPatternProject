//Usually you will require both swing and awt packages
// even if you are working with just swings.
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.lang.reflect.Array;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.text.Document;


class GUI implements ActionListener
{
   Wellness wellness = new Wellness();
   JTable dataTable;

   String title;
   Food food;
   static JPanel recipePanel, inputPanel, addFoodPanel, recInputPanel, editPanel;
   static JFrame logFrame, foodFrame, exerciseFrame, caloriesFrame, recipeFrame, addFoodFrame, editFrame, graphFrame;
   static JTextField tfYear, tfMonth, tfDays, weightField, calField;
   static JTextField tfExerciseValue, tfMinutes;
   static JTextField tfFoodName, tfCalories, tfFat, tfCarb, tfPro, tfDate, tfServe, tfTitle;
   static JLabel jlCalcIntake;
   static JComboBox comboboxChoices, comboboxFoodList, comboboxLogDateList, comboboxWorC, basicCombo, titleCombo;
   static JTextField tfValue, tfFoodDailyLogValue;
   static JButton enterLog, enterFood, enterExercise, deleteLog, showGraph, changeCalories, enterRecipe, enterAddFood, enterEditFood;  
   static JLabel calLabel;


   public static void main(String args[]) {
      GUI guii = new GUI();
      guii.createWellnessFrame();
   }

   public void createWellnessFrame(){
      try {
         //Creating the Frame
         JFrame window = new JFrame("Food App");
         window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         window.setSize(700, 300);

         // Makes the frame look more MODERN based on their operating system (Windows 10 gets Windows 10 style button)
         try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
         } catch (Exception e) {
            // if it can't look modern, then that's okay
            e.printStackTrace();
         }

         //Creating the MenuBar and adding components
         JMenuBar menubar = new JMenuBar();
         JMenu file = new JMenu("File");
         menubar.add(file);

         // Creating Menu Items under File
         JMenuItem save = new JMenuItem("Save All");
         JMenuItem help = new JMenuItem("Help");

         file.add(save);
         file.add(help);

         JPanel southPanel = new JPanel(new GridLayout(2,1));

         JPanel panelSelectedDate = new JPanel();
         JLabel labelDate = new JLabel("Selected Date:");
         comboboxLogDateList = new JComboBox();
         comboboxLogDateList.addActionListener(this);
         comboboxLogDateList.setActionCommand("selectedDateForDataTable");
         populateSelectedDateComboBox();

         panelSelectedDate.add(labelDate);
         panelSelectedDate.add(comboboxLogDateList);
         panelSelectedDate.add(jlCalcIntake = new JLabel("My Intake Calorie: "));
         southPanel.add(panelSelectedDate);

         //Creating the panel at bottom and adding components
         JPanel panelButton = new JPanel(); // the panel is not visible in output
         enterLog = new JButton("Add Log");
         enterLog.addActionListener(this);
         enterLog.setActionCommand("OpenLogFrame");

         enterFood = new JButton("Add Food");
         enterFood.addActionListener(this);
         enterFood.setActionCommand("OpenFoodFrame");

         enterRecipe = new JButton("Add Recipe");
         enterRecipe.addActionListener(this);
         enterRecipe.setActionCommand("OpenRecipeFrame");
         enterAddFood = new JButton("Edit Foods");
         enterAddFood.addActionListener(this);
         enterAddFood.setActionCommand("OpenAddFood");
//         enterEditFood = new JButton("Edit Food");
//         enterEditFood.addActionListener(this);
//         enterEditFood.setActionCommand("OpenEditFood");

         enterExercise = new JButton("Add Exercise");
         enterExercise.addActionListener(this);
         enterExercise.setActionCommand("OpenExerciseFrame");

         deleteLog = new JButton("Delete Log");
         deleteLog.addActionListener(this);
         deleteLog.setActionCommand("deleteLog");
         changeCalories = new JButton("My Health");
         changeCalories.addActionListener(this);
         changeCalories.setActionCommand("OpenCaloriesFrame");

         deleteLog = new JButton("Show Graph");
         deleteLog.addActionListener(this);
         deleteLog.setActionCommand("ShowGraphFrame");

         panelButton.add(enterLog);
         panelButton.add(enterFood);
         panelButton.add(enterRecipe);
         panelButton.add(enterAddFood);
         panelButton.add(enterExercise);
         panelButton.add(changeCalories);
         panelButton.add(deleteLog);

         southPanel.add(panelButton);

         dataTable = new JTable();
         populateDataTable("");

         JScrollPane sp = new JScrollPane(dataTable);

         dataTable.setFillsViewportHeight(true);
         sp.setViewportView(dataTable);
         window.add(sp);

         window.add(dataTable.getTableHeader(), BorderLayout.CENTER);


         //Adding Components to the window.
         window.getContentPane().add(BorderLayout.NORTH, menubar);
         // window.getContentPane().add(BorderLayout.CENTER, ta);
         window.getContentPane().add(BorderLayout.NORTH, dataTable);
         window.getContentPane().add(BorderLayout.SOUTH, southPanel);


         window.setVisible(true);
      }
      catch (Exception e){
         e.printStackTrace();
      }
   }

   // When the Enter exercise button is clicked - invoke this method
   public void createExerciseFrame()
   {
      try{
         // Set up frame that allows you to enter log
         exerciseFrame = new JFrame("Enter Exercise");
         exerciseFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

         // Makes the frame look more MODERN based on their operating system (Windows 10 gets Windows 10 style button)
         try
         {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
         }
         catch (Exception e)
         {
            // if it can't look modern, then that's okay
            e.printStackTrace();
         }

         // Creates a panel and style on JFrame
         JPanel panel = new JPanel();
         panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
         panel.setOpaque(true);

         JLabel labelYear = new JLabel("Enter the year (YYYY):", SwingConstants.LEFT);

         JPanel panelYear = new JPanel();
         panelYear.setLayout(new FlowLayout());

         tfYear = new JTextField(4);

         panelYear.add(labelYear);
         panelYear.add(tfYear);
         panel.add(panelYear);

         JLabel labelMonth = new JLabel("Enter the month (MM):", SwingConstants.LEFT);

         JPanel panelMonth = new JPanel();
         panelMonth.setLayout(new FlowLayout());

         tfMonth = new JTextField(4);

         panelMonth.add(labelMonth);
         panelMonth.add(tfMonth);
         panel.add(panelMonth);



         JPanel panelDays = new JPanel();

         JLabel labelDays = new JLabel("Enter the day (DD):", SwingConstants.LEFT);

         JPanel inputDays = new JPanel();
         panelDays.setLayout(new FlowLayout());

         tfDays = new JTextField(4);

         panelDays.add(labelDays);
         panelDays.add(tfDays);
         panel.add(panelDays);

         JPanel panelValue = new JPanel();

         JLabel labelExerciseValue = new JLabel("Exercise:", SwingConstants.LEFT);

         tfExerciseValue = new JTextField(10);

         panelValue.add(labelExerciseValue);
         panelValue.add(tfExerciseValue);
         panel.add(panelValue);

         JPanel panelMinutes = new JPanel();
         panelMinutes.setLayout(new FlowLayout());
         JLabel labelMinutes = new JLabel("Minutes:");

         tfMinutes = new JTextField(4);

         panelMinutes.add(labelMinutes);
         panelMinutes.add(tfMinutes);
         panel.add(panelMinutes);

         Panel panelButtons = new Panel();
         panelButtons.setLayout(new FlowLayout());

         JButton buttonEnter = new JButton("Enter");
         buttonEnter.addActionListener(this);
         buttonEnter.setActionCommand("EnterExerciseFrame");
         JButton buttonCancel = new JButton("Cancel");
         buttonCancel.addActionListener(this);
         buttonCancel.setActionCommand("CancelExerciseFrame");

         panelButtons.add(buttonEnter);
         panelButtons.add(buttonCancel);
         panel.add(panelButtons);

         exerciseFrame.getContentPane().add(BorderLayout.CENTER, panel);

         exerciseFrame.pack();
         exerciseFrame.setLocationByPlatform(true);
         exerciseFrame.setVisible(true);
         exerciseFrame.setResizable(false);

         panelYear.requestFocus();
      }
      catch (Exception e){
         e.printStackTrace();
      }
   }

   public void createAddFoodFrame()
   {
      try
      {
         addFoodFrame = new JFrame("Enter AddFood");
         addFoodFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
         try
         {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");

            addFoodPanel = new JPanel();
            addFoodPanel.setLayout(new BoxLayout(addFoodPanel, BoxLayout.Y_AXIS));
            addFoodPanel.setOpaque(true);


            ArrayList<String> recipeTitles = new ArrayList<String>();

            for(var r : wellness.getFoodManager().showRecipe())
            {
               if(r instanceof Recipe)
               {
                  if(r.getLetter().equalsIgnoreCase("r"))
                  {
                     recipeTitles.add(((Recipe)r).getTitle());
                  }
               }
            }

            titleCombo = new JComboBox(recipeTitles.toArray());
            titleCombo.addActionListener(this);
            titleCombo.setActionCommand("TitleSelected");

            JPanel comboPanel = new JPanel();
            JPanel buttonPanel = new JPanel();
            comboPanel.add(titleCombo);

            addFoodPanel.add(comboPanel);
            addFoodPanel.add(new JLabel("Delete Food by blank food name & serve"));
            recInputPanel = new JPanel();
            addFoodPanel.add(recInputPanel);

            JButton buttonEnter = new JButton("Enter");
            buttonEnter.addActionListener(this);
            buttonEnter.setActionCommand("EnterEditFrame");

            JButton buttonDelete = new JButton("Delete");
            buttonDelete.addActionListener(this);
            buttonDelete.setActionCommand("DeleteEditFrame");

            JButton buttonCancel = new JButton("Cancel");
            buttonCancel.addActionListener(this);
            buttonCancel.setActionCommand("CancelEditFrame");

            buttonPanel.add(buttonEnter);
            buttonPanel.add(buttonDelete);
            buttonPanel.add(buttonCancel);

            addFoodPanel.add(buttonPanel);

            addFoodFrame.getContentPane().add(BorderLayout.CENTER, addFoodPanel);
            addFoodFrame.pack();
            addFoodFrame.setSize(500, 500);
            addFoodFrame.setLocationByPlatform(true);
            addFoodFrame.setVisible(true);
            addFoodFrame.setResizable(true);

         }
         catch(Exception ex)
         {
            ex.printStackTrace();
         }
      }
      catch(Exception ex)
      {
         ex.printStackTrace();
      }
   }

   public void createRecipeFrame()
   {
      try
      {
         recipeFrame = new JFrame("Enter Recipe");
         recipeFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

         try
         {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");

            recipePanel = new JPanel();

            recipePanel.setLayout(new BoxLayout(recipePanel, BoxLayout.Y_AXIS));
            recipePanel.setOpaque(true);

            JPanel panelCombo = new JPanel();
            JPanel buttonPanel = new JPanel();

            JLabel jHead = new JLabel("Add Title of Recipe");
            tfTitle = new JTextField(10);

            JPanel titlePanel = new JPanel();
            titlePanel.add(jHead);
            titlePanel.add(tfTitle);

            basicCombo = new JComboBox(wellness.getFoodManager().getBasicFood().toArray());
            basicCombo.addActionListener(this);
            basicCombo.setActionCommand("BasicSelected");

            JLabel jServe = new JLabel("Add Serving");

            tfServe = new JTextField(2);

            inputPanel = new JPanel();


            inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.Y_AXIS));
            panelCombo.add(basicCombo);


            JButton buttonEnter = new JButton("Enter");
            buttonEnter.addActionListener(this);
            buttonEnter.setActionCommand("EnterRecipeFrame");

            JButton buttonCancel = new JButton("Cancel");
            buttonCancel.addActionListener(this);
            buttonCancel.setActionCommand("CancelRecipeFrame");


            buttonPanel.add(buttonEnter);
            buttonPanel.add(buttonCancel);


            recipePanel.add(titlePanel);
            recipePanel.add(panelCombo);
            recipePanel.add(inputPanel);

            recipePanel.add(buttonPanel);
            recipeFrame.getContentPane().add(BorderLayout.CENTER, recipePanel);

            recipeFrame.pack();
            recipeFrame.setLocationByPlatform(true);
            recipeFrame.setVisible(true);
            recipeFrame.setResizable(true);
         }
         catch (Exception e)
         {
            e.printStackTrace();
         }
      }
      catch(Exception e)
      {
         e.printStackTrace();
      }
   }

   // When the Enter food button is clicked - invoke this method
   public void createFoodFrame()
   {
      try{
         // Set up frame that allows you to enter log
         foodFrame = new JFrame("Enter Food");
         foodFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

         // Makes the frame look more MODERN based on their operating system (Windows 10 gets Windows 10 style button)
         try
         {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
         }
         catch (Exception e)
         {
            // if it can't look modern, then that's okay
            e.printStackTrace();
         }

         // Creates a panel and style on JFrame
         JPanel panel = new JPanel();

         panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
         panel.setOpaque(true);

         JPanel panelFood = new JPanel();


         JLabel labelFoodName = new JLabel("Food Name:", SwingConstants.LEFT);

         panelFood.setLayout(new FlowLayout());

         tfFoodName = new JTextField(25);

         panelFood.add(labelFoodName);
         panelFood.add(tfFoodName);
         panel.add(panelFood);

         JPanel panelCalories = new JPanel();

         JLabel labelCalories = new JLabel("Calories:", SwingConstants.LEFT);

         panelCalories.setLayout(new FlowLayout());

         tfCalories = new JTextField(4);

         panelCalories.add(labelCalories);
         panelCalories.add(tfCalories);
         panel.add(panelCalories);



         JPanel panelFat = new JPanel();

         JLabel labelFat = new JLabel("Fat:", SwingConstants.LEFT);

         JPanel inputFat = new JPanel();
         panelFat.setLayout(new FlowLayout());

         tfFat = new JTextField(4);

         panelFat.add(labelFat);
         panelFat.add(tfFat);
         panel.add(panelFat);



         JPanel panelCarb = new JPanel();

         JLabel labelCarb = new JLabel("Carbohydrate:", SwingConstants.LEFT);

         JPanel inputCarb = new JPanel();
         panelCarb.setLayout(new FlowLayout());

         tfCarb = new JTextField(4);

         panelCarb.add(labelCarb);
         panelCarb.add(tfCarb);
         panel.add(panelCarb);



         JPanel panelPro = new JPanel();
         panelPro.setLayout(new FlowLayout());
         JLabel labelPro = new JLabel("Protein:");

         tfPro = new JTextField(4);

         panelPro.add(labelPro);
         panelPro.add(tfPro);
         panel.add(panelPro);

         Panel panelButtons = new Panel();
         panelButtons.setLayout(new FlowLayout());

         JButton buttonEnter = new JButton("Enter");
         buttonEnter.addActionListener(this);
         buttonEnter.setActionCommand("EnterFoodFrame");

         JButton buttonCancel = new JButton("Cancel");
         buttonCancel.addActionListener(this);
         buttonCancel.setActionCommand("CancelFoodFrame");

         panelButtons.add(buttonEnter);
         panelButtons.add(buttonCancel);
         panel.add(panelButtons);


         foodFrame.getContentPane().add(BorderLayout.CENTER, panel);

         foodFrame.pack();
         foodFrame.setLocationByPlatform(true);
         foodFrame.setVisible(true);
         foodFrame.setResizable(false);

         panelFood.requestFocus();
      }
      catch (Exception e){
         e.printStackTrace();
      }
   }

   public void createCaloriesFrame()
   {
      try
      {
         caloriesFrame = new JFrame("Change Calories Limit");
         caloriesFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

         try
         {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
         }
         catch (Exception e)
         {
            // if it can't look modern, then that's okay
            e.printStackTrace();
         }

         JPanel panel = new JPanel();
         panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
         panel.setOpaque(true);



         JLabel labelDate = new JLabel("Select Date (dd/MM/yyyy):", SwingConstants.LEFT);

         JPanel panelDate = new JPanel();
         panelDate.setLayout(new FlowLayout());

         tfDate = new JTextField(8);

         panelDate.add(labelDate);
         panelDate.add(tfDate);
         panel.add(panelDate);

         //WEIGHT PANEL
         JLabel weightLabel = new JLabel("Choose what you want to change:", SwingConstants.LEFT);

         JPanel panelWeight = new JPanel();
         panelWeight.setLayout(new FlowLayout());

         String[] choices = { "Weight", "Calorie" };
         comboboxWorC = new JComboBox(choices);
         comboboxWorC.setSelectedIndex(0);
         comboboxWorC.addActionListener(this);
         comboboxWorC.setActionCommand("comboboxWorC");

         weightField = new JTextField(8);

         panelWeight.add(weightLabel);
         //panelWeight.add(weightField);
         panelWeight.add(comboboxWorC);
         panel.add(panelWeight);


         //CALORIES PANEL
         calLabel = new JLabel("Enter Weight:", SwingConstants.LEFT);

         JPanel panelCal = new JPanel();
         panelWeight.setLayout(new FlowLayout());

         calField = new JTextField(8);

         panelCal.add(calLabel);
         panelCal.add(calField);
         panel.add(panelCal);

         //BUTTONS IN THIS FRAME
         Panel panelButtons = new Panel();
         panelButtons.setLayout(new FlowLayout());

         JButton buttonSave = new JButton("SAVE");
         buttonSave.addActionListener(this);
         buttonSave.setActionCommand("saveCaloriesFrame");

         JButton buttonCancel = new JButton("CANCEL");
         buttonCancel.addActionListener(this);
         buttonCancel.setActionCommand("closeCaloriesFrame");

         panelButtons.add(buttonSave);
         panelButtons.add(buttonCancel);
         panel.add(panelButtons);




         caloriesFrame.getContentPane().add(BorderLayout.CENTER, panel);
         caloriesFrame.pack();
         caloriesFrame.setLocationByPlatform(true);
         caloriesFrame.setVisible(true);
         caloriesFrame.setResizable(false);

      }
      catch(Exception e)
      {
         e.printStackTrace();
      }
   }

   // When the Enter log button is clicked - invoke this method
   public void createLogFrame()
   {
      try {
         // Set up frame that allows you to enter log
         logFrame = new JFrame("Enter Log");
         logFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
         // Makes the frame look more MODERN based on their operating system (Windows 10 gets Windows 10 style button)
         try
         {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
         }
         catch (Exception e)
         {
            // if it can't look modern, then that's okay
            e.printStackTrace();
         }

         // Creates a panel and style on JFrame
         JPanel panel = new JPanel();
         panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
         panel.setOpaque(true);

         JLabel labelYear = new JLabel("Enter the year (YYYY):", SwingConstants.LEFT);

         JPanel panelYear = new JPanel();
         panelYear.setLayout(new FlowLayout());

         tfYear = new JTextField(4);

         panelYear.add(labelYear);
         panelYear.add(tfYear);
         panel.add(panelYear);

         JLabel labelMonth = new JLabel("Enter the month (MM):", SwingConstants.LEFT);

         JPanel panelMonth = new JPanel();
         panelMonth.setLayout(new FlowLayout());

         tfMonth = new JTextField(4);

         panelMonth.add(labelMonth);
         panelMonth.add(tfMonth);
         panel.add(panelMonth);



         JPanel panelDays = new JPanel();

         JLabel labelDays = new JLabel("Enter the day (DD):", SwingConstants.LEFT);

         JPanel inputDays = new JPanel();
         panelDays.setLayout(new FlowLayout());

         tfDays = new JTextField(4);

         panelDays.add(labelDays);
         panelDays.add(tfDays);
         panel.add(panelDays);

         JPanel panelValue = new JPanel();


         String[] choices = { "Food", "Calorie", "Weight" };
         comboboxChoices = new JComboBox(choices);
         comboboxChoices.setSelectedIndex(0);
         comboboxChoices.addActionListener(this);
         comboboxChoices.setActionCommand("comboboxChoicesLogFrame");

         tfValue = new JTextField(10);

         // Combobox of Food List and set visible false.
         comboboxFoodList = new JComboBox();

         panelValue.add(comboboxChoices);
         panelValue.add(tfValue);
         panelValue.add(comboboxFoodList);
         panel.add(panelValue);

         JPanel panelFoodDailyLogValue = new JPanel();
         panelFoodDailyLogValue.setLayout(new FlowLayout());
         JLabel labelFoodDailyLogValue = new JLabel("Servings:");

         tfFoodDailyLogValue = new JTextField(4);

         panelFoodDailyLogValue.add(labelFoodDailyLogValue);
         panelFoodDailyLogValue.add(tfFoodDailyLogValue);
         panel.add(panelFoodDailyLogValue);

         Panel panelButtons = new Panel();
         panelButtons.setLayout(new FlowLayout());

         JButton buttonEnter = new JButton("Enter");
         buttonEnter.addActionListener(this);
         buttonEnter.setActionCommand("enterLogFrame");

         JButton buttonCancel = new JButton("Cancel");
         buttonCancel.addActionListener(this);
         buttonCancel.setActionCommand("cancelLogFrame");

         panelButtons.add(buttonEnter);
         panelButtons.add(buttonCancel);
         panel.add(panelButtons);

         logFrame.getContentPane().add(BorderLayout.CENTER, panel);

         logFrame.pack();
         logFrame.setLocationByPlatform(true);
         logFrame.setVisible(true);
         logFrame.setResizable(false);

         panelYear.requestFocus();
         comboboxFoodList.setVisible(false);
      }
      catch (Exception e){
         e.printStackTrace();
      }
   }

   public void populateSelectedDateComboBox(){
       comboboxLogDateList.setModel(new DefaultComboBoxModel(wellness.getLogManager().getDateList().toArray()));
   }

   public void populateDataTable(String _date){
      Object[][] data;
      int count = 0;
      if (!_date.equalsIgnoreCase("")){
         for (var c : wellness.getLogManager().GetDailyLog()){
            if ((c.getYear() + " " + c.getMonth() + " " + c.getDay()).equalsIgnoreCase(_date)){
               if (c.getType().equalsIgnoreCase("f") || c.getType().equalsIgnoreCase("e")){
                  count++;
               }
            }
         }
         data = new Object[count][6];
      } else {
         for (var c : wellness.getLogManager().GetDailyLog()){
            if (c.getType().equalsIgnoreCase("f") || c.getType().equalsIgnoreCase("e")){
               count++;
            }
         }
         data = new Object[count][6];
      }

      int i = 0;
      for (var s : wellness.getLogManager().GetDailyLog()) {
         int c = 0;
         if (!_date.equals("")){
            if ((s.getYear() + " " + s.getMonth() + " " + s.getDay()).equalsIgnoreCase(_date)){
               if (s.getType().equals("f") || s.getType().equals("e")) {
                  data[i][c++] = s.getYear();
                  data[i][c++] = s.getMonth();
                  data[i][c++] = s.getDay();
                  data[i][c++] = s.getType();
                  data[i][c++] = s.getFood();
                  data[i][c++] = s.getOunce();
                  i++;
               }
            }
         } else if (_date.equals("")){
            if (s.getType().equals("f") || s.getType().equals("e")) {
               data[i][c++] = s.getYear();
               data[i][c++] = s.getMonth();
               data[i][c++] = s.getDay();
               data[i][c++] = s.getType();
               data[i][c++] = s.getFood();
               data[i][c++] = s.getOunce();
               i++;
            }
         }
      }
      String[] columnNames = {"Year", "Month", "Day", "Type", "Name", "Value"};
      if (comboboxLogDateList.getSelectedIndex() > 0){
         jlCalcIntake.setText("My Intake Calorie: " + calculateForCalorieIntake((String)comboboxLogDateList.getSelectedItem()));
      } else{
         jlCalcIntake.setText("My Intake Calorie: ");
      }

      TableModel cModel = new DefaultTableModel(data, columnNames);
      dataTable.setModel(cModel);
      dataTable.revalidate();
   }

   public Float calculateForCalorieIntake(String _selectedDate){
      ArrayList<Food> selectedFoodData = new ArrayList<Food>();
      double totalMinutesOfExercise = 0;

      for (var c : wellness.getLogManager().GetDailyLog()){
         if ((c.getYear() + " " + c.getMonth() + " " + c.getDay()).equalsIgnoreCase(_selectedDate) && c.getType().equalsIgnoreCase("f")){
            selectedFoodData.add(wellness.getFoodManager().findFood(c.getFood()));
         } else if ((c.getYear() + " " + c.getMonth() + " " + c.getDay()).equalsIgnoreCase(_selectedDate) && c.getType().equalsIgnoreCase("e")){
            totalMinutesOfExercise += c.getMinutes();
         }
      }
      Float totalC = wellness.getLogManager().totalCalories(selectedFoodData);
      if (totalC == 0){
         return 0f;
      } else{
         return wellness.getExerciseManager().totalCalorieBurn(totalC, totalMinutesOfExercise);
      }
   }

   @Override
   public void actionPerformed(ActionEvent ae)
   {
   
      // Recipe Frame - open the frame
      if(ae.getActionCommand().equals("OpenRecipeFrame"))
      {
         createRecipeFrame();
      }

      // Calories Frame - Open the frame
      if (ae.getActionCommand().equalsIgnoreCase("OpenCaloriesFrame")){
         createCaloriesFrame();
         System.out.println("Open Calories Frame!");
      }

      // Wellness Frame - Display only logs by selected date
      if (ae.getActionCommand().equalsIgnoreCase("selectedDateForDataTable")){
         populateDataTable((String)comboboxLogDateList.getSelectedItem());
      }

      // Wellness Frame - Delete log from the selected row on table
      if (ae.getActionCommand().equalsIgnoreCase("deleteLog")){
         if (!dataTable.getSelectionModel().isSelectionEmpty()){
            int row = dataTable.getSelectedRow();
            ArrayList<String> sd = new ArrayList<String>();
            for (int col = 0; col < dataTable.getColumnCount(); col++){
               sd.add(dataTable.getModel().getValueAt(row, col).toString());
            }
            wellness.getLogManager().Remove(sd);
         } else {
            System.out.println("You need to select the row");
         }
         populateDataTable("");
         comboboxLogDateList.setSelectedIndex(0);
         wellness.getLogManager().writeLogData();
      }

      if(ae.getActionCommand().equalsIgnoreCase("OpenAddFood"))
      {
         createAddFoodFrame();
      }

      if(ae.getActionCommand().equalsIgnoreCase("BasicSelected"))
      {
         //tfName.add((String)basicCombo.getSelectedItem());
         JPanel jselected = new JPanel();
         jselected.add(new JLabel("Selected Food"));

         JTextField jText = new JTextField(((String)basicCombo.getSelectedItem()));
         jText.setEnabled(false);
         jselected.add(jText);
         JPanel jserve = new JPanel();
         jserve.add(new JLabel("Add Serving"));
         jserve.add(tfServe = new JTextField(10));
         inputPanel.add(jselected);
         inputPanel.add(jserve);
         recipePanel.revalidate();

      }
      // Log Frame - Display Food List when Serving logging is selected
      if (ae.getActionCommand().equalsIgnoreCase("comboboxChoicesLogFrame")){
         // Get the selected item
         if (((String)comboboxChoices.getSelectedItem()).equals("Food")){
            tfValue.setVisible(false);
            if (comboboxFoodList.getItemCount() == 0) {
               ArrayList<String> foodArray = new ArrayList<String>();
               /*for (Food f : wellness.getFoodManager().getFoodCollection()){
                  foodArray.add(f.get)
               }*/
               comboboxFoodList.setModel(new DefaultComboBoxModel(wellness.getFoodManager().getFoodList().toArray()));
            }
            comboboxFoodList.setVisible(true);
         } else if (((String)comboboxChoices.getSelectedItem()).equals("Weight")){
            tfValue.setVisible(true);
            comboboxFoodList.setVisible(false);
         } else if (((String)comboboxChoices.getSelectedItem()).equals("Calorie")){
            tfValue.setVisible(true);
            comboboxFoodList.setVisible(false);
         }
         logFrame.revalidate();
      }

      // Wellness Frame - enter button to create Food Frame
      if (ae.getActionCommand().equalsIgnoreCase("OpenLogFrame")){
         createLogFrame();
         System.out.println("Open Log Frame!");
      }

      // Wellness Frame - enter button to create Food Frame
      if (ae.getActionCommand().equalsIgnoreCase("OpenFoodFrame")){
         createFoodFrame();
         System.out.println("Open Food Frame!");
      }

      if(ae.getActionCommand().equalsIgnoreCase("EnterRecipeFrame"))
      {
         String letter = "r";
         String title = tfTitle.getText();
         ArrayList<String> list = new ArrayList<String>();

         Recipe recipe = new Recipe(letter, title);

         int i = 0;
         for(var r : inputPanel.getComponents())
         {
            if(r instanceof  JPanel)
            {
               JPanel jPanel = (JPanel)r;

               for(var k : jPanel.getComponents())
               {
                  if(k instanceof JTextField)
                  {
                     String getT = ((JTextField)k).getText();
                     list.add(getT);
                  }
               }
            }
         }

         for(int s = 0; s <list.size();s++)
         {
            recipe.Add(new Recipe(list.get(s), Float.parseFloat(list.get(++s))));
         }

         wellness.getFoodManager().AddFood(recipe);

         wellness.getFoodManager().writeRecipeData();
         recipeFrame.dispatchEvent(new WindowEvent(recipeFrame, WindowEvent.WINDOW_CLOSING));
      }

      if(ae.getActionCommand().equalsIgnoreCase("CancelEditFrame"))
      {
         addFoodFrame.dispatchEvent(new WindowEvent(addFoodFrame, WindowEvent.WINDOW_CLOSING));
      }

      if(ae.getActionCommand().equalsIgnoreCase("CancelRecipeFrame"))
      {
         recipeFrame.dispatchEvent(new WindowEvent(recipeFrame, WindowEvent.WINDOW_CLOSING));
      }

      // Wellness Frame - enter button to create Exercise Frame
      if (ae.getActionCommand().equalsIgnoreCase("OpenExerciseFrame")){
         createExerciseFrame();
         System.out.println("Open Exercise Frame!");
      }

      // Wellness Frame - enter button to see Graph Frame
      if (ae.getActionCommand().equalsIgnoreCase("ShowGraphFrame")){

         BarGraphFrame bgf = new BarGraphFrame(null, null, null);

         ArrayList<Food> data5 = wellness.getFoodManager().showRecipe();
         System.out.println(wellness.getLogManager().getAllNutriFact(data5));
         ArrayList<Float> result = wellness.getLogManager().getAllNutriFact(data5);

         bgf.createBarGraph(result.get(1), result.get(2), result.get(3));







         System.out.println("Show Graph Frame!");
      }

      // Exercise Frame - Enter Button to log new exercise
      if (ae.getActionCommand().equalsIgnoreCase("EnterExerciseFrame")){
         System.out.println("Enter!");
         String letter = "e";
         String year = tfYear.getText();
         String month = tfMonth.getText();
         String day = tfDays.getText();
         String valueExercise = tfExerciseValue.getText();
         String minutes = tfMinutes.getText();

         wellness.getLogManager().Add(new DailyLog( Integer.parseInt(year), Integer.parseInt(month), Integer.parseInt(day), letter, valueExercise, Double.parseDouble(minutes)));
         wellness.getLogManager().writeLogData();
         wellness.getExerciseManager().writeExerciseData();
         populateDataTable("");
         exerciseFrame.dispatchEvent(new WindowEvent(exerciseFrame, WindowEvent.WINDOW_CLOSING));
      }

      // Exercise Frame - Cancel Button to cancel the exercise frame
      if (ae.getActionCommand().equalsIgnoreCase("CancelExerciseFrame")){
         System.out.println("Cancel!");
         exerciseFrame.dispatchEvent(new WindowEvent(exerciseFrame, WindowEvent.WINDOW_CLOSING));
      }

      if(ae.getActionCommand().equalsIgnoreCase("DeleteEditFrame"))
      {
         //titleCombo.remove(titleCombo.getSelectedIndex());

         String title = ((String)titleCombo.getSelectedItem());

         Food removeRecipe = wellness.getFoodManager().findFood(title);

         int index = wellness.getFoodManager().showRecipe().indexOf(removeRecipe);

         wellness.getFoodManager().showRecipe().remove(index);

         wellness.getFoodManager().writeRecipeData();

         addFoodFrame.dispatchEvent(new WindowEvent(addFoodFrame, WindowEvent.WINDOW_CLOSING));
      }

      if(ae.getActionCommand().equalsIgnoreCase("TitleSelected"))
      {
         recInputPanel.removeAll();
         recInputPanel.revalidate();

         food = wellness.getFoodManager().findFood(((String)titleCombo.getSelectedItem()));

         Recipe rec = (Recipe)food;

         for(var f: rec.getRecipe())
         {

            JTextField jName = new JTextField(((Recipe)f).getName());

            System.out.println(((Recipe)f).getServing());
            Float serv = ((Recipe)f).getServing();
            String convertServe = String.valueOf(serv);
            JTextField jServe = new JTextField(convertServe, 10);
            recInputPanel.add(jName);
            recInputPanel.add(jServe);
         }

         addFoodFrame.revalidate();
      }


      // Food Frame - Enter Button to create new food
      if (ae.getActionCommand().equalsIgnoreCase("EnterFoodFrame")){
         System.out.println("Enter!");
         String letter = "f";
         String foodName = tfFoodName.getText();
         String calories = tfCalories.getText();
         String fat = tfFat.getText();
         String carb = tfCarb.getText();
         String protein = tfPro.getText();

         //System.out.println(foodName + " contains " + calories + " calories, " + fat + " fat, " + carb + " carbohydrate, and " + protein + " protein");
         wellness.getFoodManager().AddFood(new BasicFood("b",foodName,Float.parseFloat(calories),Float.parseFloat(fat),Float.parseFloat(carb),Float.parseFloat(protein)));

         foodFrame.dispatchEvent(new WindowEvent(foodFrame, WindowEvent.WINDOW_CLOSING));
         wellness.getFoodManager().writeRecipeData();
      }
      // Food Frame - Cancel Button to cancel food frame
      if (ae.getActionCommand().equalsIgnoreCase("CancelFoodFrame")){
         System.out.println("Cancel!");
         foodFrame.dispatchEvent(new WindowEvent(foodFrame, WindowEvent.WINDOW_CLOSING));
      }

      // Recipe Frame - enter to edit
      if(ae.getActionCommand().equalsIgnoreCase("EnterEditFrame"))
      {
         //recInputPanel
         title = ((String)titleCombo.getSelectedItem());
         String name = "";
         Recipe recs = new Recipe("r",title);
         ArrayList<String> list = new ArrayList<String>();
         //wellness.getFoodManager().swapFoodServing();

         int index = wellness.getFoodManager().showRecipe().indexOf(food);

         for(var r : recInputPanel.getComponents())
         {
            String jname = ((JTextField)r).getText();
            if(!jname.equalsIgnoreCase(""))
            {
               list.add(jname);
            }
         }

         for(int i = 0; i < list.size(); i++)
         {
            recs.Add(new Recipe(list.get(i), Float.parseFloat(list.get(++i))));
         }

         wellness.getFoodManager().showRecipe().set(index, recs);
         wellness.getFoodManager().writeRecipeData();

         addFoodFrame.dispatchEvent(new WindowEvent(addFoodFrame, WindowEvent.WINDOW_CLOSING));
      }

      // Log Frame - Enter Button to enter log frame
      if (ae.getActionCommand().equalsIgnoreCase("EnterLogFrame")){
         System.out.println("Enter!");
         String letter = "e";
         String f = "f";
         String c = "c";
         String w = "w";


         String year = tfYear.getText();
         String month = tfMonth.getText();
         String day = tfDays.getText();
         String valueType = (String)comboboxChoices.getSelectedItem();
         String food = (String)comboboxFoodList.getSelectedItem();
         String value = tfValue.getText();
         String servings = tfFoodDailyLogValue.getText();

         if (((String)comboboxChoices.getSelectedItem()).equals("Food"))
         {


            if(!year.isEmpty() && !month.isEmpty() && !day.isEmpty() && !servings.isEmpty())
            {
               wellness.getLogManager().Add(new DailyLog(Integer.parseInt(year), Integer.parseInt(month), Integer.parseInt(day), f, food, Float.parseFloat(servings)));
               wellness.getLogManager().writeLogData();
            }
            else
            {
               System.out.println("Everything needs to filled in!");
            }
         }
         else if (((String)comboboxChoices.getSelectedItem()).equals("Weight"))
         {
            if (!year.isEmpty() && !month.isEmpty() && !day.isEmpty() && !value.isEmpty())
            {
               wellness.getLogManager().Add(new DailyLog(Integer.parseInt(year), Integer.parseInt(month), Integer.parseInt(day), w, Float.parseFloat(value)));
               wellness.getLogManager().writeLogData();
            }
            else
            {
               System.out.println("Everything needs to filled in!");
            }
         }
         else if (((String)comboboxChoices.getSelectedItem()).equals("Calorie"))
         {
            if (!year.isEmpty() && !month.isEmpty() && !day.isEmpty() && !value.isEmpty())
            {
               wellness.getLogManager().Add(new DailyLog(Integer.parseInt(year), Integer.parseInt(month), Integer.parseInt(day), c, Float.parseFloat(value)));
               wellness.getLogManager().writeLogData();
            }
            else
            {
               System.out.println("Everything needs to filled in!");
            }
         }
         System.out.println(month +"/" + day+ "/" + year );
         System.out.println(valueType + ":" + value);
         System.out.println(servings);

         logFrame.dispatchEvent(new WindowEvent(logFrame, WindowEvent.WINDOW_CLOSING));
      }

      // Log Frame - Cancel Button to cancel log frame
      if (ae.getActionCommand().equalsIgnoreCase("CancelLogFrame")){
         System.out.println("Cancel!");
         logFrame.dispatchEvent(new WindowEvent(logFrame, WindowEvent.WINDOW_CLOSING));
      }

      // Calories Frame - Save
      if (ae.getActionCommand().equalsIgnoreCase("saveCaloriesFrame"))
      {
         System.out.println("SAVE!");

         SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
         Date input = null;
         Calendar cal = Calendar.getInstance();

         try
         {
            input = formatter.parse(tfDate.getText());
            cal.setTime(input);
         }
         catch(ParseException e)
         {
            System.out.println("ERROR PARSING");
         }

         ArrayList<DailyLog> collection = wellness.getLogManager().GetDailyLog();
         int month = cal.get(Calendar.MONTH) + 1;
         int day = cal.get(Calendar.DAY_OF_MONTH);
         int year = cal.get(Calendar.YEAR);

         Boolean matchFOUND = false;
         for(int i = 0; i < collection.size(); i++)
         {

            // if the date matched
            System.out.println("Input Year: " + year + "\nCollection Year: " + collection.get(i).getYear());
            System.out.println("Input Month: " + month + "\nCollection Month: " + collection.get(i).getMonth());
            System.out.println("Input Day: " + day + "\nCollection Day: " + collection.get(i).getDay() + "\n\n");
            // checks if weightField has a value
            if ((((String)comboboxWorC.getSelectedItem()).equals("Weight")))
            {
               //IF DATE EXISTS
               if (year == collection.get(i).getYear() && day == collection.get(i).getDay() && month == collection.get(i).getMonth() && collection.get(i).getType().equals("w"))
               {


                  System.out.println("EXECUTED weight");

                  Float weight = Float.parseFloat(calField.getText());
                  collection.get(i).setWeight(weight);
                  wellness.getLogManager().writeLogData();
                  matchFOUND = true;


               }

            }
            // checks if calField has a value
            else if ((((String)comboboxWorC.getSelectedItem()).equals("Calorie")))
            {
               if (year == collection.get(i).getYear() && day == collection.get(i).getDay() && month == collection.get(i).getMonth() && collection.get(i).getType().equals("c")) {


                  System.out.println("EXECUTED calories");

                  Float calories = Float.parseFloat(calField.getText());
                  collection.get(i).setCalories(calories);
                  wellness.getLogManager().writeLogData();
                  matchFOUND = true;


               }
            }
            else {
               System.out.println("You must only choose one to change, weight or calories");
            }

         }

         if(matchFOUND)
         {
            System.out.println("MATCH FOUND");
         }
         else
         {
            System.out.println("NO MATCH FOUND!");

            if ((((String)comboboxWorC.getSelectedItem()).equals("Weight")))
            {
               wellness.getLogManager().Add(new DailyLog(year, month, day, "w", Float.parseFloat(calField.getText())));
               wellness.getLogManager().writeLogData();
            }
            else if ((((String)comboboxWorC.getSelectedItem()).equals("Calorie")))
            {
               wellness.getLogManager().Add(new DailyLog(year, month, day, "c", Float.parseFloat(calField.getText())));
               wellness.getLogManager().writeLogData();
            }


         }
         populateDataTable("");
         caloriesFrame.dispatchEvent(new WindowEvent(caloriesFrame, WindowEvent.WINDOW_CLOSING));
      }

      // Calories Frame
      if (ae.getActionCommand().equalsIgnoreCase("closeCaloriesFrame"))
      {
         System.out.println("Closed!");
         caloriesFrame.dispatchEvent(new WindowEvent(caloriesFrame, WindowEvent.WINDOW_CLOSING));
      }

      if (ae.getActionCommand().equalsIgnoreCase("comboboxWorC"))
      {
         if (((String)comboboxWorC.getSelectedItem()).equals("Weight"))
         {
            calLabel.setText("Enter Weight: ");
         }
         else if (((String)comboboxWorC.getSelectedItem()).equals("Calorie"))
         {
            calLabel.setText("Enter Calories: ");
         }
      }
   }
}