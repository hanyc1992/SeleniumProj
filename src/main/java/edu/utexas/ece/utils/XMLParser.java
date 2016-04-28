package edu.utexas.ece.utils;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by hanyc on 4/26/2016.
 */
public class XMLParser {

    public static XMLParser xmlParser = null;

    private XMLParser() {
    }

    /**
     * @return
     */
    public static XMLParser getInstance() {
        if (xmlParser == null)
            return new XMLParser();
        return xmlParser;
    }

    /**
     * @param xmlFile
     * @return
     */
    public Map<String, Object> parserXml(String xmlFile) {
        XMLParserHandler xmlPasHandler = null;
        SAXParserFactory saxfac = SAXParserFactory.newInstance();
        try {
            SAXParser saxparser = saxfac.newSAXParser();
            System.out.println("The path of file is " + xmlFile);
            InputStream is = getClass().getResourceAsStream(xmlFile);
            if (is == null) {

                is = new FileInputStream((new File(getClass().getResource("/").getFile()))
                        + xmlFile);
            }

            xmlPasHandler = new XMLParserHandler();
            saxparser.parse(is, xmlPasHandler);
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return (xmlPasHandler == null ? new HashMap<String, Object>()
                : xmlPasHandler.getElements());
    }

    /**
     * @param xmlPath
     * @return
     */
    public Map<String, Object> parserXmlByPath(String xmlPath) {
        return parserXml(xmlPath);
    }

    public Map<String, String> getMap(String xmlFile) {
        final Map<String, String> result = new HashMap<String, String>();
        Map<String, Object> temp = parserXml(xmlFile);
        Iterator it = temp.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            result.put((String) pair.getKey(), ((String)pair.getValue()).replace(')',']'));
        }
        return result;
    }
}

