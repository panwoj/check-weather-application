package com.pirog.weatherProject.format;

public class TextFormat {
    public static String formatDouble(Double d) {
        java.text.DecimalFormat df = new java.text.DecimalFormat();
        df.setMaximumFractionDigits(2);
        df.setMinimumFractionDigits(2);
        return df.format(d);
    }
}
