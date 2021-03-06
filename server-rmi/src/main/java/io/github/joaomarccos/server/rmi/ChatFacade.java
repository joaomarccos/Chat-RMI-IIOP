package io.github.joaomarccos.server.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

/**
 *
 * @author joaomarcos
 */
public interface ChatFacade extends Remote {

    public AcessTO login(CredentialTO to) throws RemoteException;

    public void send(AcessTO aTo, MessageTO mTo) throws RemoteException;

    public List<MessageTO> receiveAll(AcessTO to) throws RemoteException;

    public QuantityTO howMany(AcessTO to) throws RemoteException;

    public void logout(AcessTO to) throws RemoteException;
    
}
