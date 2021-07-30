package com.hubu.aspirin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Test1 {
    @Autowired
    Test2 test2;
}
