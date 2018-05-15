package com.example.zhaoyi.festec;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.activitys.ProxyActivity;
import com.app.Latte;
import com.delegates.LatteDelegate;

public class MainActivity extends ProxyActivity{
    @Override
    public LatteDelegate setRootDelegare() {
        Log.i("zhaoyi","MainActivity");
        return new ExampleDelegate();
    }
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        //哈哈
//        setContentView(R.layout.activity_main);
//        Toast.makeText(Latte.getApplication(),"穿入Context啦",Toast.LENGTH_LONG).show();
//
//    }
}
