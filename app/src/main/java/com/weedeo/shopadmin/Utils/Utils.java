package com.weedeo.shopadmin.Utils;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.preference.PreferenceManager;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.weedeo.shopadmin.R;

import java.util.Calendar;

public class Utils {

    private static Context mContext;
    private static final String TAG = Utils.class.getSimpleName();

    public static void alert(Context context, String msg) {

        AlertDialog dialog = new AlertDialog.Builder(context,
                R.style.Theme_App_Light_Dialog_NoActionBar)
                .setMessage(msg)
                .setCancelable(false)
                .setPositiveButton(context.getString(R.string.btn_ok), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                })
                .create();

        if (dialog != null) {
            dialog.show();
        }
    }

    public static void Toast(Context context,String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
    }
    public static Dialog showProgressDialog(Context context){
        Dialog m_Dialog = new Dialog(context);
        m_Dialog.setContentView(R.layout.custom_progress_view);
        m_Dialog.setCancelable(false);
        m_Dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        return m_Dialog;
    }

    public static void showSnackBar(View view, String msg) {
        Snackbar.make(view, msg, Snackbar.LENGTH_INDEFINITE)
                .setAction("Action", null)
                .setDuration(6000)
                .show();
    }

    public static String LoadPreferences(Context context, String key) {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        String val = "";
        val = sp.getString(key, "");
        return val;
    }
    public static void hideKeyboard(View view) {
        InputMethodManager manager = (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);
        manager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    public static void SavePreferences(Context context, String key, String value) {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(key, value);
        editor.commit();
        //Log.w("Preference saved  .", value);

    }

    public static Boolean LoadPreferencesBoolean(Context context, String key) {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        Boolean val = false;
        val = sp.getBoolean(key, false);
        return val;
    }

    public static void clearPreference(Context context, String key) {
        SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(context).edit();
        editor.remove(key).commit();
    }



    public static String getDeviceDensity(Context context){
        String deviceDensity = "";
        switch (context.getResources().getDisplayMetrics().densityDpi) {
            case DisplayMetrics.DENSITY_LOW:
                deviceDensity =  0.75 + " ldpi";
                break;
            case DisplayMetrics.DENSITY_MEDIUM:
                deviceDensity =  1.0 + " mdpi";
                break;
            case DisplayMetrics.DENSITY_HIGH:
                deviceDensity =  1.5 + " hdpi";
                break;
            case DisplayMetrics.DENSITY_XHIGH:
                deviceDensity =  2.0 + " xhdpi";
                break;
            case DisplayMetrics.DENSITY_XXHIGH:
                deviceDensity =  3.0 + " xxhdpi";
                break;
            case DisplayMetrics.DENSITY_XXXHIGH:
                deviceDensity =  4.0 + " xxxhdpi";
                break;
            default:
                deviceDensity = "Not found";
        }
        return deviceDensity;
    }

    public static void showTimePickerDialog(Context mContext,TextView tvToDisplayTime){

        Calendar mcurrentTime = Calendar.getInstance();
        int hour = mcurrentTime.get(Calendar.HOUR);
        int minute = mcurrentTime.get(Calendar.MINUTE);

        TimePickerDialog mTimePicker;
        mTimePicker = new TimePickerDialog(mContext, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {

                String am_pm = "";

                if (selectedHour == 0) {

                    selectedHour += 12;

                    am_pm = "AM";
                }
                else if (selectedHour == 12) {

                    am_pm = "PM";

                }
                else if (selectedHour > 12) {

                    selectedHour -= 12;

                    am_pm = "PM";

                }
                else {

                    am_pm = "AM";
                }

                tvToDisplayTime.setText(String.format("%02d:%02d",selectedHour,selectedMinute) +am_pm);
                tvToDisplayTime.setBackgroundResource(0);

            }
        }, hour, minute, false);//No 24 hour time
        mTimePicker.setTitle("Select Time");
        mTimePicker.show();
    }
}
