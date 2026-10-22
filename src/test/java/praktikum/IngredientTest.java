package praktikum;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class IngredientTest {

    private static final float DELTA = 0.0001f;

    @ParameterizedTest
    @CsvSource({
            "SAUCE, hot sauce, 100",
            "FILLING, cutlet, 100",
            "FILLING, sausage, 300"
    })
    void getTypeReturnsTypePassedToConstructor(IngredientType type, String name, float price) {
        Ingredient ingredient = new Ingredient(type, name, price);

        assertEquals(type, ingredient.getType());
    }

    @ParameterizedTest
    @CsvSource({
            "SAUCE, hot sauce, 100",
            "FILLING, cutlet, 100",
            "FILLING, sausage, 300"
    })
    void getNameReturnsNamePassedToConstructor(IngredientType type, String name, float price) {
        Ingredient ingredient = new Ingredient(type, name, price);

        assertEquals(name, ingredient.getName());
    }

    @ParameterizedTest
    @CsvSource({
            "SAUCE, hot sauce, 100",
            "FILLING, cutlet, 100",
            "FILLING, sausage, 300"
    })
    void getPriceReturnsPricePassedToConstructor(IngredientType type, String name, float price) {
        Ingredient ingredient = new Ingredient(type, name, price);

        assertEquals(price, ingredient.getPrice(), DELTA);
    }
}
