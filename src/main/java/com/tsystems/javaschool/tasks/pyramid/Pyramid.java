package com.tsystems.javaschool.tasks.pyramid;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Pyramid {
    public Pyramid() {
    }

    public int[][] buildPyramid(List<Integer> inputNumbers) {
        boolean flag;//Флаг для возможности/невозможности построения пирамиды
        int[][] matrix;//Получившаяся матрица
        int size = inputNumbers.size();//Проверяем размер полученного массива

        //Проверим, является ли данное число треугольным
        int count = 0;
        int rows = 1;
        int cols = 1;
        while (count < size) {
            count = count + rows;
            rows++;
            cols = cols + 2;
        }
        rows = rows - 1;//Актуальное число строк
        cols = cols - 2;//Актуальное число столбцов
        flag = size == count;//Если возможно построить матрицу

        if (flag) {
            try {
                List<Integer> sorted = inputNumbers.stream().sorted().collect(Collectors.toList());
                matrix = new int[rows][cols];//Задаем размерность матрице
                for (int[] row : matrix) {
                    Arrays.fill(row, 0);
                }

                int center = (cols / 2);//Находим центральную точку матрицы
                count = 1; // сколько чисел будет в строке
                int arrIdx = 0; // индекс массива

                for (int i = 0, offset = 0; i < rows; i++, offset++, count++) {
                    int start = center - offset;
                    for (int j = 0; j < count * 2; j += 2, arrIdx++) {
                        matrix[i][start + j] = sorted.get(arrIdx);
                    }
                }
            } catch (Exception e) {
                throw new CannotBuildPyramidException();
            }
        }
        else {
            throw new CannotBuildPyramidException();
        }

        return matrix;
    }
}
