package hello.hellospring.controller;

import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

/** 회원 등록 폼 컨트롤러*/
/*회원 컨트롤러에 의존관계 추가*/

@Controller
public class MemberController {
    private final MemberService memberService;

    @Autowired
    /**생성자에 @Autowired 가 있으면, "스프링이" 연관된 객체를 스프링 컨테이너에서 찾아서 넣어준다.
     * 이렇게 객체 의존관계를 "외부에서" 넣어주는 것이 DI (Dependency Injection:의존성 주입)
     */
    public MemberController(MemberService memberService) {

        this.memberService = memberService;
    }

    @GetMapping(value = "/members/new")
    public String createForm() {
        return "members/createMemberForm";
    }

    /** 회원을 실제 등록하는 기능*/
    @PostMapping(value = "/members/new")
    public String create(MemberForm form) {
        Member member = new Member();
        member.setName(form.getName());

        //System.out.println("member= "+member.getName());
        memberService.join(member);
        return "redirect:/";
    }

    /** 조회 기능*/
    @GetMapping(value = "/members")
    public String list(Model model) {
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "members/memberList";
    }
}
