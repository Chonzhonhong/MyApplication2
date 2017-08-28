package com.example.czhgzsk.czhtotiao.Activity;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.TextView;

import com.example.czhgzsk.czhtotiao.R;
import com.example.czhgzsk.czhtotiao.abapter.MFragmentPagerAdapter;
import com.example.czhgzsk.czhtotiao.fragment.CaijinFragment;
import com.example.czhgzsk.czhtotiao.fragment.GuojiFragment;
import com.example.czhgzsk.czhtotiao.fragment.GuoneiFragment;
import com.example.czhgzsk.czhtotiao.fragment.JunshiFragment;
import com.example.czhgzsk.czhtotiao.fragment.KejiFragment;
import com.example.czhgzsk.czhtotiao.fragment.ShehuiFragment;
import com.example.czhgzsk.czhtotiao.fragment.ShishangFragment;
import com.example.czhgzsk.czhtotiao.fragment.TiyuFragment;
import com.example.czhgzsk.czhtotiao.fragment.ToutiaoFragment;
import com.example.czhgzsk.czhtotiao.fragment.YuleFragment;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;

/**
 * Created by czhgzsk on 17-8-25.
 */

@ContentView(R.layout.home_activity)
public class HomeActivity extends FragmentActivity {
//：返回头条，社会，国内，娱乐，体育，军事，科技，财经，时尚等新闻信息
    @ViewInject(R.id.tv_hom_toutiao)
    private TextView tvTouTiao;
    @ViewInject(R.id.tv_hom_shehui)
    private TextView tvShehui;
    @ViewInject(R.id.tv_hom_guonei)
    private TextView tvGuonei;
    @ViewInject(R.id.tv_hom_guoji)
    private TextView tvGuoji;
    @ViewInject(R.id.tv_hom_yule)
    private TextView tvYule;
    @ViewInject(R.id.tv_hom_tieyu)
    private TextView tvTieyu;
    @ViewInject(R.id.tv_hom_junshi)
    private TextView tvJunshi;
    @ViewInject(R.id.tv_hom_keji)
    private TextView tvKeji;
    @ViewInject(R.id.tv_hom_caijin)
    private TextView tvCaijin;
    @ViewInject(R.id.tv_hom_shishang)
    private TextView tvShesahng;

    @ViewInject(R.id.vp_hom_pag)
    private ViewPager viewPager;


    //当前页卡编号
    private int currIndex = 0;
    //动画图片偏移量
    private int offset = 0;
    private int position_one;
    private int position_two;
    //存放Fragment
    private ArrayList<Fragment> fragmentArrayList;

    //管理Fragment
    private FragmentManager fragmentManager;

    public Context context;

    public static final String TAG = "HomeActivity";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        x.view().inject(this);
        dainJi();
        InitFragment();

