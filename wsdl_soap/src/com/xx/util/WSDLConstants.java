/*
 * Copyright SocialTwist Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of SocialTwist.
 * You shall not disclose such confidential information and shall use it only 
 * in accordance with the terms of the source code license agreement you 
 * entered into with SocialTwist.
 */

package com.xx.util;

import javax.xml.namespace.QName;

/**
 * @author <a href="mailto:ravi@socialtwist.com">Ravi Kishore</a>
 *
 */
public interface WSDLConstants
{

    static final String SOAPENV = "http://schemas.xmlsoap.org/soap/envelope/";
    static final String WSDL_SOAP = "http://schemas.xmlsoap.org/wsdl/soap/";
    
    static final QName WSDL_SOAP_BODY = new QName(WSDLConstants.WSDL_SOAP, "body");
    static final QName WSDL_SOAP_HEADER = new QName(WSDLConstants.WSDL_SOAP, "header");
    static final QName WSDL_SOAP_BINDING = new QName(WSDLConstants.WSDL_SOAP, "binding");
    
    
    static final String SOAP_TEMPATE = "<soapenv:Envelope xmlns:soapenv='http://schemas.xmlsoap.org/soap/envelope/'> "
                                            + "<soapenv:Header> </soapenv:Header> "
                                            + "<soapenv:Body> </soapenv:Body> "
                                      + "</soapenv:Envelope>";
    
    //static final QName SOAPENV_QNAME = new QN
}
