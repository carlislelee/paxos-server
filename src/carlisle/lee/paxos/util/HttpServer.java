/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package carlisle.lee.paxos.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.AbstractHandler;
import org.eclipse.jetty.server.handler.ContextHandler;
import org.eclipse.jetty.server.nio.SelectChannelConnector;

/**
 *
 * @author lizhaoxi
 */
public class HttpServer {

    public static void start(String ip,int port,String path,AbstractHandler handler) throws InterruptedException, Exception {
        Connector connector = new SelectChannelConnector();
        connector.setHost(ip);
        connector.setPort(port);

        Server s = new Server();
        s.setConnectors(new Connector[]{connector});

        ContextHandler ch = new ContextHandler("/"+path);
        ch.setHandler(handler);
        s.setHandler(ch);

        s.start();
        s.join();
    }
   
}
