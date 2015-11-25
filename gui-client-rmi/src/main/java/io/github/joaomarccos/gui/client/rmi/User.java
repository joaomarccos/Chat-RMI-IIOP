package io.github.joaomarccos.gui.client.rmi;


/**
 *
 * @author joaomarcos
 */
public class User {

    private String name;
    private String token;

    public User(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public CredentialTO toCredentialTO() {
        CredentialTO to = new CredentialTO();
        to.setUserName(name);
        return to;
    }

    public AcessTO toAcessTO() {
        AcessTO to = new AcessTO();
        to.setToken(token);
        return to;
    }
}
