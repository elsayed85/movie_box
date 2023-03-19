package com.movieboxpro.android.timroes.axmlrpc.serializer;

import com.movieboxpro.android.timroes.axmlrpc.XMLRPCException;
import com.movieboxpro.android.timroes.axmlrpc.XMLUtil;
import com.movieboxpro.android.timroes.axmlrpc.xmlcreator.XmlElement;
import java.math.BigDecimal;
import org.w3c.dom.Element;
/* loaded from: classes3.dex */
public class DoubleSerializer implements Serializer {
    @Override // com.movieboxpro.android.timroes.axmlrpc.serializer.Serializer
    public Object deserialize(Element element) throws XMLRPCException {
        return Double.valueOf(XMLUtil.getOnlyTextContent(element.getChildNodes()));
    }

    @Override // com.movieboxpro.android.timroes.axmlrpc.serializer.Serializer
    public String deserialize2(Element element) throws XMLRPCException {
        return Double.valueOf(XMLUtil.getOnlyTextContent(element.getChildNodes())) + "";
    }

    @Override // com.movieboxpro.android.timroes.axmlrpc.serializer.Serializer
    public XmlElement serialize(Object obj) {
        return XMLUtil.makeXmlTag(SerializerHandler.TYPE_DOUBLE, BigDecimal.valueOf(((Number) obj).doubleValue()).toPlainString());
    }
}
