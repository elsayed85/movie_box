package com.movieboxpro.android.view.activity.settings;

import android.content.Context;
import android.graphics.Color;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IFillFormatter;
import com.github.mikephil.charting.interfaces.dataprovider.LineDataProvider;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.movieboxpro.android.R;
import com.movieboxpro.android.model.common.NetTestModel;
import com.movieboxpro.android.utils.CommonExtKt;
import com.movieboxpro.android.utils.CommonUtils;
import com.movieboxpro.android.utils.SpanUtils;
import com.movieboxpro.android.utils.TimeUtils;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
/* compiled from: TestSpeedActivity.kt */
@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\n¢\u0006\u0002\b\u0006"}, d2 = {"<anonymous>", "", "helper", "Lcom/chad/library/adapter/base/viewholder/BaseViewHolder;", "item", "Lcom/movieboxpro/android/model/common/NetTestModel;", "invoke"}, k = 3, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
final class TestSpeedActivity$initData$1 extends Lambda implements Function2<BaseViewHolder, NetTestModel, Unit> {
    final /* synthetic */ TestSpeedActivity this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TestSpeedActivity$initData$1(TestSpeedActivity testSpeedActivity) {
        super(2);
        this.this$0 = testSpeedActivity;
    }

    @Override // kotlin.jvm.functions.Function2
    public /* bridge */ /* synthetic */ Unit invoke(BaseViewHolder baseViewHolder, NetTestModel netTestModel) {
        invoke2(baseViewHolder, netTestModel);
        return Unit.INSTANCE;
    }

