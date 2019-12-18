import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class BarGraphFrame extends JPanel {
   private float[] value;
   private String[] nutrition;
   private String title;

   public BarGraphFrame(float[] val, String[] nutri, String t) {
      nutrition = nutri;
      value = val;
      title = t;
   }

   // Leave this alone
   public void paintComponent(Graphics graphics) {
      super.paintComponent(graphics);
      if (value == null || value.length == 0)
         return;
      double minValue = 0;
      double maxValue = 0;
      for (int i = 0; i < value.length; i++) {
         if (minValue > value[i])
            minValue = value[i];
         if (maxValue < value[i])
            maxValue = value[i];
      }
      Dimension dim = getSize();
      int clientWidth = dim.width;
      int clientHeight = dim.height;
      int barWidth = clientWidth / value.length;
      Font titleFont = new Font("HelveticaNeue", Font.BOLD, 20);
      FontMetrics titleFontMetrics = graphics.getFontMetrics(titleFont);
      Font labelFont = new Font("HelveticaNeue", Font.PLAIN, 16);
      FontMetrics labelFontMetrics = graphics.getFontMetrics(labelFont);
      int titleWidth = titleFontMetrics.stringWidth(title);
      int q = titleFontMetrics.getAscent();
      int p = (clientWidth - titleWidth) / 2;
      graphics.setFont(titleFont);
      graphics.drawString(title, p, q);
      int top = titleFontMetrics.getHeight();
      int bottom = labelFontMetrics.getHeight();
      if (maxValue == minValue)
         return;
      double scale = (clientHeight - top - bottom) / (maxValue - minValue);
      q = clientHeight - labelFontMetrics.getDescent();
      graphics.setFont(labelFont);
      for (int j = 0; j < value.length; j++) {
         int valueP = j * barWidth + 1;
         int valueQ = top;
         int height = (int) (value[j] * scale);
         if (value[j] >= 0)
            valueQ += (int) ((maxValue - value[j]) * scale);
         else {
            valueQ += (int) (maxValue * scale);
            height = -height;
         }
         graphics.setColor(Color.red);
         graphics.fillRect(valueP, valueQ, barWidth - 2, height);
         graphics.setColor(Color.black);
         graphics.drawRect(valueP, valueQ, barWidth - 2, height);
         int labelWidth = labelFontMetrics.stringWidth(nutrition[j]);
         p = j * barWidth + (barWidth - labelWidth) / 2;
         graphics.drawString(nutrition[j], p, q);


      }


   }

   public void createBarGraph(float fatValue, float carbohydrateValue, float proteinValue)
   {
      JFrame frame = new JFrame();
      frame.setSize(450, 300);
      float[] value= new float[3];
      String[] type = new String[3];
      value[0] = fatValue;
      type[0] = "Fat";

      value[1] = carbohydrateValue;
      type[1] = "Carbohydrate";

      value[2] = proteinValue;
      type[2] = "Protein";


      frame.getContentPane().add(new BarGraphFrame(value, type,
              "Overall Consumption"));

      frame.setVisible(true);
   }


}