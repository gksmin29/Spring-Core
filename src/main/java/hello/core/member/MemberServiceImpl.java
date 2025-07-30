package hello.core.member;

public class MemberServiceImpl implements MemberService {

    //인터페이스만 선언
    private final MemberRepository memberRepository;

    //AppConfig에서 만들어진 구현체를 생성자로 주입받음
    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public void join(Member member) {
        memberRepository.save(member);

    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
