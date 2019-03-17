/**
 * default package
 */
package typ21;

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
			new Brain(args[0]);
		} else {
			new Brain();
		}

	}

}
