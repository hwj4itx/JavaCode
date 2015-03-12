package utils;

import java.io.File;
import java.io.IOException;

/**
 * 文件工具类
 * @author hwj
 */
public class FileUtil {
	
	/**
	 * 生成一个文件
	 * @param path
	 * @return
	 */
	public static File createFile(String path) throws IOException {
		File file = new File(path);
		if (file.exists()) {
			return file;
		}
		// 先生成目录，不然创建文件失败
		if (file.getParentFile().exists() == false) {
			boolean succ = file.getParentFile().mkdirs();
			if (!succ) {
				return null;
			}
		}
		file.createNewFile();
		return file;
	}
}
