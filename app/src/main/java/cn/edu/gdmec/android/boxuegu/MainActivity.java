package cn.edu.gdmec.android.boxuegu;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import cn.edu.gdmec.android.boxuegu.view.CourseView;
import cn.edu.gdmec.android.boxuegu.view.ExercisesView;
import cn.edu.gdmec.android.boxuegu.view.MyInfoView;

public class   MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(data!=null){
            boolean isLogin = data.getBooleanExtra("isLogin",false);
            if(isLogin){
                clearBoottomImageState();
                selectDisplayView(0);


            }
            if(mMyInfoView!=null){
                mMyInfoView.setLoginParams(isLogin);
            }
        }
    }

    private TextView tv_back;
    private TextView tv_main_title;
    private RelativeLayout rl_title_bar;
    private FrameLayout mBodyLayout;
    private LinearLayout mBottomLayout;
    private RelativeLayout mCourseBtn;
    private RelativeLayout  mExercisesBtn;
    private RelativeLayout mMyInfoBtn;
    private TextView tv_course;
    private TextView tv_exercises;
    private TextView tv_myinfo;
    private ImageView iv_course;
    private ImageView iv_exercises;
    private ImageView iv_myinfo;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        initBottomBar();
        setListener();
        setInitStatus();
    }

    private void setInitStatus() {
        clearBoottomImageState();
        setSelectedStatus(0);
        createView(0);

    }

    //设置底部三个点击监听
    private void setListener() {
        for (int i=0;i<mBottomLayout.getChildCount();i++){
            mBottomLayout.getChildAt(i).setOnClickListener(this);
        }



    }

    private void initBottomBar() {
        mBottomLayout = (LinearLayout)findViewById(R.id.main_bottom_bar);
        mCourseBtn = (RelativeLayout)findViewById(R.id.bottom_bar_course_btn);
        mExercisesBtn = (RelativeLayout)findViewById(R.id.bottom_bar_exercises_btn);
        mMyInfoBtn = (RelativeLayout)findViewById(R.id.bottom_bar_myinfo_btn);

        tv_course = (TextView)findViewById(R.id.bottom_bar_text_course);
        tv_exercises = (TextView)findViewById(R.id.bottom_bar_text_exercises);
        tv_myinfo = (TextView)findViewById(R.id.bottom_bar_text_myinfo);

         iv_course = ((ImageView) findViewById(R.id.bottom_bar_image_course));
         iv_exercises = ((ImageView) findViewById(R.id.bottom_bar_image_exercises));
         iv_myinfo = ((ImageView) findViewById(R.id.bottom_bar_image_myinfo));


    }



    private void init() {
        tv_back = ((TextView) findViewById(R.id.tv_back));
        tv_main_title = (TextView) findViewById(R.id.tv_main_title);
        tv_main_title.setText("博学古课程");
        rl_title_bar = (RelativeLayout)findViewById(R.id.title_bar);
        rl_title_bar.setBackgroundColor(Color.parseColor("#30B4FF"));
        tv_back.setVisibility(View.GONE);
        initBodyLayout();
    }

    private void initBodyLayout() {
        mBodyLayout = (FrameLayout)findViewById(R.id.main_body);



    }
    //控件的点击事件

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            //课程的点击事件
            case R.id.bottom_bar_course_btn:
                clearBoottomImageState();
                selectDisplayView(0);
                break;
            //习题的点击事件
            case R.id.bottom_bar_exercises_btn:
                clearBoottomImageState();
                selectDisplayView(1);
                break;
            //我的点击事件
            case R.id.bottom_bar_myinfo_btn:
                clearBoottomImageState();
                selectDisplayView(2);
                break;
        }

    }

    private void selectDisplayView(int index) {
        removeAllView();
        createView(index);
        setSelectedStatus(index);

    }

    private void setSelectedStatus(int index) {
        switch (index){
            case 0:
                mCourseBtn.setSelected(true);
                iv_course.setImageResource(R.drawable.main_course_icon_selected);
                tv_course.setTextColor(Color.parseColor("#0097f7"));
                rl_title_bar.setVisibility(View.VISIBLE);
                tv_main_title.setText("博学古课程");
                break;
            case 1:
                mExercisesBtn.setSelected(true);
                iv_exercises.setImageResource(R.drawable.main_exercises_icon_selected);
                tv_exercises.setTextColor(Color.parseColor("#0097f7"));
                rl_title_bar.setVisibility(View.VISIBLE);
                tv_main_title.setText("博学古习题");
                break;
            case 2:
                mMyInfoBtn.setSelected(true);
                iv_myinfo.setImageResource(R.drawable.main_my_icon_selected);
                tv_myinfo.setTextColor(Color.parseColor("#0097f7"));
                rl_title_bar.setVisibility(View.VISIBLE);
                break;
        }

    }
    private MyInfoView mMyInfoView;
    private ExercisesView mExercisesView;
    private CourseView mCourseView;

    //选择视图

    private void createView(int viewIndex) {
        switch (viewIndex){
            case 0:
                //课程界面
               if(mCourseView == null){
                    mCourseView  = new CourseView(this);
                    mBodyLayout.addView(mCourseView.getView());

                }else{
                    mCourseView.getView();
                }
                mCourseView.showView();

                break;

            case 1:
                //习题界面
                if(mExercisesView == null){
                    mExercisesView  = new ExercisesView(this);
                    mBodyLayout.addView(mExercisesView.getView());

                }else{
                    mExercisesView.getView();
                }
                mExercisesView.showView();

                break;
            case 2:
                //我的界面
                if(mMyInfoView == null){
                    mMyInfoView = new MyInfoView(this);
                    mBodyLayout.addView(mMyInfoView.getView());

                }else{
                    mMyInfoView.getView();
                }
                mMyInfoView.showView();

                break;
        }


        
    }
    //移去不需要的视图

    private void removeAllView() {
        for (int i=0;i<mBodyLayout.getChildCount();i++){
            mBodyLayout.getChildAt(i).setVisibility(View.GONE);
        }
        
    }

    private void clearBoottomImageState() {
        tv_course.setTextColor(Color.parseColor("#666666"));
        tv_exercises.setTextColor(Color.parseColor("#666666"));
        tv_myinfo.setTextColor(Color.parseColor("#666666"));

        iv_course.setImageResource(R.drawable.main_course_icon);
        iv_exercises.setImageResource(R.drawable.main_exercises_icon);
        iv_myinfo.setImageResource(R.drawable.main_my_icon);

        for (int i=0;i<mBottomLayout.getChildCount();i++){
            mBottomLayout.getChildAt(i).setSelected(false);
        }

    }

    protected long exitTime;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK && event.getAction() ==KeyEvent.ACTION_DOWN){
            if((System.currentTimeMillis() - exitTime) > 2000){
                Toast.makeText(this,"再按一次退出博学古",Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
            }else{
                MainActivity.this.finish();
                if(readLoginStatus()){
                    clearLoginStatus();

                }
                System.exit(0);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    private void clearLoginStatus() {
        SharedPreferences sp = getSharedPreferences("loginInfo", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putBoolean("isLogin",false);
        editor.putString("loginUserName","");
        editor.commit();

    }

    private boolean readLoginStatus() {
        SharedPreferences sp = getSharedPreferences("loginInfo", Context.MODE_PRIVATE);
        boolean isLogin = false;
        return isLogin;
    }
}
