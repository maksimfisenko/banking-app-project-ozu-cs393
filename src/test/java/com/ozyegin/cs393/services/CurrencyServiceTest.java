package com.ozyegin.cs393.services;

import com.ozyegin.cs393.entities.Currency;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
public class CurrencyServiceTest {
    @Autowired
    private CurrencyService currencyService;

//    @Test
//    @Transactional
//    public void testCreateCurrency() {
//        Currency createdCurrency = currencyService.createCurrency(
//                new Currency(null, "testCurrency", '$', 1));
//
//        assertNotNull(createdCurrency);
//        assertNotNull(createdCurrency.getId());
//        assertEquals("testCurrency", createdCurrency.getName());
//    }
//
//    @Test
//    @Transactional
//    public void testGetAllCurrencies() {
//        Currency createdCurrency1 = currencyService.createCurrency(
//                new Currency(null, "testCurrency1", '$', 1));
//        Currency createdCurrency2 = currencyService.createCurrency(
//                new Currency(null, "testCurrency2", '&', 2));
//
//        List<Currency> createdCurrencies = currencyService.getAllCurrencies();
//
//        assertEquals(2, createdCurrencies.size());
//        assertEquals("testCurrency1", createdCurrencies.get(0).getName());
//        assertEquals('&', createdCurrencies.get(1).getSymbol());
//    }
//
//    @Test
//    @Transactional
//    public void testUpdateCurrency() {
//        Currency createdCurrency = currencyService.createCurrency(
//                new Currency(null, "testCurrency", '$', 1));
//
//        createdCurrency.setName("updatedCurrency");
//        Currency updatedCurrency = currencyService.updateCurrency(createdCurrency);
//
//        assertNotNull(updatedCurrency);
//        assertEquals(createdCurrency.getId(), updatedCurrency.getId());
//        assertEquals("updatedCurrency", updatedCurrency.getName());
//    }
//
//    @Test
//    @Transactional
//    public void testDeleteCurrency() {
//        Currency createdCurrency1 = currencyService.createCurrency(
//                new Currency(null, "testCurrency1", '$', 1));
//        Currency createdCurrency2 = currencyService.createCurrency(
//                new Currency(null, "testCurrency2", '&', 2));
//
//        currencyService.deleteCurrencyById(createdCurrency1.getId());
//
//        assertEquals(1, currencyService.getAllCurrencies().size());
//    }
}
