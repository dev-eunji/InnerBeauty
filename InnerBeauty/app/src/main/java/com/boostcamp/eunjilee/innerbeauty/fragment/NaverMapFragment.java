package com.boostcamp.eunjilee.innerbeauty.fragment;

import static com.boostcamp.eunjilee.innerbeauty.BuildConfig.NAVER_CLIENT_ID;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.boostcamp.eunjilee.innerbeauty.nmap.NMapCalloutCustomOldOverlay;
import com.boostcamp.eunjilee.innerbeauty.nmap.NMapPOIflagType;
import com.boostcamp.eunjilee.innerbeauty.nmap.NMapViewerResourceProvider;
import com.boostcamp.eunjilee.innerbeauty.R;
import com.boostcamp.eunjilee.innerbeauty.model.MapModel;
import com.boostcamp.eunjilee.innerbeauty.module.MapModule;
import com.boostcamp.eunjilee.innerbeauty.service.MapService;
import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;
import com.nhn.android.maps.NMapCompassManager;
import com.nhn.android.maps.NMapContext;
import com.nhn.android.maps.NMapLocationManager;
import com.nhn.android.maps.NMapOverlay;
import com.nhn.android.maps.NMapOverlayItem;
import com.nhn.android.maps.NMapView;
import com.nhn.android.maps.maplib.NGeoPoint;
import com.nhn.android.maps.nmapmodel.NMapError;
import com.nhn.android.maps.overlay.NMapPOIdata;
import com.nhn.android.maps.overlay.NMapPOIitem;
import com.nhn.android.mapviewer.overlay.NMapCalloutCustomOverlay;
import com.nhn.android.mapviewer.overlay.NMapCalloutOverlay;
import com.nhn.android.mapviewer.overlay.NMapMyLocationOverlay;
import com.nhn.android.mapviewer.overlay.NMapOverlayManager;
import com.nhn.android.mapviewer.overlay.NMapPOIdataOverlay;

import java.util.ArrayList;

/**
 * Created by eunjilee on 20/02/2017.
 */

public class NaverMapFragment extends Fragment {
    private NMapContext mMapContext;
    private NMapView mNMapView;
    private NMapOverlayManager mOverlayManager;
    private NMapMyLocationOverlay mMyLocationOverlay;
    private NMapLocationManager mMapLocationManager;
    private NMapCompassManager mMapCompassManager;
    private NMapViewerResourceProvider mMapViewerResourceProvider;
    private String mAddress;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mAddress = getArguments().getString("address");
        return inflater.inflate(R.layout.fragment_naver_map, container, false);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mMapContext = new NMapContext(super.getActivity());
        mMapContext.onCreate();
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mNMapView = (NMapView)getView().findViewById(R.id.nmv_map);
        if (mNMapView  == null) {
            throw new IllegalArgumentException("NMapFragment dose not have an instance of NMapView.");
        }
        mNMapView.setClientId(NAVER_CLIENT_ID);// 클라이언트 아이디 설정
        mMapContext.setupMapView(mNMapView);

        mNMapView.setClickable(true);
        mNMapView.setOnMapStateChangeListener(onMapViewStateChangeListener);
        mNMapView.setOnMapViewTouchEventListener(onMapViewTouchEventListener);
        mNMapView.setBuiltInZoomControls(true, null);
        mNMapView.setScalingFactor(1.5f);
        mMapViewerResourceProvider = new NMapViewerResourceProvider(getContext());
        mOverlayManager = new NMapOverlayManager(getContext(), mNMapView, mMapViewerResourceProvider);

