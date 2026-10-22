package praktikum;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BunTest {

    private static final float DELTA = 0.0001f;

    @ParameterizedTest
    @CsvSource({
            "black bun, 100",
            "white bun, 200",
            "red bun, 300.5"
    })
    void getNameReturnsNamePassedToConstructor(String name, float price) {
        Bun bun = new Bun(name, price);

        assertEquals(name, bun.getName());
    }

    @ParameterizedTest
    @CsvSource({
            "black bun, 100",
            "white bun, 200",
            "red bun, 300.5"
    })
    void getPriceReturnsPricePassedToConstructor(String name, float price) {
        Bun bun = new Bun(name, price);

        assertEquals(price, bun.getPrice(), DELTA);
    }
}
