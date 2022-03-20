package com.company;

import java.util.Arrays;

/**
 * Класс с основными операциями над матрицей
 * @author Никита Аверочкин
 * @version 1.0
 * */
public class Matrix {
    /** Поля количество колонок и строк */
    private int cols, rows;
    /** Поле матрицы */
    private double[][] matrix;

    /**
     * Matrix - Конструктор нулевой матрицы
     * @param rows - количество строк
     * @param cols - количество колонок
     * @throws NegativeArraySizeException - исключение, количество строк или колонок < 0
     * */
    public Matrix(int rows, int cols) throws NegativeArraySizeException {
        if (rows <= 0 || cols <= 0) {
            throw new NegativeArraySizeException("Matrix size cannot be negative");
        }
        this.rows = rows;
        this.cols = cols;
        matrix = new double[rows][cols];
    }

    /**
     * Matrix - Конструктор инициализации матрицы готовым двумерным массивом
     * @param tdArray - двумерный массив, которым будет заполнена матрица
     * @throws IllegalArgumentException - исключение, если переданный массив null
     */
    public Matrix(double[][] tdArray) throws IllegalArgumentException {
        if (tdArray == null) {
            throw new NullPointerException("Two-dimensional array cannot be null");
        }
        rows = tdArray.length;
        cols = tdArray[0].length;
        matrix = tdArray;
    }

    /**
     * Matrix - Конструктор матрицы, как копия другой матрицы
     * @param anotherMatrix - другая матрица, с которой берутся значения
     * @throws IllegalArgumentException - исключение, если переданная матрица null
     */
    public Matrix(Matrix anotherMatrix) throws IllegalArgumentException {
        if (anotherMatrix == null) {
            throw new NullPointerException("Matrix cannot be null");
        }

        matrix = new double[anotherMatrix.rows][anotherMatrix.cols];
        rows = anotherMatrix.rows;
        cols = anotherMatrix.cols;
        for (int i = 0; i < rows; i++) {
            System.arraycopy(matrix[i], 0, anotherMatrix.matrix[i], 0, cols);
        }
//        глубокая копия
//        for (int i = 0; i < rows; i++) {
//            for (int j = 0; j < cols; j++) {
//                this.matrix[i][j] = anotherMatrix.matrix[i][j];
//            }
//        }
    }

    /**
     * getRows() - геттер
     * @return rows - получения количества строк в матрице
     */
    public int getRows() {
        return rows;
    }

    /**
     * getCols() - геттер
     * @return cols - получение количества столбцов в матрице
     */
    public int getCols() {
        return cols;
    }

    /**
     * getMatrixValue() - Получение значения матрицы по определенной строке и колонке
     * @param rows - индекс определенной строки
     * @param cols - индекс определенной колонки
     * @return matrix[row][column] - значение по определеннй строке и колонке
     * @throws IndexOutOfBoundsException - исключение, если в матрице нет такого номера строки или колонки
     */
    public double getMatrixValue(int rows, int cols) throws IndexOutOfBoundsException {
        if (rows >= this.rows || cols >= this.cols) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
        return matrix[rows][cols];
    }

    /**
     * setMatrixValue() - Задание значения матрицы по определенной строке и колонке
     * @param value - значение для задания
     * @param rows - индекс определенной строки
     * @param cols - индекс определенной колонки
     * @throws IndexOutOfBoundsException - исключение, если в матрице нет такого номера строки или колонки
     */
    public void setMatrixValue(double value, int cols, int rows) throws IndexOutOfBoundsException {
        if (cols >= this.cols || rows >= this.rows) {
            throw new IndexOutOfBoundsException("Invalid index in set operation");
        }
        matrix[cols][rows] = value;
    }