        //初始化ViewPager
        InitViewPager();
    }

    private void dainJi() {
        //：返回头条，社会，国内，娱乐，体育，军事，科技，财经，时尚等新闻信息
        //添加点击事件
        tvTouTiao.setOnClickListener(new MyOnClickListener(0));
        tvShehui.setOnClickListener(new MyOnClickListener(1));
        tvGuonei.setOnClickListener(new MyOnClickListener(2));
        tvGuoji.setOnClickListener(new MyOnClickListener(3));
        tvYule.setOnClickListener(new MyOnClickListener(4));
        tvTieyu.setOnClickListener(new MyOnClickListener(5));
        tvJunshi.setOnClickListener(new MyOnClickListener(6));
        tvKeji.setOnClickListener(new MyOnClickListener(7));
        tvCaijin.setOnClickListener(new MyOnClickListener(8));
        tvShesahng.setOnClickListener(new MyOnClickListener(9));
    }


    private void InitFragment(){
        fragmentArrayList = new ArrayList<Fragment>();
        fragmentArrayList.add(new ToutiaoFragment());
        fragmentArrayList.add(new ShehuiFragment());
        fragmentArrayList.add(new JunshiFragment());
        fragmentArrayList.add(new CaijinFragment());
        fragmentArrayList.add(new GuoneiFragment());
        fragmentArrayList.add(new KejiFragment());
        fragmentArrayList.add(new TiyuFragment());
        fragmentArrayList.add(new YuleFragment());
        fragmentArrayList.add(new GuojiFragment());
        fragmentArrayList.add(new ShishangFragment());
        fragmentManager = getSupportFragmentManager();


    }
    private void resetTextViewTextColor(){
        tvCaijin.setTextColor(getResources().getColor(R.color.huise));
        tvGuoji.setTextColor(getResources().getColor(R.color.huise));
        tvGuonei.setTextColor(getResources().getColor(R.color.huise));
        tvJunshi.setTextColor(getResources().getColor(R.color.huise));
        tvKeji.setTextColor(getResources().getColor(R.color.huise));
        tvShehui.setTextColor(getResources().getColor(R.color.huise));
        tvShesahng.setTextColor(getResources().getColor(R.color.huise));
        tvTieyu.setTextColor(getResources().getColor(R.color.huise));
        tvTouTiao.setTextColor(getResources().getColor(R.color.huise));
        tvYule.setTextColor(getResources().getColor(R.color.huise));
    }
    /**
     * 初始化页卡内容区
     */
    private void InitViewPager() {

        viewPager = (ViewPager) findViewById(R.id.vp_hom_pag);
        viewPager.setAdapter(new MFragmentPagerAdapter(fragmentManager, fragmentArrayList));

        //让ViewPager缓存2个页面
        viewPager.setOffscreenPageLimit(4);

        //设置默认打开第一页
        viewPager.setCurrentItem(0);

        //将顶部文字恢复默认值
        resetTextViewTextColor();
        tvTouTiao.setTextColor(getResources().getColor(R.color.heise));

        //设置viewpager页面滑动监听事件
        viewPager.setOnPageChangeListener(new MyOnPageChangeListener());
    }
    /**
     * 头标点击监听
     * @author weizhi
     * @version 1.0
     */
    public class MyOnClickListener implements View.OnClickListener{
        private int index = 0 ;
        public MyOnClickListener(int i) {
            index = i;
        }

        @Override
        public void onClick(View v) {
            viewPager.setCurrentItem(index);
        }
    }

    /**
     * 页卡切换监听
     * @author weizhi
     * @version 1.0
     */
    public class MyOnPageChangeListener implements ViewPager.OnPageChangeListener{

        @Override
        public void onPageSelected(int position) {
            Animation animation = null ;
            switch (position){
                //：返回头条，社会，国内，娱乐，体育，军事，科技，财经，时尚等新闻信息
                //当前为页卡1
                case 0:
                    switch (currIndex){

                        case 1:
                            animation = new TranslateAnimation(position_one, 0, 0, 0);
                            resetTextViewTextColor();
                            tvTouTiao.setTextColor(getResources().getColor(R.color.heise));
                            break;
                        case 2:
                            animation = new TranslateAnimation(position_one, 0, 0, 0);
                            resetTextViewTextColor();
                            tvTouTiao.setTextColor(getResources().getColor(R.color.heise));
                            break;
                        case 3:
                            animation = new TranslateAnimation(position_one, 0, 0, 0);
                            resetTextViewTextColor();
                            tvTouTiao.setTextColor(getResources().getColor(R.color.heise));
                            break;
                        case 4:
                            animation = new TranslateAnimation(position_one, 0, 0, 0);
                            resetTextViewTextColor();
                            tvTouTiao.setTextColor(getResources().getColor(R.color.heise));
                            break;
                        case 5:
                            animation = new TranslateAnimation(position_one, 0, 0, 0);
                            resetTextViewTextColor();
                            tvTouTiao.setTextColor(getResources().getColor(R.color.heise));
                            break;
                        case 6:
                            animation = new TranslateAnimation(position_one, 0, 0, 0);
                            resetTextViewTextColor();
                            tvTouTiao.setTextColor(getResources().getColor(R.color.heise));
                            break;
                        case 7:
                            animation = new TranslateAnimation(position_one, 0, 0, 0);
                            resetTextViewTextColor();
                            tvTouTiao.setTextColor(getResources().getColor(R.color.heise));
                            break;
                        case 8:
                            animation = new TranslateAnimation(position_one, 0, 0, 0);
                            resetTextViewTextColor();
                            tvTouTiao.setTextColor(getResources().getColor(R.color.heise));
                            break;
                        case 9:
                            animation = new TranslateAnimation(position_one, 0, 0, 0);
                            resetTextViewTextColor();
                            tvTouTiao.setTextColor(getResources().getColor(R.color.heise));
                            break;

                    }

                    break;
                //：返回头条，社会，国内，娱乐，体育，军事，科技，财经，时尚等新闻信息
                //当前为页卡3
                case 1:
                    switch (currIndex){

                        case 0:
                            animation = new TranslateAnimation(position_one, 0, 0, 0);
                            resetTextViewTextColor();
                            tvShehui.setTextColor(getResources().getColor(R.color.heise));
                            break;

                        case 2:
                            animation = new TranslateAnimation(position_one, 0, 0, 0);
                            resetTextViewTextColor();
                            tvShehui.setTextColor(getResources().getColor(R.color.heise));
                            break;
                        case 3:
                            animation = new TranslateAnimation(position_one, 0, 0, 0);
                            resetTextViewTextColor();
                            tvShehui.setTextColor(getResources().getColor(R.color.heise));
                            break;
                        case 4:
                            animation = new TranslateAnimation(position_one, 0, 0, 0);
                            resetTextViewTextColor();
                            tvShehui.setTextColor(getResources().getColor(R.color.heise));
                            break;
                        case 5:
                            animation = new TranslateAnimation(position_one, 0, 0, 0);
                            resetTextViewTextColor();
                            tvShehui.setTextColor(getResources().getColor(R.color.heise));
                            break;
                        case 6:
                            animation = new TranslateAnimation(position_one, 0, 0, 0);
                            resetTextViewTextColor();
                            tvShehui.setTextColor(getResources().getColor(R.color.heise));
                            break;
                        case 7:
                            animation = new TranslateAnimation(position_one, 0, 0, 0);
                            resetTextViewTextColor();
                            tvShehui.setTextColor(getResources().getColor(R.color.heise));
                            break;
                        case 8:
                            animation = new TranslateAnimation(position_one, 0, 0, 0);
                            resetTextViewTextColor();
                            tvShehui.setTextColor(getResources().getColor(R.color.heise));
                            break;
                        case 9:
                            animation = new TranslateAnimation(position_one, 0, 0, 0);
                            resetTextViewTextColor();
                            tvShehui.setTextColor(getResources().getColor(R.color.heise));
                            break;

                    }

                    break;
                //当前为页卡3
//                国内
                case 2:
                    switch (currIndex){
                        case 0:
                            animation = new TranslateAnimation(position_one, 0, 0, 0);
                            resetTextViewTextColor();
                            tvGuonei.setTextColor(getResources().getColor(R.color.heise));
                            break;
                        case 1:
                            animation = new TranslateAnimation(position_one, 0, 0, 0);
                            resetTextViewTextColor();
                            tvGuonei.setTextColor(getResources().getColor(R.color.heise));
                            break;

                        case 3:
                            animation = new TranslateAnimation(position_one, 0, 0, 0);
                            resetTextViewTextColor();
                            tvGuonei.setTextColor(getResources().getColor(R.color.heise));
                            break;
                        case 4:
                            animation = new TranslateAnimation(position_one, 0, 0, 0);
                            resetTextViewTextColor();
                            tvGuonei.setTextColor(getResources().getColor(R.color.heise));
                            break;
                        case 5:
                            animation = new TranslateAnimation(position_one, 0, 0, 0);
                            resetTextViewTextColor();
                            tvGuonei.setTextColor(getResources().getColor(R.color.heise));
                            break;
                        case 6:
                            animation = new TranslateAnimation(position_one, 0, 0, 0);
                            resetTextViewTextColor();
                            tvGuonei.setTextColor(getResources().getColor(R.color.heise));
                            break;
                        case 7:
                            animation = new TranslateAnimation(position_one, 0, 0, 0);
                            resetTextViewTextColor();
                            tvGuonei.setTextColor(getResources().getColor(R.color.heise));
                            break;
                        case 8:
                            animation = new TranslateAnimation(position_one, 0, 0, 0);
                            resetTextViewTextColor();
                            tvGuonei.setTextColor(getResources().getColor(R.color.heise));
                            break;
                        case 9:
                            animation = new TranslateAnimation(position_one, 0, 0, 0);
                            resetTextViewTextColor();
                            tvGuonei.setTextColor(getResources().getColor(R.color.heise));
                            break;

                    }

                    break;
                //当前为页卡3
//                国际
                case 3:
                    switch (currIndex){
                        case 0:
                            animation = new TranslateAnimation(position_one, 0, 0, 0);
                            resetTextViewTextColor();
                            tvGuoji.setTextColor(getResources().getColor(R.color.heise));
                            break;
                        case 1:
                            animation = new TranslateAnimation(position_one, 0, 0, 0);
                            resetTextViewTextColor();
                            tvGuoji.setTextColor(getResources().getColor(R.color.heise));
                            break;
                        case 2:
                            animation = new TranslateAnimation(position_one, 0, 0, 0);
                            resetTextViewTextColor();
                            tvGuoji.setTextColor(getResources().getColor(R.color.heise));
                            break;

                        case 4:
                            animation = new TranslateAnimation(position_one, 0, 0, 0);
                            resetTextViewTextColor();
                            tvGuoji.setTextColor(getResources().getColor(R.color.heise));
                            break;
                        case 5:
                            animation = new TranslateAnimation(position_one, 0, 0, 0);
                            resetTextViewTextColor();
                            tvGuoji.setTextColor(getResources().getColor(R.color.heise));
                            break;
                        case 6:
                            animation = new TranslateAnimation(position_one, 0, 0, 0);
                            resetTextViewTextColor();
                            tvGuoji.setTextColor(getResources().getColor(R.color.heise));
                            break;
                        case 7:
                            animation = new TranslateAnimation(position_one, 0, 0, 0);
                            resetTextViewTextColor();
                            tvGuoji.setTextColor(getResources().getColor(R.color.heise));
                            break;
                        case 8:
                            animation = new TranslateAnimation(position_one, 0, 0, 0);
                            resetTextViewTextColor();
                            tvGuoji.setTextColor(getResources().getColor(R.color.heise));
                            break;
                        case 9:
                            animation = new TranslateAnimation(position_one, 0, 0, 0);
                            resetTextViewTextColor();
                            tvGuoji.setTextColor(getResources().getColor(R.color.heise));
                            break;

                    }

                    break;
                //当前为页卡3
//                娱乐
                case 4:
                    switch (currIndex){
                        case 0:
                            animation = new TranslateAnimation(position_one, 0, 0, 0);
                            resetTextViewTextColor();
                            tvYule.setTextColor(getResources().getColor(R.color.heise));
                            break;
                        case 1:
                            animation = new TranslateAnimation(position_one, 0, 0, 0);
                            resetTextViewTextColor();
                            tvYule.setTextColor(getResources().getColor(R.color.heise));
                            break;
                        case 2:
                            animation = new TranslateAnimation(position_one, 0, 0, 0);
                            resetTextViewTextColor();
                            tvYule.setTextColor(getResources().getColor(R.color.heise));
                            break;
                        case 3:
                            animation = new TranslateAnimation(position_one, 0, 0, 0);
                            resetTextViewTextColor();
                            tvYule.setTextColor(getResources().getColor(R.color.heise));
                            break;

                        case 5:
                            animation = new TranslateAnimation(position_one, 0, 0, 0);
                            resetTextViewTextColor();
                            tvYule.setTextColor(getResources().getColor(R.color.heise));
                            break;
                        case 6:
                            animation = new TranslateAnimation(position_one, 0, 0, 0);
                            resetTextViewTextColor();
                            tvYule.setTextColor(getResources().getColor(R.color.heise));
                            break;
                        case 7:
                            animation = new TranslateAnimation(position_one, 0, 0, 0);
                            resetTextViewTextColor();
                            tvYule.setTextColor(getResources().getColor(R.color.heise));
                            break;
                        case 8:
                            animation = new TranslateAnimation(position_one, 0, 0, 0);
                            resetTextViewTextColor();
                            tvYule.setTextColor(getResources().getColor(R.color.heise));
                            break;
                        case 9:
                            animation = new TranslateAnimation(position_one, 0, 0, 0);
                            resetTextViewTextColor();
                            tvYule.setTextColor(getResources().getColor(R.color.heise));
                            break;

                    }

                    break;
                //当前为页卡3
//                体育
                case 5:
                    switch (currIndex){
                        case 0:
                            animation = new TranslateAnimation(position_one, 0, 0, 0);
                            resetTextViewTextColor();
                            tvTieyu.setTextColor(getResources().getColor(R.color.heise));
                            break;
                        case 1:
                            animation = new TranslateAnimation(position_one, 0, 0, 0);
                            resetTextViewTextColor();
                            tvTieyu.setTextColor(getResources().getColor(R.color.heise));
                            break;
                        case 2:
                            animation = new TranslateAnimation(position_one, 0, 0, 0);
                            resetTextViewTextColor();
                            tvTieyu.setTextColor(getResources().getColor(R.color.heise));
                            break;
                        case 3:
                            animation = new TranslateAnimation(position_one, 0, 0, 0);
                            resetTextViewTextColor();
                            tvTieyu.setTextColor(getResources().getColor(R.color.heise));
                            break;
                        case 4:
                            animation = new TranslateAnimation(position_one, 0, 0, 0);
                            resetTextViewTextColor();
                            tvTieyu.setTextColor(getResources().getColor(R.color.heise));
                            break;

                        case 6:
                            animation = new TranslateAnimation(position_one, 0, 0, 0);
                            resetTextViewTextColor();
                            tvTieyu.setTextColor(getResources().getColor(R.color.heise));
                            break;
                        case 7:
                            animation = new TranslateAnimation(position_one, 0, 0, 0);
                            resetTextViewTextColor();
                            tvTieyu.setTextColor(getResources().getColor(R.color.heise));
                            break;
                        case 8:
                            animation = new TranslateAnimation(position_one, 0, 0, 0);
                            resetTextViewTextColor();
                            tvTieyu.setTextColor(getResources().getColor(R.color.heise));
                            break;
                        case 9:
                            animation = new TranslateAnimation(position_one, 0, 0, 0);
                            resetTextViewTextColor();
                            tvTieyu.setTextColor(getResources().getColor(R.color.heise));
                            break;

                    }

                    break;
                //当前为页卡3
//                军事
                case 6:
                    switch (currIndex){
                        case 0:
                            animation = new TranslateAnimation(position_one, 0, 0, 0);
                            resetTextViewTextColor();
                            tvJunshi.setTextColor(getResources().getColor(R.color.heise));
                            break;
                        case 1:
                            animation = new TranslateAnimation(position_one, 0, 0, 0);
                            resetTextViewTextColor();
                            tvJunshi.setTextColor(getResources().getColor(R.color.heise));
                            break;
                        case 2:
                            animation = new TranslateAnimation(position_one, 0, 0, 0);
                            resetTextViewTextColor();
                            tvJunshi.setTextColor(getResources().getColor(R.color.heise));
                            break;
                        case 3:
                            animation = new TranslateAnimation(position_one, 0, 0, 0);
                            resetTextViewTextColor();
                            tvJunshi.setTextColor(getResources().getColor(R.color.heise));
                            break;
                        case 4:
                            animation = new TranslateAnimation(position_one, 0, 0, 0);
                            resetTextViewTextColor();
                            tvJunshi.setTextColor(getResources().getColor(R.color.heise));
                            break;
                        case 5:
                            animation = new TranslateAnimation(position_one, 0, 0, 0);
                            resetTextViewTextColor();
                            tvJunshi.setTextColor(getResources().getColor(R.color.heise));
                            break;

                        case 7:
                            animation = new TranslateAnimation(position_one, 0, 0, 0);
                            resetTextViewTextColor();
                            tvJunshi.setTextColor(getResources().getColor(R.color.heise));
                            break;
                        case 8:
                            animation = new TranslateAnimation(position_one, 0, 0, 0);
                            resetTextViewTextColor();
                            tvJunshi.setTextColor(getResources().getColor(R.color.heise));
                            break;
                        case 9:
                            animation = new TranslateAnimation(position_one, 0, 0, 0);
                            resetTextViewTextColor();
                            tvJunshi.setTextColor(getResources().getColor(R.color.heise));
                            break;

                    }

                    break;
                //当前为页卡3
//                科技
                case 7:
                    switch (currIndex){
                        case 0:
                            animation = new TranslateAnimation(position_one, 0, 0, 0);
                            resetTextViewTextColor();
                            tvKeji.setTextColor(getResources().getColor(R.color.heise));
                            break;
                        case 1:
                            animation = new TranslateAnimation(position_one, 0, 0, 0);
                            resetTextViewTextColor();
                            tvKeji.setTextColor(getResources().getColor(R.color.heise));
                            break;
                        case 2:
                            animation = new TranslateAnimation(position_one, 0, 0, 0);
                            resetTextViewTextColor();
                            tvKeji.setTextColor(getResources().getColor(R.color.heise));
                            break;
                        case 3:
                            animation = new TranslateAnimation(position_one, 0, 0, 0);
                            resetTextViewTextColor();
                            tvKeji.setTextColor(getResources().getColor(R.color.heise));
                            break;
                        case 4:
                            animation = new TranslateAnimation(position_one, 0, 0, 0);
                            resetTextViewTextColor();
                            tvKeji.setTextColor(getResources().getColor(R.color.heise));
                            break;
                        case 5:
                            animation = new TranslateAnimation(position_one, 0, 0, 0);
                            resetTextViewTextColor();
                            tvKeji.setTextColor(getResources().getColor(R.color.heise));
                            break;
                        case 6:
                            animation = new TranslateAnimation(position_one, 0, 0, 0);
                            resetTextViewTextColor();
                            tvKeji.setTextColor(getResources().getColor(R.color.heise));
                            break;

                        case 8:
                            animation = new TranslateAnimation(position_one, 0, 0, 0);
                            resetTextViewTextColor();
                            tvKeji.setTextColor(getResources().getColor(R.color.heise));
                            break;
                        case 9:
                            animation = new TranslateAnimation(position_one, 0, 0, 0);
                            resetTextViewTextColor();
                            tvKeji.setTextColor(getResources().getColor(R.color.heise));
                            break;

                    }

                    break;
                //当前为页卡3
//                财经
                case 8:
                    switch (currIndex){
                        case 0:
                            animation = new TranslateAnimation(position_one, 0, 0, 0);
                            resetTextViewTextColor();
                            tvCaijin.setTextColor(getResources().getColor(R.color.heise));
                            break;
                        case 1:
                            animation = new TranslateAnimation(position_one, 0, 0, 0);
                            resetTextViewTextColor();
                            tvCaijin.setTextColor(getResources().getColor(R.color.heise));
                            break;
                        case 2:
                            animation = new TranslateAnimation(position_one, 0, 0, 0);
                            resetTextViewTextColor();
                            tvCaijin.setTextColor(getResources().getColor(R.color.heise));
                            break;
                        case 3:
                            animation = new TranslateAnimation(position_one, 0, 0, 0);
                            resetTextViewTextColor();
                            tvCaijin.setTextColor(getResources().getColor(R.color.heise));
                            break;
                        case 4:
                            animation = new TranslateAnimation(position_one, 0, 0, 0);
                            resetTextViewTextColor();
                            tvCaijin.setTextColor(getResources().getColor(R.color.heise));
                            break;
                        case 5:
                            animation = new TranslateAnimation(position_one, 0, 0, 0);
                            resetTextViewTextColor();
                            tvCaijin.setTextColor(getResources().getColor(R.color.heise));
                            break;
                        case 6:
                            animation = new TranslateAnimation(position_one, 0, 0, 0);
                            resetTextViewTextColor();
                            tvCaijin.setTextColor(getResources().getColor(R.color.heise));
                            break;
                        case 7:
                            animation = new TranslateAnimation(position_one, 0, 0, 0);
                            resetTextViewTextColor();
                            tvCaijin.setTextColor(getResources().getColor(R.color.heise));
                            break;

                        case 9:
                            animation = new TranslateAnimation(position_one, 0, 0, 0);
                            resetTextViewTextColor();
                            tvCaijin.setTextColor(getResources().getColor(R.color.heise));
                            break;

                    }

                    break;
                //当前为页卡3
//                时尚
                case 9:
                    switch (currIndex){
                        case 0:
                            animation = new TranslateAnimation(position_one, 0, 0, 0);
                            resetTextViewTextColor();
                            tvShesahng.setTextColor(getResources().getColor(R.color.heise));
                            break;
                        case 1:
                            animation = new TranslateAnimation(position_one, 0, 0, 0);
                            resetTextViewTextColor();
                            tvShesahng.setTextColor(getResources().getColor(R.color.heise));
                            break;
                        case 2:
                            animation = new TranslateAnimation(position_one, 0, 0, 0);
                            resetTextViewTextColor();
                            tvShesahng.setTextColor(getResources().getColor(R.color.heise));
                            break;
                        case 3:
                            animation = new TranslateAnimation(position_one, 0, 0, 0);
                            resetTextViewTextColor();
                            tvShesahng.setTextColor(getResources().getColor(R.color.heise));
                            break;
                        case 4:
                            animation = new TranslateAnimation(position_one, 0, 0, 0);
                            resetTextViewTextColor();
                            tvShesahng.setTextColor(getResources().getColor(R.color.heise));
                            break;
                        case 5:
                            animation = new TranslateAnimation(position_one, 0, 0, 0);
                            resetTextViewTextColor();
                            tvShesahng.setTextColor(getResources().getColor(R.color.heise));
                            break;
                        case 6:
                            animation = new TranslateAnimation(position_one, 0, 0, 0);
                            resetTextViewTextColor();
                            tvShesahng.setTextColor(getResources().getColor(R.color.heise));
                            break;
                        case 7:
                            animation = new TranslateAnimation(position_one, 0, 0, 0);
                            resetTextViewTextColor();
                            tvShesahng.setTextColor(getResources().getColor(R.color.heise));
                            break;
                        case 8:
                            animation = new TranslateAnimation(position_one, 0, 0, 0);
                            resetTextViewTextColor();
                            tvShesahng.setTextColor(getResources().getColor(R.color.heise));
                            break;


                    }

                    break;
            }
            currIndex = position;
            animation.setFillAfter(true);// true:图片停在动画结束位置
            animation.setDuration(300);
//            cursor.startAnimation(animation);

        }

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };
}
