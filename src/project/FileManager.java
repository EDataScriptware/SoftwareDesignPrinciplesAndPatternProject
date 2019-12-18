

import java.util.*;
import java.io.*;

public class FileManager
{
   //Default Constructor
   public FileManager(){}

   File file;
   // set the file to read and write on file log.csv

   // returns boolean on whether file is exists or not
   private boolean isExist(File _file){
      if( _file.exists() )
         return true;
      else
         return false;
   }

   // return the information via reading file, otherwise return empty arraylist
   public ArrayList<ArrayList<String>> read(String fileName)
   {
      file = new File("src/data/"+fileName+".csv");

      if( isExist( file ) ){
         try{
            // begin the reader mode on
            BufferedReader br = new BufferedReader( new FileReader(file) );

            // set a variable to collecting reading data
            ArrayList<ArrayList<String>> collection = new ArrayList<ArrayList<String>>();
            String line = "";
            while( (line = br.readLine()) != null ){
               String[] split = line.split(",");
               ArrayList<String> ar = new ArrayList<String>();
               for( int i = 0; i < split.length; i++ ){
                  ar.add( split[i] );
               }
               collection.add(ar);
            }
            br.close();
            return collection;
         }
         catch( IOException i ){
            i.printStackTrace();
         }
      }
      else
      {
         System.out.println( file + " cannot be found.");
      }
      return new ArrayList<ArrayList<String>>();
   }

   public void write(ArrayList<String> _object, String fileName)
   {
      file = new File("src/data/"+fileName+".csv");

      try
      {
         FileWriter bw = new FileWriter(file);


         for(var s : _object)
         {
            bw.write(s);

         }
         bw.close();
         System.out.println("The Object is successfully write");

      }
      catch(Exception e)
      {
         e.printStackTrace();
      }
   }
}