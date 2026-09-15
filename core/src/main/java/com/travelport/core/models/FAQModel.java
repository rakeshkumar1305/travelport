package com.travelport.core.models;

import com.travelport.core.pojo.FaqItems;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ChildResource;
import java.util.List;

@Model(adaptables = {SlingHttpServletRequest.class, Resource.class},
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class FAQModel {

    @ChildResource
    private List<FaqItems> faqItems;

    public List<FaqItems> getFaqItems() {
        return faqItems;
    }
}
