package talmo5.talmorello.board.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import talmo5.talmorello.board.constant.BoardColor;
import talmo5.talmorello.board.dto.BoardRequestDto;
import talmo5.talmorello.board.dto.BoardResponseDto;
import talmo5.talmorello.board.entity.Board;
import talmo5.talmorello.board.repository.BoardRepository;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {
    private final BoardRepository boardRepository;
    //    private final UserRepository userRepository;
    @Override
    public BoardResponseDto postBoard(BoardRequestDto requestDto/*, User user*/) {
        String title = requestDto.getTitle();
        String content = requestDto.getContent();
        String bc = requestDto.getBoardColor();

        Board board = new Board(toBoardColor(bc), content, title);
        board.addUser(/*user*/);

        boardRepository.save(board);

        return new BoardResponseDto(board);
    }

    private BoardColor toBoardColor(String bc){
        return switch (bc) {
            case "pink" -> BoardColor.PINK;
            case "purple" -> BoardColor.PURPLE;
            case "yellow" -> BoardColor.YELLOW;
            case "orange" -> BoardColor.ORANGE;
            default -> BoardColor.WHITE;
        };
    }
}
