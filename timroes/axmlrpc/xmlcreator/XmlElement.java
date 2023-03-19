package com.movieboxpro.android.timroes.axmlrpc.xmlcreator;

import java.util.ArrayList;
import java.util.List;
/* loaded from: classes3.dex */
public class XmlElement {
    private List<XmlElement> children = new ArrayList();
    private String content;
    private String name;

    public XmlElement(String str) {
        this.name = str;
    }

    public void addChildren(XmlElement xmlElement) {
        this.children.add(xmlElement);
    }

    public void setContent(String str) {
        this.content = str;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        String str = this.content;
        if (str != null && str.length() > 0) {
            sb.append("\n<");
            sb.append(this.name);
            sb.append(">");
            sb.append(this.content);
            sb.append("</");
            sb.append(this.name);
            sb.append(">\n");
            return sb.toString();
        } else if (this.children.size() > 0) {
            sb.append("\n<");
            sb.append(this.name);
            sb.append(">");
            for (XmlElement xmlElement : this.children) {
                sb.append(xmlElement.toString());
            }
            sb.append("</");
            sb.append(this.name);
            sb.append(">\n");
            return sb.toString();
        } else {
            sb.append("\n<");
            sb.append(this.name);
            sb.append("/>\n");
            return sb.toString();
        }
    }
}
