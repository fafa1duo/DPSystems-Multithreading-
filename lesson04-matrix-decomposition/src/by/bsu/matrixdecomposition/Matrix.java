package by.bsu.matrixdecomposition;

import java.util.Arrays;

public class Matrix {
  private int[][] values;
  //private int row;//==values.length
  //private int column;//==values[0].length

  public Matrix(int rows, int columns) {
    //row = newSows;
    //column = newColumns;
// creation and filling with random values
    values = new int[rows][columns];

    //show();
  }

  public Matrix(int row, int column, int valueToFill) {
//    row = newSows;
//    column = newColumns;
    values = new int[row][column];
    for (int i = 0; i < row; i++) {
      for (int j = 0; j < column; j++) {
        values[i][j] = valueToFill;
      }
    }
//    if (valueToFill != 0) {
//      show();
//    }
  }

  public int getRows() {
	  return values.length;
  }
  public int getColumns() {
	  return values[0].length;
  }
  public int getItem(int i,int j) throws MatrixException {
	  //0<j<value.length
	  //0<j<value[0].length
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
//  public void show() {
//    System.out.println("matrix : " + values.length + " by " + values[0].length);
//    for (int i = 0; i < values.length; i++) {
//      for (int j = 0; j < values[0].length; j++) {
//        System.out.print(values[i][j] + " ");
//      }
//      System.out.println();
//    }
//  }

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
//		return "Matrix [values=" + Arrays.toString(values) + "]";
	}

//  public static void main(String[] args) {
//    int n = 2, m = 3, l = 4;
//    Matrix p = new Matrix(n, m);
//    Matrix q = new Matrix(m, l);
//    Matrix r = new Matrix(n, l, 0);
//    for (int i = 0; i < p.values.length; i++) {
//      for (int j = 0; j < q.values[0].length; j++) {
//        for (int k = 0; k < p.values[0].length; k++) {
//          r.values[i][j] += p.values[i][k] * q.values[k][j];
//        }
//      }
//    }
//    System.out.println("matrix multiplication result: ");
//    r.show();
//  }
}