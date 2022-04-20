package problem19;

public class Example2 {

    // ход Пети, результат отвечает на вопрос, удовлетворяется ли
    // требование задачи
    static boolean player1(int l, int r, int step) {
        for (int i = 0; i < step; i++) {
            System.out.print(" ");
        }
        System.out.println("player1 "+l+" "+r);
        // если это первый ход Пети, тогда Ваня пока что
        // не сделал ни одного хода
        if (step == 1) {
            // выигрышная стратегия подразумевает, что
            // при любом поведении Вани требование
            // задачи должно быть удовлетворено, поэтому И
            return player2(l + 1, r, step) &&
                    player2(l, r + 1, step) &&
                    player2(l * 2, r, step) &&
                    player2(l, r * 2, step);
        } else { // если это второй ход Пети, т.е. Ваня сделал уже один ход
            // если Ваня при этом победил,
            if (l + r >= 77)
                // требование задачи не выполнено
                return false;

            // каким-то своим ходом Петя может выиграть,
            // то условие задачи будет выполнено, поэтому ИЛИ
            return player2(l + 1, r, step) ||
                    player2(l, r + 1, step) ||
                    player2(l * 2, r, step) ||
                    player2(l, r * 2, step);
        }
    }

    // ход Вани, результат отвечает на вопрос, удовлетворяется ли
    // требование задачи
    static boolean player2(int l, int r, int step) {
        for (int i = 0; i < step; i++) {
            System.out.print(" ");
        }
        System.out.println("player2: "+l+" "+r);
        // обработка состояния игры после хода Пети
        // если Петя сделал первый ход
        if (step == 1) {
            // если он при этом победил,
            if (l + r >= 77)
                // требование задачи не выполнено
                return false;

            // разные ходы Вани
            // нам нужно, чтобы при любом ходе Вани у Пети
            // требование задачи было выполнено
            return player1(l + 1, r, step + 1) &&
                    player1(l, r + 1, step + 1) &&
                    player1(l * 2, r, step + 1) &&
                    player1(l, r * 2, step + 1);
        } else { // если Петя сделал второй ход
            // он должен победить
            return l + r >= 77;
        }
    }


    public static void main(String[] args) {
        // перебираем состояние кучи
        for (int s = 31; s < 32; s++) {
            // запускаем обработку первого шага Пети, если
            // нас устраивает результат,
            if (player1(7, s, 1)) {
                // выводим кол-во камней во второй куче
                System.out.println(s);
            }
        }
    }
}
