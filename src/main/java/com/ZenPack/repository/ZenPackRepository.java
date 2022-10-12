package com.ZenPack.repository;

import com.ZenPack.model.ZenPack;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ZenPackRepository extends JpaRepository<ZenPack,Integer> {
//    List<ZenPack> findByZenPackName();

//    List<ZenPack> findAllByNameLikeAndCategoryIn(String name, List<ZenPack> categories);
}
