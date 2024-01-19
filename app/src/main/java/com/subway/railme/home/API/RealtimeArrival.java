package com.subway.railme.home.API;

import java.util.LinkedHashMap;
import java.util.Map;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "beginRow",
        "endRow",
        "curPage",
        "pageRow",
        "totalCount",
        "rowNum",
        "selectedCount",
        "subwayId",
        "subwayNm",
        "updnLine",
        "trainLineNm",
        "subwayHeading",
        "statnFid",
        "statnTid",
        "statnId",
        "statnNm",
        "trainCo",
        "trnsitCo",
        "ordkey",
        "subwayList",
        "statnList",
        "btrainSttus",
        "barvlDt",
        "btrainNo",
        "bstatnId",
        "bstatnNm",
        "recptnDt",
        "arvlMsg2",
        "arvlMsg3",
        "arvlCd"
})
@Generated("jsonschema2pojo")
public class RealtimeArrival {

    @JsonProperty("beginRow")
    private Object beginRow;
    @JsonProperty("endRow")
    private Object endRow;
    @JsonProperty("curPage")
    private Object curPage;
    @JsonProperty("pageRow")
    private Object pageRow;
    @JsonProperty("totalCount")
    private Integer totalCount;
    @JsonProperty("rowNum")
    private Integer rowNum;
    @JsonProperty("selectedCount")
    private Integer selectedCount;
    @JsonProperty("subwayId")
    private String subwayId;
    @JsonProperty("subwayNm")
    private Object subwayNm;
    @JsonProperty("updnLine")
    private String updnLine;
    @JsonProperty("trainLineNm")
    private String trainLineNm;
    @JsonProperty("subwayHeading")
    private Object subwayHeading;
    @JsonProperty("statnFid")
    private String statnFid;
    @JsonProperty("statnTid")
    private String statnTid;
    @JsonProperty("statnId")
    private String statnId;
    @JsonProperty("statnNm")
    private String statnNm;
    @JsonProperty("trainCo")
    private Object trainCo;
    @JsonProperty("trnsitCo")
    private String trnsitCo;
    @JsonProperty("ordkey")
    private String ordkey;
    @JsonProperty("subwayList")
    private String subwayList;
    @JsonProperty("statnList")
    private String statnList;
    @JsonProperty("btrainSttus")
    private String btrainSttus;
    @JsonProperty("barvlDt")
    private String barvlDt;
    @JsonProperty("btrainNo")
    private String btrainNo;
    @JsonProperty("bstatnId")
    private String bstatnId;
    @JsonProperty("bstatnNm")
    private String bstatnNm;
    @JsonProperty("recptnDt")
    private String recptnDt;
    @JsonProperty("arvlMsg2")
    private String arvlMsg2;
    @JsonProperty("arvlMsg3")
    private String arvlMsg3;
    @JsonProperty("arvlCd")
    private String arvlCd;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("beginRow")
    public Object getBeginRow() {
        return beginRow;
    }

    @JsonProperty("beginRow")
    public void setBeginRow(Object beginRow) {
        this.beginRow = beginRow;
    }

    @JsonProperty("endRow")
    public Object getEndRow() {
        return endRow;
    }

    @JsonProperty("endRow")
    public void setEndRow(Object endRow) {
        this.endRow = endRow;
    }

    @JsonProperty("curPage")
    public Object getCurPage() {
        return curPage;
    }

    @JsonProperty("curPage")
    public void setCurPage(Object curPage) {
        this.curPage = curPage;
    }

    @JsonProperty("pageRow")
    public Object getPageRow() {
        return pageRow;
    }

    @JsonProperty("pageRow")
    public void setPageRow(Object pageRow) {
        this.pageRow = pageRow;
    }

    @JsonProperty("totalCount")
    public Integer getTotalCount() {
        return totalCount;
    }

    @JsonProperty("totalCount")
    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    @JsonProperty("rowNum")
    public Integer getRowNum() {
        return rowNum;
    }

    @JsonProperty("rowNum")
    public void setRowNum(Integer rowNum) {
        this.rowNum = rowNum;
    }

    @JsonProperty("selectedCount")
    public Integer getSelectedCount() {
        return selectedCount;
    }

    @JsonProperty("selectedCount")
    public void setSelectedCount(Integer selectedCount) {
        this.selectedCount = selectedCount;
    }

    @JsonProperty("subwayId")
    public String getSubwayId() {
        return subwayId;
    }

    @JsonProperty("subwayId")
    public void setSubwayId(String subwayId) {
        this.subwayId = subwayId;
    }

    @JsonProperty("subwayNm")
    public Object getSubwayNm() {
        return subwayNm;
    }

    @JsonProperty("subwayNm")
    public void setSubwayNm(Object subwayNm) {
        this.subwayNm = subwayNm;
    }

