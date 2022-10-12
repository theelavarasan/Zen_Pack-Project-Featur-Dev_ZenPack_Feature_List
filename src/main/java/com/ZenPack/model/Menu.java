package com.ZenPack.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Menu")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Menu {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "menu_id")
    private Long id;

    @Column(name = "menu_name")
    private String menuName;

    @Column(name = "created_time")
    private Date createdTime=new Date();

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "parent_menu_id")
    private String parentMenuId;

    @OneToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinColumn(name = "menu_id")
    private List<Menu> subMenus;

    @OneToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinColumn(name = "menu_id")
    private List<FeaturedList> features;
}
