package com.etn.shoppingmall.core;

import com.etn.shoppingmall.core.service.impl.StorageServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class StorageServiceTest {

    @Autowired
    private StorageServiceImpl storageService;

    @Test
    public void testStorage() {
        System.out.println(storageService.queryByKey("123"));
    }

}
