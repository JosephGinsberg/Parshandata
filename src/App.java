import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileOutputStream;

public class App {
	public static void main(String[] args) throws FileNotFoundException, IOException{
		String data = Bible.maker.torah();

		FileOutputStream out = new FileOutputStream("output.txt");
		out.write(data.getBytes("UTF-8"));
		out.close();
	}
}