package com.travelport.core.pojo;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;
import org.apache.sling.api.resource.Resource;

@Model(adaptables =
        {SlingHttpServletRequest.class, Resource.class},
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class ProductItems {

    @ValueMapValue
    private String productId;
    @ValueMapValue
    private String productName;
    @ValueMapValue
    private String productDescription;
    @ValueMapValue
    private String productPrice;
    @ValueMapValue
    private String productImage;
    @ValueMapValue
    private String productCategory;

    public String getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public String getProductImage() {
        return productImage;
    }

    public String getProductCategory() {
        return productCategory;
    }
}
