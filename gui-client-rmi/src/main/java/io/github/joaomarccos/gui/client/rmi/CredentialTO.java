package io.github.joaomarccos.gui.client.rmi;

import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author joaomarcos
 */
public class CredentialTO implements Serializable {

    private String userName;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.userName);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final CredentialTO other = (CredentialTO) obj;
        if (!Objects.equals(this.userName, other.userName)) {
            return false;
        }
        return true;
    }

}
