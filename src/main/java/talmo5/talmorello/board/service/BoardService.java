package talmo5.talmorello.board.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import talmo5.talmorello.board.dto.GetBoardDTO;
import talmo5.talmorello.board.dto.ModifyBoardDTO;
import talmo5.talmorello.board.dto.PostBoardDTO;
import talmo5.talmorello.board.entity.Board;
import talmo5.talmorello.board.repository.BoardRepository;
import talmo5.talmorello.global.exception.board.BoardNotFoundException;

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

    public GetBoardDTO.Response getBoard(Long boardId) {
        Board board = boardRepository.findByIdWithCatalogListAndCardList(boardId).orElseThrow(BoardNotFoundException::new);
        return GetBoardDTO.buildResponse(board.getTitle(), board.getContent(), board.getBoardColor());
    }
}

