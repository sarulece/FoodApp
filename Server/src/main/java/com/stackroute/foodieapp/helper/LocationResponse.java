
package com.stackroute.foodieapp.helper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "location_suggestions",
    "status",
    "has_more",
    "has_total"
})
public class LocationResponse {

    @JsonProperty("location_suggestions")
    private List<LocationSuggestion> locationSuggestions = null;
    @JsonProperty("status")
    private String status;
    @JsonProperty("has_more")
    private Integer hasMore;
    @JsonProperty("has_total")
    private Integer hasTotal;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("location_suggestions")
    public List<LocationSuggestion> getLocationSuggestions() {
        return locationSuggestions;
    }

    @JsonProperty("location_suggestions")
    public void setLocationSuggestions(List<LocationSuggestion> locationSuggestions) {
        this.locationSuggestions = locationSuggestions;
    }

    @JsonProperty("status")
    public String getStatus() {
        return status;
    }

    @JsonProperty("status")
    public void setStatus(String status) {
        this.status = status;
    }

    @JsonProperty("has_more")
    public Integer getHasMore() {
        return hasMore;
    }

    @JsonProperty("has_more")
    public void setHasMore(Integer hasMore) {
        this.hasMore = hasMore;
    }

    @JsonProperty("has_total")
    public Integer getHasTotal() {
        return hasTotal;
    }

    @JsonProperty("has_total")
    public void setHasTotal(Integer hasTotal) {
        this.hasTotal = hasTotal;
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
