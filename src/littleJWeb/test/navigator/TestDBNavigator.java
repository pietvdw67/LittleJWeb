package littleJWeb.test.navigator;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import infinity.database.DataAdapter;
import infinity.database.MySQLDataAdapter;
import littleJWeb.navigator.Navigator;
@SuppressWarnings("serial")
public class TestDBNavigator  extends Navigator {
	@Override
	public boolean performTask(HttpServletRequest req, HttpServletResponse resp) {
		String connectionMessage = "";	
		
		storeObject("dbURL",getSettingsProperties().getDbUrl());
		storeObject("dbUser",getSettingsProperties().getDbUsername());
		storeObject("dbPassword",getSettingsProperties().getDbPassword());
		
		Connection conn = null;
		try {
			DataAdapter adapter = new MySQLDataAdapter();
			conn = adapter.getConnection(getSettingsProperties().getDbUrl(), getSettingsProperties().getDbUsername(),
					getSettingsProperties().getDbPassword());
		} catch (SQLException eSQL) {
			connectionMessage = "Connection failed:";
			connectionMessage += eSQL.getMessage();	
			connectionMessage += "<br>" + stackTraceToString(eSQL);			
		}
		
		if (connectionMessage.length() == 0){
			connectionMessage = "Connection successful";
		}
		
		try {
			conn.close();
		} catch (Exception e){
			// do nothing
		} finally {
			conn = null;
		}

		storeObject("connectionMessage",connectionMessage);
		
		return true;
		
	}
	
	private String stackTraceToString(Throwable t){
		StringWriter sw = new StringWriter();
		t.printStackTrace(new PrintWriter(sw));
		return sw.toString();
	}
	
			

}
