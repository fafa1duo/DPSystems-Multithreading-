package by.bsu.lesson02task4;


public class Main {
	public static void main(String[] args) {
		try {
			MatrixCreator creator = new MatrixCreator();
			Matrix matrix1 = creator.create(2,3); //new Matrix(2, 3);
			creator.fillRandomized(matrix1, 2, 8);
			System.out.println("Matrix first is: " + matrix1);
			Matrix matrix2 = new Matrix(3, 4);
			creator.fillRandomized(matrix2, 2, 7);
			System.out.println("Matrix second is: " + matrix2);
//			Multiplicator multiplicator = new Multiplicator();
//			Matrix result = multiplicator.multiply(matrix1, matrix2);
//			System.out.println("Matrices product is " + result);
		} catch (MatrixException e) {
			System.err.println("Error of creating matrix " + e);
		}
	}
}