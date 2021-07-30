package com.hubu.aspirin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Test2 {
    @Autowired
    Test1 test1;
}
