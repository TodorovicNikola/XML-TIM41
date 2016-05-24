package xml_app.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Vuletic on 24.5.2016.
 */

@RestController
public class IndexController {

    @RequestMapping("/")
    void sendToHome(HttpServletResponse resp){
        try {
            resp.sendRedirect("/initial");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
