import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class LogManager {
    private DailyLog dailyLog = new DailyLog();
    private FileManager fm = new FileManager();
//    private View view;


    public LogManager() {

        loadLogData();
    }

    public void Add(DailyLog log) { dailyLog.Add(log); }

    public void Remove(ArrayList<String> _data) {
        ArrayList<DailyLog> tempDailyLogData = dailyLog.getDailyLog();
        for (var i = 0; i < tempDailyLogData.size(); i++) {
            if (_data.get(3).equalsIgnoreCase("w")) {
                if (tempDailyLogData.get(i).getYear() == Integer.parseInt(_data.get(0)) && tempDailyLogData.get(i).getMonth() == Integer.parseInt(_data.get(1)) && tempDailyLogData.get(i).getDay() == Integer.parseInt(_data.get(2)) && tempDailyLogData.get(i).getType().equalsIgnoreCase(_data.get(3)) && tempDailyLogData.get(i).getWeight() == Float.parseFloat(_data.get(4))) {
                    dailyLog.Remove(tempDailyLogData.get(i));
                }
            } else if (_data.get(3).equalsIgnoreCase("c")) {
                if (tempDailyLogData.get(i).getYear() == Integer.parseInt(_data.get(0)) && tempDailyLogData.get(i).getMonth() == Integer.parseInt(_data.get(1)) && tempDailyLogData.get(i).getDay() == Integer.parseInt(_data.get(2)) && tempDailyLogData.get(i).getType().equalsIgnoreCase(_data.get(3)) && tempDailyLogData.get(i).getCalories() == Float.parseFloat(_data.get(4))) {
                    dailyLog.Remove(tempDailyLogData.get(i));
                }
            } else if (_data.get(3).equalsIgnoreCase("f")) {
                if (tempDailyLogData.get(i).getYear() == Integer.parseInt(_data.get(0)) && tempDailyLogData.get(i).getMonth() == Integer.parseInt(_data.get(1)) && tempDailyLogData.get(i).getDay() == Integer.parseInt(_data.get(2)) && tempDailyLogData.get(i).getType().equalsIgnoreCase(_data.get(3)) && tempDailyLogData.get(i).getFood().equalsIgnoreCase(_data.get(4)) && tempDailyLogData.get(i).getOunce() == Float.parseFloat(_data.get(5))) {
                    dailyLog.Remove(tempDailyLogData.get(i));
                }
            }
        }
    }

    public void removeExercise(String name)
    {
        for(var e : dailyLog.getDailyLog())
        {
            if(e.getType().equals("e"))
            {
                if(e.getFood().equals(name))
                {
                    dailyLog.getDailyLog().remove(e);
                    break;
                }
            }
        }
    }

    public ArrayList<String> getDateList(){
        ArrayList<String> arrayDate = new ArrayList<String>();
        for (DailyLog d : dailyLog.getDailyLog()){
            String s = String.valueOf(d.getYear());
            s += " " + String.valueOf(d.getMonth());
            s += " " + String.valueOf(d.getDay());
            arrayDate.add(s);
        }

        Set<String> set = new HashSet<>(arrayDate);
        arrayDate.clear();
        arrayDate.add("");
        arrayDate.addAll(set);

        return arrayDate;
    }

    public ArrayList<DailyLog> GetDailyLog()
    {
        return dailyLog.getDailyLog();
    }

    private void loadLogData() {
        ArrayList<ArrayList<String>> datas = fm.read("log");

        for (var data : datas) {
            if (data.get(3).equals("f")) {
                dailyLog.Add(new DailyLog(Integer.parseInt(data.get(0)), Integer.parseInt(data.get(1)), Integer.parseInt(data.get(2)), data.get(3), data.get(4), Float.parseFloat(data.get(5))));

            } else if (data.get(3).equals("w") || data.get(3).equals("c")) {
                dailyLog.Add(new DailyLog(Integer.parseInt(data.get(0)), Integer.parseInt(data.get(1)), Integer.parseInt(data.get(2)), data.get(3), Float.parseFloat(data.get(4))));
            } else if (data.get(3).equals("e")){
                dailyLog.Add(new DailyLog(Integer.parseInt(data.get(0)), Integer.parseInt(data.get(1)), Integer.parseInt(data.get(2)), data.get(3), data.get(4), Double.parseDouble(data.get(5))));
            }
        }

        for (var s : dailyLog.getDailyLog()) {
            System.out.println(s.toString());
        }
    }

    public void writeLogData() {
        ArrayList<String> writeObj = new ArrayList<String>();

        for (var d : dailyLog.getDailyLog()) {
            writeObj.add(d.toString());
        }

        fm.write(writeObj, "log");
    }

    public void displayLogData() {
        ArrayList<DailyLog> tempDailyLogData = dailyLog.getDailyLog();
        for (var i = 0; i < tempDailyLogData.size(); i++) {
            System.out.print(i + ". " + tempDailyLogData.get(i).toString());
        }
    }

    // return the total amount of calories
    public float totalCalories(ArrayList<Food> data) {
        float allCalories = 0.0f;
        ArrayList<DailyLog> input = getConsumedAll();
        for( DailyLog log: input ){
            if( !log.getType().equalsIgnoreCase("f") ){
                continue;
            }
            for( Food dat : data ){
                String foodName = log.getFood();
                if( dat instanceof Recipe ){
                    Recipe tt = (Recipe) dat;
                    if( tt.getLetter().equalsIgnoreCase("r") && tt.getTitle().equalsIgnoreCase(log.getFood()) ){
                        ArrayList<Food> dataRecipe = tt.getRecipe();

                        for( Food re: dataRecipe ){
                            for( Food caloreList: data ){
                                if( re.getName().equalsIgnoreCase( caloreList.getName() ) ){
                                    if( caloreList instanceof Food ){
                                        String foodList = caloreList.getName();
                                        allCalories += caloreList.getCalories();
                                    }
                                }
                            }
                        }
                    }
                }
                else if( dat instanceof BasicFood ){
                    Food fCurrent = (Food) dat;
                    if ( log.getFood().equalsIgnoreCase( fCurrent.getName() ) ){
                        float currentCalories = fCurrent.getCalories();
                        allCalories += currentCalories;
                    }
                }
            }
        }

        return allCalories;
    }


    // return the total amount of calories in string
    public ArrayList<Float> getAllNutriFact( ArrayList<Food> data ) {
        String result = "";
        float allCalories = 0f;
        float allFat = 0.0f;
        float allCarbo = 0.0f;
        float allProtein = 00f;

        ArrayList<DailyLog> input = getConsumedAll();
        for (DailyLog log : input) {
            if (!log.getType().equalsIgnoreCase("f")) {
                continue;
            }
            float calories = 0.0f;
            float serving = 0.0f;
            String name = log.getFood();
            for (Food dat : data) {
                String foodName = log.getFood();
                if (dat instanceof Recipe) {
                    Recipe tt = (Recipe) dat;
                    if (tt.getLetter().equalsIgnoreCase("r") && tt.getTitle().equalsIgnoreCase(log.getFood())) {
                        ArrayList<Food> dataRecipe = tt.getRecipe();
                        serving += log.getCount();
                        for (Food re : dataRecipe) {
                            for (Food caloreList : data) {
                                if (re.getName().equalsIgnoreCase(caloreList.getName())) {
                                    if (caloreList instanceof Food) {
                                        calories += caloreList.getCalories();

                                        allCarbo += caloreList.getCarb();
                                        allFat += caloreList.getFat();
                                        allProtein += caloreList.getProtein();

                                    }
                                }
                            }
                        }
                        result += name + " = Calories => " + calories + " Serving => " + log.getCount() + "\n";
                    }
                } else if (dat instanceof BasicFood) {
                    Food fCurrent = (Food) dat;
                    if (log.getFood().equalsIgnoreCase(fCurrent.getName())) {
                        float currentCalories = fCurrent.getCalories();
                        calories += currentCalories;
                        allCarbo += fCurrent.getCarb();
                        allFat += fCurrent.getFat();
                        allProtein += fCurrent.getProtein();

                        result += name += " Calories => " + calories + " = Serving =>" + log.getCount() + "\n";
                    }
                }
            }
            allCalories += calories;

        }
        ArrayList<Float> allNutri = new ArrayList<Float>();
        allNutri.add( allCalories );
        allNutri.add( allFat );
        allNutri.add( allCalories );
        allNutri.add( allProtein );
        return allNutri;
    }

    public Float calculateTotalCalories(String selectedDate){
        float totalCalories = 0.0f;
        ArrayList<DailyLog> data = dailyLog.getDailyLog();

        if ( !data.isEmpty() ){
            for (var exe : data){
                String exeDate = exe.getYear() + " " + exe.getMonth() + " " + exe.getDay();
                if (exeDate.equalsIgnoreCase(selectedDate) && exe.getType().equalsIgnoreCase("f")){
                    totalCalories += exe.getCalories();
                }
            }
        }
        return totalCalories;
    }

    // return all data of consumed food
    public ArrayList<DailyLog> getConsumedFoods() {
        ArrayList<DailyLog> result = new ArrayList<DailyLog>();
        ArrayList<DailyLog> data = dailyLog.getDailyLog();
        for (DailyLog exe : data) {
            if (exe.getType().equalsIgnoreCase("f")) {
                result.add(exe);
            }
        }
        return result;
    }

    // returns all consumed foods (both basic food and recipes)
    public ArrayList<DailyLog> getConsumedAll() {
        return dailyLog.getDailyLog();
    }
}