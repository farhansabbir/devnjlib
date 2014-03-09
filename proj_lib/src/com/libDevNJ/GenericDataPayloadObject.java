package com.libDevNJ;

/**
 * Class used to create a generic object for payload.<br>
 * Particularly useful for situations when you dont know what to send as payload object.<br>
 * @author fsabbir
 */
public class GenericDataPayloadObject extends AbstractPayloadObject {
    org.apache.log4j.Logger LOGGER = org.apache.log4j.Logger.getLogger(com.libDevNJ.GenericDataPayloadObject.class);
    private String OBJECT_NAME;
    private Object OBJECT;
    private int OBJECT_TYPE;
    private boolean PAYLOAD_COMPLETE;
    /**
     * Class used to create a generic object for payload.<br>
     * Particularly useful for situations when you dont know what to send as payload object.<br>
     * @param name The name is this generic object. Usually taken from the library com.libDevNJ
     * @throws com.libDevNJ.DevNJException 
     */
    public GenericDataPayloadObject(String name) throws com.libDevNJ.DevNJException
    {
        this.setPayloadObjectName(name);
        this.OBJECT = null;
        this.OBJECT_TYPE = com.libDevNJ.DevNI.PAYLOAD_OBJECTTYPE_GENERICDATAPAYLOADOBJECT;
        this.PAYLOAD_COMPLETE = false;
    }
    /**
     * This method is responsible to set the object name for this generic object.
     * @param name Name of the object
     * @throws com.libDevNJ.DevNJException 
     */
    @Override
    public void setPayloadObjectName(String name) throws com.libDevNJ.DevNJException{
        if(name.equals(""))
        {
            throw new com.libDevNJ.DevNJException("Missing payload object name.", com.libDevNJ.DevNI.DevNJException_PAYLOAD_OBJECT_NAME_MISSING);
        }
        else
        {
            this.OBJECT_NAME = name;
        }
    }
    /**
     * 
     * @param objecttype
     * @throws com.libDevNJ.DevNJException 
     */
    @Override
    public void setPayloadObjectType(int objecttype) throws com.libDevNJ.DevNJException
    {
        if(objecttype!=com.libDevNJ.DevNI.PAYLOAD_OBJECTTYPE_GENERICDATAPAYLOADOBJECT)
        {
            throw new com.libDevNJ.DevNJException("Object type not defined in library.", com.libDevNJ.DevNI.DevNJException_PAYLOAD_OBJECTTYPE_ERROR);
        }
        this.OBJECT_TYPE = com.libDevNJ.DevNI.PAYLOAD_OBJECTTYPE_GENERICDATAPAYLOADOBJECT;
    }
    /**
     * Returns the value for this object type as defined in the library
     * @return Returns the value for this object type as defined in the library
     */
    @Override
    public int getPayloadObjectType()
    {
        return this.getPayloadObjectType();
    }
    /**
     * Returns the value for this object name as defined in the library or defined in custom
     * @return Returns the value for this object name as defined in the library or defined in custom
     */
    @Override
    public String getPayloadObjectName()
    {
        return this.getPayloadObjectName();
    }
    /**
     * This method puts the actual object in this Generic object.<br>
     * User must work on the object before putting it here.<br>
     * @param obj The object to be put in the generic object. 
     */
    public void setObject(Object obj)
    {
        this.OBJECT = obj;
    }
    /**
     * 
     * @param complete
     * @throws com.libDevNJ.DevNJException 
     */
    @Override
    public void setPayloadObjectComplete(boolean complete) throws com.libDevNJ.DevNJException
    {
        
    }
    /**
     * 
     * @return 
     */
    @Override
    public boolean isPayloadObjectComplete()
    {
        return this.PAYLOAD_COMPLETE;
    }
}
