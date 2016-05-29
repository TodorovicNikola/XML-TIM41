package xml_app.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
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
            resp.sendRedirect("/XMLProject");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("/XMLProject")
    void home(HttpServletRequest req, HttpServletResponse resp){
        try {
            //DatabaseHelper dbH = new DatabaseHelper();
            //dbH.write("xml/Akt.xml","Akt1");

            RequestDispatcher rd = req.getRequestDispatcher("/angular/dist/index.html");
            rd.forward(req, resp);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

}
