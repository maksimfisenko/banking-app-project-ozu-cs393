package com.ozyegin.cs393.services;

import com.ozyegin.cs393.entities.DebitCard;
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

    @Test
    @Transactional
    public void testCreateDebitCard() {
        DebitCard createdDebitCard = debitCardService.createDebitCard(
                new DebitCard(null, "12345678", LocalDate.ofYearDay(2024, 1),
                        "testName", null));

        assertNotNull(createdDebitCard);
        assertNotNull(createdDebitCard.getId());
        assertEquals("testName" ,createdDebitCard.getName());
    }

    @Test
    @Transactional
    public void testGetAllDebitCards() {
        DebitCard createdDebitCard1 = debitCardService.createDebitCard(
                new DebitCard(null, "123456781", LocalDate.ofYearDay(2024, 1),
                        "testName1", null));
        DebitCard createdDebitCard2 = debitCardService.createDebitCard(
                new DebitCard(null, "123456782", LocalDate.ofYearDay(2024, 1),
                        "testName2", null));

        List<DebitCard> createdDebitCards = debitCardService.getAllDebitCards();

        assertEquals(2, createdDebitCards.size());
        assertEquals("testName1", createdDebitCards.get(0).getName());
        assertEquals("123456782", createdDebitCards.get(1).getNumber());
    }

    @Test
    @Transactional
    public void testUpdateDebitCard() {
        DebitCard createdDebitCard = debitCardService.createDebitCard(
                new DebitCard(null, "12345678", LocalDate.ofYearDay(2024, 1),
                        "testName", null));

        createdDebitCard.setName("updatedName");
        DebitCard updatedDebitCard = debitCardService.updateDebitCard(createdDebitCard);

        assertNotNull(updatedDebitCard);
        assertEquals(createdDebitCard.getId(), updatedDebitCard.getId());
        assertEquals("updatedName", updatedDebitCard.getName());
    }

    @Test
    @Transactional
    public void testDeleteDebitCard() {
        DebitCard createdDebitCard1 = debitCardService.createDebitCard(
                new DebitCard(null, "123456781", LocalDate.ofYearDay(2024, 1),
                        "testName1", null));
        DebitCard createdDebitCard2 = debitCardService.createDebitCard(
                new DebitCard(null, "123456782", LocalDate.ofYearDay(2024, 1),
                        "testName2", null));

        debitCardService.deleteDebitCardById(createdDebitCard1.getId());

        assertEquals(1, debitCardService.getAllDebitCards().size());
    }
}
