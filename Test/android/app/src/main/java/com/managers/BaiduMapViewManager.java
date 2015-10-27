package com.managers;

import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.CatalystStylesDiffMap;
import com.facebook.react.uimanager.UIProp;

import com.views.BaiduMapView;

public class BaiduMapViewManager extends SimpleViewManager<BaiduMapView> {

    public static final String REACT_CLASS = "BaiduMap";

    @UIProp(UIProp.Type.STRING)
    public static final String PROP_CONTENT = "content";



    @Override
    public String getName() {
        return REACT_CLASS;
    }

    @Override
    public BaiduMapView createViewInstance(ThemedReactContext context) {
        BaiduMapView mMapView = new BaiduMapView(context);
        return mMapView;
    }

    @Override
    public void updateView(final BaiduMapView view,
                         final CatalystStylesDiffMap props) {
        super.updateView(view, props);
    }

}