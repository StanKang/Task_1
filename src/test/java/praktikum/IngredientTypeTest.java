package praktikum;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

class IngredientTypeTest {

    @ParameterizedTest
    @CsvSource({
            "SAUCE, SAUCE",
            "FILLING, FILLING"
    })
    void valueOfReturnsMatchingConstant(String constantName, IngredientType expectedType) {
        assertEquals(expectedType, IngredientType.valueOf(constantName));
    }

    @Test
    void valuesContainsSauceAndFillingInOrder() {
        IngredientType[] expectedValues = {IngredientType.SAUCE, IngredientType.FILLING};

        assertArrayEquals(expectedValues, IngredientType.values());
    }
}
