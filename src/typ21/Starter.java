/**
 * default package
 */
package typ21;

import java.io.File;

/**
 * @author voruti
 *
 */
public class Starter {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		if (args.length > 0) {
			String workingDirectory = System.getProperty("user.dir");

			File file = new File(workingDirectory, args[0]);
			System.out.println(file.getAbsolutePath());

		}
	}

}
