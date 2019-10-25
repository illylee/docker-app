package com.example.dockerapp.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SynonymDomain {

    private Integer synonymSeq;
    private String synonym;
    private String modifiedYmdt;
}
