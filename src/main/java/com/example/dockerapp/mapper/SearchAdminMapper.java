package com.example.dockerapp.mapper;

import com.example.dockerapp.domain.SynonymDomain;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SearchAdminMapper {
    List<SynonymDomain> selectSynonymList(@Param("keyword") String keyword);
    List<SynonymDomain> selectSynonymList2(@Param("keyword") String keyword);

}
