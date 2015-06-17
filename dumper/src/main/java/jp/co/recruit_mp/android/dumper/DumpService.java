package jp.co.recruit_mp.android.dumper;

import android.app.IntentService;
import android.content.Intent;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
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
public class DumpService extends IntentService {

    private Handler handler = new Handler(Looper.getMainLooper());

    public DumpService(String name) {
        super(name);
    }

    public DumpService() {
        super("DumpService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        copyHomeToExternal();
        handler.post(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(DumpService.this, "success", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void copyHomeToExternal() {
        File homeDir = getFilesDir().getParentFile();
        File dumperDir = new File(Environment.getExternalStorageDirectory(), "Dumper");
        if (!dumperDir.exists()) {
            dumperDir.mkdir();
        }
        File destDir = new File(dumperDir, getApplicationInfo().packageName);
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
                File destFile = new File(dest, file);
                copy(srcFile, destFile);
            }
        } else {
            InputStream in = new FileInputStream(src);
            OutputStream out = new FileOutputStream(dest);
            byte[] buff = new byte[1024];
            int length;
            while ((length = in.read(buff)) > 0) {
                out.write(buff, 0, length);
            }
            in.close();
            out.close();
        }
    }

}
