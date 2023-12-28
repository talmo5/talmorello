package talmo5.talmorello.board.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import talmo5.talmorello.board.dto.PostBoardDTO;
import talmo5.talmorello.board.dto.PostBoardDTO.Request;
import talmo5.talmorello.board.entity.Board;
import talmo5.talmorello.board.repository.BoardRepository;
import talmo5.talmorello.global.exception.board.BoardNotFoundException;

@Service
@RequiredArgsConstructor
public class BoardService{
    private final BoardRepository boardRepository;

    public PostBoardDTO.Response postBoard(PostBoardDTO.Request requestDto) {
        return buildBoard(requestDto);
    }

    private PostBoardDTO.Response buildBoard(PostBoardDTO.Request requestDto){
        Board board = PostBoardDTO.BoardBuild(requestDto);
        boardRepository.save(board);
        return PostBoardDTO.response(board);
    }

    public void patchBoard(Request requestDto, Long boardId) {
        Board board = findbyId(boardId);
        board.update(requestDto.title(), requestDto.content(), requestDto.boardColor());
    }

    private Board findbyId(Long boardId) {
        return boardRepository.findById(boardId).orElseThrow(BoardNotFoundException::new);
    }
}

