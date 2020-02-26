package tests.pricingtypetests;

import models.PricingType;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;
import services.SimulationService;
import util.Result;

import java.util.Optional;

public abstract class PricingTypeTests {
    private final int timeInMinutes;
    private final double expectedResult;
    private SimulationService service;
    private PricingType pricingType;

    public abstract  PricingType getPricingType();

    public PricingTypeTests(int timeInMinutes, double expectedResult) {

        this.timeInMinutes = timeInMinutes;
        this.expectedResult = expectedResult;
        setupMock();

    }

    private void setupMock() {

        service = Mockito.mock(SimulationService.class);
        pricingType = getPricingType();


        Mockito.when(service.calculate(pricingType, timeInMinutes)).thenReturn(new Result(Optional.of(expectedResult)));

    }

    @Test
    public void calculate_price() {

        //arrange, act
        var result = service.calculate(pricingType, timeInMinutes);

        //assert...
        Assert.assertTrue(result.getValue().get()==expectedResult);
        Assert.assertTrue(result.isSuccess());

    }
}
