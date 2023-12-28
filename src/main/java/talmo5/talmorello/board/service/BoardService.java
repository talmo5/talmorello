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
        Board board = Board.buildBoard(requestDto);
        boardRepository.save(board);
        return PostBoardDTO.response(board);
    }
}

