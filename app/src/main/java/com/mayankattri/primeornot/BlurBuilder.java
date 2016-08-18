package com.mayankattri.primeornot;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Build;
import android.renderscript.Allocation;
import android.renderscript.Element;
import android.renderscript.RenderScript;
import android.renderscript.ScriptIntrinsicBlur;

/**
 * Created by mayank on 16/8/16.
 */
public class BlurBuilder {
    private static final float BITMAP_SCALE = 0.4f;
    private static final float BLUR_RADIUS = 7.5f;
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    public static Bitmap blur(Context context, Bitmap image) {
        int mWidth = Math.round(image.getWidth() * BITMAP_SCALE);
        int mHeight = Math.round(image.getHeight() * BITMAP_SCALE);
        Bitmap givenBitmap = Bitmap.createScaledBitmap(image, mWidth, mHeight, false);
        Bitmap takenBitmap = Bitmap.createBitmap(givenBitmap);
        RenderScript rs = RenderScript.create(context);
        ScriptIntrinsicBlur theIntrinsic = ScriptIntrinsicBlur.create(rs, Element.U8_4(rs));
        Allocation tmpIn = Allocation.createFromBitmap(rs, givenBitmap);
        Allocation tmpOut = Allocation.createFromBitmap(rs, takenBitmap);
        theIntrinsic.setRadius(BLUR_RADIUS);
        theIntrinsic.setInput(tmpIn);
        theIntrinsic.forEach(tmpOut);
        tmpOut.copyTo(takenBitmap);
        return takenBitmap;
    }
}
