/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package carlisle.lee.paxos.main;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Carlisle
 */
public class DataMaintainer {

    static Map<String, Map.Entry<Long, String>> data = new HashMap<>();

    public static Long getEpoch(String key) {
        if (!data.containsKey(key)) {
            return -1L;
        }
        return data.get(key).getKey();
    }

    public static String getValue(String key) {
        if (!data.containsKey(key)) {
            return null;
        }
        return data.get(key).getValue();
    }

    public static void updateValue(String key) {

    }
}
