import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class EvenCalculatorTest {

    @Test
    public void testCalculateEven() {
        // Тестовые данные
        byte[] data1 = {1, 2, 3, -1, 0};
        byte[] data2 = { -127, 0, -1};

        // Ожидаемые результаты
        int expectedEven1 = 0;
        int expectedEven2 = 0;

        // Вычисление контрольных чисел четности
        int even1 = EvenCalculator.calculateEven(data1);
        int even2 = EvenCalculator.calculateEven(data2);

        // Проверка результатов
        assertEquals(expectedEven1, even1);
        assertEquals(expectedEven2, even2);
    }
}

class EvenCalculator {

    public static int calculateEven(byte[] data) {
        int even = 0;
        for (byte b : data) {
            int count = 0;
            for (int i = 0; i < 8; i++) {
                count += (b >> i & 1);
            }
            if (count % 2 == 0) {
                even += 0;
            } else {
                even += 1;
            }
        }
        return even % 2;
    }
}
