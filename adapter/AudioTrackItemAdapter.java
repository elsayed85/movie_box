package com.movieboxpro.android.adapter;

import android.widget.ImageView;
import android.widget.TextView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.google.firebase.crashlytics.internal.common.IdManager;
import com.movieboxpro.android.R;
import com.movieboxpro.android.model.AudioTrackModel;
import com.movieboxpro.android.utils.CommonExtKt;
import com.movieboxpro.android.utils.FileUtils;
import com.movieboxpro.android.utils.VideoIconUtils;
import java.text.DecimalFormat;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
/* compiled from: AudioTrackItemAdapter.kt */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B!\u0012\u001a\u0010\u0004\u001a\u0016\u0012\u0004\u0012\u00020\u0002\u0018\u00010\u0005j\n\u0012\u0004\u0012\u00020\u0002\u0018\u0001`\u0006¢\u0006\u0002\u0010\u0007J\u0018\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u00032\u0006\u0010\u000b\u001a\u00020\u0002H\u0014¨\u0006\f"}, d2 = {"Lcom/movieboxpro/android/adapter/AudioTrackItemAdapter;", "Lcom/chad/library/adapter/base/BaseQuickAdapter;", "Lcom/movieboxpro/android/model/AudioTrackModel;", "Lcom/chad/library/adapter/base/viewholder/BaseViewHolder;", "list", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "(Ljava/util/ArrayList;)V", "convert", "", "helper", "item", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class AudioTrackItemAdapter extends BaseQuickAdapter<AudioTrackModel, BaseViewHolder> {
    public AudioTrackItemAdapter(ArrayList<AudioTrackModel> arrayList) {
        super(R.layout.adapter_audio_track_item, arrayList);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.chad.library.adapter.base.BaseQuickAdapter
    public void convert(BaseViewHolder helper, AudioTrackModel item) {
        int i;
        Intrinsics.checkNotNullParameter(helper, "helper");
        Intrinsics.checkNotNullParameter(item, "item");
        TextView textView = (TextView) helper.getView(R.id.tv_size);
        if (item.getSize_bytes() == 0) {
            CommonExtKt.gone(textView);
        } else {
            CommonExtKt.visible(textView);
            textView.setText(FileUtils.byte2FitMemorySize(item.getSize_bytes()));
        }
        ImageView imageView = (ImageView) helper.getView(R.id.iv_selected);
        if (item.isSelect()) {
            CommonExtKt.visible(imageView);
        } else {
            CommonExtKt.gone(imageView);
        }
        TextView textView2 = (TextView) helper.getView(R.id.tvFrequency);
        String frequency = item.getFrequency();
        boolean z = true;
        if (frequency == null || StringsKt.isBlank(frequency)) {
            CommonExtKt.gone(textView2);
        } else {
            textView2.setText("");
            DecimalFormat decimalFormat = new DecimalFormat(IdManager.DEFAULT_VERSION_NAME);
            String frequency2 = item.getFrequency();
            Intrinsics.checkNotNullExpressionValue(frequency2, "item.frequency");
            double parseInt = Integer.parseInt(StringsKt.trim((CharSequence) StringsKt.replace$default(frequency2, "Hz", "", false, 4, (Object) null)).toString());
            Double.isNaN(parseInt);
            textView2.setText(decimalFormat.format(parseInt / 1000.0d));
        }
        ImageView imageView2 = (ImageView) helper.getView(R.id.ivLang);
        ImageView imageView3 = (ImageView) helper.getView(R.id.ivAudioCode);
        ImageView imageView4 = (ImageView) helper.getView(R.id.ivChannel);
        TextView textView3 = (TextView) helper.getView(R.id.tvRate);
        ImageView imageView5 = (ImageView) helper.getView(R.id.ivVip);
        TextView textView4 = (TextView) helper.getView(R.id.tvLang);
        if (item.getVip_only() == 0) {
            CommonExtKt.gone(imageView2);
            CommonExtKt.gone(imageView5);
        } else {
            CommonExtKt.visible(imageView5);
        }
        String bitstream = item.getBitstream();
        if (!(bitstream == null || StringsKt.isBlank(bitstream))) {
            try {
                String bitstream2 = item.getBitstream();
                Intrinsics.checkNotNullExpressionValue(bitstream2, "item.bitstream");
                i = Integer.parseInt(bitstream2);
            } catch (Exception unused) {
                i = 0;
            }
            if (i < 1014) {
                StringBuilder sb = new StringBuilder();
                sb.append(i);
                sb.append('K');
                textView3.setText(sb.toString());
            } else {
                textView3.setText(Intrinsics.stringPlus(new DecimalFormat(IdManager.DEFAULT_VERSION_NAME).format(Float.valueOf(i / 1024.0f)), "M"));
            }
            CommonExtKt.visible(textView3);
        } else {
            CommonExtKt.gone(textView3);
        }
        int audioChannelIcon = VideoIconUtils.INSTANCE.getAudioChannelIcon(item.getChannel());
        if (audioChannelIcon == -1) {
            CommonExtKt.gone(imageView4);
        } else {
            imageView4.setImageResource(audioChannelIcon);
        }
        int audioCodeIcon = VideoIconUtils.INSTANCE.getAudioCodeIcon(item.getFormat());
        if (audioCodeIcon == -1) {
            CommonExtKt.gone(imageView3);
        } else {
            CommonExtKt.visible(imageView3);
            imageView3.setImageResource(audioCodeIcon);
        }
        int audioLangIcon = VideoIconUtils.INSTANCE.getAudioLangIcon(item.getLang());
        if (audioLangIcon == -1) {
            CommonExtKt.gone(imageView2);
            String lang = item.getLang();
            if (lang != null && !StringsKt.isBlank(lang)) {
                z = false;
            }
            if (z) {
                CommonExtKt.gone(textView4);
                return;
            }
            CommonExtKt.visible(textView4);
            textView4.setText(item.getLang());
            return;
        }
        CommonExtKt.gone(textView4);
        CommonExtKt.visible(imageView2);
        imageView2.setImageResource(audioLangIcon);
    }
}
