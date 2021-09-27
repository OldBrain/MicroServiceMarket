
package ru.geekbrains.trainingproject.market.soap;

import javax.xml.bind.annotation.*;


public class GetProductByTitleRequest {

    @XmlElement(required = true)
    protected String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String value) {
        this.title = value;
    }

}
