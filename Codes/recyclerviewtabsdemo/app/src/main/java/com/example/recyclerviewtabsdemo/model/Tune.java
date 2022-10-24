package com.example.recyclerviewtabsdemo.model;

public class Tune {
    String tuneName;
    int tunePic;

    public Tune(String tuneName, int tunePic) {
        this.tuneName = tuneName;
        this.tunePic = tunePic;
    }

    public String getTuneName() {
        return tuneName;
    }

    public void setTuneName(String tuneName) {
        this.tuneName = tuneName;
    }

    public int getTunePic() {
        return tunePic;
    }

    public void setTunePic(int tunePic) {
        this.tunePic = tunePic;
    }
}
