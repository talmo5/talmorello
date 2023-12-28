package talmo5.talmorello.board.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import talmo5.talmorello.board.dto.ModifyBoardDTO;
import talmo5.talmorello.board.dto.PostBoardDTO;
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

    @Transactional
    public void patchBoard(ModifyBoardDTO.Request requestDto, Long boardId) {
        Board board = findbyId(boardId);
        String[] tc = ModifyBoardDTO.fill(requestDto, board);
        board.update(tc[0], tc[1], requestDto.boardColor());
    }

    private Board findbyId(Long boardId) {
        return boardRepository.findById(boardId).orElseThrow(BoardNotFoundException::new);
    }
}

