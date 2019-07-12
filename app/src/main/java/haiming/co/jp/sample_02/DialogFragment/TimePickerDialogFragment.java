package haiming.co.jp.sample_02.DialogFragment;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.widget.TimePicker;

import java.util.Calendar;

import haiming.co.jp.sample_02.Interface.TimeDecisionCallback;

public class TimePickerDialogFragment  extends DialogFragment implements TimePickerDialog.OnTimeSetListener {
    private static TimeDecisionCallback timeDecisionCallback;

    public static void TimeCallback_Set(TimeDecisionCallback callback){
        timeDecisionCallback = null;
        timeDecisionCallback = callback;
    }


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);

        TimePickerDialog timePickerDialog = new TimePickerDialog(getActivity(), this, hour, minute, true);
        this.setCancelable(false);
        return timePickerDialog;
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        Calendar calendar = Calendar.getInstance();
        int min =  calendar.get(Calendar.MINUTE);
//        int hour = calendar.get

//        if ()

        timeDecisionCallback.DecidionTime(String.valueOf(hourOfDay),String.valueOf(minute));
    }
}
