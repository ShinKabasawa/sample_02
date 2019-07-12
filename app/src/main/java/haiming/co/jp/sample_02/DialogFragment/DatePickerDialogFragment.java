package haiming.co.jp.sample_02.DialogFragment;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.widget.DatePicker;

import java.util.Calendar;

import haiming.co.jp.sample_02.Interface.DateDecisionCallback;

public class DatePickerDialogFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {

    private static DateDecisionCallback decisionCallback;

    public static void DateCallback_Set(DateDecisionCallback callback){
        decisionCallback = null;
        decisionCallback = callback;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(),this, year, month, dayOfMonth);
        this.setCancelable(false);
        return datePickerDialog;
    }


    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        decisionCallback.onDecisionDate(String.valueOf(year), String.valueOf(month), String.valueOf(dayOfMonth));
    }
}