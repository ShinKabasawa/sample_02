package haiming.co.jp.sample_02.Interface;

import javax.inject.Singleton;

import dagger.Component;
import haiming.co.jp.sample_02.GreetingCard;
import haiming.co.jp.sample_02.Module.GreetingModule;

public interface GreetingService {
    /*貰った文字列を今日時点として出力*/
    void outAsOfTody(String word);

    @Component(modules = { GreetingModule.class } )
    @Singleton
    interface GreetingCompornent {
        GreetingCard greet();
    }
}
