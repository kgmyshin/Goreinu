package com.kgmyshin.goreinu;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class GoreinuActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = new Intent(this, GoreinuService.class);
        startService(intent);
        finish();
    }
}
