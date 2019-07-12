package haiming.co.jp.sample_02.Interface;

public interface DateDecisionCallback  {
    /**
     * アラーム設定の日付
     * @param year      年
     * @param month     月
     * @param day       日
     */
    void onDecisionDate(String year, String month, String day);
}
