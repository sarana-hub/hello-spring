package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/** 회원 서비스 개발*/

//@Service
//@Component
//@Controller

@Transactional

public class MemberService {
    //private final MemberRepository memberRepository= new MemoryMemberRepository();;
    //회원 서비스가 메모리 회원 리포지토리를 직접 생성

    //->회원 리포지토리의 코드가 회원 서비스 코드를 DI 가능하게 변경
    private MemberRepository memberRepository;

    /**생성자 주입 방식
     *생성자에 @Autowired 를 사용하면
     * 객체 생성 시점에 스프링 컨테이너에서 해당 스프링 빈을 찾아서 주입
    */
    //@Autowired    //생성자가 하나면 생략 가능
    public MemberService(MemberRepository memberRepository) {

        this.memberRepository = memberRepository;
    }


    /**
     * 회원가입
     */
    public Long join(Member member) {
        long start=System.currentTimeMillis();

        try{
            validateDuplicateMember(member);  //중복(같은이름회원) 검증

            memberRepository.save(member);
            return member.getId();
        }finally {
            long finish=System.currentTimeMillis();
            long timeMs=finish-start;   //회원 조회 시간(ms)
            System.out.println("join="+timeMs+"ms");
        }

    }
    private void validateDuplicateMember(Member member) {
        //중복(같은이름회원) 검증
        memberRepository.findByName(member.getName())
                .ifPresent(m -> {   //리포지토리에 값(같은이름)이 있으면
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }


    /**
     * 전체 회원 조회
     */
    /*public List<Member> findMembers() {
        return memberRepository.findAll();
    }*/
    public List<Member> findMembers() {
        long start=System.currentTimeMillis();
        try {
            return memberRepository.findAll();
        } finally {
            long finish=System.currentTimeMillis();
            long timeMs=finish-start;   //회원 조회 시간(ms)
            System.out.println("findMembers " + timeMs + "ms");
        }
    }

    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
