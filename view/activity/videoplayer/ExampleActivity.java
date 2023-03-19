package com.movieboxpro.android.view.activity.videoplayer;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.FrameLayout;
import androidx.appcompat.app.AppCompatActivity;
import com.dl7.player.media.IjkVideoView;
import com.movieboxpro.android.R;
import com.movieboxpro.android.view.activity.videoplayer.floatmanager.FloatManager;
/* loaded from: classes3.dex */
public class ExampleActivity extends AppCompatActivity {
    private IjkVideoView ijkVideoView;
    private FloatManager mPIPManager;

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_normal_player);
        getFloat();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onPause() {
        super.onPause();
        this.ijkVideoView.pause();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        this.ijkVideoView.resume();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        this.ijkVideoView.destroy();
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        super.onBackPressed();
    }

    public void getFloat() {
        FrameLayout frameLayout = (FrameLayout) findViewById(R.id.player_container);
        FloatManager floatManager = FloatManager.getInstance();
        this.mPIPManager = floatManager;
        this.ijkVideoView = floatManager.getIjkVideoViews();
        if (this.mPIPManager.isStartFloatWindow()) {
            this.mPIPManager.stopFloatWindow();
        }
        removePlayerFormParent();
        frameLayout.addView(this.ijkVideoView);
    }

    private void removePlayerFormParent() {
        ViewParent parent = this.ijkVideoView.getParent();
        if (parent instanceof ViewGroup) {
            ((ViewGroup) parent).removeView(this.ijkVideoView);
        }
    }

    public void startFloatWindow(View view) {
        this.mPIPManager.startFloatWindow();
    }
}
