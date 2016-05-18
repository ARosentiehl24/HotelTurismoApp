package com.innovationtechnology.app.hotelturismoapp;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v8.renderscript.Allocation;
import android.support.v8.renderscript.Element;
import android.support.v8.renderscript.RenderScript;
import android.support.v8.renderscript.ScriptIntrinsicBlur;
import android.view.View;

public class Util {

    public static void setImmersiveMode(Activity immersiveMode) {
        immersiveMode.getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_FULLSCREEN |
                        View.SYSTEM_UI_FLAG_HIDE_NAVIGATION |
                        View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY |
                        View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN |
                        View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION |
                        View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
        );
    }

    public static void launchFragment(AppCompatActivity appCompatActivity, Class fragmentLaunch, int container, boolean addToBackStack, boolean popBackStack) {
        Class fragmentClass;
        Fragment fragment = null;
        FragmentManager fragmentManager;

        fragmentClass = fragmentLaunch;

        try {
            fragment = (Fragment) fragmentClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }

        fragmentManager = appCompatActivity.getSupportFragmentManager();

        if (popBackStack) {
            fragmentManager.popBackStack();
        }

        FragmentTransaction transaction = fragmentManager.beginTransaction();

        if (addToBackStack) {
            transaction.addToBackStack(fragmentLaunch.getName());
            //transaction.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out, android.R.anim.fade_in, android.R.anim.fade_out);
        } else {
            //transaction.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out);
        }

        transaction.replace(container, fragment, fragmentLaunch.getName());
        transaction.commit();
    }

    public static Fragment getInstance(AppCompatActivity appCompatActivity, Class fragment) {
        return appCompatActivity.getSupportFragmentManager().findFragmentByTag(fragment.getName());
    }

    private static final float BITMAP_SCALE = 0.5f;
    private static final float BLUR_RADIUS = 10.0f;

    public static Bitmap blur(Context context, Bitmap image) {
        int width = Math.round(image.getWidth() * BITMAP_SCALE);
        int height = Math.round(image.getHeight() * BITMAP_SCALE);

        Bitmap inputBitmap = Bitmap.createScaledBitmap(image, width, height, false);
        Bitmap outputBitmap = Bitmap.createBitmap(inputBitmap);

        RenderScript rs = RenderScript.create(context);
        ScriptIntrinsicBlur theIntrinsic = ScriptIntrinsicBlur.create(rs, Element.U8_4(rs));

        Allocation tmpIn = Allocation.createFromBitmap(rs, inputBitmap);
        Allocation tmpOut = Allocation.createFromBitmap(rs, outputBitmap);

        theIntrinsic.setRadius(BLUR_RADIUS);
        theIntrinsic.setInput(tmpIn);
        theIntrinsic.forEach(tmpOut);

        tmpOut.copyTo(outputBitmap);

        return outputBitmap;
    }

    public static Bitmap blur(Context context, Bitmap image, Float radius) {
        int width = Math.round(image.getWidth() * BITMAP_SCALE);
        int height = Math.round(image.getHeight() * BITMAP_SCALE);

        Bitmap inputBitmap = Bitmap.createScaledBitmap(image, width, height, false);
        Bitmap outputBitmap = Bitmap.createBitmap(inputBitmap);

        RenderScript rs = RenderScript.create(context);
        ScriptIntrinsicBlur theIntrinsic = ScriptIntrinsicBlur.create(rs, Element.U8_4(rs));

        Allocation tmpIn = Allocation.createFromBitmap(rs, inputBitmap);
        Allocation tmpOut = Allocation.createFromBitmap(rs, outputBitmap);

        theIntrinsic.setRadius(radius);
        theIntrinsic.setInput(tmpIn);
        theIntrinsic.forEach(tmpOut);

        tmpOut.copyTo(outputBitmap);

        return outputBitmap;
    }

    public static Bitmap blur(Context context, Bitmap image, Float radius, Float scale) {
        int width = Math.round(image.getWidth() * scale);
        int height = Math.round(image.getHeight() * scale);

        Bitmap inputBitmap = Bitmap.createScaledBitmap(image, width, height, false);
        Bitmap outputBitmap = Bitmap.createBitmap(inputBitmap);

        RenderScript rs = RenderScript.create(context);
        ScriptIntrinsicBlur theIntrinsic = ScriptIntrinsicBlur.create(rs, Element.U8_4(rs));

        Allocation tmpIn = Allocation.createFromBitmap(rs, inputBitmap);
        Allocation tmpOut = Allocation.createFromBitmap(rs, outputBitmap);

        theIntrinsic.setRadius(radius);
        theIntrinsic.setInput(tmpIn);
        theIntrinsic.forEach(tmpOut);

        tmpOut.copyTo(outputBitmap);

        return outputBitmap;
    }
}
