package com.ctakesoft.datepickerdialogsample;

import android.app.DatePickerDialog;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    private static final String TAG = MainActivity.class.getSimpleName();

    Button mButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mButton = (Button) findViewById(R.id.dateBtn);
        if (mButton != null) {
            setCurrentDate();
            mButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String dateStr = mButton.getText().toString();
                    String[] dates = dateStr.split("/");
                    DialogFragment dialogFragment = new DatePickerDialogFragment();
                    try {
                        dialogFragment = DatePickerDialogFragment.newInstance(Integer.valueOf(dates[0]),
                                Integer.valueOf(dates[1]) - 1, Integer.valueOf(dates[2]));
                    } catch (NumberFormatException | IndexOutOfBoundsException e) {
                        Log.w(TAG, "onClick: ", e);
                    }
                    dialogFragment.show(getSupportFragmentManager(), "datePicker");
                }
            });
        }

    }

    @Override
    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        setDate(year, monthOfYear, dayOfMonth);
    }

    private void setCurrentDate() {
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        setDate(year, month, day);
    }

    private void setDate(int year, int monthOfYear, int dayOfMonth) {
        if (mButton != null) {
            mButton.setText(String.valueOf(year) + "/" + String.valueOf(monthOfYear + 1) + "/" + String.valueOf(dayOfMonth));
        }
    }
}