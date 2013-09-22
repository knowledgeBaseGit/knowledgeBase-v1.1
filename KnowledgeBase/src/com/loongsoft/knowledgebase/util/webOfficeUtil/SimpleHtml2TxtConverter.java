package com.loongsoft.knowledgebase.util.webOfficeUtil;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 在格式不变的前提下，实现CKEDITOR 编辑区内的 html 和文本之间的相互转换
 * @author suoyanming 
 * @date 2013-9-17
 */
public class SimpleHtml2TxtConverter {
	/**将CKEDITOR 编辑区内的 html 转换为同等格式的文本
	 * @param html CKEDITOR 编辑区内的 html
	 * @return 同等格式的文本
	 */
	public static String html2txt(String html) {
	/* 样本数据
		<p style="margin-left:40px">adadasdasdasdas hello</p>
		
		<p>&nbsp; asdasdsadas</p>
		
		<p>asdasdasddsadsadasdas好世界</p>
	 */
		//提取每一行： <p[^/]*>.*</p>
	      String pattern = "<p[^/]*>.*</p>";
	      Pattern r = Pattern.compile(pattern);
	      Matcher m = r.matcher(html);
	      
	      StringBuilder sb = new StringBuilder();
	      while(m.find()){
	    	  String line = m.group();
	    	  sb.append(transLine(line));
	      }
	      
		return sb.toString();
	}

	/**将CKEDITOR 编辑区内的 html 的某一行转换为同等格式的文本
	 * 		转换每行内的缩进和空格,删除  <p[^/]*> 和 </p>, 为每行添加换行符
	 * @param line CKEDITOR 编辑区内的 html 中的某一行
	 * @return 同等格式的文本
	 */
	public static String transLine(String line) {
		String indentRegex = "[0-9]+";
		Pattern indentPtn = Pattern.compile(indentRegex);
		Matcher indent_m = indentPtn.matcher(line);
		
		int tabCnt = 0;
		if(indent_m.find()){
			tabCnt = Integer.parseInt(indent_m.group())/40;
		}
		
		StringBuilder tabs = new StringBuilder(); 
		for(int i = 0; i < tabCnt; i++){
			tabs.append("\t");
		}
		
		return line.replaceAll("<p[^/]*>", tabs.toString()).replaceAll("&nbsp;", " ").replaceAll("</p>", "\r\n");
	}
	/**将文本文件的内容转换为 CKEDITOR 编辑区内的 html
	 * @param file 文本文件
	 * @return CKEDITOR 编辑区内的 html
	 */
	public static String txt2html(File file) {
		StringBuilder sb_html = new StringBuilder();
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(file));
			String line;
			while ((line = br.readLine()) != null) {
				//tab 需要转换为 html 中的 style 属性，为了避免麻烦，先把 tab 转换为 4 个 空格
				String p = ("<p>" + line +"</p>").replaceAll("\t", "&nbsp;&nbsp;&nbsp;&nbsp;");
				sb_html.append(p);
			}
		} catch (IOException e) {
			
		} finally{
			try {
				if (null != br) {
					br.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
//		System.out.println(sb_html.toString());
		return sb_html.toString();
	}
}
