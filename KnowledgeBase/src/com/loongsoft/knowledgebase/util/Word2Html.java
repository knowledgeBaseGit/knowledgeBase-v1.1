package com.loongsoft.knowledgebase.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.poi.hwpf.HWPFDocumentCore;
import org.apache.poi.hwpf.converter.AbstractWordUtils;
import org.apache.poi.hwpf.converter.WordToHtmlConverter;
import org.apache.poi.hwpf.converter.WordToHtmlUtils;
import org.w3c.dom.Document;

public class Word2Html {
//	public static void main(String[] args) throws Exception {
//		String result = new Word2Html().word2html(new File("/home/allbutone/Downloads/win7.doc"));
//		System.out.println(result);
//	}
	
//	public static void main(String[] args) throws Exception{
	public String word2html(File file) throws Exception{
//	   HWPFDocumentCore wordDocument = WordToHtmlUtils.loadDoc(new FileInputStream("/home/allbutone/Downloads/win7.doc"));
	   HWPFDocumentCore wordDocument = AbstractWordUtils.loadDoc(new FileInputStream(file));

	    WordToHtmlConverter wordToHtmlConverter = new WordToHtmlConverter(
	            DocumentBuilderFactory.newInstance().newDocumentBuilder()
	                    .newDocument());
	    wordToHtmlConverter.processDocument(wordDocument);
	    Document htmlDocument = wordToHtmlConverter.getDocument();
	    ByteArrayOutputStream out = new ByteArrayOutputStream();
	    DOMSource domSource = new DOMSource(htmlDocument);
	    StreamResult streamResult = new StreamResult(out);

	    TransformerFactory tf = TransformerFactory.newInstance();
	    Transformer serializer = tf.newTransformer();
	    serializer.setOutputProperty(OutputKeys.ENCODING, "GBK");
	    serializer.setOutputProperty(OutputKeys.INDENT, "yes");
	    serializer.setOutputProperty(OutputKeys.METHOD, "html");
	    serializer.transform(domSource, streamResult);
	    out.close();

	    String result = new String(out.toByteArray());
	    
    	int headBegin = result.indexOf("<head>") + 6;
    	int headEnd = result.indexOf("</head>");
    	String headContent = result.substring(headBegin, headEnd);//content inside <head></head>
    	
    	int bodyBegin = headEnd + 7;
    	int bodyEnd = result.indexOf("</body>") + 7;
    	String body = result.substring(bodyBegin, bodyEnd);//body
    	
    	//construct new <head>
    	String headMid = "<style data-cke-temp=\"1\"><link href=\"ckeditor/contents.css\" rel=\"stylesheet\" type=\"text/css\"><style data-cke-temp=\"1\">";
    	String head = "<head>" + headMid + headContent + "</head>";
    	
//    	System.out.println(head);
    	
    	
//		construct new <body>
    	String newBodyBegin = "<body class=\"cke_editable cke_editable_themed cke_contents_ltr\" contenteditable=\"true\" spellcheck=\"false\">";
    	body = body.replaceFirst("<body.*>", newBodyBegin);
    	
//    	System.out.println(body);
	    
    	String html = "<html>" + head + body + "</html>";
//    	String html = head + body;
    	
//    	System.out.println(html);
    	
	    return html;
	}
}
