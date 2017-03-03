package com.example.HealthApplication.application.interfaces.dmaInterface;

import java.sql.Date;
import java.sql.Timestamp;

/**
 * Created by liweihao on 17/3/2.
 */

public interface ISelfRecordDataModel {

    void setData(double data, Date date, int clock);
    double getSingleDataByDateAndTime(Date date,int clock);
    double getAllDateSumByDate(Date date);

}
