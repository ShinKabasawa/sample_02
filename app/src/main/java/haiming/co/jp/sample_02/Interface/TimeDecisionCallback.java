package haiming.co.jp.sample_02.Interface;

public interface TimeDecisionCallback {
    /**
     * アラームの日時
     * @param hour      時
     * @param minute    分
     */
    void DecidionTime(String hour,String minute);
}
