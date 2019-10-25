package com.example.dockerapp.controller;

import com.example.dockerapp.domain.ApiResult;
import com.example.dockerapp.domain.SynonymDomain;
import com.example.dockerapp.service.SearchAdminService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/api")
@Slf4j
public class DemoController {

    @Autowired
    SearchAdminService searchAdminService;


    @RequestMapping(value = "/synonym/list", method = RequestMethod.GET)
    public ResponseEntity<ApiResult<List<SynonymDomain>>> synonymList(@RequestParam(value = "keyword", required = false) String keyword) throws Exception {

        log.info("DemoController.synonymList keyword: {}", keyword );

        List<SynonymDomain> list = searchAdminService.getSynonymList(keyword);
        return ResponseEntity.ok(ApiResult.ok(list));
    }


    @RequestMapping(value = "/synonym/list2", method = RequestMethod.GET)
    public ResponseEntity<ApiResult<List<SynonymDomain>>> synonymList2(@RequestParam(value = "keyword", required = false) String keyword) throws Exception {
        log.info("DemoController.synonymList2 keyword: {}", keyword );

        List<SynonymDomain> list = searchAdminService.getSynonymList2(keyword);
        return ResponseEntity.ok(ApiResult.ok(list));
    }
}
