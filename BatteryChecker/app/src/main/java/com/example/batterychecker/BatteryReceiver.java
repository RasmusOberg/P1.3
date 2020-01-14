package com.example.batterychecker;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.BatteryManager;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.w3c.dom.Text;

public class BatteryReceiver extends BroadcastReceiver {

    //Called every time the OS sends a broadcast
    public void onReceive(Context context, Intent intent){
        TextView tvStatus = ((MainActivity)context).findViewById(R.id.tvStatus);
        TextView tvPercentage = ((MainActivity)context).findViewById(R.id.tvPercentage);
        ImageView ivBattery = ((MainActivity)context).findViewById(R.id.ivBattery);
        ProgressBar progress = ((MainActivity)context).findViewById(R.id.progress);
        TextView tvTest = ((MainActivity)context).findViewById(R.id.tvTest);

        String action = intent.getAction();
        if(action != null && action.equals(Intent.ACTION_BATTERY_CHANGED)){
            //Status textview
            int status = intent.getIntExtra(BatteryManager.EXTRA_STATUS, -1);
            String message = "";
            switch (status){
                case BatteryManager.BATTERY_STATUS_FULL:
                    message = "FULL";
                    break;
                case BatteryManager.BATTERY_STATUS_CHARGING:
                    message = "CHARGING";
                    break;
                case BatteryManager.BATTERY_STATUS_DISCHARGING:
                    message = "DISCHARGING";
                    break;
                case BatteryManager.BATTERY_STATUS_NOT_CHARGING:
                    message = "NOT CHARGING";
                    break;
                case BatteryManager.BATTERY_STATUS_UNKNOWN:
                    message ="UNKNOWN";
                    break;
            }
            tvStatus.setText(message);

            //Percentage
            int level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
            int scale = intent.getIntExtra(BatteryManager.EXTRA_SCALE, -1);
            int percentage = level * 100 / scale;
            tvPercentage.setText(percentage + "%");

            //Image
            Resources res = context.getResources();
            if(percentage >= 90)
                ivBattery.setImageDrawable(res.getDrawable(R.drawable.b100));
            else if(90 > percentage && percentage >= 65)
                ivBattery.setImageDrawable(res.getDrawable(R.drawable.b75));
            else if(65 > percentage && percentage >= 40)
                ivBattery.setImageDrawable(res.getDrawable(R.drawable.b50));
            else if(40 > percentage && percentage >= 15)
                ivBattery.setImageDrawable(res.getDrawable(R.drawable.b25));
            else
                ivBattery.setImageDrawable(res.getDrawable(R.drawable.b0));

            //ProgressBar
            progress.setProgress(percentage);
            if(percentage == 100)
                tvTest.setText("Fullt!");
            else
                tvTest.setText("Laddar!");
        }
    }

}
