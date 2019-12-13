package com.experthealth.pharmacy.ms.model;

import java.io.Serializable;

public class OpenClose implements Serializable {
    private String open;
    private String close;

    public void setOpen(String open) {
        this.open = open;
    }

    public void setClose(String close) {
        this.close = close;
    }
}
