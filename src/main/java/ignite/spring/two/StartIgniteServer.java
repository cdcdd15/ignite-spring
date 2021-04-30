package ignite.spring.two;

import org.apache.ignite.IgniteException;
import org.apache.ignite.Ignition;

public class StartIgniteServer {
	public static void main(String[] args) throws IgniteException {
		Ignition.start("C:\\javaDev\\apache-ignite\\examples\\config\\example-ignite.xml");
		
	}
}
