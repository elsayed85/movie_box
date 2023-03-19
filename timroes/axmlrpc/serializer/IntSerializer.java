package com.movieboxpro.android.timroes.axmlrpc.serializer;

import com.movieboxpro.android.timroes.axmlrpc.XMLRPCException;
import com.movieboxpro.android.timroes.axmlrpc.XMLUtil;
import com.movieboxpro.android.timroes.axmlrpc.xmlcreator.XmlElement;
import org.w3c.dom.Element;
/* loaded from: classes3.dex */
public class IntSerializer implements Serializer {
    @Override // com.movieboxpro.android.timroes.axmlrpc.serializer.Serializer
    public Object deserialize(Element element) throws XMLRPCException {
        return Integer.valueOf(Integer.parseInt(XMLUtil.getOnlyTextContent(element.getChildNodes())));
    }

    @Override // com.movieboxpro.android.timroes.axmlrpc.serializer.Serializer
    public String deserialize2(Element element) throws XMLRPCException {
        return Integer.parseInt(XMLUtil.getOnlyTextContent(element.getChildNodes())) + "";
    }

    @Override // com.movieboxpro.android.timroes.axmlrpc.serializer.Serializer
    public XmlElement serialize(Object obj) {
        return XMLUtil.makeXmlTag("int", obj.toString());
    }
}
