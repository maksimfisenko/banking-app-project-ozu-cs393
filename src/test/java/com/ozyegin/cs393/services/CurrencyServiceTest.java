package com.ozyegin.cs393.services;

import com.ozyegin.cs393.dto.CurrencyDTO;
import com.ozyegin.cs393.entities.Currency;
import com.ozyegin.cs393.mappers.CurrencyMapper;
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
    @Autowired
    private CurrencyMapper currencyMapper;

    @Test
    @Transactional
    public void testCreateCurrency() {

        Currency currency = new Currency(null, "test", '$', 1);
        CurrencyDTO createdCurrency =
                currencyService.createCurrency(currencyMapper.currencyToCurrencyDto(currency));

        assertNotNull(createdCurrency);
        assertNotNull(createdCurrency.getId());
        assertEquals("test", createdCurrency.getName());
    }

    @Test
    @Transactional
    public void testGetAllCurrencies() {

        Currency currency1 = new Currency(null, "test1", '$', 1);
        CurrencyDTO createdCurrency1 =
                currencyService.createCurrency(currencyMapper.currencyToCurrencyDto(currency1));

        Currency currency2 = new Currency(null, "test2", '-', 2);
        CurrencyDTO createdCurrency2 =
                currencyService.createCurrency(currencyMapper.currencyToCurrencyDto(currency2));

        List<CurrencyDTO> createdCurrencies = currencyService.getAllCurrencies();

        assertEquals(2, createdCurrencies.size());
        assertEquals("test1", createdCurrencies.get(0).getName());
        assertEquals('-', createdCurrencies.get(1).getSymbol());
    }

    @Test
    @Transactional
    public void testUpdateCurrency() {
        Currency currency = new Currency(null, "test", '$', 1);
        CurrencyDTO createdCurrency =
                currencyService.createCurrency(currencyMapper.currencyToCurrencyDto(currency));

        createdCurrency.setName("updatedCurrency");
        CurrencyDTO updatedCurrency = currencyService.updateCurrency(createdCurrency);

        assertNotNull(updatedCurrency);
        assertEquals(createdCurrency.getId(), updatedCurrency.getId());
        assertEquals("updatedCurrency", updatedCurrency.getName());
    }

    @Test
    @Transactional
    public void testGetCurrencyById() {
        Currency currency = new Currency(null, "test", '$', 1);
        CurrencyDTO createdCurrency =
                currencyService.createCurrency(currencyMapper.currencyToCurrencyDto(currency));
        CurrencyDTO foundCurrency = currencyService.getCurrencyById(createdCurrency.getId());

        assertEquals(createdCurrency.getId(), foundCurrency.getId());
        assertEquals(createdCurrency.getName(), foundCurrency.getName());
    }

    @Test
    @Transactional
    public void testDeleteCurrencyById() {

        Currency currency1 = new Currency(null, "test1", '$', 1);
        CurrencyDTO createdCurrency1 =
                currencyService.createCurrency(currencyMapper.currencyToCurrencyDto(currency1));

        Currency currency2 = new Currency(null, "test2", '-', 2);
        CurrencyDTO createdCurrency2 =
                currencyService.createCurrency(currencyMapper.currencyToCurrencyDto(currency2));

        currencyService.deleteCurrencyById(createdCurrency1.getId());

        assertEquals(1, currencyService.getAllCurrencies().size());
    }

    @Test
    @Transactional
    public void testDeleteAllCurrencies() {

        Currency currency1 = new Currency(null, "test1", '$', 1);
        CurrencyDTO createdCurrency1 =
                currencyService.createCurrency(currencyMapper.currencyToCurrencyDto(currency1));

        Currency currency2 = new Currency(null, "test2", '-', 2);
        CurrencyDTO createdCurrency2 =
                currencyService.createCurrency(currencyMapper.currencyToCurrencyDto(currency2));

        currencyService.deleteAllCurrencies();

        assertEquals(0, currencyService.getAllCurrencies().size());
    }
}
