package com.movieboxpro.android.timroes.axmlrpc.xmlcreator;
/* loaded from: classes3.dex */
public class SimpleXMLCreator {
    private XmlElement root;

    public void setRootElement(XmlElement xmlElement) {
        this.root = xmlElement;
    }

    public String toString() {
        return "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" + this.root.toString();
    }
}
