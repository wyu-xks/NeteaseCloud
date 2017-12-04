package com.wyuxks.neteasecloud.utils;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.wyuxks.neteasecloud.NeteaseCloud;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Administrator on 2015/10/19.
 */
public class DensityUtil {

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dip2px(float dpValue) {
        NeteaseCloud neteaseCloud = NeteaseCloud.Companion.getInstance();
        final float scale = neteaseCloud.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }


    public static int dip2px(Context context, float dpValue) {
        NeteaseCloud neteaseCloud = NeteaseCloud.Companion.getInstance();
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public static int px2dip(float pxValue) {
        NeteaseCloud neteaseCloud = NeteaseCloud.Companion.getInstance();
        final float scale = neteaseCloud.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    /**
     * 设置某个View的margin
     *
     * @param view   需要设置的view
     * @param isDp   需要设置的数值是否为DP
     * @param left   左边距
     * @param right  右边距
     * @param top    上边距
     * @param bottom 下边距
     * @return
     */
    public static ViewGroup.LayoutParams setViewMargin(View view, boolean isDp, int left, int right, int top, int bottom) {
        if (view == null) {
            return null;
        }

        int leftPx = left;
        int rightPx = right;
        int topPx = top;
        int bottomPx = bottom;
        ViewGroup.LayoutParams params = view.getLayoutParams();
        ViewGroup.MarginLayoutParams marginParams = null;
        //获取view的margin设置参数
        if (params instanceof ViewGroup.MarginLayoutParams) {
            marginParams = (ViewGroup.MarginLayoutParams) params;
        } else {
            //不存在时创建一个新的参数
            marginParams = new ViewGroup.MarginLayoutParams(params);
        }

        //根据DP与PX转换计算值
        if (isDp) {
            leftPx = dip2px(left);
            rightPx = dip2px(right);
            topPx = dip2px(top);
            bottomPx = dip2px(bottom);
        }
        //设置margin
        marginParams.setMargins(leftPx, topPx, rightPx, bottomPx);
        view.setLayoutParams(marginParams);
        view.requestLayout();
        return marginParams;
    }

    public static void px2dip(Byte byt) {
        ArrayList<Byte> arrayList = new ArrayList<>();
        arrayList.add(byt);
        Collections.sort(arrayList);
        int i = 0;
        byte[] bytes = new byte[arrayList.size() + 2];
        bytes[i++] = (byte) (arrayList.size() + 1);
        bytes[i++] = (byte) 0x04;
        for (Byte bt : arrayList) {
            bytes[i++] = bt;
        }
    }

}