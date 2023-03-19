package com.movieboxpro.android.adapter;

import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.mikhaellopez.circularprogressbar.CircularProgressBar;
import com.movieboxpro.android.R;
import com.movieboxpro.android.app.App;
import com.movieboxpro.android.db.dao.DownloadDao;
import com.movieboxpro.android.db.entity.Download;
import com.movieboxpro.android.model.DownloadInfo;
import com.movieboxpro.android.model.tv.TvDetail;
import com.movieboxpro.android.utils.CommonExtKt;
import com.movieboxpro.android.utils.CommonUtils;
import com.movieboxpro.android.utils.GlideUtils;
import com.movieboxpro.android.utils.TimeUtils;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
/* compiled from: TvDownloadAdapter.kt */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\u0013\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0018\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u00032\u0006\u0010\n\u001a\u00020\u0002H\u0014¨\u0006\u000b"}, d2 = {"Lcom/movieboxpro/android/adapter/TvDownloadAdapter;", "Lcom/chad/library/adapter/base/BaseQuickAdapter;", "Lcom/movieboxpro/android/model/tv/TvDetail$SeasonDetail;", "Lcom/chad/library/adapter/base/viewholder/BaseViewHolder;", "list", "", "(Ljava/util/List;)V", "convert", "", "helper", "item", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class TvDownloadAdapter extends BaseQuickAdapter<TvDetail.SeasonDetail, BaseViewHolder> {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TvDownloadAdapter(List<TvDetail.SeasonDetail> list) {
        super(R.layout.adapter_tv_download_item, list);
        Intrinsics.checkNotNullParameter(list, "list");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.chad.library.adapter.base.BaseQuickAdapter
    public void convert(BaseViewHolder helper, TvDetail.SeasonDetail item) {
        Long l;
        Long l2;
        Long l3;
        Long l4;
        Intrinsics.checkNotNullParameter(helper, "helper");
        Intrinsics.checkNotNullParameter(item, "item");
        ImageView imageView = (ImageView) helper.getView(R.id.ivTag);
        ImageView imageView2 = (ImageView) helper.getView(R.id.ivPoster);
        ImageView imageView3 = (ImageView) helper.getView(R.id.ivPlayStatus);
        ProgressBar progressBar = (ProgressBar) helper.getView(R.id.progressBar);
        TextView textView = (TextView) helper.getView(R.id.tvName);
        TextView textView2 = (TextView) helper.getView(R.id.tvRate);
        TextView textView3 = (TextView) helper.getView(R.id.tvTime);
        TextView textView4 = (TextView) helper.getView(R.id.tvDuration);
        TextView textView5 = (TextView) helper.getView(R.id.tvLastViewTime);
        TextView textView6 = (TextView) helper.getView(R.id.tvDesc);
        ImageView imageView4 = (ImageView) helper.getView(R.id.ivDownload);
        ImageView imageView5 = (ImageView) helper.getView(R.id.ivDownloaded);
        FrameLayout frameLayout = (FrameLayout) helper.getView(R.id.flDownloading);
        CircularProgressBar circularProgressBar = (CircularProgressBar) helper.getView(R.id.progress);
        ProgressBar progressBar2 = (ProgressBar) helper.getView(R.id.downloadReady);
        String str = item.quality_tag;
        if (str == null || str.length() == 0) {
            CommonExtKt.gone(imageView);
        } else {
            CommonExtKt.visible(imageView);
            imageView.setImageResource(CommonUtils.getMovieTag(item.quality_tag));
        }
        GlideUtils.loadLandGifHolder(getContext(), item.thumbs, imageView2);
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        String format = String.format("%s %s", Arrays.copyOf(new Object[]{Integer.valueOf(item.episode), item.title}, 2));
        Intrinsics.checkNotNullExpressionValue(format, "format(format, *args)");
        textView.setText(format);
        textView3.setText(item.released);
        StringCompanionObject stringCompanionObject2 = StringCompanionObject.INSTANCE;
        String format2 = String.format("%s minutes", Arrays.copyOf(new Object[]{item.runtime}, 1));
        Intrinsics.checkNotNullExpressionValue(format2, "format(format, *args)");
        textView4.setText(format2);
        String str2 = item.synopsis;
        if (str2 == null || str2.length() == 0) {
            CommonExtKt.gone(textView6);
        } else {
            CommonExtKt.visible(textView6);
            textView6.setText(item.synopsis);
        }
        String str3 = item.imdb_rating;
        if (str3 == null || str3.length() == 0) {
            textView2.setTextColor(CommonExtKt.colorInt(getContext(), (int) R.color.white_70alpha));
            textView2.setText("-.-");
        } else {
            textView2.setTextColor(CommonExtKt.colorInt(getContext(), (int) R.color.white));
            textView2.setText(item.imdb_rating);
        }
        HashMap<String, Long> hashMap = item.play_progress;
        if (hashMap == null || (l = hashMap.get(DownloadInfo.DOWNLOAD_OVER)) == null) {
            l = -1L;
        }
        long longValue = l.longValue();
        if (longValue == 1) {
            CommonExtKt.gone(progressBar);
            CommonExtKt.visible(imageView3);
            TextView textView7 = textView5;
            CommonExtKt.visible(textView7);
            imageView3.setImageResource(R.mipmap.ic_tv_play_complete);
            HashMap<String, Long> hashMap2 = item.play_progress;
            if (hashMap2 == null || (l4 = hashMap2.get("last_time")) == null) {
                l4 = 0L;
            }
            long longValue2 = l4.longValue();
            if (longValue2 > 0) {
                CommonExtKt.visible(textView7);
                textView5.setText(TimeUtils.formatTime(longValue2 * 1000));
            } else {
                CommonExtKt.gone(textView7);
            }
        } else if (longValue == 0) {
            TextView textView8 = textView5;
            CommonExtKt.visible(textView8);
            CommonExtKt.gone(imageView3);
            CommonExtKt.visible(progressBar);
            HashMap<String, Long> hashMap3 = item.play_progress;
            if (hashMap3 == null || (l2 = hashMap3.get("last_time")) == null) {
                l2 = 0L;
            }
            long longValue3 = l2.longValue();
            if (longValue3 > 0) {
                CommonExtKt.visible(textView8);
                textView5.setText(TimeUtils.formatTime(longValue3 * 1000));
            } else {
                CommonExtKt.gone(textView8);
            }
            String str4 = item.runtime;
            progressBar.setMax((str4 != null ? Integer.parseInt(str4) : 0) * 60);
            HashMap<String, Long> hashMap4 = item.play_progress;
            if (hashMap4 == null || (l3 = hashMap4.get("seconds")) == null) {
                l3 = 0L;
            }
            progressBar.setProgress((int) l3.longValue());
        } else if (longValue == -1) {
            CommonExtKt.gone(progressBar);
            CommonExtKt.gone(textView5);
            CommonExtKt.visible(imageView3);
            imageView3.setImageResource(R.mipmap.ic_tv_play);
        }
        if (item.code_file != 1) {
            CommonExtKt.gone(imageView3);
            CommonExtKt.gone(imageView4);
            CommonExtKt.gone(imageView5);
            CommonExtKt.gone(frameLayout);
            CommonExtKt.gone(circularProgressBar);
            CommonExtKt.gone(progressBar2);
            return;
        }
        DownloadDao downloadDao = App.getDB().downloadDao();
        Download findByType = downloadDao.findByType(2, item.tid + '_' + ((Object) item.id));
        if (findByType != null) {
            int statue = findByType.getStatue();
            if (statue == 0) {
                CommonExtKt.gone(imageView4);
                CommonExtKt.gone(imageView5);
                CommonExtKt.gone(frameLayout);
                CommonExtKt.visible(progressBar2);
                return;
            } else if (statue == 1) {
                CommonExtKt.gone(imageView4);
                CommonExtKt.gone(imageView5);
                ProgressBar progressBar3 = progressBar2;
                CommonExtKt.gone(progressBar3);
                CommonExtKt.visible(frameLayout);
                CommonExtKt.gone(progressBar3);
                circularProgressBar.setProgress(findByType.getProgress());
                return;
            } else if (statue == 2) {
                CommonExtKt.gone(imageView4);
                CommonExtKt.visible(imageView5);
                CommonExtKt.gone(frameLayout);
                CommonExtKt.gone(progressBar2);
                return;
            } else if (statue == 3) {
                CommonExtKt.gone(imageView4);
                CommonExtKt.gone(imageView5);
                CommonExtKt.gone(progressBar2);
                CommonExtKt.visible(frameLayout);
                circularProgressBar.setProgress(findByType.getProgress());
                return;
            } else {
                CommonExtKt.visible(imageView4);
                CommonExtKt.gone(imageView5);
                CommonExtKt.gone(frameLayout);
                CommonExtKt.gone(progressBar2);
                return;
            }
        }
        CommonExtKt.visible(imageView4);
        CommonExtKt.gone(imageView5);
        CommonExtKt.gone(frameLayout);
        CommonExtKt.gone(progressBar2);
    }
}
