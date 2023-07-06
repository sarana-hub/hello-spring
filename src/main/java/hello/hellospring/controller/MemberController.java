package hello.hellospring.controller;

import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;



@Controller
public class MemberController {
    private final MemberService memberService;

    /**생성자에 @Autowired가 있으면, 스프링이 연관된 객체를 찾아 넣어준다.*/
    //이것이 DI(의존성 주입:객체 의존관계를 "외부에서" 넣어주는 것)
    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    /** 회원 등록 폼*/
    @GetMapping("/members/new")
    public String createForm() {
        return "members/createMemberForm";
    }

    /** 회원을 등록하는 기능*/
    @PostMapping("/members/new")
    public String create(MemberForm form) {
        Member member = new Member();
        member.setName(form.getName());

        //System.out.println("member= "+member.getName());

        memberService.join(member);
        return "redirect:/";  //홈화면으로
    }


    /** 조회 기능*/
    @GetMapping("/members")
    public String list(Model model) {
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "members/memberList";
    }
}
