package com.jojoldu.book.springboot.domain;

import com.jojoldu.book.springboot.domain.posts.Posts;
import com.jojoldu.book.springboot.domain.posts.PostsRepository;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.assertj.core.api.Assertions.assertThat;




@RunWith(SpringRunner.class)
@SpringBootTest
public class PostsRepositoryTest {

    @Autowired
    PostsRepository postsRepository;

    @After
    public void cleanup(){
        postsRepository.deleteAll();
    }

    @Test
    public void 게시글저장_불러오기(){
        //given
        String title1= "제목임";
        String content1="내용임";

        postsRepository.save(Posts.builder()
                .title(title1)
                .content(content1)
                .author("jojoldu@gmail.com")
                .build());
        //when
        List<Posts> postsList = postsRepository.findAll();

        //then
        Posts posts = postsList.get(0);/*리스트에서 첫번째 값*/
        assertEquals(title1,posts.getTitle());
        assertEquals(content1,posts.getContent());
    }

    @Test
    public void BaseTimeEntity_등록(){
        //givne
        LocalDateTime now = LocalDateTime.of(2019,6,4,0,0,0);
        postsRepository.save(Posts.builder()
                .title("title")
                .content("content")
                .author("author")
                .build());

        //when
        List<Posts> all = postsRepository.findAll();

        //then
        Posts posts = all.get(0);

        System.out.println(">>>>> createDate="+ posts.getCreatedDate()+",modifiedDate="+posts.getModifiedDate());

        assertThat(posts.getModifiedDate()).isAfter(now);
        assertThat(posts.getCreatedDate()).isAfter(now);
    }


}