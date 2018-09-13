package com.xter.support.util;

import android.os.Environment;
import android.text.TextUtils;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

/**
 * Created by XTER on 2017/11/2.
 * 文件工具
 */
public class FileUtil {
	/**
	 * 删除文件或目录
	 *
	 * @param path 文件或目录。
	 * @return true 表示删除成功，否则为失败
	 */
	synchronized public static boolean delete(File path) {
		if (null == path) {
			return true;
		}

		if (path.isDirectory()) {
			File[] files = path.listFiles();
			if (null != files) {
				for (File file : files) {
					if (!delete(file)) {
						return false;
					}
				}
			}
		}
		return !path.exists() || path.delete();
	}

	/**
	 * 创建文件或目录
	 *
	 * @param path 路径
	 * @return file
	 * @throws IOException IO异常
	 */
	public synchronized static File createFile(String path) throws IOException {
		if (TextUtils.isEmpty(path)) {
			return null;
		}
		File file = new File(path);
		if (!file.exists())
			if (path.endsWith("/")) {
				file.mkdirs();
			} else {
				if (file.getParentFile().exists()) {
					file.createNewFile();
				} else {
					file.getParentFile().mkdirs();
					file.createNewFile();
				}
			}
		return file;
	}

	/**
	 * 存储数据至文件
	 *
	 * @param bytes 数据
	 * @param path  文件路径
	 */
	public synchronized static void storeFile(byte[] bytes, String path) {
		FileOutputStream fos = null;
		BufferedOutputStream bos = null;
		try {
			createFile(path);
			fos = new FileOutputStream(path);
			bos = new BufferedOutputStream(fos);
			bos.write(bytes);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (fos != null && bos != null)
				try {
					fos.close();
					bos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
	}

	/**
	 * 存储数据至文件
	 *
	 * @param bytes    数据
	 * @param path     路径
	 * @param listener 存储成功监听
	 */
	public synchronized static void storeFile(byte[] bytes, String path, OnStoreFileListener listener) {
		FileOutputStream fos = null;
		BufferedOutputStream bos = null;
		File file = null;
		try {
			file = createFile(path);
			fos = new FileOutputStream(path);
			bos = new BufferedOutputStream(fos);
			bos.write(bytes);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (fos != null && bos != null)
				try {
					fos.close();
					bos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			listener.onStoredComplete(file == null ? "" : file.getAbsolutePath());
		}
	}

	/**
	 * 存储成功的监听
	 */
	public interface OnStoreFileListener {
		void onStoredComplete(String absolutePath);
	}

	/**
	 * 存储数据至文件
	 *
	 * @param inputStream 数据流
	 * @param path        文件路径
	 * @param listener    存储成功的监听
	 */
	public synchronized static void storeFile(InputStream inputStream, String path, OnStoreFileListener listener) {
		FileOutputStream fileOutputStream = null;
		File dstFile = new File(path);
		try {
			fileOutputStream = new FileOutputStream(dstFile);
			byte[] buffer = new byte[2048];
			int len = 0;
			while ((len = inputStream.read(buffer)) != -1) {
				fileOutputStream.write(buffer, 0, len);
			}
			fileOutputStream.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				inputStream.close();
				fileOutputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			listener.onStoredComplete(dstFile.getAbsolutePath());
		}
	}

	/**
	 * 向文件中写入内容
	 *
	 * @param filepath 文件路径与名称
	 * @param newstr   写入的内容
	 */
	public static void writeFileContent(String filepath, String newstr) throws IOException {
		PrintWriter pw = null;
		try {
			FileWriter fw = new FileWriter(filepath, true);
			pw = new PrintWriter(fw);
			pw.println(newstr);
			pw.flush();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			createFile(filepath);
		} finally {
			if (pw != null) {
				pw.close();
			}
		}
	}

	/**
	 * 写入日志文件
	 *
	 * @param newstr 日志信息
	 * @throws IOException IO异常
	 */
	public static void writeLogFile(String newstr) throws IOException {
		String logFileName = Environment.getExternalStorageDirectory() + "/logs/note_log_" + SysUtil.getDate() + ".txt";
//		File logFile = new File(logFileName);
//		if(logFile.exists()&&logFile.length()>200*1024*1024){
//			logFileName =
//		}
		PrintWriter pw = null;
		try {
			FileWriter fw = new FileWriter(logFileName, true);
			pw = new PrintWriter(fw);
			pw.println(newstr);
			pw.flush();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			createFile(logFileName);
		} finally {
			if (pw != null) {
				pw.close();
			}
		}
	}

	/**
	 * 向文件写入字符
	 *
	 * @param content  内容
	 * @param filePath 文件路径
	 */
	public static void writeToFile(String content, String filePath) {
		FileWriter fileWriter = null;
		try {
			fileWriter = new FileWriter(filePath, true);
			fileWriter.write(content);
			fileWriter.flush();
		} catch (Throwable t) {
			t.printStackTrace();
		} finally {
			if (fileWriter != null) {
				try {
					fileWriter.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
}
