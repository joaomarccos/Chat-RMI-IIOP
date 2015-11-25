package io.github.joaomarccos.server.rmi;

import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author joaomarcos
 */
public class AcessTO implements Serializable {

    private String token;
    private boolean haveMessage;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public boolean isHaveMessage() {
        return haveMessage;
    }

    public void setHaveMessage(boolean haveMessage) {
        this.haveMessage = haveMessage;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 89 * hash + Objects.hashCode(this.token);
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
        final AcessTO other = (AcessTO) obj;
        if (!Objects.equals(this.token, other.token)) {
            return false;
        }
        return true;
    }

}
