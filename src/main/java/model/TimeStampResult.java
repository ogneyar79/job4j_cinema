package model;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public  class TimeStampResult {
    public static Timestamp stringDateConvertTs(String dateSt) {
        DateFormat formatter = new SimpleDateFormat("MMMM d, yyyy", Locale.ENGLISH);
        try {
            Date date = formatter.parse(dateSt);
            return new Timestamp(date.getTime());
        } catch (ParseException e) {
            System.out.println("Exception parsing String Date :" + e);
            return null;
        }
    }


}
