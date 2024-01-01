package talmo5.talmorello.column.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import talmo5.talmorello.board.entity.Board;
import talmo5.talmorello.board.service.BoardService;
import talmo5.talmorello.boarduser.validator.BoardUserValidator;
import talmo5.talmorello.column.dto.CreateColumnDTO;
import talmo5.talmorello.column.dto.CreateColumnDTO.Response;
import talmo5.talmorello.column.entity.Column;
import talmo5.talmorello.column.repository.ColumnRepository;
import talmo5.talmorello.global.exception.column.ColumnNotFoundException;
import talmo5.talmorello.global.exception.column.InvalidNewOrdersException;
import talmo5.talmorello.user.entity.User;
import talmo5.talmorello.user.service.UserService;

@Service
@RequiredArgsConstructor
public class ColumnService {

    private final BoardService boardService;

    private final UserService userService;

    private final ColumnRepository columnRepository;

    private final BoardUserValidator boardUserValidator;

    private static final int ORDER_MIN_VALUE = 1;

    public Response createColumn(Long boardId, CreateColumnDTO.Request createColumnDTO, Long userId) {

        Board board = boardService.findById(boardId);
        User user = userService.findById(userId);

        boardUserValidator.validateBoardUser(board, user);

        int orders = getLastOrders();
        Column column = createColumnDTO.toEntity(createColumnDTO.columnTitle(), board, orders + 1);

        Column savecolumn = columnRepository.save(column);

        return CreateColumnDTO.Response.from(savecolumn);
    }

    private int getLastOrders() {

        Integer orders = columnRepository.getLastOrders();
        if (orders == null) {
            return 0;
        }
        return orders;
    }

    @Transactional
    public void modifyColumnName(Long columnId, String columnTitle, Long userId) {

        Column column = getColumnWithBoard(columnId);
        User user = userService.findById(userId);

        boardUserValidator.validateBoardUser(column.getBoard(), user);

        column.modifyColumnName(columnTitle);
    }

    @Transactional
    public void changeColumn(Long columnId, int newOrders, Long userId) {

        Column column = getColumnWithBoard(columnId);
        User user = userService.findById(userId);

        boardUserValidator.validateBoardUser(column.getBoard(), user);

        int oldOrders = column.getOrders();

        if (newOrders > columnRepository.orderCount() || newOrders < ORDER_MIN_VALUE) {
            throw new InvalidNewOrdersException();
        }

        if (newOrders > oldOrders) {
            columnRepository.subtractOneToColumnOrders(column.getBoard().getId(), columnId,
                    newOrders, oldOrders);
        } else {
            columnRepository.addOneToColumnOrders(column.getBoard().getId(), columnId, newOrders,
                    oldOrders);
        }
        column.changeOrders(newOrders);
    }

    @Transactional
    public void deleteColumn(Long columnId, Long userId) {

        Column column = getColumnWithBoard(columnId);
        User user = userService.findById(userId);

        boardUserValidator.validateBoardUser(column.getBoard(), user);

        columnRepository.subtractOrders(column.getBoard().getId(), column.getOrders());
        columnRepository.delete(column);
    }

    public Column getColumnWithBoard(Long columnId) {
        return columnRepository.getColumnWithBoard(columnId)
                .orElseThrow(ColumnNotFoundException::new);
    }

}

