package com.tecnomant.util;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public final class Formato {
    private static final DateTimeFormatter FECHA=DateTimeFormatter.ofPattern("dd/MM/uuuu");
    private static final NumberFormat MONEDA=NumberFormat.getCurrencyInstance(Locale.of("es","CR"));
    private Formato(){}
    public static String fecha(LocalDate f){return f==null?"—":f.format(FECHA);}
    public static String moneda(BigDecimal v){return v==null?"—":MONEDA.format(v);}
}
