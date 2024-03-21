/*
//API 응답(json)을 자바 객체로 매핑하는 클래스
package com.subway.railme.home.API;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import javax.annotation.processing.Generated;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "errorMessage",
        "realtimeArrivalList"
})
@Generated("jsonschema2pojo")
public class ApiResponseModel<C extends Iterable<E>, E> {

    @JsonProperty("errorMessage")
    private ErrorMessage errorMessage;
    @JsonProperty("realtimeArrivalList")
    private List<RealtimeArrival> realtimeArrivalList;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("errorMessage")
    public ErrorMessage getErrorMessage() {
        return errorMessage;
    }

    @JsonProperty("errorMessage")
    public void setErrorMessage(ErrorMessage errorMessage) {
        this.errorMessage = errorMessage;
    }

    @JsonProperty("realtimeArrivalList")
    public List<RealtimeArrival> getRealtimeArrivalList() {
        return realtimeArrivalList;
    }

    @JsonProperty("realtimeArrivalList")
    public void setRealtimeArrivalList(List<RealtimeArrival> realtimeArrivalList) {
        this.realtimeArrivalList = realtimeArrivalList;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(ApiResponseModel.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("errorMessage");
        sb.append('=');
        sb.append(((this.errorMessage == null)?"<null>":this.errorMessage));
        sb.append(',');
        sb.append("realtimeArrivalList");
        sb.append('=');
        sb.append(((this.realtimeArrivalList == null)?"<null>":this.realtimeArrivalList));
        sb.append(',');
        sb.append("additionalProperties");
        sb.append('=');
        sb.append(((this.additionalProperties == null)?"<null>":this.additionalProperties));
        sb.append(',');
        if (sb.charAt((sb.length()- 1)) == ',') {
            sb.setCharAt((sb.length()- 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }

}

*/
