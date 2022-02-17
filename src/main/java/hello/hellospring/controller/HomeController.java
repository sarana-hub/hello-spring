package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/** 홈 화면 추가 -홈 컨트롤러 */

@Controller
public class HomeController {
    @GetMapping("/")
    public String home() {
        return "home";
    }
}
