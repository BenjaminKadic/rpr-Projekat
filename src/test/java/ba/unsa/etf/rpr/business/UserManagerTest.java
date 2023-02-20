package ba.unsa.etf.rpr.business;

import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.dao.UserDao;
import ba.unsa.etf.rpr.domain.User;
import ba.unsa.etf.rpr.exceptions.RentACarException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class UserManagerTest {
    UserManager userManager;
    MockedStatic<DaoFactory> mockedDao;
    @BeforeEach
    void setUp() throws RentACarException {
        List<User> userList = new ArrayList<>();
        userList.add(new User(1,"A123T4","Neko","Nekic",new Date(1969,2,3)));
        userList.add(new User(2,"A62E63","Hamo","Pipa",new Date(2001,6,9)));

        mockedDao = Mockito.mockStatic(DaoFactory.class);
        UserDao mockUserDao = Mockito.mock(UserDao.class);
        when(DaoFactory.userDao()).thenReturn(mockUserDao);
        when(DaoFactory.userDao().getAll()).thenReturn(userList);
        userManager = new UserManager();
    }

    @AfterEach
    public void cleanup() {
        mockedDao.close();
    }
    @Test
    void validateRegistrationTest(){
        assertThrows(RentACarException.class, () -> userManager.validateLicense("Test123"));
    }
    @Test
    void validateAgeTest() {
        assertDoesNotThrow(() -> userManager.validateAge(Date.valueOf(new Date(System.currentTimeMillis()).toLocalDate().minusYears(23))));
    }

    @Test
    void getAllTest() throws RentACarException {
        List<User> userList = new ArrayList<>();
        userList.add(new User(1,"A123T4","Neko","Nekic",new Date(1969,2,3)));
        userList.add(new User(2,"A62E63","Hamo","Pipa",new Date(2001,6,9)));
        assertArrayEquals(new List[]{userList}, new List[]{userManager.getAll()});
    }
}