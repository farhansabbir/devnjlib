package com.libDevNJ;
import java.util.concurrent.*;

/**
 *
 * @author fsabbir
 */
public class PayloadProcessor {
    private java.util.concurrent.ConcurrentHashMap<String, com.libDevNJ.Payload> QUEUE;
    public PayloadProcessor()
    {
        
    }
    public void removeWritePayload(String payloadid)
    {
        this.QUEUE.remove(payloadid);
    }
}
