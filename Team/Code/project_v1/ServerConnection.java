import java.net.*;
import java.util.*;
import java.io.IOException;
import org.json.* ;
import org.restlet.representation.* ;
import org.restlet.ext.jackson.* ;
import org.restlet.resource.* ;
import org.restlet.ext.json.*;


/**
 * Write a description of class ServerConnection here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ServerConnection  
{
    // instance variables - replace the example below with your own
    private String ipAddress = "10.0.0.175";

    /**
     * Constructor for objects of class ServerConnection
     */
    public ServerConnection()
    {
    }

   public ClientResource getServerConnection(String urlPath){
       //Get the server connection using the ip
       ClientResource helloClientresource = new ClientResource(urlPath); 
       return helloClientresource;
   }
}
