package com.libDevNJ;

import java.io.*;
/**
 * This is an implementation of the AbstractPayloadObject, showing how it should be implemented.
 * This class creates a FileDataPayloadObject to be used as a payload object.<br>
 * This class is responsible to create a payload object after reading the entire contents from a file.<br>
 * @author fsabbir
 */
public class FileDataPayloadObject extends com.libDevNJ.AbstractPayloadObject{
    org.apache.log4j.Logger LOGGER = org.apache.log4j.Logger.getLogger(com.libDevNJ.FileDataPayloadObject.class);
    /**
     * Defines the name of this payload object
     */
    private String OBJECT_NAME;
    /**
     * Defines the type of this payload object
     */
    private int OBJECT_TYPE;
    /**
     * Defines the name of the file of this payload object.
     */
    private String FILE_NAME;
    /**
     * Defines the completeness of this payload object
     */
    private boolean PAYLOAD_COMPLETE;
    /**
     * The actual data of this payload object
     */
    private byte[] DATA;
    /**
     * Constructor of FileDataPayloadObject.
     * The implementers do not need to call setPayloadObjectName() or setPayloadObjectType() method explicitly. Its initialized by constructor.<br>
     * The implementers need to call setFilePayloadObjectData() to set data and lastly call pack() method to finalize the payload object.
     * The constructor initializes the:<br>
     * - FILE_NAME<br>
     * - OBJECT_TYPE to PAYLOAD_OBJECT_FILEDATAPAYLOADOBJECT_NAME<br>
     * - OBJECT_TYPE to PAYLOAD_OBJECTTYPE_FILEDATAPAYLOADOBJECT<br>
     * - DATA to null<br>
     * - PAYLOAD_COMPLETE to false;
     *  
     * @param filename The filename of the file which is to be treated as object for payload
     */
    public FileDataPayloadObject(String filename) throws com.libDevNJ.DevNJException
    {
        this.FILE_NAME = this.setFileName(filename);
        this.OBJECT_NAME = com.libDevNJ.DevNI.PAYLOAD_OBJECT_FILEDATAPAYLOADOBJECT_NAME;
        this.OBJECT_TYPE = com.libDevNJ.DevNI.PAYLOAD_OBJECTTYPE_FILEDATAPAYLOADOBJECT;
        this.DATA = null;
        this.PAYLOAD_COMPLETE = false;
        this.initialize();
    }
    /**
     * 
     * @param objectname The name of this FilePayloadObject
     * @param filename The filename of the file which is to be treated as object for payload
     */
    public FileDataPayloadObject(String objectname, String filename) throws com.libDevNJ.DevNJException
    {
        this.FILE_NAME = this.setFileName(filename);
        this.OBJECT_NAME = objectname;
        this.OBJECT_TYPE = com.libDevNJ.DevNI.PAYLOAD_OBJECTTYPE_FILEDATAPAYLOADOBJECT;
        this.DATA = null;
        this.PAYLOAD_COMPLETE = false;
        this.initialize();
    }
    private void initialize() throws com.libDevNJ.DevNJException
    {
        this.setPayloadObjectName(com.libDevNJ.DevNI.PAYLOAD_OBJECT_FILEDATAPAYLOADOBJECT_NAME);
        this.setPayloadObjectType(com.libDevNJ.DevNI.PAYLOAD_OBJECTTYPE_FILEDATAPAYLOADOBJECT);
    }
    /**
     * 
     * @param name
     * @throws com.libDevNJ.DevNJException 
     */
    @Override
    public void setPayloadObjectName(String name) throws com.libDevNJ.DevNJException
    {
        this.OBJECT_NAME = com.libDevNJ.DevNI.PAYLOAD_OBJECT_FILEDATAPAYLOADOBJECT_NAME;
    }
    /**
     * 
     * @param objecttype
     * @throws com.libDevNJ.DevNJException 
     */
    @Override
    public void setPayloadObjectType(int objecttype) throws com.libDevNJ.DevNJException
    {
        if(objecttype!=com.libDevNJ.DevNI.PAYLOAD_OBJECTTYPE_FILEDATAPAYLOADOBJECT)
        {
            throw new com.libDevNJ.DevNJException("Object type not defined in library.", com.libDevNJ.DevNI.DevNJException_PAYLOAD_OBJECTTYPE_ERROR);
        }
        this.OBJECT_TYPE = com.libDevNJ.DevNI.PAYLOAD_OBJECTTYPE_FILEDATAPAYLOADOBJECT;
    }
    /**
     * 
     * @return 
     */
    @Override
    public int getPayloadObjectType()
    {
        return this.getPayloadObjectType();
    }
    /**
     * 
     * @return 
     */
    @Override
    public String getPayloadObjectName()
    {
        return this.getPayloadObjectName();
    }

    /**
     * 
     * @return 
     */
    public String getFileName()
    {
        return this.FILE_NAME;
    }
    /**
     * 
     * @param filename
     * @return 
     */
    public String setFileName(String filename)
    {
        this.FILE_NAME = (new java.io.File(filename)).getName();
        return this.FILE_NAME;
    }
    /**
     * 
     * @param data 
     */
    public void setFilePayloadObjectData(byte[] data)
    {
        this.DATA = data;
    }
    /**
     * 
     * @return 
     */
    public byte[] getFilePayloadObjectData()
    {
        return this.DATA;
    }
    /**
     * 
     * @return 
     */
    public int getPayloadObjectDataSize()
    {
        return this.DATA.length;
    }
    /**
     * 
     * @param complete
     * @throws com.libDevNJ.DevNJException 
     */
    public void setPayloadObjectComplete(boolean complete) throws com.libDevNJ.DevNJException
    {
        this.PAYLOAD_COMPLETE = complete;
    }
    /**
     * 
     * @throws com.libDevNJ.DevNJException 
     */
    public void pack() throws com.libDevNJ.DevNJException
    {
        this.setPayloadObjectComplete(true);
    }
    /**
     * 
     * @return 
     */
    public boolean isPayloadObjectComplete()
    {
        return this.PAYLOAD_COMPLETE;
    }
}
