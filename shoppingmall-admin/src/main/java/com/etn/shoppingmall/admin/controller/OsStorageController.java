package com.etn.shoppingmall.admin.controller;

import com.etn.shoppingmall.common.util.CharUtil;
import com.etn.shoppingmall.common.util.ResponseUtil;
import com.etn.shoppingmall.core.entity.Storage;
import com.etn.shoppingmall.core.service.ObjectStorageService;
import com.etn.shoppingmall.core.service.impl.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/os/storage")
public class OsStorageController {
    @javax.annotation.Resource(name = "localOsService")
    private ObjectStorageService localOsService;
    @Autowired
    private StorageService storageService;

    private String generateKey(String originalFilename) {
        int index = originalFilename.lastIndexOf('.');
        String suffix = originalFilename.substring(index);

        String key = null;
        Storage storage = null;

        do {
            key = CharUtil.getRandomString(20) + suffix;
            storage = storageService.findByKey(key);
        }
        while (storage != null);

        return key;
    }

    @GetMapping("/list")
    public Object list(String key, String name,
                       @RequestParam(value = "page", defaultValue = "1") Integer page,
                       @RequestParam(value = "limit", defaultValue = "10") Integer limit,
                       String sort, String order) {
        List<Storage> storageList = storageService.querySelective(key, name, page, limit, sort, order);
        int total = storageService.countSelective(key, name, page, limit, sort, order);
        Map<String, Object> data = new HashMap<>();
        data.put("total", total);
        data.put("items", storageList);

        return ResponseUtil.ok(data);
    }

    @PostMapping("/create")
    public Object create(@RequestParam("file") MultipartFile file) {
        String originalFilename = file.getOriginalFilename();
        InputStream inputStream = null;
        try {
            inputStream = file.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseUtil.badArgumentValue();
        }
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
        Storage storage = storageService.findById(id);
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
        Storage storage = storageService.findByKey(key);
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
        Storage storage = storageService.findByKey(key);
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
