package problem27;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class Example1 {

    // главный метод программы, не забудьте throws FileNotFoundException, иначе программа,
    // работающая с файлом не запустится
    public static void main(String[] args) throws FileNotFoundException {
        // открываем файл, относительный путь строится от корня проекта
        // можно вместо этого закинуть файл куда-нибудь на диск и указать полный путь
        Scanner in = new Scanner(new File("src/main/java/problem27/27_B.txt"));
        //Scanner in = new Scanner(new File("src/main/java/problem27/27_B.txt"));
        int n = in.nextInt();

        // массив, хранящий минимальную сумму, имеющую остаток, равный индексу элемента
        int[] minS = new int[43];
        // массив соответствующих длин этих сумм
        int[] ls = new int[43];
        for (int i = 0; i < 43; i++) {
            // заполняем их -1
            minS[i] = -1;
            ls[i] = -1;
        }
        // сумма всех перебранных элементов
        int sum = 0;
        // минимальная длина, задаём просто большое число
        int minL = 100000;
        // мак
        int maxSum = 0;
        for (int i = 0; i < n; i++) {
            // читаем число
            int a = in.nextInt();
            // прибавляем его к сумме
            sum += a;
            // получаем остаток от деления суммы на 43
            int r =  sum % 43;
            // если сумма делится нацело, у нас готов ответ
            if (sum % 43 == 0) {
                minL = i + 1;
                maxSum = sum;
                continue;
            }
            // если минимальная сумма имеющая соответствующий остаток не сохранена
            if (minS[r] == -1) {
                // сохраняем сумму в массив минимальных сумм по остаткам с соответствующим индексом
                minS[r] = sum;
                // сохраняем длину последовательности
                ls[r] = i + 1;
            }else{ // если сумма для текущего остатка найдена
                // если выкинув сохранённую сумму, мы получим сумму, большую сохранённой
                if (sum - minS[r] > maxSum) {
                    // сохраняем эту сумму в качестве максимальной
                    maxSum = sum - minS[r];
                    // сохраняем длину, как разность между длиной всех чисел
                    // и длиной последовательности, начинающейся с нулевого
                    // элемента, вычитая которую мы получим сумму, делящуюся на 43
                    minL = i + 1 - ls[r];
                    // если новая сумма равна максимальной, но длина меньше
                } else if (sum - minS[r] == maxSum && i + 1 - ls[r] < minL) {
                    // сохраняем новую длину
                    minL = i + 1 - ls[r];
                }
            }
        }

        // выводим минимальную длину
        System.out.println(minL);

    }
}
