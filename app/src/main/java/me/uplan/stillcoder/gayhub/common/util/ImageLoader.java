package me.uplan.stillcoder.gayhub.common.util;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;

import me.uplan.stillcoder.gayhub.R;

/**
 * Created by mac on 2018/1/7.
 */

public class ImageLoader {

    private static RequestOptions normalOptions = new RequestOptions()
            .centerCrop().placeholder(R.drawable.ic_octoface).error(R.drawable.ic_octoface);

    private static RequestOptions circleOptions = RequestOptions.bitmapTransform(new CircleCrop()).
            centerCrop().placeholder(R.drawable.ic_octoface).error(R.drawable.ic_octoface);

    public static void load(Context context, int source, ImageView view) {
        Glide.with(context)
                .load(source).apply(normalOptions)
                .into(view);
    }

    public static void load(int source, ImageView view) {
        Glide.with(view.getContext())
                .load(source).apply(normalOptions)
                .into(view);
    }

    public static void load(Context context, Object source, ImageView view) {
        Glide.with(context)
                .load(source).apply(normalOptions)
                .into(view);
    }

    public static void load(Object source, ImageView view) {
        Glide.with(view.getContext())
                .load(source).apply(normalOptions)
                .into(view);
    }

    public static void loadWithCircle(Context context, int source, ImageView view) {
        Glide.with(context)
                .load(source)
                .apply(circleOptions)
                .into(view);
    }

    public static void loadWithCircle(int source, ImageView view) {
        Glide.with(view.getContext())
                .load(source)
                .apply(circleOptions)
                .into(view);
    }

    public static void loadWithCircle(Context context, Object source, ImageView view) {
        Glide.with(context)
                .load(source)
                .apply(circleOptions)
                .into(view);
    }

    public static void loadWithCircle(Object source, ImageView view) {
        Glide.with(view.getContext())
                .load(source)
                .apply(circleOptions)
                .into(view);
    }
}
