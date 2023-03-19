package com.movieboxpro.android.utils;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.util.Base64;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
/* loaded from: classes3.dex */
public class BitmapUtil {
    private static final String TAG = BitmapUtil.class.getSimpleName();

    public static byte[] bitmapToByte(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }

    public static Bitmap byteToBitmap(byte[] bArr) {
        if (bArr == null || bArr.length == 0) {
            return null;
        }
        return BitmapFactory.decodeByteArray(bArr, 0, bArr.length);
    }

    public static String bitmapToString(Bitmap bitmap) {
        return Base64.encodeToString(bitmapToByte(bitmap), 0);
    }

    public static Bitmap drawableToBitmap(Drawable drawable) {
        if (drawable == null) {
            return null;
        }
        return ((BitmapDrawable) drawable).getBitmap();
    }

    public static Drawable bitmapToDrawable(Bitmap bitmap) {
        if (bitmap == null) {
            return null;
        }
        return new BitmapDrawable(bitmap);
    }

    public static Bitmap scaleImageTo(Bitmap bitmap, int i, int i2) {
        return scaleImage(bitmap, i / bitmap.getWidth(), i2 / bitmap.getHeight());
    }

    public static Bitmap scaleImage(Bitmap bitmap, float f, float f2) {
        if (bitmap == null) {
            return null;
        }
        Matrix matrix = new Matrix();
        matrix.postScale(f, f2);
        return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
    }

    public static Bitmap toRoundCorner(Bitmap bitmap) {
        int height = bitmap.getHeight();
        int height2 = bitmap.getHeight();
        Bitmap createBitmap = Bitmap.createBitmap(height2, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        Paint paint = new Paint();
        Rect rect = new Rect(0, 0, height2, height);
        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(0);
        float f = height2 / 2;
        canvas.drawCircle(f, height / 2, f, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);
        return createBitmap;
    }

    public static Bitmap createBitmapThumbnail(Bitmap bitmap, boolean z, int i, int i2) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        Matrix matrix = new Matrix();
        matrix.postScale(i2 / width, i / height);
        Bitmap createBitmap = Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix, true);
        if (z) {
            bitmap.recycle();
        }
        return createBitmap;
    }

    public static boolean saveBitmap(Bitmap bitmap, File file) {
        FileOutputStream fileOutputStream;
        if (bitmap == null) {
            return false;
        }
        FileOutputStream fileOutputStream2 = null;
        try {
            try {
                fileOutputStream = new FileOutputStream(file);
            } catch (Throwable th) {
                th = th;
            }
        } catch (Exception e) {
            e = e;
        }
        try {
            bitmap.compress(Bitmap.CompressFormat.JPEG, 60, fileOutputStream);
            fileOutputStream.flush();
            try {
                fileOutputStream.close();
            } catch (IOException e2) {
                e2.printStackTrace();
            }
            return true;
        } catch (Exception e3) {
            e = e3;
            fileOutputStream2 = fileOutputStream;
            e.printStackTrace();
            if (fileOutputStream2 != null) {
                try {
                    fileOutputStream2.close();
                } catch (IOException e4) {
                    e4.printStackTrace();
                }
            }
            return false;
        } catch (Throwable th2) {
            th = th2;
            fileOutputStream2 = fileOutputStream;
            if (fileOutputStream2 != null) {
                try {
                    fileOutputStream2.close();
                } catch (IOException e5) {
                    e5.printStackTrace();
                }
            }
            throw th;
        }
    }

    public static boolean saveBitmap(Bitmap bitmap, String str) {
        return saveBitmap(bitmap, new File(str));
    }

    public static Intent buildImageGetIntent(Uri uri, int i, int i2, boolean z) {
        return buildImageGetIntent(uri, 1, 1, i, i2, z);
    }

    public static Intent buildImageGetIntent(Uri uri, int i, int i2, int i3, int i4, boolean z) {
        Intent intent = new Intent();
        if (Build.VERSION.SDK_INT < 19) {
            intent.setAction("android.intent.action.GET_CONTENT");
        } else {
            intent.setAction("android.intent.action.OPEN_DOCUMENT");
            intent.addCategory("android.intent.category.OPENABLE");
        }
        intent.setType("image/*");
        intent.putExtra("output", uri);
        intent.putExtra("aspectX", i);
        intent.putExtra("aspectY", i2);
        intent.putExtra("outputX", i3);
        intent.putExtra("outputY", i4);
        intent.putExtra("scale", true);
        intent.putExtra("return-data", z);
        intent.putExtra("outputFormat", Bitmap.CompressFormat.PNG.toString());
        return intent;
    }

    public static Intent buildImageCropIntent(Uri uri, Uri uri2, int i, int i2, boolean z) {
        return buildImageCropIntent(uri, uri2, 1, 1, i, i2, z);
    }

    public static Intent buildImageCropIntent(Uri uri, Uri uri2, int i, int i2, int i3, int i4, boolean z) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", "true");
        intent.putExtra("output", uri2);
        intent.putExtra("aspectX", i);
        intent.putExtra("aspectY", i2);
        intent.putExtra("outputX", i3);
        intent.putExtra("outputY", i4);
        intent.putExtra("scale", true);
        intent.putExtra("return-data", z);
        intent.putExtra("outputFormat", Bitmap.CompressFormat.PNG.toString());
        return intent;
    }

    public static Intent buildImageCaptureIntent(Uri uri) {
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        intent.putExtra("output", uri);
        return intent;
    }

    public static int calculateInSampleSize(BitmapFactory.Options options, int i, int i2) {
        int min;
        int i3 = options.outHeight;
        int i4 = options.outWidth;
        if (i3 > i2 || i4 > i) {
            min = (int) Math.min(i3 / i2, i4 / i);
        } else {
            min = 0;
        }
        return Math.max(1, min);
    }

    public static Bitmap getSmallBitmap(String str, int i, int i2) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(str, options);
        options.inSampleSize = calculateInSampleSize(options, i, i2);
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeFile(str, options);
    }

    public byte[] compressBitmapToBytes(String str, int i, int i2, int i3) {
        Bitmap smallBitmap = getSmallBitmap(str, i, i2);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        smallBitmap.compress(Bitmap.CompressFormat.JPEG, i3, byteArrayOutputStream);
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        smallBitmap.recycle();
        return byteArray;
    }

    public byte[] compressBitmapSmallTo(String str, int i, int i2, int i3) {
        int i4 = 100;
        byte[] compressBitmapToBytes = compressBitmapToBytes(str, i, i2, 100);
        while (compressBitmapToBytes.length > i3 && i4 > 0) {
            i4 /= 2;
            compressBitmapToBytes = compressBitmapToBytes(str, i, i2, i4);
        }
        return compressBitmapToBytes;
    }

    public byte[] compressBitmapQuikly(String str) {
        return compressBitmapToBytes(str, 480, 800, 50);
    }

    public byte[] compressBitmapQuiklySmallTo(String str, int i) {
        return compressBitmapSmallTo(str, 480, 800, i);
    }
}
