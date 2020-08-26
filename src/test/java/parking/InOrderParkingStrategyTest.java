package parking;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;

public class InOrderParkingStrategyTest {

    @Test
    public void testCreateReceipt_givenACarAndAParkingLog_thenGiveAReceiptWithCarNameAndParkingLotName() {

        /* Exercise 1, Write a test case on InOrderParkingStrategy.createReceipt()
         * With using Mockito to mock the input parameter */
        //given
        InOrderParkingStrategy inOrderParkingStrategy = new InOrderParkingStrategy();
        ParkingLot parkingLot = mock(ParkingLot.class);
        Car car = mock(Car.class);
        //when
        Receipt receipt = inOrderParkingStrategy.park(Collections.singletonList(parkingLot), car);
        //then
        assertNotNull(receipt);
    }

    @Test
    public void testCreateNoSpaceReceipt_givenACar_thenGiveANoSpaceReceipt() {

        /* Exercise 1, Write a test case on InOrderParkingStrategy.createNoSpaceReceipt()
         * With using Mockito to mock the input parameter */
        //given
        InOrderParkingStrategy inOrderParkingStrategy = new InOrderParkingStrategy();
        Car car = mock(Car.class);
        //when
        Receipt receipt = inOrderParkingStrategy.park(null, car);
        //then
        assertNotNull(receipt);
    }

    @Test
    public void testPark_givenNoAvailableParkingLot_thenCreateNoSpaceReceipt() {

        /* Exercise 2: Test park() method. Use Mockito.spy and Mockito.verify to test the situation for no available parking lot */
        //given
        InOrderParkingStrategy inOrderParkingStrategy = spy(new InOrderParkingStrategy());
        Car car = mock(Car.class);
        //when
        inOrderParkingStrategy.park(Collections.emptyList(), car);
        //then
        verify(inOrderParkingStrategy, times(1)).createNoSpaceReceipt(car);
    }

    @Test
    public void testPark_givenThereIsOneParkingLotWithSpace_thenCreateReceipt() {

        /* Exercise 2: Test park() method. Use Mockito.spy and Mockito.verify to test the situation for one available parking lot */
        //given
        InOrderParkingStrategy inOrderParkingStrategy = spy(new InOrderParkingStrategy());
        ParkingLot parkingLot = mock(ParkingLot.class);
        Car car = mock(Car.class);
        //when
        inOrderParkingStrategy.park(Collections.singletonList(parkingLot), car);
        //then
        verify(inOrderParkingStrategy, times(1)).createReceipt(parkingLot, car);
    }

    @Test
    public void testPark_givenThereIsOneFullParkingLot_thenCreateReceipt() {

        /* Exercise 2: Test park() method. Use Mockito.spy and Mockito.verify to test the situation for one available parking lot but it is full */
        InOrderParkingStrategy inOrderParkingStrategy = spy(new InOrderParkingStrategy());
        ParkingLot parkingLot = mock(ParkingLot.class);
        Car car = mock(Car.class);
        //when
        when(parkingLot.isFull()).thenReturn(true);
        inOrderParkingStrategy.park(Collections.singletonList(parkingLot), car);
        //then
        verify(inOrderParkingStrategy, times(1)).createNoSpaceReceipt(car);
    }

    @Test
    public void testPark_givenThereIsMultipleParkingLotAndFirstOneIsFull_thenCreateReceiptWithUnfullParkingLot() {

        /* Exercise 3: Test park() method. Use Mockito.spy and Mockito.verify to test the situation for multiple parking lot situation */
        //given
        InOrderParkingStrategy inOrderParkingStrategy = spy(new InOrderParkingStrategy());
        ParkingLot parkingLot = mock(ParkingLot.class);
        Car car = mock(Car.class);
        //when
        inOrderParkingStrategy.park(Arrays.asList(parkingLot, parkingLot), car);

        //then
        verify(inOrderParkingStrategy, times(1)).createReceipt(parkingLot, car);
    }


}
