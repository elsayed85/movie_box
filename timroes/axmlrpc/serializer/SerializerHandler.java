package com.movieboxpro.android.timroes.axmlrpc.serializer;

import com.movieboxpro.android.timroes.axmlrpc.XMLRPCException;
import com.movieboxpro.android.timroes.axmlrpc.XMLUtil;
import com.movieboxpro.android.timroes.axmlrpc.xmlcreator.XmlElement;
import com.movieboxpro.android.utils.MLog;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import org.w3c.dom.Element;
/* loaded from: classes3.dex */
public class SerializerHandler {
    public static final String TYPE_ARRAY = "array";
    public static final String TYPE_BASE64 = "base64";
    public static final String TYPE_BOOLEAN = "boolean";
    public static final String TYPE_DATETIME = "dateTime.iso8601";
    public static final String TYPE_DOUBLE = "double";
    public static final String TYPE_INT = "int";
    public static final String TYPE_INT2 = "i4";
    public static final String TYPE_LONG = "i8";
    public static final String TYPE_NULL = "nil";
    public static final String TYPE_STRING = "string";
    public static final String TYPE_STRUCT = "struct";
    private ArraySerializer array;
    private Base64Serializer base64;
    private BooleanSerializer bool;
    private DateTimeSerializer datetime;
    private int flags;
    private DoubleSerializer floating;
    private IntSerializer integer;
    private LongSerializer long8;
    private NullSerializer nil;
    private StringSerializer string;
    private StructSerializer struct;

    public SerializerHandler() {
        this(8192);
    }

    public SerializerHandler(int i) {
        this.bool = new BooleanSerializer();
        this.integer = new IntSerializer();
        this.long8 = new LongSerializer();
        this.floating = new DoubleSerializer();
        this.base64 = new Base64Serializer();
        this.nil = new NullSerializer();
        this.flags = i;
        this.string = new StringSerializer((i & 4096) == 0, (i & 2048) == 0);
        this.struct = new StructSerializer(this);
        this.array = new ArraySerializer(this);
        this.datetime = new DateTimeSerializer((i & 16384) != 0);
    }

    public Object deserialize(Element element) throws XMLRPCException {
        String nodeName;
        Serializer serializer;
        if (!"value".equals(element.getNodeName())) {
            throw new XMLRPCException("Value tag is missing around value.");
        }
        if (!XMLUtil.hasChildElement(element.getChildNodes())) {
            if ((this.flags & 256) != 0) {
                return this.string.deserialize(element);
            }
            throw new XMLRPCException("Missing type element inside of value element.");
        }
        Element onlyChildElement = XMLUtil.getOnlyChildElement(element.getChildNodes());
        if ((this.flags & 512) != 0) {
            nodeName = onlyChildElement.getLocalName() == null ? onlyChildElement.getNodeName() : onlyChildElement.getLocalName();
        } else {
            nodeName = onlyChildElement.getNodeName();
        }
        if ((this.flags & 8) != 0 && TYPE_NULL.equals(nodeName)) {
            serializer = this.nil;
        } else if ("string".equals(nodeName)) {
            serializer = this.string;
        } else if ("boolean".equals(nodeName)) {
            serializer = this.bool;
        } else if (TYPE_DOUBLE.equals(nodeName)) {
            serializer = this.floating;
        } else if ("int".equals(nodeName) || TYPE_INT2.equals(nodeName)) {
            serializer = this.integer;
        } else if (TYPE_DATETIME.equals(nodeName)) {
            serializer = this.datetime;
        } else if (TYPE_LONG.equals(nodeName)) {
            if ((this.flags & 2) != 0) {
                serializer = this.long8;
            } else {
                throw new XMLRPCException("8 byte integer is not in the specification. You must use FLAGS_8BYTE_INT to enable the i8 tag.");
            }
        } else if (TYPE_STRUCT.equals(nodeName)) {
            serializer = this.struct;
        } else if (TYPE_ARRAY.equals(nodeName)) {
            serializer = this.array;
        } else if (TYPE_BASE64.equals(nodeName)) {
            serializer = this.base64;
        } else {
            throw new XMLRPCException("No deserializer found for type '" + nodeName + "'.");
        }
        return serializer.deserialize(onlyChildElement).toString();
    }

