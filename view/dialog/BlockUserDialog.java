package com.movieboxpro.android.view.dialog;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.google.android.exoplayer2.text.ttml.TtmlNode;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.movieboxpro.android.R;
import com.movieboxpro.android.base.BaseBindingBottomDialogFragment;
import com.movieboxpro.android.databinding.DialogBlockUserBinding;
import com.movieboxpro.android.utils.Api;
import com.movieboxpro.android.utils.CommonExtKt;
import com.movieboxpro.android.utils.FragmentArgsKt;
import com.movieboxpro.android.utils.RxSubscribersKt;
import com.movieboxpro.android.view.dialog.DialogAction;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.MutablePropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.properties.ReadWriteProperty;
import kotlin.reflect.KProperty;
/* compiled from: BlockUserDialog.kt */
@Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u000b\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 )2\u00020\u0001:\u0001)B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u001b\u001a\u00020\u001cH\u0002J\b\u0010\u001d\u001a\u00020\u001cH\u0016J\b\u0010\u001e\u001a\u00020\u001cH\u0016J\b\u0010\u001f\u001a\u00020\u001cH\u0016J$\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020#2\b\u0010$\u001a\u0004\u0018\u00010%2\b\u0010&\u001a\u0004\u0018\u00010'H\u0016J\u000e\u0010(\u001a\u00020\u001c2\u0006\u0010\u000e\u001a\u00020\u000fR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.¢\u0006\u0002\n\u0000R+\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u00068B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\b\f\u0010\r\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\u0010\u0010\u000e\u001a\u0004\u0018\u00010\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R/\u0010\u0011\u001a\u0004\u0018\u00010\u00102\b\u0010\u0005\u001a\u0004\u0018\u00010\u00108B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\b\u0016\u0010\r\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015R/\u0010\u0017\u001a\u0004\u0018\u00010\u00102\b\u0010\u0005\u001a\u0004\u0018\u00010\u00108B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\b\u001a\u0010\r\u001a\u0004\b\u0018\u0010\u0013\"\u0004\b\u0019\u0010\u0015¨\u0006*"}, d2 = {"Lcom/movieboxpro/android/view/dialog/BlockUserDialog;", "Lcom/movieboxpro/android/base/BaseBindingBottomDialogFragment;", "()V", "binding", "Lcom/movieboxpro/android/databinding/DialogBlockUserBinding;", "<set-?>", "", "blocked", "getBlocked", "()I", "setBlocked", "(I)V", "blocked$delegate", "Lkotlin/properties/ReadWriteProperty;", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "Lcom/movieboxpro/android/view/dialog/DialogAction$ActionListener;", "", "name", "getName", "()Ljava/lang/String;", "setName", "(Ljava/lang/String;)V", "name$delegate", "uid", "getUid", "setUid", "uid$delegate", "blockUser", "", "initData", "initListener", "initView", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", TtmlNode.RUBY_CONTAINER, "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "setListener", "Companion", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class BlockUserDialog extends BaseBindingBottomDialogFragment {
    static final /* synthetic */ KProperty<Object>[] $$delegatedProperties = {Reflection.mutableProperty1(new MutablePropertyReference1Impl(BlockUserDialog.class, "uid", "getUid()Ljava/lang/String;", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(BlockUserDialog.class, "name", "getName()Ljava/lang/String;", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(BlockUserDialog.class, "blocked", "getBlocked()I", 0))};
    public static final Companion Companion = new Companion(null);
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    private DialogBlockUserBinding binding;
    private final ReadWriteProperty blocked$delegate;
    private DialogAction.ActionListener listener;
    private final ReadWriteProperty name$delegate;
    private final ReadWriteProperty uid$delegate;

    @Override // com.movieboxpro.android.base.BaseBindingBottomDialogFragment
    public void _$_clearFindViewByIdCache() {
        this._$_findViewCache.clear();
    }

    @Override // com.movieboxpro.android.base.BaseBindingBottomDialogFragment
    public View _$_findCachedViewById(int i) {
        View findViewById;
        Map<Integer, View> map = this._$_findViewCache;
        View view = map.get(Integer.valueOf(i));
        if (view == null) {
            View view2 = getView();
            if (view2 == null || (findViewById = view2.findViewById(i)) == null) {
                return null;
            }
            map.put(Integer.valueOf(i), findViewById);
            return findViewById;
        }
        return view;
    }

    @Override // com.movieboxpro.android.base.BaseBindingBottomDialogFragment
    public void initData() {
    }

    @Override // com.movieboxpro.android.base.BaseBindingBottomDialogFragment, androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    public BlockUserDialog() {
        BlockUserDialog blockUserDialog = this;
        this.uid$delegate = FragmentArgsKt.argOrNull(blockUserDialog);
        this.name$delegate = FragmentArgsKt.argOrNull(blockUserDialog);
        this.blocked$delegate = FragmentArgsKt.argOrDefault(blockUserDialog, 0);
    }

    private final String getUid() {
        return (String) this.uid$delegate.getValue(this, $$delegatedProperties[0]);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setUid(String str) {
        this.uid$delegate.setValue(this, $$delegatedProperties[0], str);
    }

    private final String getName() {
        return (String) this.name$delegate.getValue(this, $$delegatedProperties[1]);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setName(String str) {
        this.name$delegate.setValue(this, $$delegatedProperties[1], str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final int getBlocked() {
        return ((Number) this.blocked$delegate.getValue(this, $$delegatedProperties[2])).intValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setBlocked(int i) {
        this.blocked$delegate.setValue(this, $$delegatedProperties[2], Integer.valueOf(i));
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup viewGroup, Bundle bundle) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        ViewDataBinding inflate = DataBindingUtil.inflate(inflater, R.layout.dialog_block_user, viewGroup, false);
        Intrinsics.checkNotNullExpressionValue(inflate, "inflate(inflater, R.layo…k_user, container, false)");
        DialogBlockUserBinding dialogBlockUserBinding = (DialogBlockUserBinding) inflate;
        this.binding = dialogBlockUserBinding;
        if (dialogBlockUserBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            dialogBlockUserBinding = null;
        }
        View root = dialogBlockUserBinding.getRoot();
        Intrinsics.checkNotNullExpressionValue(root, "binding.root");
        return root;
    }

    public final void setListener(DialogAction.ActionListener listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        this.listener = listener;
    }

    @Override // com.movieboxpro.android.base.BaseBindingBottomDialogFragment
    public void initListener() {
        DialogBlockUserBinding dialogBlockUserBinding = this.binding;
        DialogBlockUserBinding dialogBlockUserBinding2 = null;
        if (dialogBlockUserBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            dialogBlockUserBinding = null;
        }
        dialogBlockUserBinding.tvCancel.setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.dialog.-$$Lambda$BlockUserDialog$nJ3Z6NKkp2iDjMgXnamXOtj9mIc
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                BlockUserDialog.m935initListener$lambda0(BlockUserDialog.this, view);
            }
        });
        DialogBlockUserBinding dialogBlockUserBinding3 = this.binding;
        if (dialogBlockUserBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            dialogBlockUserBinding2 = dialogBlockUserBinding3;
        }
        dialogBlockUserBinding2.tvBlock.setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.dialog.-$$Lambda$BlockUserDialog$irEDsiHr8IoNJZuof4S_KM71gho
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                BlockUserDialog.m936initListener$lambda1(BlockUserDialog.this, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initListener$lambda-0  reason: not valid java name */
    public static final void m935initListener$lambda0(BlockUserDialog this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.dismissAllowingStateLoss();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initListener$lambda-1  reason: not valid java name */
    public static final void m936initListener$lambda1(BlockUserDialog this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.blockUser();
    }

    private final void blockUser() {
        RxSubscribersKt.subscribeTo$default(RxSubscribersKt.transformMsg(CommonExtKt.request$default(Api.INSTANCE.blockUser(getUid(), getBlocked() == 1 ? "delete" : "add"), null, 1, null), this), new BlockUserDialog$blockUser$1(this), null, new BlockUserDialog$blockUser$2(this), null, new BlockUserDialog$blockUser$3(this), 10, null);
    }

    @Override // com.movieboxpro.android.base.BaseBindingBottomDialogFragment
    public void initView() {
        DialogBlockUserBinding dialogBlockUserBinding = null;
        if (getBlocked() == 1) {
            DialogBlockUserBinding dialogBlockUserBinding2 = this.binding;
            if (dialogBlockUserBinding2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                dialogBlockUserBinding2 = null;
            }
            dialogBlockUserBinding2.tvBlock.setText("Unblock");
            DialogBlockUserBinding dialogBlockUserBinding3 = this.binding;
            if (dialogBlockUserBinding3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
            } else {
                dialogBlockUserBinding = dialogBlockUserBinding3;
            }
            TextView textView = dialogBlockUserBinding.tvBlockDesc;
            Intrinsics.checkNotNullExpressionValue(textView, "binding.tvBlockDesc");
            CommonExtKt.gone(textView);
            return;
        }
        DialogBlockUserBinding dialogBlockUserBinding4 = this.binding;
        if (dialogBlockUserBinding4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            dialogBlockUserBinding4 = null;
        }
        dialogBlockUserBinding4.tvBlock.setText("Block");
        DialogBlockUserBinding dialogBlockUserBinding5 = this.binding;
        if (dialogBlockUserBinding5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            dialogBlockUserBinding = dialogBlockUserBinding5;
        }
        dialogBlockUserBinding.tvBlockDesc.setText("Block \"" + ((Object) getName()) + "\"? After blocking, you will not see all of " + ((Object) getName()) + "'s comments");
    }

    /* compiled from: BlockUserDialog.kt */
    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\"\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\u00062\u0006\u0010\b\u001a\u00020\t¨\u0006\n"}, d2 = {"Lcom/movieboxpro/android/view/dialog/BlockUserDialog$Companion;", "", "()V", "newInstance", "Lcom/movieboxpro/android/view/dialog/BlockUserDialog;", "uid", "", "name", "blocked", "", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    /* loaded from: classes3.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final BlockUserDialog newInstance(String str, String str2, int i) {
            BlockUserDialog blockUserDialog = new BlockUserDialog();
            blockUserDialog.setUid(str);
            blockUserDialog.setName(str2);
            blockUserDialog.setBlocked(i);
            return blockUserDialog;
        }
    }
}
