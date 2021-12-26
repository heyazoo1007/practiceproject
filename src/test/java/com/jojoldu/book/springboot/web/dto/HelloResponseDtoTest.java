package com.jojoldu.book.springboot.web.dto;

import com.jojoldu.book.springboot.web.dto.HelloResponseDto;
import org.junit.Test;

import static org.junit.Assert.*;

public class HelloResponseDtoTest {

    @Test
    public void 롬복_기능_테스트(){
        //given
        String name = "test";
        int amount =123;

        //when
        HelloResponseDto dto = new HelloResponseDto(name,amount);

        //then
        assertEquals(dto.getName(),name);
        assertEquals(dto.getAmount(),amount);
    }

}