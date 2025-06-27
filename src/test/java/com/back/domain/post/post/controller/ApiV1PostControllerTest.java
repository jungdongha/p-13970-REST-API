package com.back.domain.post.post.controller;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("test") //메모리 db, applicatin-test.yml활성화
@SpringBootTest
//mockMVC 설정
@AutoConfigureMockMvc
//각 메소드 실행후 롤백
@Transactional
public class ApiV1PostControllerTest {

    @Autowired
    private MockMvc mvc;

    // 회원가입 테스트
    @Test
    @DisplayName("글 쓰기")
    void t1() throws Exception {
        // 회원가입 요청을 보냅니다.
        ResultActions resultActions = mvc
                .perform(
                        post("/api/v1/posts")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("""
                                        {
                                            "title": "제목",
                                            "content": "내용"
                                        }
                                        """)
                )
                .andDo(print()); //응답 결과 출력
        //201 created 상태 코드 검증
        resultActions
                .andExpect(status().isCreated());
    }


    @Test
    @DisplayName("글 수정")
    void t2() throws Exception {
        // 회원가입 요청을 보냅니다.
        ResultActions resultActions = mvc
                .perform(
                        put("/api/v1/posts/1")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("""
                                        {
                                            "title": "제목 new",
                                            "content": "내용 new"
                                        }
                                        """)
                )
                .andDo(print()); //응답 결과 출력
        //201 created 상태 코드 검증
        resultActions
                .andExpect(status().isOk());
    }
}
