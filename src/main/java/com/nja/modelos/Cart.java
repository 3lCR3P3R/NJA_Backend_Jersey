package com.nja.modelos;

public class Cart {

    private int ca_id;
    private int po_id;
    private int us_id;

    public Cart() {
    }

    public Cart(int ca_id, int po_id, int us_id) {
        this.ca_id = ca_id;
        this.po_id = po_id;
        this.us_id = us_id;
    }

    public int getCa_id() {
        return ca_id;
    }

    public void setCa_id(int ca_id) {
        this.ca_id = ca_id;
    }

    public int getPo_id() {
        return po_id;
    }

    public void setPo_id(int po_id) {
        this.po_id = po_id;
    }

    public int getUs_id() {
        return us_id;
    }

    public void setUs_id(int us_id) {
        this.us_id = us_id;
    }
}
