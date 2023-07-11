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


//@Component
@Transactional //join에만 해줘도 된다
public class MemberService {
    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {

        this.memberRepository = memberRepository;
    }

    /**
     * 회원가입
     */
    public Long join(Member member) {
        validateDuplicateMember(member);  //중복회원(같은이름회원) 검증
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
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

    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }
}

    /**
     * 회원가입
     *//*
    public Long join(Member member) {
        long start=System.currentTimeMillis();

        try{
            validateDuplicateMember(member);  //Extract method로 자동생성

            memberRepository.save(member);
            return member.getId();
        }finally {
            long finish=System.currentTimeMillis();
            long timeMs=finish-start;
            System.out.println("join="+timeMs+"ms");
        }

    }
    private void validateDuplicateMember(Member member) {
        //중복회원(같은이름회원) 검증
        memberRepository.findByName(member.getName())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    *//**
     * 전체 회원 조회
     *//*
    public List<Member> findMembers() {
        long start=System.currentTimeMillis();
        try {
            return memberRepository.findAll();
        } finally {
            long finish=System.currentTimeMillis();
            long timeMs=finish-start;
            System.out.println("findMembers " + timeMs + "ms");
        }
    }

    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }*/

