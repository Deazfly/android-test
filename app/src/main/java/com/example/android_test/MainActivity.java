package com.example.android_test;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    TextView tv;
    NetworkService ns;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv = findViewById(R.id.textview1);
        ns = new NetworkService();

        final Handler mainThreadHandler = new Handler();



        ns.sendRequest(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Document doc = Jsoup.parse(response.body().string());
                Elements elements = doc.getElementsByClass("first_page_week col2_right_conteiner_item");
                Element element = elements.first();
                mainThreadHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        tv.setText(element.text());
                    }
                });
            }
        });


        if (savedInstanceState != null) {
            tv.setText(savedInstanceState.getString("tv1"));
        }
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("tv1", tv.getText().toString());
    }
}
