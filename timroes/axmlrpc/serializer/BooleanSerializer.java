package com.movieboxpro.android.timroes.axmlrpc.serializer;

import com.google.android.exoplayer2.metadata.icy.IcyHeaders;
import com.movieboxpro.android.timroes.axmlrpc.XMLRPCException;
import com.movieboxpro.android.timroes.axmlrpc.XMLUtil;
import com.movieboxpro.android.timroes.axmlrpc.xmlcreator.XmlElement;
import org.w3c.dom.Element;
/* loaded from: classes3.dex */
public class BooleanSerializer implements Serializer {
    @Override // com.movieboxpro.android.timroes.axmlrpc.serializer.Serializer
    public Object deserialize(Element element) throws XMLRPCException {
        return XMLUtil.getOnlyTextContent(element.getChildNodes()).equals(IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE) ? Boolean.TRUE : Boolean.FALSE;
    }

    @Override // com.movieboxpro.android.timroes.axmlrpc.serializer.Serializer
    public String deserialize2(Element element) throws XMLRPCException {
        return XMLUtil.getOnlyTextContent(element.getChildNodes()).equals(IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE) ? "true" : "false";
    }

    @Override // com.movieboxpro.android.timroes.axmlrpc.serializer.Serializer
    public XmlElement serialize(Object obj) {
        return XMLUtil.makeXmlTag("boolean", ((Boolean) obj).booleanValue() ? IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE : "0");
    }
}
