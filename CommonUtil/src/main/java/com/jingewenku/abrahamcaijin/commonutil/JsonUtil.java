package com.jingewenku.abrahamcaijin.commonutil;

/**
 * @Description:主要功能:
 * @Prject: CommonUtilLibrary
 * @Package: com.jingewenku.abrahamcaijin.commonutil
 * @author: AbrahamCaiJin
 * @date: 2017年12月20日 16:27
 * @Copyright: 个人版权所有
 * @Company:
 * @version: 1.0.0
 */

public class JsonUtil {
//    public static String stringToJson(String s) {
//        if (s == null) {
//            return nullToJson();
//        }
//        StringBuilder sb = new StringBuilder();
//        for (int i = 0; i < s.length(); i++) {
//            char ch = s.charAt(i);
//            switch (ch) {
//                case '"':
//                    sb.append("\\\"");
//                    break;
//                case '\\':
//                    sb.append("\\\\");
//                    break;
//                case '\b':
//                    sb.append("\\b");
//                    break;
//                case '\f':
//                    sb.append("\\f");
//                    break;
//                case '\n':
//                    sb.append("\\n");
//                    break;
//                case '\r':
//                    sb.append("\\r");
//                    break;
//                case '\t':
//                    sb.append("\\t");
//                    break;
//                case '/':
//                    sb.append("\\/");
//                    break;
//                default:
//                    if (ch >= '\u0000' && ch <= '\u001F') {
//                        String ss = Integer.toHexString(ch);
//                        sb.append("\\u");
//                        for (int k = 0; k < 4 - ss.length(); k++) {
//                            sb.append('0');
//                        }
//                        sb.append(ss.toUpperCase());
//                    } else {
//                        sb.append(ch);
//                    }
//            }
//        }
//        return sb.toString();
//    }
//
//    public static String nullToJson() {
//        return "";
//    }
//
//    public static String objectToJson(Object obj) {
//        StringBuilder json = new StringBuilder();
//        if (obj == null) {
//            json.append("\"\"");
//        } else if (obj instanceof Number) {
//            json.append(numberToJson((Number) obj));
//        } else if (obj instanceof Boolean) {
//            json.append(booleanToJson((Boolean) obj));
//        } else if (obj instanceof String) {
//            json.append("\"").append(stringToJson(obj.toString())).append("\"");
//        } else if (obj instanceof Object[]) {
//            json.append(arrayToJson((Object[]) obj));
//        } else if (obj instanceof List) {
//            json.append(listToJson((List<?>) obj));
//        } else if (obj instanceof Map) {
//            json.append(mapToJson((Map<?, ?>) obj));
//        } else if (obj instanceof Set) {
//            json.append(setToJson((Set<?>) obj));
//        } else {
//            json.append(beanToJson(obj));
//        }
//        return json.toString();
//    }
//
//    public static String numberToJson(Number number) {
//        return number.toString();
//    }
//
//    public static String booleanToJson(Boolean bool) {
//        return bool.toString();
//    }
//
//    /**
//     * @param bean
//     *            bean对象
//     * @return String
//     */
//    public static String beanToJson(Object bean) {
//        StringBuilder json = new StringBuilder();
//        json.append("{");
//        PropertyDescriptor[] props = null;
//        try {
//            props = Introspector.getBeanInfo(bean.getClass(), Object.class)
//                                .getPropertyDescriptors();
//        } catch (IntrospectionException e) {
//        }
//        if (props != null) {
//            for (int i = 0; i < props.length; i++) {
//                try {
//                    String name = objectToJson(props[i].getName());
//                    String value = objectToJson(props[i].getReadMethod()
//                                                        .invoke(bean));
//                    json.append(name);
//                    json.append(":");
//                    json.append(value);
//                    json.append(",");
//                } catch (Exception e) {
//                }
//            }
//            json.setCharAt(json.length() - 1, '}');
//        } else {
//            json.append("}");
//        }
//        return json.toString();
//    }
//
//    /**
//     * @param list
//     *            list对象
//     * @return String
//     */
//    public static String listToJson(List<?> list) {
//        StringBuilder json = new StringBuilder();
//        json.append("[");
//        if (list != null && list.size() > 0) {
//            for (Object obj : list) {
//                json.append(objectToJson(obj));
//                json.append(",");
//            }
//            json.setCharAt(json.length() - 1, ']');
//        } else {
//            json.append("]");
//        }
//        return json.toString();
//    }
//
//    /**
//     * @param array
//     *            对象数组
//     * @return String
//     */
//    public static String arrayToJson(Object[] array) {
//        StringBuilder json = new StringBuilder();
//        json.append("[");
//        if (array != null && array.length > 0) {
//            for (Object obj : array) {
//                json.append(objectToJson(obj));
//                json.append(",");
//            }
//            json.setCharAt(json.length() - 1, ']');
//        } else {
//            json.append("]");
//        }
//        return json.toString();
//    }
//
//    /**
//     * @param map
//     *            map对象
//     * @return String
//     */
//    public static String mapToJson(Map<?, ?> map) {
//        StringBuilder json = new StringBuilder();
//        json.append("{");
//        if (map != null && map.size() > 0) {
//            for (Object key : map.keySet()) {
//                json.append(objectToJson(key));
//                json.append(":");
//                json.append(objectToJson(map.get(key)));
//                json.append(",");
//            }
//            json.setCharAt(json.length() - 1, '}');
//        } else {
//            json.append("}");
//        }
//        return json.toString();
//    }
//
//    /**
//     * @param set
//     *            集合对象
//     * @return String
//     */
//    public static String setToJson(Set<?> set) {
//        StringBuilder json = new StringBuilder();
//        json.append("[");
//        if (set != null && set.size() > 0) {
//            for (Object obj : set) {
//                json.append(objectToJson(obj));
//                json.append(",");
//            }
//            json.setCharAt(json.length() - 1, ']');
//        } else {
//            json.append("]");
//        }
//        return json.toString();
//    }

}