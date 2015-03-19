import copy.NetworkCopier;


/**
 * 拷贝网络文件工具
 * @author hwj
 */
public class NetworkCopierDemo {

	private static String networkPath = "https://www.baidu.com";
	private static String localPath = "D:/A";
	
	public static void main(String[] args) {
		NetworkCopier.networkPath = networkPath;
		NetworkCopier.localPath = localPath;
		NetworkCopier.copyNetworkFiles("...");
	}

}
