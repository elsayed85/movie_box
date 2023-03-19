package com.movieboxpro.android.view.dialog;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatDialog;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.movieboxpro.android.R;
import com.movieboxpro.android.view.dialog.ListDialog;
import java.util.Arrays;
import java.util.List;
/* loaded from: classes3.dex */
public class ListDialog extends AppCompatDialog {
    private Context mContext;

    /* loaded from: classes3.dex */
    public interface OnItemClickListener {
        void onClick(View view, int i);
    }

    public ListDialog(Context context, int i) {
        super(context, i);
        this.mContext = context;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.appcompat.app.AppCompatDialog, android.app.Dialog
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        initDialog();
    }

    private void initDialog() {
        Window window = getWindow();
        if (window == null) {
            return;
        }
        window.setGravity(17);
        WindowManager.LayoutParams attributes = window.getAttributes();
        double d = this.mContext.getResources().getDisplayMetrics().widthPixels;
        Double.isNaN(d);
        attributes.width = (int) (d * 0.8d);
        attributes.height = -2;
        window.setAttributes(attributes);
    }

    /* loaded from: classes3.dex */
    public static class Builder {
        private Context context;
        private boolean isHideTitle;
        private RecyclerView.Adapter mAdapter;
        private OnItemClickListener mOnPositiveListener;
        private List<String> strings;
        private String title = "提示";
        private boolean cancelable = true;
        private boolean canceledOnTouchOutside = true;

        public Builder(Context context) {
            this.context = context;
        }

        public Builder setTitle(String str) {
            this.title = str;
            return this;
        }

        public Builder setItems(List<String> list) {
            this.strings = list;
            return this;
        }

        public Builder setItems(String[] strArr) {
            this.strings = Arrays.asList(strArr);
            return this;
        }

        public Builder setTitleVisible(boolean z) {
            this.isHideTitle = z;
            return this;
        }

        public Builder setAdapter(RecyclerView.Adapter adapter) {
            this.mAdapter = adapter;
            return this;
        }

        public Builder setCancelable(boolean z) {
            this.cancelable = z;
            return this;
        }

        public Builder setCanceledOnTouchOutside(boolean z) {
            this.canceledOnTouchOutside = z;
            return this;
        }

        public Builder addAction(OnItemClickListener onItemClickListener) {
            this.mOnPositiveListener = onItemClickListener;
            return this;
        }

        public ListDialog create() {
            final ListDialog listDialog = new ListDialog(this.context, R.style.DialogStyle);
            View inflate = LayoutInflater.from(this.context).inflate(R.layout.list_dialog_layout, (ViewGroup) null);
            listDialog.addContentView(inflate, new ViewGroup.LayoutParams(-1, -2));
            View findViewById = inflate.findViewById(R.id.view_line);
            TextView textView = (TextView) inflate.findViewById(R.id.tv_title);
            if (this.isHideTitle) {
                textView.setVisibility(8);
                findViewById.setVisibility(8);
            } else {
                textView.setText(this.title);
            }
            RecyclerView recyclerView = (RecyclerView) inflate.findViewById(R.id.recyclerView);
            RecyclerView.Adapter adapter = this.mAdapter;
            if (adapter != null) {
                recyclerView.setAdapter(adapter);
            } else {
                BaseQuickAdapter<String, BaseViewHolder> baseQuickAdapter = new BaseQuickAdapter<String, BaseViewHolder>(R.layout.adapter_text_item, this.strings) { // from class: com.movieboxpro.android.view.dialog.ListDialog.Builder.1
                    /* JADX INFO: Access modifiers changed from: protected */
                    @Override // com.chad.library.adapter.base.BaseQuickAdapter
                    public void convert(BaseViewHolder baseViewHolder, String str) {
                        baseViewHolder.setText(R.id.textView, str);
                    }
                };
                baseQuickAdapter.setOnItemClickListener(new com.chad.library.adapter.base.listener.OnItemClickListener() { // from class: com.movieboxpro.android.view.dialog.-$$Lambda$ListDialog$Builder$U3Nhz-bb3PBODDL72QlcptmymeM
                    @Override // com.chad.library.adapter.base.listener.OnItemClickListener
                    public final void onItemClick(BaseQuickAdapter baseQuickAdapter2, View view, int i) {
                        ListDialog.Builder.this.lambda$create$0$ListDialog$Builder(listDialog, baseQuickAdapter2, view, i);
                    }
                });
                recyclerView.setAdapter(baseQuickAdapter);
            }
            recyclerView.setLayoutManager(new LinearLayoutManager(this.context));
            listDialog.setCancelable(this.cancelable);
            listDialog.setCanceledOnTouchOutside(this.canceledOnTouchOutside);
            return listDialog;
        }

        public /* synthetic */ void lambda$create$0$ListDialog$Builder(ListDialog listDialog, BaseQuickAdapter baseQuickAdapter, View view, int i) {
            listDialog.dismiss();
            OnItemClickListener onItemClickListener = this.mOnPositiveListener;
            if (onItemClickListener != null) {
                onItemClickListener.onClick(view, i);
            }
        }
    }
}
