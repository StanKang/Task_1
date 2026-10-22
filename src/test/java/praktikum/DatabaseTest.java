package praktikum;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static java.util.stream.Collectors.toList;
import static org.junit.jupiter.api.Assertions.assertEquals;

class DatabaseTest {

    private Database database;

    @BeforeEach
    void setUp() {
        database = new Database();
    }

    @Test
    void availableBunsReturnsThreeBuns() {
        assertEquals(3, database.availableBuns().size());
    }

    @Test
    void availableBunsReturnsBunNamesInOrder() {
        List<String> names = database.availableBuns().stream()
                .map(Bun::getName)
                .collect(toList());

        assertEquals(List.of("black bun", "white bun", "red bun"), names);
    }

    @Test
    void availableIngredientsReturnsIngredientNamesInOrder() {
        List<String> names = database.availableIngredients().stream()
                .map(Ingredient::getName)
                .collect(toList());

        assertEquals(
                List.of("hot sauce", "sour cream", "chili sauce", "cutlet", "dinosaur", "sausage"),
                names);
    }
}
