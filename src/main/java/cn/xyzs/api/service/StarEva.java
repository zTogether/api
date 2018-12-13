package cn.xyzs.api.service;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Table;

@Data
@Table(name = "XY_STAR_EVA_LIST")
public class StarEva {
    @Column(name = "EVA_NO")
    String evaNo;

    @Column(name = "QUALITY")
    String quality;

    @Column(name = "EVALUATION")
    String evaluation;

    @Column(name = "EVA_NAME")
    String evaName;

    @Column(name = "EVA_CODE")
    String evaCode;

    @Column(name = "EVA_TYPE")
    String evaType;

    @Column(name = "EVA_DATE")
    String evaDate;

    @Column(name = "SERVICE")
    String service;

    @Column(name = "DAYS")
    String days;

    @Column(name = "HYGIENE")
    String hygiene;
}
