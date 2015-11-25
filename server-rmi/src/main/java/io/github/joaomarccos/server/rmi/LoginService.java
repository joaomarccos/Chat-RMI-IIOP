package io.github.joaomarccos.server.rmi;

import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author joaomarcos
 */
public class LoginService {
    private final Set<AcessTO> activeUsers;

    public LoginService() {
        this.activeUsers = new HashSet<>();
    }
    
    public void login(AcessTO user){
        this.activeUsers.add(user);
    }
    
    public void logout(AcessTO user){
        this.activeUsers.remove(user);
    }
    
    public boolean isLogged(AcessTO user){
        return this.activeUsers.contains(user);
    }
    
    public int numberOfUsers(){
        return this.activeUsers.size();
    }
    
}
