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
import org.springframework.web.bind.annotation.RestController;
import talmo5.talmorello.card.dto.CreateCardDTO;
import talmo5.talmorello.card.dto.ModifyCardTitleDTO;
import talmo5.talmorello.card.service.CardService;
import talmo5.talmorello.user.entity.User;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/cards")
public class CardController {

    private final CardService cardService;

    private User user = User.builder()
            .id(1L)
            .email("wkdehdgk159@gmail.com")
            .username("dongha")
            .build();

    @PostMapping
    public ResponseEntity<CreateCardDTO.Response> createCard(
            @RequestBody @Valid CreateCardDTO.Request createCardDTO) {

        CreateCardDTO.Response responseDTO = cardService.createCard(createCardDTO, user.getId());

        return ResponseEntity.ok(responseDTO);
    }

    @PatchMapping("/{cardId}/title")
    public ResponseEntity<String> modifyCardTitle(@PathVariable Long cardId,
            @RequestBody @Valid ModifyCardTitleDTO modifyCardTitleDTO) {

        cardService.modifyCardTitle(cardId, modifyCardTitleDTO.cardTitle());

        return ResponseEntity.ok("카드 제목 변경 성공");
    }

    @PostMapping("/{cardId}/member/{userId}")
    public ResponseEntity<String> addUserToCard(@PathVariable Long cardId,
            @PathVariable Long userId) {

        cardService.addUserToCard(cardId, userId);

        return ResponseEntity.ok("카드에 유저 추가 완료");
    }

    @DeleteMapping("/{cardId}/member/{userId}")
    public ResponseEntity<String> deleteCardUser(@PathVariable Long cardId,
            @PathVariable Long userId) {

        cardService.deleteCardUser(cardId, userId);

        return ResponseEntity.ok("카드 유저 삭제 완료");
    }
}
