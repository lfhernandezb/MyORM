import org.apache.commons.lang3.text.WordUtils;

public class Util {

	public static String toJavaFieldName(String name) { // "MY_COLUMN"
	    String name0 = name.replace("_", " "); // to "MY COLUMN"
	    name0 = WordUtils.capitalizeFully(name0); // to "My Column"
	    name0 = name0.replace(" ", ""); // to "MyColumn"
	    name0 = WordUtils.uncapitalize(name0); // to "myColumn"
	    return name0;
	}
}