    /**
     * fillMatrix() - заполнение матрицы
     */
    public void fillMatrix() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                this.matrix[i][j] = -100 + (int)(Math.random() * 200);
            }
        }
    }

    /**
     * printMatrix() - тестовая печать матрицы
     */
     public void printMatrix() {
         Arrays.stream(this.matrix).map(Arrays::toString).forEach(System.out::println);
     }

    /**
     * getTdArray() - получение двумерного массива на основе матрицы
     * @return tdArray - двумерный массив
     */
    public double[][] getTdArray() {
        double[][] tdArray = new double[rows][cols];
        for (int i = 0; i < rows; i++) {
            System.arraycopy(matrix[i], 0, tdArray[i], 0, cols);
        }
        return tdArray;
    }

    /**
     * add() - Сложение матриц
     * @param anotherMatrix - матрица, которая прибавляется к текущей
     * @return resultMatrix - суммированная матрица
     * @throws UnsupportedOperationException - исключение, если матрицы имеют разные размеры
     */
    public Matrix addMatrix(Matrix anotherMatrix) throws UnsupportedOperationException {
        if (cols != anotherMatrix.cols || rows != anotherMatrix.rows) {
            throw new UnsupportedOperationException("Each matrix should have equal sizes to sum it");
        }

        Matrix resultMatrix = new Matrix(cols, rows);
        for (int i = 0; i < cols; i++) {
            for (int j = 0; j < rows; j++) {
                resultMatrix.matrix[i][j] = this.matrix[i][j] + anotherMatrix.matrix[i][j];
            }
        }
        return resultMatrix;
    }

    /**
     * substractMatrix() - Вычитание матриц
     * @param anotherMatrix - матрица для вычитания из текущей
     * @return resultMatrix - матрица после вычитания
     * @throws UnsupportedOperationException - исключение, если матрицы имеют разные размеры
     */
    public Matrix substractMatrix(Matrix anotherMatrix) throws UnsupportedOperationException {
        if (cols != anotherMatrix.cols || rows != anotherMatrix.rows) {
            throw new UnsupportedOperationException("Each matrix should have equal sizes to subtract it");
        }

        Matrix resultMatrix = new Matrix(cols, rows);
        for (int i = 0; i < cols; i++) {
            for (int j = 0; j < rows; j++) {
                resultMatrix.matrix[i][j] = this.matrix[i][j] - anotherMatrix.matrix[i][j];
            }
        }
        return resultMatrix;
    }

    /**
     * multiplyMatrix() - Умножение матриц
     * @param anotherMatrix - матрица для умножения с текущей
     * @return resultMatrix - матрица после умножения
     * @throws UnsupportedOperationException - исключение, если число колонок первой матрицы не совпадает с числом строк второй матрицы
     */
    public Matrix multiplyMatrix(Matrix anotherMatrix) throws UnsupportedOperationException {
        if (cols != anotherMatrix.rows) {
            throw new UnsupportedOperationException("Wrong sized matrices in multiplication");
        }

        Matrix resultMatrix = new Matrix(cols, anotherMatrix.rows);
        for (int i = 0; i < resultMatrix.cols; i++) {
            for (int j = 0; j < resultMatrix.rows; j++) {
                double value = 0;
                for (int k = 0; k < cols; k++) {
                    value += this.matrix[i][k] * anotherMatrix.matrix[k][j];
                }
                resultMatrix.matrix[i][j] = value;
            }
        }
        return resultMatrix;
    }

    /**
     * multiplyMatrixByValue() - Умножение матрицы на определенное число (int)
     * @param value - число, на которое матрица будет умножена
     * @return resultMatrix - матрица после умножения
     */
    public Matrix multiplyMatrixByValue(int value) {
        Matrix resultMatrix = new Matrix(rows, cols);
        for (var i = 0; i < resultMatrix.rows; i++) {
            for (var j = 0; j < resultMatrix.cols; j++) {
                resultMatrix.matrix[i][j] = this.matrix[i][j] * value;
            }
        }
        return resultMatrix;
    }

    /**
     * transposition() - Транспонирование матрицы (замена строк на столбцы)
     */
    public void transposition() {
        Matrix resultMatrix = new Matrix(rows, cols);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                resultMatrix.matrix[j][i] = this.matrix[i][j];
            }
        }

        rows = resultMatrix.matrix.length;
        cols = resultMatrix.matrix[0].length;
        this.matrix = resultMatrix.matrix;
    }

    /**
     * generateIdentityMatrix() - Генерация единичной матрицы (единицы на главной диагонали)
     * @param n - размер генерируемой матрицы
     * @return resultMatrix - единичная матрица
     */
    public static Matrix generateIdentityMatrix(int n) {
        Matrix resultMatrix = new Matrix(n,n);
        for (var i = 0; i < n; i++) {
            resultMatrix.matrix[i][i] = 1;
        }

        return resultMatrix;
    }

    /**
     * reversedMatrix() - нахождение обратной матрицы методом дописывания единичной матрицы (метод Гаусса)
     * @return resultMatrix - обратная матрица
     * @throws UnsupportedOperationException - исключение, если определитель матрицы == 0
     */
    public Matrix reversedMatrix() throws UnsupportedOperationException {
        if (getDeterminant() == 0) {
            throw new UnsupportedOperationException("Reversed matrix doesn't exist");
        }

        Matrix copy = new Matrix(rows, cols);
        for (int i = 0; i < rows; i++) {
            if (cols >= 0) System.arraycopy(matrix[i], 0, copy.matrix[i], 0, cols);
        }

        Matrix resultMatrix = generateIdentityMatrix(cols);
        for (int i = 0; i < cols; i++) {
            double multiplier = copy.matrix[i][i];
            // Деление строки на множитель
            for (int j = 0; j < cols; j++) {
                copy.matrix[i][j] /= multiplier;
                resultMatrix.matrix[i][j] /= multiplier;
            }
            for (int j = 0; j < rows; j++) {
                if (j != i) {
                    var localMultiplier = copy.matrix[j][i];
                    for (int k = 0; k < cols; k++) {
                        copy.matrix[j][k] -= localMultiplier * copy.matrix[i][k];
                        resultMatrix.matrix[j][k] -= localMultiplier * resultMatrix.matrix[i][k];
                    }
                }
            }
        }

        return resultMatrix;
    }

    /**
     * Нахождение определителя
     * @return determinant - определитель матрицы
     */
    public double getDeterminant() {
        double determinant = 0;

        if (rows == 1 && cols == 1) {
            determinant = this.matrix[0][0];
        }
        if (rows == 2 && cols == 2) {
            determinant = matrix[0][0] * matrix[1][1] - matrix[0][1] * matrix[1][0];
            return determinant;
        }
        if (rows == 3 && cols == 3) {
            determinant = matrix[0][0] * matrix[1][1] * matrix[2][2] + matrix[2][0] * matrix[0][1] * matrix[1][2] +
                    matrix[0][2] * matrix[1][0] * matrix[2][1] - matrix[2][0] * matrix[1][1] * matrix[0][2] -
                    matrix[1][0] * matrix[0][1] * matrix[2][2] - matrix[2][1] * matrix[1][2] * matrix[0][0];
            return determinant;
        }

        for (int i = 0; i < cols; i++) {
            int j = 0;
            Matrix tmp = new Matrix(rows - 1, cols - 1);
            while (j < i) {
                int k = 1;
                while (k < rows) {
                    tmp.matrix[k - 1][j] = matrix[k][j];
                    k++;
                }
                j++;
            }

            j = i + 1;
            while (j < cols) {
                int k = 1;
                while (k < rows) {
                    tmp.matrix[k - 1][j - 1] = matrix[k][j];
                    k++;
                }
                j++;
            }

            if (i % 2 == 0) {
                determinant += matrix[0][i] * tmp.getDeterminant();
            } else {
                determinant -= matrix[0][i] * tmp.getDeterminant();
            }
        }

        return determinant;
    }

    /**
     * getAlgebraicComplement() - Получение алгебраического дополнения матрицы
     * @param row - строка алгебраического дополнения
     * @param column столбец алгебраического дополнения
     * @return algebraicComplement - алгебраическое дополнение
     */
    public double getAlgebraicComplement(int row, int column) {
        double algebraicComplement = 0;
        Matrix tmp = new Matrix(rows - 1, cols - 1);

        int i = 0;
        while (i < row) {
            int j = 0;
            while (j < column) {
                tmp.matrix[i][j] = this.matrix[i][j];
                j++;
            }

            j = column + 1;

            while (j < cols) {
                tmp.matrix[i][j - 1] = this.matrix[i][j];
                j++;
            }
            i++;
        }

        i = row + 1;
        while (i < rows) {
            int j = 0;
            while (j < column) {
                tmp.matrix[i - 1][j] = this.matrix[i][j];
                j++;
            }

            j = column + 1;

            while (j < cols) {
                tmp.matrix[i - 1][j - 1] = this.matrix[i][j];
                j++;
            }
            i++;
        }

        algebraicComplement = tmp.getDeterminant();

        if ((row + column) % 2 != 0) {
            algebraicComplement = -algebraicComplement;
        }

        return algebraicComplement;
    }

    /**
     * getAlliedMatrix - нахождение присоединенной матрицы (состоящей из алгебраических дополнений)
     * @return result - присоединенная матрица
     */
    public Matrix getAlliedMatrix() {
        var result = new Matrix(rows, cols);

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                result.matrix[i][j] = getAlgebraicComplement(i, j);
            }
        }

        return result;
    }
}
