package net.msonic.testsessionws;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by manuelzegarra on 19/06/16.
 */

public class Session {

    private Session(){ }

    private static Session singleton = new Session( );

    public static Session getInstance( ) {
        return singleton;
    }


    public Map<String,String> valores = new HashMap<String,String>();

}
