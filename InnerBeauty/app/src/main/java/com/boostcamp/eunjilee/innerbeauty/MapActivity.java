//package com.boostcamp.eunjilee.innerbeauty;
//
//import android.Manifest;
//import android.content.SharedPreferences;
//import android.content.pm.PackageManager;
//import android.graphics.Rect;
//import android.location.LocationManager;
//import android.support.v4.app.ActivityCompat;
//import android.support.v7.app.AppCompatActivity;
//import android.os.Bundle;
//import android.util.Log;
//import android.view.MotionEvent;
//import android.view.View;
//import android.widget.Toast;
//
//import com.gun0912.tedpermission.PermissionListener;
//import com.gun0912.tedpermission.TedPermission;
//import com.nhn.android.maps.NMapActivity;
//import com.nhn.android.maps.NMapCompassManager;
//import com.nhn.android.maps.NMapController;
//import com.nhn.android.maps.NMapLocationManager;
//import com.nhn.android.maps.NMapOverlay;
//import com.nhn.android.maps.NMapOverlayItem;
//import com.nhn.android.maps.NMapView;
//import com.nhn.android.maps.maplib.NGeoPoint;
//import com.nhn.android.maps.nmapmodel.NMapError;
//import com.nhn.android.maps.nmapmodel.NMapPlacemark;
//import com.nhn.android.maps.overlay.NMapPOIdata;
//import com.nhn.android.maps.overlay.NMapPOIitem;
//import com.nhn.android.mapviewer.overlay.NMapCalloutCustomOverlay;
//import com.nhn.android.mapviewer.overlay.NMapCalloutOverlay;
//import com.nhn.android.mapviewer.overlay.NMapMyLocationOverlay;
//import com.nhn.android.mapviewer.overlay.NMapOverlayManager;
//import com.nhn.android.mapviewer.overlay.NMapPOIdataOverlay;
//
//import java.util.ArrayList;
//import java.util.Map;
//
//public class MapActivity extends NMapActivity{
//
//    private NMapView mMapView;// 지도 화면 View
//    private NMapPlacemark mMapPlacemark;
//    private NMapController mMapController;
//    private NMapLocationManager mMapLocationManager;
//    private NMapOverlayManager mOverlayManager;
//    private NMapMyLocationOverlay mMyLocationOverlay;
//    private NMapCompassManager mMapCompassManager;
//
//    private NMapViewerResourceProvider mMapViewerResourceProvider;
//    private final String CLIENT_ID = "IIUrL9SHY62I8qM3OdtT";// 애플리케이션 클라이언트 아이디 값
//    private SharedPreferences mPreferences;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
//        mMapView = new NMapView(this);
//        setContentView(mMapView);
//        mMapView.setClientId(CLIENT_ID);
//
//        // initialize map view
//        mMapView.setClickable(true);
//        mMapView.setEnabled(true);
//        mMapView.setFocusable(true);
//        mMapView.setFocusableInTouchMode(true);
//        mMapView.requestFocus();
//
//        mMapView.setOnMapStateChangeListener(onMapViewStateChangeListener);
//        mMapView.setOnMapViewTouchEventListener(onMapViewTouchEventListener);
//        mMapController = mMapView.getMapController();
//
//        // use built in zoom controls
//        NMapView.LayoutParams lp = new NMapView.LayoutParams(NMapView.LayoutParams.WRAP_CONTENT,
//                NMapView.LayoutParams.WRAP_CONTENT, NMapView.LayoutParams.BOTTOM_RIGHT);
//        mMapView.setBuiltInZoomControls(true, lp);
//
//        // create resource provider
//        //mMapViewerResourceProvider = new NMapViewerResourceProvider(this);
//
//        // set data provider listener
//        super.setMapDataProviderListener(onDataProviderListener);
//
//        // create overlay manager
//        mOverlayManager = new NMapOverlayManager(this, mMapView, mMapViewerResourceProvider);
//        mOverlayManager.setOnCalloutOverlayListener(onCalloutOverlayListener);
//        mOverlayManager.setOnCalloutOverlayViewListener(onCalloutOverlayViewListener);
//
//        // location manager
//        mMapLocationManager = new NMapLocationManager(this);
//        mMapLocationManager.setOnLocationChangeListener(onMyLocationChangeListener);
//
//        // compass manager
//        mMapCompassManager = new NMapCompassManager(this);
//
//        // create my location overlay
//        mMyLocationOverlay = mOverlayManager.createMyLocationOverlay(mMapLocationManager, mMapCompassManager);
//    }
//
//    public void placeContentPlaceMark() { // 연극 전시회 등의 위치를 표시해준다
//        mMapPlacemark = new NMapPlacemark();
//        Toast.makeText(this, mMapPlacemark.toString(), Toast.LENGTH_LONG);
//    }
//
//    public void initSettingForShowLocation() {
//        new TedPermission(this)
//                .setPermissionListener(permissionlistenerCurrentLocation)
//                .setDeniedMessage("If you reject permission,you can not use setting address service\n\nPlease turn on permissions at [Setting] > [Permission]")
//                .setPermissions(Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION)
//                .check();
//    }
//
//    PermissionListener permissionlistenerCurrentLocation = new PermissionListener() {
//        @Override
//        public void onPermissionGranted() {
//            if (ActivityCompat.checkSelfPermission(MapActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
//                    && ActivityCompat.checkSelfPermission(MapActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
//                showCurrentLocation();
//            } else {
//                //Wifi이용
//            }
//        }
//
//        @Override
//        public void onPermissionDenied(ArrayList<String> deniedPermissions) {
//            Toast.makeText(MapActivity.this, "Permission Denied\n" + deniedPermissions.toString(), Toast.LENGTH_SHORT).show();
//        }
//    };
//
//    private static boolean mIsMapEnlared = false;
//
//    private void restoreInstanceState() {
//        mPreferences = getPreferences(MODE_PRIVATE);
//
////        int longitudeE6 = mPreferences.getInt(KEY_CENTER_LONGITUDE, NMAP_LOCATION_DEFAULT.getLongitudeE6());
////        int latitudeE6 = mPreferences.getInt(KEY_CENTER_LATITUDE, NMAP_LOCATION_DEFAULT.getLatitudeE6());
////        int level = mPreferences.getInt(KEY_ZOOM_LEVEL, NMAP_ZOOMLEVEL_DEFAULT);
////        int viewMode = mPreferences.getInt(KEY_VIEW_MODE, NMAP_VIEW_MODE_DEFAULT);
//
////        mMapController.setMapViewMode(viewMode);
////        mMapController.setMapCenter(new NGeoPoint(longitudeE6, latitudeE6), level);
//
//        if (mIsMapEnlared) {
//            mMapView.setScalingFactor(2.0F);
//        } else {
//            mMapView.setScalingFactor(1.0F);
//        }
//    }
//
//    private void showCurrentLocation() {
//        // 단말기의 현재 위치 탐색 기능을 사용
//        mMapLocationManager = new NMapLocationManager(this);
//        NGeoPoint myLocation = mMapLocationManager.getMyLocation();
//        if (NMapView.isValidLocation(myLocation.getLatitude(), myLocation.getLongitude())) {
//            NMapOverlayItem item = new NMapOverlayItem(myLocation, "내 위치", "snippet", null);
//            item.setVisibility(NMapOverlayItem.VISIBLE);
//        }
//
//    }
//
//
//    /* MapView State Change Listener*/
//    private final NMapView.OnMapStateChangeListener onMapViewStateChangeListener = new NMapView.OnMapStateChangeListener() {
//        @Override
//        public void onMapInitHandler(NMapView mapView, NMapError errorInfo) {
//            if (errorInfo == null) { // success
//                restoreInstanceState();
//            } else {
//                Toast.makeText(MapActivity.this, errorInfo.toString(), Toast.LENGTH_LONG).show();
//            }
//        }
//
//        @Override
//        public void onAnimationStateChange(NMapView mapView, int animType, int animState) {
//        }
//
//        @Override
//        public void onMapCenterChange(NMapView mapView, NGeoPoint center) {
//        }
//
//        @Override
//        public void onZoomLevelChange(NMapView mapView, int level) {
//        }
//
//        @Override
//        public void onMapCenterChangeFine(NMapView mapView) {
//        }
//    };
//
//    private final NMapView.OnMapViewTouchEventListener onMapViewTouchEventListener = new NMapView.OnMapViewTouchEventListener() {
//        @Override
//        public void onLongPress(NMapView mapView, MotionEvent ev) {
//        }
//        @Override
//        public void onLongPressCanceled(NMapView mapView) {
//        }
//        @Override
//        public void onSingleTapUp(NMapView mapView, MotionEvent ev) {
//        }
//        @Override
//        public void onTouchDown(NMapView mapView, MotionEvent ev) {
//        }
//        @Override
//        public void onScroll(NMapView mapView, MotionEvent e1, MotionEvent e2) {
//        }
//        @Override
//        public void onTouchUp(NMapView mapView, MotionEvent ev) {
//
//        }
//
//    };
//
//    private final NMapOverlayManager.OnCalloutOverlayListener onCalloutOverlayListener = new NMapOverlayManager.OnCalloutOverlayListener() {
//
//        @Override
//        public NMapCalloutOverlay onCreateCalloutOverlay(NMapOverlay itemOverlay, NMapOverlayItem overlayItem,Rect itemBounds) {
//
//            // handle overlapped items
//            if (itemOverlay instanceof NMapPOIdataOverlay) {
//                NMapPOIdataOverlay poiDataOverlay = (NMapPOIdataOverlay) itemOverlay;
//
//                // check if it is selected by touch event
//                if (!poiDataOverlay.isFocusedBySelectItem()) {
//                    int countOfOverlappedItems = 1;
//
//                    NMapPOIdata poiData = poiDataOverlay.getPOIdata();
//                    for (int i = 0; i < poiData.count(); i++) {
//                        NMapPOIitem poiItem = poiData.getPOIitem(i);
//                        // skip selected item
//                        if (poiItem == overlayItem) {
//                            continue;
//                        }
//                        // check if overlapped or not
//                        if (Rect.intersects(poiItem.getBoundsInScreen(), overlayItem.getBoundsInScreen())) {
//                            countOfOverlappedItems++;
//                        }
//                    }
//
//                    if (countOfOverlappedItems > 1) {
//                        String text = countOfOverlappedItems + " overlapped items for " + overlayItem.getTitle();
//                        Toast.makeText(MapActivity.this, text, Toast.LENGTH_LONG).show();
//                        return null;
//                    }
//                }
//            }
//
//            // use custom old callout overlay
//            if (overlayItem instanceof NMapPOIitem) {
//                NMapPOIitem poiItem = (NMapPOIitem) overlayItem;
//
//                if (poiItem.showRightButton()) {
//                    return new NMapCalloutCustomOldOverlay(itemOverlay, overlayItem, itemBounds,
//                            mMapViewerResourceProvider);
//                }
//            }
//
//            // use custom callout overlay
//            return new NMapCalloutCustomOverlay(itemOverlay, overlayItem, itemBounds, mMapViewerResourceProvider);
//            // set basic callout overlay
//            //return new NMapCalloutBasicOverlay(itemOverlay, overlayItem, itemBounds);
//        }
//
//    };
//    private final NMapOverlayManager.OnCalloutOverlayViewListener onCalloutOverlayViewListener = new NMapOverlayManager.OnCalloutOverlayViewListener() {
//        @Override
//        public View onCreateCalloutOverlayView(NMapOverlay itemOverlay, NMapOverlayItem overlayItem, Rect itemBounds) {
//
//            if (overlayItem != null) {
//                // [TEST] 말풍선 오버레이를 뷰로 설정함
//                String title = overlayItem.getTitle();
//                if (title != null && title.length() > 5) {
//                    return new NMapCalloutCustomOverlayView(MapActivity.this, itemOverlay, overlayItem, itemBounds);
//                }
//            }
//            // null을 반환하면 말풍선 오버레이를 표시하지 않음
//            return null;
//        }
//
//    };
//
//    /* MyLocation Listener */
//    private final NMapLocationManager.OnLocationChangeListener onMyLocationChangeListener = new NMapLocationManager.OnLocationChangeListener() {
//
//        @Override
//        public boolean onLocationChanged(NMapLocationManager locationManager, NGeoPoint myLocation) {
//            if (mMapController != null) {
//                mMapController.animateTo(myLocation);
//            }
//            return true;
//        }
//
//        @Override
//        public void onLocationUpdateTimeout(NMapLocationManager locationManager) {
//            Toast.makeText(MapActivity.this, "Your current location is temporarily unavailable.", Toast.LENGTH_LONG).show();
//        }
//
//        @Override
//        public void onLocationUnavailableArea(NMapLocationManager locationManager, NGeoPoint myLocation) {
//            Toast.makeText(MapActivity.this, "Your current location is unavailable area.", Toast.LENGTH_LONG).show();
//            stopMyLocation();
//        }
//
//    };
//    /* NMapDataProvider Listener */
//    private final OnDataProviderListener onDataProviderListener = new OnDataProviderListener() {
//        @Override
//        public void onReverseGeocoderResponse(NMapPlacemark placeMark, NMapError errInfo) {
//            if (errInfo != null) {
//                Toast.makeText(MapActivity.this, errInfo.toString(), Toast.LENGTH_LONG).show();
//                return;
//            }
//            if (mFloatingPOIitem != null && mFloatingPOIdataOverlay != null) {
//                mFloatingPOIdataOverlay.deselectFocusedPOIitem();
//                if (placeMark != null) {
//                    mFloatingPOIitem.setTitle(placeMark.toString());
//                }
//                mFloatingPOIdataOverlay.selectPOIitemBy(mFloatingPOIitem.getId(), false);
//            }
//        }
//
//    };
//}