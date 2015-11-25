package io.github.joaomarccos.client.rmi;

import io.github.joaomarccos.contrato.rmi.MessageTO;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author joaomarcos
 */
public class Message {

    private String text;
    private LocalDateTime date;

    public Message() {
    }

    public Message(String text, LocalDateTime date) {
        this.text = text;
        this.date = date;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public void fromMessageTo(MessageTO to) {
        this.text = to.getText();
        this.date = to.getDate();
    }

    public MessageTO toMessageTO() {
        MessageTO to = new MessageTO();
        to.setText(text);
        to.setDate(date);
        return to;
    }

    @Override
    public String toString() {
        return date.format(DateTimeFormatter.ISO_LOCAL_TIME)+"\n"+text+"\n\n"; 
    }
        

}
