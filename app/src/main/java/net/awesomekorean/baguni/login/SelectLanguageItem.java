package net.awesomekorean.baguni.login;

import android.graphics.drawable.Drawable;

public class SelectLanguageItem {

    private Drawable flag;
    private String nation;
    private Drawable checked;

    public Drawable getFlag() {
        return flag;
    }

    public void setFlag(Drawable flag) {
        this.flag = flag;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public Drawable getChecked() {
        return checked;
    }

    public void setChecked(Drawable checked) {
        this.checked = checked;
    }

}
