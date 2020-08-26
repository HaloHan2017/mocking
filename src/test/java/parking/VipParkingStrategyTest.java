package parking;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Collections;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class VipParkingStrategyTest {

    @InjectMocks
    private VipParkingStrategy vipParkingStrategy;
    @Mock
    private CarDao carDao;

    @Test
    public void testPark_givenAVipCarAndAFullParkingLog_thenGiveAReceiptWithCarNameAndParkingLotName() {

        /* Exercise 4, Write a test case on VipParkingStrategy.park()
         * With using Mockito spy, verify and doReturn */
        //given
        VipParkingStrategy vipParkingStrategy = spy(new VipParkingStrategy());
        ParkingLot parkingLot = mock(ParkingLot.class);
        Car car = mock(Car.class);
        Receipt receipt = mock(Receipt.class);
        //when
        vipParkingStrategy.park(Collections.singletonList(parkingLot), car);
        doReturn(receipt).when(vipParkingStrategy).park(Collections.singletonList(parkingLot), car);
        //then
        verify(vipParkingStrategy, times(1)).createReceipt(parkingLot, car);
    }

    @Test
    public void testPark_givenCarIsNotVipAndAFullParkingLog_thenGiveNoSpaceReceipt() {

        /* Exercise 4, Write a test case on VipParkingStrategy.park()
         * With using Mockito spy, verify and doReturn */
        //given
        VipParkingStrategy vipParkingStrategy = spy(new VipParkingStrategy());
        ParkingLot parkingLot = mock(ParkingLot.class);
        Car car = mock(Car.class);
        Receipt receipt = mock(Receipt.class);
        doReturn(true).when(parkingLot).isFull();
        //when
        vipParkingStrategy.park(Collections.singletonList(parkingLot), car);
        doReturn(receipt).when(vipParkingStrategy).park(Collections.singletonList(parkingLot), car);
        //then
        verify(vipParkingStrategy, times(1)).createNoSpaceReceipt(car);
    }

    @Test
    public void testIsAllowOverPark_givenCarNameContainsCharacterAAndIsVipCar_thenReturnTrue() {

        /* Exercise 5, Write a test case on VipParkingStrategy.isAllowOverPark()
         * You may refactor the code, or try to use
         * use @RunWith(MockitoJUnitRunner.class), @Mock (use Mockito, not PowerMock) and @InjectMocks
         */
        //given
        ParkingLot parkingLot = mock(ParkingLot.class);
        Car car = mock(Car.class);
        //when
        doReturn(true).when(carDao).isVip(anyString());
        doReturn("A").when(car).getName();
        Receipt receipt = vipParkingStrategy.park(Collections.singletonList(parkingLot), car);
        //then
        assertNotNull(receipt);
    }

    @Test
    public void testIsAllowOverPark_givenCarNameDoesNotContainsCharacterAAndIsVipCar_thenReturnFalse() {

        /* Exercise 5, Write a test case on VipParkingStrategy.isAllowOverPark()
         * You may refactor the code, or try to use
         * use @RunWith(MockitoJUnitRunner.class), @Mock (use Mockito, not PowerMock) and @InjectMocks
         */
        //given
        ParkingLot parkingLot = mock(ParkingLot.class);
        Car car = mock(Car.class);
        //when
        doReturn(true).when(carDao).isVip(anyString());
        doReturn("B").when(car).getName();
        boolean isVip = vipParkingStrategy.isAllowOverPark(car);
        //then
        assertFalse(isVip);
    }

    @Test
    public void testIsAllowOverPark_givenCarNameContainsCharacterAAndIsNotVipCar_thenReturnFalse() {
        /* Exercise 5, Write a test case on VipParkingStrategy.isAllowOverPark()
         * You may refactor the code, or try to use
         * use @RunWith(MockitoJUnitRunner.class), @Mock (use Mockito, not PowerMock) and @InjectMocks
         */
        //given
        ParkingLot parkingLot = mock(ParkingLot.class);
        Car car = mock(Car.class);
        //when
        doReturn(false).when(carDao).isVip(anyString());
        doReturn("A").when(car).getName();
        boolean isVip = vipParkingStrategy.isAllowOverPark(car);
        //then
        assertFalse(isVip);
    }

    @Test
    public void testIsAllowOverPark_givenCarNameDoesNotContainsCharacterAAndIsNotVipCar_thenReturnFalse() {
        /* Exercise 5, Write a test case on VipParkingStrategy.isAllowOverPark()
         * You may refactor the code, or try to use
         * use @RunWith(MockitoJUnitRunner.class), @Mock (use Mockito, not PowerMock) and @InjectMocks
         */
        //given
        ParkingLot parkingLot = mock(ParkingLot.class);
        Car car = mock(Car.class);
        //when
        doReturn(false).when(carDao).isVip(anyString());
        doReturn("B").when(car).getName();
        boolean isVip = vipParkingStrategy.isAllowOverPark(car);
        //then
        assertFalse(isVip);
    }

    private Car createMockCar(String carName) {
        Car car = mock(Car.class);
        when(car.getName()).thenReturn(carName);
        return car;
    }
}
