package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/** 회원 서비스 개발*/

//@Service
//@Component
@Transactional
public class MemberService {
    //private final MemberRepository memberRepository= new MemoryMemberRepository();;
    //회원 서비스가 메모리 회원 리포지토리를 직접 생성

    /**->회원 리포지토리의 코드가
     * 회원 서비스 코드를 DI 가능하게 변경
     * (외부에서 넣어줄수있게?)
     * */
     private final MemberRepository memberRepository;

    //@Autowired
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
            long timeMs=finish-start;
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
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }
    /*public List<Member> findMembers() {
        long start=System.currentTimeMillis();
        try {
            return memberRepository.findAll();
        } finally {
            long finish=System.currentTimeMillis();
            long timeMs=finish-start;
            System.out.println("findMembers " + timeMs + "ms");
        }
    }*/

    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
