package com.nexbis.fwcms.ws.endpoint.adapter;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringEscapeUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.HandlerAdapter;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.ws.InvalidXmlException;
import org.springframework.ws.WebServiceMessageFactory;
import org.springframework.ws.support.DefaultStrategiesHelper;
import org.springframework.ws.transport.WebServiceConnection;
import org.springframework.ws.transport.WebServiceMessageReceiver;
import org.springframework.ws.transport.http.HttpServletConnection;
import org.springframework.ws.transport.http.HttpTransportConstants;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.transport.http.WebServiceMessageReceiverHandlerAdapter;

public class FwcmsWsMessageReceiverHandlerAdapter extends WebServiceMessageReceiverHandlerAdapter
													implements HandlerAdapter, Ordered, InitializingBean, ApplicationContextAware {

	private ApplicationContext context;
	
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.context = applicationContext;
    }

    @Override
    public void afterPropertiesSet() {
        DefaultStrategiesHelper defaultStrategiesHelper = new DefaultStrategiesHelper(MessageDispatcherServlet.class);
        WebServiceMessageFactory factory = defaultStrategiesHelper
                .getDefaultStrategy(WebServiceMessageFactory.class, context);
        setMessageFactory(factory);
    }

    public ModelAndView handle(HttpServletRequest httpServletRequest,
                               HttpServletResponse httpServletResponse,
                               Object handler) throws Exception {
        if (HttpTransportConstants.METHOD_POST.equals(httpServletRequest.getMethod())) {
            WebServiceConnection connection = new FwcmsWebServiceConnection(httpServletRequest, httpServletResponse);
            try {
                handleConnection(connection, (WebServiceMessageReceiver) handler);
            } catch (InvalidXmlException ex) {
                handleInvalidXmlException(httpServletRequest, httpServletResponse, handler, ex);
            } catch (Exception ex) {
                handleGeneralException(httpServletRequest, httpServletResponse, handler, ex);
            }
        }
        else {
            handleNonPostMethod(httpServletRequest, httpServletResponse, handler);
        }
        return null;
    }


    private void handleGeneralException(
            HttpServletRequest httpServletRequest,
            HttpServletResponse response, Object handler,
            Exception ex) throws IOException {
        writeErrorResponseWithMessage(response, ex.getClass().getName() + ": " + ex.getMessage());
    }

    /**
     * By default, sets SC_BAD_REQUEST as response in Spring, so overwritten to
     * provide HTTP_OK and reasonable SOAP fault response.
     */
    protected void handleInvalidXmlException(
            HttpServletRequest httpServletRequest,
            HttpServletResponse response, Object handler, InvalidXmlException ex)
            throws IOException {
        writeErrorResponseWithMessage(response, ex.getClass().getName() + ": " + ex.getMessage());
    }

    /**
     * By default, sets SC_METHOD_NOT_ALLOWED as response in Spring, so overwritten to
     * provide HTTP_OK and reasonable SOAP fault response.
     */
    protected void handleNonPostMethod(HttpServletRequest httpServletRequest,
                                       HttpServletResponse response,
                                       Object handler) throws Exception {
        writeErrorResponseWithMessage(response, "HTTP Method not allowed");
    }

    private void writeErrorResponseWithMessage(HttpServletResponse response, String message)
            throws IOException {
        String errorXml = String.format(
                 ""
                +"    "
                +"    "
                +"        "
                +"            SOAP-ENV:Client"
                +"            %s"
                +"        "
                +"    "
                +"",
                StringEscapeUtils.escapeXml(message)
                );

        response.setStatus(HttpServletResponse.SC_OK);
        response.setContentType("text/xml");
        response.getWriter().write(errorXml);
        response.getWriter().flush();
    }
    @Override
    public int getOrder() {
        return 1;
    }

    /**
     * This class is needed as org.springframework.ws.transport.http.HttpServletConnection will throw an
     * exception if it is used outside Spring framework files. However, extending it and using the same
     * implementation seems to be fine.
     *
     */
    static class FwcmsWebServiceConnection extends HttpServletConnection {
        protected FwcmsWebServiceConnection(HttpServletRequest httpServletRequest,
                HttpServletResponse httpServletResponse) {
            super(httpServletRequest, httpServletResponse);
        }
    }
	
}
