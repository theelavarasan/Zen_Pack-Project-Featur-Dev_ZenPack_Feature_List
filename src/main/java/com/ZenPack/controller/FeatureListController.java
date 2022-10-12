package com.ZenPack.controller;

import com.ZenPack.model.FeaturedList;
import com.ZenPack.service.Impl.FeaturedListServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class FeatureListController {

    @Autowired
    private FeaturedListServiceImpl service;

    @PostMapping("/create_feature_list")
    @ResponseStatus(HttpStatus.CREATED)
    public FeaturedList create(@RequestBody FeaturedList featuredList){
        return service.save(featuredList);
    }

    @GetMapping("/get_feature_list")
    @ResponseStatus(HttpStatus.OK)
    public List<FeaturedList> findAllFeatureList(){
        return service.findAllList();
    }

    @GetMapping("/search_by_name")
    @ResponseStatus(HttpStatus.OK)
    public List<FeaturedList> findByName(@RequestParam String keyword){
        return service.findByKeyword(keyword);
    }

    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<FeaturedList> getList(@PathVariable int id){
        return service.getListById(id);
    }


    @DeleteMapping("/deleteList/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id){
        service.deleteList(id);
    }

    @PutMapping("{id}")
    public ResponseEntity<FeaturedList> updateEmployee(@PathVariable("id") int listId,
                                                   @RequestBody FeaturedList featuredList){
        return service.getListById(listId)
                .map(savedList -> {

                    savedList.setFeatureName(featuredList.getFeatureName());
                    savedList.setFeatureUrl(featuredList.getFeatureUrl());
                    savedList.setCreatedTime(featuredList.getCreatedTime());
                    savedList.setCreatedBy(featuredList.getCreatedBy());

                    FeaturedList updatedList = service.updatedList(savedList);
                    return new ResponseEntity<>(updatedList, HttpStatus.OK);

                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

}
