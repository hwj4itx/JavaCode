import copy.FileCopier;


/**
 * 拷贝目录资源小工具
 * @author hwj
 */
public class FileCopierDemo {

	private static String sourceFolder = "D:/A";
	private static String targetFolder = "E:/B";
	
	public static void main(String[] args) {
		FileCopier.copyFiles(sourceFolder, targetFolder);
	}

}
