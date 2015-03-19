package copy;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import utils.FileUtil;

/**
 * 拷贝网络文件工具
 * @author hwj
 */
public class NetworkCopier {

	public static String networkPath = "https://www.baidu.com";
	public static String localPath = "D:/A";
	
	public static void copyNetworkFiles(String path) {
		// 网络地址的分隔符一般都是“/”，需要注意
		loadFile(path);
	}

	private static void loadFile(String networkURL) {
		if (networkURL.indexOf(networkPath) == -1) {
			return;
		}
		try {
			URL url = new URL(networkURL);
			URLConnection conn = url.openConnection();
			InputStream inStream = conn.getInputStream();
			
			String localURL = networkURL.replace(networkPath, localPath);
			if (localURL.lastIndexOf("?") >= 0) {	// 处理URL中带有的版本号
				localURL = localURL.substring(0, localURL.indexOf("?"));
			}
			File localFile = FileUtil.createFile(localURL);
			FileOutputStream outStream = new FileOutputStream(localFile);
			
			// 下载网络文件
			int readbytes = 0;
			byte[] buffer = new byte[1204];
			while ( (readbytes = inStream.read(buffer)) != -1 ) {
				outStream.write(buffer, 0, readbytes);
			}
			outStream.flush();
			outStream.close();
			inStream.close();
			System.out.println("Success loaded. " + networkURL);
		} catch (Exception e) {
			System.out.println("Error loading. " + e.getMessage());
		}
	}
}
