package be.teachngo.controller;

import be.teachngo.data.User;
import be.teachngo.dto.UploadFileResponse;
import be.teachngo.service.StorageService;
import be.teachngo.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.File;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("api")
public class DocumentController {

    @Autowired
    private StorageService storageService;

    @PostMapping("/uploadFile")
    public UploadFileResponse uploadFile(@RequestParam("file") MultipartFile file,
                                         @RequestParam("type") String type) {

        String new_name = "";
        switch (type) {
            case Constants.PROFILE:
                final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
                if (authentication.getPrincipal() instanceof User) {
                    User user = (User) authentication.getPrincipal();
                    new_name = type + File.separator + user.getId() + ".jpeg";
                }
                break;
            default:
                throw new IllegalArgumentException("type :" + type + " isn't defined yet");
        }
        storageService.store(file, new_name);
        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/api/downloadFile/")
                .path(new_name)
                .toUriString();
        return new UploadFileResponse(new_name, fileDownloadUri,
                file.getContentType(), file.getSize());
    }


    /**
     * Anonymouse mode
     *
     * @param filename
     * @return
     */
    @GetMapping("/downloadFile/{type}/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> loadImageFromServer(@PathVariable String filename,
                                                        @PathVariable String type) {
        Resource file = storageService.loadAsResource(type + File.separator + filename);
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(MediaType.IMAGE_JPEG_VALUE))
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"" + file.getFilename()).body(file);
    }


}



