package com.company;

public class Main {

    public static void main(String[] args) {
        final int cols = 4;
        final int rows = 4;
        double[][] tdArray = {{1,2,3,4},{5,6,7,8},{9,10,11,12},{13,14,15,16}};

//        var _Matrix = new Matrix(rows, cols);
//        _Matrix.fillMatrix();
      var _Matrix = new Matrix(tdArray);
        _Matrix.printMatrix();

        System.out.print("=============================\n");

        var multipliedMatrix = _Matrix.multiplyMatrixByValue(5);
        multipliedMatrix.printMatrix();
    }
}
