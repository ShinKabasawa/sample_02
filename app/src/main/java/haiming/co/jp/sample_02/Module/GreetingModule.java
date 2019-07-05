package haiming.co.jp.sample_02.Module;

import android.util.Log;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import haiming.co.jp.sample_02.Service.CalenderServiceImpl;
import haiming.co.jp.sample_02.Service.GreetingServiceImpl;
import haiming.co.jp.sample_02.Interface.CalenderService;
import haiming.co.jp.sample_02.Interface.GreetingService;

@Module
public class GreetingModule {
    @Singleton
    @Provides
    GreetingService provideGreetingService(CalenderService calenderService){
        Log.v("GreetingModule","provideGreetingService");
        return new GreetingServiceImpl(calenderService);
    }

    @Singleton
    @Provides
    CalenderService provideCalculatorService() {
        Log.v("GreetingModule","provideCalculatorService");
        return new CalenderServiceImpl();
    }
}
