package com.wyuxks.neteasecloud.ui.adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.wyuxks.neteasecloud.R;

import java.util.ArrayList;
import java.util.List;

import uk.co.senab.photoview.PhotoView;
import uk.co.senab.photoview.PhotoViewAttacher;

/**
 * Created by Fire on 2017/4/11.
 */

public /**
 * ViewPager的适配器
 *
 * @author guolin
 */
class PhotoViewAdapter extends PagerAdapter {

    LayoutInflater inflater;
    Context context;
    // 接收传过来的uri地址
    List<String> imageuri;
    PhotoViewAttacher.OnPhotoTapListener photoTapListener;

    public PhotoViewAdapter(Context context,  List<String> imageuri,PhotoViewAttacher.OnPhotoTapListener photoTapListener) {
        inflater = LayoutInflater.from(context);
        this.context = context;
        this.photoTapListener = photoTapListener;
        this.imageuri = imageuri;
    }


    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = inflater.inflate(R.layout.viewpager_very_image, container, false);
        final PhotoView zoom_image_view = (PhotoView) view.findViewById(R.id.zoom_image_view);
        final ProgressBar spinner = (ProgressBar) view.findViewById(R.id.loading);
        // 保存网络图片的路径
        String adapter_image_Entity = (String) getItem(position);
        //TODO
        String imageUrl;
//        if (isLocal) {
//            imageUrl = "file://" + adapter_image_Entity;
//            tv_save_big_image.setVisibility(View.GONE);
//        } else {
            imageUrl = adapter_image_Entity;
//        }

        spinner.setVisibility(View.VISIBLE);
        spinner.setClickable(false);
        Glide.with(context).load(imageUrl)
                .crossFade(700)
                .listener(new RequestListener<String, GlideDrawable>() {
                    @Override
                    public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                        Toast.makeText(context, "资源加载异常", Toast.LENGTH_SHORT).show();
                        spinner.setVisibility(View.GONE);
                        return false;
                    }

                    //这个用于监听图片是否加载完成
                    @Override
                    public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
//                            Toast.makeText(getApplicationContext(), "图片加载完成", Toast.LENGTH_SHORT).show();
                        spinner.setVisibility(View.GONE);

                        /**这里应该是加载成功后图片的高*/
                        int height = zoom_image_view.getHeight();

                        int wHeight = ((Activity) context).getWindowManager().getDefaultDisplay().getHeight();
                        if (height > wHeight) {
                            zoom_image_view.setScaleType(ImageView.ScaleType.CENTER_CROP);
                        } else {
                            zoom_image_view.setScaleType(ImageView.ScaleType.FIT_CENTER);
                        }
                        return false;
                    }
                }).into(zoom_image_view);
        if (photoTapListener != null) {
            zoom_image_view.setOnPhotoTapListener(photoTapListener);
        }
        container.addView(view, 0);
        return view;
    }

    @Override
    public int getCount() {
        if (imageuri == null || imageuri.size() == 0) {
            return 0;
        }
        return imageuri.size();
    }

    @Override
    public boolean isViewFromObject(View arg0, Object arg1) {
        return arg0 == arg1;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        View view = (View) object;
        container.removeView(view);
    }

    Object getItem(int position) {
        return imageuri.get(position);
    }
}