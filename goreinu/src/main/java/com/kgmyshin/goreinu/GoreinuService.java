package com.kgmyshin.goreinu;

import android.app.IntentService;
import android.content.Intent;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by kgmyshin on 15/06/17.
 */
public class GoreinuService extends IntentService {
    private static final String TAG = GoreinuService.class.getSimpleName();
    private Handler handler = new Handler(Looper.getMainLooper());

    public GoreinuService(String name) {
        super(name);
    }

    public GoreinuService() {
        super("GoreinuService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        copyHomeToExternal();
        handler.post(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(GoreinuService.this, "success", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void copyHomeToExternal() {
        File homeDir = getFilesDir().getParentFile();
        File dumperDir = new File(Environment.getExternalStorageDirectory(), "Goreinu");
        if (!dumperDir.exists()) {
            dumperDir.mkdir();
        }
        File destDir = new File(dumperDir, getApplicationInfo().packageName);
        if (destDir.exists()) {
            deleteAll(destDir);
        }
        if (homeDir.exists()) {
            try {
                copy(homeDir, destDir);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void copy(File src, File dest) throws IOException {
        if (src.isDirectory()) {
            if (!dest.exists()) {
                dest.mkdir();
            }
            String[] files = src.list();
            for (String file : files) {
                File srcFile = new File(src, file);
                if (srcFile.exists()) {
                    File destFile = new File(dest, file);
                    copy(srcFile, destFile);
                }
            }
        } else if (src.isFile()) {
            InputStream in = new FileInputStream(src);
            OutputStream out = new FileOutputStream(dest);
            byte[] buff = new byte[1024];
            int length;
            while ((length = in.read(buff)) > 0) {
                out.write(buff, 0, length);
            }
            in.close();
            out.close();
        } else {
            Log.d(TAG, src.toString() + " file is symbolic link");
        }
    }

    private void deleteAll(File target) {
        if (target.isDirectory()) {
            File[] files = target.listFiles();
            for (File file : files) {
                deleteAll(file);
            }
        } else {
            target.delete();
        }
    }

}
