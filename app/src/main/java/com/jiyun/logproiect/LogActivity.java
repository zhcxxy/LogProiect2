package com.jiyun.logproiect;

import android.Manifest;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//Pattern.matches(REGEX_EMAIL, email);  正则
public class LogActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText et_name;
    private EditText et_password;
    private Button bt_log;
    private ImageView img_qq;
    private ImageView img_wb;
    private TextView tv_ret;
    private TextView reg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UMShareAPI.get(this);
        setContentView(R.layout.activity_log);

        if (Build.VERSION.SDK_INT >= 23) {
            String[] mPermissionList = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.CALL_PHONE, Manifest.permission.READ_LOGS, Manifest.permission.READ_PHONE_STATE, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.SET_DEBUG_APP, Manifest.permission.SYSTEM_ALERT_WINDOW, Manifest.permission.GET_ACCOUNTS, Manifest.permission.WRITE_APN_SETTINGS};
            ActivityCompat.requestPermissions(this, mPermissionList, 123);
        }

        initView();


    }

    //6.0权限回调处理
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
    }

    private void initView() {
        et_name = (EditText) findViewById(R.id.et_name);
        et_password = (EditText) findViewById(R.id.et_password);
        bt_log = (Button) findViewById(R.id.bt_log);
        img_qq = (ImageView) findViewById(R.id.img_qq);
        img_wb = (ImageView) findViewById(R.id.img_wb);
        tv_ret = (TextView) findViewById(R.id.tv_ret);
        reg = (TextView) findViewById(R.id.reg);

        bt_log.setOnClickListener(this);
        img_qq.setOnClickListener(this);
        img_wb.setOnClickListener(this);
        tv_ret.setOnClickListener(this);
        reg.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_log:
                submit();
                break;
            case R.id.img_qq:
                qqSubmit();
                break;
            case R.id.img_wb:
                wbSubmit();
                break;
            case R.id.tv_ret:

                break;
            case R.id.reg:

                break;
        }
    }

    private void wbSubmit() {
        UMShareAPI.get(LogActivity.this).getPlatformInfo(LogActivity.this, SHARE_MEDIA.SINA, new UMAuthListener() {
            @Override
            public void onStart(SHARE_MEDIA share_media) {

            }

            @Override
            public void onComplete(SHARE_MEDIA share_media, int i, Map<String, String> map) {
                Toast.makeText(LogActivity.this, "新浪微博登录成功", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(SHARE_MEDIA share_media, int i, Throwable throwable) {

            }

            @Override
            public void onCancel(SHARE_MEDIA share_media, int i) {

            }
        });
    }

    private void qqSubmit() {
        UMShareAPI.get(LogActivity.this).getPlatformInfo(LogActivity.this, SHARE_MEDIA.QQ, new UMAuthListener() {
            @Override
            public void onStart(SHARE_MEDIA share_media) {

            }

            @Override
            public void onComplete(SHARE_MEDIA share_media, int i, Map<String, String> map) {
                Toast.makeText(LogActivity.this, "QQ登录成功", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(SHARE_MEDIA share_media, int i, Throwable throwable) {

            }

            @Override
            public void onCancel(SHARE_MEDIA share_media, int i) {

            }
        });
    }

    private void submit() {
        String name = et_name.getText().toString().trim();
        String password = et_password.getText().toString().trim();

        String rename = "^0?(13[0-9]|15[012356789]|17[013678]|18[0-9]|14[57])[0-9]{8}$";
        String reemail = "\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*";


//        if (TextUtils.isEmpty(name)) {
//            Toast.makeText(this, "请输入手机号或邮箱", Toast.LENGTH_SHORT).show();
//        }
//        if (TextUtils.isEmpty(password)) {
//            Toast.makeText(this, "请输入密码", Toast.LENGTH_SHORT).show();
//        } else {

            Pattern p = Pattern.compile(reemail);
            Matcher m = p.matcher(name);
            Pattern p1 = Pattern.compile(rename);
            Matcher m1 = p1.matcher(name);


//        if (Pattern.matches(rename, name) || Pattern.matches(reemail, name)) {
            if (m.find() || m1.find()) {
                Toast.makeText(this, "账号正确", Toast.LENGTH_SHORT).show();
                if (password.length() >= 6 && password.length() <= 16) {
                    Toast.makeText(LogActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(LogActivity.this, "您输入的密码有误", Toast.LENGTH_SHORT).show();

                }
            } else {
                Toast.makeText(LogActivity.this, "您输入的账号不正确", Toast.LENGTH_SHORT).show();
            }


//        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }
}
