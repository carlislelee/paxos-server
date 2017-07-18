/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package carlisle.lee.paxos.comm;

/**
 *
 * @author lizhaoxi
 */
public class PrepareResponse {
    boolean majorPrepared = false;
    String retValue = null;

    public PrepareResponse(boolean majorPrepared,String value) {
        this.majorPrepared = majorPrepared;
        this.retValue = value;
    }
    
    public boolean ifMajorPrepared(){
        return majorPrepared;
    }
    
    public String getRetValue(){
        return retValue;
    }
}
