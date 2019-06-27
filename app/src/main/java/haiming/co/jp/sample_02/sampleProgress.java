package haiming.co.jp.sample_02;

import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ProgressBar;

/**
 * 非同期処理
 */
public class sampleProgress extends AsyncTask <Long, Integer,String>{

    private Activity activity;
    private ProgressBar p_bar;

    /**
     * 非同期処理開始
     * @param count1
     * @param progressBar
     */
    public sampleProgress(int count1, ProgressBar progressBar, Activity a){
        this.p_bar = progressBar;
        this.p_bar.setMax(count1);
        this.activity = a;
    }

    // 実際に動いている部分
    @Override
    protected String doInBackground(Long... longs) {
        int values = 0;
        String result = "OK";
        publishProgress(values);

        for (int i = 0; i < p_bar.getMax() / 10; i++ ){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
            p_bar.setProgress(i * 10);
        }

        //処理が終了したとき、onPostExecuteのresultに渡す
        return result;
    }

    @Override
    protected void onPostExecute(String result) {
        //処理が終了した
        //resultは、doInBackgroundからの戻り値
        Log.v("非同期処理","非同期処理終了 : " + result);
    }
}
