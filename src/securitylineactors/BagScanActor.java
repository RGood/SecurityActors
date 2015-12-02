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
public class BagScanActor extends UntypedActor{
    private final String id;
    private final ActorRef securityActor;
    
    public BagScanActor(String id, ActorRef sA){
        this.id = id;
        securityActor = sA;
    }

    @Override
    public void onReceive(Object o) throws Exception {
        //Get bag and log
        boolean bag = (boolean) o;
        System.out.println("Bag Scanner " + id + " scans a bag.");
        
        //Bag is already boolean result, so send that to security
        System.out.println("Bag Scanner " + id + " sends result to Security.");
        securityActor.tell(bag, getSelf());
    }
    
}
