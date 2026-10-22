package praktikum;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BurgerTest {

    private static final float DELTA = 0.0001f;

    private static Locale originalLocale;

    @Mock
    private Bun bun;

    @Mock
    private Ingredient firstIngredient;

    @Mock
    private Ingredient secondIngredient;

    @Mock
    private Ingredient thirdIngredient;

    private Burger burger;

    @BeforeAll
    static void setUpLocale() {
        originalLocale = Locale.getDefault();
        Locale.setDefault(Locale.US);
    }

    @AfterAll
    static void restoreLocale() {
        Locale.setDefault(originalLocale);
    }

    @BeforeEach
    void setUp() {
        burger = new Burger();
    }

    @Test
    void setBunsSetsBun() {
        burger.setBuns(bun);

        assertSame(bun, burger.bun);
    }

    @Test
    void addIngredientAddsIngredientToEndOfList() {
        burger.addIngredient(firstIngredient);
        burger.addIngredient(secondIngredient);

        assertEquals(List.of(firstIngredient, secondIngredient), burger.ingredients);
    }

    @Test
    void removeIngredientRemovesIngredientByIndex() {
        burger.addIngredient(firstIngredient);
        burger.addIngredient(secondIngredient);

        burger.removeIngredient(0);

        assertEquals(List.of(secondIngredient), burger.ingredients);
    }

    @Test
    void moveIngredientChangesIngredientPosition() {
        burger.addIngredient(firstIngredient);
        burger.addIngredient(secondIngredient);
        burger.addIngredient(thirdIngredient);

        burger.moveIngredient(0, 2);

        assertEquals(List.of(secondIngredient, thirdIngredient, firstIngredient), burger.ingredients);
    }

    @ParameterizedTest
    @CsvSource({
            "100, 100, 200, 500",
            "200, 0, 0, 400",
            "300.5, 10.25, 20.25, 631.5"
    })
    void getPriceReturnsDoubleBunPricePlusIngredientsPrice(float bunPrice, float firstIngredientPrice,
                                                           float secondIngredientPrice, float expectedPrice) {
        when(bun.getPrice()).thenReturn(bunPrice);
        when(firstIngredient.getPrice()).thenReturn(firstIngredientPrice);
        when(secondIngredient.getPrice()).thenReturn(secondIngredientPrice);
        burger.setBuns(bun);
        burger.addIngredient(firstIngredient);
        burger.addIngredient(secondIngredient);

        assertEquals(expectedPrice, burger.getPrice(), DELTA);
    }

    @Test
    void getReceiptReturnsReceiptWithBunIngredientsAndPrice() {
        when(bun.getName()).thenReturn("black bun");
        when(bun.getPrice()).thenReturn(100f);
        when(firstIngredient.getType()).thenReturn(IngredientType.SAUCE);
        when(firstIngredient.getName()).thenReturn("hot sauce");
        when(firstIngredient.getPrice()).thenReturn(100f);
        when(secondIngredient.getType()).thenReturn(IngredientType.FILLING);
        when(secondIngredient.getName()).thenReturn("cutlet");
        when(secondIngredient.getPrice()).thenReturn(200f);
        burger.setBuns(bun);
        burger.addIngredient(firstIngredient);
        burger.addIngredient(secondIngredient);
        String expectedReceipt = String.format(Locale.US,
                "(==== black bun ====)%n= sauce hot sauce =%n= filling cutlet =%n(==== black bun ====)%n%nPrice: %f%n",
                500f);

        assertEquals(expectedReceipt, burger.getReceipt());
    }
}
