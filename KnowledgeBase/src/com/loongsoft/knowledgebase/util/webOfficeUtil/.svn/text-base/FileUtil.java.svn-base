package com.loongsoft.knowledgebase.util.webOfficeUtil;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class FileUtil {

	/**
	 * 将输入流中的内容 flush 到输出流
	 * 
	 * @param is
	 *            输入流
	 * @param os
	 *            输出流
	 * @throws IOException
	 */
	public static void flushInputToOutput(InputStream is, OutputStream os)
			throws IOException {
		byte[] buffer = new byte[1024];
		int len = -1;

		while ((len = is.read(buffer)) != -1) {
			os.write(buffer, 0, len);
		}

		os.flush();
	}

	/**
	 * 读取 *.txt 文件， 并返回其内容
	 * 
	 * @param file
	 *            要读取的目标文件
	 * @return 文件内容
	 */
	public static String readTxtFile(File file) {
		BufferedReader br = null;
		StringBuffer sb = new StringBuffer();
		try {
			br = new BufferedReader(new FileReader(file));

			String line = null;

			while ((line = br.readLine()) != null) {
				sb.append(line);
				sb.append('\n');
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (null != br) {
					br.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return sb.toString();
	}
}