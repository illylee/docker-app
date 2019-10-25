package com.example.dockerapp.service;

import com.example.dockerapp.domain.SynonymDomain;
import com.example.dockerapp.mapper.SearchAdminMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@CacheConfig(cacheNames = {"synonym"})
public class SearchAdminService {

    @Autowired
    SearchAdminMapper searchAdminMapper;


    public List<SynonymDomain> getSynonymList(String keyword) {
        return searchAdminMapper.selectSynonymList(keyword);
    }

    public List<SynonymDomain> getSynonymList2(String keyword) {
        return searchAdminMapper.selectSynonymList2(keyword);
    }

}
