
package com.stackroute.foodieapp.helper;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "cuisine_id",
    "cuisine_name"
})
public class CuisineData {

    @JsonProperty("cuisine_id")
    private Integer cuisineId;
    @JsonProperty("cuisine_name")
    private String cuisineName;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("cuisine_id")
    public Integer getCuisineId() {
        return cuisineId;
    }

    @JsonProperty("cuisine_id")
    public void setCuisineId(Integer cuisineId) {
        this.cuisineId = cuisineId;
    }

    @JsonProperty("cuisine_name")
    public String getCuisineName() {
        return cuisineName;
    }

    @JsonProperty("cuisine_name")
    public void setCuisineName(String cuisineName) {
        this.cuisineName = cuisineName;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
