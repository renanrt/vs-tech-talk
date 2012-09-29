package br.com.videosoft.pinpad.web.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

@SuppressWarnings("serial")
public class StartCreditCardPaymentServlet extends HttpServlet {

	private static final Logger LOGGER = Logger.getLogger(StartCreditCardPaymentServlet.class);
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession(false);
		
		if(session!=null){
			LOGGER.info("Session already exists. Let's destroy all objects");
			session.invalidate();
		}
		
		req.getRequestDispatcher("card_payment.jsp").forward(req, resp);
	}
}