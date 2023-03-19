package com.movieboxpro.android.adapter;

import android.widget.ImageView;
import android.widget.TextView;
import com.chad.library.adapter.base.delegate.BaseMultiTypeDelegate;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.movieboxpro.android.R;
import com.movieboxpro.android.model.ChatMsgModel;
import com.movieboxpro.android.utils.CommonExtKt;
import com.movieboxpro.android.utils.GlideUtils;
import com.movieboxpro.android.utils.TimeUtils;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: ChatAdapter.kt */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\u0005¢\u0006\u0002\u0010\u0004J\u0018\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\u0002H\u0014¨\u0006\t"}, d2 = {"Lcom/movieboxpro/android/adapter/ChatAdapter;", "Lcom/movieboxpro/android/adapter/BaseLoadmoreDelegateMultiAdapter;", "Lcom/movieboxpro/android/model/ChatMsgModel;", "Lcom/chad/library/adapter/base/viewholder/BaseViewHolder;", "()V", "convert", "", "helper", "item", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class ChatAdapter extends BaseLoadmoreDelegateMultiAdapter<ChatMsgModel, BaseViewHolder> {
    public ChatAdapter() {
        super(null);
        setMultiTypeDelegate(new BaseMultiTypeDelegate<ChatMsgModel>() { // from class: com.movieboxpro.android.adapter.ChatAdapter.1
            @Override // com.chad.library.adapter.base.delegate.BaseMultiTypeDelegate
            public int getItemType(List<? extends ChatMsgModel> data, int i) {
                Intrinsics.checkNotNullParameter(data, "data");
                return data.get(i).getIsYou();
            }
        });
        BaseMultiTypeDelegate<ChatMsgModel> multiTypeDelegate = getMultiTypeDelegate();
        Intrinsics.checkNotNull(multiTypeDelegate);
        multiTypeDelegate.addItemType(1, R.layout.adapter_chat_left_item);
        BaseMultiTypeDelegate<ChatMsgModel> multiTypeDelegate2 = getMultiTypeDelegate();
        Intrinsics.checkNotNull(multiTypeDelegate2);
        multiTypeDelegate2.addItemType(0, R.layout.adapter_chat_right_item);
        addChildClickViewIds(R.id.ivAvatar);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.chad.library.adapter.base.BaseQuickAdapter
    public void convert(BaseViewHolder helper, ChatMsgModel item) {
        Intrinsics.checkNotNullParameter(helper, "helper");
        Intrinsics.checkNotNullParameter(item, "item");
        TextView textView = (TextView) helper.getView(R.id.tvTime);
        if (item.getDateline() - (helper.getAdapterPosition() > 0 ? getItem(helper.getAdapterPosition() - 1).getDateline() : 0L) > 300) {
            CommonExtKt.visible(textView);
            textView.setText(TimeUtils.formatTime(item.getDateline() * 1000));
        } else {
            CommonExtKt.gone(textView);
        }
        helper.setText(R.id.tvContent, item.getMessage());
        GlideUtils.load(getContext(), item.getAvatar(), (ImageView) helper.getView(R.id.ivAvatar), (int) R.mipmap.ic_panda_forum_default_avatar);
    }
}
