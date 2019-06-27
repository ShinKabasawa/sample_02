package haiming.co.jp.sample_02.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.Calendar;

import haiming.co.jp.sample_02.Adapter.Weather_5days_ver_Adapter;
import haiming.co.jp.sample_02.Data.Common;
import haiming.co.jp.sample_02.R;

public class Weather_2_ACtivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_2__activity);
    }

    @Override
    protected void onStart(){
        super.onStart();

        Calendar calender = Calendar.getInstance();

        calender.set(2019,6,21);



        LinearLayoutManager llm_1 = new LinearLayoutManager(this);
        llm_1.setSmoothScrollbarEnabled(true);
        llm_1.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView = (RecyclerView)findViewById(R.id.recycler_view_5days);

        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(itemDecoration);
        Weather_5days_ver_Adapter weather_5days_ver_adapter = new Weather_5days_ver_Adapter(Common.arrayList_weather,this);
        recyclerView.setLayoutManager(llm_1);
        recyclerView.swapAdapter(weather_5days_ver_adapter,false);
    }
}
