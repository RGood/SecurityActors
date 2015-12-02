/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package securitylineactors;

import akka.actor.ActorRef;
import akka.actor.UntypedActor;
import java.util.ArrayList;

/**
 *
 * @author Randall
 */
public class SecurityActor extends UntypedActor{
    String id;
    private final ActorRef baggageScanner;
    private final ActorRef bodyScanner;
    private final ActorRef jailActor;
    private final ArrayList<Object[]> bodyList;
    private final ArrayList<Boolean> baggageList;
    
    public SecurityActor(String id, ActorRef bagS, ActorRef bodyS, ActorRef jA){
        this.id = id;
        baggageScanner = bagS;
        bodyScanner = bodyS;
        jailActor = jA;
        bodyList = new ArrayList<>();
        baggageList = new ArrayList<>();
    }

    @Override
    public void onReceive(Object o) throws Exception {
        
        //If sender is baggageScanner
        if(getSender() == baggageScanner){
            
            //Add result to baggage list
            System.out.println("Security " + id + " receives a report.");
            boolean bag = (boolean) o;
            baggageList.add(bag);
            
        //If sender is bodyScanner
        }else if(getSender() == bodyScanner){
            
            //Add result to body list
            System.out.println("Security " + id + " receives a body report.");
            Object[] passenger = (Object[]) o;
            bodyList.add(passenger);
        }
        
        //Remove body and bag pairs
        while(baggageList.size() > 0 && bodyList.size() > 0){
            boolean tempBag = baggageList.remove(0);
            Object[] tempPassenger = bodyList.remove(0);
            Passenger p = (Passenger) tempPassenger[1];
            
            //Check report if valid, let through
            if(tempBag && (boolean) tempPassenger[0]){
                System.out.println("Security " + id + " lets Passenger " + p.getID() + " through.");
                
            //if invalid, send to jail
            }else{
                System.out.println("Security " + id + " sends Passenger " + p.getID() + " to jail.");
                jailActor.tell(tempPassenger[1], null);
            }
        }
    }
    
}
