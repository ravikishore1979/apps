/*
 * Copyright SocialTwist Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of SocialTwist.
 * You shall not disclose such confidential information and shall use it only 
 * in accordance with the terms of the source code license agreement you 
 * entered into with SocialTwist.
 */

package com.xx.util.exceptions;

/**
 * @author <a href="mailto:ravi@socialtwist.com">Ravi Kishore</a>
 *
 */
public class WSDLParserException extends Exception
{

    private static final long serialVersionUID = 1L;

    public WSDLParserException()
    {
        // TODO Auto-generated constructor stub
    }

    /**
     * @param arg0
     */
    public WSDLParserException( String arg0 )
    {
        super(arg0);
    }

    /**
     * @param arg0
     */
    public WSDLParserException( Throwable arg0 )
    {
        super(arg0);
    }

    /**
     * @param arg0
     * @param arg1
     */
    public WSDLParserException( String arg0, Throwable arg1 )
    {
        super(arg0, arg1);
    }

}
