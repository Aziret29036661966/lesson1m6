package com.example.lesson1m6.ui

import android.content.Context
import android.widget.Toast
import androidx.core.content.ContextCompat
import es.dmoral.toasty.Toasty

fun Context.toastShow(msg:String, mark: Int, color: Int){
    Toasty.custom(this, msg,
        ContextCompat.getDrawable(this, mark),
        ContextCompat.getColor(this, android.R.color.black),
        ContextCompat.getColor(this, color),
        Toast.LENGTH_SHORT, true, true).show()
    Toasty.Config.reset()
}