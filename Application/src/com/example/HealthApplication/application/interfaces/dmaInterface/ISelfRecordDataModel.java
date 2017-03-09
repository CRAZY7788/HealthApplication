package com.example.HealthApplication.application.interfaces.dmaInterface;

import java.sql.Date;
import java.sql.Timestamp;

/**
 * Created by liweihao on 17/3/2.
 */

public interface ISelfRecordDataModel {

    void setData(int id,int data, String date);
    int getSingleDayValue(String date);

}
