package hello.login.web;

import hello.login.domain.member.Member;
import hello.login.domain.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller
@RequiredArgsConstructor
public class HomeController {

    private final MemberRepository memberRepository;

//    @GetMapping("/")
//    public String home() {
//        return "home";
//    }

    @GetMapping("/")
    public String homeLogin(@CookieValue(name="memberId", required = false) Long memberId, Model model){
                                                        //쿠키값이 없는 사용자도 들어오기 때문에 required=false
                                                        //Long값은 스프링이 알아서 타입컨버트 해줌
        if(memberId==null){
            return "home";
        }
        Member loginMember= memberRepository.findById(memberId);
        if(loginMember==null){
            return"home";
        }
        model.addAttribute("member",loginMember);
        return "loginHome";


    }
}