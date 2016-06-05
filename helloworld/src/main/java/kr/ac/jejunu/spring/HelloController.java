package kr.ac.jejunu.spring;

import kr.ac.jejunu.hello.Hello;
import kr.ac.jejunu.hello.HelloModel;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.logging.Logger;

/**
 * Created by admin on 2016-05-13.
 */
@Controller
@RequestMapping("/spring")
@SessionAttributes("HelloModel")
public class HelloController {

    private final static Logger logger = (Logger) LoggerFactory.getLogger(HelloController.class);

    @RequestMapping("hello")
    public void hello(Model model){
        model.addAttribute("hello world");
    }

    @RequestMapping(path = "/hello/{name}", method = RequestMethod.GET)
    public String hello2(@PathVariable String name, Model model){

        model.addAttribute("hello world!!" + name);
        return "/spring/hello";
    }

    @RequestMapping(path = "/hello/{name}", method = RequestMethod.GET)
    public String hello3(@PathVariable String hello, @PathVariable String name , Model model){

        model.addAttribute(hello + "!!" + name);
        return "/spring/hello";
    }

    @RequestMapping("/hi/{hello}")
    public String helo4(@PathVariable String hello, @MatrixVariable String name, Model model)
    {
        model.addAttribute(hello + "!!" +name);
        return "/spring/hello";
    }

    @RequestMapping("/helloworld")
    public String hello5(@RequestParam String hello, @RequestParam String name, Model model){
        model.addAttribute(hello + "!!" + name);
        return "/spring/hello";
    }

    @RequestMapping("/hellomodel")
    public String hello6(HelloModel model){
        logger.info("*******" + model.getHello() + model.getNmae() + "********");
        return "/spring/hellomodel";
    }

    @RequestMapping("/hellocookie")
    public String hello7(HelloModel model,
                         @CookieValue(value = "name", defaultValue = "wow") String name,
                          HttpServletResponse response)
    {
        model.setName(name);
        if("wow".equals(name))
            response.addCookie(new Cookie("name", "HOTS"));
        else
            response.addCookie(new Cookie("name", "wow"));
        return "/spring/hellomodel";
    }

    @RequestMapping("/hellosession")
    public String hello8(HelloModel model, HttpSession session)
    {
        String name = (String) session.getAttribute("name");
        model.setName(name);
        if("wow".equals(name))
            session.setAttribute("name", "HOTS");
        else
            session.setAttribute("name", "wow");
        return "/spring/hellomodel";
    }

    @ModelAttribute("helloModel")
    public HelloModel model()
    {
     return new HelloModel();
    }

    @RequestMapping("/sessionattribute")
    public String hell9(HelloModel helloModel)
    {
        helloModel.setHello("HOTS");
        helloModel.setName("wow");
        return "/spring/hellomodel";
    }

    @RequestMapping("/sessionattributevalue")
    public String hello9(HelloModel helloModel)
    {
        return "/spring/hellomodel";
    }

    @RequestMapping("/returnmodel")
    public HelloModel hello10()
    {
        HelloModel helloModel = new HelloModel();
        helloModel.setHello("wow");
        helloModel.setHello("HOTS");
        return helloModel;
    }

}
