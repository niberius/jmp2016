package org.zoltor.provider.jsonplaceholder.entity;

import org.zoltor.communicator.annotation.JsonItem;

/**
 * Created by zoltor on 11/07/16.
 */
public class Company extends BaseEntity {

    @JsonItem("name")
    private String name;

    @JsonItem("catchPhrase")
    private String catchPhrase;

    @JsonItem("bs")
    private String bs;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCatchPhrase() {
        return catchPhrase;
    }

    public void setCatchPhrase(String catchPhrase) {
        this.catchPhrase = catchPhrase;
    }

    public String getBs() {
        return bs;
    }

    public void setBs(String bs) {
        this.bs = bs;
    }
}
