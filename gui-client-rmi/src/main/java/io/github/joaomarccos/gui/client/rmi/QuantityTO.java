package io.github.joaomarccos.gui.client.rmi;

import java.io.Serializable;

/**
 *
 * @author joaomarcos
 */
public class QuantityTO implements Serializable{

    private int quantity;

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

}
