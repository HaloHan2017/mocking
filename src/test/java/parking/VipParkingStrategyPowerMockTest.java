package parking;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.Calendar;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.powermock.api.mockito.PowerMockito.*;

@RunWith(PowerMockRunner.class)
@PrepareForTest(value = {VipParkingStrategy.class,ParkingLot.class})
public class VipParkingStrategyPowerMockTest {


    @Test
    public void testCalculateHourlyPrice_givenSunday_thenPriceIsDoubleOfSundayPrice(){

        /* Exercise 6: Write test case for VipParkingStrategy calculateHourlyPrice
        * by using PowerMock to mock static method */
        //given
        VipParkingStrategy vipParkingStrategy = new VipParkingStrategy();
        mockStatic(ParkingLot.class);
        when(ParkingLot.getBasicHourlyPrice()).thenReturn(25);
        //when
        Integer sum = vipParkingStrategy.calculateHourlyPrice();
        //then
        assertEquals(50, (int) sum);
    }

    @Test
    public void testCalculateHourlyPrice_givenNotSunday_thenPriceIsDoubleOfNonSundayPrice(){

        /* Exercise 6: Write test case for VipParkingStrategy calculateHourlyPrice
         * by using PowerMock to mock static method */
        //given
        VipParkingStrategy vipParkingStrategy = new VipParkingStrategy();
        mockStatic(ParkingLot.class);
        when(ParkingLot.getBasicHourlyPrice()).thenReturn(20);
        //when
        Integer sum = vipParkingStrategy.calculateHourlyPrice();
        //then
        assertEquals(40, (int) sum);

    }
}
