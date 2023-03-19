package com.movieboxpro.android.timroes.axmlrpc.serializer;

import com.movieboxpro.android.timroes.axmlrpc.XMLRPCException;
import com.movieboxpro.android.timroes.axmlrpc.XMLRPCRuntimeException;
import com.movieboxpro.android.timroes.axmlrpc.XMLUtil;
import com.movieboxpro.android.timroes.axmlrpc.xmlcreator.XmlElement;
import java.util.ArrayList;
import java.util.Arrays;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
/* loaded from: classes3.dex */
public class ArraySerializer implements Serializer {
    private static final String ARRAY_DATA = "data";
    private static final String ARRAY_VALUE = "value";
    private final SerializerHandler serializerHandler;

    @Override // com.movieboxpro.android.timroes.axmlrpc.serializer.Serializer
    public String deserialize2(Element element) throws XMLRPCException {
        return null;
    }

    public ArraySerializer(SerializerHandler serializerHandler) {
        this.serializerHandler = serializerHandler;
    }

    @Override // com.movieboxpro.android.timroes.axmlrpc.serializer.Serializer
    public Object deserialize(Element element) throws XMLRPCException {
        ArrayList arrayList = new ArrayList();
        Element onlyChildElement = XMLUtil.getOnlyChildElement(element.getChildNodes());
        if (!"data".equals(onlyChildElement.getNodeName())) {
            throw new XMLRPCException("The array must contain one data tag.");
        }
        for (int i = 0; i < onlyChildElement.getChildNodes().getLength(); i++) {
            Node item = onlyChildElement.getChildNodes().item(i);
            if (item != null && ((item.getNodeType() != 3 || item.getNodeValue().trim().length() > 0) && item.getNodeType() != 8)) {
                if (item.getNodeType() != 1) {
                    throw new XMLRPCException("Wrong element inside of array.");
                }
                arrayList.add(this.serializerHandler.deserialize((Element) item).toString());
            }
        }
        return Arrays.toString(arrayList.toArray());
    }

    @Override // com.movieboxpro.android.timroes.axmlrpc.serializer.Serializer
    public XmlElement serialize(Object obj) {
        Iterable asList;
        if (obj instanceof Iterable) {
            asList = (Iterable) obj;
        } else {
            asList = Arrays.asList((Object[]) obj);
        }
        XmlElement xmlElement = new XmlElement(SerializerHandler.TYPE_ARRAY);
        XmlElement xmlElement2 = new XmlElement("data");
        xmlElement.addChildren(xmlElement2);
        try {
            for (Object obj2 : asList) {
                XmlElement xmlElement3 = new XmlElement("value");
                xmlElement3.addChildren(this.serializerHandler.serialize(obj2));
                xmlElement2.addChildren(xmlElement3);
            }
            return xmlElement;
        } catch (XMLRPCException e) {
            throw new XMLRPCRuntimeException(e);
        }
    }
}
