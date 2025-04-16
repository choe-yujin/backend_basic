package org.metaverse.chap02handlermethod;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller // 컨트롤러 bean의 이름 작성할 수 있음
@RequestMapping("/first/*") // url
public class FirstController {

    @GetMapping("regist")
    public void regist() {

    }
}
