package com.movieboxpro.android.timroes.axmlrpc.serializer;

import com.movieboxpro.android.timroes.axmlrpc.XMLRPCException;
import com.movieboxpro.android.timroes.axmlrpc.XMLUtil;
import com.movieboxpro.android.timroes.axmlrpc.xmlcreator.XmlElement;
import java.text.SimpleDateFormat;
import org.w3c.dom.Element;
/* loaded from: classes3.dex */
public class DateTimeSerializer implements Serializer {
    private static final String DATETIME_FORMAT = "yyyyMMdd'T'HHmmss";
    private final SimpleDateFormat DATE_FORMATER = new SimpleDateFormat(DATETIME_FORMAT);
    private final boolean accepts_null_input;

    public DateTimeSerializer(boolean z) {
        this.accepts_null_input = z;
    }

    @Override // com.movieboxpro.android.timroes.axmlrpc.serializer.Serializer
    public Object deserialize(Element element) throws XMLRPCException {
        return deserialize(XMLUtil.getOnlyTextContent(element.getChildNodes()));
    }

    @Override // com.movieboxpro.android.timroes.axmlrpc.serializer.Serializer
    public String deserialize2(Element element) throws XMLRPCException {
        return deserialize(XMLUtil.getOnlyTextContent(element.getChildNodes())) + "";
    }

    public Object deserialize(String str) throws XMLRPCException {
        if (this.accepts_null_input) {
            if (str == null || str.trim().length() == 0) {
                return null;
            }
            return "";
        }
        return "";
    }

    @Override // com.movieboxpro.android.timroes.axmlrpc.serializer.Serializer
    public XmlElement serialize(Object obj) {
        return XMLUtil.makeXmlTag(SerializerHandler.TYPE_DATETIME, this.DATE_FORMATER.format(obj));
    }
}
