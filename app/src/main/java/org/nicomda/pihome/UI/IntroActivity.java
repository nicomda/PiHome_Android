package org.nicomda.pihome.UI;

import android.Manifest;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;


import com.github.paolorotolo.appintro.AppIntro;
import com.github.paolorotolo.appintro.AppIntroFragment;

import org.nicomda.pihome.R;


public class IntroActivity extends AppIntro {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addSlide(AppIntroFragment.newInstance(getString(R.string.intro_1_title), getString(R.string.intro_1_description), R.drawable.welcome , Color.parseColor("#424242")));
        addSlide(AppIntroFragment.newInstance(getString(R.string.intro_2_title), getString(R.string.intro_2_description), R.drawable.ic_gps , Color.parseColor("#424242")));
        addSlide(AppIntroFragment.newInstance(getString(R.string.intro_3_title), getString(R.string.intro_3_description), R.drawable.ic_camera , Color.parseColor("#424242")));
        addSlide(AppIntroFragment.newInstance(getString(R.string.intro_4_title), getString(R.string.intro_4_description), R.drawable.ic_tick , Color.parseColor("#424242")));
        askForPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION},2);
        askForPermissions(new String[]{Manifest.permission.CAMERA},3);
        showSkipButton(false);
        showStatusBar(false);
        setProgressButtonEnabled(true);
        setBarColor(Color.parseColor("#000000"));

    }

    @Override
    public void onDonePressed(Fragment currentFragment) {
        super.onDonePressed(currentFragment);
        Intent i=new Intent(getApplicationContext(),MainActivity.class);
        startActivity(i);
        finish();
    }


}
