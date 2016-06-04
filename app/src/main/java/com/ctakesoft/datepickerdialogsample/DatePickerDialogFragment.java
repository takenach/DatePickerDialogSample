package com.ctakesoft.datepickerdialogsample;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.widget.DatePicker;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class DatePickerDialogFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {
    @SuppressWarnings("unused")
    private static final String TAG = DatePickerDialogFragment.class.getSimpleName();
    @SuppressWarnings("unused")
    private final DatePickerDialogFragment self = this;

    private static final String BUNDLE_KEY_YEAR = "year";
    private static final String BUNDLE_KEY_MONTH = "monthOfYear";
    private static final String BUNDLE_KEY_DAY = "dayOfMonth";

    public static DatePickerDialogFragment newInstance(int year, int monthOfYear, int dayOfMonth) {
        DatePickerDialogFragment dialog = new DatePickerDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(BUNDLE_KEY_YEAR, year);
        bundle.putInt(BUNDLE_KEY_MONTH, monthOfYear);
        bundle.putInt(BUNDLE_KEY_DAY, dayOfMonth);
        dialog.setArguments(bundle);
        return dialog;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        // min is today
        GregorianCalendar minDate = new GregorianCalendar();
        minDate.set(year, month, day);

        // max is after 1 month
        GregorianCalendar maxDate = new GregorianCalendar();
        maxDate.set(year, month + 1, day);

        if (getArguments() != null) {
            year = getArguments().getInt(BUNDLE_KEY_YEAR);
            month = getArguments().getInt(BUNDLE_KEY_MONTH);
            day = getArguments().getInt(BUNDLE_KEY_DAY);
        }

        DatePickerDialog dialog = new DatePickerDialog(getActivity(), (MainActivity)getActivity(),  year, month, day);
        dialog.getDatePicker().setCalendarViewShown(true);
        dialog.getDatePicker().setSpinnersShown(false);
        dialog.getDatePicker().setMaxDate(maxDate.getTimeInMillis());
        dialog.getDatePicker().setMinDate(minDate.getTimeInMillis());

        return dialog;
    }

    @Override
    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

    }
}
