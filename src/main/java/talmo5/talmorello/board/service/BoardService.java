package talmo5.talmorello.board.service;

import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import talmo5.talmorello.board.dto.ModifyBoardDTO;
import talmo5.talmorello.board.dto.PostBoardDTO;
import talmo5.talmorello.board.entity.Board;
import talmo5.talmorello.board.repository.BoardRepository;
import talmo5.talmorello.global.exception.board.BoardNotFoundException;

@Slf4j
@Service
@RequiredArgsConstructor
public class BoardService{
    private final BoardRepository boardRepository;

    public void postBoard(PostBoardDTO.Request requestDto) {
        buildBoard(requestDto);
    }

    private void buildBoard(PostBoardDTO.Request requestDto){
        Board board = PostBoardDTO.BoardBuild(requestDto);
        boardRepository.save(board);
    }

    @Transactional
    public void patchBoard(ModifyBoardDTO.Request requestDto, Long boardId) {
        Board board = findbyId(boardId);
        board.update(requestDto.title(), requestDto.content(), requestDto.boardColor());
    }

    public void deleteBoard(Long boardId) {
        Board board = findbyId(boardId);
        boardRepository.delete(board);
    }

    private Board findbyId(Long boardId) {
        return boardRepository.findById(boardId).orElseThrow(BoardNotFoundException::new);
    }

    public List<Map<String,?>> getBoard(Long boardId) {
//        List<Map<String, ?>> board = boardRepository.findByIdWithCatalogListAndCardList(boardId);
        return boardRepository.findByIdWithCatalogListAndCardList(boardId);
        //return GetBoardDTO.buildResponse(board.getTitle(), board.getContent(), board.getBoardColor());
    }
}

