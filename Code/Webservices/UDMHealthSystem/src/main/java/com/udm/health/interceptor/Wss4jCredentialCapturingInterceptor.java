package com.udm.health.interceptor;

import static com.udm.health.interceptor.MessageContextEntry.WS_PASSWORD;
import static com.udm.health.interceptor.MessageContextEntry.WS_USERNAME;

import org.apache.ws.security.message.token.UsernameToken;
import org.apache.ws.security.util.WSSecurityUtil;
import org.springframework.ws.context.MessageContext;
import org.springframework.ws.server.EndpointInterceptor;
import org.springframework.ws.soap.SoapMessage;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

public class Wss4jCredentialCapturingInterceptor implements EndpointInterceptor {

	@Override
	public boolean handleRequest(MessageContext messageContext, Object endpoint) throws Exception {
		SoapMessage soapMessage = (SoapMessage) messageContext.getRequest();
		Element securityHeader = WSSecurityUtil.getSecurityHeader(soapMessage.getDocument(), "");
		if (securityHeader != null) {
			Element token = locateTokenElement(securityHeader);
			if (token != null) {
				UsernameToken usernameToken = new UsernameToken(token, true, false);
				messageContext.setProperty(WS_USERNAME.name(), usernameToken.getName());
				messageContext.setProperty(WS_PASSWORD.name(), usernameToken.getPassword());
			}
		}
		return true;
	}
	
	private Element locateTokenElement(Element securityHeader) {
		Node node = securityHeader.getFirstChild();
        while (node != null && ! "UsernameToken".equals(node.getLocalName())) {
        	node = node.getNextSibling();
        }
        return (Element) node;
	}

	@Override
	public boolean handleResponse(MessageContext messageContext, Object endpoint) throws Exception {
		return true;
	}

	@Override
	public boolean handleFault(MessageContext messageContext, Object endpoint) throws Exception {
		return true;
	}

	@Override
	public void afterCompletion(MessageContext messageContext, Object endpoint, Exception ex) throws Exception {

	}

}
