package com.etn.shoppingmall.core;

import com.etn.shoppingmall.core.service.impl.LocalOsServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.Resource;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.util.FileCopyUtils;

import java.io.FileInputStream;
import java.io.IOException;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class LosTest {
    @Autowired
    private LocalOsServiceImpl localOsService;

    @Test
    public void test() throws IOException {
        String test = getClass().getClassLoader().getResource("litemall.png").getFile();
        byte[] content = (byte[]) FileCopyUtils.copyToByteArray(new FileInputStream(test));
        MockMultipartFile mockMultipartFile = new MockMultipartFile("litemall.png", "litemall.png", "image/jpeg", content);
        localOsService.store(mockMultipartFile, "los.png");
        Resource resource = localOsService.loadAsResource("los.png");
        String url = localOsService.generateUrl("los.png");
        System.out.println("test file " + test);
        System.out.println("store file " + resource.getURI());
        System.out.println("generate url " + url);

//        localOsService.delete("los.png");

    }

}