package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @GetMapping("hello")
    public String hello(Model model) {
        model.addAttribute("data", "sara!!");   // model(data:sara!!)
        return "hello";     //컨트롤러에서 리턴 값으로 문자를 반환하면,
        // viewResolver가 화면(templates/hello.html)을 찾아서 처리
    }

    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam("name") String name, Model model) {
        model.addAttribute("name", name);   // model(name:spring)
        return "hello-template";
    }
    // http://localhost:8080/hello-mvc?name=spring

    /** API - @ResponseBody 문자 반환*/
    @GetMapping("hello-string")
    @ResponseBody
    public String helloString(@RequestParam("name") String name) {
        return "hello " + name;    //@ResponseBody 를 사용하고, 문자를 반환하면
        //HTTP의 BODY에 문자 내용을 직접 반환
    }

    /** API - @ResponseBody 객체 반환*/
    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name) {
        Hello hello = new Hello();
        hello.setName(name);
        return hello;   //@ResponseBody 를 사용하고, 객체를 반환하면
        // 객체가 JSON으로 변환됨
    }
    class Hello{
        String name;
        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }
    }

}
