package talmo5.talmorello.card.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import talmo5.talmorello.card.dto.CardMemberDTO;
import talmo5.talmorello.card.dto.CreateCardDTO;
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

    @PostMapping("/{cardId}/member/{userId}")
    public ResponseEntity<CardMemberDTO> addMemberToCard(@PathVariable Long cardId,
            @PathVariable Long userId) {

        CardMemberDTO responseDTO = cardService.addMemberToCard(cardId, userId);

        return ResponseEntity.ok(responseDTO);
    }

    @DeleteMapping("/{cardId}/member/{userId}")
    public ResponseEntity<CardMemberDTO> deleteCardMember(@PathVariable Long cardId,
            @PathVariable Long userId) {

        CardMemberDTO responseDTO = cardService.deleteCardMember(cardId, userId);

        return ResponseEntity.ok(responseDTO);
    }
}
