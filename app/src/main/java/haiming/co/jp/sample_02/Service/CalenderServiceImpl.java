package haiming.co.jp.sample_02.Service;

import android.os.Build;
import android.util.Log;

import java.time.LocalDate;

import haiming.co.jp.sample_02.Interface.CalenderService;

public class CalenderServiceImpl implements CalenderService {
    @Override
    public LocalDate getToday() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            Log.v("CalenderServiceImple","getToday");
            return LocalDate.now();
        }
        return null;
    }
}
