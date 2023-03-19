package com.movieboxpro.android.view.widget.CircleProgress;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.widget.FrameLayout;
import android.widget.ImageView;
import com.alibaba.fastjson.asm.Opcodes;
import java.io.PrintStream;
/* loaded from: classes3.dex */
public class Circlelayout extends FrameLayout implements View.OnClickListener {
    private RotateAnimation arcRotation;
    private ImageView arc_image;
    private ImageView buttonimage;
    public CusImage cusview;
    private Path download_rectangle;
    private Path download_triangle;
    private AlphaAnimation fade_in;
    private AlphaAnimation fade_out;
    private Paint fill_color;
    private ImageView fillcircle;
    private Paint final_icon_color;
    boolean first_click;
    private Bitmap first_icon_bmp;
    public int flg_frmwrk_mode;
    private ImageView full_circle_image;
    private Paint icon_color;
    private AnimationSet in;
    private ScaleAnimation new_scale_in;
    private AnimationSet out;
    public int pix;
    private Path play;
    public RectF rect;
    private ScaleAnimation scale_in;
    private ScaleAnimation scale_out;
    private Bitmap second_icon_bmp;
    private Path stop;
    private Paint stroke_color;
    private Bitmap third_icon_bmp;
    private Path tick;

    public Circlelayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.pix = 0;
        this.flg_frmwrk_mode = 0;
        this.first_click = false;
        setOnClickListener(this);
        initialise();
        setpaint();
        setAnimation();
        displayMetrics();
        iconCreate();
        init();
    }

    public Circlelayout(Context context) {
        super(context);
        this.pix = 0;
        this.flg_frmwrk_mode = 0;
        this.first_click = false;
        setOnClickListener(this);
        setBackgroundColor(-16711681);
        initialise();
        setpaint();
        setAnimation();
        displayMetrics();
        iconCreate();
        init();
    }

    private void initialise() {
        this.cusview = new CusImage(getContext(), this);
        this.buttonimage = new ImageView(getContext());
        this.full_circle_image = new ImageView(getContext());
        this.arc_image = new ImageView(getContext());
        this.fillcircle = new ImageView(getContext());
        this.cusview.setClickable(false);
        this.buttonimage.setClickable(false);
        this.full_circle_image.setClickable(false);
        this.arc_image.setClickable(false);
        this.cusview.setClickable(false);
        setClickable(true);
        this.fillcircle.setClickable(false);
    }

    private void setpaint() {
        Paint paint = new Paint(1);
        this.stroke_color = paint;
        paint.setAntiAlias(true);
        this.stroke_color.setColor(Color.rgb(0, (int) Opcodes.IF_ICMPLT, 234));
        this.stroke_color.setStrokeWidth(3.0f);
        this.stroke_color.setStyle(Paint.Style.STROKE);
        Paint paint2 = new Paint(1);
        this.icon_color = paint2;
        paint2.setColor(Color.rgb(0, (int) Opcodes.IF_ICMPLT, 234));
        this.icon_color.setStyle(Paint.Style.FILL_AND_STROKE);
        this.icon_color.setAntiAlias(true);
        Paint paint3 = new Paint(1);
        this.final_icon_color = paint3;
        paint3.setColor(-1);
        this.final_icon_color.setStrokeWidth(12.0f);
        this.final_icon_color.setStyle(Paint.Style.STROKE);
        this.final_icon_color.setAntiAlias(true);
        Paint paint4 = new Paint(1);
        this.fill_color = paint4;
        paint4.setColor(Color.rgb(0, (int) Opcodes.IF_ICMPLT, 234));
        this.fill_color.setStyle(Paint.Style.FILL_AND_STROKE);
        this.fill_color.setAntiAlias(true);
    }

    private void setAnimation() {
        RotateAnimation rotateAnimation = new RotateAnimation(0.0f, 360.0f, 1, 0.5f, 1, 0.5f);
        this.arcRotation = rotateAnimation;
        rotateAnimation.setDuration(1000L);
        this.in = new AnimationSet(true);
        AnimationSet animationSet = new AnimationSet(true);
        this.out = animationSet;
        animationSet.setInterpolator(new AccelerateDecelerateInterpolator());
        this.in.setInterpolator(new AccelerateDecelerateInterpolator());
        this.scale_in = new ScaleAnimation(0.0f, 1.0f, 0.0f, 1.0f, 1, 0.5f, 1, 0.5f);
        this.scale_out = new ScaleAnimation(1.0f, 3.0f, 1.0f, 3.0f, 1, 0.5f, 1, 0.5f);
        ScaleAnimation scaleAnimation = new ScaleAnimation(0.0f, 1.0f, 0.0f, 1.0f, 1, 0.5f, 1, 0.5f);
        this.new_scale_in = scaleAnimation;
        scaleAnimation.setDuration(200L);
        this.fade_in = new AlphaAnimation(0.0f, 1.0f);
        this.fade_out = new AlphaAnimation(1.0f, 0.0f);
        this.scale_in.setDuration(150L);
        this.scale_out.setDuration(150L);
        this.fade_in.setDuration(150L);
        this.fade_out.setDuration(150L);
        this.in.addAnimation(this.scale_in);
        this.in.addAnimation(this.fade_in);
        this.out.addAnimation(this.fade_out);
        this.out.addAnimation(this.scale_out);
        this.arcRotation.setAnimationListener(new Animation.AnimationListener() { // from class: com.movieboxpro.android.view.widget.CircleProgress.Circlelayout.1
            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationRepeat(Animation animation) {
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationStart(Animation animation) {
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationEnd(Animation animation) {
                Circlelayout.this.first_click = false;
                Circlelayout.this.buttonimage.startAnimation(Circlelayout.this.out);
            }
        });
        this.out.setAnimationListener(new Animation.AnimationListener() { // from class: com.movieboxpro.android.view.widget.CircleProgress.Circlelayout.2
            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationRepeat(Animation animation) {
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationStart(Animation animation) {
                System.out.println("print this");
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationEnd(Animation animation) {
                Circlelayout.this.buttonimage.setVisibility(8);
                Circlelayout.this.buttonimage.setImageBitmap(Circlelayout.this.second_icon_bmp);
                Circlelayout.this.buttonimage.setVisibility(0);
                Circlelayout.this.buttonimage.startAnimation(Circlelayout.this.in);
                Circlelayout.this.arc_image.setVisibility(8);
                Circlelayout.this.full_circle_image.setVisibility(0);
                Circlelayout.this.cusview.setVisibility(0);
                Circlelayout.this.flg_frmwrk_mode = 2;
                PrintStream printStream = System.out;
                printStream.println("flg_frmwrk_mode" + Circlelayout.this.flg_frmwrk_mode);
            }
        });
        this.new_scale_in.setAnimationListener(new Animation.AnimationListener() { // from class: com.movieboxpro.android.view.widget.CircleProgress.Circlelayout.3
            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationRepeat(Animation animation) {
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationStart(Animation animation) {
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationEnd(Animation animation) {
                Circlelayout.this.cusview.setVisibility(8);
                Circlelayout.this.buttonimage.setVisibility(0);
                Circlelayout.this.buttonimage.setImageBitmap(Circlelayout.this.third_icon_bmp);
                Circlelayout.this.flg_frmwrk_mode = 3;
                Circlelayout.this.buttonimage.startAnimation(Circlelayout.this.in);
            }
        });
    }

    private void displayMetrics() {
        DisplayMetrics displayMetrics = getContext().getResources().getDisplayMetrics();
        Double.isNaN(r0);
        this.pix = (int) Math.sqrt(r0 * 0.0217d);
    }

    private void iconCreate() {
        Path path = new Path();
        this.play = path;
        int i = this.pix;
        path.moveTo((i * 40) / 100, (i * 36) / 100);
        Path path2 = this.play;
        int i2 = this.pix;
        path2.lineTo((i2 * 40) / 100, (i2 * 63) / 100);
        Path path3 = this.play;
        int i3 = this.pix;
        path3.lineTo((i3 * 69) / 100, (i3 * 50) / 100);
        this.play.close();
        Path path4 = new Path();
        this.stop = path4;
        int i4 = this.pix;
        path4.moveTo((i4 * 38) / 100, (i4 * 38) / 100);
        Path path5 = this.stop;
        int i5 = this.pix;
        path5.lineTo((i5 * 62) / 100, (i5 * 38) / 100);
        Path path6 = this.stop;
        int i6 = this.pix;
        path6.lineTo((i6 * 62) / 100, (i6 * 62) / 100);
        Path path7 = this.stop;
        int i7 = this.pix;
        path7.lineTo((i7 * 38) / 100, (i7 * 62) / 100);
        this.stop.close();
        Path path8 = new Path();
        this.download_triangle = path8;
        int i8 = this.pix;
        path8.moveTo((i8 * 375) / 1000, ((i8 / 2) + ((i8 * 625) / 10000)) - ((i8 * 3) / 100));
        Path path9 = this.download_triangle;
        int i9 = this.pix;
        path9.lineTo(i9 / 2, (((i9 * 625) / 1000) + ((i9 * 625) / 10000)) - ((i9 * 3) / 100));
        Path path10 = this.download_triangle;
        int i10 = this.pix;
        path10.lineTo((i10 * 625) / 1000, ((i10 / 2) + ((i10 * 625) / 10000)) - ((i10 * 3) / 100));
        this.download_triangle.close();
        Path path11 = new Path();
        this.download_rectangle = path11;
        int i11 = this.pix;
        path11.moveTo((i11 * 4375) / 10000, ((i11 / 2) + ((i11 * 625) / 10000)) - ((i11 * 3) / 100));
        Path path12 = this.download_rectangle;
        int i12 = this.pix;
        path12.lineTo((i12 * 5625) / 10000, ((i12 / 2) + ((i12 * 625) / 10000)) - ((i12 * 3) / 100));
        Path path13 = this.download_rectangle;
        int i13 = this.pix;
        path13.lineTo((i13 * 5625) / 10000, (((i13 * 375) / 1000) + ((i13 * 625) / 10000)) - ((i13 * 3) / 100));
        Path path14 = this.download_rectangle;
        int i14 = this.pix;
        path14.lineTo((i14 * 4375) / 10000, (((i14 * 375) / 1000) + ((i14 * 625) / 10000)) - ((i14 * 3) / 100));
        this.download_rectangle.close();
        Path path15 = new Path();
        this.tick = path15;
        int i15 = this.pix;
        path15.moveTo((i15 * 30) / 100, (i15 * 50) / 100);
        Path path16 = this.tick;
        int i16 = this.pix;
        path16.lineTo((i16 * 45) / 100, (i16 * 625) / 1000);
        Path path17 = this.tick;
        int i17 = this.pix;
        path17.lineTo((i17 * 65) / 100, (i17 * 350) / 1000);
    }

    public void setProgress(int i) {
        this.cusview.setupprogress(i);
        initialise();
    }

    public void init() {
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-2, -2);
        layoutParams.setMargins(10, 10, 10, 10);
        this.fillcircle.setVisibility(8);
        Bitmap.Config config = Bitmap.Config.ARGB_8888;
        int i = this.pix;
        Bitmap createBitmap = Bitmap.createBitmap(i, i, config);
        int i2 = this.pix;
        Bitmap createBitmap2 = Bitmap.createBitmap(i2, i2, config);
        int i3 = this.pix;
        Bitmap createBitmap3 = Bitmap.createBitmap(i3, i3, config);
        int i4 = this.pix;
        this.first_icon_bmp = Bitmap.createBitmap(i4, i4, config);
        int i5 = this.pix;
        this.second_icon_bmp = Bitmap.createBitmap(i5, i5, config);
        int i6 = this.pix;
        this.third_icon_bmp = Bitmap.createBitmap(i6, i6, config);
        Canvas canvas = new Canvas(this.first_icon_bmp);
        Canvas canvas2 = new Canvas(this.second_icon_bmp);
        Canvas canvas3 = new Canvas(this.third_icon_bmp);
        Canvas canvas4 = new Canvas(createBitmap3);
        Canvas canvas5 = new Canvas(createBitmap);
        Canvas canvas6 = new Canvas(createBitmap2);
        int i7 = this.pix;
        double d = i7;
        Double.isNaN(d);
        double d2 = i7;
        Double.isNaN(d2);
        System.out.println("full circle " + canvas5.getWidth() + canvas5.getHeight());
        int i8 = this.pix;
        double d3 = (double) i8;
        Double.isNaN(d3);
        double d4 = (double) i8;
        Double.isNaN(d4);
        this.rect = new RectF((float) (d * 0.05d), (float) (d3 * 0.05d), (float) (d2 * 0.95d), (float) (d4 * 0.95d));
        canvas.drawPath(this.play, this.fill_color);
        canvas2.drawPath(this.stop, this.icon_color);
        canvas3.drawPath(this.tick, this.final_icon_color);
        canvas5.drawArc(this.rect, 0.0f, 360.0f, false, this.stroke_color);
        canvas4.drawArc(this.rect, 0.0f, 360.0f, false, this.fill_color);
        canvas6.drawArc(this.rect, -80.0f, 340.0f, false, this.stroke_color);
        this.buttonimage.setImageBitmap(this.first_icon_bmp);
        this.flg_frmwrk_mode = 1;
        this.fillcircle.setImageBitmap(createBitmap3);
        this.full_circle_image.setImageBitmap(createBitmap);
        this.arc_image.setImageBitmap(createBitmap2);
        this.cusview.setVisibility(8);
        addView(this.full_circle_image, layoutParams);
        addView(this.arc_image, layoutParams);
        addView(this.fillcircle, layoutParams);
        addView(this.buttonimage, layoutParams);
        addView(this.cusview, layoutParams);
    }

    public void animation() {
        if (this.first_click || this.flg_frmwrk_mode != 1) {
            return;
        }
        this.first_click = true;
        this.full_circle_image.setVisibility(8);
        this.arc_image.setVisibility(0);
        this.arc_image.startAnimation(this.arcRotation);
    }

    public void finalAnimation() {
        this.buttonimage.setVisibility(8);
        this.fillcircle.setVisibility(0);
        this.fillcircle.startAnimation(this.new_scale_in);
    }

    public void reset() {
        this.cusview.reset();
        this.cusview.setVisibility(8);
        this.buttonimage.setImageBitmap(this.first_icon_bmp);
        this.flg_frmwrk_mode = 1;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        animation();
        System.out.println("Action onclick...");
    }
}
