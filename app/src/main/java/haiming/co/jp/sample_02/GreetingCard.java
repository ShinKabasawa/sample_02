package haiming.co.jp.sample_02;

import android.util.Log;

import javax.inject.Inject;

import haiming.co.jp.sample_02.Interface.GreetingService;

public class GreetingCard {
    private final GreetingService greetingService;

    @Inject GreetingCard(GreetingService greetingService){
        this.greetingService = greetingService;
    }

    public void print(String receivename){
        Log.v("GreetingCard","print");
        greetingService.outAsOfTody(receivename);
    }
}
