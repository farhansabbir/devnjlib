package com.libDevNJ;
import java.io.*;
import java.net.*;
/**
 *
 * @author fsabbir
 */
public class Transceiver implements Runnable{
    org.apache.log4j.Logger LOGGER = org.apache.log4j.Logger.getLogger(com.libDevNJ.Transceiver.class);
    private Socket SOCKET;
    private Thread THREAD;
    private ObjectInputStream IN;
    private ObjectOutputStream OUT;
    private com.libDevNJ.RequestEngine REQUEST_ENGINE;
    private java.util.concurrent.ConcurrentHashMap<Integer, com.libDevNJ.RequestEngine> ENGINES; 
    /**
     * 
     * @param socket 
     */
    public Transceiver(Socket socket)
    {
        this.SOCKET = socket;
        this.THREAD = new Thread(this);
        
        this.start();
    }
    /**
     * 
     */
    private void start()
    {
        try
        {
            this.initializeReadObjectFromSocket();
        }
        catch(com.libDevNJ.DevNJException ex)
        {
                LOGGER.debug(ex);
                System.err.println(ex);
        }
        try
        {
            this.initializeWriteObjectToSocket();
        }
        catch(com.libDevNJ.DevNJException ex)
        {
                LOGGER.debug(ex);
                System.err.println(ex);
        }
        this.REQUEST_ENGINE = new com.libDevNJ.RequestEngine(this.OUT, this.IN);
        this.ENGINES = new java.util.concurrent.ConcurrentHashMap<Integer, com.libDevNJ.RequestEngine>();
        this.ENGINES.put(1, this.REQUEST_ENGINE);
        this.THREAD.start();
    }
    /**
     * 
     */
    public void run()
    {
        while(true)
        {
            
        }
    }
    /**
     * 
     * @throws com.libDevNJ.DevNJException 
     */
    private void initializeReadObjectFromSocket()  throws com.libDevNJ.DevNJException
    {
        try
        {
            this.IN = new ObjectInputStream(this.SOCKET.getInputStream());
        }
        catch(IOException ioex)
        {
            throw new com.libDevNJ.DevNJException("Unable to read content from socket connected: " + this.SOCKET.getInetAddress() + ";" + ioex, com.libDevNJ.DevNI.DevNJException_PAYLOAD_SOCKET_READ_ERROR);
        }
    }
    /**
     * 
     * @throws com.libDevNJ.DevNJException 
     */
    private void initializeWriteObjectToSocket() throws com.libDevNJ.DevNJException
    {
        try
        {
            this.OUT = new ObjectOutputStream(this.SOCKET.getOutputStream());
        }
        catch(IOException ioex)
        {
            throw new com.libDevNJ.DevNJException("Unable to write content from socket connected: " + this.SOCKET.getInetAddress() + ";" + ioex, com.libDevNJ.DevNI.DevNJException_PAYLOAD_SOCKET_READ_ERROR);
        }
    }
    public void sendFile(String filename)
    {
        com.libDevNJ.RequestEngine engine = new com.libDevNJ.RequestEngine(OUT);
    }
    public void sendString(String string)
    {
        
    }
    
}
