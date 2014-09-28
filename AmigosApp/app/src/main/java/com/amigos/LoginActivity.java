package com.amigos;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.content.res.AssetFileDescriptor;
import android.graphics.SurfaceTexture;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.TextureView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.widget.VideoView;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseFacebookUtils;
import com.parse.ParseUser;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;


public class LoginActivity extends Activity implements TextureView.SurfaceTextureListener {

    private TextureView splashVideo;
    private MediaPlayer mediaPlayer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        getActionBar().hide();

        splashVideo = (TextureView)findViewById(R.id.splash_video);
        splashVideo.setSurfaceTextureListener(this);







        Button loginButton = (Button)findViewById(R.id.facebook_login);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initFacebookLogin();
            }
        });

        ParseUser currentUser = ParseUser.getCurrentUser();
        if ((currentUser != null) && ParseFacebookUtils.isLinked(currentUser)) {
            Intent homeIntent = new Intent(LoginActivity.this,HomeActivity.class);
            startActivity(homeIntent);
            finish();
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        ParseFacebookUtils.finishAuthentication(requestCode,resultCode,data);
    }


    @Override
    protected void onResume() {
        super.onResume();


    }

    private void initFacebookLogin(){
            final  ProgressDialog progressDialog = ProgressDialog.show(
                    LoginActivity.this, "", "Logging in...", true);

            List<String> permissions = Arrays.asList("public_profile","email");

            ParseFacebookUtils.logIn(permissions, this, new LogInCallback() {


                @Override
                public void done(ParseUser user, ParseException err) {

                    if (user == null) {
                        progressDialog.dismiss();
                        Toast.makeText(LoginActivity.this,"Error",Toast.LENGTH_SHORT).show();

                    } else if (user.isNew()) {
                        progressDialog.dismiss();

                        Intent homeIntent = new Intent(LoginActivity.this,HomeActivity.class);
                        startActivity(homeIntent);
                        finish();
                    } else {
                        progressDialog.dismiss();
                        Intent homeIntent = new Intent(LoginActivity.this,HomeActivity.class);
                        startActivity(homeIntent);
                        finish();
                    }
                }
            });

    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    public void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture, int i, int i2) {

        Surface surface = new Surface(surfaceTexture);

        try {
            AssetFileDescriptor afd = getAssets().openFd("BattleHack.mp4");
            mediaPlayer = new MediaPlayer();
            mediaPlayer.setDataSource(afd.getFileDescriptor(),afd.getStartOffset(), afd.getLength());
            mediaPlayer.setSurface(surface);
            mediaPlayer.setLooping(true);
            mediaPlayer.prepareAsync();


            mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mediaPlayer) {
                    mediaPlayer.start();
                }
            });



        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    @Override
    public void onSurfaceTextureSizeChanged(SurfaceTexture surfaceTexture, int i, int i2) {

    }

    @Override
    public boolean onSurfaceTextureDestroyed(SurfaceTexture surfaceTexture) {
        return false;
    }

    @Override
    public void onSurfaceTextureUpdated(SurfaceTexture surfaceTexture) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mediaPlayer != null){
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }
}
