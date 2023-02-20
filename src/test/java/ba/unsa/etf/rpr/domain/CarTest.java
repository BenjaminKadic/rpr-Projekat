package ba.unsa.etf.rpr.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CarTest {
    Car arteon=new Car(1,"Volkswagen","Arteon","Red","A12O345",120);

    @Test
    void getMakeTest() {
        assertEquals("Volkswagen",arteon.getMake());
    }

    @Test
    void setModelTest() {
        arteon.setModel("Passat");
        assertEquals("Passat", arteon.getModel());
    }

    @Test
    void getPriceTest() {
        assertEquals(120, arteon.getPrice());
    }

    @Test
    void compareToTest() {
        int comparison=0;
        Car benz=new Car(3, "Mercedes", "GTR","Grey", "A00A000", 300);
        if(arteon.compareTo(benz)>0) comparison=1;
        else if (arteon.compareTo(benz)<0) comparison=-1;
        assertEquals(1,comparison);
    }

    @Test
    void toStringTest(){
        assertEquals("Volkswagen Arteon registration: A12O345", arteon.toString());
    }
}