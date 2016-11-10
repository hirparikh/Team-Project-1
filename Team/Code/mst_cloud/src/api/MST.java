package api ;

import java.util.HashMap;

public class MST {

    private static MST mst;
    private HashMap<String,Score> map = new HashMap<String,Score>();

    public static MST getInstance(){
	if(mst == null){
	    mst = new MST();
	    return mst;
	}else{
	    return mst;
	}
    }

    public HashMap<String, Score> getMap(){
	return map;
    }
}

