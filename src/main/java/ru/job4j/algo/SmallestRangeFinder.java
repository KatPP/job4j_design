package ru.job4j.algo;

import java.util.Arrays;

class SmallestRangeFinder {
    /**
     * Вычислительная сложность: Big(O) = O(n).
     * Пространственная сложность:
     * Переменные: 20 байт.
     * Массив 'res': 8 байт заголовок, 4 байта - размер массива, 2 переменные * тип переменных: 8 байт.
     * Если массив равен null: для хранения null нужен 1 байт.
     * Итоговая пространственная сложность: 40 байт/21 байт, в зависимости от результата.
     */
    public static int[] findSmallestRange(int[] nums, int k) {
        int[] res = new int[2];
        int left = 0;
        int right = left + 1;
        int n = nums.length;
        int counter = 1;
        int start = left;
        while (right < n) {
            if (nums[left] != nums[right]) {
                counter++;
                right++;
                left++;
            } else {
                left = right;
                start = left;
                right = left + 1;
                counter = 1;
            }
            if (counter == k) {
                res[0] = start;
                res[1] = right - 1;
                break;
            }
        }
        if (counter != k) {
            res = null;
        }
        return res;
    }

    public static void main(String[] args) {
        int[] nums = {1, 3, 5, 7, 9};
        int k = 3;
        int[] result = findSmallestRange(nums, k);
        if (result != null) {
            System.out.println("Наименьший диапазон с " + k + " различными элементами: " + Arrays.toString(result));
        } else {
            System.out.println("Такой диапазон не существует.");
        }
    }
}
