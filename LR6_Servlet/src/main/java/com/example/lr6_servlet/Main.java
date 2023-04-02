package com.example.lr6_servlet;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "Temperature", value = "/LR6")
public class Main extends HttpServlet {
    private String message;

    public void init() {
        message = "";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        response.setContentType("text/html");

        int [] temperatures = getData("C:\\Users\\Egor\\Desktop\\ДОМАШКА\\ООП\\LR6 temperatures.txt");
        double averageTemperature = getAverageTemperature(temperatures);                //  a
        int daysAboveAverage = getDaysAboveAverage(temperatures, averageTemperature);   //  б
        int daysBelowAverage = getDaysBelowAverage(temperatures, averageTemperature);   //  в
        int [][] hottestDays = getHottestDays(temperatures);                            //  г

        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h2> Monthly average air temperature: " + averageTemperature + " C </h2>");
        out.println("<h2> The number of days when the temperature was higher than the monthly average: " + daysAboveAverage + " C </h2>");
        out.println("<h2> The number of days when the temperature dropped below: " + daysBelowAverage + " C </h2>");
        out.println("<br><h2>The three warmest days</h2>");
        for (int[] hottestDay : hottestDays)
            out.println("<h2>April " + hottestDay[0] + ": " + hottestDay[1] + " C </h2>");
        out.println("<h2>" + message + "</h2>");
        out.println("</body></html>");
    }
    public static double getAverageTemperature(int [] temperatures){
        double averageTemp = 0;

        int sum = 0;
        for (int temperature : temperatures) sum += temperature;
        averageTemp =  (double) sum/temperatures.length;

        return averageTemp;
    }//a
    public static int getDaysAboveAverage(int [] temperatures, double averageTemp) {
        int daysAboveAverage = 0;
        for (int temperature : temperatures)
            if (temperature > averageTemp)
                daysAboveAverage++;
        return daysAboveAverage;
    }//б
    public static int getDaysBelowAverage(int [] temperatures, double averageTemp){
        int daysBelowAverage = 0;
        for (int temperature : temperatures)
            if (temperature < averageTemp)
                daysBelowAverage++;

        return daysBelowAverage;
    }//в
    public static int [][] getHottestDays(int [] temperatures){
        int [][] hottestDays = new int [3][2];

        Integer[] indices = new Integer[temperatures.length];   //  массив для запоминания индексов дней

        for (int i = 0; i < indices.length; i++) indices[i]=i;
        Arrays.sort(indices, (a, b) -> temperatures[b] - temperatures[a]);

        for (int i = 0; i < hottestDays.length; i++){
            hottestDays[i][0] = indices[i];
            hottestDays[i][1] = temperatures[indices[i]];
        }
        return hottestDays;
    }//г
    public static int[] getData(String path){
        List<String> Data = new ArrayList<>();
        try (FileReader fr= new FileReader(path)){
            Scanner scan = new Scanner(fr);
            while (scan.hasNextLine())
                Data.add(scan.nextLine());
        }
        catch(IOException ex){
            System.out.println("[FILE NOT FOUND] " + ex.getMessage());
        }

        int [] DataTemperature = new int[Data.size()];
        for (int i = 0; i < Data.size(); i++)
            DataTemperature[i]=Integer.parseInt(Data.get(i));

        System.out.println("Data from "+ path +"is uploaded: ");
        for (int t : DataTemperature)
            System.out.print("[" + t + "] ");
        System.out.println();
        return DataTemperature;
    }//загрузка данных из файла и запихивание в массив температур

    public void destroy() {
    }
}