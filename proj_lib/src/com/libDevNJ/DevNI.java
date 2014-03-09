/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.libDevNJ;

import java.util.Arrays;
import java.util.List;

/**
 *
 * @author fsabbir
 */
public interface DevNI {
    /**
     * Current name of the library
     */
    public final String LIB_NAME = "libDevNJ";
    /**
     * Current version of the library
     */
    public final String LIB_VERSION = "0.1a";
    /**
     * twitter of Farhan Sabbir Siddique <a href="https://twitter.com/share" class="twitter-share-button" data-via="farhansabbir">Tweet</a>
     */
    public final String LIB_AUTHOR = "@farhansabbir";
    /**
     * The release type of this library. Usually will denote alpha, beta, RC{x}, stable
     */
    public final String LIB_RELEASE_STATE = "alpha";
    /**
     * Contains the valid number of exceptions defined in the library
     */
    public final List DevNJException_EXCEPTION_TYPES = Arrays.asList(0,1,2);
    /**
     * Exceptions not defined within current libDevNJ library
     */
    public final int DevNJException_INVALID_EXCEPTION = 0; // 
    /**
     * Exceptions raised without @errorcode, i.e. to facilitate extension of the library for exception types not defined.
     */
    public final int DevNJException_GENERIC_EXCEPTION = 1; //
    /**
     * Denotes that the payload object has already been marked as complete and ready for transmission
     */
    public final int DevNJException_PAYLOAD_ALREADY_MARKED_COMPLETE = 2;
    /**
     * Denotes that the payload's object is not marked complete
     */
    public final int DevNJException_PAYLOAD_OBJECT_NOT_COMPLETE = 3;
    /**
     * Denotes that the payload's object is not present
     */
    public final int DevNJException_PAYLOAD_OBJECT_NOT_PRESENT = 4;
    /**
     * Denotes that the payload object being created does not have its name specified by the implementer
     */
    public final int DevNJException_PAYLOAD_OBJECT_NAME_MISSING = 0;
    /**
     * Denotes the object type of the payload's object
     */
    public final int DevNJException_PAYLOAD_OBJECTTYPE_ERROR = 1;
    /**
     * Denotes the exception thrown when socket input stream cannot be read
     */
    public final int DevNJException_PAYLOAD_SOCKET_READ_ERROR = 0;
    /**
     * Denotes that the payload is not marked complete.
     */
    public final int DevNJException_PAYLOAD_NOT_COMPLETE = 1;
    /**
     * Denotes that the payload is not valid
     */
    public final int DevNJException_PAYLOAD_NOT_VALID = 2;
    /**
     * Denotes the exception thrown when:<br> 
     * - Output file cannot be created (permission problem or file already exists)
     * - Output file name given is null.
     */
    public final int DevNJException_OUTPUT_FILE_ERROR = 0;
    /**
     * Defines the name of the payload ID prefix
     */
    public final String PAYLOAD_ID_NAME = "Payload";
    /**
     * Defines the payload type as data. Meaning payload object contains only data
     */
    public final int PAYLOAD_TYPE_DATA = 1;
    /**
     * Defines the payload type as control. Meaning payload object contains control messages
     */
    public final int PAYLOAD_TYPE_CONTROL = 0;
    /**
     * Defines the name of the payload object's name. This name is used within the library if no name is passed. 
     */
    public final String PAYLOAD_DATAOBJECT_DEFAULT_NAME = "GenericDataPayloadObject";
    /**
     * Defines the name of the file payload object.
     */
    public final String PAYLOAD_OBJECT_FILEDATAPAYLOADOBJECT_NAME = "FileDataPayloadObject";
    /**
     * Defines the current payload types supported in the library.
     */
    public final List PAYLOAD_OBJECTTYPES = Arrays.asList(1);
    /**
     * Defines the file payload object type supported in the library.
     */
    public final int PAYLOAD_OBJECTTYPE_FILEDATAPAYLOADOBJECT = 1;
    /**
     * Defines the generic payload object type supported in the library.
     */
    public final int PAYLOAD_OBJECTTYPE_GENERICDATAPAYLOADOBJECT = 1;
    /**
     * Defines the maximum file size supported by the file payload object
     */
    public final int PAYLOAD_OBJECT_FILEDATAPAYLOADOBJECT_MAX_FILE_SIZE = Integer.MAX_VALUE;
    /**
     * Defines the maximum at a time read size from a file of the file payload object
     */
    public final int PAYLOAD_OBJECT_FILEDATAPAYLOADOBJECT_MAX_READ_SIZE = 1024;
    /**
     * Defines the maximum at a time write size from a file of the file payload object
     */
    public final int PAYLOAD_OBJECT_FILEDATAPAYLOADOBJECT_MAX_WRITE_SIZE = 1024;
    /**
     * Denotes the error of a payload return. This is a generic error return of the payload
     */
    public final int PAYLOAD_ERROR_RETURN = -1;
}
