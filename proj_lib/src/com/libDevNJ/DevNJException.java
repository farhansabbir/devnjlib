
package com.libDevNJ;
import org.apache.log4j.*;
/**
 * @version 1.0
 * @author fsabbir
 */
public class DevNJException extends Exception{
    org.apache.log4j.Logger LOGGER = org.apache.log4j.Logger.getLogger(com.libDevNJ.DevNJException.class);
    
    private String MESSAGE;
    private int ERRORCODE;
    
    /***
     *  
     * @return Returns the name of the exception along with version of the library it belongs to. Exception version may be different than library version
     */
    public String toString()
    {
        return "DevNException. libDevNJ: " + com.libDevNJ.DevNI.LIB_VERSION;
    }
    
    /***
     * 
     * @param message String representing the reason for why the exception was thrown
     */
    public DevNJException(String message)
    {
        super(message);
        this.MESSAGE = message;
        this.ERRORCODE = com.libDevNJ.DevNI.DevNJException_GENERIC_EXCEPTION;
    }
    
    /***
     * 
     * @param message String representing the reason for why the exception was thrown
     * @param errorcode Integer representation of the error. 
     * @throws com.libDevNJ.DevNJException If errorcode parameter passed does not comply with errorcodes defined in library, exception is thrown.
     * @see com.libDevNJ.DevNI
     */
    public DevNJException(String message, int errorcode) throws DevNJException
    {
        super(message);
        if(com.libDevNJ.DevNI.DevNJException_EXCEPTION_TYPES.contains(errorcode))
        {
            this.ERRORCODE = errorcode;
            this.MESSAGE = message;
        }
        else
            throw new com.libDevNJ.DevNJException("Error code " + errorcode + " is not defined in " + com.libDevNJ.DevNI.LIB_VERSION + " version of " + com.libDevNJ.DevNI.LIB_NAME, com.libDevNJ.DevNI.DevNJException_INVALID_EXCEPTION);
    }
}
