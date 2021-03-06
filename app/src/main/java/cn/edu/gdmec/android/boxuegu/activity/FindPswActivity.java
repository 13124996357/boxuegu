package cn.edu.gdmec.android.boxuegu.activity;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import cn.edu.gdmec.android.boxuegu.R;
import cn.edu.gdmec.android.boxuegu.utils.AnalysisUtils;
import cn.edu.gdmec.android.boxuegu.utils.MD5utils;

public class FindPswActivity extends AppCompatActivity {

    private String from;
    private TextView tv_back;
    private TextView tv_main_title;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_psw);
        from = getIntent().getStringExtra("from");
        init();
    }

    private void init() {
        tv_main_title = (TextView)findViewById(R.id.tv_main_title);
        tv_back = (TextView)findViewById(R.id.tv_back);

        final EditText et_validate_name = (EditText)findViewById(R.id.et_validate_name);
        Button btn_validate = (Button)findViewById(R.id.btn_validate);
        final TextView tv_reset_psw = (TextView)findViewById(R.id.tv_reset_psw);
        final EditText et_user_name = (EditText)findViewById(R.id.et_user_name);
        TextView tv_user_name = (TextView)findViewById(R.id.tv_user_name);
        if("security".equals(from)){
            tv_main_title.setText("设置密保");
        }else{
            tv_main_title.setText("找回密码");
            tv_user_name.setVisibility(View.VISIBLE);
            et_user_name.setVisibility(View.VISIBLE);
        }

        btn_validate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String validatename = et_validate_name.getText().toString().trim();
                if ("security".equals(from)) {
                    if (TextUtils.isEmpty(validatename)) {
                        Toast.makeText(FindPswActivity.this, "请输入你要验证的姓名", Toast.LENGTH_SHORT).show();
                        return;
                    }else{
                        Toast.makeText(FindPswActivity.this, "密保设置成功", Toast.LENGTH_SHORT).show();
                        //保存密保到sharedpreference
                        saveSecurity(validatename);
                        FindPswActivity.this.finish();
                    }
                } else {
                    //找回密码
                    String userName = et_user_name.getText().toString().trim();
                    String sp_security = readSecurity(userName);

                    if (TextUtils.isEmpty(userName)){
                        Toast.makeText(FindPswActivity.this, "请输入你的用户名", Toast.LENGTH_SHORT).show();
                        return;
                    }else if(!isExistUserName(userName)){
                        Toast.makeText(FindPswActivity.this, "你输入的用户名不存在", Toast.LENGTH_SHORT).show();
                        return;

                    }else if(TextUtils.isEmpty(validatename)){
                        Toast.makeText(FindPswActivity.this, "你输入你要验证的姓名", Toast.LENGTH_SHORT).show();
                        return;
                    }else if (!validatename.equals(sp_security)){
                        Toast.makeText(FindPswActivity.this, "输入的密保不正确", Toast.LENGTH_SHORT).show();
                        return;
                    }else {
                        tv_reset_psw.setVisibility(View.VISIBLE);
                        tv_reset_psw.setText("初始密码:123456");
                        savePsw(userName);
                    }



                }
            }
        });





    }
//保存初始密码
    private void savePsw(String userName) {
        String md5Psw = MD5utils.md5("123456");
        SharedPreferences sp = getSharedPreferences("loginInfo",MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(userName,md5Psw);
        editor.commit();

    }

    private boolean isExistUserName(String userName) {
        boolean hasUserName = false;
        SharedPreferences sp = getSharedPreferences("loginInfo",MODE_PRIVATE);
        String spPsw = sp.getString(userName,"");
        if(!TextUtils.isEmpty(spPsw)){
            hasUserName = true;
        }
        return hasUserName;
    }

    private String readSecurity(String userName) {
        SharedPreferences sp = getSharedPreferences("loginInfo",MODE_PRIVATE);
        String security = sp.getString(userName + "_security","");
        return security;

    }

    private void saveSecurity(String validatename) {
        SharedPreferences sp = getSharedPreferences("loginInfo",MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(AnalysisUtils.readLoginUserName(this) + "_security",validatename);
        editor.commit();

    }
}
