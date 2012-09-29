package br.com.videosoft.pinpad.web.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.web.HttpRequestHandler;

import br.com.videosoft.pinpad.services.spec.CustomerService;

@Service("requestPaymentKeyServlet")
@Scope("session")
public class RequestPaymentKeyServlet implements HttpRequestHandler,Serializable{

	private static final long serialVersionUID = 697530277965676510L;

	private static final Logger LOGGER = Logger.getLogger(RequestPaymentKeyServlet.class);
	
	@Autowired	
	private CustomerService orderService;
	
	public void handleRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		LOGGER.info("Iniciando o pagamento do carrinho de compras da natura.");
		
		System.out.println("VEIO PARA O SERVLET LOCALHOST");
		String b2wOrderId = req.getParameter("orderId");
		
		System.out.println(b2wOrderId);
		
		PrintWriter out = resp.getWriter();
		out.flush();
		out.close();
	}
	
}
