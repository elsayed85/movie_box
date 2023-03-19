package com.movieboxpro.android.utils;

import com.google.android.exoplayer2.text.ttml.TtmlNode;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import org.fourthline.cling.support.messagebox.parser.MessageElement;
import org.joda.time.DateTime;
import org.joda.time.DateTimeConstants;
import org.joda.time.Days;
import org.joda.time.Hours;
import org.joda.time.LocalDateTime;
import org.joda.time.Minutes;
import org.joda.time.Seconds;
import org.seamless.xhtml.XHTMLElement;
/* compiled from: TimeUtils.kt */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0010\n\u0002\u0010\b\n\u0002\b\u000f\n\u0002\u0010\u000b\n\u0002\b\u0005\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0016\u0010\u0011\u001a\u00020\u00042\u0006\u0010\u0012\u001a\u00020\u00072\u0006\u0010\u0013\u001a\u00020\u0007J\u000e\u0010\u0014\u001a\u00020\u00042\u0006\u0010\u0015\u001a\u00020\u0007J\u000e\u0010\u0016\u001a\u00020\u00042\u0006\u0010\u0017\u001a\u00020\u0018J\u000e\u0010\u0019\u001a\u00020\u00042\u0006\u0010\u0015\u001a\u00020\u0007J\u000e\u0010\u001a\u001a\u00020\u00042\u0006\u0010\u001b\u001a\u00020\u0007J\u000e\u0010\u001c\u001a\u00020\u00042\u0006\u0010\u0015\u001a\u00020\u0007J\u0010\u0010\u001d\u001a\u00020\u00042\u0006\u0010\u0015\u001a\u00020\u0007H\u0007J\u0016\u0010\u001d\u001a\u00020\u00042\u0006\u0010\u0015\u001a\u00020\u00072\u0006\u0010\u001e\u001a\u00020\u0004J\u001e\u0010\u001d\u001a\u00020\u00042\b\u0010\u001f\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u001e\u001a\u0004\u0018\u00010\u0004H\u0007J\u000e\u0010 \u001a\u00020\u00042\u0006\u0010\u0015\u001a\u00020\u0007J\u0010\u0010!\u001a\u00020\u00042\u0006\u0010\u0015\u001a\u00020\u0007H\u0007J\u0010\u0010\"\u001a\u00020\u00042\u0006\u0010\u0015\u001a\u00020\u0007H\u0007J\u0012\u0010\r\u001a\u00020\u00042\b\u0010\u001e\u001a\u0004\u0018\u00010\u0004H\u0007J\u0010\u0010#\u001a\u00020\u00042\u0006\u0010$\u001a\u00020\u0018H\u0007J\u0010\u0010%\u001a\u00020\u00042\u0006\u0010$\u001a\u00020\u0007H\u0007J\u0010\u0010&\u001a\u00020\u00042\u0006\u0010$\u001a\u00020\u0007H\u0007J\u000e\u0010'\u001a\u00020(2\u0006\u0010\u001b\u001a\u00020\u0007J\u000e\u0010)\u001a\u00020(2\u0006\u0010*\u001a\u00020\u0007J\u0010\u0010+\u001a\u00020\u00182\u0006\u0010,\u001a\u00020\u0007H\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u001a\u0010\u0006\u001a\u00020\u00078FX\u0087\u0004¢\u0006\f\u0012\u0004\b\b\u0010\u0002\u001a\u0004\b\t\u0010\nR\u001a\u0010\u000b\u001a\u00020\u00048FX\u0087\u0004¢\u0006\f\u0012\u0004\b\f\u0010\u0002\u001a\u0004\b\r\u0010\u000eR\u0014\u0010\u000f\u001a\u00020\u00078BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0010\u0010\n¨\u0006-"}, d2 = {"Lcom/movieboxpro/android/utils/TimeUtils;", "", "()V", "DETAULT_PATTERN", "", "PATTERN_MINUTE_SECOND", "currentTime", "", "getCurrentTime$annotations", "getCurrentTime", "()J", "formatedTime", "getFormatedTime$annotations", "getFormatedTime", "()Ljava/lang/String;", "weeOfToday", "getWeeOfToday", "calPercent", "diliverNum", "queryMailNum", "chatTime", "ms", "convertSeconds", "seconds", "", "formatPlayTime", "formatRecentFileTime", "millis", "formatReviewTime", "formatTime", "pattern", "time", "formatTime2", "formatTime3", "formatTime4", "getTime", "second", "getTimeFull", "getTimeL", "isToady", "", "isYesterday", "day1", "minutesSinceLastBack", TtmlNode.ANNOTATION_POSITION_BEFORE, "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes.dex */
public final class TimeUtils {
    public static final String DETAULT_PATTERN = "yyyy-MM-dd HH:mm:ss";
    public static final TimeUtils INSTANCE = new TimeUtils();
    public static final String PATTERN_MINUTE_SECOND = "HH:mm";

    @JvmStatic
    public static /* synthetic */ void getCurrentTime$annotations() {
    }

    @JvmStatic
    public static /* synthetic */ void getFormatedTime$annotations() {
    }

    public final String formatTime(String str) {
        return formatTime$default(this, str, null, 2, null);
    }

    private TimeUtils() {
    }

    public static final long getCurrentTime() {
        return LocalDateTime.now().toDateTime().getMillis();
    }

    public static final String getFormatedTime() {
        return getFormatedTime(PATTERN_MINUTE_SECOND);
    }

    @JvmStatic
    public static final String getFormatedTime(String str) {
        if (str == null) {
            str = DETAULT_PATTERN;
        }
        String dateTime = LocalDateTime.now().toDateTime().toString(str);
        Intrinsics.checkNotNullExpressionValue(dateTime, "now().toDateTime().toString(pattern)");
        return dateTime;
    }

    public static /* synthetic */ String formatTime$default(TimeUtils timeUtils, String str, String str2, int i, Object obj) {
        if ((i & 2) != 0) {
            str2 = DETAULT_PATTERN;
        }
        return timeUtils.formatTime(str, str2);
    }

    public final String formatTime(String str, String str2) {
        try {
            return formatTime(new SimpleDateFormat(str2, Locale.CHINA).parse(str).getTime());
        } catch (ParseException e) {
            e.printStackTrace();
            return "";
        }
    }

    public final String formatTime(long j, String pattern) {
        Intrinsics.checkNotNullParameter(pattern, "pattern");
        String format = new SimpleDateFormat(pattern, Locale.ENGLISH).format(new Date(j));
        Intrinsics.checkNotNullExpressionValue(format, "sdf.format(date)");
        return format;
    }

    @JvmStatic
    public static final int minutesSinceLastBack(long j) {
        return Minutes.minutesBetween(new LocalDateTime(j).toDateTime(), LocalDateTime.now().toDateTime()).getMinutes();
    }

    public final String formatTime2(long j) {
        LocalDateTime.now().toDateTime();
        String dateTime = new LocalDateTime(j).toDateTime().toString("yyyy-MM-dd");
        Intrinsics.checkNotNullExpressionValue(dateTime, "before.toString(\"yyyy-MM-dd\")");
        return dateTime;
    }

    @JvmStatic
    public static final String formatTime3(long j) {
        String format = new SimpleDateFormat("MMM dd,yyyy", Locale.ENGLISH).format(new Date(j));
        Intrinsics.checkNotNullExpressionValue(format, "sdf.format(date)");
        return format;
    }

    @JvmStatic
    public static final String formatTime4(long j) {
        String format = new SimpleDateFormat("HH:mm,MMM dd,yyyy", Locale.ENGLISH).format(new Date(j));
        Intrinsics.checkNotNullExpressionValue(format, "sdf.format(date)");
        return format;
    }

    @JvmStatic
    public static final String formatTime(long j) {
        DateTime dateTime = LocalDateTime.now().toDateTime();
        DateTime dateTime2 = new LocalDateTime(j).toDateTime();
        if (dateTime2.getMillis() > dateTime.getMillis()) {
            String dateTime3 = dateTime2.toString("HH:mm:ss,MMM dd,yyyy");
            Intrinsics.checkNotNullExpressionValue(dateTime3, "before.toString(\"HH:mm:ss,MMM dd,yyyy\")");
            return dateTime3;
        }
        StringBuilder sb = new StringBuilder();
        DateTime dateTime4 = dateTime2;
        DateTime dateTime5 = dateTime;
        int seconds = Seconds.secondsBetween(dateTime4, dateTime5).getSeconds();
        int minutes = Minutes.minutesBetween(dateTime4, dateTime5).getMinutes();
        int hours = Hours.hoursBetween(dateTime4, dateTime5).getHours();
        int days = Days.daysBetween(dateTime4, dateTime5).getDays();
        if (seconds == 0) {
            sb.append("1s");
            String sb2 = sb.toString();
            Intrinsics.checkNotNullExpressionValue(sb2, "builder.toString()");
            return sb2;
        } else if (seconds < 5) {
            sb.append(seconds);
            sb.append("s");
            String sb3 = sb.toString();
            Intrinsics.checkNotNullExpressionValue(sb3, "builder.toString()");
            return sb3;
        } else if (seconds < 60) {
            sb.append(seconds);
            sb.append("s");
            String sb4 = sb.toString();
            Intrinsics.checkNotNullExpressionValue(sb4, "builder.toString()");
            return sb4;
        } else if (minutes < 60) {
            sb.append(minutes);
            sb.append(MessageElement.XPATH_PREFIX);
            String sb5 = sb.toString();
            Intrinsics.checkNotNullExpressionValue(sb5, "builder.toString()");
            return sb5;
        } else if (hours < 24) {
            sb.append(hours);
            sb.append(XHTMLElement.XPATH_PREFIX);
            String sb6 = sb.toString();
            Intrinsics.checkNotNullExpressionValue(sb6, "builder.toString()");
            return sb6;
        } else if (days <= 10) {
            sb.append(days);
            sb.append("d");
            String sb7 = sb.toString();
            Intrinsics.checkNotNullExpressionValue(sb7, "builder.toString()");
            return sb7;
        } else {
            String dateTime6 = dateTime2.toString("MMM dd,yyyy");
            Intrinsics.checkNotNullExpressionValue(dateTime6, "before.toString(\"MMM dd,yyyy\")");
            return dateTime6;
        }
    }

    public final String formatRecentFileTime(long j) {
        return isToady(j) ? "TODAY" : isYesterday(j) ? "YESTERDAY" : formatTime2(j);
    }

    public final boolean isYesterday(long j) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(j);
        int i = calendar.get(6);
        calendar.setTimeInMillis(getWeeOfToday());
        int i2 = calendar.get(6);
        return i - i2 == 1 || i2 - i == 1;
    }

    public final boolean isToady(long j) {
        long weeOfToday = getWeeOfToday();
        return j >= weeOfToday && j < weeOfToday + ((long) DateTimeConstants.MILLIS_PER_DAY);
    }

    private final long getWeeOfToday() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(11, 0);
        calendar.set(13, 0);
        calendar.set(12, 0);
        calendar.set(14, 0);
        return calendar.getTimeInMillis();
    }

    public final String formatReviewTime(long j) {
        DateTime dateTime = LocalDateTime.now().toDateTime();
        DateTime dateTime2 = new LocalDateTime(j).toDateTime();
        if (dateTime2.getMillis() > dateTime.getMillis()) {
            String dateTime3 = dateTime2.toString(DETAULT_PATTERN);
            Intrinsics.checkNotNullExpressionValue(dateTime3, "before.toString(\"yyyy-MM-dd HH:mm:ss\")");
            return dateTime3;
        }
        StringBuilder sb = new StringBuilder();
        DateTime dateTime4 = dateTime2;
        DateTime dateTime5 = dateTime;
        int seconds = Seconds.secondsBetween(dateTime4, dateTime5).getSeconds();
        int minutes = Minutes.minutesBetween(dateTime4, dateTime5).getMinutes();
        int hours = Hours.hoursBetween(dateTime4, dateTime5).getHours();
        int days = Days.daysBetween(dateTime4, dateTime5).getDays();
        if (seconds < 60) {
            sb.append(seconds);
            sb.append("s");
            String sb2 = sb.toString();
            Intrinsics.checkNotNullExpressionValue(sb2, "builder.toString()");
            return sb2;
        } else if (minutes < 60) {
            sb.append(minutes);
            sb.append(MessageElement.XPATH_PREFIX);
            String sb3 = sb.toString();
            Intrinsics.checkNotNullExpressionValue(sb3, "builder.toString()");
            return sb3;
        } else if (hours < 24) {
            sb.append(hours);
            sb.append(XHTMLElement.XPATH_PREFIX);
            String sb4 = sb.toString();
            Intrinsics.checkNotNullExpressionValue(sb4, "builder.toString()");
            return sb4;
        } else if (days <= 10) {
            sb.append(days);
            sb.append("d");
            String sb5 = sb.toString();
            Intrinsics.checkNotNullExpressionValue(sb5, "builder.toString()");
            return sb5;
        } else if (days > 10) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(j);
            if (calendar.get(1) == Calendar.getInstance().get(1)) {
                String dateTime6 = dateTime2.toString("dd MMM");
                Intrinsics.checkNotNullExpressionValue(dateTime6, "{\n                before…g(\"dd MMM\")\n            }");
                return dateTime6;
            }
            String dateTime7 = dateTime2.toString("dd MMM,yyyy");
            Intrinsics.checkNotNullExpressionValue(dateTime7, "{\n                before… MMM,yyyy\")\n            }");
            return dateTime7;
        } else {
            String dateTime8 = dateTime2.toString(DETAULT_PATTERN);
            Intrinsics.checkNotNullExpressionValue(dateTime8, "before.toString(\"yyyy-MM-dd HH:mm:ss\")");
            return dateTime8;
        }
    }

    public final String chatTime(long j) {
        DateTime dateTime = LocalDateTime.now().toDateTime();
        DateTime dateTime2 = new LocalDateTime(j).toDateTime();
        if (dateTime2.getMillis() > dateTime.getMillis()) {
            String dateTime3 = dateTime2.toString(DETAULT_PATTERN);
            Intrinsics.checkNotNullExpressionValue(dateTime3, "before.toString(\"yyyy-MM-dd HH:mm:ss\")");
            return dateTime3;
        }
        long j2 = 1000;
        if (dateTime2.getMillis() / j2 == dateTime.getMillis() / j2) {
            return "刚刚";
        }
        StringBuilder sb = new StringBuilder();
        DateTime dateTime4 = dateTime2;
        DateTime dateTime5 = dateTime;
        int seconds = Seconds.secondsBetween(dateTime4, dateTime5).getSeconds();
        int minutes = Minutes.minutesBetween(dateTime4, dateTime5).getMinutes();
        int hours = Hours.hoursBetween(dateTime4, dateTime5).getHours();
        int days = Days.daysBetween(dateTime4, dateTime5).getDays();
        if (seconds < 60) {
            sb.append(seconds);
            sb.append("秒前");
            String sb2 = sb.toString();
            Intrinsics.checkNotNullExpressionValue(sb2, "builder.toString()");
            return sb2;
        } else if (minutes < 60) {
            sb.append(minutes);
            sb.append("分钟前");
            String sb3 = sb.toString();
            Intrinsics.checkNotNullExpressionValue(sb3, "builder.toString()");
            return sb3;
        } else if (hours < 24) {
            sb.append(hours);
            sb.append("小时前");
            String sb4 = sb.toString();
            Intrinsics.checkNotNullExpressionValue(sb4, "builder.toString()");
            return sb4;
        } else if (days <= 10) {
            sb.append(days);
            sb.append("天前");
            String sb5 = sb.toString();
            Intrinsics.checkNotNullExpressionValue(sb5, "builder.toString()");
            return sb5;
        } else {
            String dateTime6 = dateTime2.toString("yyyy-MM-dd");
            Intrinsics.checkNotNullExpressionValue(dateTime6, "before.toString(\"yyyy-MM-dd\")");
            return dateTime6;
        }
    }

    public final String convertSeconds(int i) {
        if (i <= 0) {
            return "00:00";
        }
        int i2 = i / 60;
        String str = i2 + "";
        if (i2 < 10) {
            str = Intrinsics.stringPlus("0", Integer.valueOf(i2));
        }
        int i3 = i % 60;
        String str2 = i3 + "";
        if (i3 < 10) {
            str2 = Intrinsics.stringPlus("0", Integer.valueOf(i3));
        }
        return str + ':' + str2;
    }

    @JvmStatic
    public static final String getTimeL(long j) {
        if (j < 10) {
            return Intrinsics.stringPlus("00:0", Long.valueOf(j));
        }
        if (j < 60) {
            return Intrinsics.stringPlus("00:", Long.valueOf(j));
        }
        if (j < 3600) {
            long j2 = 60;
            long j3 = j / j2;
            long j4 = j - (j2 * j3);
            if (j3 >= 10) {
                if (j4 < 10) {
                    return j3 + ":0" + j4;
                }
                StringBuilder sb = new StringBuilder();
                sb.append(j3);
                sb.append(':');
                sb.append(j4);
                return sb.toString();
            } else if (j4 < 10) {
                return '0' + j3 + ":0" + j4;
            } else {
                StringBuilder sb2 = new StringBuilder();
                sb2.append('0');
                sb2.append(j3);
                sb2.append(':');
                sb2.append(j4);
                return sb2.toString();
            }
        }
        long j5 = 3600;
        long j6 = j / j5;
        long j7 = j - (j5 * j6);
        long j8 = 60;
        long j9 = j7 / j8;
        long j10 = j7 - (j8 * j9);
        if (j6 >= 10) {
            if (j9 < 10) {
                if (j10 < 10) {
                    return j6 + ":0" + j9 + ":0" + j10;
                }
                return j6 + ":0" + j9 + ':' + j10;
            } else if (j10 < 10) {
                return j6 + ':' + j9 + ":0" + j10;
            } else {
                StringBuilder sb3 = new StringBuilder();
                sb3.append(j6);
                sb3.append(':');
                sb3.append(j9);
                sb3.append(':');
                sb3.append(j10);
                return sb3.toString();
            }
        } else if (j9 < 10) {
            if (j10 < 10) {
                return '0' + j6 + ":0" + j9 + ":0" + j10;
            }
            return '0' + j6 + ":0" + j9 + ':' + j10;
        } else if (j10 < 10) {
            return '0' + j6 + ':' + j9 + ":0" + j10;
        } else {
            StringBuilder sb4 = new StringBuilder();
            sb4.append('0');
            sb4.append(j6);
            sb4.append(':');
            sb4.append(j9);
            sb4.append(':');
            sb4.append(j10);
            return sb4.toString();
        }
    }

    @JvmStatic
    public static final String getTime(int i) {
        return getTimeL(i);
    }

    public final String calPercent(long j, long j2) {
        NumberFormat numberFormat = NumberFormat.getInstance();
        numberFormat.setMaximumFractionDigits(0);
        String format = numberFormat.format((((float) j) / ((float) j2)) * 100);
        Intrinsics.checkNotNullExpressionValue(format, "numberFormat.format((dil…loat() * 100).toDouble())");
        return format;
    }

    @JvmStatic
    public static final String getTimeFull(long j) {
        if (j < 10) {
            return Intrinsics.stringPlus("00:00:0", Long.valueOf(j));
        }
        if (j < 60) {
            return Intrinsics.stringPlus("00:00:", Long.valueOf(j));
        }
        if (j < 3600) {
            long j2 = 60;
            long j3 = j / j2;
            long j4 = j - (j2 * j3);
            if (j3 < 10) {
                if (j4 < 10) {
                    return "00:0" + j3 + ":0" + j4;
                }
                return "00:0" + j3 + ':' + j4;
            } else if (j4 < 10) {
                return "00:" + j3 + ":0" + j4;
            } else {
                return "00:" + j3 + ':' + j4;
            }
        }
        long j5 = 3600;
        long j6 = j / j5;
        long j7 = j - (j5 * j6);
        long j8 = 60;
        long j9 = j7 / j8;
        long j10 = j7 - (j8 * j9);
        if (j6 >= 10) {
            if (j9 < 10) {
                if (j10 < 10) {
                    return j6 + ":0" + j9 + ":0" + j10;
                }
                return j6 + ":0" + j9 + ':' + j10;
            } else if (j10 < 10) {
                return j6 + ':' + j9 + ":0" + j10;
            } else {
                StringBuilder sb = new StringBuilder();
                sb.append(j6);
                sb.append(':');
                sb.append(j9);
                sb.append(':');
                sb.append(j10);
                return sb.toString();
            }
        } else if (j9 < 10) {
            if (j10 < 10) {
                return '0' + j6 + ":0" + j9 + ":0" + j10;
            }
            return '0' + j6 + ":0" + j9 + ':' + j10;
        } else if (j10 < 10) {
            return '0' + j6 + ':' + j9 + ":0" + j10;
        } else {
            StringBuilder sb2 = new StringBuilder();
            sb2.append('0');
            sb2.append(j6);
            sb2.append(':');
            sb2.append(j9);
            sb2.append(':');
            sb2.append(j10);
            return sb2.toString();
        }
    }

    public final String formatPlayTime(long j) {
        DateTime dateTime = LocalDateTime.now().toDateTime();
        DateTime dateTime2 = new LocalDateTime(j).toDateTime();
        if (dateTime2.getMillis() > dateTime.getMillis()) {
            String dateTime3 = dateTime2.toString("MMM dd,yyyy HH:mm:ss");
            Intrinsics.checkNotNullExpressionValue(dateTime3, "before.toString(\"MMM dd,yyyy HH:mm:ss\")");
            return dateTime3;
        }
        StringBuilder sb = new StringBuilder();
        DateTime dateTime4 = dateTime2;
        DateTime dateTime5 = dateTime;
        int seconds = Seconds.secondsBetween(dateTime4, dateTime5).getSeconds();
        int minutes = Minutes.minutesBetween(dateTime4, dateTime5).getMinutes();
        int hours = Hours.hoursBetween(dateTime4, dateTime5).getHours();
        int days = Days.daysBetween(dateTime4, dateTime5).getDays();
        if (seconds < 5) {
            sb.append("Just Now");
            String sb2 = sb.toString();
            Intrinsics.checkNotNullExpressionValue(sb2, "builder.toString()");
            return sb2;
        } else if (seconds < 60) {
            sb.append(seconds);
            sb.append(" seconds ago");
            String sb3 = sb.toString();
            Intrinsics.checkNotNullExpressionValue(sb3, "builder.toString()");
            return sb3;
        } else if (minutes < 60) {
            sb.append(minutes);
            sb.append(" minutes ago");
            String sb4 = sb.toString();
            Intrinsics.checkNotNullExpressionValue(sb4, "builder.toString()");
            return sb4;
        } else if (hours < 24) {
            sb.append(hours);
            sb.append(" hours ago");
            String sb5 = sb.toString();
            Intrinsics.checkNotNullExpressionValue(sb5, "builder.toString()");
            return sb5;
        } else if (days <= 10) {
            sb.append(days);
            sb.append(" days ago");
            String sb6 = sb.toString();
            Intrinsics.checkNotNullExpressionValue(sb6, "builder.toString()");
            return sb6;
        } else {
            String dateTime6 = dateTime2.toString("MMM dd,yyyy");
            Intrinsics.checkNotNullExpressionValue(dateTime6, "before.toString(\"MMM dd,yyyy\")");
            return dateTime6;
        }
    }
}
