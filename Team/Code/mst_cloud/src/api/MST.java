package api ;

import java.util.HashMap;

public class MST {

    private static MST mst;
    private HashMap<String,Integer> map = new HashMap<String,Integer>();

    public static MST getInstance(){
	if(mst == null){
	    mst = new MST();
	    return mst;
	}else{
	    return mst;
	}
    }

    public HashMap<String, Integer> getMap(){
	return map;
    }
}

