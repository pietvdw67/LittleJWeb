package littleJWeb;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import littleJ.dto.SettingsDTO;
import littleJWeb.DTO.LittleJWebHandlerDTO;
import littleJWeb.navigator.HeaderBarNavigator;
import littleJWeb.navigator.Navigator;

@SuppressWarnings("serial")
public class Controller extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		processHttpRequest(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		processHttpRequest(req, resp);
	}

	private void processHttpRequest(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		processHttpRequest(req, resp, "", null);
	}

	private void processHttpRequest(HttpServletRequest req, HttpServletResponse resp, String alternativeNav,
			Map<String, String> alternativeParms) throws ServletException, IOException {

		String navigatorParameter = "";

		// Sets properties if not set
		HttpSession session = req.getSession();
		if (session.getAttribute("LoadedProperties") != "True") {
			setSessionProperties(req);
		}
		SettingsDTO settingsDTO = (SettingsDTO) session.getAttribute("settingsDTO");
		LittleJWebHandlerDTO littleJWebHandlerDTO = (LittleJWebHandlerDTO) session.getAttribute("littleJWebHandlerDTO");

		if (alternativeNav.length() == 0) {
			navigatorParameter = req.getParameter("nav");
		} else {
			navigatorParameter = alternativeNav;

			if (alternativeParms != null) {
				for (String key : alternativeParms.keySet()) {
					req.setAttribute(key, alternativeParms.get(key));
				}
			}
		}

		if (navigatorParameter == null || navigatorParameter.length() == 0) {
			navigatorParameter = "views.items.navigator.ItemFilter";
			req.setAttribute("filterType", "favourite");
		}

		if (navigatorParameter != null && navigatorParameter.length() > 0) {
			try {

				if (navigatorParameter.equalsIgnoreCase("cancel")
						|| navigatorParameter.equalsIgnoreCase("cancelMain")) {
					String cancelNav = "";
					List<String> previousNavs = littleJWebHandlerDTO.getPopulatorNavs();
					if (previousNavs.size() > 0) {
						if (navigatorParameter.equalsIgnoreCase("cancelMain")) {
							cancelNav = previousNavs.get(0);
							previousNavs.clear();
							previousNavs.add(cancelNav);

						} else {
							cancelNav = previousNavs.get(previousNavs.size() - 1);
							if (previousNavs.size() > 1) {
								previousNavs.remove(previousNavs.size() - 1);
							}
						}
					}

					if (cancelNav.length() > 0) {
						if (cancelNav.indexOf("&") > -1) {
							navigatorParameter = cancelNav.substring(0, cancelNav.indexOf("&"));
							String params = cancelNav.substring(cancelNav.indexOf("&") + 1);
							String[] parmslist = params.split("&");
							for (String currentParm : parmslist) {
								if (currentParm.contains("=")) {
									String key = currentParm.substring(0, currentParm.indexOf("="));
									String value = currentParm.substring(currentParm.indexOf("=") + 1);
									req.setAttribute(key, value);
								}
							}
						} else {
							navigatorParameter = cancelNav;
						}
					}

					resp.sendRedirect("Controller.do?nav=" + cancelNav);
					return;
				}

				// Sets the header bar
				HeaderBarNavigator headerBarNav = new HeaderBarNavigator();
				headerBarNav.setRequestAndResponse(req, resp);
				headerBarNav.setSettingsProperties(settingsDTO);
				headerBarNav.startup();
				headerBarNav.performTask(req, resp);

				String navigatorclass = "littleJWeb." + navigatorParameter + "Navigator";
				String jspPath = "/" + navigatorParameter.replace(".", "/") + ".jsp";

				Navigator navigator = (Navigator) Class.forName(navigatorclass).newInstance();
				navigator.setRequestAndResponse(req, resp);
				navigator.setSettingsProperties(settingsDTO);
				navigator.setLittleJWebHandlerDTO(littleJWebHandlerDTO);
				navigator.startup();

				boolean result = navigator.performTask(req, resp);

				if (result) {
					if (navigator.getAlternativeNav().length() > 0) {
						if (navigator.getAlternativeNavParms() != null) {
							processHttpRequest(req, resp, navigator.getAlternativeNav(),
									navigator.getAlternativeNavParms());
						} else {
							processHttpRequest(req, resp, navigator.getAlternativeNav(), null);
						}
						return;
					}

					RequestDispatcher rd = req.getRequestDispatcher(jspPath);
					rd.forward(req, resp);
				} else {
					setError(req, navigator.getErrorMessage(), navigator.getErrorException());
					RequestDispatcher rd = req.getRequestDispatcher("/exception.jsp");
					rd.forward(req, resp);
				}
			} catch (Exception e) {

				e.printStackTrace();
				setError(req, e.getMessage(), e);
				RequestDispatcher rd = req.getRequestDispatcher("/exception.jsp");
				rd.forward(req, resp);
			}

		}

	}

	private void setSessionProperties(HttpServletRequest req) {
		HttpSession session = req.getSession();
		SettingsDTO settingsDTO = new SettingsDTO();
		LittleJWebHandlerDTO littleJWebHandlerDTO = new LittleJWebHandlerDTO();
		littleJWebHandlerDTO.setPopulatorNavs(new ArrayList<>());
		settingsDTO.setDbUrl(getServletConfig().getInitParameter("dbURL"));
		settingsDTO.setDbUsername(getServletConfig().getInitParameter("dbUser"));
		settingsDTO.setDbPassword(getServletConfig().getInitParameter("dbPassword"));
		settingsDTO.setDbPassword(getServletConfig().getInitParameter("dbPassword"));
		settingsDTO.setPageRefresh(Integer.parseInt(getServletConfig().getInitParameter("pageRefresh")));
		settingsDTO.setDeviceCode(getServletConfig().getInitParameter("RaspberryName"));
		settingsDTO.setRaspberryMaxTemp(Integer.parseInt(getServletConfig().getInitParameter("Rasbperry_Max_Temp")));		

		session.setAttribute("settingsDTO", settingsDTO);
		session.setAttribute("littleJWebHandlerDTO", littleJWebHandlerDTO);
		session.setAttribute("LoadedProperties", "True");
	}

	private String getStackTraceText(Exception e) {
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		e.printStackTrace(pw);
		return sw.toString();
	}

	private void setError(HttpServletRequest req, String message, Exception exception) {
		HttpSession session = req.getSession();

		session.setAttribute("ErrorMessage", message);

		if (exception != null) {
			String errorText = getStackTraceText(exception);
			errorText = "<h3>" + exception.getMessage() + "</h3>" + errorText;
			session.setAttribute("ErrorText", errorText);
		} else {
			session.setAttribute("ErrorText", "No more info was given");
		}

	}
}
