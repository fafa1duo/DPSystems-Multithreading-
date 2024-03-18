package by.bsu.matrixdecomposition;

import java.util.Arrays;

public class Matrix {
  private int[][] values;
  public Matrix(int rows, int columns) {
    values = new int[rows][columns];

  }

  public Matrix(int row, int column, int valueToFill) {
    values = new int[row][column];
    for (int i = 0; i < row; i++) {
      for (int j = 0; j < column; j++) {
        values[i][j] = valueToFill;
      }
    }
  }

  public int getRows() {
	  return values.length;
  }
  public int getColumns() {
	  return values[0].length;
  }
  public int getItem(int i,int j) throws MatrixException {
	  if (checkRange(i,j)) {
		  return values[i][j];
		  
	  }else
	  throw new MatrixException("indexes are not correct");
  }
  public void setItem(int i,int j,int value) {
	  values[i][j]=value;
  }
  
  private boolean checkRange(int i, int j) {
	  if (i < 0 || i > values.length -1
			  || j < 0 
			  || j > values[0].length) {
	  return false;
	  } else {
	  return true;
	  }
  }

	@Override
	public String toString() {
		final String BLANK = " ";
		StringBuilder str = new StringBuilder("\nMatrix: "+
		values.length+"x"+values[0].length + "\n");
		for(int []row:values) {
			for(int values:row)
			str.append(values).append(BLANK);
			
			str.append("\n");
		}
		return str.toString();

	}


	public boolean isCompatibleForMultiplication(Matrix matrix2) {
	
		return false;
	}

	public Matrix multiply(Matrix matrix2) {

		return null;
	}

	public double get(int i, int j) {

		return 0;
	}


}