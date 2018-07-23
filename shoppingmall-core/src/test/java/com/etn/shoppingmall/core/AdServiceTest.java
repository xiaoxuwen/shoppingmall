package com.etn.shoppingmall.core;

import com.etn.shoppingmall.core.entity.Ad;
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
        Ad ad = adService.load(1);
        ad.setName("name2");
        ad.setLink("name2");
        ad.setUrl("name2");
        ad.setPosition(1);
        ad.setContent("name2");
        adService.update(ad);
    }

    @Test
    public void testDelete() {
        Ad ad = adService.load(1);

        adService.delete(ad);
    }

    @Test
    public void testList(){
        System.out.println(adService.listAd(1,10,null,null).getDatas());
    }
}
