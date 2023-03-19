package com.movieboxpro.android.timroes.axmlrpc.serializer;

import com.movieboxpro.android.timroes.axmlrpc.XMLRPCException;
import com.movieboxpro.android.timroes.axmlrpc.XMLUtil;
import com.movieboxpro.android.timroes.axmlrpc.xmlcreator.XmlElement;
import com.movieboxpro.android.timroes.base64.Base64;
import org.w3c.dom.Element;
/* loaded from: classes3.dex */
public class Base64Serializer implements Serializer {
    @Override // com.movieboxpro.android.timroes.axmlrpc.serializer.Serializer
    public Object deserialize(Element element) throws XMLRPCException {
        return Base64.decode(XMLUtil.getOnlyTextContent(element.getChildNodes()));
    }

    @Override // com.movieboxpro.android.timroes.axmlrpc.serializer.Serializer
    public String deserialize2(Element element) throws XMLRPCException {
        return Base64.decode(XMLUtil.getOnlyTextContent(element.getChildNodes())) + "";
    }

    @Override // com.movieboxpro.android.timroes.axmlrpc.serializer.Serializer
    public XmlElement serialize(Object obj) {
        return XMLUtil.makeXmlTag(SerializerHandler.TYPE_BASE64, Base64.encode((Byte[]) obj));
    }
}
