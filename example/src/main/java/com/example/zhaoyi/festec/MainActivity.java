package com.example.zhaoyi.festec;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.activitys.ProxyActivity;
import com.app.Latte;
import com.delegates.LatteDelegate;
import com.example.zhaoyi.latte_ec.launcher.LauncherDelegate;
import com.example.zhaoyi.latte_ec.launcher.LauncherScrollDeleagte;
import com.example.zhaoyi.latte_ec.sign.ISignListener;
import com.example.zhaoyi.latte_ec.sign.SignUpDelegate;
import com.ui.launcher.ILauncherListener;
import com.ui.launcher.OnLauncherFinishTag;

public class MainActivity extends ProxyActivity implements ISignListener ,ILauncherListener{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar =getSupportActionBar();
        if (actionBar != null){
            actionBar.hide();
        }

    }

    @Override
    public LatteDelegate setRootDelegare() {
        Log.i("zhaoyi","MainActivity");
       // return new ExampleDelegate();
       //return new LauncherDelegate();
         return new SignUpDelegate();
    }

    @Override
    public void onSignInSuccess() {

    }

    @Override
    public void onSignUpSuccess() {
        Toast.makeText(this,"注册成功",Toast.LENGTH_LONG).show();
    }

    @Override
    public void onLauncherFinish(OnLauncherFinishTag tag) {
        switch (tag){
            case SIGNED:
                break;
            case NOT_SIGNED:
                break;
            default:
                break;
        }
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
