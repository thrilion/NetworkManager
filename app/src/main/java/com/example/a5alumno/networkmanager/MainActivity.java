package com.example.a5alumno.networkmanager;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "MainActivity";

    private ImageView mImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ImageButton downloadImage_Btn = (ImageButton) this.findViewById(R.id.imgBtnDownload);
        downloadImage_Btn.setOnClickListener(this);
        this.mImageView = (ImageView) this.findViewById(R.id.imageViewMain);
        final Button readFeed_Btn = (Button) this.findViewById(R.id.btnReadFeed);
        readFeed_Btn.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        // 'ConnectivityManager' answers queries about the state of network connectivity
        ConnectivityManager mConnectionManager = (ConnectivityManager) this.getSystemService(this.CONNECTIVITY_SERVICE);
        // 'NetworkInfo' describes the status of a network interface
        NetworkInfo mNetworkInfo = mConnectionManager.getActiveNetworkInfo();
        Log.i(MainActivity.TAG, mNetworkInfo.getTypeName() + " " + mNetworkInfo.getState().toString());

        // 'isConnected()' handles cases like flaky mobile networks, airplane mode, and restricted background data
        if (mNetworkInfo != null && mNetworkInfo.isConnected()) {
            if(view.getId() == R.id.imgBtnDownload){
                final String urlString = "http://www.tutorialspoint.com/green/images/logo.png";
                Picasso.with(this).load(urlString).into(this.mImageView);
            }
        } else {
            Log.e(MainActivity.TAG, "Connection  not available");
            Toast.makeText(this, "Connection  not available", Toast.LENGTH_SHORT).show();
        }
    }
}
