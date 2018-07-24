package com.etn.shoppingmall.core.controller;

import com.etn.shoppingmall.common.util.CharUtil;
import com.etn.shoppingmall.common.util.ResponseUtil;
import com.etn.shoppingmall.core.entity.Storage;
import com.etn.shoppingmall.core.model.Pager;
import com.etn.shoppingmall.core.service.ObjectStorageService;
import com.etn.shoppingmall.core.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/os/storage")
public class OsStorageController {

    @Autowired
    private ObjectStorageService localOsService;
    @Autowired
    private StorageService storageService;

    private String generateKey(String originalFilename) {
        int index = originalFilename.lastIndexOf('.');
        String suffix = originalFilename.substring(index);

        String key;
        Storage storage;

        do {
            key = CharUtil.getRandomString(20) + suffix;
            storage = storageService.queryByKey(key);
        }
        while (storage != null);

        return key;
    }

    @GetMapping("/list")
    public Object list(String key, String name) {
        Pager<Storage> pager = storageService.listSelective(key, name);
        return ResponseUtil.ok(pager);
    }

    @PostMapping("/create")
    public Object create(@RequestParam("file") MultipartFile file) {
        String originalFilename = file.getOriginalFilename();
        String key = generateKey(originalFilename);
        localOsService.store(file, key);
        String url = localOsService.generateUrl(key);
        Storage storage = new Storage();
        storage.setName(originalFilename);
        storage.setSize((int) file.getSize());
        storage.setType(file.getContentType());
        storage.setAddTime(LocalDateTime.now());
        storage.setIkey(key);
        storage.setUrl(url);
        storageService.add(storage);
        return ResponseUtil.ok(storage);
    }

    @PostMapping("/read")
    public Object read(Integer id) {
        if (id == null) {
            return ResponseUtil.badArgument();
        }
        Storage storage = storageService.queryById(id);
        if (storage == null) {
            return ResponseUtil.badArgumentValue();
        }
        return ResponseUtil.ok(storage);
    }

    @PostMapping("/update")
    public Object update(@RequestBody Storage storage) {

        storageService.update(storage);
        return ResponseUtil.ok(storage);
    }

    @PostMapping("/delete")
    public Object delete(@RequestBody Storage storage) {
        storageService.deleteByKey(storage.getIkey());
        localOsService.delete(storage.getIkey());
        return ResponseUtil.ok();
    }

    @GetMapping("/fetch/{key:.+}")
    public ResponseEntity<Resource> fetch(@PathVariable String key) {
        Storage storage = storageService.queryByKey(key);
        if (key == null) {
            ResponseEntity.notFound();
        }
        String type = storage.getType();
        MediaType mediaType = MediaType.parseMediaType(type);

        Resource file = localOsService.loadAsResource(key);
        if (file == null) {
            ResponseEntity.notFound();
        }
        return ResponseEntity.ok().contentType(mediaType).body(file);
    }

    @GetMapping("/download/{key:.+}")
    public ResponseEntity<Resource> download(@PathVariable String key) {
        Storage storage = storageService.queryByKey(key);
        if (key == null) {
            ResponseEntity.notFound();
        }
        String type = storage.getType();
        MediaType mediaType = MediaType.parseMediaType(type);

        Resource file = localOsService.loadAsResource(key);
        if (file == null) {
            ResponseEntity.notFound();
        }
        return ResponseEntity.ok().contentType(mediaType).header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }

}
