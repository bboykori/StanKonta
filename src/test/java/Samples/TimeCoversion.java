package Samples;


import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

public class TimeCoversion {
    public static void main(String[] args) {



       Timestamp now = new Timestamp(System.currentTimeMillis());


       long daysOffset = -93;
       long milisecondsOffset = daysOffset*3600*24*1000;

       long nowLong = now.getTime();
       long pastLong = nowLong+milisecondsOffset;
        System.out.println(milisecondsOffset);

        System.out.println(nowLong);
        System.out.println(pastLong);




        System.out.println(now);
        System.out.println(now.toLocalDateTime());
        //Calendar calendar = Calendar.getInstance();


        Date time= new Date(pastLong);

        System.out.println(time);
        LocalDateTime pastLocalDateTime = Instant.ofEpochMilli(pastLong).atZone(ZoneId.systemDefault()).toLocalDateTime();
        System.out.println(pastLocalDateTime);

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        String formattedNow = dtf.format(pastLocalDateTime);
        System.out.println(formattedNow);






    }
}
