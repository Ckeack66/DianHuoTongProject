package com.mhky.dianhuotong.main;

import android.content.Context;
import android.net.Uri;
import android.widget.ImageView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.squareup.picasso.Picasso;
import com.youth.banner.loader.ImageLoader;

/**
 * Created by Administrator on 2018/4/4.
 */

public class GlideImageLoader extends ImageLoader {
    private static final String TAG = "GlideImageLoader";

    @Override
    public void displayImage(Context context, Object path, ImageView imageView) {
        Uri uri = Uri.parse((String) path);
        Picasso.get().load(uri).fit().into(imageView);
//        Uri uri = Uri.parse((String) path);
//        imageView.setImageURI(uri);
    }

    @Override
    public ImageView createImageView(Context context) {
        SimpleDraweeView simpleDraweeView = new SimpleDraweeView(context);
        return simpleDraweeView;
    }
}
