package com.example.savethebird.Fragment.Map;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.savethebird.MainActivity;
import com.example.savethebird.R;

import static com.mapbox.mapboxsdk.style.expressions.Expression.division;
import static com.mapbox.mapboxsdk.style.expressions.Expression.exponential;
import static com.mapbox.mapboxsdk.style.expressions.Expression.get;
import static com.mapbox.mapboxsdk.style.expressions.Expression.gte;
import static com.mapbox.mapboxsdk.style.expressions.Expression.has;
import static com.mapbox.mapboxsdk.style.expressions.Expression.interpolate;
import static com.mapbox.mapboxsdk.style.expressions.Expression.literal;
import static com.mapbox.mapboxsdk.style.expressions.Expression.lt;
import static com.mapbox.mapboxsdk.style.expressions.Expression.rgb;
import static com.mapbox.mapboxsdk.style.layers.PropertyFactory.circleColor;
import static com.mapbox.mapboxsdk.style.layers.PropertyFactory.circleRadius;
import static com.mapbox.mapboxsdk.style.layers.PropertyFactory.iconColor;
import static com.mapbox.mapboxsdk.style.layers.PropertyFactory.iconImage;
import static com.mapbox.mapboxsdk.style.layers.PropertyFactory.iconSize;
import static com.mapbox.mapboxsdk.style.layers.PropertyFactory.textAllowOverlap;
import static com.mapbox.mapboxsdk.style.layers.PropertyFactory.textColor;
import static com.mapbox.mapboxsdk.style.layers.PropertyFactory.textField;
import static com.mapbox.mapboxsdk.style.layers.PropertyFactory.textIgnorePlacement;
import static com.mapbox.mapboxsdk.style.layers.PropertyFactory.textSize;

public class MapsFragment extends Fragment {
    private WebView mwbMap;

//    private MapView mapView;
//    private MapboxMap mapboxMap;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

//        Mapbox.getInstance(getContext(), getString(R.string.mapbox_access_token));
        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);


        initView(root);
//        initMap(root, savedInstanceState);

        return root;
    }

    private void initView(View view){
        String url = "file:///android_asset/index.html";
        mwbMap = view.findViewById(R.id.wb_map);
        mwbMap.getSettings().setJavaScriptEnabled(true);
        mwbMap.getSettings().setSupportZoom(true);
        mwbMap.getSettings().setUseWideViewPort(true);
        // Adapt the device size
        mwbMap.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        mwbMap.getSettings().setLoadWithOverviewMode(true);
        mwbMap.setWebViewClient(new WebViewClient() {

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                // false means all the requests will be handled by webView
                // true means using URL deal with the request
                if (url.toString().contains("sina.cn")){
                    view.loadUrl("http://ask.csdn.net/questions/178242");
                    return true;
                }

                return false;
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request)
            {
                // false means all the requests will be handled by webView
                // true means using URL deal with the request
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    if (request.getUrl().toString().contains("sina.cn")){
                        view.loadUrl("http://ask.csdn.net/questions/178242");
                        return true;
                    }
                }

                return false;
            }

        });
        mwbMap.loadUrl(url);
    }

    public void onResume() {
        super.onResume();

        ((MainActivity) getActivity())
                .setActionBarTitle("Distribution of Hooded Plovers");

    }

