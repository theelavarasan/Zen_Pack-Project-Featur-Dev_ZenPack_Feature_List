package com.ZenPack.service.Services;

import com.ZenPack.model.FeaturedList;

import java.util.List;
import java.util.Optional;

public interface FeaturedListService {

////    ResponseEntity<ZenPack> saveZenPack(ZenPack zenPack);
//
    FeaturedList save(FeaturedList featuredList);

    List<FeaturedList> findAllList();

    //Get Feature_list by keyword
    List<FeaturedList> findByKeyword(String keyword);
//
//
////    ResponseEntity<FeaturedList> saveFeauturedList(FeaturedList list);

    FeaturedList updateList(FeaturedList list, int id);

//    void deleteMovie(int id);

//    FeaturedList updateList(FeaturedList updateList);

    FeaturedList updatedList(FeaturedList updatedList);

    Optional<FeaturedList> getListById(int listId);
}
