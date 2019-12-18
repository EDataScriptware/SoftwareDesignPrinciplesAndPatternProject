//Usually you will require both swing and awt packages
// even if you are working with just swings.
import javax.swing.*;
import java.awt.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.text.Document;

public class FoodFrame
{

   // When the Enter log button is clicked - invoke this method
   public static void createFoodFrame()
   {
      // dont execute now
      EventQueue.invokeLater(
         new Runnable()
         {
            // run
            public void run()
            {
               // Set up frame that allows you to enter log
               JFrame frame = new JFrame("Enter Food");
               frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
               
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
               
               JTextField tfFoodName = new JTextField(25);
                           
               panelFood.add(labelFoodName);
               panelFood.add(tfFoodName);
               panel.add(panelFood);
                              
               JPanel panelCalories = new JPanel();
            
               JLabel labelCalories = new JLabel("Calories:", SwingConstants.LEFT);
               
               panelCalories.setLayout(new FlowLayout());
               
               JTextField tfCalories = new JTextField(4);
                           
               panelCalories.add(labelCalories);
               panelCalories.add(tfCalories);
               panel.add(panelCalories);
            
               
               
               JPanel panelFat = new JPanel();
               
               JLabel labelFat = new JLabel("Fat:", SwingConstants.LEFT);
                
               JPanel inputFat = new JPanel();
               panelFat.setLayout(new FlowLayout());
               
               JTextField tfFat = new JTextField(4);
                           
               panelFat.add(labelFat);
               panelFat.add(tfFat);
               panel.add(panelFat);
               
               
               
               JPanel panelCarb = new JPanel();
               
               JLabel labelCarb = new JLabel("Carbohydrate:", SwingConstants.LEFT);
                
               JPanel inputCarb = new JPanel();
               panelCarb.setLayout(new FlowLayout());
               
               JTextField tfCarb = new JTextField(4);
                           
               panelCarb.add(labelCarb);
               panelCarb.add(tfCarb);
               panel.add(panelCarb);
               
               
                              
               JPanel panelPro = new JPanel();
               panelPro.setLayout(new FlowLayout());
               JLabel labelPro = new JLabel("Protein:");
               
               JTextField tfPro = new JTextField(4);
                           
               panelPro.add(labelPro);
               panelPro.add(tfPro);
               panel.add(panelPro);
            
               Panel panelButtons = new Panel();
               panelButtons.setLayout(new FlowLayout());
            
               JButton buttonEnter = new JButton("Enter");
               JButton buttonCancel = new JButton("Cancel");
               
               panelButtons.add(buttonEnter);
               panelButtons.add(buttonCancel);
               panel.add(panelButtons);
               
            
               frame.getContentPane().add(BorderLayout.CENTER, panel);
            
               frame.pack();
               frame.setLocationByPlatform(true);
               frame.setVisible(true);
               frame.setResizable(false);
             
               panelFood.requestFocus();
               
               buttonEnter.addActionListener( 
                  new ActionListener()
                  {
                     public void actionPerformed(ActionEvent e)
                     {
                     
                        System.out.println("Enter!");
                        String letter = "f";
                        String foodName = tfFoodName.getText();
                        String calories = tfCalories.getText();
                        String fat = tfFat.getText();
                        String carb = tfCarb.getText();
                        String protein = tfPro.getText();
                        
                        System.out.println(foodName + " contains " + calories + " calories, " + fat + " fat, " + carb + " carbohydrate, and " + protein + " protein");
                        
                        frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
                     }
                  });
                  
                                       
               buttonCancel.addActionListener( 
                  new ActionListener()
                  {
                     public void actionPerformed(ActionEvent e)
                     {
                     
                        System.out.println("Cancel!");
                        frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
                     }
                  });
            
            }
         });
   }
   
}