package util;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class ReadProperties {

	private Properties properties = new Properties();
	private File file;
	private int count = 0;
	
	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		/*ReadProperties readProperties = new ReadProperties();
		readProperties.setFile(new File("adminLogin/AdminLogin_Data.properties"));
		readProperties.readFile();
		System.out.println(readProperties.get("url"));*/
	}
	
	public void readFile() throws Exception{
		BufferedInputStream bis = new BufferedInputStream(new FileInputStream(this.file));
		this.properties.load(bis);
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public Properties getProperties() {
		return properties;
	}

	public void setProperties(Properties properties) {
		this.properties = properties;
	}

	public String get(String property){
		if(count > 0){
			return this.properties.getProperty(count + property);
		}else{
			return this.properties.getProperty(property);
		}
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getCount() {
		return count;
	}
}
