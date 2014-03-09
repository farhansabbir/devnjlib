package com.libDevNJ;

import java.io.File;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 *
 * @author fsabbir
 */
public class RequestEngine implements Runnable{
    org.apache.log4j.Logger LOGGER = org.apache.log4j.Logger.getLogger(com.libDevNJ.RequestEngine.class);
    private ObjectInputStream IN;
    private ObjectOutputStream OUT;
    private boolean REQUEST_COMPLETE;
    private Thread THREAD;
    /**
     * 
     * @param in 
     */
    public RequestEngine(ObjectInputStream in)
    {
        this.REQUEST_COMPLETE = false;
        this.IN = in;
    }
    /**
     * 
     * @param out 
     */
    public RequestEngine(ObjectOutputStream out)
    {
        this.REQUEST_COMPLETE = false;
        this.OUT = out;
    }
    /**
     * 
     * @param out
     * @param in 
     */
    public RequestEngine(ObjectOutputStream out, ObjectInputStream in)
    {
        this.REQUEST_COMPLETE = false;
        this.OUT = out;
        this.IN = in;
        this.start();
    }
    /**
     * 
     */
    private void start()
    {
        this.THREAD = new Thread(this);        
        this.THREAD.start();
    }
    /**
     * 
     */
    public void run()
    {
        while(!this.REQUEST_COMPLETE)
        {
            
        }
    }
    public void stopRequesrEngine()
    {
        this.REQUEST_COMPLETE = true;
    }
            
}
