package talmo5.talmorello.board.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import talmo5.talmorello.board.dto.PostBoardDTO;
import talmo5.talmorello.board.entity.Board;
import talmo5.talmorello.board.repository.BoardRepository;

@Service
@RequiredArgsConstructor
public class BoardService{
    private final BoardRepository boardRepository;

    public PostBoardDTO.Response postBoard(PostBoardDTO.Request requestDto) {
        return buildBoard(requestDto);
    }

    private PostBoardDTO.Response buildBoard(PostBoardDTO.Request requestDto){
        Board board = Board.builder()
                .title(requestDto.title())
                .content(requestDto.content())
                .boardColor(requestDto.boardColor())
                .build();

        boardRepository.save(board);
        return PostBoardDTO.Response.builder()
                .title(board.getTitle())
                .content(board.getContent())
                .board_color(board.getBoardColor())
                .build();
    }
}

