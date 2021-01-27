package SatelliteApp.helpers;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class Helpers {

    public String dateTimeNow(){
        Timestamp now = new Timestamp(System.currentTimeMillis());
        long nowLong = now.getTime();
        LocalDateTime nowLocalDateTime = Instant.ofEpochMilli(nowLong).atZone(ZoneId.systemDefault()).toLocalDateTime();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        String formattedNow = dtf.format(nowLocalDateTime);
        return formattedNow;
    }

    public String dateTimePast(long daysAgo){
        Timestamp now = new Timestamp(System.currentTimeMillis());
        long milisecondsOffset = daysAgo*3600*24*1000;
        long nowLong = now.getTime();
        long pastLong = nowLong-milisecondsOffset;
        LocalDateTime pastLocalDateTime = Instant.ofEpochMilli(pastLong).atZone(ZoneId.systemDefault()).toLocalDateTime();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        String formattedPast = dtf.format(pastLocalDateTime);
        return formattedPast;
    }

    public long calculateRecordAge(String timeAsString)
    {
        Date timeAsDate = null;
        try {
            timeAsDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(timeAsString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Timestamp now=new Timestamp(System.currentTimeMillis());
        long timeDifference = (now.getTime()-timeAsDate.getTime())/1000;
        return timeDifference;
    }
}
