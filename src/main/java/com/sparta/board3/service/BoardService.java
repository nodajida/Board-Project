package com.sparta.board3.service;


import com.sparta.board3.dto.*;
import com.sparta.board3.entity.Board;
import com.sparta.board3.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BoardService {

    private final BoardRepository boardRepository;
    @Transactional
    public BoardSaveResponseDto saveBoard(BoardSaveRequestDto boardSaveRequestDto) {
        Board newBoard = new Board(
                boardSaveRequestDto.getTitle(),
                boardSaveRequestDto.getContents(),
                boardSaveRequestDto.getName()
        );
        Board savedBoard = boardRepository.save(newBoard);

        return new BoardSaveResponseDto(
                savedBoard.getId(),
                savedBoard.getTitle(),
                savedBoard.getContents(),
                savedBoard.getName()
        );
    }

    public List<BoardSimpleResponseDto> getBoards() {
        List<Board> boardList = boardRepository.findAll();

        List<BoardSimpleResponseDto> dtoList = new ArrayList<>();

        for (Board board : boardList) {
            BoardSimpleResponseDto dto = new BoardSimpleResponseDto(board.getTitle());
            dtoList.add(dto);
        }
        return dtoList;
    }

    public BoardDetailResponseDto getBoard(Long boardId) {
        Board board = boardRepository.findById(boardId).orElseThrow(() -> new NullPointerException("보드 없음"));

        return new BoardDetailResponseDto(
                board.getId(),
                board.getTitle(),
                board.getContents(),
                board.getName()
        );
    }

    @Transactional
    public BoardUpdateTitleResponseDto updateBoardTitle(Long boardId, BoardUpdateTitleRequestDto boardUpdateTitleRequestDto) {
        Board board = boardRepository.findById(boardId).orElseThrow(() -> new NullPointerException("보드 없음"));

        board.updateTitle(boardUpdateTitleRequestDto.getTitle());

        return new BoardUpdateTitleResponseDto(
                board.getId(),
                board.getTitle(),
                board.getContents(),
                board.getName()
        );
    }

    @Transactional
    public BoardUpdateContentsResponseDto updateBoardContents(
            Long boardId,
            BoardUpdateContentsRequestDto boardUpdateContentsRequestDto
    ) {
        Board board = boardRepository.findById(boardId).orElseThrow(() -> new NullPointerException("보드 없음"));

        board.updateContents(board.getContents());

        return new BoardUpdateContentsResponseDto(
                board.getId(),
                board.getTitle(),
                board.getContents(),
                board.getName()
        );
    }
    @Transactional
    public void deleteBoard(Long boardId) {

    }
}
