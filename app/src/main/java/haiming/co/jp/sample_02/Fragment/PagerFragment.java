package haiming.co.jp.sample_02.Fragment;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import haiming.co.jp.sample_02.Activity.TodoRegistActivity;
import haiming.co.jp.sample_02.Adapter.TodoListAdapater;
import haiming.co.jp.sample_02.AlarmNotification;
import haiming.co.jp.sample_02.Dao.DaoTodo;
import haiming.co.jp.sample_02.Data.TodoData;
import haiming.co.jp.sample_02.DialogFragment.DatePickerDialogFragment;
import haiming.co.jp.sample_02.DialogFragment.TimePickerDialogFragment;
import haiming.co.jp.sample_02.Interface.DateDecisionCallback;
import haiming.co.jp.sample_02.Interface.TimeDecisionCallback;
import haiming.co.jp.sample_02.R;

import static android.content.Context.ALARM_SERVICE;

public class PagerFragment extends Fragment {
    private int requestCode = 1;
    private AlarmManager am;
    private PendingIntent pending;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_pageritem, container, false);

        Bundle bundle = getArguments();
        int index = bundle.getInt("INDEX");

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.todo_rec);

        List<TodoData> arrayList = new ArrayList<>();
        DaoTodo daoTodo = new DaoTodo(getActivity().getApplicationContext(), "", null, 1);

        arrayList = daoTodo.sel_all_todo();

        RecyclerView.Adapter adapter = new TodoListAdapater(arrayList, getActivity());

        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(llm);
        llm.setSmoothScrollbarEnabled(true);

        // 区切り線追加
        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(itemDecoration);
        recyclerView.setAdapter(adapter);


        FloatingActionButton floatingActionButton = (FloatingActionButton) view.findViewById(R.id.floatingActionButton3);
        floatingActionButton.setOnClickListener(onClickListener);

        TextView textView = (TextView) view.findViewById(R.id.textView1);
        textView.setText("Index:" + index);

        ImageView imageView = (ImageView) view.findViewById(R.id.calender_img);
        imageView.setOnClickListener(img_onClickListener);

        return view;
    }

    private View.OnClickListener img_onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            final String[] year = {""};
            final String[] month = {""};
            final String[] day = {""};
            final String[] hour = {""};
            final String[] minute = {""};

            final TimeDecisionCallback timeDecisionCallback = new TimeDecisionCallback() {
                @Override
                public void DecidionTime(String hour_, String minute_) {

                    hour[0] = hour_;
                    minute[0] = minute_;

                    // 設定された日時の確認
                    Log.v("Calender", "date = " + year[0] + "/" + month[0] + "/" + day[0] + " " + hour[0] + ":" + minute[0]);
                    String date =year[0] + "/" + month[0] + "/" + day[0] + " " + hour[0] + ":" + minute[0];
                    setAlarm(date);

                }
            };
            DateDecisionCallback decisionCallback = new DateDecisionCallback() {
                @Override
                public void onDecisionDate(String year_, String month_, String day_) {
                    year[0] = year_;
                    month[0] = String.valueOf(Integer.parseInt(month_) + 1);
                    day[0] = day_;

                    TimePickerDialogFragment.TimeCallback_Set(timeDecisionCallback);
                    TimePickerDialogFragment timePickerDialogFragment = new TimePickerDialogFragment();
                    timePickerDialogFragment.show(getActivity().getSupportFragmentManager(), "timerpickerfragment");

                }
            };

            DatePickerDialogFragment.DateCallback_Set(decisionCallback);
            DatePickerDialogFragment datePicker = new DatePickerDialogFragment();
            datePicker.show(getActivity().getSupportFragmentManager(), "datePicker");
        }
    };


    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(getActivity().getApplicationContext(), TodoRegistActivity.class);
            startActivity(intent);
            getActivity().overridePendingTransition(R.anim.in_right,R.anim.out_left);
        }
    };

    private void setAlarm(String date){
        // Alarmの登録

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        // 10sec
        calendar.add(Calendar.SECOND, 10);

        Intent intent = new Intent(getContext(), AlarmNotification.class);
        intent.putExtra("RequestCode", requestCode);

        pending = PendingIntent.getBroadcast(getContext(), requestCode, intent, 0);

        // アラームをセットする
        am = (AlarmManager) getActivity().getSystemService(ALARM_SERVICE);

        if (am != null) {
            am.setExact(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pending);

            // トーストで設定されたことをを表示
            Toast.makeText(getContext(), "alarm start", Toast.LENGTH_SHORT).show();

            Log.d("debug", "start");
        }
    }
}
