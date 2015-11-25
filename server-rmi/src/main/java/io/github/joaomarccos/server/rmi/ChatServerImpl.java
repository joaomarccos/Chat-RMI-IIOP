package io.github.joaomarccos.server.rmi;

import java.rmi.RemoteException;
import java.util.List;
import javax.rmi.PortableRemoteObject;

/**
 *
 * @author joaomarcos
 */
public class ChatServerImpl extends /*UnicastRemoteObject*/ PortableRemoteObject implements ChatFacade {

    private final UserRepository userRepository;
    private final LoginService loginService;
    private final MessageRepository messageRepository;

    public ChatServerImpl() throws RemoteException {
        super();
        this.userRepository = new UserRepository();
        this.loginService = new LoginService();
        this.messageRepository = new MessageRepository();
    }

    @Override
    public AcessTO login(CredentialTO credential) throws RemoteException {
        if (!userRepository.exists(credential)) {
            AcessTO token = generateToken(credential);
            userRepository.addUser(credential, token);
            loginService.login(token);
            return token;
        } else {
            AcessTO token = userRepository.getRepository().get(credential);
            loginService.login(token);
            return token;
        }
    }

    @Override
    public void send(AcessTO aTo, MessageTO mTo) throws RemoteException {
        if (loginService.isLogged(aTo)) {
            this.messageRepository.addMessage(mTo);
        }
    }

    @Override
    public List<MessageTO> receiveAll(AcessTO to) throws RemoteException {
        if (loginService.isLogged(to)) {
            return this.messageRepository.getMessageTOs(to);
        }

        return null;
    }

    @Override
    public QuantityTO howMany(AcessTO to) throws RemoteException {
        if (loginService.isLogged(to)) {
            QuantityTO qtt = new QuantityTO();
            qtt.setQuantity(this.loginService.numberOfUsers());
            return qtt;
        }
        
        return null;
    }

    @Override
    public void logout(AcessTO to) throws RemoteException {
        loginService.logout(to);
    }

    private AcessTO generateToken(CredentialTO credential) {
        AcessTO newAcessTO = new AcessTO();
        newAcessTO.setToken(credential.getUserName() + System.currentTimeMillis());
        return newAcessTO;
    }

}
