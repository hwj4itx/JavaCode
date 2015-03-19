package copy;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.regex.Matcher;

import utils.FileUtil;

/**
 * 拷贝目录资源小工具
 * @author hwj
 */
public class FileCopier {

	private static String sourceFolder = "D:/A";
	private static String targetFolder = "E:/B";
	
	public static void copyFiles(String srcFolder, String tarFolder) {
		sourceFolder = srcFolder;
		targetFolder = tarFolder;
		
		// 方法1、Java中的文件分割符号是\，所以有必要做一下转换
		/*
		sourceFolder = sourceFolder.replaceAll("/", "\\\\");
		targetFolder = targetFolder.replaceAll("/", "\\\\");
		 */
		// 方法2、使用Java提供的API
		sourceFolder = sourceFolder.replaceAll("/", Matcher.quoteReplacement(File.separator));
		targetFolder = targetFolder.replaceAll("/", Matcher.quoteReplacement(File.separator));
		System.out.println(sourceFolder + "---" + targetFolder);
		
		File source = new File(sourceFolder);
		if (source.exists() == false) {
			System.out.println("Source must be exist.");
			return;
		}
		copyFile(source);
	}
	
	private static void copyFile(File sourceFile) {
		if (sourceFile.isDirectory()) {
			File[] subFileList = sourceFile.listFiles();
			for (File subFile : subFileList) {
				copyFile(subFile);
			}
		} else {
			String targetPath = sourceFile.getPath().replace(sourceFolder, targetFolder);
			try {
				File targetFile = FileUtil.createFile(targetPath);
				FileInputStream in = new FileInputStream(sourceFile);
				FileOutputStream out = new FileOutputStream(targetFile);
				byte[] buffer = new byte[1024];
				int readbytes = 0;
				while ( (readbytes = in.read(buffer)) != -1 ) {
					out.write(buffer, 0, readbytes);
				}
				out.flush();
				out.close();
				in.close();
			} catch (Exception e) {
				System.out.println("Error. " + e.getMessage() + " " + targetPath);
			}
		}
	}

}
