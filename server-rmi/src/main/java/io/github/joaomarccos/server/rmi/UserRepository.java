package io.github.joaomarccos.server.rmi;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author joaomarcos
 */
public class UserRepository {

    private final Map<CredentialTO, AcessTO> users;    

    public UserRepository() {
        this.users = new HashMap<>();
    }

    public void addUser(CredentialTO credentialTO, AcessTO acessTO) {
        this.users.put(credentialTO, acessTO);
    }

    public Map<CredentialTO, AcessTO> getRepository() {
        return users;
    }

    public boolean exists(CredentialTO credentialTO) {
        return this.users.containsKey(credentialTO);
    }

}
