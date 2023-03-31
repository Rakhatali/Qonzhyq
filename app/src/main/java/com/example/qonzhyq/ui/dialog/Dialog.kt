package com.example.dentalplaza.front.dialog

import android.annotation.SuppressLint
import android.app.Activity
import android.app.AlertDialog
import com.example.qonzhyq.R


class Dialog(myActivity: Activity) {
    private var activity: Activity = myActivity
    private var dialog: AlertDialog? = null


    @SuppressLint("InflateParams")
    fun startLoadingdialog() {

        // adding ALERT Dialog builder object and passing activity as parameter
        val builder: AlertDialog.Builder = AlertDialog.Builder(activity)

        val inflater = activity.layoutInflater
        builder.setView(inflater.inflate(R.layout.dialog_loading, null))
        builder.setCancelable(true)
        dialog = builder.create()
        dialog?.show()
    }

    // dismiss method
    fun dismissdialog() {
        dialog?.dismiss()
    }
}