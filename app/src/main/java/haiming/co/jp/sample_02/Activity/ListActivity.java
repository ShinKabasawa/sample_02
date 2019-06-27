package haiming.co.jp.sample_02.Activity;

import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import haiming.co.jp.sample_02.Data.SampleData;
import haiming.co.jp.sample_02.R;
import haiming.co.jp.sample_02.Adapter.samplelistAdapter;

/**
 * リスト表示
 */
public class ListActivity extends AppCompatActivity {

    private RecyclerView rv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
    }

    @Override
    protected void onStart(){
        super.onStart();
        rv = (RecyclerView)findViewById(R.id.prefectures_list);
        RecyclerView.Adapter adapter = new samplelistAdapter(createDateset(),this);

        LinearLayoutManager llm = new LinearLayoutManager(this);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(llm);
        llm.setSmoothScrollbarEnabled(true);

        // 区切り線追加
        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        rv.addItemDecoration(itemDecoration);
        rv.setAdapter(adapter);

    }

    /**
     * リストデータ作成
     * @return リストデータ配列
     */
    public List<SampleData> createDateset(){
        List<SampleData> dataset = new ArrayList<>();

        SampleData data;
        Resources res = getResources();
        String[] ta = res.getStringArray(R.array.ken_name_array);

        ///////////////////////////////////////////////////
        // string-arrayから取得した配列をArrayListに格納 //
        ///////////////////////////////////////////////////
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(Arrays.asList(ta));
        ///////////////////////////////////////////////////

        Log.v("ListActivity","arraylist.size =" + arrayList.size());

        for (String name : ta) {
            data = new SampleData();
            data.Name = name;
            dataset.add(data);
        }

        return dataset;
    }
}
