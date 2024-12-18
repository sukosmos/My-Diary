package com.example.myapplicationn;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.io.File;

public class MainActivity extends AppCompatActivity implements OnTabItemSelectedListener, MyApplication.OnResponseListener {

    private static final String TAG = "MainActivity";

    BottomNavigationView bottomNavigation;

    GPSListener gpsListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Default Fragment 설정
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, new Fragment1())
                    .commit();
        }

        bottomNavigation = findViewById(R.id.bottom_navigation);

        bottomNavigation.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();
            if (itemId == R.id.tab1) {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.container, new Fragment1())
                        .commit();
                return true;
            } else if (itemId == R.id.tab2) {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.container, new Fragment2())
                        .commit();
                return true;
            } else if (itemId == R.id.tab3) {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.container, new Fragment3())
                        .commit();
                return true;
            }
            return false;
        });

        setPicturePath();
        openDatabase();
    }




    // 사진 경로 설정
    private void setPicturePath() {
        String sdcardPath = Environment.getExternalStorageDirectory().getAbsolutePath();
        Log.d(TAG, "Photo path: " + sdcardPath + "/photo");
    }

    // 데이터베이스 열기
    private void openDatabase() {
        if (NoteDatabase.mDatabase == null) {
            NoteDatabase.mDatabase = NoteDatabase.getInstance(this);
            boolean isOpen = NoteDatabase.mDatabase.open();
            if (isOpen) {
                Log.d("MainActivity", "Database opened successfully.");
            } else {
                Log.e("MainActivity", "Failed to open database.");
            }
        }
    }

    // Abstract method implementation
    @Override
    public void processResponse(int requestCode, int responseCode, String response) {
        Log.d(TAG, "processResponse: requestCode=" + requestCode + ", responseCode=" + responseCode);
    }

    // GPSListener 클래스 정의
    class GPSListener implements LocationListener {
        @Override
        public void onLocationChanged(Location location) {
            Log.d(TAG, "GPS Location Changed: " + location.getLatitude() + ", " + location.getLongitude());
            getCurrentWeather();
            getCurrentAddress();
        }

        @Override
        public void onProviderDisabled(String provider) { }

        @Override
        public void onProviderEnabled(String provider) { }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) { }
    }

    // 날씨 가져오기
    private void getCurrentWeather() {
        Log.d(TAG, "Fetching current weather...");
    }

    // 주소 가져오기
    private void getCurrentAddress() {
        Log.d(TAG, "Fetching current address...");
    }

    @Override
    public void onTabSelected(int position) {
        Log.d(TAG, "Tab selected: " + position);
    }

    @Override
    public void showFragment2(Note item) {
        Log.d(TAG, "Show Fragment2 with item: " + item.toString());
    }
}
