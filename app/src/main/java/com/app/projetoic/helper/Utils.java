package com.app.projetoic.helper;

import java.text.DecimalFormat;

public class Utils {

    public static String arredondar( double numeroArredondado){
        DecimalFormat df = new DecimalFormat("#.###");
        return df.format(numeroArredondado);
    }
}
