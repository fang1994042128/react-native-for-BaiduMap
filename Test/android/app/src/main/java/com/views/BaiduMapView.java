package com.views;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapPoi;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;

import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.geocode.GeoCodeResult;
import com.baidu.mapapi.search.geocode.OnGetGeoCoderResultListener;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeResult;
import com.facebook.react.uimanager.ThemedReactContext;
import com.funmi.tufang.R;

import java.lang.Override;

public class BaiduMapView extends LinearLayout implements
        BaiduMap.OnMapClickListener, BaiduMap.OnMarkerClickListener, OnGetGeoCoderResultListener
        ,BaiduMap.OnMapLoadedCallback{

    private MapView mMapView;
    private BaiduMap mBaiduMap;
    private LocationClient mLocClient;
    private MyLocationData mLocationData;
    private boolean isFirstLoc=true;



    public BaiduMapView(Context context) {
        super(context);
        System.out.println("Map onInit");
        mMapView = new MapView(((ThemedReactContext)context).getBaseContext());
        initMap();
        initLocation(context);
        addView(mMapView);
    }
    public  void initMap(){
        MapStatusUpdate msu_DefualtZoom = MapStatusUpdateFactory.zoomTo(16);
        mBaiduMap = mMapView.getMap();
        this.mBaiduMap.setMaxAndMinZoomLevel(27, 12);
        this.mBaiduMap.setMapStatus(msu_DefualtZoom);
        this.mBaiduMap.setOnMapLoadedCallback(this);
        this.mBaiduMap.setOnMarkerClickListener(this);
        mBaiduMap.setOnMapClickListener(this);
    }

    public void initLocation(Context context){
        mMapView.showScaleControl(false);
        // 开启定位图层
        mBaiduMap.setMyLocationEnabled(true);
        // 定位初始化
        mLocClient = new LocationClient(((ThemedReactContext)context).getBaseContext());
        mLocClient.registerLocationListener(new MyLocationListener());
        LocationClientOption option = new LocationClientOption();
        option.setOpenGps(true);// 打开gps
        option.setCoorType("bd09ll"); // 设置坐标类型
        option.setAddrType("all");
        option.setScanSpan(1000);
        option.setIsNeedAddress(true);
        option.setLocationMode(com.baidu.location.LocationClientOption.LocationMode.Hight_Accuracy);
        mLocClient.setLocOption(option);
		mLocClient.requestLocation();
		mLocClient.start();
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        System.out.println("Map onDestroy");
        if (mMapView !=null) {
            mMapView.onDestroy();
        }
    }

    @Override
    protected void onVisibilityChanged(View changedView, int visibility) {
        super.onVisibilityChanged(changedView, visibility);
        if ( visibility == View.INVISIBLE ) {
            System.out.println("Map onPause");
            mMapView.onPause();
        } else if (visibility == View.VISIBLE ) {
            System.out.println("Map onResume");
            mMapView.onResume();
        }
    }


    @Override
    public void onGetGeoCodeResult(GeoCodeResult geoCodeResult) {

    }

    @Override
    public void onGetReverseGeoCodeResult(ReverseGeoCodeResult reverseGeoCodeResult) {
        System.out.println("纬度啊..........."+reverseGeoCodeResult.getLocation().latitude);
        System.out.println("经度啊................."+reverseGeoCodeResult.getLocation().longitude);

    }

    @Override
    public void onMapClick(LatLng latLng) {

    }

    @Override
    public boolean onMapPoiClick(MapPoi mapPoi) {
        return false;
    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        return false;
    }

    public class MyLocationListener implements BDLocationListener {
        @Override
        public void onReceiveLocation(BDLocation location) {
            if (location == null) {
                return;
            }
            mLocationData = new MyLocationData.Builder()//
                    .latitude(location.getLatitude())//
                    .longitude(location.getLongitude())//
                    .accuracy(location.getRadius())//
                    .build();
            System.out.println("lat..........."+location.getLatitude());
            System.out.println("long................."+location.getLongitude());
            mBaiduMap.setMyLocationData(mLocationData);
            if (isFirstLoc) {
                isFirstLoc =false;
                MapStatusUpdate msu = MapStatusUpdateFactory
                        .newLatLngZoom(
                                new LatLng(location.getLatitude(), location
                                        .getLongitude()), 15.0f);
                if (msu != null) {
                    mBaiduMap.setMapStatus(msu);
                }
                mLocClient.stop();
            }
            //定义Maker坐标点
            LatLng point = new LatLng(location.getLatitude(), location.getLongitude());
            //构建Marker图标
            BitmapDescriptor bitmap = BitmapDescriptorFactory
                    .fromResource(R.mipmap.ic_launcher);
            //构建MarkerOption，用于在地图上添加Marker
            OverlayOptions option = new MarkerOptions()
                    .position(point)
                    .icon(bitmap);
            //在地图上添加Marker，并显示
            mBaiduMap.addOverlay(option);
        }

        public void onReceivePoi(BDLocation location) {
        }
    }

    @Override
    public void onMapLoaded() {

    }
}