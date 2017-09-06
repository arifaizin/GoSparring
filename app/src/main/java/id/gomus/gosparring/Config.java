package id.gomus.gosparring;

/**
 * Created by root on 31/08/17.
 */

public class Config {
    public static String formatCustomDate(int year, int month, int date) {
        String formattedDate = "";

        if(date < 10)   {
            formattedDate = "0" + Integer.toString(date);
        }
        else {
            formattedDate = Integer.toString(date);
        }

        if(month == 1)  formattedDate += "-Jan-";
        if(month == 2)  formattedDate += "-Feb-";
        if(month == 3)  formattedDate += "-Mar-";
        if(month == 4)  formattedDate += "-Apr-";
        if(month == 5)  formattedDate += "-Mei-";
        if(month == 6)  formattedDate += "-Jun-";
        if(month == 7)  formattedDate += "-Jul-";
        if(month == 8)  formattedDate += "-Ags-";
        if(month == 9)  formattedDate += "-Sep-";
        if(month == 10)  formattedDate += "-Okt-";
        if(month == 11)  formattedDate += "-Nov-";
        if(month == 12)  formattedDate += "-Des-";

        return (formattedDate + Integer.toString(year));
    }

    public static String formatDMY(int year, int month, int date) {
        String formattedDate = "";

        if(date < 10)   {
            formattedDate += "0" + Integer.toString(date);
        }
        else {
            formattedDate += Integer.toString(date);
        }
        formattedDate += "-";

        if(month < 10) {
            formattedDate += "0" + Integer.toString(month);
        }
        else {
            formattedDate += Integer.toString(month);
        }
        formattedDate += "-";

        formattedDate += Integer.toString(year);

        return formattedDate;
    }

}