    /* renamed from: invoke  reason: avoid collision after fix types in other method */
    public final void invoke2(BaseViewHolder helper, NetTestModel item) {
        boolean z;
        String formatSpeed;
        String formatSpeed2;
        Intrinsics.checkNotNullParameter(helper, "helper");
        Intrinsics.checkNotNullParameter(item, "item");
        TestSpeedActivity testSpeedActivity = this.this$0;
        int itemType = item.getItemType();
        if (itemType != 1) {
            if (itemType != 2) {
                return;
            }
            SpanUtils with = SpanUtils.with((TextView) helper.getView(R.id.tvOtherSites));
            Intrinsics.checkNotNullExpressionValue(with, "with(title)");
            SpanUtils bold = CommonExtKt.addText(with, "Other sites  ", 14, R.color.white_38alpha).setBold();
            Intrinsics.checkNotNullExpressionValue(bold, "with(title).addText(\"Oth….white_38alpha).setBold()");
            CommonExtKt.addText(bold, "(Compared)", 12, R.color.white_38alpha).create();
            return;
        }
        helper.setText(R.id.tvServerName, item.country);
        helper.setText(R.id.tvServerInfo, item.description);
        final LineChart lineChart = (LineChart) helper.getView(R.id.lineChart);
        ConstraintLayout constraintLayout = (ConstraintLayout) helper.getView(R.id.constraintLayout);
        ImageView imageView = (ImageView) helper.getView(R.id.ivSelected);
        if (item.isSelect) {
            constraintLayout.setBackgroundColor(Color.parseColor("#262626"));
            CommonExtKt.visible(imageView);
        } else {
            constraintLayout.setBackgroundColor(CommonExtKt.colorInt((Context) testSpeedActivity, (int) R.color.color_main));
            CommonExtKt.gone(imageView);
        }
        ProgressBar progressBar = (ProgressBar) helper.getView(R.id.loading);
        ImageView imageView2 = (ImageView) helper.getView(R.id.ivAction);
        TextView textView = (TextView) helper.getView(R.id.tvSpeed);
        int status = item.getStatus();
        if (status == 0) {
            CommonExtKt.invisible(lineChart);
            z = testSpeedActivity.isAutoTesting;
            if (z) {
                CommonExtKt.invisible(imageView2);
            } else {
                CommonExtKt.visible(imageView2);
            }
            imageView2.setImageResource(R.mipmap.ic_speed_test_refresh);
            CommonExtKt.gone(textView);
            CommonExtKt.gone(progressBar);
        } else if (status != 1) {
            if (status == 2) {
                CommonExtKt.gone(progressBar);
                CommonExtKt.invisible(lineChart);
                CommonExtKt.textColor(textView, R.color.red);
                textView.setText("Failed");
                CommonExtKt.visible(textView);
                CommonExtKt.visible(imageView2);
                imageView2.setImageResource(R.mipmap.ic_speed_test_refresh);
            } else if (status != 3) {
                if (status != 4) {
                    return;
                }
                CommonExtKt.invisible(lineChart);
                CommonExtKt.invisible(imageView2);
                CommonExtKt.gone(textView);
                CommonExtKt.visible(progressBar);
            } else {
                CommonExtKt.gone(progressBar);
                CommonExtKt.visible(lineChart);
                CommonExtKt.visible(textView);
                CommonExtKt.textColor(textView, R.color.color_main_blue);
                List<Integer> speeds = item.getSpeeds();
                double averageOfInt = speeds == null ? 1.0d : CollectionsKt.averageOfInt(speeds);
                double d = 1024;
                Double.isNaN(d);
                formatSpeed2 = testSpeedActivity.formatSpeed(averageOfInt * d);
                textView.setText(formatSpeed2);
                imageView2.setImageResource(R.mipmap.ic_speed_test_refresh);
                CommonExtKt.visible(imageView2);
            }
        } else {
            CommonExtKt.gone(progressBar);
            CommonExtKt.visible(imageView2);
            imageView2.setImageResource(R.mipmap.ic_stop_test_speed);
            CommonExtKt.visible(textView);
            CommonExtKt.textColor(textView, R.color.color_main_blue);
            String valueOf = String.valueOf(item.getCurrSize());
            long currentTime = TimeUtils.getCurrentTime();
            Long l = item.startTime;
            Intrinsics.checkNotNullExpressionValue(l, "item.startTime");
            String speedKbS = CommonUtils.multiply(CommonUtils.divide(valueOf, String.valueOf(currentTime - l.longValue()), 10), "0.9765625", 0);
            Intrinsics.checkNotNullExpressionValue(speedKbS, "speedKbS");
            double parseDouble = Double.parseDouble(speedKbS);
            double d2 = 1024;
            Double.isNaN(d2);
            formatSpeed = testSpeedActivity.formatSpeed(parseDouble * d2);
            if (item.getSpeeds() == null) {
                item.setSpeeds(new ArrayList());
            }
            item.getSpeeds().add(Integer.valueOf((int) Float.parseFloat(speedKbS)));
            textView.setText(formatSpeed);
            CommonExtKt.visible(lineChart);
            if (!item.isInit()) {
                testSpeedActivity.initLineChart(lineChart, item.getSize());
                LineDataSet lineDataSet = new LineDataSet(item.getValues(), "");
                lineDataSet.setMode(LineDataSet.Mode.HORIZONTAL_BEZIER);
                lineDataSet.setCubicIntensity(0.2f);
                lineDataSet.setDrawFilled(true);
                lineDataSet.setDrawCircles(false);
                lineDataSet.setLineWidth(1.0f);
                lineDataSet.setCircleRadius(4.0f);
                lineDataSet.setCircleColor(-1);
                lineDataSet.setFillDrawable(ContextCompat.getDrawable(testSpeedActivity, R.drawable.line_chart_fill_shape));
                lineDataSet.setColor(Color.rgb(2, 164, 255));
                lineDataSet.setDrawHorizontalHighlightIndicator(false);
                lineDataSet.setFillFormatter(new IFillFormatter() { // from class: com.movieboxpro.android.view.activity.settings.-$$Lambda$TestSpeedActivity$initData$1$cGe7MNJDaxB5MN9_LuTXuWCs_78
                    @Override // com.github.mikephil.charting.formatter.IFillFormatter
                    public final float getFillLinePosition(ILineDataSet iLineDataSet, LineDataProvider lineDataProvider) {
                        float m829invoke$lambda1$lambda0;
                        m829invoke$lambda1$lambda0 = TestSpeedActivity$initData$1.m829invoke$lambda1$lambda0(LineChart.this, iLineDataSet, lineDataProvider);
                        return m829invoke$lambda1$lambda0;
                    }
                });
                LineData lineData = new LineData(lineDataSet);
                lineData.setValueTextSize(9.0f);
                lineData.setDrawValues(false);
                lineChart.setData(lineData);
                item.setInit(true);
                item.setLineData(lineData);
            } else if (item.getAddEntry() != null) {
                LineData lineData2 = item.getLineData();
                Intrinsics.checkNotNullExpressionValue(lineData2, "it.lineData");
                Entry addEntry = item.getAddEntry();
                Intrinsics.checkNotNullExpressionValue(addEntry, "it.addEntry");
                testSpeedActivity.addEntry(lineData2, lineChart, addEntry);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: invoke$lambda-1$lambda-0  reason: not valid java name */
    public static final float m829invoke$lambda1$lambda0(LineChart chart, ILineDataSet iLineDataSet, LineDataProvider lineDataProvider) {
        Intrinsics.checkNotNullParameter(chart, "$chart");
        return chart.getAxisLeft().getAxisMinimum();
    }
}
