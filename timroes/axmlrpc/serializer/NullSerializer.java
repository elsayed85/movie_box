package com.movieboxpro.android.timroes.axmlrpc.serializer;

import com.movieboxpro.android.timroes.axmlrpc.XMLRPCException;
import com.movieboxpro.android.timroes.axmlrpc.xmlcreator.XmlElement;
import org.w3c.dom.Element;
/* loaded from: classes3.dex */
public class NullSerializer implements Serializer {
    @Override // com.movieboxpro.android.timroes.axmlrpc.serializer.Serializer
    public Object deserialize(Element element) throws XMLRPCException {
        return null;
    }

    @Override // com.movieboxpro.android.timroes.axmlrpc.serializer.Serializer
    public String deserialize2(Element element) throws XMLRPCException {
        return null;
    }

    @Override // com.movieboxpro.android.timroes.axmlrpc.serializer.Serializer
    public XmlElement serialize(Object obj) {
        return new XmlElement(SerializerHandler.TYPE_NULL);
    }
}
