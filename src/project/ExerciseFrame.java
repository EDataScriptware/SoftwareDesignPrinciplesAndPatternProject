//Usually you will require both swing and awt packages
// even if you are working with just swings.
import javax.swing.*;
import java.awt.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.text.Document;


public class ExerciseFrame
{
   // When the Enter log button is clicked - invoke this method
   public static void createExerciseFrame()
   {
      // dont execute now
      // dont execute now
      EventQueue.invokeLater(
         new Runnable()
         {
            // run
            public void run()
            {
               // Set up frame that allows you to enter log
               JFrame frame = new JFrame("Enter Exercise");
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
               
               JLabel labelYear = new JLabel("Enter the year (YYYY):", SwingConstants.LEFT);
               
               JPanel panelYear = new JPanel();
               panelYear.setLayout(new FlowLayout());
               
               JTextField tfYear = new JTextField(4);
                           
               panelYear.add(labelYear);
               panelYear.add(tfYear);
               panel.add(panelYear);
                              
               JLabel labelMonth = new JLabel("Enter the month (MM):", SwingConstants.LEFT);
               
               JPanel panelMonth = new JPanel();
               panelMonth.setLayout(new FlowLayout());
               
               JTextField tfMonth = new JTextField(4);
                           
               panelMonth.add(labelMonth);
               panelMonth.add(tfMonth);
               panel.add(panelMonth);
            
               
               
               JPanel panelDays = new JPanel();
               
               JLabel labelDays = new JLabel("Enter the day (DD):", SwingConstants.LEFT);
                
               JPanel inputDays = new JPanel();
               panelDays.setLayout(new FlowLayout());
               
               JTextField tfDays = new JTextField(4);
                           
               panelDays.add(labelDays);
               panelDays.add(tfDays);
               panel.add(panelDays);
               
               JPanel panelValue = new JPanel();

               JLabel labelExerciseValue = new JLabel("Exercise:", SwingConstants.LEFT);               
               
               JTextField tfExerciseValue = new JTextField(10);
               
               panelValue.add(labelExerciseValue);
               panelValue.add(tfExerciseValue);
               panel.add(panelValue);
               
               JPanel panelMinutes = new JPanel();
               panelMinutes.setLayout(new FlowLayout());
               JLabel labelMinutes = new JLabel("Minutes:");
               
               JTextField tfMinutes = new JTextField(4);
                           
               panelMinutes.add(labelMinutes);
               panelMinutes.add(tfMinutes);
               panel.add(panelMinutes);
            
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
             
               panelYear.requestFocus();
               
               buttonEnter.addActionListener( 
                  new ActionListener()
                  {
                     public void actionPerformed(ActionEvent e)
                     {
                     
                        System.out.println("Enter!");
                        String letter = "e";
                        String year = tfYear.getText();
                        String month = tfMonth.getText();
                        String day = tfDays.getText();
                        String valueExercise = tfExerciseValue.getText();
                        String minutes = tfMinutes.getText();
                        
                        System.out.println(month +"/" + day+ "/" + year );
                        System.out.println("I did " + valueExercise + " for " + minutes + " minutes!");
                        
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