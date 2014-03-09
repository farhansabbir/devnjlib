package com.libDevNJ;

/**
 * This is a layer that every class must implement to pass to Payload as object.<br>
 * All objects are accepted as payload abstract objects.<br>
 * Implementers must carefully implement the AbstractPayloadObject so that the object implemented takes care of creating a valid object first.<br>
 All implementers must implement the setPayloadObjectName(String name) at least to set the name of the object so that the object read/write is clean.<br>
 * @author fsabbir
 */
public abstract class AbstractPayloadObject {
    org.apache.log4j.Logger LOGGER = org.apache.log4j.Logger.getLogger(com.libDevNJ.AbstractPayloadObject.class);
    /**
     * The name of this payload object
     */
    private static String PAYLOAD_OBJECT_NAME;
    private static int PAYLOAD_OBJECT_TYPE;
    /**
     * This method must be implemented by implementing subclass to set name of the payload object
     * @param name
     * @throws com.libDevNJ.DevNJException 
     */
    public abstract void setPayloadObjectName(String name) throws com.libDevNJ.DevNJException;
    public abstract void setPayloadObjectType(int objectype) throws com.libDevNJ.DevNJException;
    public abstract String getPayloadObjectName() throws com.libDevNJ.DevNJException;
    public abstract int getPayloadObjectType();
    public abstract void setPayloadObjectComplete(boolean complete) throws com.libDevNJ.DevNJException;
    public abstract boolean isPayloadObjectComplete();

    /**
     * 
     * @param name
     * @return
     * @throws com.libDevNJ.DevNJException 
     */
    private static AbstractPayloadObject createPayloadObject(String name) throws com.libDevNJ.DevNJException
    {
        if(name.equals(""))
        {
            PAYLOAD_OBJECT_NAME = com.libDevNJ.DevNI.PAYLOAD_DATAOBJECT_DEFAULT_NAME;
        }
        return new com.libDevNJ.GenericDataPayloadObject(PAYLOAD_OBJECT_NAME);
    }
    /**
     * 
     * @return
     * @throws com.libDevNJ.DevNJException 
     */
    public static AbstractPayloadObject getDefaultPayloadObject() throws com.libDevNJ.DevNJException
    {
        AbstractPayloadObject payload = AbstractPayloadObject.createPayloadObject(com.libDevNJ.DevNI.PAYLOAD_DATAOBJECT_DEFAULT_NAME);
        return payload;
    }
    
}
