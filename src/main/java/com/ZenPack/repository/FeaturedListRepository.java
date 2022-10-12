package com.ZenPack.repository;

import com.ZenPack.model.FeaturedList;
import com.ZenPack.model.ZenPack;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Repository
public interface FeaturedListRepository extends JpaRepository<FeaturedList, Integer> {

    //Custom Query
    @Query("SELECT p FROM FeaturedList p WHERE p.featureName LIKE %:keyword%" )
    List<FeaturedList> findByKeyword(String keyword);

}
