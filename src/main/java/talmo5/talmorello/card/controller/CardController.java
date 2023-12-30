package talmo5.talmorello.card.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import talmo5.talmorello.card.constant.Priority;
import talmo5.talmorello.card.dto.CreateCardDTO;
import talmo5.talmorello.card.dto.ModifyCardDateDTO;
import talmo5.talmorello.card.dto.ModifyCardDescriptionDTO;
import talmo5.talmorello.card.dto.ModifyCardTitleDTO;
import talmo5.talmorello.card.service.CardService;
import talmo5.talmorello.global.argumentresolver.LoginUserId;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/cards")
public class CardController {

    private final CardService cardService;

    @PostMapping("/columns/{columnId}")
    public ResponseEntity<CreateCardDTO.Response> createCard(
            @PathVariable Long columnId,
            @RequestBody @Valid CreateCardDTO.Request createCardDTO,
            @LoginUserId Long userId) {

        CreateCardDTO.Response responseDTO = cardService.createCard(columnId, createCardDTO, userId);

        return ResponseEntity.ok(responseDTO);
    }

    @PatchMapping("/{cardId}/title")
    public ResponseEntity<String> modifyCardTitle(
            @PathVariable Long cardId,
            @RequestBody @Valid ModifyCardTitleDTO modifyCardTitleDTO,
            @LoginUserId Long userId) {

        cardService.modifyCardTitle(cardId, modifyCardTitleDTO.cardTitle(), userId);

        return ResponseEntity.ok("카드 제목 수정 성공");
    }

    @PatchMapping("/{cardId}/description")
    public ResponseEntity<String> modifyCardDescription(
            @PathVariable Long cardId,
            @RequestBody @Valid ModifyCardDescriptionDTO modifyCardDescriptionDTO,
            @LoginUserId Long userId) {

        cardService.modifyCardDescription(
                cardId, modifyCardDescriptionDTO.cardDescription(), userId);

        return ResponseEntity.ok("카드 내용 수정 성공");
    }

    @PatchMapping("/{cardId}/order/{cardOrders}/columns/{columnId}")
    public ResponseEntity<String> changeColumnOfCard(@PathVariable Long cardId,
            @PathVariable int cardOrders, @PathVariable Long columnId) {

        cardService.changeColumnOfCard(cardId, cardOrders, columnId);

        return ResponseEntity.ok("카드 컬럼 변경 성공");
    }

    @PostMapping("/{cardId}/member/{userIdToAdd}")
    public ResponseEntity<String> addUserToCard(@PathVariable Long cardId,
            @PathVariable Long userIdToAdd, @LoginUserId Long userId) {

        cardService.addUserToCard(cardId, userIdToAdd, userId);

        return ResponseEntity.ok("카드에 유저 추가 완료");
    }

    @DeleteMapping("/{cardId}/member/{userIdToDelete}")
    public ResponseEntity<String> deleteCardUser(@PathVariable Long cardId,
            @PathVariable Long userIdToDelete, @LoginUserId Long userId) {

        cardService.deleteCardUser(cardId, userIdToDelete, userId);

        return ResponseEntity.ok("카드 유저 삭제 완료");
    }

    @PatchMapping("/{cardId}/date")
    public ResponseEntity<String> modifyCardDate(@PathVariable Long cardId,
            @RequestBody @Valid ModifyCardDateDTO modifyCardDateDTO, @LoginUserId Long userId) {

        cardService.modifyCardDate(cardId, modifyCardDateDTO, userId);

        return ResponseEntity.ok("카드 날짜 수정 성공");
    }

    @PatchMapping("/{cardId}/boards/{boardId}/priority")
    public ResponseEntity<String> modifyCardPriority(
            @PathVariable Long cardId,
            @RequestParam("priority") Priority priority,
            @LoginUserId Long userId) {

        cardService.modifyCardPriority(cardId, priority, userId);

        return ResponseEntity.ok("카드 우선순위 수정 성공");
    }
}
