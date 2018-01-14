package littleJWeb.navigator;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import infinity.database.DataAdapter;
import infinity.database.MySQLDataAdapter;
import littleJ.dto.SettingsDTO;
import littleJWeb.DTO.LittleJWebHandlerDTO;

@SuppressWarnings("serial")
public abstract class Navigator extends HttpServlet {
	private SettingsDTO settingsDTO = null;
	private LittleJWebHandlerDTO littleJWebHandlerDTO = null;
	private HttpServletRequest req;
	private String alternativeNav = "";
	private Map<String, String> alternativeNavParms = null;
	private Exception errorException = null;
	private String errorMessage = "";

	public void setRequestAndResponse(HttpServletRequest req, HttpServletResponse resp) {
		this.req = req;
	}

	public void startup() {

	}
	
	/**
	 * Retrieves a value from the reqeust, if the parameter is not present, look in the attributes section
	 * @param req
	 * @param parameter
	 * @return
	 */
	public String getParm(HttpServletRequest req,String parameter){
		String returnValue = req.getParameter(parameter);
		if (returnValue == null || returnValue.length() == 0){
			returnValue = (String)req.getAttribute(parameter);
		}
		
		if (returnValue == null){
			return "";
		} else {
			return returnValue;
		}
		
	}
	
	public void setMainPopulatingNavigator(String fullPath){
		littleJWebHandlerDTO.getPopulatorNavs().clear();
		littleJWebHandlerDTO.getPopulatorNavs().add(fullPath);		
	}
	
	public void addPopulatingNavigator(String fullPath){
		if (littleJWebHandlerDTO.getPopulatorNavs().size()>0){
			String lastValue =littleJWebHandlerDTO.getPopulatorNavs().get(littleJWebHandlerDTO.getPopulatorNavs().size()-1);
			if (fullPath.equalsIgnoreCase(lastValue)){
				return;
			}
		}
		
		littleJWebHandlerDTO.getPopulatorNavs().add(fullPath);		
	}

	public String getRequestVariable(HttpServletRequest req, String variableName) {
		String returnVar = req.getParameter(variableName);
		if (returnVar == null) {
			returnVar = req.getAttribute(variableName).toString();
		}

		if (returnVar == null) {
			returnVar = "";
		}

		return returnVar;
	}

	public abstract boolean performTask(HttpServletRequest req, HttpServletResponse resp);

	public void setSettingsProperties(SettingsDTO settingsDTO) {
		this.settingsDTO = settingsDTO;
	}

	public SettingsDTO getSettingsProperties() {
		return settingsDTO;
	}

	public LittleJWebHandlerDTO getLittleJWebHandlerDTO() {
		return littleJWebHandlerDTO;
	}

	public void setLittleJWebHandlerDTO(LittleJWebHandlerDTO littleJWebHandlerDTO) {
		this.littleJWebHandlerDTO = littleJWebHandlerDTO;
	}

	public void storeObject(String key, Object value) {
		req.getSession().setAttribute(key, value);
	}

	public Object getObject(String key) {
		return req.getSession().getAttribute(key);
	}

	public void clearObject(String key) {
		req.getSession().removeAttribute(key);
	}

	public Connection getConnection() {
		DataAdapter adapter = new MySQLDataAdapter();
		Connection conn = null;
		try {
			conn = adapter.getConnection(settingsDTO.getDbUrl(), settingsDTO.getDbUsername(),
					settingsDTO.getDbPassword());
		} catch (SQLException eSQL) {
			System.err.println(eSQL.getMessage());
		}

		return conn;
	}

	public void closeConnection(Connection conn) {
		if (conn != null) {
			try {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			} finally {
				conn = null;
			}
		}

	}

	public String getAlternativeNav() {
		return alternativeNav;
	}

	public void setAlternativeNav(String alternativeNav) {
		this.alternativeNav = alternativeNav;
	}

	public Exception getErrorException() {
		return errorException;
	}

	public void setErrorException(Exception errorException) {
		this.errorException = errorException;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public Map<String, String> getAlternativeNavParms() {
		return alternativeNavParms;
	}

	public void setAlternativeNavParms(Map<String, String> alternativeNavParms) {
		this.alternativeNavParms = alternativeNavParms;
	}
}
