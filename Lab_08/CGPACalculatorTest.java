import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import com.example.*;

class CGPACalculatorTest {

    private final CGPACalculator calculator = new CGPACalculator();

    @Test
    void testCalculateCGPAWithNormalInput() {
        double[] grades = { 3.0, 3.5, 4.0, 2.5 };
        double expectedCGPA = 3.25;
        assertEquals(expectedCGPA, calculator.calculateCGPA(grades), 0.001);
    }

    @Test
    void testCalculateCGPAWithBoundaryConditions() {
        double[] grades = { 0.0, 4.0, 4.0, 0.0 };
        double expectedCGPA = 2.0;
        assertEquals(expectedCGPA, calculator.calculateCGPA(grades), 0.001);
    }

    @Test
    void testCalculateCGPAWithEmptyInput() {
        double[] grades = {};
        double expectedCGPA = 0.0;
        assertEquals(expectedCGPA, calculator.calculateCGPA(grades), 0.001);
    }

    @Test
    void testCalculateCGPAWithInvalidInput() {
        double[] grades = { 3.0, 4.1, 2.5, -1.0 };
        assertThrows(IllegalArgumentException.class, () -> calculator.calculateCGPA(grades));
    }
}
