package com.libDevNJ;

import java.io.*;
import org.apache.log4j.*;

/**
 * This is the class that is responsible to create or validate the payload that's to be transfered or received
 * It contains all the necessary functionalities to contain the objects to be transferred as payload
 * @author fsabbir
 */
public class Payload {
    org.apache.log4j.Logger LOGGER = org.apache.log4j.Logger.getLogger(com.libDevNJ.Payload.class);
    /**
     * Represents if the Payload object contains string message
     */
    private boolean CONTAINS_STRING;
    /**
     * Represents if the Payload object contains object
     */
    private boolean CONTAINS_OBJECT;
    /**
     * Represents if the Payload object is a complete payload ready to be transferred
     */
    private boolean PAYLOAD_COMPLETE;
    /**
     * Represents the static numeric ID part Payload object
     */
    private static long PAYLOAD_NUMERIC_ID;
    /**
     * Represents the Payload object ID
     */
    private String PAYLOAD_ID;
    /**
     * Defines the type of the payload object.
     * @see com.libDevNJ.DevNI Types of Payload Object
     */
    private int PAYLOAD_TYPE;
    /**
     * Defines the type of control of this payload object.<br>
     * This is only valid if PAYLOAD_TYPE is set to PAYLOAD_TYPE_CONTROL, else this variable is ignored
     */
    private int PAYLOAD_CONTROL;
    