//    private void initMap(View view, Bundle savedInstanceState) {
//        mapView = view.findViewById(R.id.mapView);
//
//        mapView.onCreate(savedInstanceState);
//        mapView.getMapAsync(new OnMapReadyCallback() {
//            @Override
//            public void onMapReady(@NonNull MapboxMap map) {
//
//                mapboxMap = map;
//
//                map.setStyle(new Style.Builder().fromUri("mapbox://styles/mapbox/streets-v11"), new Style.OnStyleLoaded() {
//                    @Override
//                    public void onStyleLoaded(@NonNull Style style) {
//
//                        // Disable any type of fading transition when icons collide on the map. This enhances the visual
//                        // look of the data clustering together and breaking apart.
//                        style.setTransition(new TransitionOptions(0, 0, false));
//
//                        mapboxMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(
//                                -37.8, 144.96), 3));
//
//                        addClusteredGeoJsonSource(style);
//                        style.addImage(
//                                "cross-icon-id",
//                                BitmapUtils.getBitmapFromDrawable(getResources().getDrawable(R.drawable.ic_cross)),
//                                true
//                        );
//
//                        Toast.makeText(getContext(), R.string.zoom_map_in_and_out_instruction,
//                                Toast.LENGTH_SHORT).show();
//                    }
//                });
//            }
//        });
//
//
//    }
//
//    private void addClusteredGeoJsonSource(@NonNull Style loadedMapStyle) {
//
//        // Add a new source from the GeoJSON data and set the 'cluster' option to true.
//        try {
//            loadedMapStyle.addSource(
//                    // Point to GeoJSON data. This example visualizes all M1.0+ earthquakes from
//                    // 12/22/15 to 1/21/16 as logged by USGS' Earthquake hazards program.
//                    new GeoJsonSource("earthquakes",
//                            new URI("asset://hooded.json"),
//                            new GeoJsonOptions()
//                                    .withCluster(true)
//                                    .withClusterMaxZoom(14)
//                                    .withClusterRadius(50)
//                    )
//            );
//        } catch (URISyntaxException uriSyntaxException) {
//            Timber.e("Check the URL %s", uriSyntaxException.getMessage());
//        }
//
//        //Creating a marker layer for single data points
//        SymbolLayer unclustered = new SymbolLayer("unclustered-points", "earthquakes");
//
//        unclustered.setProperties(
//                iconImage("cross-icon-id"),
//                iconSize(
//                        division(
//                                get("count"), literal(4.0f)
//                        )
//                ),
//                iconColor(
//                        interpolate(exponential(1), get("count"),
//                                stop(2.0, rgb(0, 255, 0)),
//                                stop(4.5, rgb(0, 0, 255)),
//                                stop(7.0, rgb(255, 0, 0))
//                        )
//                )
//        );
//        unclustered.setFilter(has("count"));
//        loadedMapStyle.addLayer(unclustered);
//
//        // Use the earthquakes GeoJSON source to create three layers: One layer for each cluster category.
//        // Each point range gets a different fill color.
//        int[][] layers = new int[][] {
//                new int[] {150, ContextCompat.getColor(getContext(), R.color.mapboxRed)},
//                new int[] {20, ContextCompat.getColor(getContext(), R.color.mapboxGreen)},
//                new int[] {0, ContextCompat.getColor(getContext(), R.color.mapbox_blue)}
//        };
//
//        for (int i = 0; i < layers.length; i++) {
//            //Add clusters' circles
//            CircleLayer circles = new CircleLayer("cluster-" + i, "earthquakes");
//            circles.setProperties(
//                    circleColor(layers[i][1]),
//                    circleRadius(18f)
//            );
//
//            Expression pointCount = toNumber(get("point_count"));
//
//            // Add a filter to the cluster layer that hides the circles based on "point_count"
//            circles.setFilter(
//                    i == 0
//                            ? all(has("point_count"),
//                            gte(pointCount, literal(layers[i][0]))
//                    ) : all(has("point_count"),
//                            gte(pointCount, literal(layers[i][0])),
//                            lt(pointCount, literal(layers[i - 1][0]))
//                    )
//            );
//            loadedMapStyle.addLayer(circles);
//        }
//
//        //Add the count labels
//        SymbolLayer count = new SymbolLayer("count", "earthquakes");
//        count.setProperties(
//                textField(Expression.toString(get("point_count"))),
//                textSize(12f),
//                textColor(Color.WHITE),
//                textIgnorePlacement(true),
//                textAllowOverlap(true)
//        );
//        loadedMapStyle.addLayer(count);
//    }



}