// GreetingController.java
package com.example.spring_practice.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller // 이 클래스가 MVC 패턴의 컨트롤러임을 나타냅니다.
public class GreetingController {

    // HTTP GET 요청이 "/greeting" 경로로 올 때 이 메서드를 실행합니다.
    @GetMapping("/greeting")
    public String greeting(
            // URL 파라미터 `name`을 받습니다. 값이 없으면 기본값 "World"를 사용합니다.
            @RequestParam(name = "name", required = false, defaultValue = "World") String name,
            Model model // 뷰에 데이터를 전달할 Model 객체입니다.
    ) {
        // "message"라는 이름으로 뷰에 전달할 데이터를 모델에 추가합니다.
        model.addAttribute("message", "Hello, " + name + "!");
        // 렌더링할 뷰의 이름을 반환합니다. "greeting.html" 파일을 찾게 됩니다.
        return "greeting";
    }
}
