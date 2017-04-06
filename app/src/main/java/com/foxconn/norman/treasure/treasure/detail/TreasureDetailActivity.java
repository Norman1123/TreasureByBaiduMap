package com.foxconn.norman.treasure.treasure.detail;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BaiduMapOptions;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.model.LatLng;
import com.foxconn.norman.treasure.R;
import com.foxconn.norman.treasure.commons.ActivityUtils;
import com.foxconn.norman.treasure.custom.TreasureView;
import com.foxconn.norman.treasure.treasure.Treasure;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TreasureDetailActivity extends AppCompatActivity implements TreasureDetailView{
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.frameLayout)
    FrameLayout mFrameLayout;
    @BindView(R.id.detail_treasure)
    TreasureView mTreasureView;
    @BindView(R.id.tv_detail_description)
    TextView mTvDetail;
    private static final String KEY_TREASURE = "key_treasure";
    private Treasure mTreasure;
    private ActivityUtils mActivityUtils;
    private TreasureDetailPresenter mDetailPresenter;

    /**
     * 对外提供一个方法，跳转到本页面
     * 规范一下传递的数据：需要什么参数就必须要传入
     */
    public static void open(Context context, Treasure treasure){
        Intent intent = new Intent(context,TreasureDetailActivity.class);
        intent.putExtra(KEY_TREASURE,treasure);
        context.startActivity(intent);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_treasure_detail);

        mActivityUtils = new ActivityUtils(this);

        mDetailPresenter = new TreasureDetailPresenter(this);

        ButterKnife.bind(this);

        mTreasure= (Treasure) getIntent().getSerializableExtra(KEY_TREASURE);
        //设置toolBar及展示
        setSupportActionBar(mToolbar);
        if (getSupportActionBar()!=null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle(mTreasure.getTitle());
        }

        // 地图的展示
        initMapView();

        // 宝藏信息卡片的展示
        mTreasureView.bindTreasure(mTreasure);

        // 网络获取宝藏的详情数据
        TreasureDetail treasureDetail = new TreasureDetail(mTreasure.getId());
        mDetailPresenter.getTreasureDetail(treasureDetail);
    }
    // 地图的展示
    private void initMapView() {
        // 宝藏的位置
        LatLng latlng = new LatLng(mTreasure.getLatitude(), mTreasure.getLongitude());

        MapStatus mapStatus = new MapStatus.Builder()
                .target(latlng)
                .zoom(18)
                .rotate(0)
                .overlook(-20)
                .build();

        // 地图只是用于展示，没有任何操作
        BaiduMapOptions options = new BaiduMapOptions()
                .mapStatus(mapStatus)
                .compassEnabled(false)
                .scrollGesturesEnabled(false)
                .scaleControlEnabled(false)
                .zoomControlsEnabled(false)
                .zoomGesturesEnabled(false)
                .rotateGesturesEnabled(false);

        // 创建的地图控件
        MapView mapView = new MapView(this, options);

        // 放到布局中
        mFrameLayout.addView(mapView);

        // 拿到地图的操作类
        BaiduMap map = mapView.getMap();

        // 添加一个覆盖物
        BitmapDescriptor dot_expand = BitmapDescriptorFactory.fromResource(R.mipmap.treasure_expanded);
        MarkerOptions option = new MarkerOptions()
                .position(latlng)
                .icon(dot_expand)
                .anchor(0.5f, 0.5f);
        map.addOverlay(option);
    }
    // 处理toolBar返回箭头的事件
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId()==android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    // toolbar的图标的点击事件
    @OnClick(R.id.iv_navigation)
    public void showPopupMenu(View view){
        // 展示出来一个PopupMenu
        /**
         * 1. 创建一个弹出式菜单
         * 2. 菜单项的填充(布局)
         * 3. 设置菜单项的点击监听
         * 4. 显示
         */
        PopupMenu popupMenu=new PopupMenu(this,view);
        popupMenu.inflate(R.menu.menu_navigation);
        popupMenu.setOnMenuItemClickListener(mMenuItemClickListener);
        popupMenu.show();
    }
    // 菜单项的点击监听
    private PopupMenu.OnMenuItemClickListener mMenuItemClickListener = new PopupMenu.OnMenuItemClickListener() {

        // 点击菜单项会触发：具体根据item的id来判断
        @Override
        public boolean onMenuItemClick(MenuItem item) {

            // TODO: 2017/4/6 步行和骑行的点击事件

            return false;
        }
    };
//---------------------------宝藏详情数据获取后的处理（实现TreasureDetailView）--------------------------------------------
    @Override
    public void showMessage(String msg) {
        mActivityUtils.showToast(msg);
    }

    @Override
    public void setDetailData(List<TreasureDetailResult> list) {
// 请求数据的展示
        if (list.size() >= 1) {
            TreasureDetailResult result = list.get(0);
            mTvDetail.setText(result.description);
            return;
        }
        mTvDetail.setText("当前宝藏没有详情信息");
    }
}