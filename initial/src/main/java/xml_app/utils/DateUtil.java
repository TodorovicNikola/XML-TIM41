package xml_app.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Vuletic on 16.6.2016.
 */
public class DateUtil {

    public static Date parseDate(String date) {
        int day = Integer.parseInt(date.substring(8,10)) + 1;
        String daystr = "";
        if(day < 10)
            daystr = "0" + Integer.toString(day);
        else
            daystr = Integer.toString(day);
        date = date.substring(0,8) + day;

        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date d = null;
        try {
            d = format.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return d;
    }

}
