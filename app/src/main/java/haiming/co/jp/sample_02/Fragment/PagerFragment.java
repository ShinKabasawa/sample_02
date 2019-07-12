package haiming.co.jp.sample_02.Fragment;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
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
import java.util.Objects;

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

    @SuppressLint("SetTextI18n")
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_pageritem, container, false);

        Bundle bundle = getArguments();
        int index = 0;
        if (bundle != null) {
            index = bundle.getInt("INDEX");
        }

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.todo_rec);

        List<TodoData> arrayList = new ArrayList<>();
        DaoTodo daoTodo = new DaoTodo(Objects.requireNonNull(getActivity()).getApplicationContext(), "", null, 1);

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

    // ImageViewのクリックリスナー
    private View.OnClickListener img_onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            final String[] year = {""};
            final String[] month = {""};
            final String[] day = {""};
            final String[] hour = {""};
            final String[] minute = {""};

            final TimeDecisionCallback timeDecisionCallback = new TimeDecisionCallback() {
                @RequiresApi(api = Build.VERSION_CODES.M)
                @Override
                public void DecidionTime(String hour_, String minute_) {

                    hour[0] = hour_;
                    minute[0] = minute_;

                    // 設定された日時の確認
                    Log.v("Calender", "date = " + year[0] + "/" + month[0] + "/" + day[0] + " " + hour[0] + ":" + minute[0]);
                    String[] date = new String[5];
                    date[0] = year[0];
                    date[1] = month[0];
                    date[2] = day[0];
                    date[3] = hour[0];
                    date[4] = minute[0];
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
                    timePickerDialogFragment.show(Objects.requireNonNull(getActivity()).getSupportFragmentManager(), "timerpickerfragment");

                }
            };

            DatePickerDialogFragment.DateCallback_Set(decisionCallback);
            DatePickerDialogFragment datePicker = new DatePickerDialogFragment();
            datePicker.show(Objects.requireNonNull(getActivity()).getSupportFragmentManager(), "datePicker");
        }
    };

    // FABボタンのクリックリスナー
    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(Objects.requireNonNull(getActivity()).getApplicationContext(), TodoRegistActivity.class);
            startActivity(intent);
            getActivity().overridePendingTransition(R.anim.in_right,R.anim.out_left);
        }
    };

    /**
     * アラームをセットする
     * @param date　設定した日時
     */
    @RequiresApi(api = Build.VERSION_CODES.M)
    private void setAlarm(String[] date){
        // Alarmの登録
        Log.v("setAlarm","currentTimeMills = " + System.currentTimeMillis());

        //////////////////////////////////////////////////////////
        //Calendar calendar = Calendar.getInstance();           //
        //calendar.setTimeInMillis(System.currentTimeMillis()); //
        //////////////////////////////////////////////////////////

        Calendar calendar = Calendar.getInstance();
        calendar.set(Integer.parseInt(date[0]),Integer.parseInt(date[1]),Integer.parseInt(date[2]),Integer.parseInt(date[3]),Integer.parseInt(date[4]));
        // 10sec
        //calendar.add(Calendar.SECOND, 10);

        Log.v("setAlarm","calender.getTImeMIlls" + calendar.getTimeInMillis());

        Intent intent = new Intent(getContext(), AlarmNotification.class);
        intent.putExtra("RequestCode", requestCode);

        pending = PendingIntent.getBroadcast(getContext(), requestCode, intent, 0);

        // アラームをセットする
        am = (AlarmManager) Objects.requireNonNull(getActivity()).getSystemService(ALARM_SERVICE);

        if (am != null) {
            am.setAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pending);

            // トーストで設定されたことをを表示
            Toast.makeText(getContext(), "alarm start", Toast.LENGTH_SHORT).show();

            Log.d("debug", "start");
        }
    }
}
