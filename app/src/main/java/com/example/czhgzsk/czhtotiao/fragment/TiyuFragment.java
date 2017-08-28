package com.example.czhgzsk.czhtotiao.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.czhgzsk.czhtotiao.Activity.WebActivity;
import com.example.czhgzsk.czhtotiao.R;
import com.example.czhgzsk.czhtotiao.util.ToutiaoUtil;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Response;

import static com.example.czhgzsk.czhtotiao.utitserver.utilServer.KEYSERVER;
import static com.example.czhgzsk.czhtotiao.utitserver.utilServer.TOUTIAO;

/**
 * Created by czhgzsk on 17-8-25.
 */
@ContentView(R.layout.tiyu_fragment)
public class TiyuFragment extends Fragment{
    private View view;
    @ViewInject(R.id.pv_ty_view)
    private ListView listView;
    private String top = "tiyu";
    private String TAG = "ＴＡＧ";
    List<ToutiaoUtil.ResultBean.DataBean> list = new ArrayList<>();
    private MyAbapter1 abapter;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return x.view().inject(this,inflater,container);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        okQingqiu();
        intiView();
    }


    private void intiView() {
        abapter = new MyAbapter1();
        listView.setAdapter(abapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), WebActivity.class);
                intent.putExtra("url", list.get(position).getUrl());
                startActivity(intent);

            }
        });
    }
    private void okQingqiu() {
//        type=top&key=APPKEY
        StringBuffer sb = new StringBuffer();
        sb.append("type=" + top);
        sb.append("&key=" + KEYSERVER);
        OkGo.post(TOUTIAO + sb.toString())
                .tag(this)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Log.d(TAG, s);
                        try {
                            JSONObject j = new JSONObject(s);
                            String result = j.optString("result");
                            JSONObject js = new JSONObject(result);
                            JSONArray jr = js.getJSONArray("data");
                            for (int i = 0; i < jr.length(); i++) {
                                ToutiaoUtil.ResultBean.DataBean rb = new ToutiaoUtil.ResultBean.DataBean();
                                JSONObject jsd = jr.optJSONObject(i);
                                Log.d("url", jsd.optString("url"));
                                rb.setAuthor_name(jsd.optString("author_name"));
                                rb.setCategory(jsd.optString("category"));
                                rb.setDate(jsd.optString("date"));
                                rb.setThumbnail_pic_s(jsd.optString("thumbnail_pic_s"));
                                rb.setThumbnail_pic_s02(jsd.optString("thumbnail_pic_s02"));
                                rb.setThumbnail_pic_s03(jsd.optString("thumbnail_pic_s03"));
                                rb.setTitle(jsd.optString("title"));
                                rb.setUrl(jsd.optString("url"));
                                list.add(rb);
                            }
                            abapter.notifyDataSetChanged();

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                });

        /**
         * uniquekey : ad954b8beb4caf629eac89b2434a9e1d
         * title : 成都袭胸主播疑再露面“种草莓” 警方：在调查
         * date : 2017-08-25 14:30
         * category : 头条
         * author_name : 搜狐
         * url : http://mini.eastday.com/mobile/170825143038165.html
         * thumbnail_pic_s : http://08.imgmini.eastday.com/mobile/20170825/20170825143038_2012450885808251e556177bbf9fd4a1_1_mwpm_03200403.jpg
         * thumbnail_pic_s02 : http://08.imgmini.eastday.com/mobile/20170825/20170825143038_2012450885808251e556177bbf9fd4a1_2_mwpm_03200403.jpg
         * thumbnail_pic_s03 : http://08.imgmini.eastday.com/mobile/20170825/20170825143038_2012450885808251e556177bbf9fd4a1_3_mwpm_03200403.jpg
         */
    }


    class MyAbapter1 extends BaseAdapter {

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            tanView tanview;
            if (convertView == null) {

                convertView = View.inflate(getActivity(),R.layout.toutiao_itme, null);
                tanview = new tanView();

                tanview.baioti = (TextView) convertView.findViewById(R.id.tv_tt_biaoti);
                tanview.shijain = (TextView) convertView.findViewById(R.id.tv_tt_shijian);
                tanview.laiyun = (TextView) convertView.findViewById(R.id.tv_tt_laiyuon);
                tanview.biaozhu = (TextView) convertView.findViewById(R.id.tv_tt_toutiao);
                tanview.img1 = (ImageView) convertView.findViewById(R.id.img_tt_tu1);
                tanview.img2 = (ImageView) convertView.findViewById(R.id.img_tt_tu2);
                tanview.img3 = (ImageView) convertView.findViewById(R.id.img_tt_tu3);
                convertView.setTag(tanview);
            } else {
                tanview = (tanView) convertView.getTag();
            }

            tanview.baioti.setText(list.get(position).getTitle());
            tanview.shijain.setText(list.get(position).getDate());
            tanview.laiyun.setText(list.get(position).getAuthor_name());
            tanview.biaozhu.setText(list.get(position).getCategory());


            Glide.with(getActivity()).load(list.get(position).getThumbnail_pic_s()).into(tanview.img1);
            Glide.with(getActivity()).load(list.get(position).getThumbnail_pic_s02()).into(tanview.img2);
            Glide.with(getActivity()).load(list.get(position).getThumbnail_pic_s03()).into(tanview.img3);


            return convertView;
        }

        class tanView {
            TextView baioti, shijain, laiyun, biaozhu;
            ImageView img1, img2, img3;

        }
    }
}
