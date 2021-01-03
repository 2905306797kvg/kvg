package qst.com.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DataUtils {
    public static final String DEFAULT_PATTERN="yyyy-MM-dd";
    public static String format(Date date){
        SimpleDateFormat sdf=new SimpleDateFormat(DEFAULT_PATTERN);
        return sdf.format(date);
    }
}
