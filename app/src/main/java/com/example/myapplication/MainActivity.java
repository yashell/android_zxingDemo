package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.PermissionChecker;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import zxing.Constants;
import zxing.activity.CaptureActivity;

public class MainActivity extends AppCompatActivity {

    private Bundle activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        activity = savedInstanceState;

    }
    public void aa(View view){
//        Intent intent=new Intent();
//        intent.setAction("zxing");
//        startActivity(intent);
        Log.e("fds", initPermission()+"".toString());
//        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, Constants.REQ_PERM_CAMERA);
////
//        if (ContextCompat.checkSelfPermission(getBaseContext(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
//            Log.e("fds", "aa1");
//            // android 6.0以上需要动态申请权限
//            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, Constants.REQ_PERM_CAMERA);
//            // 二维码扫码
//            return;
//        }
//
//        if (initPermission()){
//            Log.e("fds1", "aa1");
//            Intent intent = new Intent(getBaseContext(), CaptureActivity.class);
//            startActivityForResult(intent, Constants.REQ_QR_CODE);
//        }else {
//            new AlertDialog.Builder(MainActivity.this).setMessage("没有开启摄像机权限，是否去设置开启？")
//                    .setPositiveButton("去开启", new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialogInterface, int i) {
//                            //调用系统内部去开启权限
//                            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.CAMERA}, Constants.REQ_PERM_CAMERA);
//                            ApplicationInfo(MainActivity.this);
//                        }
//                    }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
//                @Override
//                public void onClick(DialogInterface dialogInterface, int i) {
//                    dialogInterface.dismiss();
//                }
//            }).show();
//
//        }

        Intent intent = new Intent(getBaseContext(), CaptureActivity.class);

        //getBaseContext()在Fragment中 不能用的话   getActivity().getBaseContext()
        startActivityForResult(intent, Constants.REQ_QR_CODE);


    }
    private boolean initPermission() {
        //需要在Android里面找到你要开的权限
        String permissions = Manifest.permission.CAMERA;
        boolean ret = false;
        //Android 6.0以上才有动态权限
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            //permission granted 说明权限开了
            ret = PermissionChecker.checkSelfPermission(MainActivity.this, permissions) == PermissionChecker.PERMISSION_GRANTED;
        }
        return ret;
    }

    //调用系统内部开启权限
    public static void ApplicationInfo(Activity activity) {
        try {
            Intent localIntent = new Intent();
            localIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            if (Build.VERSION.SDK_INT >= 9) {
                localIntent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
                localIntent.setData(Uri.fromParts("package", activity.getPackageName(), null));
            } else if (Build.VERSION.SDK_INT <= 8) {
                localIntent.setAction(Intent.ACTION_VIEW);
                localIntent.setClassName("com.android.settings", "com.android.settings.InstalledAppDetails");
                localIntent.putExtra("com.android.settings.ApplicationPkgName", activity.getPackageName());
            }
            activity.startActivity(localIntent);
        } catch (Exception e) {
        }
    }



//    private void startQrCode() {
//
//    }


}
