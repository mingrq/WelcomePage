package com.ming.welcomepage;

import android.icu.text.IDNA;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.ming.welcome_lib.Info;
import com.ming.welcome_lib.WelcomePage;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        WelcomePage welcomePage = findViewById(R.id.welcome);

        List<Info> mList = new ArrayList<>();
        Info info = new Info();
        info.setBannerUri("https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=2097124721,3074829049&fm=27&gp=0.jpg");
        Info info1 = new Info();
        info1.setBannerUri("https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=3034670085,1677733933&fm=27&gp=0.jpg");
        Info info2 = new Info();
        info2.setBannerUri("http://imgsrc.baidu.com/forum/pic/item/b64543a98226cffc8872e00cb9014a90f603ea30.jpg");
        Info info3 = new Info();
        info3.setBannerUri("http://imgsrc.baidu.com/forum/pic/item/261bee0a19d8bc3e6db92913828ba61eaad345d4.jpg");
        mList.add(info);
        mList.add(info1);
        mList.add(info2);
        mList.add(info3);
        welcomePage.setData(mList);
        welcomePage.commit();

    }
}
