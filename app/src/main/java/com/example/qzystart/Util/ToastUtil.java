package com.example.qzystart.Util;


import android.view.Gravity;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import com.example.qzystart.Base.MyApplication;

public class ToastUtil {
    private static Toast toast;
    private static AlertDialog dialog;

    public static void showToast(int textId) {
        showToast(MyApplication.getContext().getString(textId));
    }

    public static void showToast(String text) {
        if (toast == null) {
            toast = Toast.makeText(MyApplication.getContext(), text, Toast.LENGTH_SHORT);
        } else {
            toast.setText(text);
        }
        toast.show();
    }

    /**
     * 显示带图片的Toast
     * @param text  需要显示的文字
     * @param imgId 需要显示的图片
     */
    public static void showImageToast(String text, int imgId) {
        Toast toast = Toast.makeText(MyApplication.getContext(), text, Toast.LENGTH_SHORT);
        toast.setText(text);
        LinearLayout toastView = (LinearLayout) toast.getView();
        toastView.setGravity( Gravity.CENTER);
        ImageView toastImg = new ImageView(MyApplication.getContext());
        toastImg.setLayoutParams(new LinearLayout.LayoutParams(25, 25));
        toastImg.setScaleType( ImageView.ScaleType.FIT_XY);
        toastImg.setImageResource(imgId);
        toastView.addView(toastImg, 0);
        toast.show();
    }

    public static void cancel(){
        if (toast != null) {
            toast.cancel();
        }
        if (dialog != null) {
            dialog.cancel();
        }
    }

    public static void cancelToast() {
        if (toast != null) {
            toast.cancel();
        }
    }

    public static void cancelDialog() {
        if (dialog != null) {
            dialog.cancel();
        }
    }
}
