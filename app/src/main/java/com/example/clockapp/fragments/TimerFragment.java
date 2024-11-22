package com.example.clockapp.fragments;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.clockapp.R;

public class TimerFragment extends Fragment {

    private TextView tvTime;
    private Button btnStart, btnPause, btnReset, btnResume;

    private Handler handler = new Handler();
    private long startTime = 0;
    private boolean isRunning = false;
    private Runnable updateTimer;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_timer, container, false);

        // Liên kết các thành phần giao diện
        tvTime = view.findViewById(R.id.tvTime);
        btnStart = view.findViewById(R.id.btnStart);
        btnPause = view.findViewById(R.id.btnPause);
        btnReset = view.findViewById(R.id.btnReset);
        btnResume = view.findViewById(R.id.btnResume);

        // Xử lý nút Bắt đầu
        btnStart.setOnClickListener(v -> startTimer());

        // Xử lý nút Tạm dừng
        btnPause.setOnClickListener(v -> pauseTimer());

        // Xử lý nút Đặt lại
        btnReset.setOnClickListener(v -> resetTimer());

        // Xử lý nút Tiếp tục
        btnResume.setOnClickListener(v -> resumeTimer());

        return view;
    }


    private void startTimer() {
        startTime = System.currentTimeMillis();
        isRunning = true;

        // Ẩn nút Bắt đầu, hiện nút Tạm dừng
        btnStart.setVisibility(View.GONE);
        btnPause.setVisibility(View.VISIBLE);

        // Cập nhật thời gian mỗi giây
        updateTimer = new Runnable() {
            @Override
            public void run() {
                if (isRunning) {
                    long currentTime = System.currentTimeMillis() - startTime;
                    int seconds = (int) (currentTime / 1000) % 60;
                    int minutes = (int) ((currentTime / (1000 * 60)) % 60);
                    int hours = (int) ((currentTime / (1000 * 60 * 60)) % 24);
                    tvTime.setText(String.format("%02d:%02d:%02d", hours, minutes, seconds));
                    handler.postDelayed(this, 1000);
                }
            }
        };
        handler.post(updateTimer);
    }

    private void pauseTimer() {
        isRunning = false;
        btnPause.setVisibility(View.GONE);
        btnResume.setVisibility(View.VISIBLE);
        btnReset.setVisibility(View.VISIBLE);
    }

    private void resetTimer() {
        isRunning = false;
        startTime = 0;
        tvTime.setText("00:00:00");

        btnStart.setVisibility(View.VISIBLE);
        btnPause.setVisibility(View.GONE);
        btnReset.setVisibility(View.GONE);
        btnResume.setVisibility(View.GONE);
    }

    private void resumeTimer() {
        startTime = System.currentTimeMillis() - getTimeElapsed();
        isRunning = true;

        btnResume.setVisibility(View.GONE);
        btnReset.setVisibility(View.GONE);
        btnPause.setVisibility(View.VISIBLE);

        handler.post(updateTimer);
    }

    private long getTimeElapsed() {
        String[] timeParts = tvTime.getText().toString().split(":");
        int hours = Integer.parseInt(timeParts[0]);
        int minutes = Integer.parseInt(timeParts[1]);
        int seconds = Integer.parseInt(timeParts[2]);
        return (hours * 3600 + minutes * 60 + seconds) * 1000;
    }
}
