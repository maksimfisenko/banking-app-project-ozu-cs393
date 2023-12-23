package com.ozyegin.cs393.services;

import com.ozyegin.cs393.dto.AccountDTO;
import com.ozyegin.cs393.dto.CurrencyDTO;
import com.ozyegin.cs393.dto.DebitCardDTO;
import com.ozyegin.cs393.dto.PaymentDTO;
import com.ozyegin.cs393.entities.Account;
import com.ozyegin.cs393.entities.Currency;
import com.ozyegin.cs393.entities.DebitCard;
import com.ozyegin.cs393.mappers.AccountMapper;
import com.ozyegin.cs393.mappers.CurrencyMapper;
import com.ozyegin.cs393.mappers.DebitCardMapper;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.DateTimeException;
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
    @Autowired
    private AccountService accountService;
    @Autowired
    private AccountMapper accountMapper;
    @Autowired
    private CurrencyService currencyService;
    @Autowired
    private CurrencyMapper currencyMapper;

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


        debitCardService.deleteDebitCardById(createdDebitCard1.getId());

        assertEquals(1, debitCardService.getAllDebitCards().size());
    }

    @Test
    @Transactional
    public void testOpenDebitCard(){

        Account account = new Account(
                null, "testName", null, null, 100,
                LocalDate.now(), null, null, null, null);

        AccountDTO createdAccount = accountService.createAccount(accountMapper.accountToAccountDto(account));
        DebitCardDTO debitCardDTO = debitCardService.openDebitCard(createdAccount.getNumber(), "testCard");
        assertNotNull(debitCardDTO);
        assertNotNull(debitCardDTO.getNumber());
        assertEquals(createdAccount, debitCardDTO.getAccount());

    }

    @Test
    @Transactional
    public void testMakePayment(){

        Currency currency = new Currency(null, "test", '$', 1);
        CurrencyDTO createdCurrency =
                currencyService.createCurrency(currencyMapper.currencyToCurrencyDto(currency));

        Account account = new Account(
                null, "testName", currencyMapper.currencyDtoToCurrency(createdCurrency),
                null, 100, LocalDate.now(), null, null,
                null, null);
        AccountDTO createdAccount = accountService.createAccount(accountMapper.accountToAccountDto(account));

        DebitCardDTO debitCardDTO =
                debitCardService.openDebitCard(createdAccount.getNumber(), "testCard");

        Account receivingAccount = new Account(
                null, "receiver", currency, null, 100,
                LocalDate.now(), null, null, null, null);

        AccountDTO receivingAccountDTO =
                accountService.createAccount(accountMapper.accountToAccountDto(receivingAccount));

        boolean res = debitCardService.makePayment(debitCardDTO, receivingAccountDTO, 50);
        createdAccount = accountService.getAccountByNumber(createdAccount.getNumber());
        receivingAccountDTO = accountService.getAccountByNumber(receivingAccountDTO.getNumber());
        assertTrue(res);
        assertEquals(50, createdAccount.getAmount());
        assertEquals(150, receivingAccountDTO.getAmount());

        res = debitCardService.makePayment(debitCardDTO, receivingAccountDTO, 150);

        assertFalse(res);
        assertEquals(50, createdAccount.getAmount());
        assertEquals(150, receivingAccountDTO.getAmount());
    }

    @Test
    @Transactional
    public void testGetPaymentsByDates() {

        Currency currency = new Currency(null, "test", '$', 1);
        CurrencyDTO currencyDTO = currencyService.createCurrency(currencyMapper.currencyToCurrencyDto(currency));

        Account account = new Account(
                null, "testName", currencyMapper.currencyDtoToCurrency(currencyDTO), null, 100,
                LocalDate.now(), null, null, null, null);
        AccountDTO createdAccount = accountService.createAccount(accountMapper.accountToAccountDto(account));

        DebitCardDTO debitCardDTO = debitCardService.openDebitCard(createdAccount.getNumber(), "testCard");

        assertThrows(DateTimeException.class, () -> debitCardService.getPaymentsByDates(
                debitCardDTO, LocalDate.now().plusDays(1), LocalDate.now()
        ));

        Account receivingAccount = new Account(
                null, "receiver", currencyMapper.currencyDtoToCurrency(currencyDTO), null, 100,
                LocalDate.now(), null, null, null, null);
        AccountDTO receivingAccountDTO = accountService.createAccount(accountMapper.accountToAccountDto(receivingAccount));

        debitCardService.makePayment(debitCardDTO, receivingAccountDTO, 50);

        List<PaymentDTO> paymentDTOs = debitCardService.getPaymentsByDates(debitCardDTO, LocalDate.now(), LocalDate.now());
        assertEquals(1, paymentDTOs.size());

    }
}
