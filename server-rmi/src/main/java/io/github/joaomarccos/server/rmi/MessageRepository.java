package io.github.joaomarccos.server.rmi;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author joaomarcos
 */
public class MessageRepository {

    private final Map<AcessTO, List<MessageTO>> messages;

    public MessageRepository() {
        this.messages = new HashMap<>();
    }

    public void addMessage(MessageTO messageTO) {
        for (AcessTO user : messages.keySet()) {
            messages.get(user).add(messageTO);
        }
    }

    public List<MessageTO> getMessageTOs(AcessTO acessTO) {
        if (!this.messages.containsKey(acessTO)) {
            List<MessageTO> msgs = new ArrayList<>();
            messages.put(acessTO, msgs);
        }
        List<MessageTO> unreadMessages = new ArrayList<>();
        unreadMessages.addAll(messages.get(acessTO));
        messages.get(acessTO).clear();
        return unreadMessages;
    }

}
