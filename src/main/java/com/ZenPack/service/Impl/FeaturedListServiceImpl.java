package com.ZenPack.service.Impl;

import com.ZenPack.model.FeaturedList;
import com.ZenPack.repository.FeaturedListRepository;
import com.ZenPack.service.Services.FeaturedListService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FeaturedListServiceImpl implements FeaturedListService {


    @Autowired
    private FeaturedListRepository repository;

    private static final Logger logger = LoggerFactory.getLogger(FeaturedListServiceImpl.class);

    @Override
    public FeaturedList save(FeaturedList featuredList) {
        return repository.save(featuredList);
    }

    //Get the Feature_List
    @Override
    public List<FeaturedList> findAllList() {
        return repository.findAll();
    }

    //Get Feature_list by keyword
    @Override
    public List<FeaturedList> findByKeyword(String keyword){
        return repository.findByKeyword(keyword);
    }

    @Override
    public FeaturedList updateList(FeaturedList list, int id) {
        FeaturedList existingList = repository.findById(id).get();
        existingList.setFeatureName(list.getFeatureName());
        existingList.setFeatureUrl(list.getFeatureUrl());
        existingList.setCreatedTime(list.getCreatedTime());
        existingList.setCreatedBy(list.getCreatedBy());
        return repository.save(existingList);
    }

    @Override
    public FeaturedList updatedList(FeaturedList updatedList) {
        return repository.save(updatedList);
    }

    @Override
    public Optional<FeaturedList> getListById(int listId) {
        return repository.findById(listId);
    }

    public void deleteList(int id) {
        FeaturedList existingList=repository.findById(id).get();
        repository.delete(existingList);
    }




}
