package com.movieboxpro.android.adapter;

import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.chad.library.adapter.base.delegate.BaseMultiTypeDelegate;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.movieboxpro.android.R;
import com.movieboxpro.android.app.App;
import com.movieboxpro.android.model.movie.MovieListModel;
import com.movieboxpro.android.utils.CommonExtKt;
import com.movieboxpro.android.utils.CommonUtils;
import com.movieboxpro.android.utils.DensityUtils;
import com.movieboxpro.android.utils.GlideUtils;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: PlayListItemAdapter.kt */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\u0013\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0018\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u00032\u0006\u0010\f\u001a\u00020\u0002H\u0014R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Lcom/movieboxpro/android/adapter/PlayListItemAdapter;", "Lcom/movieboxpro/android/adapter/BaseLoadmoreDelegateMultiAdapter;", "Lcom/movieboxpro/android/model/movie/MovieListModel$MovieListItem;", "Lcom/chad/library/adapter/base/viewholder/BaseViewHolder;", "list", "", "(Ljava/util/List;)V", "screenWidth", "", "convert", "", "helper", "item", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class PlayListItemAdapter extends BaseLoadmoreDelegateMultiAdapter<MovieListModel.MovieListItem, BaseViewHolder> {
    private final int screenWidth;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PlayListItemAdapter(List<MovieListModel.MovieListItem> list) {
        super(list);
        Intrinsics.checkNotNullParameter(list, "list");
        this.screenWidth = DensityUtils.getScreenWidth(App.getContext());
        setMultiTypeDelegate(new BaseMultiTypeDelegate<MovieListModel.MovieListItem>() { // from class: com.movieboxpro.android.adapter.PlayListItemAdapter.1
            @Override // com.chad.library.adapter.base.delegate.BaseMultiTypeDelegate
            public int getItemType(List<? extends MovieListModel.MovieListItem> data, int i) {
                Intrinsics.checkNotNullParameter(data, "data");
                return data.get(i).getItemType();
            }
        });
        BaseMultiTypeDelegate<MovieListModel.MovieListItem> multiTypeDelegate = getMultiTypeDelegate();
        Intrinsics.checkNotNull(multiTypeDelegate);
        multiTypeDelegate.addItemType(1, R.layout.adapter_detail_movie_list_image_item);
        BaseMultiTypeDelegate<MovieListModel.MovieListItem> multiTypeDelegate2 = getMultiTypeDelegate();
        Intrinsics.checkNotNull(multiTypeDelegate2);
        multiTypeDelegate2.addItemType(5, R.layout.adapter_day_hot_list_item);
        BaseMultiTypeDelegate<MovieListModel.MovieListItem> multiTypeDelegate3 = getMultiTypeDelegate();
        Intrinsics.checkNotNull(multiTypeDelegate3);
        multiTypeDelegate3.addItemType(6, R.layout.adapter_collection_item);
        BaseMultiTypeDelegate<MovieListModel.MovieListItem> multiTypeDelegate4 = getMultiTypeDelegate();
        Intrinsics.checkNotNull(multiTypeDelegate4);
        multiTypeDelegate4.addItemType(2, R.layout.adapter_compilations_item);
        BaseMultiTypeDelegate<MovieListModel.MovieListItem> multiTypeDelegate5 = getMultiTypeDelegate();
        Intrinsics.checkNotNull(multiTypeDelegate5);
        multiTypeDelegate5.addItemType(3, R.layout.adapter_home_actor_item);
        BaseMultiTypeDelegate<MovieListModel.MovieListItem> multiTypeDelegate6 = getMultiTypeDelegate();
        Intrinsics.checkNotNull(multiTypeDelegate6);
        multiTypeDelegate6.addItemType(4, R.layout.adapter_special_movie_list_item);
        if (CommonUtils.isTablet()) {
            BaseMultiTypeDelegate<MovieListModel.MovieListItem> multiTypeDelegate7 = getMultiTypeDelegate();
            Intrinsics.checkNotNull(multiTypeDelegate7);
            multiTypeDelegate7.addItemType(7, R.layout.adapter_week_hot_list_land_item);
        } else {
            BaseMultiTypeDelegate<MovieListModel.MovieListItem> multiTypeDelegate8 = getMultiTypeDelegate();
            Intrinsics.checkNotNull(multiTypeDelegate8);
            multiTypeDelegate8.addItemType(7, R.layout.adapter_week_hot_list_item);
        }
        if (CommonUtils.isTablet()) {
            BaseMultiTypeDelegate<MovieListModel.MovieListItem> multiTypeDelegate9 = getMultiTypeDelegate();
            Intrinsics.checkNotNull(multiTypeDelegate9);
            multiTypeDelegate9.addItemType(8, R.layout.adapter_week_hot_list_land_item);
            return;
        }
        BaseMultiTypeDelegate<MovieListModel.MovieListItem> multiTypeDelegate10 = getMultiTypeDelegate();
        Intrinsics.checkNotNull(multiTypeDelegate10);
        multiTypeDelegate10.addItemType(8, R.layout.adapter_week_hot_list_item);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    @Override // com.chad.library.adapter.base.BaseQuickAdapter
    public void convert(BaseViewHolder helper, MovieListModel.MovieListItem item) {
        List<String> imgArr;
        List<String> imgArr2;
        List<String> imgArr3;
        List<String> imgArr4;
        List<String> imgArr5;
        List<String> imgArr6;
        List<String> imgArr7;
        Intrinsics.checkNotNullParameter(helper, "helper");
        Intrinsics.checkNotNullParameter(item, "item");
        boolean z = true;
        switch (item.getItemType()) {
            case 1:
                ImageView imageView = (ImageView) helper.getView(R.id.imageView);
                TextView textView = (TextView) helper.getView(R.id.tv_num);
                TextView textView2 = (TextView) helper.getView(R.id.tv_name);
                if (item.getImgArr() != null) {
                    List<String> imgArr8 = item.getImgArr();
                    Intrinsics.checkNotNullExpressionValue(imgArr8, "item.imgArr");
                    if (true ^ imgArr8.isEmpty()) {
                        GlideUtils.loadPost(getContext(), item.getImgArr().get(0), imageView, 4);
                    }
                }
                CommonExtKt.textShadow(textView, String.valueOf(item.getCount()), 12, R.color.white_f2);
                String name = item.getName();
                Intrinsics.checkNotNullExpressionValue(name, "item.name");
                CommonExtKt.textShadow(textView2, name, 14, R.color.white_f2);
                Unit unit = Unit.INSTANCE;
                return;
            case 2:
                GlideUtils.loadWithCorner(getContext(), item.getCover(), (ImageView) helper.getView(R.id.imageView), DensityUtils.dp2px(App.getContext(), 4.0f));
                Unit unit2 = Unit.INSTANCE;
                return;
            case 3:
                ImageView imageView2 = (ImageView) helper.getView(R.id.ivAvatar);
                TextView textView3 = (TextView) helper.getView(R.id.tvNameFirst);
                TextView textView4 = (TextView) helper.getView(R.id.tvName);
                TextView textView5 = (TextView) helper.getView(R.id.tvJob);
                String avatar = item.getAvatar();
                if (avatar != null && avatar.length() != 0) {
                    z = false;
                }
                if (z) {
                    CommonExtKt.gone(imageView2);
                    CommonExtKt.visible(textView3);
                    textView3.setText(CommonUtils.getNameFirstLetter(item.getName()));
                } else {
                    CommonExtKt.visible(imageView2);
                    CommonExtKt.gone(textView3);
                    GlideUtils.loadWithCircle(getContext(), item.getAvatar(), imageView2, 88, (int) R.drawable.image_loading_placeholer);
                }
                textView4.setText(item.getName());
                textView5.setText(item.getJob());
                Unit unit3 = Unit.INSTANCE;
                return;
            case 4:
                TextView textView6 = (TextView) helper.getView(R.id.tv_name);
                TextView textView7 = (TextView) helper.getView(R.id.tv_num);
                if (CommonUtils.isTablet()) {
                    String name2 = item.getName();
                    Intrinsics.checkNotNullExpressionValue(name2, "item.name");
                    CommonExtKt.textShadow(textView6, name2, 12, R.color.white_f2);
                    CommonExtKt.textShadow(textView7, String.valueOf(item.getCount()), 14, R.color.white_f2);
                } else {
                    String name3 = item.getName();
                    Intrinsics.checkNotNullExpressionValue(name3, "item.name");
                    CommonExtKt.textShadow(textView6, name3, 10, R.color.white_f2);
                    CommonExtKt.textShadow(textView7, String.valueOf(item.getCount()), 10, R.color.white_f2);
                }
                if (item.getImgArr() != null) {
                    Intrinsics.checkNotNullExpressionValue(item.getImgArr(), "item.imgArr");
                    if (!imgArr.isEmpty()) {
                        GlideUtils.loadPost(getContext(), item.getImgArr().get(0), (ImageView) helper.getView(R.id.imageView), 4);
                        break;
                    }
                }
                break;
            case 5:
                ImageView imageView3 = (ImageView) helper.getView(R.id.imageView);
                TextView textView8 = (TextView) helper.getView(R.id.tvNum);
                TextView textView9 = (TextView) helper.getView(R.id.tvName);
                if (item.getImgArr() != null) {
                    Intrinsics.checkNotNullExpressionValue(item.getImgArr(), "item.imgArr");
                    if (!imgArr2.isEmpty()) {
                        GlideUtils.loadPost(getContext(), item.getImgArr().get(0), imageView3, 4);
                    }
                }
                CommonExtKt.textShadow(textView8, String.valueOf(item.getCount()), 10, R.color.white_f2);
                String name4 = item.getName();
                Intrinsics.checkNotNullExpressionValue(name4, "item.name");
                CommonExtKt.textShadow(textView9, name4, 10, R.color.white_f2);
                List<MovieListModel.VideoItem> videoArr = item.getVideoArr();
                Integer valueOf = videoArr == null ? null : Integer.valueOf(videoArr.size());
                if (valueOf != null && valueOf.intValue() == 4) {
                    helper.setText(R.id.tvOne, item.getVideoArr().get(0).getTitle());
                    helper.setText(R.id.tvTwo, item.getVideoArr().get(1).getTitle());
                    helper.setText(R.id.tvThree, item.getVideoArr().get(2).getTitle());
                    helper.setText(R.id.tvFour, item.getVideoArr().get(3).getTitle());
                    return;
                } else if (valueOf != null && valueOf.intValue() == 3) {
                    helper.setText(R.id.tvOne, item.getVideoArr().get(0).getTitle());
                    helper.setText(R.id.tvTwo, item.getVideoArr().get(1).getTitle());
                    helper.setText(R.id.tvThree, item.getVideoArr().get(2).getTitle());
                    return;
                } else if (valueOf != null && valueOf.intValue() == 2) {
                    helper.setText(R.id.tvOne, item.getVideoArr().get(0).getTitle());
                    helper.setText(R.id.tvTwo, item.getVideoArr().get(1).getTitle());
                    return;
                } else if (valueOf != null && valueOf.intValue() == 1) {
                    helper.setText(R.id.tvOne, item.getVideoArr().get(0).getTitle());
                    return;
                } else {
                    Unit unit4 = Unit.INSTANCE;
                    return;
                }
            case 6:
                GlideUtils.loadWithCorner(getContext(), item.getCover(), (ImageView) helper.getView(R.id.imageView), DensityUtils.dp2px(App.getContext(), 4.0f));
                Unit unit5 = Unit.INSTANCE;
                return;
            case 7:
                if (!CommonUtils.isTablet()) {
                    ImageView imageView4 = (ImageView) helper.getView(R.id.imageView);
                    TextView textView10 = (TextView) helper.getView(R.id.tvNum);
                    TextView textView11 = (TextView) helper.getView(R.id.tvName);
                    ConstraintLayout constraintLayout = (ConstraintLayout) helper.getView(R.id.container);
                    ViewGroup.LayoutParams layoutParams = constraintLayout.getLayoutParams();
                    double d = this.screenWidth;
                    Double.isNaN(d);
                    layoutParams.width = (int) (d * 0.9d);
                    layoutParams.height = -2;
                    constraintLayout.setLayoutParams(layoutParams);
                    if (item.getImgArr() != null) {
                        Intrinsics.checkNotNullExpressionValue(item.getImgArr(), "item.imgArr");
                        if (!imgArr5.isEmpty()) {
                            GlideUtils.loadPost(getContext(), item.getImgArr().get(0), imageView4, 4);
                        }
                    }
                    CommonExtKt.textShadow(textView10, String.valueOf(item.getCount()), 12, R.color.white);
                    String name5 = item.getName();
                    Intrinsics.checkNotNullExpressionValue(name5, "item.name");
                    CommonExtKt.textShadow(textView11, name5, 14, R.color.white_f2);
                } else {
                    ImageView imageView5 = (ImageView) helper.getView(R.id.imageView1);
                    TextView textView12 = (TextView) helper.getView(R.id.tvNum1);
                    TextView textView13 = (TextView) helper.getView(R.id.tvName1);
                    ImageView imageView6 = (ImageView) helper.getView(R.id.imageView2);
                    TextView textView14 = (TextView) helper.getView(R.id.tvNum2);
                    TextView textView15 = (TextView) helper.getView(R.id.tvName2);
                    LinearLayout linearLayout = (LinearLayout) helper.getView(R.id.linearLayout);
                    ViewGroup.LayoutParams layoutParams2 = linearLayout.getLayoutParams();
                    double d2 = this.screenWidth;
                    Double.isNaN(d2);
                    layoutParams2.width = (int) (d2 * 0.9d);
                    layoutParams2.height = -2;
                    linearLayout.setLayoutParams(layoutParams2);
                    MovieListModel.MovieListItem first = item.getDoubleMovie().getFirst();
                    if (item.getDoubleMovie() != null) {
                        if (first != null) {
                            CommonExtKt.visible(imageView5);
                            CommonExtKt.visible(textView12);
                            CommonExtKt.visible(textView13);
                            if (first.getImgArr() != null) {
                                Intrinsics.checkNotNullExpressionValue(first.getImgArr(), "item1.imgArr");
                                if (!imgArr4.isEmpty()) {
                                    GlideUtils.loadPost(getContext(), first.getImgArr().get(0), imageView5, 4);
                                }
                            }
                            CommonExtKt.textShadow(textView12, String.valueOf(first.getCount()), 12, R.color.white_f2);
                            String name6 = first.getName();
                            Intrinsics.checkNotNullExpressionValue(name6, "item1.name");
                            CommonExtKt.textShadow(textView13, name6, 14, R.color.white_f2);
                        } else {
                            CommonExtKt.gone(imageView5);
                            CommonExtKt.gone(textView12);
                            CommonExtKt.gone(textView13);
                        }
                        MovieListModel.MovieListItem second = item.getDoubleMovie().getSecond();
                        if (second != null) {
                            CommonExtKt.visible(imageView6);
                            CommonExtKt.visible(textView14);
                            CommonExtKt.visible(textView15);
                            if (second.getImgArr() != null) {
                                Intrinsics.checkNotNullExpressionValue(second.getImgArr(), "item2.imgArr");
                                if (!imgArr3.isEmpty()) {
                                    GlideUtils.loadPost(getContext(), second.getImgArr().get(0), imageView6, 4);
                                }
                            }
                            CommonExtKt.textShadow(textView14, String.valueOf(second.getCount()), 12, R.color.white_f2);
                            String name7 = second.getName();
                            Intrinsics.checkNotNullExpressionValue(name7, "item2.name");
                            CommonExtKt.textShadow(textView15, name7, 14, R.color.white_f2);
                        } else {
                            CommonExtKt.gone(imageView6);
                            CommonExtKt.gone(textView14);
                            CommonExtKt.gone(textView15);
                        }
                    } else {
                        CommonExtKt.gone(imageView5);
                        CommonExtKt.gone(textView12);
                        CommonExtKt.gone(textView13);
                        CommonExtKt.gone(imageView6);
                        CommonExtKt.gone(textView14);
                        CommonExtKt.gone(textView15);
                    }
                }
                Unit unit6 = Unit.INSTANCE;
                return;
            case 8:
                if (!CommonUtils.isTablet()) {
                    ImageView imageView7 = (ImageView) helper.getView(R.id.imageView);
                    TextView textView16 = (TextView) helper.getView(R.id.tvNum);
                    TextView textView17 = (TextView) helper.getView(R.id.tvName);
                    if (item.getImgArr() != null) {
                        List<String> imgArr9 = item.getImgArr();
                        Intrinsics.checkNotNullExpressionValue(imgArr9, "item.imgArr");
                        if (true ^ imgArr9.isEmpty()) {
                            GlideUtils.loadWithCorner(getContext(), item.getImgArr().get(0), imageView7, DensityUtils.dp2px(4.0f));
                        }
                    }
                    CommonExtKt.textShadow(textView16, String.valueOf(item.getCount()), 12, R.color.white_f2);
                    String name8 = item.getName();
                    Intrinsics.checkNotNullExpressionValue(name8, "item.name");
                    CommonExtKt.textShadow(textView17, name8, 14, R.color.white_f2);
                } else {
                    try {
                        ImageView imageView8 = (ImageView) helper.getView(R.id.imageView1);
                        TextView textView18 = (TextView) helper.getView(R.id.tvNum1);
                        TextView textView19 = (TextView) helper.getView(R.id.tvName1);
                        ImageView imageView9 = (ImageView) helper.getView(R.id.imageView2);
                        TextView textView20 = (TextView) helper.getView(R.id.tvNum2);
                        TextView textView21 = (TextView) helper.getView(R.id.tvName2);
                        if (item.getDoubleMovie() != null) {
                            MovieListModel.MovieListItem first2 = item.getDoubleMovie().getFirst();
                            if (first2 != null) {
                                CommonExtKt.visible(imageView8);
                                CommonExtKt.visible(textView18);
                                CommonExtKt.visible(textView19);
                                if (first2.getImgArr() != null) {
                                    Intrinsics.checkNotNullExpressionValue(first2.getImgArr(), "item1.imgArr");
                                    if (!imgArr7.isEmpty()) {
                                        GlideUtils.loadPost(getContext(), first2.getImgArr().get(0), imageView8, 4);
                                    }
                                }
                                CommonExtKt.textShadow(textView18, String.valueOf(first2.getCount()), 12, R.color.white_f2);
                                String name9 = first2.getName();
                                Intrinsics.checkNotNullExpressionValue(name9, "item1.name");
                                CommonExtKt.textShadow(textView19, name9, 14, R.color.white_f2);
                            } else {
                                CommonExtKt.gone(imageView8);
                                CommonExtKt.gone(textView18);
                                CommonExtKt.gone(textView19);
                            }
                            MovieListModel.MovieListItem second2 = item.getDoubleMovie().getSecond();
                            if (second2 != null) {
                                CommonExtKt.visible(imageView9);
                                CommonExtKt.visible(textView20);
                                CommonExtKt.visible(textView21);
                                if (second2.getImgArr() != null) {
                                    Intrinsics.checkNotNullExpressionValue(second2.getImgArr(), "item2.imgArr");
                                    if (!imgArr6.isEmpty()) {
                                        GlideUtils.loadPost(getContext(), second2.getImgArr().get(0), imageView9, 4);
                                    }
                                }
                                CommonExtKt.textShadow(textView20, String.valueOf(second2.getCount()), 12, R.color.white_f2);
                                String name10 = second2.getName();
                                Intrinsics.checkNotNullExpressionValue(name10, "item2.name");
                                CommonExtKt.textShadow(textView21, name10, 14, R.color.white_f2);
                            } else {
                                CommonExtKt.gone(imageView9);
                                CommonExtKt.gone(textView20);
                                CommonExtKt.gone(textView21);
                            }
                        } else {
                            CommonExtKt.gone(imageView8);
                            CommonExtKt.gone(textView18);
                            CommonExtKt.gone(textView19);
                            CommonExtKt.gone(imageView9);
                            CommonExtKt.gone(textView20);
                            CommonExtKt.gone(textView21);
                        }
                    } catch (IllegalStateException unused) {
                    }
                }
                Unit unit7 = Unit.INSTANCE;
                return;
        }
        Unit unit8 = Unit.INSTANCE;
    }
}
