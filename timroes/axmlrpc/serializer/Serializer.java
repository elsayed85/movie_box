package com.movieboxpro.android.timroes.axmlrpc.serializer;

import com.movieboxpro.android.timroes.axmlrpc.XMLRPCException;
import com.movieboxpro.android.timroes.axmlrpc.xmlcreator.XmlElement;
import org.w3c.dom.Element;
/* loaded from: classes3.dex */
public interface Serializer {
    Object deserialize(Element element) throws XMLRPCException;

    String deserialize2(Element element) throws XMLRPCException;

    XmlElement serialize(Object obj);
}
