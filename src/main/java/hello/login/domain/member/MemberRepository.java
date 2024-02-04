package hello.login.domain.member;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
@Slf4j
public class MemberRepository {
    private static Map<Long,Member> store = new HashMap<>();//static사용

    private static long sequence = 0l;

    public Member save(Member member){
        ++sequence;
        member.setId(sequence);
        log.info("save:member={}",member);
        store.put(member.getId(), member);
        return member;

    }

    public Member findById(Long id){
        return store.get(id);
    }

    public Optional<Member> findByLoginId(String loginId){
//        List<Member> all = findAll();
//        for(Member m : all){
//            if(m.getLoginId().equals(loginId)){
//                return Optional.of(m);
//            }
//        }
//        return Optional.empty();
        return findAll().stream() //List를 Stream으로 바꾼다 (마치 루프를 도는것)
                .filter(m->m.getLoginId().equals(loginId)) //필터를 통해 만족하는 값만 다음단계로넘어간다
                .findFirst();         //그중 제일 먼저 나오는애를 반환한다.

    }

    public void clearStore(){
        store.clear();
    }
    public List<Member> findAll(){
        return new ArrayList<>(store.values());
    }


}
