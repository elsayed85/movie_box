package com.movieboxpro.android.timroes.axmlrpc;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.movieboxpro.android.timroes.axmlrpc.serializer.SerializerHandler;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.Map;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
/* loaded from: classes3.dex */
public class ResponseParser {
    private static final String FAULT_CODE = "faultCode";
    private static final String FAULT_STRING = "faultString";

    public Object parse(SerializerHandler serializerHandler, InputStream inputStream, boolean z) throws XMLRPCException {
        try {
            DocumentBuilderFactory newInstance = DocumentBuilderFactory.newInstance();
            newInstance.setNamespaceAware(true);
            Document parse = newInstance.newDocumentBuilder().parse(inputStream);
            if (z) {
                printDocument(parse, System.out);
            }
            Element documentElement = parse.getDocumentElement();
            if (!documentElement.getNodeName().equals("methodResponse")) {
                throw new XMLRPCException("MethodResponse root tag is missing.");
            }
            Element onlyChildElement = XMLUtil.getOnlyChildElement(documentElement.getChildNodes());
            if (onlyChildElement.getNodeName().equals("params")) {
                Element onlyChildElement2 = XMLUtil.getOnlyChildElement(onlyChildElement.getChildNodes());
                if (!onlyChildElement2.getNodeName().equals("param")) {
                    throw new XMLRPCException("The params tag must contain a param tag.");
                }
                return getReturnValueFromElement(serializerHandler, onlyChildElement2);
            } else if (onlyChildElement.getNodeName().equals("fault")) {
                Map map = (Map) getReturnValueFromElement(serializerHandler, onlyChildElement);
                throw new XMLRPCServerException((String) map.get(FAULT_STRING), ((Integer) map.get(FAULT_CODE)).intValue());
            } else {
                throw new XMLRPCException("The methodResponse tag must contain a fault or params tag.");
            }
        } catch (XMLRPCServerException e) {
            throw e;
        } catch (Exception e2) {
            throw new XMLRPCException("Error getting result from server.", e2);
        }
    }

    public static void printDocument(Document document, OutputStream outputStream) throws IOException, TransformerException {
        Transformer newTransformer = TransformerFactory.newInstance().newTransformer();
        newTransformer.setOutputProperty("omit-xml-declaration", "no");
        newTransformer.setOutputProperty(FirebaseAnalytics.Param.METHOD, "xml");
        newTransformer.setOutputProperty("indent", "yes");
        newTransformer.setOutputProperty("encoding", "UTF-8");
        newTransformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
        newTransformer.transform(new DOMSource(document), new StreamResult(new OutputStreamWriter(outputStream, "UTF-8")));
    }

    private Object getReturnValueFromElement(SerializerHandler serializerHandler, Element element) throws XMLRPCException {
        return serializerHandler.deserialize2(XMLUtil.getOnlyChildElement(element.getChildNodes())).toString();
    }
}
