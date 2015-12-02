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
public class DocumentCheckerActor extends UntypedActor{
    
    private ArrayList<ActorRef> lines;
    private int curLine;
    
    public DocumentCheckerActor(ArrayList<ActorRef> l){
        curLine = 0;
        lines = l;
    }

    @Override
    public void onReceive(Object o) throws Exception {
        //Get passenger message
        Passenger p = (Passenger) o;
        
        //Check their document
        if(p.getDocument()){
            
            //Log line sent to and increment
            System.out.println("Passenger " + p.getID() + " sent to line " + String.valueOf(curLine));
            curLine++;curLine%=lines.size();
            
            //Send to line
            lines.get(curLine).tell(p, null);
        }else{
            
            //Log invalid document.
            System.out.println("Passenger " + p.getID() + " turned away.");
        }
    }
}
