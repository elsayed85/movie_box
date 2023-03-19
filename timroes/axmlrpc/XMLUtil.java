package com.movieboxpro.android.timroes.axmlrpc;

import com.movieboxpro.android.timroes.axmlrpc.xmlcreator.XmlElement;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
/* loaded from: classes3.dex */
public class XMLUtil {
    private XMLUtil() {
    }

    public static Element getOnlyChildElement(NodeList nodeList) throws XMLRPCException {
        Element element = null;
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node item = nodeList.item(i);
            if ((item.getNodeType() != 3 || item.getNodeValue().trim().length() > 0) && item.getNodeType() != 8) {
                if (item.getNodeType() != 1) {
                    throw new XMLRPCException("Only element nodes allowed.");
                }
                if (element != null) {
                    throw new XMLRPCException("Element has more than one children.");
                }
                element = (Element) item;
            }
        }
        return element;
    }

    public static String getOnlyTextContent(NodeList nodeList) throws XMLRPCException {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node item = nodeList.item(i);
            if (item.getNodeType() != 8) {
                if (item.getNodeType() != 3) {
                    throw new XMLRPCException("Element must contain only text elements.");
                }
                sb.append(item.getNodeValue());
            }
        }
        return sb.toString();
    }

    public static boolean hasChildElement(NodeList nodeList) {
        for (int i = 0; i < nodeList.getLength(); i++) {
            if (nodeList.item(i).getNodeType() == 1) {
                return true;
            }
        }
        return false;
    }

    public static XmlElement makeXmlTag(String str, String str2) {
        XmlElement xmlElement = new XmlElement(str);
        xmlElement.setContent(str2);
        return xmlElement;
    }
}
