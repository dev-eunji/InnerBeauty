package com.boostcamp.eunjilee.innerbeauty;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.Transformation;
import com.bumptech.glide.load.engine.Resource;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapResource;

/**
 * Created by eunjilee on 09/02/2017.
 */
public class RoundedCornersTransformation implements Transformation<Bitmap> {
    private BitmapPool mBitmapPool;
    private int mRadius;
    private int mDiameter;
    private int mMargin;
    private Context mContext;

    public RoundedCornersTransformation(Context context, int radius, int margin) {
        this(Glide.get(context).getBitmapPool(), radius, margin);
    }

    private RoundedCornersTransformation(BitmapPool pool, int radius, int margin) {
        mBitmapPool = pool;
        mRadius = radius;
        mDiameter = mRadius * 2;
        mMargin = margin;
    }

    @Override
    public Resource<Bitmap> transform(Resource<Bitmap> resource, int outWidth, int outHeight) {
        Bitmap source = resource.get();
        int width = source.getWidth();
        int height = source.getHeight();

        Bitmap bitmap = mBitmapPool.get(width, height, Bitmap.Config.ARGB_8888);
        if (bitmap == null) {
            bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        }

        Canvas canvas = new Canvas(bitmap);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setShader(new BitmapShader(source, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP));
        drawTopRoundRect(canvas, paint, width, height);
        return BitmapResource.obtain(bitmap, mBitmapPool);
    }

    @Override
    public String getId() {
        return "RoundedTransformation(radius=" + mRadius + ", margin=" + mMargin + ", diameter="
                + mDiameter + ", cornerType=" + "" + ")";
    }

    private void drawTopRoundRect(Canvas canvas, Paint paint, float right, float bottom) {
        canvas.drawRoundRect(new RectF(mMargin, mMargin, right, mMargin + mDiameter), mRadius, mRadius, paint);
        canvas.drawRect(new RectF(mMargin, mMargin + mRadius, right, bottom), paint);
    }
}