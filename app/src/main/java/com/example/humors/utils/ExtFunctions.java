package com.example.humors.utils;


import android.graphics.Paint;
import android.widget.TextView;

public class ExtFunctions {

    public static void underlineText(TextView tv) {
        tv.setPaintFlags(tv.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
    }
}
