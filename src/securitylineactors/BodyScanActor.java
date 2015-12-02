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
public class BodyScanActor extends UntypedActor {
    private final String id;
    private final ActorRef securityActor;
    
    public BodyScanActor(String id, ActorRef sA){
        this.id = id;
        securityActor = sA;
    }

    @Override
    public void onReceive(Object o) throws Exception {
        //Get passenger
        Passenger p = (Passenger) o;
        
        //Log
        System.out.println("Passenger " + p.getID() + " arrives at Body Scanner " + id);
        
        //Create object array list to store passenger and result
        //I know, I know.
        Object[] result = new Object[2];
        result[0] = p.getBody();
        result[1] = p;
        
        //Log and send result to security.
        System.out.println("BodyScanner " + id + " sends result to Security.");
        securityActor.tell(result, getSelf());
    }
    
}
