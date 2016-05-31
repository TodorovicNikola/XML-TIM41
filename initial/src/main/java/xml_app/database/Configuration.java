package xml_app.database;

import com.marklogic.client.DatabaseClientFactory.Authentication;

public class Configuration {

	static public class ConnectionProperties {


		public String host = "147.91.177.194";

		public int port = 8000;

		public String user = "tim41";

		public String password = "tim41";

		public String database = "Tim41";

		public Authentication authType = Authentication.DIGEST;

		public ConnectionProperties() {
			super();
		}
	}

	/**
	 * @return the configuration object
	 */
	public static ConnectionProperties loadProperties(){

		return new ConnectionProperties();
	}

}