    @JsonProperty("updnLine")
    public String getUpdnLine() {
        return updnLine;
    }

    @JsonProperty("updnLine")
    public void setUpdnLine(String updnLine) {
        this.updnLine = updnLine;
    }

    @JsonProperty("trainLineNm")
    public String getTrainLineNm() {
        return trainLineNm;
    }

    @JsonProperty("trainLineNm")
    public void setTrainLineNm(String trainLineNm) {
        this.trainLineNm = trainLineNm;
    }

    @JsonProperty("subwayHeading")
    public Object getSubwayHeading() {
        return subwayHeading;
    }

    @JsonProperty("subwayHeading")
    public void setSubwayHeading(Object subwayHeading) {
        this.subwayHeading = subwayHeading;
    }

    @JsonProperty("statnFid")
    public String getStatnFid() {
        return statnFid;
    }

    @JsonProperty("statnFid")
    public void setStatnFid(String statnFid) {
        this.statnFid = statnFid;
    }

    @JsonProperty("statnTid")
    public String getStatnTid() {
        return statnTid;
    }

    @JsonProperty("statnTid")
    public void setStatnTid(String statnTid) {
        this.statnTid = statnTid;
    }

    @JsonProperty("statnId")
    public String getStatnId() {
        return statnId;
    }

    @JsonProperty("statnId")
    public void setStatnId(String statnId) {
        this.statnId = statnId;
    }

    @JsonProperty("statnNm")
    public String getStatnNm() {
        return statnNm;
    }

    @JsonProperty("statnNm")
    public void setStatnNm(String statnNm) {
        this.statnNm = statnNm;
    }

    @JsonProperty("trainCo")
    public Object getTrainCo() {
        return trainCo;
    }

    @JsonProperty("trainCo")
    public void setTrainCo(Object trainCo) {
        this.trainCo = trainCo;
    }

    @JsonProperty("trnsitCo")
    public String getTrnsitCo() {
        return trnsitCo;
    }

    @JsonProperty("trnsitCo")
    public void setTrnsitCo(String trnsitCo) {
        this.trnsitCo = trnsitCo;
    }

    @JsonProperty("ordkey")
    public String getOrdkey() {
        return ordkey;
    }

    @JsonProperty("ordkey")
    public void setOrdkey(String ordkey) {
        this.ordkey = ordkey;
    }

    @JsonProperty("subwayList")
    public String getSubwayList() {
        return subwayList;
    }

    @JsonProperty("subwayList")
    public void setSubwayList(String subwayList) {
        this.subwayList = subwayList;
    }

    @JsonProperty("statnList")
    public String getStatnList() {
        return statnList;
    }

    @JsonProperty("statnList")
    public void setStatnList(String statnList) {
        this.statnList = statnList;
    }

    @JsonProperty("btrainSttus")
    public String getBtrainSttus() {
        return btrainSttus;
    }

    @JsonProperty("btrainSttus")
    public void setBtrainSttus(String btrainSttus) {
        this.btrainSttus = btrainSttus;
    }

    @JsonProperty("barvlDt")
    public String getBarvlDt() {
        return barvlDt;
    }

    @JsonProperty("barvlDt")
    public void setBarvlDt(String barvlDt) {
        this.barvlDt = barvlDt;
    }

    @JsonProperty("btrainNo")
    public String getBtrainNo() {
        return btrainNo;
    }

    @JsonProperty("btrainNo")
    public void setBtrainNo(String btrainNo) {
        this.btrainNo = btrainNo;
    }

    @JsonProperty("bstatnId")
    public String getBstatnId() {
        return bstatnId;
    }

    @JsonProperty("bstatnId")
    public void setBstatnId(String bstatnId) {
        this.bstatnId = bstatnId;
    }

    @JsonProperty("bstatnNm")
    public String getBstatnNm() {
        return bstatnNm;
    }

    @JsonProperty("bstatnNm")
    public void setBstatnNm(String bstatnNm) {
        this.bstatnNm = bstatnNm;
    }

    @JsonProperty("recptnDt")
    public String getRecptnDt() {
        return recptnDt;
    }

    @JsonProperty("recptnDt")
    public void setRecptnDt(String recptnDt) {
        this.recptnDt = recptnDt;
    }

    @JsonProperty("arvlMsg2")
    public String getArvlMsg2() {
        return arvlMsg2;
    }

    @JsonProperty("arvlMsg2")
    public void setArvlMsg2(String arvlMsg2) {
        this.arvlMsg2 = arvlMsg2;
    }

    @JsonProperty("arvlMsg3")
    public String getArvlMsg3() {
        return arvlMsg3;
    }

    @JsonProperty("arvlMsg3")
    public void setArvlMsg3(String arvlMsg3) {
        this.arvlMsg3 = arvlMsg3;
    }

    @JsonProperty("arvlCd")
    public String getArvlCd() {
        return arvlCd;
    }

