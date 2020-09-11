package com.example.savethebird.ui.Map;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.savethebird.R;
import com.mapbox.mapboxsdk.Mapbox;
import com.mapbox.mapboxsdk.location.LocationComponentActivationOptions;
import com.mapbox.mapboxsdk.maps.MapView;
import com.mapbox.mapboxsdk.maps.MapboxMap;
import com.mapbox.mapboxsdk.maps.OnMapReadyCallback;
import com.mapbox.mapboxsdk.maps.Style;

public class MapsFragment extends Fragment {
//    private WebView mwbMap;


    private MapView mapView;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        Mapbox.getInstance(getContext(), getString(R.string.mapbox_access_token));
        View root = inflater.inflate(R.layout.fragment_map, container, false);

//        initView(root);
        initMap(root, savedInstanceState);



        //写在return root之前
        return root;
    }

//    private void initView(View view){
//        String url = "file:///android_asset/ebird.html";
//        mwbMap = view.findViewById(R.id.wb_map);
//        mwbMap.getSettings().setJavaScriptEnabled(true);
//        mwbMap.getSettings().setSupportZoom(true);
//        mwbMap.getSettings().setUseWideViewPort(true);
//        //自适应屏幕
//        mwbMap.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
//        mwbMap.getSettings().setLoadWithOverviewMode(true);
//
//        //如果不设置WebViewClient，请求会跳转系统浏览器
//        mwbMap.setWebViewClient(new WebViewClient() {
//
//            @Override
//            public boolean shouldOverrideUrlLoading(WebView view, String url) {
//                //该方法在Build.VERSION_CODES.LOLLIPOP以前有效，从Build.VERSION_CODES.LOLLIPOP起，建议使用shouldOverrideUrlLoading(WebView, WebResourceRequest)} instead
//                //返回false，意味着请求过程里，不管有多少次的跳转请求（即新的请求地址），均交给webView自己处理，这也是此方法的默认处理
//                //返回true，说明你自己想根据url，做新的跳转，比如在判断url符合条件的情况下，我想让webView加载http://ask.csdn.net/questions/178242
//
//                if (url.toString().contains("sina.cn")){
//                    view.loadUrl("http://ask.csdn.net/questions/178242");
//                    return true;
//                }
//
//                return false;
//            }
//
//            @Override
//            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request)
//            {
//                //返回false，意味着请求过程里，不管有多少次的跳转请求（即新的请求地址），均交给webView自己处理，这也是此方法的默认处理
//                //返回true，说明你自己想根据url，做新的跳转，比如在判断url符合条件的情况下，我想让webView加载http://ask.csdn.net/questions/178242
//                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//                    if (request.getUrl().toString().contains("sina.cn")){
//                        view.loadUrl("http://ask.csdn.net/questions/178242");
//                        return true;
//                    }
//                }
//
//                return false;
//            }
//
//        });
//        mwbMap.loadUrl(url);
//    }



  private void initMap(View view,Bundle savedInstanceState){
      mapView = view.findViewById(R.id.mapView);
      mapView.onCreate(savedInstanceState);
      mapView.getMapAsync(new OnMapReadyCallback() {
          @Override
          public void onMapReady(@NonNull MapboxMap mapboxMap) {

              mapboxMap.setStyle(Style.MAPBOX_STREETS, new Style.OnStyleLoaded() {
                  @Override
                  public void onStyleLoaded(@NonNull Style style) {

                      // Map is set up and the style has loaded. Now you can add data or make other map adjustments


                  }
              });

          }
      });
  }

}