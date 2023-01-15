package hello.core.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MemberServiceImpl implements MemberService {
    // AppConfig 에 의한 DI
    private final MemberRepository memberRepository;

    //Autowired -> 의존 관계 주입을 자동으로 하기 위해서 사용한다
    @Autowired
    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }


    /*   추상화 의존 + 구체화 의존 -> dip 원칙에 맞지 않다.
         MemberRepository 에 다른 구현체를 넣고 싶으면, 뒤의 코드를 수정해야 한다. -> ocp 원칙에 맞지 않다.

         private final MemberRepository memberRepository = new MemoryMemberRepository(); */

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }

    public MemberRepository instance() {
        return memberRepository;
    }
}
