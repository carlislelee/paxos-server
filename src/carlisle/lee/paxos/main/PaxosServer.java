/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package carlisle.lee.paxos.main;

import carlisle.lee.paxos.comm.AcceptResponce;
import carlisle.lee.paxos.comm.Config;
import carlisle.lee.paxos.comm.PrepareResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.AbstractHandler;

/**
 *
 * @author Carlisle
 */
public class PaxosServer extends AbstractHandler {

    AtomicLong epoch = new AtomicLong(0);

    @Override
    public void handle(String string, Request baseReq, HttpServletRequest hsr, HttpServletResponse response) throws IOException, ServletException {
        baseReq.setHandled(true);
        String cliIp = baseReq.getRemoteHost();
        int cliPort = baseReq.getRemotePort();
        System.out.println("Receive request from " + cliIp + ":" + cliPort);

        String type = baseReq.getParameter("type");
        switch (type) {
            case "propose":
                try {
                    Long proposalId = epoch.incrementAndGet() + (Long) Config.getConf(Config.NODE_NUM_KEY);
                    String proposalVal = baseReq.getHeader("value");
                    PrepareResponse presp = RequestSender.prepare();
                    if(!presp.ifMajorPrepared()){
                        response.setStatus(HttpServletResponse.SC_ACCEPTED);
                        response.getWriter().println("less than major prepared.");
                        return;
                    }
                    proposalVal = presp.getRetValue()==null?proposalVal:presp.getRetValue();
                    AcceptResponce accResponse = RequestSender.accept(proposalVal);
                    
                } catch (Exception e) {

                } finally {

                }
                break;
            case "prepare":
                break;
            case "accept":
                break;
            case "heartbeat":
                break;
            default:
                break;
        }
        String name = baseReq.getHeader("name");
        System.out.println("Header:");
        System.out.println("name=" + name);
        ServletInputStream sis = baseReq.getInputStream();
        ByteArrayOutputStream b = new ByteArrayOutputStream();
        byte[] bb = new byte[2048];
        int i = 0;
        while ((i = sis.read(bb, 0, 2048)) > 0) {
            b.write(bb, 0, i);
        }
        String content = new String(b.toByteArray());
        System.out.println("content:" + content);
        response.setStatus(HttpServletResponse.SC_OK);
        response.getWriter().println("ok");
        System.out.println("----------------------------");
    }
}
