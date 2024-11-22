package com.example.clockapp.activity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.clockapp.R;

public class Alarm_active extends AppCompatActivity {
    private TextView tv;
    private Button bt;
    private MediaPlayer play;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_alarm_active);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
//        tv = this.findViewById(R.id.tvTime);
//        bt = this.findViewById(R.id.btOff);
//        Bundle b = getIntent().getExtras();
//        alarm a = (alarm) b.getSerializable("alarm");
//
//        //phat nhac
////        try {
////            play.setDataSource(this, MediaStore.Audio.Media.INTERNAL_CONTENT_URI);
////            play.prepare();
////        } catch (IOException e) {
////            throw new RuntimeException(e);
////        }
////        play.start();
//
//        tv.setText(a.getTime().toString());
//
//        bt.setOnClickListener(v -> {
//            play.stop();
//            play.release();
//
//            finish();
//                }
//        );
    }
//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        play.stop();
//        play.release();
//    }

}