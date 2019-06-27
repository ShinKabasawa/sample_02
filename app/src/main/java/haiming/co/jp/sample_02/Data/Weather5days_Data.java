package haiming.co.jp.sample_02.Data;

/**
 * 5日間天気データクラス
 */
public class Weather5days_Data {
    public String temp;                 // 温度
    public String temp_min;             // 最低温度
    public String temp_max;             // 最高温度
    public String pressure;             // デフォルトの海面気圧
    public String sea_level_pre;        // 海面気圧
    public String grnd_level_pre;       // 地上の大気圧
    public String humidity;             // 湿度
    public String main;                 // 気象条件
    public String description;          // 天気パラメータ
    public String icon;                 // アイコンのファイル名
    public String date;                 // 日付データ

    /////////////////////////////////
    // 使うかわからないパラメータ  //
    /////////////////////////////////
    public String cloud_all;            // 雲量
    public String wind_speed;           // 風速
    public String wind_deg;             // 風向
    public String rain_3h;              // 過去3時間の雨量
    public String snow_3h;              // 過去3時間の積雪量
}