    /////////////////////////////////////////////////////
    // PAYLOAD DATA BEGIN
    /**
     * The string payload of the Payload object
     */
    private String PAYLOAD_STRING;
    /**
     * The object payload of the Payload object.
     * Implementers passing objects must implement the AbstractPayloadObject and put it on the Payload object
     */
    private com.libDevNJ.AbstractPayloadObject PAYLOAD_OBJECT;

    
    /**
     * Entry point in preparing a payload.<br>
     * Every payload object must be validated by calling isValid() method just before sending and as soon as it is received.<br>
     * Every payload must be marked as complete using markComplete() method before calling isValid() by the sender.<br>
     * Every payload can only be verified by a reader by calling isValid() method. The isValid() method verifies that the payload object meets the checks of basic protocol design.<br>
     * Every payload ID is different for every payload object. This is used to keep track of the payload object.<br>
     * Payload ID of any payload object can be obtained using getPayloadID() method.<br>
     * Payload readers are yet to cast received object to payload object for further analysis.<br>
     */
    public Payload()
    {
        LOGGER.debug("In Payload() method.");
        com.libDevNJ.Payload.PAYLOAD_NUMERIC_ID++; // increment the ID for this new object
        this.PAYLOAD_ID = com.libDevNJ.DevNI.PAYLOAD_ID_NAME + "-" + com.libDevNJ.Payload.PAYLOAD_NUMERIC_ID; // set the incremented ID of this object
        
        this.CONTAINS_OBJECT = false; // initialize payload does not contain any object yet
        this.CONTAINS_STRING = false; // initialize payload does not contain any string yet
        
        // payload data
        this.PAYLOAD_STRING = ""; // initialize payload string data to null
        this.PAYLOAD_OBJECT = null; // initialize payload object data to null
        
        this.PAYLOAD_COMPLETE = false; // initialize payload object is complete to false.
        
        LOGGER.debug("VARIABLES: com.libDevNJ.Payload.PAYLOAD_NUMERIC_ID: " + com.libDevNJ.Payload.PAYLOAD_NUMERIC_ID);
        LOGGER.debug("VARIABLES: com.libDevNJ.Payload.PAYLOAD_ID: " + this.PAYLOAD_ID);
        LOGGER.debug("VARIABLES: this.CONTAINS_OBJECT: " + this.CONTAINS_OBJECT);
        LOGGER.debug("VARIABLES: this.CONTAINS_STRING: " + this.CONTAINS_STRING);
        LOGGER.debug("VARIABLES: this.PAYLOAD_STRING: " + this.PAYLOAD_STRING);
        LOGGER.debug("VARIABLES: this.PAYLOAD_OBJECT: " + this.PAYLOAD_OBJECT);
        LOGGER.debug("VARIABLES: this.PAYLOAD_COMPLETE: " + this.PAYLOAD_COMPLETE);
        LOGGER.debug("Bailing out of Payload() method.");
    }
    /**
     *
     * @param obj The Payload object to compare with
     * @return Returns true if the ID of this payload object matches with the ID of the argument Payload object
     */
    @Override
    public boolean equals(Object obj)
    {
        LOGGER.debug("In equals(Object obj) method.");
        Payload p = (Payload)obj;
        LOGGER.debug("PARAMETER: (Payload)obj's ID: " + p.getPayloadID());
        LOGGER.debug("Current Payload object's ID: " + this.getPayloadID());
        LOGGER.debug("Bailing out from equals(Object obj) method.");
        return p.getPayloadID().equals(this.getPayloadID());
    }
    /**
     * Method returns the ID of this payload object. This is also used internally to generate the equals method
     * @return ID of this payload object
     */
    public String getPayloadID()
    {
        LOGGER.debug("In getPayloadID() method.");
        LOGGER.debug("VARIABLES: this.PAYLOAD_ID:" + this.PAYLOAD_ID);
        LOGGER.debug("Bailing out from getPayloadID() method.");
        return this.PAYLOAD_ID;
    }
    /**
     * Marks the payload object as complete for transmission. 
     * Every payload object must be marked as complete before transmission
     * Over writing complete status of an already complete payload can be done for future API extension
     * API reading the payload object must call isValid() method before continuing to read the object further.
     * @param complete Denotes that the payload object to be marked as complete or not.
     */
    public void markComplete(boolean complete) throws com.libDevNJ.DevNJException
    {
        LOGGER.debug("In markComplete(boolean complete) method.");
        if(!complete)
        {
            LOGGER.debug("PARAMETER: complete: " + complete);
            this.PAYLOAD_COMPLETE = false;
            LOGGER.debug("Bailing out of markComplete(boolean complete) method with complete: " + complete);
            return;
        }
        if(this.isValid())
        {
            LOGGER.debug("Payload is valid. Setting complete to true.");
            this.PAYLOAD_COMPLETE = true;
            if(!this.CONTAINS_OBJECT) // payload contains no object
            {
                LOGGER.debug("Payload does not contain any object.");
                if(!this.CONTAINS_STRING) // payload contains no string, so set payload type as control
                {
                    LOGGER.debug("Payload does not contain string. Setting payload type to control.");
                    this.PAYLOAD_TYPE = com.libDevNJ.DevNI.PAYLOAD_TYPE_CONTROL;
                }
            }
            if(this.PAYLOAD_TYPE==com.libDevNJ.DevNI.PAYLOAD_TYPE_CONTROL) // payload type is control
            {
                LOGGER.debug("Payload type is control. Setting payload control to same as payload type.");
                this.PAYLOAD_CONTROL = this.PAYLOAD_TYPE; // set payload control to payload type
            }
        }
        else
        {
            LOGGER.debug("Payload is not valid. Setting PAYLOAD_COMPLETE=false;");
            this.PAYLOAD_COMPLETE = false;
            LOGGER.debug("Bailing out of markComplete(boolean complete) method with payload is not valid exception.");
            throw new com.libDevNJ.DevNJException("Payload is not valid", com.libDevNJ.DevNI.DevNJException_PAYLOAD_NOT_VALID);
        }
        LOGGER.debug("Bailing out of markComplete(boolean complete) method.");
    }
    
