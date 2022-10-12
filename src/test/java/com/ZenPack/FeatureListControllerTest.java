package com.ZenPack;

import com.ZenPack.controller.FeatureListController;
import com.ZenPack.model.FeaturedList;
import com.ZenPack.repository.FeaturedListRepository;
import com.ZenPack.service.Impl.FeaturedListServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import java.time.Instant;
import java.util.*;

import static org.assertj.core.internal.bytebuddy.matcher.ElementMatchers.is;
//import static org.hamcrest.Matchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest
public class FeatureListControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FeaturedListRepository repository;

    @MockBean
    private FeaturedListServiceImpl service;

    @InjectMocks
    private FeatureListController featureListController;

    @Autowired
    private ObjectMapper objectMapper;

    private FeaturedList list;
    private FeaturedList list1;

    Date dateOne = new Date();

    // Creating Instant object
    Instant inst = Instant.now();



    @BeforeEach
    void init(){
        FeaturedList list=new FeaturedList();
        list.setId(1);
        list.setFeatureName("Project Management");
        list.setFeatureUrl(null);
//        list.setCreatedTime(Date.from(inst));
        list.setCreatedBy("Elavarasan");

        FeaturedList list1=new FeaturedList();
        list.setId(2);
        list.setFeatureName("Project Summary");
        list.setFeatureUrl(null);
//        list.setCreatedTime(Date.from(inst));
        list.setCreatedBy("Elavarasan");


    }

    @Test
    void shouldCreateNewFeatureListTest() throws Exception {
        FeaturedList list=new FeaturedList();
        list.setId(1);
        list.setFeatureName("Project Management");
        list.setFeatureUrl(null);
        list.setCreatedTime(dateOne);
        list.setCreatedBy("Elavarasan");

        when(service.save(any(FeaturedList.class))).thenReturn(list);

        this.mockMvc.perform(post("/api/v1/create_feature_list")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(list)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.featureName", CoreMatchers.is(list.getFeatureName())))
                .andExpect(jsonPath("$.featureUrl", CoreMatchers.is(list.getFeatureUrl())))
                .andExpect(jsonPath("$.createdBy", CoreMatchers.is(list.getCreatedBy())));

    }

    @Test
    void shouldFetchAllList() throws Exception {

        List<FeaturedList> featuredListList = new ArrayList<>();
        featuredListList.add(list);
        featuredListList.add(list1);

        when(service.findAllList()).thenReturn(featuredListList);

        this.mockMvc.perform(get("/api/v1/get_feature_list"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()",CoreMatchers.is(featuredListList.size())));
    }

    @Test
    void shouldDeleteList() throws Exception {

        doNothing().when(service).deleteList(anyInt());

        this.mockMvc.perform(delete("/api/v1/deleteList/{id}", 2))
                .andExpect(status().isNoContent());
    }

    @Test
    public void shouldUpdateList() throws Exception{
        int ListId = 2;
        FeaturedList savedList = FeaturedList.builder()
                .featureName("Project Summary")
                .featureUrl("null")
                .createdBy("Elavarasan")
                .build();

        FeaturedList updatedList = FeaturedList.builder()
                .featureName("Project Summary")
                .featureUrl("null")
                .createdBy("Murugan")
                .build();
        given(service.getListById(ListId)).willReturn(Optional.of(savedList));
        given(service.updatedList(any(FeaturedList.class)))
                .willAnswer((invocation)-> invocation.getArgument(0));

        ResultActions response = mockMvc.perform(put("/api/v1/{id}", ListId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updatedList)));

        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.featureName",CoreMatchers.is(updatedList.getFeatureName())));
    }



}
