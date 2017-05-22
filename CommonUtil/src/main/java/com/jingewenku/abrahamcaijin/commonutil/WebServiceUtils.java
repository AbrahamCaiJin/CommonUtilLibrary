package com.jingewenku.abrahamcaijin.commonutil;

import android.os.Handler;
import android.os.Message;
import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpResponseException;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Description:主要功能:WebService网络工具类
 * @Prject: CommonUtilLibrary
 * @Package: com.jingewenku.abrahamcaijin.commonutil
 * @author: AbrahamCaiJin
 * @date: 2017年05月22日 11:47
 * @Copyright: 个人版权所有
 * @Company:
 * @version: 1.0.0
 */

public class WebServiceUtils {
    public static final String WEB_SERVER_URL = "";
    // 含有3个线程的线程池
    private static final ExecutorService executorService = Executors
        .newFixedThreadPool(3);
    // 命名空间
    private static final String NAMESPACE = "";


    /**
     *请求网络数据
     * @param url
     *            WebService服务器地址
     * @param methodName
     *            WebService的调用方法名
     * @param properties
     *            WebService的参数
     * @param webServiceCallBack
     *            回调接口
     */
    public static void callWebService(String url, final String methodName,
        HashMap<String, String> properties,
        final WebServiceCallBack webServiceCallBack) {
        // 创建HttpTransportSE对象，传递WebService服务器地址
        final HttpTransportSE httpTransportSE = new HttpTransportSE(url);
        // 创建SoapObject对象
        SoapObject soapObject = new SoapObject(NAMESPACE, methodName);

        // SoapObject添加参数

        //写真实的变量名也没有用，传递参数必须是arg开头，必须是arg0 ,arg1 一直拍下去

        if (properties != null) {
            for (Iterator<Entry<String, String>> it = properties.entrySet()
                                                                .iterator(); it.hasNext();) {
                Map.Entry<String, String> entry = it.next();
                soapObject.addProperty(entry.getKey(), entry.getValue());
            }
        }
        // 实例化SoapSerializationEnvelope，传入WebService的SOAP协议的版本号

        //tomcat 7.055 for 64  jdk 1.6 for 64  web3.0 这里写 ver12 ，如果是ver11会报 http 415错误
        final SoapSerializationEnvelope soapEnvelope = new SoapSerializationEnvelope(
            SoapEnvelope.VER12);
        // 设置是否调用的是.Net开发的WebService
        soapEnvelope.bodyOut = soapObject;
        soapEnvelope.dotNet = false;//如果调用的是.Net的WebService，这里为true，否则为false，不然调用会不成功
        soapEnvelope.setOutputSoapObject(soapObject);
        httpTransportSE.debug = true;
        // 用于子线程与主线程通信的Handler
        final Handler mHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                // 将返回值回调到callBack的参数中
                webServiceCallBack.callBack((Object) msg.obj);
            }
        };

        // 开启线程去访问WebService
        executorService.submit(new Runnable() {
            @Override
            public void run() {
                Object resultSoapObject = null;
                try {
                    httpTransportSE.call(WEB_SERVER_URL + methodName, soapEnvelope);
                    if (soapEnvelope.getResponse() != null) {
                        // 获取服务器响应返回的SoapObject
                        resultSoapObject = (Object) soapEnvelope.bodyOut;
                    }
                } catch (HttpResponseException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (XmlPullParserException e) {
                    e.printStackTrace();
                } finally {
                    // 将获取的消息利用Handler发送到主线程
                    mHandler.sendMessage(mHandler.obtainMessage(0,
                        resultSoapObject));
                }
            }
        });
    }

    public interface WebServiceCallBack {
        public void callBack(Object result);
    }
}
