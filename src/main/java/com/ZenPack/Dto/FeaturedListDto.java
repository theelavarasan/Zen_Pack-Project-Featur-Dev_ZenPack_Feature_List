package com.ZenPack.Dto;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;
@Getter
@Setter
public class FeaturedListDto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer featureId;
    private String featureName;
    private String featureUrl;
    private String createdBy;
    private Date createdTime;
}
