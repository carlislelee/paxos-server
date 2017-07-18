/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package carlisle.lee.paxos.comm;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;

/**
 *
 * @author lizhaoxi
 */
public class Config {
    
    public static final String CLUSTER_LIST_KEY = "cluster_list";
    public static final String NODE_NUM_KEY = "node_id";

    static HashMap<String, Object> map = new HashMap<>();
    
    public static void load(String filename) throws FileNotFoundException, IOException {
        FileInputStream fis = new FileInputStream(filename);
        Properties pro = new Properties();
        pro.load(fis);
        for (Object key : pro.keySet()) {
            map.put((String) key, pro.get(key));
        }
        if(!(map.containsKey(CLUSTER_LIST_KEY)&&map.containsKey(NODE_NUM_KEY))){
            System.exit(1);
        }
    }
    
    public static Object getConf(String confKey){
        if (!map.containsKey(confKey)){
            return null;
        }
        return map.get(confKey);
    }
}
