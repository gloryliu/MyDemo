package com.liu.glory.rollview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.liu.demo.view.RollView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RollView rollview;
    private List<String> messages = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rollview = (RollView) findViewById(R.id.rollview);
        initData();
        rollview.setAdapter(new MyMessageAdapter(this,messages));
    }
    private void initData(){
        messages.add("推荐分类");
        messages.add("潮流女装");
        messages.add("品牌男装");
        messages.add("个护化妆");
        messages.add("家用电器");
        messages.add("电脑办公");
        messages.add("手机数码");
        messages.add("母婴童装");
        messages.add("图书音像");
        messages.add("家居家纺");
        messages.add("居家生活");
        messages.add("家具建材");
        messages.add("食品生鲜");
        messages.add("推荐分类");
        messages.add("潮流女装");
        messages.add("品牌男装");
        messages.add("个护化妆");
        messages.add("家用电器");
        messages.add("电脑办公");
        messages.add("手机数码");
        messages.add("母婴童装");
        messages.add("图书音像");
        messages.add("家居家纺");
        messages.add("居家生活");
        messages.add("家具建材");
        messages.add("食品生鲜");
    }
}
