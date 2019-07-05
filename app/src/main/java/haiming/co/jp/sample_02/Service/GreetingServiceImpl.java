package haiming.co.jp.sample_02.Service;

import android.util.Log;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.inject.Inject;

import haiming.co.jp.sample_02.Interface.CalenderService;
import haiming.co.jp.sample_02.Interface.GreetingService;

public class GreetingServiceImpl implements GreetingService {
    private final CalenderService calenderService;

    @Inject
    public GreetingServiceImpl(CalenderService calenderService){
        Log.v("GreetingServiceImpl","@Inject GreetingServiceImple");
        this.calenderService = calenderService;
    }

    @Override
    public void outAsOfTody(String word) {
        Log.v("GreetingServiceImpl","outAsOfTody");
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            LocalDate today = calenderService.getToday();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
            String formattedDate = today.format(formatter);
            Log.v("GreetingServiceImpl","Hello " + word +" 今日は" + formattedDate + "です。");
        }
    }
}
