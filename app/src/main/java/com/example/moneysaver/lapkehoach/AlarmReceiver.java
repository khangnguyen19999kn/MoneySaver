
package com.example.moneysaver.lapkehoach;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.example.moneysaver.FirstPage;
import com.example.moneysaver.ThemKeHoach;
import com.example.moneysaver.datasource.ChiTieuDataSource;

public class AlarmReceiver extends BroadcastReceiver {
    private ChiTieuDataSource chiTieuDataSource;
    @Override
    public void onReceive(Context context, Intent intent) {
        chiTieuDataSource = new ChiTieuDataSource(context);
        int tien = Integer.parseInt(intent.getExtras().getString("tien"));
        String ngay = intent.getExtras().getString("ngay");
        String ghiChu = intent.getExtras().getString("ghichu");
        intent.getExtras().getString("ngay");
        intent.getExtras().getString("ghichu");
        chiTieuDataSource.createChiTieu(1001,tien,
                ngay,ghiChu,"user1vi1");
//        Intent intent2 = new Intent(AlarmReceiver.this, FirstPage.class);


    }
}