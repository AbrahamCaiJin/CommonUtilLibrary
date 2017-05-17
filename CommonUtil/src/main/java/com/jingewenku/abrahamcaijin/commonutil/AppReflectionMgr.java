package com.jingewenku.abrahamcaijin.commonutil;

import java.lang.reflect.*;

/**
 * 主要功能:反射工具类
 *
 * @Prject: CommonUtilLibrary
 * @Package: com.jingewenku.abrahamcaijin.commonutil
 * @author: AbrahamCaiJin
 * @date: 2017年05月03日 16:18
 * @Copyright: 个人版权所有
 * @Company:
 * @version: 1.0.0
 */

public class AppReflectionMgr {
        /**
         * 得到某个对象的公共属性
         *
         * @param owner, fieldName
         * @return 该属性对象
         * @throws Exception
         *
         */
        public Object getProperty(Object owner, String fieldName) throws Exception {
            Class ownerClass = owner.getClass();

            Field field = ownerClass.getField(fieldName);

            Object property = field.get(owner);

            return property;
        }

        /**
         * 得到某类的静态公共属性
         *
         * @param className   类名
         * @param fieldName   属性名
         * @return 该属性对象
         * @throws Exception
         */
        public Object getStaticProperty(String className, String fieldName)
            throws Exception {
            Class ownerClass = Class.forName(className);

            Field field = ownerClass.getField(fieldName);

            Object property = field.get(ownerClass);

            return property;
        }


        /**
         * 执行某对象方法
         *
         * @param owner
         *            对象
         * @param methodName
         *            方法名
         * @param args
         *            参数
         * @return 方法返回值
         * @throws Exception
         */
        public Object invokeMethod(Object owner, String methodName, Object[] args)
            throws Exception {

            Class ownerClass = owner.getClass();

            Class[] argsClass = new Class[args.length];

            for (int i = 0, j = args.length; i < j; i++) {
                argsClass[i] = args[i].getClass();
            }

            Method method = ownerClass.getMethod(methodName, argsClass);

            return method.invoke(owner, args);
        }


        /**
         * 执行某类的静态方法
         *
         * @param className
         *            类名
         * @param methodName
         *            方法名
         * @param args
         *            参数数组
         * @return 执行方法返回的结果
         * @throws Exception
         */
        public Object invokeStaticMethod(String className, String methodName,
            Object[] args) throws Exception {
            Class ownerClass = Class.forName(className);

            Class[] argsClass = new Class[args.length];


            for (int i = 0, j = args.length; i < j; i++) {
                argsClass[i] = args[i].getClass();
            }

            Method method = ownerClass.getMethod(methodName, argsClass);

            return method.invoke(null, args);
        }



        /**
         * 新建实例
         * @param className  类名
         * @param args    构造函数的参数
         * 如果无构造参数，args 填写为 null
         * @return 新建的实例
         * @throws Exception
         */
        public Object newInstance(String className, Object[] args,Class[] argsType) throws NoSuchMethodException, SecurityException, ClassNotFoundException,
            InstantiationException, IllegalAccessException,
            IllegalArgumentException, InvocationTargetException
        {
            Class newoneClass = Class.forName(className);

            if(args == null){
                return newoneClass.newInstance();

            }else{
                //             Class[] argsClass = new Class[args.length];
                //
                //             for (int i = 0, j = args.length; i < j; i++) {
                //                 argsClass[i] = args[i].getClass();
                //             }
                //
                //             Constructor cons = newoneClass.getConstructor(argsClass);
                Constructor cons = newoneClass.getConstructor(argsType);

                return cons.newInstance(args);
            }

        }



        /**
         * 是不是某个类的实例
         * @param obj 实例
         * @param cls 类
         * @return 如果 obj 是此类的实例，则返回 true
         */
        public boolean isInstance(Object obj, Class cls) {
            return cls.isInstance(obj);
        }

        /**
         * 得到数组中的某个元素
         * @param array 数组
         * @param index 索引
         * @return 返回指定数组对象中索引组件的值
         */
        public Object getByArray(Object array, int index) {
            return Array.get(array,index);
        }

        public Class<?> GetClassListByPackage(String pPackage) {
            Package _Package = Package.getPackage(pPackage);
            Class<?> _List =   _Package.getClass();

            return _List;
        }
    }
