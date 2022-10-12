package com.ZenPack;

import com.ZenPack.model.FeaturedList;
import com.ZenPack.repository.FeaturedListRepository;
import com.ZenPack.service.Impl.FeaturedListServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class FeatureListServiceTest {
    @Mock
    private FeaturedListRepository repository;

    @InjectMocks
    private FeaturedListServiceImpl service;

    private FeaturedList list;

    Date dateOne = new Date();

    Instant inst = Instant.now();

    @BeforeEach
    public void setUp(){
        list= FeaturedList.builder()
                .id(1)
                .featureName("Project Management")
                .featureUrl(null)
                .createdTime(Date.from(inst))
                .createdBy("Elavarasan")
                .build();
    }

    @Test
    @DisplayName("Junit Test for Save List")
    void saveList(){
        given(repository.findById(list.getId()))
                .willReturn(Optional.empty());

        given(repository.save(list)).willReturn(list);

        System.out.println(repository);
        System.out.println(service);

        FeaturedList savedList = service.save(list);

        System.out.println(savedList);
        assertThat(savedList).isNotNull();
    }

    @DisplayName("JUnit test for getAllFeature method")
    @Test
    public void givenFeatureList_whenGetAllFeatureList_thenReturnFeatureList(){

        FeaturedList list1 = FeaturedList.builder()
                .id(1)
                .featureName("Project Management")
                .featureUrl(null)
                .createdTime(Date.from(inst))
                .createdBy("Elavarasan")
                .build();

        FeaturedList list2 = FeaturedList.builder()
                .id(2)
                .featureName("Project Summary")
                .featureUrl(null)
                .createdTime(Date.from(inst))
                .createdBy("Murugan")
                .build();

        given(repository.findAll()).willReturn(List.of(list,list1,list2));

        List<FeaturedList> list = service.findAllList();

        assertThat(list).isNotNull();
        assertThat(list.size()).isEqualTo(3);
    }


    @Test
    void givenFeatureId_whenDeleteFeature_thenNothing() {

        Integer listId = 2;
        when(repository.findById(anyInt())).thenReturn(Optional.of(list));
        doNothing().when(repository).delete(any(FeaturedList.class));

        service.deleteList(listId);

        verify(repository, times(1)).delete(list);

    }

    @DisplayName("JUnit test for updateFeatureList method")
    @Test
    public void givenFeatureObject_whenUpdateFeature_thenReturnUpdatedFeature(){
        given(repository.save(list)).willReturn(list);
        list.setFeatureName("Project Summary");
        list.setCreatedBy("Elavarasan");
        FeaturedList updatedList = service.updatedList(list);

        assertThat(updatedList.getFeatureName()).isEqualTo("Project Summary");
        assertThat(updatedList.getCreatedBy()).isEqualTo("Elavarasan");
    }
}
