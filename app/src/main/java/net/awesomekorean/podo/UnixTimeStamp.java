package net.awesomekorean.podo;

import java.text.SimpleDateFormat;
import java.util.Date;

public class UnixTimeStamp {

    // 현재 유닉스 시간
    public static Long getTimeNow() {
        Long timeNow = System.currentTimeMillis() / 1000L;
        return timeNow;
    }


    // 유닉스 시간을 시간 포멧으로 전환
    public static String unixTimeFormat(Long unixTime) {
        Date date = new Date(unixTime * 1000);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String formattedDate = sdf.format(date);
        return formattedDate;
    }
}
