package com.example.czhgzsk.czhtotiao;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.jude.rollviewpager.OnItemClickListener;
import com.jude.rollviewpager.RollPagerView;
import com.jude.rollviewpager.adapter.LoopPagerAdapter;
import com.jude.rollviewpager.hintview.IconHintView;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
//    private RollPagerView mLoopViewPager;
//    private Button mBtnPre,mBtnAfter;
//    private TestLoopAdapter mLoopAdapter;
//    OkHttpClient client = new OkHttpClient();
//    private  TextView textView;
//    int mPage = 1;


    private String TAG = "CZH";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        intiView();









    }

//    private void okQingqiu() {
//
//        String username = "skzn";
//        String password_md5 = "asdf1234";
//        String apikey = "8787cdcd0bd89a98a90f4d357384fb91";
//        String shoJi = "18892332998,15599109256,17685309597,18708556364,18980332941";
//        String neiRon = "你的快递一到乡民大厦，请到一楼垃圾池旁领取你的包裹，请联系快递员18892332998";
//        /**
//         * 帐号：skzn
//         密码：asdf12345
//
//         APIKEY ：8787cdcd0bd89a98a90f4d357384fb91
//
//         * */
//        StringBuffer sb = new StringBuffer();
//        sb.append("username="+username);
//        sb.append("&password_md5="+MD5Tools.MD5(password_md5).toLowerCase());
//        sb.append("&apikey="+apikey);
//        sb.append("&mobile="+shoJi);
//        sb.append("&content="+neiRon);
//        sb.append("&encode=utf-8");
//        Log.d(TAG, "okQingqiu: "+sb.toString());
//        OkGo.post("http://m.5c.com.cn/api/send/index.php?"+sb.toString())
//                .tag("daunxin")
//                .execute(new StringCallback() {
//                    @Override
//                    public void onSuccess(String s, Call call, Response response) {
//
//                        Log.d("sa",s);
//                        textView.setText(s);
//                    }
//                });
//
//
//    }
//    private void intiView() {
//
//        mLoopViewPager= (RollPagerView) findViewById(R.id.loop_view_pager);
//        mLoopViewPager.setPlayDelay(3000);
//        mLoopViewPager.setAdapter(mLoopAdapter = new TestLoopAdapter(mLoopViewPager));
//        mLoopViewPager.setHintView(new IconHintView(this,R.drawable.point_focus,R.drawable.point_normal));
//
//        mLoopViewPager.setOnItemClickListener(new OnItemClickListener() {
//            @Override
//            public void onItemClick(int position) {
//                shoeTaost("123"+position);
//
//            }
//        });
//
//
//        getData(mPage);
//
//        //        Button button = (Button) findViewById(R.id.button);
////         textView  = (TextView) findViewById(R.id.textView);
////
////        button.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View v) {
////                okQingqiu();
////            }
////        });
//
//    }
//    private void shoeTaost(String s) {
//        Toast.makeText(this,s,Toast.LENGTH_LONG).show();
//    }
//    public void getData(final int page){
//        Request request = new Request.Builder()
//                .url("http://gank.io/api/data/%E7%A6%8F%E5%88%A9/"+10+"/"+page)
//                .get()
//                .build();
//        Call call =  client.newCall(request);
//        call.enqueue(new Callback() {
//            @Override
//            public void onFailure(Call call, final IOException e) {
//                Log.i("NetImageActivity","error:"+e.getMessage());
//                runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        Toast.makeText(MainActivity.this,"网络请求失败，error:"+e.getMessage(),Toast.LENGTH_SHORT).show();
//                    }
//                });
//            }
//
//            @Override
//            public void onResponse(Call call, Response response) throws IOException {
//                try {
//                    String content = response.body().string();
//                    Log.i("NetImageActivity","raw data:"+content);
//
//                    JSONObject jsonObject = new JSONObject(content);
//                    JSONArray strArr = jsonObject.getJSONArray("results");
//                    final String[] imgs  = new String[strArr.length()];
//                    for (int i = 0; i < strArr.length(); i++) {
//                        JSONObject obj = strArr.getJSONObject(i);
//                        imgs[i] = obj.getString("url");
//                    }
//                    runOnUiThread(new Runnable() {
//                        @Override
//                        public void run() {
//                            mLoopAdapter.setImgs(imgs);
//                        }
//                    });
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//            }
//        });
//    }
//    private class TestLoopAdapter extends LoopPagerAdapter {
//        String[] imgs = new String[0];
//
//        public void setImgs(String[] imgs){
//            this.imgs = imgs;
//            notifyDataSetChanged();
//        }
//
//
//        public TestLoopAdapter(RollPagerView viewPager) {
//            super(viewPager);
//        }
//
//        @Override
//        public View getView(ViewGroup container, int position) {
//            Log.i("RollViewPager","getView:"+imgs[position]);
//
//            ImageView view = new ImageView(container.getContext());
//            view.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Log.i("RollViewPager","onClick");
//                }
//            });
//            view.setScaleType(ImageView.ScaleType.CENTER_CROP);
//            view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
//            Glide.with(MainActivity.this)
//                    .load(imgs[position])
//                    .placeholder(R.mipmap.ic_launcher)
//                    .into(view);
//            return view;
//        }
//
//        @Override
//        public int getRealCount() {
//            return imgs.length;
//        }
//
//    }
}
