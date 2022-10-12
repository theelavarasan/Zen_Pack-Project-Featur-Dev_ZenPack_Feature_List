package com.ZenPack.Dto;

import com.ZenPack.model.Menu;
import lombok.*;

import java.util.Date;
import java.util.List;
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ZenPackDto {
    private Long id;
    private String name;
    private String createdBy;
    private Date createdDate=new Date();
    private String updatedBy;
    private Date updatedTime=new Date();
    private List<Menu> menus;
}
