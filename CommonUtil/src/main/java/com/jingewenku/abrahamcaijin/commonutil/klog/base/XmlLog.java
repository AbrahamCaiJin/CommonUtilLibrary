package com.jingewenku.abrahamcaijin.commonutil.klog.base;

/**
 * @Description:主要功能:
 * @Prject: CommonUtilLibrary
 * @Package: com.jingewenku.abrahamcaijin.commonutil.klog.base
 * @author: AbrahamCaiJin
 * @date: 2017年05月16日 16:56
 * @Copyright: 个人版权所有
 * @Company:
 * @version: 1.0.0
 */

import android.util.Log;
import com.jingewenku.abrahamcaijin.commonutil.klog.KLogUtil;
import com.socks.library.KLog;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.StringReader;
import java.io.StringWriter;

/**
 * Created by zhaokaiqiang on 15/11/18.
 */
public class XmlLog {

    public static void printXml(String tag, String xml, String headString) {

        if (xml != null) {
            xml = XmlLog.formatXML(xml);
            xml = headString + "\n" + xml;
        } else {
            xml = headString + KLog.NULL_TIPS;
        }

        KLogUtil.printLine(tag, true);
        String[] lines = xml.split(KLog.LINE_SEPARATOR);
        for (String line : lines) {
            if (!KLogUtil.isEmpty(line)) {
                Log.d(tag, "║ " + line);
            }
        }
        KLogUtil.printLine(tag, false);
    }

    private static String formatXML(String inputXML) {
        try {
            Source xmlInput = new StreamSource(new StringReader(inputXML));
            StreamResult xmlOutput = new StreamResult(new StringWriter());
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
            transformer.transform(xmlInput, xmlOutput);
            return xmlOutput.getWriter().toString().replaceFirst(">", ">\n");
        } catch (Exception e) {
            e.printStackTrace();
            return inputXML;
        }
    }

}