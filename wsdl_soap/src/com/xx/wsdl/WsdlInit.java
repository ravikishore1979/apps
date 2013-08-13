/*
 * Copyright SocialTwist Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of SocialTwist.
 * You shall not disclose such confidential information and shall use it only 
 * in accordance with the terms of the source code license agreement you 
 * entered into with SocialTwist.
 */

package com.xx.wsdl;

import javax.wsdl.Definition;
import javax.wsdl.WSDLException;
import javax.wsdl.factory.WSDLFactory;
import javax.wsdl.xml.WSDLReader;

import com.xx.util.exceptions.WSDLParserException;



/**
 * @author <a href="mailto:ravi@socialtwist.com">Ravi Kishore</a>
 *
 */
public class WsdlInit
{

    private String wsdlURL;
    private Definition wsdlDef = null;
    
    public WsdlInit(String wsdlUrl) throws WSDLParserException
    {
        this.wsdlURL = wsdlUrl;
        initializeReader();
    }
    
    private void initializeReader() throws WSDLParserException
    {
        try
        {
            WSDLFactory wsdlFactory = WSDLFactory.newInstance();
            WSDLReader wsdlReader = wsdlFactory.newWSDLReader();
            this.wsdlDef = wsdlReader.readWSDL(this.wsdlURL);
        }catch( WSDLException e )
        {
            throw new WSDLParserException("Exception while parsing the WSDL.", e);
        }
    }

    /**
     * @return the wsdlURL
     */
    public String getWsdlURL()
    {
        return wsdlURL;
    }

    /**
     * @return the wsdlReader
     */
    public Definition getWsdlDefinition()
    {
        return this.wsdlDef;
    }
}
