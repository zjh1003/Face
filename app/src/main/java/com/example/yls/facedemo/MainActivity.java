package com.example.yls.facedemo;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.baidu.aip.face.AipFace;

import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    private Button btn;
    public static final String APP_ID = "9534332";
    public static final String API_KEY = "1UzMLGhZjt0nwqC8GW8Gj9zM";
    public static final String SECRET_KEY = "j2ZhEoFLgLKDc6nv2CxXFlqi04P14gjP";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        btn = (Button) findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
// 初始化一个FaceClient
                final AipFace client = new AipFace(APP_ID, API_KEY, SECRET_KEY);

                // 可选：设置网络连接参数
                client.setConnectionTimeoutInMillis(2000);
                client.setSocketTimeoutInMillis(60000);

                // 调用API
               /* String image = "test.jpg";
                JSONObject res = client.detect(path, new HashMap<String, String>());
                System.out.println(res.toString(2));*/
                Bitmap bmp = BitmapFactory.decodeResource(getResources(),R.raw.ziyi1);
                 final byte[] imgByte = Bitmap2Bytes(bmp);
                final HashMap<String,String> paraMap = new HashMap<String, String>();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        JSONObject res = client.detect(imgByte,paraMap);
                        Log.e("MainActivity",res.toString());
                    }
                }).start();
            }
        });
    }

    private byte[] Bitmap2Bytes(Bitmap bm) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.PNG,100,baos);
        return baos.toByteArray();
    }
}
