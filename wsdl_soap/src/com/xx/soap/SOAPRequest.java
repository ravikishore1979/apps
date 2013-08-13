/*
 * Copyright SocialTwist Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of SocialTwist.
 * You shall not disclose such confidential information and shall use it only 
 * in accordance with the terms of the source code license agreement you 
 * entered into with SocialTwist.
 */

package com.xx.soap;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

import javax.wsdl.Binding;
import javax.wsdl.BindingOperation;
import javax.wsdl.Definition;
import javax.wsdl.Operation;
import javax.wsdl.Port;
import javax.wsdl.Service;
import javax.wsdl.WSDLElement;
import javax.wsdl.extensions.ExtensibilityElement;
import javax.wsdl.extensions.soap.SOAPBinding;
import javax.wsdl.extensions.soap.SOAPBody;
import javax.wsdl.extensions.soap.SOAPHeader;
import javax.xml.namespace.QName;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import com.xx.util.WSDLConstants;
import com.xx.util.exceptions.WSDLParserException;

/**
 * @author <a href="mailto:ravi@socialtwist.com">Ravi Kishore</a>
 *
 */
public class SOAPRequest
{
    private Document soapDoc;
    
    private QName serviceName;
    private String operationName;
    private String servicePortName;
    private Definition wsdlDef;
    private Operation wsdlOperation;
    private SOAPHeader soapInputHeader;
    private SOAPBody soapInputBody;
    private SOAPHeader soapOutputHeader;
    private SOAPBody soapOutputBody;
    private SOAPBinding soapBinding;
    
    

    public SOAPRequest(QName serviceName, String portName, String oprName, Definition wsdlDef)
    {
        this.serviceName = serviceName;
        this.operationName = oprName;
        this.servicePortName = portName;
        this.wsdlDef = wsdlDef;
    }
    
    public void initSOAPRequest(String endPoint) throws ParserConfigurationException, SAXException, IOException
    {
        DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = builderFactory.newDocumentBuilder();
        
        this.soapDoc = builder.parse(new ByteArrayInputStream(WSDLConstants.SOAP_TEMPATE.getBytes()));
    }
    
    public void addHeaderNode(Element headerNode)
    {
        if(headerNode != null)
        {
            //this.soapDoc.get
        }
    }
    
    private void parseBindingOperation() throws WSDLParserException
    {
        Service service =  this.wsdlDef.getService(this.serviceName);
        if(service != null)
        {
            Port port = service.getPort(this.servicePortName);
            if(port != null)
            {
                Binding binding = port.getBinding();
                if(binding != null)
                {
                    this.soapBinding = (SOAPBinding)getExtensibleElement(binding, WSDLConstants.WSDL_SOAP_BINDING);
                    BindingOperation bindingOpr = binding.getBindingOperation(this.operationName, ":none", ":none");
                    if(bindingOpr != null)
                    {
                        this.wsdlOperation = bindingOpr.getOperation();
                        this.soapInputBody = (SOAPBody)getExtensibleElement(bindingOpr.getBindingInput(), WSDLConstants.WSDL_SOAP_BODY);
                        this.soapInputHeader = (SOAPHeader)getExtensibleElement(bindingOpr.getBindingInput(), WSDLConstants.WSDL_SOAP_HEADER);
                        this.soapOutputBody = (SOAPBody)getExtensibleElement(bindingOpr.getBindingOutput(), WSDLConstants.WSDL_SOAP_BODY);
                        this.soapOutputHeader = (SOAPHeader)getExtensibleElement(bindingOpr.getBindingOutput(), WSDLConstants.WSDL_SOAP_HEADER);
                    }
                    else
                    {
                        throw new WSDLParserException("Binding is not defined for the operation with name '" + this.servicePortName + "' in the wsdl.");
                    }
                }
            }
            else
            {
                throw new WSDLParserException("Port with name '" + this.servicePortName + "' is not defined in service qname '" + this.serviceName + "'.");
            }
        }
        else
        {
            throw new WSDLParserException("Service with qname '" + this.serviceName + "' is not defined in the wsdl.");
        }
    }
    
    private ExtensibilityElement getExtensibleElement(WSDLElement wsdlElement, QName elementType)
    {
        List<ExtensibilityElement> extendedElements = wsdlElement.getExtensibilityElements();
        for( ExtensibilityElement extensibilityElement : extendedElements )
        {
            return extensibilityElement.getElementType().equals(elementType) ? extensibilityElement : null;
        }
        return null;
    }
}