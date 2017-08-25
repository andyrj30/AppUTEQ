package com.example.andres_dell.uteqdemo.utils;

import android.animation.Animator;
import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.support.v7.widget.AppCompatEditText;
import android.transition.Fade;
import android.transition.Slide;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewGroup;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by varsovski on 28-Sep-15.
 */
public class UIUtil {

    public static void SlideWindowTransition(Window window) {
        Slide slide = new Slide();
        slide.setDuration(1000);
        window.setExitTransition(slide);
    }

    public static void FadeWindowTransition(Window window) {
        Fade fade = new Fade();
        fade.setDuration(1000);
        window.setEnterTransition(fade);
    }


    // public static WebView webview;
    //metodo verficar conexion a internet
    public static boolean verificaConexion(Context ctx) {
        boolean bConectado = false;
        ConnectivityManager connec = (ConnectivityManager) ctx
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        // No sólo wifi, también GPRS
        NetworkInfo[] redes = connec.getAllNetworkInfo();
        // este bucle debería no ser tan ñapa
        for (int i = 0; i < 2; i++) {
            // ¿Tenemos conexión? ponemos a true
            if (redes[i].getState() == NetworkInfo.State.CONNECTED) {
                bConectado = true;
            }
        }
        return bConectado;
    }
    //fin de "metodo verficar conexion a internet"


    ///// Animacion para los CardView a medida que se cargan o eliminan de la Vista /////
    public static void animateCircularReveal(View view) {
        int centerX = 0;
        int centerY = 0;
        int startRadius = 0;
        int endRadius = Math.max(view.getWidth(), view.getHeight());
        Animator animation = ViewAnimationUtils.createCircularReveal(view, centerX, centerY, startRadius, endRadius);
        view.setVisibility(View.VISIBLE);
        animation.start();
    }
    /////// Fin Animacion ///////////////


    //Método temporal para la validación de la red a la que está conectado el usuario
    public static String ssid(Context ctx) {
        try {
            WifiManager wifiMgr = (WifiManager) ctx.getSystemService(ctx.WIFI_SERVICE);
            WifiInfo wifiInfo = wifiMgr.getConnectionInfo();
            String ssid = wifiInfo.getSSID();
            return ssid;
        } catch (Exception ex) {
            return "Error: " + ex;
        }
    }

    public static String ipAConetarse(Context ctx) {

        String ssid = ssid(ctx);
        ssid = ssid.substring(1, ssid.length() - 1);
        String ipRetorno = "";

        if (ssid.equals("Comedor Universitario") || ssid == "lidertics1" || ssid == "Wifi_UTEQ" || ssid == "Docentes") {
            ipRetorno = Constants.IP_LOCAL;
        } else {
            ipRetorno = Constants.IP_PUBLICA;
        }
        return ipRetorno;
    }

    public static boolean isNumeric(String a) {
        boolean r = false;
        try {
            Integer.valueOf(a);
            r = true;
        } catch (Exception ex) {
        }
        return r;
    }

    public static String longevidad(String publicacion) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date fecha = format.parse(publicacion);
        String longevidad = "Publicado hace ";
        java.util.Date fechaActual = new Date();
        long diferenciaEn_ms = fechaActual.getTime() - fecha.getTime();
        long dias = diferenciaEn_ms / (1000 * 60 * 60 * 24);
        if (dias <= 30)
            longevidad += "días";
        else if (dias <= 45)
            longevidad += "más de un mes";
        else if (dias <= 360)
            longevidad += "meses";
        else if (dias <= 540)
            longevidad += "más de un año";
        else if (dias > 540)
            longevidad += "años";
        return longevidad;
    }
}