    public Object deserialize2(Element element) throws XMLRPCException {
        String nodeName;
        Serializer serializer;
        if (!"value".equals(element.getNodeName())) {
            throw new XMLRPCException("Value tag is missing around value.");
        }
        if (!XMLUtil.hasChildElement(element.getChildNodes())) {
            if ((this.flags & 256) != 0) {
                return this.string.deserialize(element);
            }
            throw new XMLRPCException("Missing type element inside of value element.");
        }
        Element onlyChildElement = XMLUtil.getOnlyChildElement(element.getChildNodes());
        if ((this.flags & 512) != 0) {
            nodeName = onlyChildElement.getLocalName() == null ? onlyChildElement.getNodeName() : onlyChildElement.getLocalName();
        } else {
            nodeName = onlyChildElement.getNodeName();
        }
        if ((this.flags & 8) != 0 && TYPE_NULL.equals(nodeName)) {
            serializer = this.nil;
        } else if ("string".equals(nodeName)) {
            serializer = this.string;
        } else if ("boolean".equals(nodeName)) {
            serializer = this.bool;
        } else if (TYPE_DOUBLE.equals(nodeName)) {
            serializer = this.floating;
        } else if ("int".equals(nodeName) || TYPE_INT2.equals(nodeName)) {
            serializer = this.integer;
        } else if (TYPE_DATETIME.equals(nodeName)) {
            serializer = this.datetime;
        } else if (TYPE_LONG.equals(nodeName)) {
            if ((this.flags & 2) != 0) {
                serializer = this.long8;
            } else {
                throw new XMLRPCException("8 byte integer is not in the specification. You must use FLAGS_8BYTE_INT to enable the i8 tag.");
            }
        } else if (TYPE_STRUCT.equals(nodeName)) {
            serializer = this.struct;
        } else if (TYPE_ARRAY.equals(nodeName)) {
            serializer = this.array;
        } else if (TYPE_BASE64.equals(nodeName)) {
            serializer = this.base64;
        } else {
            throw new XMLRPCException("No deserializer found for type '" + nodeName + "'.");
        }
        MLog.d("SSS", "SHIFOU 1 :" + serializer.deserialize(onlyChildElement).toString());
        return serializer.deserialize(onlyChildElement).toString();
    }

    public XmlElement serialize(Object obj) throws XMLRPCException {
        Serializer serializer;
        if ((this.flags & 8) != 0 && obj == null) {
            serializer = this.nil;
        } else if (obj instanceof String) {
            serializer = this.string;
        } else if (obj instanceof Boolean) {
            serializer = this.bool;
        } else if ((obj instanceof Double) || (obj instanceof Float) || (obj instanceof BigDecimal)) {
            serializer = this.floating;
        } else if ((obj instanceof Integer) || (obj instanceof Short) || (obj instanceof Byte)) {
            serializer = this.integer;
        } else if (obj instanceof Long) {
            if ((this.flags & 2) != 0) {
                serializer = this.long8;
            } else {
                long longValue = ((Long) obj).longValue();
                if (longValue > 2147483647L || longValue < -2147483648L) {
                    throw new XMLRPCException("FLAGS_8BYTE_INT must be set, if values outside the 4 byte integer range should be transfered.");
                }
                serializer = this.integer;
            }
        } else if (obj instanceof Date) {
            serializer = this.datetime;
        } else if (obj instanceof Calendar) {
            obj = ((Calendar) obj).getTime();
            serializer = this.datetime;
        } else if (obj instanceof Map) {
            serializer = this.struct;
        } else if (obj instanceof byte[]) {
            byte[] bArr = (byte[]) obj;
            int length = bArr.length;
            Byte[] bArr2 = new Byte[length];
            for (int i = 0; i < length; i++) {
                bArr2[i] = new Byte(bArr[i]);
            }
            serializer = this.base64;
            obj = bArr2;
        } else if (obj instanceof Byte[]) {
            serializer = this.base64;
        } else if ((obj instanceof Iterable) || (obj instanceof Object[])) {
            serializer = this.array;
        } else {
            if (obj != null) {
                throw new XMLRPCException("No serializer found for type '" + obj.getClass().getName() + "'.");
            }
            throw new XMLRPCException("No serializer found for type ''.");
        }
        return serializer.serialize(obj);
    }
}
