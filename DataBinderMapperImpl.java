package com.movieboxpro.android;

import android.util.SparseArray;
import android.util.SparseIntArray;
import android.view.View;
import androidx.databinding.DataBinderMapper;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import com.movieboxpro.android.databinding.ActivityAccount2BindingImpl;
import com.movieboxpro.android.databinding.ActivityAccountBindingImpl;
import com.movieboxpro.android.databinding.ActivityAccountSecurityBindingImpl;
import com.movieboxpro.android.databinding.ActivityAddMovieListBindingImpl;
import com.movieboxpro.android.databinding.ActivityChildModeBindingImpl;
import com.movieboxpro.android.databinding.ActivityDownloadResolutionBindingImpl;
import com.movieboxpro.android.databinding.ActivityHappyCastBindingImpl;
import com.movieboxpro.android.databinding.ActivityHelpSettingBindingImpl;
import com.movieboxpro.android.databinding.ActivityListLayoutBindingImpl;
import com.movieboxpro.android.databinding.ActivityMyBlockBindingImpl;
import com.movieboxpro.android.databinding.ActivityPreferBindingImpl;
import com.movieboxpro.android.databinding.ActivitySetting2BindingImpl;
import com.movieboxpro.android.databinding.ActivitySmartDownloadSettingBindingImpl;
import com.movieboxpro.android.databinding.ActivitySuperChildModeBindingImpl;
import com.movieboxpro.android.databinding.ActivitySuperChildModeMainBindingImpl;
import com.movieboxpro.android.databinding.ActivityTvDeviceBindingImpl;
import com.movieboxpro.android.databinding.AdapterMoreImageBindingImpl;
import com.movieboxpro.android.databinding.AddTvWatchPlanPopLayoutBindingImpl;
import com.movieboxpro.android.databinding.CommonTitleBarLayoutBindingImpl;
import com.movieboxpro.android.databinding.DialogAudioSynBindingImpl;
import com.movieboxpro.android.databinding.DialogBlockUserBindingImpl;
import com.movieboxpro.android.databinding.DialogChooseDownloadPathBindingImpl;
import com.movieboxpro.android.databinding.DialogLoginCodeShowBindingImpl;
import com.movieboxpro.android.databinding.DialogPreviewLocalSubtitleBindingImpl;
import com.movieboxpro.android.databinding.DialogPreviewSubtitleBindingImpl;
import com.movieboxpro.android.databinding.DialogSeasonExpandBindingImpl;
import com.movieboxpro.android.databinding.DialogSmartDownloadHintBindingImpl;
import com.movieboxpro.android.databinding.FragmentFavoriteBindingImpl;
import com.movieboxpro.android.databinding.FragmentFavoriteListBindingImpl;
import com.movieboxpro.android.databinding.FragmentRecommendBindingImpl;
import com.movieboxpro.android.databinding.FragmentSkipTimeBindingImpl;
import com.movieboxpro.android.databinding.FragmentSkipTimeBindingW600dpImpl;
import com.movieboxpro.android.databinding.FragmentSkipTimeSettingBindingImpl;
import com.movieboxpro.android.databinding.FragmentTranscodeSubtitleBindingImpl;
import com.movieboxpro.android.databinding.FragmentUserInfo2BindingImpl;
import com.movieboxpro.android.databinding.PosterViewLayoutBindingImpl;
import com.movieboxpro.android.databinding.WatchPlanTopLayoutBindingImpl;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
/* loaded from: classes3.dex */
public class DataBinderMapperImpl extends DataBinderMapper {
    private static final SparseIntArray INTERNAL_LAYOUT_ID_LOOKUP;
    private static final int LAYOUT_ACTIVITYACCOUNT = 1;
    private static final int LAYOUT_ACTIVITYACCOUNT2 = 2;
    private static final int LAYOUT_ACTIVITYACCOUNTSECURITY = 3;
    private static final int LAYOUT_ACTIVITYADDMOVIELIST = 4;
    private static final int LAYOUT_ACTIVITYCHILDMODE = 5;
    private static final int LAYOUT_ACTIVITYDOWNLOADRESOLUTION = 6;
    private static final int LAYOUT_ACTIVITYHAPPYCAST = 7;
    private static final int LAYOUT_ACTIVITYHELPSETTING = 8;
    private static final int LAYOUT_ACTIVITYLISTLAYOUT = 9;
    private static final int LAYOUT_ACTIVITYMYBLOCK = 10;
    private static final int LAYOUT_ACTIVITYPREFER = 11;
    private static final int LAYOUT_ACTIVITYSETTING2 = 12;
    private static final int LAYOUT_ACTIVITYSMARTDOWNLOADSETTING = 13;
    private static final int LAYOUT_ACTIVITYSUPERCHILDMODE = 14;
    private static final int LAYOUT_ACTIVITYSUPERCHILDMODEMAIN = 15;
    private static final int LAYOUT_ACTIVITYTVDEVICE = 16;
    private static final int LAYOUT_ADAPTERMOREIMAGE = 17;
    private static final int LAYOUT_ADDTVWATCHPLANPOPLAYOUT = 18;
    private static final int LAYOUT_COMMONTITLEBARLAYOUT = 19;
    private static final int LAYOUT_DIALOGAUDIOSYN = 20;
    private static final int LAYOUT_DIALOGBLOCKUSER = 21;
    private static final int LAYOUT_DIALOGCHOOSEDOWNLOADPATH = 22;
    private static final int LAYOUT_DIALOGLOGINCODESHOW = 23;
    private static final int LAYOUT_DIALOGPREVIEWLOCALSUBTITLE = 24;
    private static final int LAYOUT_DIALOGPREVIEWSUBTITLE = 25;
    private static final int LAYOUT_DIALOGSEASONEXPAND = 26;
    private static final int LAYOUT_DIALOGSMARTDOWNLOADHINT = 27;
    private static final int LAYOUT_FRAGMENTFAVORITE = 28;
    private static final int LAYOUT_FRAGMENTFAVORITELIST = 29;
    private static final int LAYOUT_FRAGMENTRECOMMEND = 30;
    private static final int LAYOUT_FRAGMENTSKIPTIME = 31;
    private static final int LAYOUT_FRAGMENTSKIPTIMESETTING = 32;
    private static final int LAYOUT_FRAGMENTTRANSCODESUBTITLE = 33;
    private static final int LAYOUT_FRAGMENTUSERINFO2 = 34;
    private static final int LAYOUT_POSTERVIEWLAYOUT = 35;
    private static final int LAYOUT_WATCHPLANTOPLAYOUT = 36;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray(36);
        INTERNAL_LAYOUT_ID_LOOKUP = sparseIntArray;
        sparseIntArray.put(R.layout.activity_account, 1);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.activity_account2, 2);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.activity_account_security, 3);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.activity_add_movie_list, 4);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.activity_child_mode, 5);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.activity_download_resolution, 6);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.activity_happy_cast, 7);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.activity_help_setting, 8);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.activity_list_layout, 9);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.activity_my_block, 10);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.activity_prefer, 11);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.activity_setting2, 12);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.activity_smart_download_setting, 13);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.activity_super_child_mode, 14);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.activity_super_child_mode_main, 15);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.activity_tv_device, 16);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.adapter_more_image, 17);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.add_tv_watch_plan_pop_layout, 18);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.common_title_bar_layout, 19);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.dialog_audio_syn, 20);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.dialog_block_user, 21);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.dialog_choose_download_path, 22);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.dialog_login_code_show, 23);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.dialog_preview_local_subtitle, 24);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.dialog_preview_subtitle, 25);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.dialog_season_expand, 26);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.dialog_smart_download_hint, 27);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.fragment_favorite, 28);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.fragment_favorite_list, 29);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.fragment_recommend, 30);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.fragment_skip_time, 31);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.fragment_skip_time_setting, 32);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.fragment_transcode_subtitle, 33);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.fragment_user_info2, 34);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.poster_view_layout, 35);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.watch_plan_top_layout, 36);
    }

    @Override // androidx.databinding.DataBinderMapper
    public ViewDataBinding getDataBinder(DataBindingComponent dataBindingComponent, View view, int i) {
        int i2 = INTERNAL_LAYOUT_ID_LOOKUP.get(i);
        if (i2 > 0) {
            Object tag = view.getTag();
            if (tag == null) {
                throw new RuntimeException("view must have a tag");
            }
            switch (i2) {
                case 1:
                    if ("layout/activity_account_0".equals(tag)) {
                        return new ActivityAccountBindingImpl(dataBindingComponent, view);
                    }
                    throw new IllegalArgumentException("The tag for activity_account is invalid. Received: " + tag);
                case 2:
                    if ("layout/activity_account2_0".equals(tag)) {
                        return new ActivityAccount2BindingImpl(dataBindingComponent, view);
                    }
                    throw new IllegalArgumentException("The tag for activity_account2 is invalid. Received: " + tag);
                case 3:
                    if ("layout/activity_account_security_0".equals(tag)) {
                        return new ActivityAccountSecurityBindingImpl(dataBindingComponent, view);
                    }
                    throw new IllegalArgumentException("The tag for activity_account_security is invalid. Received: " + tag);
                case 4:
                    if ("layout/activity_add_movie_list_0".equals(tag)) {
                        return new ActivityAddMovieListBindingImpl(dataBindingComponent, view);
                    }
                    throw new IllegalArgumentException("The tag for activity_add_movie_list is invalid. Received: " + tag);
                case 5:
                    if ("layout/activity_child_mode_0".equals(tag)) {
                        return new ActivityChildModeBindingImpl(dataBindingComponent, view);
                    }
                    throw new IllegalArgumentException("The tag for activity_child_mode is invalid. Received: " + tag);
                case 6:
                    if ("layout/activity_download_resolution_0".equals(tag)) {
                        return new ActivityDownloadResolutionBindingImpl(dataBindingComponent, view);
                    }
                    throw new IllegalArgumentException("The tag for activity_download_resolution is invalid. Received: " + tag);
                case 7:
                    if ("layout/activity_happy_cast_0".equals(tag)) {
                        return new ActivityHappyCastBindingImpl(dataBindingComponent, view);
                    }
                    throw new IllegalArgumentException("The tag for activity_happy_cast is invalid. Received: " + tag);
                case 8:
                    if ("layout/activity_help_setting_0".equals(tag)) {
                        return new ActivityHelpSettingBindingImpl(dataBindingComponent, view);
                    }
                    throw new IllegalArgumentException("The tag for activity_help_setting is invalid. Received: " + tag);
                case 9:
                    if ("layout/activity_list_layout_0".equals(tag)) {
                        return new ActivityListLayoutBindingImpl(dataBindingComponent, view);
                    }
                    throw new IllegalArgumentException("The tag for activity_list_layout is invalid. Received: " + tag);
                case 10:
                    if ("layout/activity_my_block_0".equals(tag)) {
                        return new ActivityMyBlockBindingImpl(dataBindingComponent, view);
                    }
                    throw new IllegalArgumentException("The tag for activity_my_block is invalid. Received: " + tag);
                case 11:
                    if ("layout/activity_prefer_0".equals(tag)) {
                        return new ActivityPreferBindingImpl(dataBindingComponent, view);
                    }
                    throw new IllegalArgumentException("The tag for activity_prefer is invalid. Received: " + tag);
                case 12:
                    if ("layout/activity_setting2_0".equals(tag)) {
                        return new ActivitySetting2BindingImpl(dataBindingComponent, view);
                    }
                    throw new IllegalArgumentException("The tag for activity_setting2 is invalid. Received: " + tag);
                case 13:
                    if ("layout/activity_smart_download_setting_0".equals(tag)) {
                        return new ActivitySmartDownloadSettingBindingImpl(dataBindingComponent, view);
                    }
                    throw new IllegalArgumentException("The tag for activity_smart_download_setting is invalid. Received: " + tag);
                case 14:
                    if ("layout/activity_super_child_mode_0".equals(tag)) {
                        return new ActivitySuperChildModeBindingImpl(dataBindingComponent, view);
                    }
                    throw new IllegalArgumentException("The tag for activity_super_child_mode is invalid. Received: " + tag);
                case 15:
                    if ("layout/activity_super_child_mode_main_0".equals(tag)) {
                        return new ActivitySuperChildModeMainBindingImpl(dataBindingComponent, view);
                    }
                    throw new IllegalArgumentException("The tag for activity_super_child_mode_main is invalid. Received: " + tag);
                case 16:
                    if ("layout/activity_tv_device_0".equals(tag)) {
                        return new ActivityTvDeviceBindingImpl(dataBindingComponent, view);
                    }
                    throw new IllegalArgumentException("The tag for activity_tv_device is invalid. Received: " + tag);
                case 17:
                    if ("layout/adapter_more_image_0".equals(tag)) {
                        return new AdapterMoreImageBindingImpl(dataBindingComponent, view);
                    }
                    throw new IllegalArgumentException("The tag for adapter_more_image is invalid. Received: " + tag);
                case 18:
                    if ("layout/add_tv_watch_plan_pop_layout_0".equals(tag)) {
                        return new AddTvWatchPlanPopLayoutBindingImpl(dataBindingComponent, view);
                    }
                    throw new IllegalArgumentException("The tag for add_tv_watch_plan_pop_layout is invalid. Received: " + tag);
                case 19:
                    if ("layout/common_title_bar_layout_0".equals(tag)) {
                        return new CommonTitleBarLayoutBindingImpl(dataBindingComponent, view);
                    }
                    throw new IllegalArgumentException("The tag for common_title_bar_layout is invalid. Received: " + tag);
                case 20:
                    if ("layout/dialog_audio_syn_0".equals(tag)) {
                        return new DialogAudioSynBindingImpl(dataBindingComponent, view);
                    }
                    throw new IllegalArgumentException("The tag for dialog_audio_syn is invalid. Received: " + tag);
                case 21:
                    if ("layout/dialog_block_user_0".equals(tag)) {
                        return new DialogBlockUserBindingImpl(dataBindingComponent, view);
                    }
                    throw new IllegalArgumentException("The tag for dialog_block_user is invalid. Received: " + tag);
                case 22:
                    if ("layout/dialog_choose_download_path_0".equals(tag)) {
                        return new DialogChooseDownloadPathBindingImpl(dataBindingComponent, view);
                    }
                    throw new IllegalArgumentException("The tag for dialog_choose_download_path is invalid. Received: " + tag);
                case 23:
                    if ("layout/dialog_login_code_show_0".equals(tag)) {
                        return new DialogLoginCodeShowBindingImpl(dataBindingComponent, view);
                    }
                    throw new IllegalArgumentException("The tag for dialog_login_code_show is invalid. Received: " + tag);
                case 24:
                    if ("layout/dialog_preview_local_subtitle_0".equals(tag)) {
                        return new DialogPreviewLocalSubtitleBindingImpl(dataBindingComponent, view);
                    }
                    throw new IllegalArgumentException("The tag for dialog_preview_local_subtitle is invalid. Received: " + tag);
                case 25:
                    if ("layout/dialog_preview_subtitle_0".equals(tag)) {
                        return new DialogPreviewSubtitleBindingImpl(dataBindingComponent, view);
                    }
                    throw new IllegalArgumentException("The tag for dialog_preview_subtitle is invalid. Received: " + tag);
                case 26:
                    if ("layout/dialog_season_expand_0".equals(tag)) {
                        return new DialogSeasonExpandBindingImpl(dataBindingComponent, view);
                    }
                    throw new IllegalArgumentException("The tag for dialog_season_expand is invalid. Received: " + tag);
                case 27:
                    if ("layout/dialog_smart_download_hint_0".equals(tag)) {
                        return new DialogSmartDownloadHintBindingImpl(dataBindingComponent, view);
                    }
                    throw new IllegalArgumentException("The tag for dialog_smart_download_hint is invalid. Received: " + tag);
                case 28:
                    if ("layout/fragment_favorite_0".equals(tag)) {
                        return new FragmentFavoriteBindingImpl(dataBindingComponent, view);
                    }
                    throw new IllegalArgumentException("The tag for fragment_favorite is invalid. Received: " + tag);
                case 29:
                    if ("layout/fragment_favorite_list_0".equals(tag)) {
                        return new FragmentFavoriteListBindingImpl(dataBindingComponent, view);
                    }
                    throw new IllegalArgumentException("The tag for fragment_favorite_list is invalid. Received: " + tag);
                case 30:
                    if ("layout/fragment_recommend_0".equals(tag)) {
                        return new FragmentRecommendBindingImpl(dataBindingComponent, view);
                    }
                    throw new IllegalArgumentException("The tag for fragment_recommend is invalid. Received: " + tag);
                case 31:
                    if ("layout-w600dp/fragment_skip_time_0".equals(tag)) {
                        return new FragmentSkipTimeBindingW600dpImpl(dataBindingComponent, view);
                    }
                    if ("layout/fragment_skip_time_0".equals(tag)) {
                        return new FragmentSkipTimeBindingImpl(dataBindingComponent, view);
                    }
                    throw new IllegalArgumentException("The tag for fragment_skip_time is invalid. Received: " + tag);
                case 32:
                    if ("layout/fragment_skip_time_setting_0".equals(tag)) {
                        return new FragmentSkipTimeSettingBindingImpl(dataBindingComponent, view);
                    }
                    throw new IllegalArgumentException("The tag for fragment_skip_time_setting is invalid. Received: " + tag);
                case 33:
                    if ("layout/fragment_transcode_subtitle_0".equals(tag)) {
                        return new FragmentTranscodeSubtitleBindingImpl(dataBindingComponent, view);
                    }
                    throw new IllegalArgumentException("The tag for fragment_transcode_subtitle is invalid. Received: " + tag);
                case 34:
                    if ("layout/fragment_user_info2_0".equals(tag)) {
                        return new FragmentUserInfo2BindingImpl(dataBindingComponent, view);
                    }
                    throw new IllegalArgumentException("The tag for fragment_user_info2 is invalid. Received: " + tag);
                case 35:
                    if ("layout/poster_view_layout_0".equals(tag)) {
                        return new PosterViewLayoutBindingImpl(dataBindingComponent, view);
                    }
                    throw new IllegalArgumentException("The tag for poster_view_layout is invalid. Received: " + tag);
                case 36:
                    if ("layout/watch_plan_top_layout_0".equals(tag)) {
                        return new WatchPlanTopLayoutBindingImpl(dataBindingComponent, view);
                    }
                    throw new IllegalArgumentException("The tag for watch_plan_top_layout is invalid. Received: " + tag);
                default:
                    return null;
            }
        }
        return null;
    }

    @Override // androidx.databinding.DataBinderMapper
    public ViewDataBinding getDataBinder(DataBindingComponent dataBindingComponent, View[] viewArr, int i) {
        if (viewArr == null || viewArr.length == 0 || INTERNAL_LAYOUT_ID_LOOKUP.get(i) <= 0 || viewArr[0].getTag() != null) {
            return null;
        }
        throw new RuntimeException("view must have a tag");
    }

    @Override // androidx.databinding.DataBinderMapper
    public int getLayoutId(String str) {
        Integer num;
        if (str == null || (num = InnerLayoutIdLookup.sKeys.get(str)) == null) {
            return 0;
        }
        return num.intValue();
    }

    @Override // androidx.databinding.DataBinderMapper
    public String convertBrIdToString(int i) {
        return InnerBrLookup.sKeys.get(i);
    }

    @Override // androidx.databinding.DataBinderMapper
    public List<DataBinderMapper> collectDependencies() {
        ArrayList arrayList = new ArrayList(2);
        arrayList.add(new androidx.databinding.library.baseAdapters.DataBinderMapperImpl());
        arrayList.add(new com.chad.library.DataBinderMapperImpl());
        return arrayList;
    }

    /* loaded from: classes3.dex */
    private static class InnerBrLookup {
        static final SparseArray<String> sKeys;

        private InnerBrLookup() {
        }

        static {
            SparseArray<String> sparseArray = new SparseArray<>(3);
            sKeys = sparseArray;
            sparseArray.put(0, "_all");
            sKeys.put(1, "dialog");
            sKeys.put(2, "vm");
        }
    }

    /* loaded from: classes3.dex */
    private static class InnerLayoutIdLookup {
        static final HashMap<String, Integer> sKeys;

        private InnerLayoutIdLookup() {
        }

        static {
            HashMap<String, Integer> hashMap = new HashMap<>(37);
            sKeys = hashMap;
            hashMap.put("layout/activity_account_0", Integer.valueOf((int) R.layout.activity_account));
            sKeys.put("layout/activity_account2_0", Integer.valueOf((int) R.layout.activity_account2));
            sKeys.put("layout/activity_account_security_0", Integer.valueOf((int) R.layout.activity_account_security));
            sKeys.put("layout/activity_add_movie_list_0", Integer.valueOf((int) R.layout.activity_add_movie_list));
            sKeys.put("layout/activity_child_mode_0", Integer.valueOf((int) R.layout.activity_child_mode));
            sKeys.put("layout/activity_download_resolution_0", Integer.valueOf((int) R.layout.activity_download_resolution));
            sKeys.put("layout/activity_happy_cast_0", Integer.valueOf((int) R.layout.activity_happy_cast));
            sKeys.put("layout/activity_help_setting_0", Integer.valueOf((int) R.layout.activity_help_setting));
            sKeys.put("layout/activity_list_layout_0", Integer.valueOf((int) R.layout.activity_list_layout));
            sKeys.put("layout/activity_my_block_0", Integer.valueOf((int) R.layout.activity_my_block));
            sKeys.put("layout/activity_prefer_0", Integer.valueOf((int) R.layout.activity_prefer));
            sKeys.put("layout/activity_setting2_0", Integer.valueOf((int) R.layout.activity_setting2));
            sKeys.put("layout/activity_smart_download_setting_0", Integer.valueOf((int) R.layout.activity_smart_download_setting));
            sKeys.put("layout/activity_super_child_mode_0", Integer.valueOf((int) R.layout.activity_super_child_mode));
            sKeys.put("layout/activity_super_child_mode_main_0", Integer.valueOf((int) R.layout.activity_super_child_mode_main));
            sKeys.put("layout/activity_tv_device_0", Integer.valueOf((int) R.layout.activity_tv_device));
            sKeys.put("layout/adapter_more_image_0", Integer.valueOf((int) R.layout.adapter_more_image));
            sKeys.put("layout/add_tv_watch_plan_pop_layout_0", Integer.valueOf((int) R.layout.add_tv_watch_plan_pop_layout));
            sKeys.put("layout/common_title_bar_layout_0", Integer.valueOf((int) R.layout.common_title_bar_layout));
            sKeys.put("layout/dialog_audio_syn_0", Integer.valueOf((int) R.layout.dialog_audio_syn));
            sKeys.put("layout/dialog_block_user_0", Integer.valueOf((int) R.layout.dialog_block_user));
            sKeys.put("layout/dialog_choose_download_path_0", Integer.valueOf((int) R.layout.dialog_choose_download_path));
            sKeys.put("layout/dialog_login_code_show_0", Integer.valueOf((int) R.layout.dialog_login_code_show));
            sKeys.put("layout/dialog_preview_local_subtitle_0", Integer.valueOf((int) R.layout.dialog_preview_local_subtitle));
            sKeys.put("layout/dialog_preview_subtitle_0", Integer.valueOf((int) R.layout.dialog_preview_subtitle));
            sKeys.put("layout/dialog_season_expand_0", Integer.valueOf((int) R.layout.dialog_season_expand));
            sKeys.put("layout/dialog_smart_download_hint_0", Integer.valueOf((int) R.layout.dialog_smart_download_hint));
            sKeys.put("layout/fragment_favorite_0", Integer.valueOf((int) R.layout.fragment_favorite));
            sKeys.put("layout/fragment_favorite_list_0", Integer.valueOf((int) R.layout.fragment_favorite_list));
            sKeys.put("layout/fragment_recommend_0", Integer.valueOf((int) R.layout.fragment_recommend));
            HashMap<String, Integer> hashMap2 = sKeys;
            Integer valueOf = Integer.valueOf((int) R.layout.fragment_skip_time);
            hashMap2.put("layout-w600dp/fragment_skip_time_0", valueOf);
            sKeys.put("layout/fragment_skip_time_0", valueOf);
            sKeys.put("layout/fragment_skip_time_setting_0", Integer.valueOf((int) R.layout.fragment_skip_time_setting));
            sKeys.put("layout/fragment_transcode_subtitle_0", Integer.valueOf((int) R.layout.fragment_transcode_subtitle));
            sKeys.put("layout/fragment_user_info2_0", Integer.valueOf((int) R.layout.fragment_user_info2));
            sKeys.put("layout/poster_view_layout_0", Integer.valueOf((int) R.layout.poster_view_layout));
            sKeys.put("layout/watch_plan_top_layout_0", Integer.valueOf((int) R.layout.watch_plan_top_layout));
        }
    }
}
