/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package securitylineactors;

import akka.actor.ActorRef;
import akka.actor.UntypedActor;

/**
 *
 * @author Randall
 */
class LineActor extends UntypedActor {
    private String id;
    private ActorRef bagScanActor;
    private ActorRef bodyScanActor;
    
    public LineActor(String id, ActorRef bagSA, ActorRef bodySA){
        this.id = id;
        bagScanActor = bagSA;
        bodyScanActor = bodySA;
    }

    @Override
    public void onReceive(Object o) throws Exception {
        //Set passenger
        Passenger p = (Passenger) o;
        
        //Logging
        System.out.println("Passenger " + p.getID() + " arrives in line " + id);
        
        //Send bags
        System.out.println("Passenger " + p.getID() + " baggage sent to scanner.");
        bagScanActor.tell(p.getBaggage(), null);
        
        //Send body
        System.out.println("Passenger " + p.getID() + " bddy sent to scanner.");
        bodyScanActor.tell(p, null);
    }
    
}