        MapModule.getLatLngFromAddressCallback(mAddress,
                new MapService.getLatLngFromAddressCallback() {
                    @Override
                    public void success(MapModel mapModel) {
                        int markerId = NMapPOIflagType.PIN;
                        NMapPOIdata poiData = new NMapPOIdata(1, mMapViewerResourceProvider);
                        poiData.beginPOIdata(1);
                        poiData.addPOIitem(mapModel.getMapResult().getMapItem().get(0).getMapPoint().getX(), mapModel.getMapResult().getMapItem().get(0).getMapPoint().getY(), mapModel.getMapResult().getMapItem().get(0).getAddress(), markerId, 0);
                        poiData.endPOIdata();
                        NMapPOIdataOverlay poiDataOverlay = mOverlayManager.createPOIdataOverlay(poiData, null);
                        poiDataOverlay.setOnStateChangeListener(onPOIdataStateChangeListener);
                        poiDataOverlay.showAllPOIdata(0);
                        mOverlayManager.setOnCalloutOverlayListener(onCalloutOverlayListener); // register callout overlay listener to customize it.
                    }
                    @Override
                    public void error(Throwable throwable) {
                        Log.v("NaverMapFragment", "error");
                    }
                });
    }

    private final NMapView.OnMapStateChangeListener onMapViewStateChangeListener = new NMapView.OnMapStateChangeListener() {
        @Override
        public void onMapInitHandler(NMapView mapView, NMapError errorInfo) {

            if (errorInfo == null) { // success
                //restoreInstanceState();
                new TedPermission(getContext())
                        .setPermissionListener(permissionlistenerLocation)
                        .setDeniedMessage("권한을 거절하시면 전화를 걸 수 없습니다.\n\n[Setting] > [Permission]에서 권한을 켜주세요.")
                        .setPermissions(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION)
                        .check();
            } else { // fail
                Log.e("NAVERMAP", "onFailedToInitializeWithError: " + errorInfo.toString());
                Snackbar.make(mNMapView, errorInfo.toString(), Snackbar.LENGTH_LONG).show();
            }
        }

        @Override
        public void onAnimationStateChange(NMapView mapView, int animType, int animState) {
            Log.i("NAVERMAP", "onAnimationStateChange: animType=" + animType + ", animState=" + animState);
        }

        @Override
        public void onMapCenterChange(NMapView mapView, NGeoPoint center) {
            Log.i("NAVERMAP", "onMapCenterChange: center=" + center.toString());
        }

        @Override
        public void onZoomLevelChange(NMapView mapView, int level) {
            Log.i("NAVERMAP", "onZoomLevelChange: level=" + level);
        }

        @Override
        public void onMapCenterChangeFine(NMapView mapView) {

        }
    };

    private final NMapView.OnMapViewTouchEventListener onMapViewTouchEventListener = new NMapView.OnMapViewTouchEventListener() {
        @Override
        public void onLongPress(NMapView mapView, MotionEvent ev) {
            mapView.setBuiltInZoomControls(true, null);
            mapView.displayZoomControls(true);
        }

        @Override
        public void onLongPressCanceled(NMapView mapView) {
            // TODO Auto-generated method stub

        }

        @Override
        public void onSingleTapUp(NMapView mapView, MotionEvent ev) {
            // TODO Auto-generated method stub

        }

        @Override
        public void onTouchDown(NMapView mapView, MotionEvent ev) {

        }

        @Override
        public void onScroll(NMapView mapView, MotionEvent e1, MotionEvent e2) {
        }

        @Override
        public void onTouchUp(NMapView mapView, MotionEvent ev) {
            // TODO Auto-generated method stub

        }

    };

    private final NMapOverlayManager.OnCalloutOverlayListener onCalloutOverlayListener = new NMapOverlayManager.OnCalloutOverlayListener() {

        @Override
        public NMapCalloutOverlay onCreateCalloutOverlay(NMapOverlay itemOverlay, NMapOverlayItem overlayItem,
                Rect itemBounds) {
            // handle overlapped items
            if (itemOverlay instanceof NMapPOIdataOverlay) {
                NMapPOIdataOverlay poiDataOverlay = (NMapPOIdataOverlay)itemOverlay;

                // check if it is selected by touch event
                if (!poiDataOverlay.isFocusedBySelectItem()) {
                    int countOfOverlappedItems = 1;

                    NMapPOIdata poiData = poiDataOverlay.getPOIdata();
                    for (int i = 0; i < poiData.count(); i++) {
                        NMapPOIitem poiItem = poiData.getPOIitem(i);
                        // skip selected item
                        if (poiItem == overlayItem) {
                            continue;
                        }
                        // check if overlapped or not
                        if (Rect.intersects(poiItem.getBoundsInScreen(), overlayItem.getBoundsInScreen())) {
                            countOfOverlappedItems++;
                        }
                    }
                    if (countOfOverlappedItems > 1) {
                        String text = countOfOverlappedItems + " overlapped items for " + overlayItem.getTitle();
                        Snackbar.make(mNMapView, text, Snackbar.LENGTH_LONG).show();
                        return null;
                    }
                }
            }
            if (overlayItem instanceof NMapPOIitem) {
                NMapPOIitem poiItem = (NMapPOIitem)overlayItem;
                if (poiItem.showRightButton()) {
                    return new NMapCalloutCustomOldOverlay(itemOverlay, overlayItem, itemBounds,
                            mMapViewerResourceProvider);
                }
            }
            return new NMapCalloutCustomOverlay(itemOverlay, overlayItem, itemBounds, mMapViewerResourceProvider);
        }

    };

    private final NMapPOIdataOverlay.OnStateChangeListener onPOIdataStateChangeListener = new NMapPOIdataOverlay.OnStateChangeListener() {

        @Override
        public void onCalloutClick(NMapPOIdataOverlay poiDataOverlay, NMapPOIitem item) { // POI가 클릭되면 호출
            mNMapView.executeNaverMap();
        }

        @Override
        public void onFocusChanged(NMapPOIdataOverlay poiDataOverlay, NMapPOIitem item) {
            if (item != null) {
                Log.i("onPOI dscl", "onFocusChanged: " + item.toString());
            } else {
                Log.i("onPOI dscl", "onFocusChanged: ");
            }
        }
    };

    PermissionListener permissionlistenerLocation = new PermissionListener() {
        @Override
        public void onPermissionGranted() {
            if (ActivityCompat.checkSelfPermission(getContext(),
                    Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
                    && ActivityCompat.checkSelfPermission(getContext(),
                    Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED ) {
                //startMyLocation();
            } else {
                Snackbar.make(mNMapView, "Location Permission Denied\n", Snackbar.LENGTH_SHORT).show();
            }
        }

        @Override
        public void onPermissionDenied(ArrayList<String> deniedPermissions) {
            Snackbar.make(mNMapView, "Permission Denied\n" + deniedPermissions.toString(), Snackbar.LENGTH_SHORT).show();
        }
    };

    private void startMyLocation() {
        mMapLocationManager = new NMapLocationManager(getContext());
        mMapLocationManager.setOnLocationChangeListener(onMyLocationChangeListener);

        boolean isMyLocationEnabled = mMapLocationManager
                .enableMyLocation(true);
        if (!isMyLocationEnabled) {
            Snackbar.make(mNMapView, "Please enable a My Location source in system settings", Snackbar.LENGTH_LONG).show();
        } else {

        }
    }

    private final NMapLocationManager.OnLocationChangeListener onMyLocationChangeListener = new NMapLocationManager.OnLocationChangeListener() {

        @Override
        public boolean onLocationChanged(NMapLocationManager locationManager, NGeoPoint myLocation) {
            mMapContext.findPlacemarkAtLocation(myLocation.getLongitude(), myLocation.getLatitude());
            return true;
        }

        @Override
        public void onLocationUpdateTimeout(NMapLocationManager locationManager) {
            //Snackbar.make(mNMapView, "Your current location is temporarily unavailable.", Snackbar.LENGTH_LONG).show();
        }

        @Override
        public void onLocationUnavailableArea(NMapLocationManager locationManager, NGeoPoint myLocation) {
            Snackbar.make(mNMapView, "Your current location is unavailable area.", Snackbar.LENGTH_LONG).show();
            stopMyLocation();
        }

    };

    private void stopMyLocation() {
        if (mMyLocationOverlay != null) {
            mMapLocationManager.disableMyLocation();

            if (mNMapView.isAutoRotateEnabled()) {
                mMyLocationOverlay.setCompassHeadingVisible(false);
                mMapCompassManager.disableCompass();
            }
        }
    }

    @Override
    public void onStart(){
        super.onStart();
        mMapContext.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
        mMapContext.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mMapContext.onPause();
    }

    @Override
    public void onStop() {
        mMapContext.onStop();
        super.onStop();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        mMapContext.onDestroy();
        super.onDestroy();
    }
}

