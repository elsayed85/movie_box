package com.movieboxpro.android.timroes.axmlrpc.serializer;

import com.alibaba.fastjson.JSONObject;
import com.movieboxpro.android.timroes.axmlrpc.XMLRPCException;
import com.movieboxpro.android.timroes.axmlrpc.XMLRPCRuntimeException;
import com.movieboxpro.android.timroes.axmlrpc.XMLUtil;
import com.movieboxpro.android.timroes.axmlrpc.xmlcreator.XmlElement;
import java.util.Map;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
/* loaded from: classes3.dex */
public class StructSerializer implements Serializer {
    private static final String STRUCT_MEMBER = "member";
    private static final String STRUCT_NAME = "name";
    private static final String STRUCT_VALUE = "value";
    private final SerializerHandler serializerHandler;

    @Override // com.movieboxpro.android.timroes.axmlrpc.serializer.Serializer
    public String deserialize2(Element element) throws XMLRPCException {
        return null;
    }

    public StructSerializer(SerializerHandler serializerHandler) {
        this.serializerHandler = serializerHandler;
    }

    @Override // com.movieboxpro.android.timroes.axmlrpc.serializer.Serializer
    public Object deserialize(Element element) throws XMLRPCException {
        JSONObject jSONObject = new JSONObject();
        for (int i = 0; i < element.getChildNodes().getLength(); i++) {
            Node item = element.getChildNodes().item(i);
            if ((item.getNodeType() != 3 || item.getNodeValue().trim().length() > 0) && item.getNodeType() != 8) {
                if (item.getNodeType() != 1 || !STRUCT_MEMBER.equals(item.getNodeName())) {
                    throw new XMLRPCException("Only struct members allowed within a struct.");
                }
                String str = null;
                Object obj = null;
                for (int i2 = 0; i2 < item.getChildNodes().getLength(); i2++) {
                    Node item2 = item.getChildNodes().item(i2);
                    if ((item2.getNodeType() != 3 || item2.getNodeValue().trim().length() > 0) && item2.getNodeType() != 8) {
                        if ("name".equals(item2.getNodeName())) {
                            if (str != null) {
                                throw new XMLRPCException("Name of a struct member cannot be set twice.");
                            }
                            str = XMLUtil.getOnlyTextContent(item2.getChildNodes());
                        } else if (item2.getNodeType() == 1 && "value".equals(item2.getNodeName())) {
                            if (obj != null) {
                                throw new XMLRPCException("Value of a struct member cannot be set twice.");
                            }
                            obj = this.serializerHandler.deserialize((Element) item2);
                        } else {
                            throw new XMLRPCException("A struct member must only contain one name and one value.");
                        }
                    }
                }
                jSONObject.put(str, obj);
            }
        }
        return jSONObject;
    }

    @Override // com.movieboxpro.android.timroes.axmlrpc.serializer.Serializer
    public XmlElement serialize(Object obj) {
        XmlElement xmlElement = new XmlElement(SerializerHandler.TYPE_STRUCT);
        try {
            for (Map.Entry entry : ((Map) obj).entrySet()) {
                XmlElement xmlElement2 = new XmlElement(STRUCT_MEMBER);
                XmlElement xmlElement3 = new XmlElement("name");
                XmlElement xmlElement4 = new XmlElement("value");
                xmlElement3.setContent((String) entry.getKey());
                xmlElement4.addChildren(this.serializerHandler.serialize(entry.getValue()));
                xmlElement2.addChildren(xmlElement3);
                xmlElement2.addChildren(xmlElement4);
                xmlElement.addChildren(xmlElement2);
            }
            return xmlElement;
        } catch (XMLRPCException e) {
            throw new XMLRPCRuntimeException(e);
        }
    }
}
