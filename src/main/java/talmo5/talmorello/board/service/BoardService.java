package talmo5.talmorello.board.service;

import talmo5.talmorello.board.dto.BoardInviteRequestDto;
import talmo5.talmorello.board.dto.BoardRequestDto;
import talmo5.talmorello.board.dto.BoardResponseDto;

public interface BoardService {
    /**
     * 보드 생성
     * @param requestDto 보드 생성 요청 정보
     * @param user 보드 작성자
     */
    BoardResponseDto postBoard(BoardRequestDto requestDto/*, User user*/);
}