    @JsonProperty("arvlCd")
    public void setArvlCd(String arvlCd) {
        this.arvlCd = arvlCd;
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
        sb.append(RealtimeArrival.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("beginRow");
        sb.append('=');
        sb.append(((this.beginRow == null)?"<null>":this.beginRow));
        sb.append(',');
        sb.append("endRow");
        sb.append('=');
        sb.append(((this.endRow == null)?"<null>":this.endRow));
        sb.append(',');
        sb.append("curPage");
        sb.append('=');
        sb.append(((this.curPage == null)?"<null>":this.curPage));
        sb.append(',');
        sb.append("pageRow");
        sb.append('=');
        sb.append(((this.pageRow == null)?"<null>":this.pageRow));
        sb.append(',');
        sb.append("totalCount");
        sb.append('=');
        sb.append(((this.totalCount == null)?"<null>":this.totalCount));
        sb.append(',');
        sb.append("rowNum");
        sb.append('=');
        sb.append(((this.rowNum == null)?"<null>":this.rowNum));
        sb.append(',');
        sb.append("selectedCount");
        sb.append('=');
        sb.append(((this.selectedCount == null)?"<null>":this.selectedCount));
        sb.append(',');
        sb.append("subwayId");
        sb.append('=');
        sb.append(((this.subwayId == null)?"<null>":this.subwayId));
        sb.append(',');
        sb.append("subwayNm");
        sb.append('=');
        sb.append(((this.subwayNm == null)?"<null>":this.subwayNm));
        sb.append(',');
        sb.append("updnLine");
        sb.append('=');
        sb.append(((this.updnLine == null)?"<null>":this.updnLine));
        sb.append(',');
        sb.append("trainLineNm");
        sb.append('=');
        sb.append(((this.trainLineNm == null)?"<null>":this.trainLineNm));
        sb.append(',');
        sb.append("subwayHeading");
        sb.append('=');
        sb.append(((this.subwayHeading == null)?"<null>":this.subwayHeading));
        sb.append(',');
        sb.append("statnFid");
        sb.append('=');
        sb.append(((this.statnFid == null)?"<null>":this.statnFid));
        sb.append(',');
        sb.append("statnTid");
        sb.append('=');
        sb.append(((this.statnTid == null)?"<null>":this.statnTid));
        sb.append(',');
        sb.append("statnId");
        sb.append('=');
        sb.append(((this.statnId == null)?"<null>":this.statnId));
        sb.append(',');
        sb.append("statnNm");
        sb.append('=');
        sb.append(((this.statnNm == null)?"<null>":this.statnNm));
        sb.append(',');
        sb.append("trainCo");
        sb.append('=');
        sb.append(((this.trainCo == null)?"<null>":this.trainCo));
        sb.append(',');
        sb.append("trnsitCo");
        sb.append('=');
        sb.append(((this.trnsitCo == null)?"<null>":this.trnsitCo));
        sb.append(',');
        sb.append("ordkey");
        sb.append('=');
        sb.append(((this.ordkey == null)?"<null>":this.ordkey));
        sb.append(',');
        sb.append("subwayList");
        sb.append('=');
        sb.append(((this.subwayList == null)?"<null>":this.subwayList));
        sb.append(',');
        sb.append("statnList");
        sb.append('=');
        sb.append(((this.statnList == null)?"<null>":this.statnList));
        sb.append(',');
        sb.append("btrainSttus");
        sb.append('=');
        sb.append(((this.btrainSttus == null)?"<null>":this.btrainSttus));
        sb.append(',');
        sb.append("barvlDt");
        sb.append('=');
        sb.append(((this.barvlDt == null)?"<null>":this.barvlDt));
        sb.append(',');
        sb.append("btrainNo");
        sb.append('=');
        sb.append(((this.btrainNo == null)?"<null>":this.btrainNo));
        sb.append(',');
        sb.append("bstatnId");
        sb.append('=');
        sb.append(((this.bstatnId == null)?"<null>":this.bstatnId));
        sb.append(',');
        sb.append("bstatnNm");
        sb.append('=');
        sb.append(((this.bstatnNm == null)?"<null>":this.bstatnNm));
        sb.append(',');
        sb.append("recptnDt");
        sb.append('=');
        sb.append(((this.recptnDt == null)?"<null>":this.recptnDt));
        sb.append(',');
        sb.append("arvlMsg2");
        sb.append('=');
        sb.append(((this.arvlMsg2 == null)?"<null>":this.arvlMsg2));
        sb.append(',');
        sb.append("arvlMsg3");
        sb.append('=');
        sb.append(((this.arvlMsg3 == null)?"<null>":this.arvlMsg3));
        sb.append(',');
        sb.append("arvlCd");
        sb.append('=');
        sb.append(((this.arvlCd == null)?"<null>":this.arvlCd));
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