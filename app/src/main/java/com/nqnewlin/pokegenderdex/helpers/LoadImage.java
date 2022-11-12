package com.nqnewlin.pokegenderdex.helpers;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;

import java.io.IOException;
import java.io.InputStream;

public class LoadImage {

    public Drawable loadImage(Context context, int id) throws IOException {
//        String idString = String.format("%05d", id);
//        String path = "data/images/" + idString + ".jpg";
        String idString = String.format("%03d", id);
        String path = "data/newImages/pokemon_icon_" + idString + "_00.png";
        try {

            // get input stream
            InputStream ims = context.getAssets().open(path);
            // load image as Drawable
            Drawable drawable = Drawable.createFromStream(ims, null);
            // set image to ImageView
            //mImage.setImageDrawable(d);
            ims.close();
            return drawable;
        }
        catch(IOException ex) {
            return loadAlternateImage(context, id);
        }
    }

    public Drawable loadType(Context context, String type) throws IOException {
        String path = "data/types/" + type.toLowerCase() + ".png";
        try {

            // get input stream
            InputStream ims = context.getAssets().open(path);
            // load image as Drawable
            Drawable drawable = Drawable.createFromStream(ims, null);
            ims.close();
            return drawable;
        }
        catch(IOException ex) {
            throw new IOException("Image load failed");
        }
    }

    public Drawable loadIcon(Context context, String type) throws IOException {
//        String idString = String.format("%05d", id);
//        String path = "data/images/" + idString + ".jpg";
        String path = "data/otherImages/" + type + ".png";
        try {

            // get input stream
            InputStream ims = context.getAssets().open(path);
            // load image as Drawable
            Drawable drawable = Drawable.createFromStream(ims, null);
            ims.close();
            return drawable;
        }
        catch(IOException ex) {
            throw new IOException("Image load failed");
        }
    }

    public Drawable loadAlternateImage(Context context, int id) throws IOException {
        String idString = String.format("%05d", id);
        String path = "data/images/" + idString + ".jpg";
        try {
            // get input stream
            InputStream ims = context.getAssets().open(path);
            // load image as Drawable
            Drawable drawable = Drawable.createFromStream(ims, null);
            // set image to ImageView
            //mImage.setImageDrawable(d);
            ims.close();
            return drawable;
        } catch (IOException ex) {
            throw new IOException("Image load failed");
        }
    }

    public Bitmap toGrayscale(Bitmap bmpOriginal) {
        int width, height;
        height = bmpOriginal.getHeight();
        width = bmpOriginal.getWidth();

        Bitmap bmpGrayscale = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(bmpGrayscale);
        Paint paint = new Paint();
        ColorMatrix cm = new ColorMatrix();
        cm.setSaturation(0);
        ColorMatrixColorFilter f = new ColorMatrixColorFilter(cm);
        paint.setColorFilter(f);
        c.drawBitmap(bmpOriginal, 0, 0, paint);
        return bmpGrayscale;
    }
}
