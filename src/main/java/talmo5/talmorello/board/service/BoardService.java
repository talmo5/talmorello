package talmo5.talmorello.board.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import talmo5.talmorello.board.constant.BoardColor;
import talmo5.talmorello.board.dto.BoardRequestDto;
import talmo5.talmorello.board.entity.Board;
import talmo5.talmorello.board.repository.BoardRepository;

@Service
@RequiredArgsConstructor
public class BoardService{
    private final BoardRepository boardRepository;

    public Board.BoardResponseDto postBoard(BoardRequestDto requestDto) {
        return buildBoard(requestDto);
    }

    private Board.BoardResponseDto buildBoard(BoardRequestDto requestDto){
        Board board = Board.builder()
                .title(requestDto.title())
                .content(requestDto.content())
                .boardColor(BoardColor.valueOfLabel(requestDto.boardColor()))
                .build();

        boardRepository.save(board);
        return PostBoardDTO.Response.builder()
                .title(board.getTitle())
                .content(board.getContent())
                .board_color(board.getBoardColor())
                .build();
    }
}

