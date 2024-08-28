package com.sparta.board3.dto;

import lombok.Getter;

@Getter
public class BoardUpdateTitleResponseDto {
    private final Long id;
    private final String title;
    private final String contents;
    private final String name;
    public BoardUpdateTitleResponseDto(Long id, String title, String contents, String name) {
        this.id = id;
        this.title = title;
        this.contents = contents;
        this.name = name;
    }


}