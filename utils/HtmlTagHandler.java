package com.movieboxpro.android.utils;

import android.graphics.Color;
import android.text.Editable;
import android.text.Html;
import android.text.TextUtils;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import java.lang.reflect.Field;
import java.util.HashMap;
import org.xml.sax.XMLReader;
/* loaded from: classes3.dex */
public class HtmlTagHandler implements Html.TagHandler {
    private String tagName;
    private int startIndex = 0;
    private int endIndex = 0;
    final HashMap<String, String> attributes = new HashMap<>();

    public HtmlTagHandler(String str) {
        this.tagName = str;
    }

    @Override // android.text.Html.TagHandler
    public void handleTag(boolean z, String str, Editable editable, XMLReader xMLReader) {
        if (str.equalsIgnoreCase(this.tagName)) {
            parseAttributes(xMLReader);
            if (z) {
                startHandleTag(str, editable, xMLReader);
            } else {
                endEndHandleTag(str, editable, xMLReader);
            }
        }
    }

    public void startHandleTag(String str, Editable editable, XMLReader xMLReader) {
        this.startIndex = editable.length();
    }

    public void endEndHandleTag(String str, Editable editable, XMLReader xMLReader) {
        this.endIndex = editable.length();
        String str2 = this.attributes.get("color");
        String str3 = this.attributes.get("size").split("px")[0];
        if (!TextUtils.isEmpty(str2)) {
            editable.setSpan(new ForegroundColorSpan(Color.parseColor(str2)), this.startIndex, this.endIndex, 33);
        }
        if (TextUtils.isEmpty(str3)) {
            return;
        }
        editable.setSpan(new AbsoluteSizeSpan(Integer.parseInt(str3)), this.startIndex, this.endIndex, 33);
    }

    private void parseAttributes(XMLReader xMLReader) {
        try {
            Field declaredField = xMLReader.getClass().getDeclaredField("theNewElement");
            declaredField.setAccessible(true);
            Object obj = declaredField.get(xMLReader);
            Field declaredField2 = obj.getClass().getDeclaredField("theAtts");
            declaredField2.setAccessible(true);
            Object obj2 = declaredField2.get(obj);
            Field declaredField3 = obj2.getClass().getDeclaredField("data");
            declaredField3.setAccessible(true);
            String[] strArr = (String[]) declaredField3.get(obj2);
            Field declaredField4 = obj2.getClass().getDeclaredField("length");
            declaredField4.setAccessible(true);
            int intValue = ((Integer) declaredField4.get(obj2)).intValue();
            for (int i = 0; i < intValue; i++) {
                int i2 = i * 5;
                this.attributes.put(strArr[i2 + 1], strArr[i2 + 4]);
            }
        } catch (Exception unused) {
        }
    }
}
