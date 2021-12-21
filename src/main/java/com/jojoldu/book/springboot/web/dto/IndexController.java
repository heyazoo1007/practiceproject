package com.jojoldu.book.springboot.web.dto;

import com.jojoldu.book.springboot.config.auth.LoginUser;
import com.jojoldu.book.springboot.config.auth.dto.SessionUser;
import com.jojoldu.book.springboot.servies.posts.PostsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final PostsService postsService;
    private final HttpSession httpSession;


    @GetMapping("/")
    public String index(Model model, @LoginUser SessionUser user){
        model.addAttribute("posts",postsService.findAllDesc());

        /*세션에 저장된 값이 있을 때*/
        if(user !=null){
            model.addAttribute("userName",user.getName());
        }
        return "index"; /*postsService.findAllDesc()로 가져온 결과를 posts로 index.mustache에 전달한다*/
    }


    @GetMapping("/posts/save")
    public String postsSave(){

        return "posts-save"; /*posts-save.mustache 호출 */
        }

     @GetMapping("/posts/update/{id}")
     public String postsUpdate(@PathVariable Long id, Model model){
        PostsResponseDto dto=postsService.findById(id);
        model.addAttribute("post",dto);

        return "posts-update"; /*posts-update.mustache 호출*/
     }


}
