/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package securitylineactors;

import akka.actor.UntypedActor;
import java.util.ArrayList;

/**
 *
 * @author Randall
 */
public class JailActor extends UntypedActor{
    private ArrayList<Passenger> jail;
    
    public JailActor(){
        jail = new ArrayList<>();
    }
    
    @Override
    public void onReceive(Object o) throws Exception {
        //Get detainee and log
        Passenger p = (Passenger) o;
        System.out.println("Jail receives Passenger " + p.getID());
        
        //Add to jail and log
        System.out.println("Jail locks away Passenger " + p.getID());
        jail.add(p);
    }
    
    //Return inmates
    public ArrayList<Passenger> getInmates(){
        return jail;
    }
    
}
