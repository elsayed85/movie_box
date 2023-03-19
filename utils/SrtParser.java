package com.movieboxpro.android.utils;

import android.graphics.Color;
import android.graphics.Typeface;
import android.text.Html;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import com.dueeeke.model.SrtPraseModel;
import com.movieboxpro.android.R;
import com.movieboxpro.android.app.App;
import com.movieboxpro.android.view.activity.videoplayer.controller.MediaPlayerControl;
import com.movieboxpro.android.view.videocontroller.LrcController;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.joda.time.DateTimeConstants;
/* loaded from: classes3.dex */
public class SrtParser {
    private static String content;
    private static ArrayList<SrtPraseModel> enSrtList;
    public static int lastEndTime;
    public static int selectPos;

    public static List<SrtPraseModel> parseSrt(String str, List<SrtPraseModel> list) {
        int i;
        int i2;
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(str), FileUtils.getCharset(str)));
            StringBuilder sb = new StringBuilder();
            while (true) {
                String readLine = bufferedReader.readLine();
                if (readLine == null) {
                    break;
                } else if (!readLine.equals("")) {
                    sb.append(readLine);
                    sb.append("@");
                } else {
                    String[] split = sb.toString().trim().split("@");
                    if (split.length < 3) {
                        sb.delete(0, sb.length());
                    } else {
                        SrtPraseModel srtPraseModel = new SrtPraseModel();
                        Matcher matcher = Pattern.compile("\\d+:\\d+:\\d+,\\d+").matcher(com.dueeeke.utils.StringUtils.formatContent(split[1]));
                        if (matcher.find()) {
                            i2 = parseSubtitleTime(matcher.group());
                            i = matcher.find() ? parseSubtitleTime(matcher.group()) : 0;
                        } else {
                            i = 0;
                            i2 = 0;
                        }
                        StringBuilder sb2 = new StringBuilder();
                        for (int i3 = 2; i3 < split.length; i3++) {
                            sb2.append(split[i3]);
                            sb2.append("<br />");
                        }
                        StringBuilder sb3 = new StringBuilder(sb2.substring(0, sb2.length() - 1));
                        srtPraseModel.setBeginTime(i2);
                        srtPraseModel.setEndTime(i);
                        srtPraseModel.setSrtBody(sb3.toString());
                        list.add(srtPraseModel);
                        sb.delete(0, sb.length());
                    }
                }
            }
            if (list.size() > 0) {
                lastEndTime = list.get(list.size() - 1).getEndTime();
            }
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static List<SrtPraseModel> parseContentSrt(String str, List<SrtPraseModel> list) {
        int i;
        int i2;
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(str.getBytes())), 8192);
            StringBuffer stringBuffer = new StringBuffer();
            while (true) {
                String readLine = bufferedReader.readLine();
                if (readLine == null) {
                    break;
                } else if (!readLine.equals("")) {
                    stringBuffer.append(readLine);
                    stringBuffer.append("@");
                } else {
                    String[] split = stringBuffer.toString().trim().split("@");
                    if (split.length < 3) {
                        stringBuffer.delete(0, stringBuffer.length());
                    } else {
                        SrtPraseModel srtPraseModel = new SrtPraseModel();
                        Matcher matcher = Pattern.compile("\\d+:\\d+:\\d+,\\d+").matcher(com.dueeeke.utils.StringUtils.formatContent(split[1]));
                        if (matcher.find()) {
                            i2 = parseSubtitleTime(matcher.group());
                            i = matcher.find() ? parseSubtitleTime(matcher.group()) : 0;
                        } else {
                            i = 0;
                            i2 = 0;
                        }
                        StringBuilder sb = new StringBuilder();
                        for (int i3 = 2; i3 < split.length; i3++) {
                            sb.append(split[i3]);
                            sb.append("<br />");
                        }
                        StringBuilder sb2 = new StringBuilder(sb.substring(0, sb.length() - 1));
                        srtPraseModel.setBeginTime(i2);
                        srtPraseModel.setEndTime(i);
                        srtPraseModel.setSrtBody(sb2.toString());
                        list.add(srtPraseModel);
                        stringBuffer.delete(0, stringBuffer.length());
                    }
                }
            }
            if (list.size() > 0) {
                lastEndTime = list.get(list.size() - 1).getEndTime();
            }
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static int parseSubtitleTime(String str) {
        int parseInt;
        String[] split = str.replace(",", ":").replace(com.huantansheng.easyphotos.utils.file.FileUtils.HIDDEN_PREFIX, ":").split(":");
        if (split[3].length() == 2) {
            parseInt = Integer.parseInt(split[3]) * 10;
        } else {
            parseInt = Integer.parseInt(split[3]);
        }
        return (Integer.parseInt(split[0]) * DateTimeConstants.MILLIS_PER_HOUR) + (Integer.parseInt(split[1]) * DateTimeConstants.MILLIS_PER_MINUTE) + (Integer.parseInt(split[2]) * 1000) + parseInt;
    }

    public static void showSRT(MediaPlayerControl mediaPlayerControl, LrcController lrcController, TextView textView, int i, int i2, List<SrtPraseModel> list) {
        int currentPosition = (int) (mediaPlayerControl.getCurrentPosition() + (i2 * 1000));
        if (currentPosition > lastEndTime) {
            textView.setVisibility(8);
        }
        int size = list.size();
        for (int i3 = 0; i3 < size; i3++) {
            SrtPraseModel srtPraseModel = list.get(i3);
            if (srtPraseModel != null) {
                if (currentPosition >= srtPraseModel.getBeginTime() && currentPosition <= srtPraseModel.getEndTime()) {
                    String srtBody = srtPraseModel.getSrtBody();
                    textView.setVisibility(0);
                    if (srtBody != null) {
                        SpanUtils.with(textView).append(Html.fromHtml(srtBody)).setFontSize(i, true).setForegroundColor(ContextCompat.getColor(App.getContext(), R.color.srt_text)).setTypeface(Typeface.DEFAULT_BOLD).setShadow(3.0f, 0.0f, 4.0f, Color.parseColor("#80000000")).create();
                    }
                    if (lrcController != null) {
                        lrcController.setToPosition(i3);
                    }
                    selectPos = i3;
                    return;
                } else if (i3 < list.size() - 2 && currentPosition > srtPraseModel.getEndTime() && currentPosition < list.get(i3 + 1).getBeginTime()) {
                    textView.setVisibility(8);
                }
            }
        }
    }

    public static Boolean isGB2312(String str) {
        int i = 0;
        while (i < str.length()) {
            int i2 = i + 1;
            boolean matches = Pattern.matches("[一-龥]", str.substring(i, i2));
            if (!matches) {
                return Boolean.valueOf(matches);
            }
            i = i2;
        }
        return true;
    }
}
