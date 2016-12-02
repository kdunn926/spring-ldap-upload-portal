package hello;

import java.io.File;
import java.io.IOException;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class PortalController {

    @GetMapping("/")
    public String index(Model model, Authentication authentication) {
        model.addAttribute("user", authentication.getName());

        /* webapp/index.ftl */
        return "index";
    }

    @GetMapping("/signin")
    public String auth() {
        /* webapp/signin.ftl */
        return "signin";
    }

    public static final String uploadingdir = System.getProperty("user.dir") + "/uploadingdir/";

    @RequestMapping("/upload")
    public String uploading(Model model) {
        File file = new File(uploadingdir);
        model.addAttribute("files", file.listFiles());

        /* webapp/uploading.ftl */
        return "uploading";
    }

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public String uploadingPost(@RequestParam("uploadingFiles") MultipartFile[] uploadingFiles, Model model) throws IOException {

        /* Allow us to show successully uploaded files */
        File dir = new File(uploadingdir);
        model.addAttribute("files", dir.listFiles());

        for(MultipartFile uploadedFile : uploadingFiles) {
            // TODO - handle upload attemps with no file specified
            File file = new File(uploadingdir + uploadedFile.getOriginalFilename());
            uploadedFile.transferTo(file);
        }

        /* webapp/uploaded.ftl */
        return "uploaded";
    }
}
