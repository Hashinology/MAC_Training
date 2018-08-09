package com.example.jasongomez.threadpoolexecutorpractice;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.lang.ref.WeakReference;

public class MainActivity extends AppCompatActivity {

    private ProgressBar progressBar1, progressBar2, progressBar3, progressBar4;
    private TextView textView1, textView2, textView3, textView4;
    private UiHandler uiHandler;
    private CustomThreadPoolManager customThreadPoolManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView1 = findViewById(R.id.tvThread1);
        textView2 = findViewById(R.id.tvThread2);
        textView3 = findViewById(R.id.tvThread3);
        textView4 = findViewById(R.id.tvThread4);
        progressBar1 = findViewById(R.id.progress1);
        progressBar2 = findViewById(R.id.progress2);
        progressBar3 = findViewById(R.id.progress3);
        progressBar4 = findViewById(R.id.progress4);

        uiHandler = new UiHandler(getMainLooper(),
                textView1,
                textView2,
                textView3,
                textView4,
                progressBar1,
                progressBar2,
                progressBar3,
                progressBar4);

        customThreadPoolManager = CustomThreadPoolManager.getInstance();
        customThreadPoolManager.setHandler(uiHandler);
    }

    public void addCallable(View view) {
        customThreadPoolManager.addRunnable();
    }

    public static class UiHandler extends Handler {

        private WeakReference<TextView> tvFirstThread, tvSecondThread, tvThirdThread, tvFourthThread;
        private WeakReference<ProgressBar> firstProgress, secondProgress, thirdProgress, fourthProgress;

        public UiHandler(Looper looper,
                         TextView tvFirstThread,
                         TextView tvSecondThread,
                         TextView tvThirdThread,
                         TextView tvFourthThread,
                         ProgressBar firstProgress,
                         ProgressBar secondProgress,
                         ProgressBar thirdProgress,
                         ProgressBar fourthProgress) {

            super(looper);
            this.tvFirstThread = new WeakReference<>(tvFirstThread);
            this.tvSecondThread = new WeakReference<>(tvSecondThread);
            this.tvThirdThread = new WeakReference<>(tvThirdThread);
            this.tvFourthThread = new WeakReference<>(tvFourthThread);
            this.firstProgress = new WeakReference<>(firstProgress);
            this.secondProgress = new WeakReference<>(secondProgress);
            this.thirdProgress = new WeakReference<>(thirdProgress);
            this.fourthProgress = new WeakReference<>(fourthProgress);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            switch (msg.what) {
                case 1:
                    firstProgress.get().setProgress(msg.getData().getInt("int"));
                    tvFirstThread.get().setText(msg.getData().getString("message"));
                    break;

                case 2:
                    secondProgress.get().setProgress(msg.getData().getInt("int"));
                    tvSecondThread.get().setText(msg.getData().getString("message"));
                    break;

                case 3:
                    thirdProgress.get().setProgress(msg.getData().getInt("int"));
                    tvThirdThread.get().setText(msg.getData().getString("message"));
                    break;

                case 4:
                    fourthProgress.get().setProgress(msg.getData().getInt("int"));
                    tvFourthThread.get().setText(msg.getData().getString("message"));
                    break;
            }
        }
    }
}
