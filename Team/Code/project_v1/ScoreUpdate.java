import java.net.*;
import java.util.*;
import java.io.IOException;
import org.json.* ;
import org.restlet.representation.* ;
import org.restlet.ext.jackson.* ;
import org.restlet.resource.* ;
import org.restlet.ext.json.* ;

public class ScoreUpdate{

  static String ipAddress = "192.168.43.9";
    public static void main(String[] args){
        System.out.println(getScore());
        Score score = new Score();  
        score.setId("Vikas");
        score.setScore(10);
        setScore(score);
        System.out.println(getScore());
        
    }

public static HashMap<String,Score> getScore() 
{
    try {
        ClientResource helloClientresource = new ClientResource("http://"+ipAddress+":8080/mst"); 
        Representation result = helloClientresource.get();
        JacksonRepresentation<HashMap> inputRep = new JacksonRepresentation<HashMap> ( result, HashMap.class ) ;
        HashMap mp = inputRep.getObject();
        Iterator it = mp.entrySet().iterator();
        HashMap<String, Score> map = new HashMap<String, Score>();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            JacksonRepresentation<Score> tempRep = new JacksonRepresentation<Score>( new JacksonRepresentation(pair.getValue()), Score.class ) ;
            map.put((String)pair.getKey(), tempRep.getObject());
            it.remove(); // avoids a ConcurrentModificationException
        }
        //return (HashMap<String,HashMap<String,Object>>)inputRep.getObject();
        return map;
    } catch (Exception e){
        e.printStackTrace();
    }
    return null;
}

public static void setScore(Score score) 
{
    try {
        ClientResource helloClientresource = new ClientResource("http://"+ipAddress+":8080/mst");
        Representation result = helloClientresource.post(new JacksonRepresentation<Score>(score));
        System.out.println(result.toString());
    } catch (Exception e){
        e.printStackTrace();
    }  
}
}