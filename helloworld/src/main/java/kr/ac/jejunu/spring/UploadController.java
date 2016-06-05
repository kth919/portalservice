package kr.ac.jejunu.spring;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by admin on 2016-06-05.
 */
@Controller
public class UploadController {
         @RequestMapping(path = "/upload", method = RequestMethod.POST)
         public String upload(MultipartFile file) throws IOException {
                 FileOutputStream fileOutputStream = new FileOutputStream(new File("/daum/" + file.getOriginalFilename()));
                 BufferedOutputStream outputStream = new BufferedOutputStream(fileOutputStream);
                 outputStream.write(file.getBytes());
                 outputStream.close();
                 return "redirect:/success";
             }

}
