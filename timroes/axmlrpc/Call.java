package com.movieboxpro.android.timroes.axmlrpc;

import com.movieboxpro.android.timroes.axmlrpc.serializer.SerializerHandler;
import com.movieboxpro.android.timroes.axmlrpc.xmlcreator.SimpleXMLCreator;
import com.movieboxpro.android.timroes.axmlrpc.xmlcreator.XmlElement;
/* loaded from: classes3.dex */
public class Call {
    private String method;
    private Object[] params;
    private final SerializerHandler serializerHandler;

    public Call(SerializerHandler serializerHandler, String str) {
        this(serializerHandler, str, null);
    }

    public Call(SerializerHandler serializerHandler, String str, Object[] objArr) {
        this.method = str;
        this.params = objArr;
        this.serializerHandler = serializerHandler;
    }

    public String getXML(boolean z) throws XMLRPCException {
        Object[] objArr;
        SimpleXMLCreator simpleXMLCreator = new SimpleXMLCreator();
        XmlElement xmlElement = new XmlElement("methodCall");
        simpleXMLCreator.setRootElement(xmlElement);
        XmlElement xmlElement2 = new XmlElement("methodName");
        xmlElement2.setContent(this.method);
        xmlElement.addChildren(xmlElement2);
        Object[] objArr2 = this.params;
        if (objArr2 != null && objArr2.length > 0) {
            XmlElement xmlElement3 = new XmlElement("params");
            xmlElement.addChildren(xmlElement3);
            for (Object obj : this.params) {
                if (obj != null) {
                    xmlElement3.addChildren(getXMLParam(obj));
                }
            }
        }
        return simpleXMLCreator.toString();
    }

    private XmlElement getXMLParam(Object obj) throws XMLRPCException, NullPointerException {
        XmlElement xmlElement = new XmlElement("param");
        XmlElement xmlElement2 = new XmlElement("value");
        xmlElement.addChildren(xmlElement2);
        xmlElement2.addChildren(this.serializerHandler.serialize(obj));
        return xmlElement;
    }
}
