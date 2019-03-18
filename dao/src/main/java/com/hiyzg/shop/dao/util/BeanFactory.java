package com.hiyzg.shop.dao.util;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Sam on 2019/3/18.
 */
public class BeanFactory {
    public static Map<String, Object> DAO_BEANS = new HashMap<>();

    static {
        SAXReader saxReader = new SAXReader();
        try {
            Document document = saxReader.read(BeanFactory.class.getResource("/dao-beans.xml"));
            List<Element> beanXmls = document.selectNodes("//bean[@id][@class]");
            for (Element element :
                    beanXmls) {
                String id = element.attributeValue("id");
                String clazz = element.attributeValue("class");
                DAO_BEANS.put(id, Class.forName(clazz).newInstance());
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }
}
