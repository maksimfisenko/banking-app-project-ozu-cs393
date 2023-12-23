package com.ozyegin.cs393.services;

import com.ozyegin.cs393.dto.DebitCardDTO;
import com.ozyegin.cs393.entities.DebitCard;
import com.ozyegin.cs393.mappers.DebitCardMapper;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import java.time.LocalDate;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
public class DebitCardServiceTest {
    @Autowired
    private DebitCardService debitCardService;
    @Autowired
    private DebitCardMapper debitCardMapper;

    @Test
    @Transactional
    public void testCreateDebitCard() {
        DebitCard debitCard =
                new DebitCard(null, "12345678", LocalDate.ofYearDay(2024, 1),
                        "testName", null);

        DebitCardDTO createdDebitCard = debitCardMapper.debitCardtoDebitCardDto(debitCard);
        createdDebitCard = debitCardService.createDebitCard(createdDebitCard);

        assertNotNull(createdDebitCard);
        assertNotNull(createdDebitCard.getId());
        assertEquals("testName" ,createdDebitCard.getName());
    }

    @Test
    @Transactional
    public void testGetAllDebitCards() {
        DebitCard debitCard1 =
                new DebitCard(null, "123456781", LocalDate.ofYearDay(2024, 1),
                        "testName1", null);
        DebitCard debitCard2 =
                new DebitCard(null, "123456782", LocalDate.ofYearDay(2024, 1),
                        "testName2", null);

        DebitCardDTO createdDebitCard1 = debitCardService.createDebitCard(debitCardMapper.debitCardtoDebitCardDto(debitCard1));
        DebitCardDTO createdDebitCard2 = debitCardService.createDebitCard(debitCardMapper.debitCardtoDebitCardDto(debitCard2));


        List<DebitCardDTO> createdDebitCards = debitCardService.getAllDebitCards();

        assertEquals(2, createdDebitCards.size());
        assertEquals("testName1", createdDebitCards.get(0).getName());
        assertEquals("123456782", createdDebitCards.get(1).getNumber());
    }

    @Test
    @Transactional
    public void testUpdateDebitCard() {
        DebitCard debitCard =
                new DebitCard(null, "12345678", LocalDate.ofYearDay(2024, 1),
                        "testName", null);

        DebitCardDTO createdDebitCard = debitCardMapper.debitCardtoDebitCardDto(debitCard);
        createdDebitCard = debitCardService.createDebitCard(createdDebitCard);


        createdDebitCard.setName("updatedName");
        DebitCardDTO updatedDebitCard = debitCardService.updateDebitCard(createdDebitCard);

        assertNotNull(updatedDebitCard);
        assertEquals(createdDebitCard.getId(), updatedDebitCard.getId());
        assertEquals("updatedName", updatedDebitCard.getName());
    }

    @Test
    @Transactional
    public void testDeleteDebitCard() {
        DebitCard debitCard1 =
                new DebitCard(null, "123456781", LocalDate.ofYearDay(2024, 1),
                        "testName1", null);
        DebitCard debitCard2 =
                new DebitCard(null, "123456782", LocalDate.ofYearDay(2024, 1),
                        "testName2", null);

        DebitCardDTO createdDebitCard1 = debitCardService.createDebitCard(debitCardMapper.debitCardtoDebitCardDto(debitCard1));
        DebitCardDTO createdDebitCard2 = debitCardService.createDebitCard(debitCardMapper.debitCardtoDebitCardDto(debitCard2));


        debitCardService.deleteDebitCardById(createdDebitCard1);

        assertEquals(1, debitCardService.getAllDebitCards().size());
    }
}
