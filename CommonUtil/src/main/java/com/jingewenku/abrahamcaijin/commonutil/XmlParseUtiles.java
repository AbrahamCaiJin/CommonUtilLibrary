package com.jingewenku.abrahamcaijin.commonutil;

import android.util.Log;
import android.util.Xml;
import org.xmlpull.v1.XmlPullParser;

import java.io.StringReader;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description:主要功能:对pull解析xml进行了封装，不用给每个xml，再创建一个对应的解析类
 * @Prject: CommonUtilLibrary
 * @Package: com.jingewenku.abrahamcaijin.commonutil
 * @author: AbrahamCaiJin
 * @date: 2017年05月24日 18:22
 * @Copyright: 个人版权所有
 * @Company:
 * @version: 1.0.0
 */

public class XmlParseUtiles {

    /**
     * 解析XML
     *
     * @param  XML 字节流
     * @param clazz 字节码      如：Object.class
     * @param startName 开始位置
     * @return 返回List列表
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public static List getXmlList(String XML, Class<?> clazz, String startName) {
        List list = null;
        XmlPullParser parser = Xml.newPullParser();
        Object object = null;
        try {
            StringReader stringReader = new StringReader(XML);
            parser.setInput(stringReader);
            //事件类型
            int eventType = parser.getEventType();

            while (eventType != XmlPullParser.END_DOCUMENT) {
                switch (eventType) {
                    case XmlPullParser.START_DOCUMENT:
                        list = new ArrayList<Object>();
                        break;
                    case XmlPullParser.START_TAG:
                        //获得当前节点元素的名称
                        String name = parser.getName();
                        if (startName.equals(name)) {
                            object = clazz.newInstance();
                            //判断标签里是否有属性，如果有，则全部解析出来
                            int count = parser.getAttributeCount();
                            for (int i = 0; i < count; i++) {
                                setXmlValue(object, parser.getAttributeName(i),
                                    parser.getAttributeValue(i));
                            }
                        }
                        else if (object != null) {
                            setXmlValue(object, name, parser.nextText());
                        }
                        break;
                    case XmlPullParser.END_TAG:
                        if (startName.equals(parser.getName())) {
                            list.add(object);
                            object = null;
                        }
                        break;
                }
                eventType = parser.next();
            }
        } catch (Exception e) {
            Log.e("xml pull error", e.toString());
        }
        return list;
    }


    /**
     * 解析XML
     *
     * @param is xml字节流
     * @param clazz 字节码      如：Object.class
     * @return 返回Object
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public static Object getXmlObject(String is, Class<?> clazz) {
        XmlPullParser parser = Xml.newPullParser();
        Object object = null;
        List list = null;
        Object subObject = null;
        String subName = null;
        try {
            StringReader stringReader = new StringReader(is);
            parser.setInput(stringReader);
            //事件类型
            int eventType = parser.getEventType();

            while (eventType != XmlPullParser.END_DOCUMENT) {
                switch (eventType) {
                    case XmlPullParser.START_DOCUMENT:
                        object = clazz.newInstance();
                        break;
                    case XmlPullParser.START_TAG:
                        //获得当前节点元素的名称
                        String name = parser.getName();

                        Field[] f = null;
                        if (subObject == null) {
                            f = object.getClass().getDeclaredFields();

                            //判断标签里是否有属性，如果有，则全部解析出来
                            int count = parser.getAttributeCount();
                            for (int j = 0; j < count; j++) {
                                setXmlValue(object, parser.getAttributeName(j),
                                    parser.getAttributeValue(j));
                            }
                        }
                        else {
                            f = subObject.getClass().getDeclaredFields();
                        }

                        for (int i = 0; i < f.length; i++) {
                            if (f[i].getName().equalsIgnoreCase(name)) {
                                //判断是不是List类型
                                if (f[i].getType()
                                        .getName()
                                        .equals("java.util.List")) {
                                    Type type = f[i].getGenericType();
                                    if (type instanceof ParameterizedType) {
                                        //获得泛型参数的实际类型
                                        Class<?> subClazz
                                            = (Class<?>) ((ParameterizedType) type)
                                            .getActualTypeArguments()[0];
                                        subObject = subClazz.newInstance();
                                        subName = f[i].getName();

                                        //判断标签里是否有属性，如果有，则全部解析出来
                                        int count = parser.getAttributeCount();
                                        for (int j = 0; j < count; j++) {
                                            setXmlValue(subObject,
                                                parser.getAttributeName(j),
                                                parser.getAttributeValue(
                                                    j));
                                        }

                                        if (list == null) {
                                            list = new ArrayList<Object>();
                                            f[i].setAccessible(true);
                                            f[i].set(object, list);
                                        }
                                    }
                                }
                                else {   //普通属性
                                    if (subObject != null) {
                                        setXmlValue(subObject, name,
                                            parser.nextText());
                                    }
                                    else {
                                        setXmlValue(object, name,
                                            parser.nextText());
                                    }
                                }
                                break;
                            }
                        }
                        break;
                    case XmlPullParser.END_TAG:
                        if (subObject != null &&
                            subName.equalsIgnoreCase(parser.getName())) {
                            list.add(subObject);
                            subObject = null;
                            subName = null;
                        }
                        break;
                }
                eventType = parser.next();
            }
        } catch (Exception e) {
            Log.e("xml pull error", "exception");
            e.printStackTrace();
        }
        return object;
    }


    /**
     * 把xml标签的值，转换成对象里属性的值
     *
     * @param t 对象
     * @param name xml标签名
     * @param value xml标签名对应的值
     */
    private static void setXmlValue(Object t, String name, String value) {
        try {
            Field[] f = t.getClass().getDeclaredFields();
            for (int i = 0; i < f.length; i++) {
                if (f[i].getName().equalsIgnoreCase(name)) {
                    f[i].setAccessible(true);
                    //获得属性类型
                    Class<?> fieldType = f[i].getType();

                    if (fieldType == String.class) {
                        f[i].set(t, value);
                    }
                    else if (fieldType == Integer.TYPE) {
                        f[i].set(t, Integer.parseInt(value));
                    }
                    else if (fieldType == Float.TYPE) {
                        f[i].set(t, Float.parseFloat(value));
                    }
                    else if (fieldType == Double.TYPE) {
                        f[i].set(t, Double.parseDouble(value));
                    }
                    else if (fieldType == Long.TYPE) {
                        f[i].set(t, Long.parseLong(value));
                    }
                    else if (fieldType == Short.TYPE) {
                        f[i].set(t, Short.parseShort(value));
                    }
                    else if (fieldType == Boolean.TYPE) {
                        f[i].set(t, Boolean.parseBoolean(value));
                    }
                    else {
                        f[i].set(t, value);
                    }
                }
            }
        } catch (Exception e) {
            Log.e("xml error", e.toString());
        }
    }
}
