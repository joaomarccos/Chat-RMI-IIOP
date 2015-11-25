package io.github.joaomarccos.server.rmi;

import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 *
 * @author joaomarcos
 */
public class Main {

    public static void main(String[] args) {
//        try {
//            Registry registry = LocateRegistry.createRegistry(11111);
//            registry.bind("server-chat", new ChatServerImpl());
//        } catch (RemoteException | AlreadyBoundException ex) {
//            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
//        }

        try {
            System.setProperty(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.cosnaming.CNCtxFactory");
            System.setProperty(Context.PROVIDER_URL, "iiop://localhost:1050");
            
            Context initialNamingContext = new InitialContext();
            initialNamingContext.rebind("server-chat", new ChatServerImpl());
        } catch (NamingException | RemoteException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
