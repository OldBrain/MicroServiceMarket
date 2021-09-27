
package ru.geekbrains.trainingproject.market.soap;


import ru.geekbrains.trainingproject.market.dtos.soap.ProductsSoapDto;

import javax.xml.bind.annotation.*;

/**
 * <p>Java class for anonymous complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="product" type="{http://www.flamexander.com/spring/ws/products}products"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "product"
})
@XmlRootElement(name = "getProductByTitleResponse")
public class GetProductByTitleResponse {

    @XmlElement(required = true)
    protected ProductsSoapDto product;

    /**
     * Gets the value of the product property.
     *
     * @return possible object is
     * {@link ProductsSoapDto
     * }
     */
    public ProductsSoapDto getProduct() {
        return product;
    }

    /**
     * Sets the value of the product property.
     *
     * @param value allowed object is
     *              {@link ProductsSoapDto }
     */
    public void setProduct(ProductsSoapDto value) {
        this.product = value;
    }

}