    /**
     * Method used internally only.<br>
     * Method checks if the payload object is valid.
     * The object itself does not hold any variable that marks the object as valid. Validation is done by computation only.<br>
     * This method is called internally when markComplete() and isPayloadComplete() is called. It does not check the completeness of the payload.<br>
     * @return Returns true if payload object is valid, else false
     */
    private boolean isValid()
    {
        LOGGER.debug("In isValid() method.");
        boolean valid = true;
        if(this.CONTAINS_OBJECT) //payload contains object
        {
            LOGGER.debug("Success for if(this.CONTAINS_OBJECT)" + this.CONTAINS_OBJECT);
            LOGGER.debug("Payload contains object.");
            if(this.PAYLOAD_TYPE!=this.PAYLOAD_OBJECT.getPayloadObjectType()) // payload type is not same as payload's object type, so return not valid payload
            {
                LOGGER.debug("Payload's type does not match with payloa'd object type.");
                LOGGER.debug("Payload's type: " + this.PAYLOAD_TYPE);
                LOGGER.debug("Payload's object type: " + this.PAYLOAD_OBJECT.getPayloadObjectType());
                LOGGER.debug("Bailing out from isValid() method with false since payload type does not match with payload's object type.");
                return false;
            }
            if(!this.PAYLOAD_OBJECT.isPayloadObjectComplete()) // payload's object is not complete. So return not valid payload.
            {
                LOGGER.debug("Payload's object is not complete.");
                LOGGER.debug("Bailing out from isValid() method with false since payload's object is not valid.");
                return false;
            }
        }
        // 
        if(this.getPayloadID().length()==0) // payload's ID cannot be zero lenght; Return false if it is
        {
            LOGGER.debug("Payload ID is null");
            LOGGER.debug("Bailing out from isValid() method with false since payload ID is not valid.");
            return false;
        }
        LOGGER.debug("VARIABLES: valid :" + valid);
        LOGGER.debug("Bailing out from isValid() method.");
        return valid;
    }
    /**
     * Method returns true if the payload object is marked as complete. 
     * This must not be called by a payload reader to validate a payload. 
     * All payload reader must call isValid() method to check the validity of the payload.
     * This method can be called by the payload writer while in the middle of preparing and sending the payload.
     * However, before sending a payload, writer must call isValid() method as the last check before actually sending the packet.
     * @return Returns true if packet is marked as complete. 
     */
    public boolean isPayloadComplete()
    {
        LOGGER.debug("In isComplete() method.");
        LOGGER.debug("VARIABLES: this.PAYLOAD_COMPLETE:" + this.PAYLOAD_COMPLETE);
        LOGGER.debug("Bailing out from isComplete() method.");
        return this.PAYLOAD_COMPLETE;
    }
    /**
     * Method returns the numeric object type of the object within the payload object. If not object is present in this payload, it returns com.libDevNJ.DevNI.PAYLOAD_ERROR_RETURN;
     * @return Returns the numeric object type as defined in the library. If not object is present in this payload, it returns com.libDevNJ.DevNI.PAYLOAD_ERROR_RETURN;
     * @throws com.libDevNJ.DevNJException 
     */
    public int getPayloadObjectType() throws com.libDevNJ.DevNJException
    {
       LOGGER.debug("In getPayloadObjectType() method.");
       LOGGER.debug("VARIABLES: this.PAYLOAD_COMPLETE:" + this.PAYLOAD_COMPLETE);
       
       if(!this.CONTAINS_OBJECT) // meaning payload does not contain object; So return -1 saying there is no type for payload object
       {
           LOGGER.debug("Success for if(!this.CONTAINS_OBJECT)" + this.CONTAINS_OBJECT);
           LOGGER.debug("Bailing out from getPayloadObjectType() method with no payload object error.");
           return com.libDevNJ.DevNI.PAYLOAD_ERROR_RETURN;
       }
       else // meaning payload contains object. Now check is object is complete
       {
           if(!this.PAYLOAD_OBJECT.isPayloadObjectComplete()) // meaning payload is not marked complete. So return -1
           {
               LOGGER.debug("Success for if(!this.PAYLOAD_OBJECT.isPayloadObjectComplete())" + this.PAYLOAD_OBJECT.isPayloadObjectComplete());
               LOGGER.debug("Bailing out from getPayloadObjectType() method with no payload object error.");
               return com.libDevNJ.DevNI.PAYLOAD_ERROR_RETURN;
           }
       }
       LOGGER.debug("Bailing out from getPayloadObjectType() method with payload object type.");
       return this.PAYLOAD_OBJECT.getPayloadObjectType();
    }
    /**
     * Returns the payload's abstractpayloadobject object if the payload contains any.
     * @return The abstractpayloadobject of the payload
     */
    public AbstractPayloadObject getPayloadObject()
    {
        LOGGER.debug("In getPayloadObject() method.");
        LOGGER.debug("VARIABLES: PAYLOAD_OBJECT:" + PAYLOAD_OBJECT);
        if(this.CONTAINS_OBJECT)
        {
            LOGGER.debug("Success for: if(this.CONTAINS_OBJECT)" + (this.CONTAINS_OBJECT));
            LOGGER.debug("Vailing out from getPayloadObject() with the AbstractPayloadObject.");
            return this.PAYLOAD_OBJECT;
        }
        LOGGER.debug("Bailing out from getPayloadObjectType() method with null.");
        return null;
    }
    /**
     * Method puts the AbstractPayloadObject object into the payload and sets the payload type to the type of payload object type.<br>
     * Method checks if the payload is already marked complete.<br>
     * If payload is complete, it will not accept any more input unless the payload is marked to be not complete explicitly.<br>
     * @param object The abstract payload object needed to be sent.
     * @throws com.libDevNJ.DevNJException 
     */
    public void setPayloadObject(com.libDevNJ.AbstractPayloadObject object) throws com.libDevNJ.DevNJException
    {
        LOGGER.debug("In setPayloadObject(com.libDevNJ.AbstractPayloadObject object) method.");
        if(this.isPayloadComplete()) // first check that payload is not marked complete
        {
            LOGGER.debug("Unable to save object to payload. Payload object " + this.getPayloadID() + " is marked complete.");
            LOGGER.debug("Bailing out from setPayloadObject(com.libDevNJ.AbstractPayloadObject object) method with exception.");
            throw new com.libDevNJ.DevNJException("Payload is already marked complete.", com.libDevNJ.DevNI.DevNJException_PAYLOAD_ALREADY_MARKED_COMPLETE);
        }
        if(object.isPayloadObjectComplete())
        {
            LOGGER.debug("Setting payload's CONTAINS_OBJECT=true");
            this.CONTAINS_OBJECT = true;
            LOGGER.debug("Saving the object to payload.");
            this.PAYLOAD_OBJECT = object;
            LOGGER.debug("Setting the payload type same as payload's object type.");
            this.PAYLOAD_TYPE = this.PAYLOAD_OBJECT.getPayloadObjectType();
        }
        else
        {
            LOGGER.debug("Setting payload's CONTAINS_OBJECT=false");
            this.CONTAINS_OBJECT = false;
            LOGGER.debug("Saving null to payload's object.");
            this.PAYLOAD_OBJECT = null;
            LOGGER.debug("Setting payload type to control.");
            this.PAYLOAD_TYPE = com.libDevNJ.DevNI.PAYLOAD_TYPE_CONTROL;
            LOGGER.debug("Bailing out from setPayloadObject(com.libDevNJ.AbstractPayloadObject object) method with payload object not complete exception.");
            throw new com.libDevNJ.DevNJException("Payload's object is still not marked complete.",com.libDevNJ.DevNI.DevNJException_PAYLOAD_OBJECT_NOT_COMPLETE);
        }
        LOGGER.debug("Bailing out from setPayloadObject(com.libDevNJ.AbstractPayloadObject object) method.");
    }
    /**
     * This method sets the actual string to be put in the Payload.
     * Calling this method against same object appends the message string in the same Payload obect
     * @param message The string message to be set in the Payload
     */
    public void setPayloadString(String message) throws com.libDevNJ.DevNJException
    {
        LOGGER.debug("In setPayloadString(String message) method.");
        if(this.isPayloadComplete()) // first check that payload is not marked complete
        {
            LOGGER.debug("Payload object " + this.getPayloadID() + " is marked complete.");
            throw new com.libDevNJ.DevNJException("Cannot put string in payload. Object is already marked complete.", com.libDevNJ.DevNI.DevNJException_PAYLOAD_ALREADY_MARKED_COMPLETE);
        }
        if(message.equals("")) {
            LOGGER.debug("PARAMETER: message is null.");
        }
        LOGGER.debug("Appending string: " + message + " to payload's string: " + this.PAYLOAD_STRING);
        StringBuilder sb = new StringBuilder();
        sb.append(this.PAYLOAD_STRING).append(message);
        LOGGER.debug("Setting payload's CONTAINS_STRING=true");
        this.CONTAINS_STRING = true;
        if(!this.CONTAINS_OBJECT) // payload contains no object, so mark it as control payload by default
        {
            LOGGER.debug("Payload does not contain object. Setting payload type to control.");
            this.PAYLOAD_TYPE = com.libDevNJ.DevNI.PAYLOAD_TYPE_CONTROL;
        }
        LOGGER.debug("Bailing out from setPayloadString(String message) method after saving string.");
    }    
}
