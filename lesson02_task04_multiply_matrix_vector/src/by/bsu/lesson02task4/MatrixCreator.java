package by.bsu.lesson02task4;

public class MatrixCreator {
	public Matrix create(int rows, int columns) throws MatrixException{
	    if(rows < 1 || columns < 1) {
	      throw new MatrixException("row and columns should be greater then zero");
	    }
	    return new Matrix(rows, columns);
	  }
	//[minValue,manValue)
	public void fillRandomized(Matrix matrix,int minValue,int maxValue) throws MatrixException{
	    for (int i = 0; i < matrix.getRows(); i++) {
	        for (int j = 0; j < matrix.getColumns(); j++) {
	          matrix.setItem(i, j, (int) (Math.random() * (maxValue-minValue))+minValue);
	        }
	      }
	}
}