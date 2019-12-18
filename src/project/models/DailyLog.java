

import java.util.ArrayList;

public class DailyLog
{
    private ArrayList<DailyLog> logCollection;
    private int year;
    private int day;
    private int month;
    private String type;
    private String food = "";
    private String exercise = "";
    private float ounce = 0.0f;
    private double count = 0;
    private float weight = 0f;
    private float calories = 0f;
    private double minutes = 0;

    // Variables
    private String input = "";

    // DailyLog Contructor
    public DailyLog()
    {
        logCollection = new ArrayList<DailyLog>();
    }


    // add calories or weight
    public DailyLog(int year, int month, int day, String type, float weightOrCalories)
    {
        this.setYear(year);
        this.setMonth(month);
        this.setDay(day);
        this.setType(type);
        if(type.equals("w"))
        {
            this.setWeight(weightOrCalories);
        }
        else if(type.equals("c"))
        {
            this.setCalories(weightOrCalories);
        }
    }

    // add food
    public DailyLog(int year, int month, int day, String type, String name, float numServing)
    {
        this.setYear(year);
        this.setMonth(month);
        this.setDay(day);
        this.setType(type);
        this.setFood(name);
        this.setCount(numServing);
    }

    public DailyLog(int year, int month, int day, String type, String name, double minutes)
    {
        this.setYear(year);
        this.setMonth(month);
        this.setDay(day);
        this.setType(type);
        this.setExercise(name);
        this.setMinutes(minutes);
    }

    //return the value of year
    public int getYear(){
        return year;}
    //set the values of year
    public void setYear(int _year){this.year = _year;}

    // set the minutes
    public void setMin( double _min ){ this.minutes = _min; }

    // return the value of min
    public double getMin(){ return this.minutes; }

    //return the value of exercise
    public String getExercise(){ return this.exercise; }

    // set the value of exercise
    public void setExercise(String _exercise){ this.exercise = _exercise; }

    //return the value of month
    public int getMonth(){
        return month;}
    //set the values of month
    public void setMonth(int _month){this.month = _month;}

    //return the value of day
    public int getDay(){
        return day;}
    //set the values of day
    public void setDay(int _day){this.day = _day;}

    //return the value of type
    public String getType(){
        return type;}
    //set the values of type
    public void setType(String _type){this.type = _type;}

    //return the value of food
    public String getFood(){
        return food;}
    //set the values of food
    public void setFood(String _food){this.food = _food;}

    //return the value of ounce
    public float getOunce(){
        return ounce;}
    //set the values of ounce
    public void setOunce(float _ounce){this.ounce = _ounce;}

    //return the value of count
    public double getCount(){
        return count;}
    //set the values of count
    public void setCount(double _count){this.count = _count;}

    //return the value of weight
    public float getWeight(){
        if (weight == 0){
            return 150;
        }
        return weight;
    }
    //set the values of weight
    public void setWeight(float _weight){this.weight = _weight;}

    //return the value of calories
    public float getCalories(){
        if (calories == 0){
            return 1500;
        }
        return calories;
    }
    //set the values of calories

    public void setCalories(float _calories){this.calories = _calories;}

    // return the value of minutes
    public double getMinutes(){
        return minutes;
    }
    // set the value of minutes
    public void setMinutes(double _minutes){this.minutes = _minutes;}

    //return the values of arraylist
    public ArrayList<DailyLog> getDailyLog()
    {
        return logCollection;
    }

    //add the dailylog class
    public void Add(DailyLog log)
    {
        logCollection.add(log);
    }

    // remove the selected log in dailylog collection
    public void Remove(DailyLog log){ logCollection.remove(log); }

    //tostring for writing to file.
    @Override
    public String toString()
    {
        if(getType().equalsIgnoreCase("w"))
        {
            return getYear()+","+getMonth()+","+getDay()+","+getType()+","+getWeight()+"\n";
        }
        else if(getType().equalsIgnoreCase("c"))
        {
            return getYear()+","+getMonth()+","+getDay()+","+getType()+","+getCalories()+"\n";
        }
        else if(getType().equals("f"))
        {
            return getYear()+","+getMonth()+","+getDay()+","+getType()+","+getFood()+","+getCount()+"\n";
        }
        else {
            return getYear()+","+getMonth()+","+getDay()+","+getType()+","+getExercise()+","+getMinutes()+"\n";
        }
    }
}