package ba.unsa.etf.rpr.business;

import ba.unsa.etf.rpr.dao.CarDao;
import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.domain.Car;
import ba.unsa.etf.rpr.exceptions.RentACarException;
import org.junit.jupiter.api.*;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class CarManagerTest {

    CarManager carManager;
    MockedStatic<DaoFactory> mockedDao;
    @BeforeEach
    void setUp() throws RentACarException {
        List<Car> carList = new ArrayList<>();
        carList.add(new Car(1,"Volkswagen","Arteon","Red","A12O345",120));
        carList.add(new Car(2,"Volkswagen","Polo","Red","A83E463",60));

        mockedDao = Mockito.mockStatic(DaoFactory.class);
        CarDao mockCarDao = Mockito.mock(CarDao.class);
        when(DaoFactory.carDao()).thenReturn(mockCarDao);
        when(DaoFactory.carDao().getAll()).thenReturn(carList);
        carManager = new CarManager();
    }

    @AfterEach
    public void cleanup() {
        mockedDao.close();
    }

    @Test
    void getAllTest() throws RentACarException {
        List<Car> carList = new ArrayList<>();
        carList.add(new Car(1,"Volkswagen","Arteon","Red","A12O345",120));
        carList.add(new Car(2,"Volkswagen","Polo","Red","A83E463",60));

        assertArrayEquals(new List[]{carList}, new List[]{carManager.getAll()});
    }

    @Test
    void validateRegistrationTest(){
        assertThrows(RentACarException.class, () -> carManager.validateRegistration("A213O000"));
    }
}