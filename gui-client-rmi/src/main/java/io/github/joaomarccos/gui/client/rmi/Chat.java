package io.github.joaomarccos.gui.client.rmi;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 *
 * @author joaomarcos
 */
public final class Chat {

    private ChatFacade facade;

    public Chat() {
        tryConnect();
    }

    public boolean tryConnect() {
        try {
//            Registry registry = LocateRegistry.getRegistry("localhost", 11111);
//            ChatFacade facade = (ChatFacade) registry.lookup("server-chat");

            System.out.println("tentando conectar");
            Properties props = new Properties();
            props.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.cosnaming.CNCtxFactory");
            props.put(Context.PROVIDER_URL, "iiop://localhost:1050");

            Context context = new InitialContext(props);
            facade = (ChatFacade) context.lookup("server-chat");
            return true;

        } catch (NamingException ex) {
            Logger.getLogger(Chat.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public void login(User user) throws NotConnectedToServerException { 
        try {
            AcessTO token = this.facade.login(user.toCredentialTO());
            user.setToken(token.getToken());
        } catch (RemoteException | NullPointerException ex) {
            throw new NotConnectedToServerException("Não conectado ao servidor: " + ex.getMessage());
        }

    }

    public void sendMessage(User user, Message message) throws NotConnectedToServerException {
        try {
            this.facade.send(user.toAcessTO(), message.toMessageTO());
        } catch (RemoteException | NullPointerException ex) {
            throw new NotConnectedToServerException("Não conectado ao servidor: " + ex.getMessage());
        }
    }

    public List<Message> receiveAll(User user) throws NotConnectedToServerException {
        List<Message> listOfMessages = new ArrayList<>();

        try {
            List<MessageTO> received = this.facade.receiveAll(user.toAcessTO());
            for (MessageTO m : received) {
                Message message = new Message();
                message.fromMessageTo(m);
                listOfMessages.add(message);
            }
        } catch (RemoteException | NullPointerException ex) {
            throw new NotConnectedToServerException("Não conectado ao servidor: " + ex.getMessage());
        }

        return listOfMessages;
    }

    public int getQuatityOfUsersConnected(User user) throws NotConnectedToServerException {
        try {
            return this.facade.howMany(user.toAcessTO()).getQuantity();
        } catch (RemoteException | NullPointerException ex) {
            throw new NotConnectedToServerException("Não conectado ao servidor: " + ex.getMessage());
        }
    }

    public void logout(User user) throws NotConnectedToServerException {
        try {
            this.facade.logout(user.toAcessTO());
        } catch (RemoteException | NullPointerException ex) {
            throw new NotConnectedToServerException("Não conectado ao servidor: " + ex.getMessage());
        }
    }

    public void ping() throws NotConnectedToServerException {
        try {
            Registry registry = LocateRegistry.getRegistry("localhost", 11111);
            registry.lookup("server-chat");
        } catch (RemoteException | NotBoundException ex) {
            throw new NotConnectedToServerException("Erro ao conectar");
        }
    }

}
