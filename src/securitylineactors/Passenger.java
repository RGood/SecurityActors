/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package securitylineactors;

/**
 *
 * @author Randall
 */
public class Passenger {
    private final boolean document, baggage, body;
    private final String id;
    
    public Passenger(String id, boolean d, boolean ba, boolean bo){
        this.id = id;
        document = d;
        baggage = ba;
        body = bo;
    }
    
    public String getID(){
        return id;
    }
    
    public boolean getDocument(){
        return document;
    }
    
    public boolean getBaggage(){
        return baggage;
    }
    
    public boolean getBody(){
        return body;
    }
}
