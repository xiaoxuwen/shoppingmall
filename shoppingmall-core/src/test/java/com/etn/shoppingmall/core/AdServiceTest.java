package com.etn.shoppingmall.core;

import com.etn.shoppingmall.core.entity.Ad;
import com.etn.shoppingmall.core.model.SystemContext;
import com.etn.shoppingmall.core.service.AdService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.time.LocalDateTime;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class AdServiceTest {

    @Autowired
    private AdService adService;

    @Test
    public void testAdd() {
        Ad ad = new Ad();
        ad.setName("name");
        ad.setLink("link");
        ad.setUrl("url");
        ad.setPosition(1);
        ad.setEnabled(false);
        ad.setAddTime(LocalDateTime.now());
        ad.setDeleted(false);
        adService.add(ad);
    }

    @Test
    public void testLoad() {
        System.out.println(adService.load(1));
    }

    @Test
    public void testUpdate() {
        Ad ad = new Ad();
        ad.setId(1);
        ad.setContent("test");
        System.out.println(adService.update(ad));
    }

    @Test
    public void testDelete() {
        adService.delete(1);
    }

    @Test
    public void testList() {
        //测试
        SystemContext.setPageOffset(2);
        SystemContext.setPageSize(2);
        System.out.println(adService.find());
    }
}
