package com.app.projetoic.helper;

import java.text.DecimalFormat;

public class Utils {

    public static String arredondar( double numero){
        DecimalFormat df = new DecimalFormat("#.##");
        String numeroArredondado = df.format(numero);
        numeroArredondado = numeroArredondado.replace(",",".");
        return numeroArredondado;
    }
}
