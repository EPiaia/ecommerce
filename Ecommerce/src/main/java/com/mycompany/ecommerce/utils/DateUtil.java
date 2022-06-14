package com.mycompany.ecommerce.utils;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author Piaia
 */
public class DateUtil implements Serializable {

    private static final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    private static final SimpleDateFormat sdfHr = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

    public static Date addDays(Date data, int days) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(data);
        cal.add(Calendar.DAY_OF_MONTH, days);
        return cal.getTime();
    }

    public static String getDataFormatada(Date data) {
        return sdf.format(data);
    }

    public static String getDataHoraFormatada(Date data) {
        return sdfHr.format(data);
    }

}
