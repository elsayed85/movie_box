package com.movieboxpro.android.timroes.axmlrpc.serializer;

import com.movieboxpro.android.timroes.axmlrpc.XMLRPCException;
import com.movieboxpro.android.timroes.axmlrpc.XMLUtil;
import com.movieboxpro.android.timroes.axmlrpc.xmlcreator.XmlElement;
import org.seamless.xml.DOM;
import org.w3c.dom.Element;
/* loaded from: classes3.dex */
public class StringSerializer implements Serializer {
    private boolean decodeStrings;
    private boolean encodeStrings;

    public StringSerializer(boolean z, boolean z2) {
        this.decodeStrings = z2;
        this.encodeStrings = z;
    }

    @Override // com.movieboxpro.android.timroes.axmlrpc.serializer.Serializer
    public Object deserialize(Element element) throws XMLRPCException {
        String onlyTextContent = XMLUtil.getOnlyTextContent(element.getChildNodes());
        return this.decodeStrings ? onlyTextContent.replaceAll("&lt;", "<").replaceAll("&amp;", "&") : onlyTextContent;
    }

    @Override // com.movieboxpro.android.timroes.axmlrpc.serializer.Serializer
    public String deserialize2(Element element) throws XMLRPCException {
        String onlyTextContent = XMLUtil.getOnlyTextContent(element.getChildNodes());
        return this.decodeStrings ? onlyTextContent.replaceAll("&lt;", "<").replaceAll("&amp;", "&") : onlyTextContent;
    }

    @Override // com.movieboxpro.android.timroes.axmlrpc.serializer.Serializer
    public XmlElement serialize(Object obj) {
        String obj2 = obj.toString();
        if (this.encodeStrings) {
            obj2 = obj2.replaceAll("&", "&amp;").replaceAll("<", "&lt;").replaceAll(DOM.CDATA_END, "]]&gt;");
        }
        return XMLUtil.makeXmlTag("string", obj2);
    }
}
