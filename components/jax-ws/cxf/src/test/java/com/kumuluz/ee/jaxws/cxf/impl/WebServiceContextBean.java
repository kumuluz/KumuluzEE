package com.kumuluz.ee.jaxws.cxf.impl;

import jakarta.xml.ws.EndpointReference;
import jakarta.xml.ws.WebServiceContext;
import jakarta.xml.ws.handler.MessageContext;
import org.apache.cxf.jaxws.handler.logical.LogicalMessageContextImpl;
import org.apache.cxf.message.MessageImpl;
import org.w3c.dom.Element;

import java.security.Principal;

public class WebServiceContextBean implements WebServiceContext {

    MessageContext messageContext;

    public WebServiceContextBean(String id) {
        MessageImpl message = new MessageImpl();
        message.put("id", id);
        messageContext = new LogicalMessageContextImpl(message);
    }

    @Override
    public MessageContext getMessageContext() {
        return messageContext;
    }

    @Override
    public Principal getUserPrincipal() {
        return null;
    }

    @Override
    public boolean isUserInRole(String role) {
        return false;
    }

    @Override
    public EndpointReference getEndpointReference(Element... referenceParameters) {
        return null;
    }

    @Override
    public <T extends EndpointReference> T getEndpointReference(Class<T> clazz, Element... referenceParameters) {
        return null;
    }
}
