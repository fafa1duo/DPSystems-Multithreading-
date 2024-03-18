package by.bsu.matrixdecomposition;

public class Vector{
private int[] values;

public Vector (int columns) {
values = new int[columns];
 }
@Override
public String toString() {
final String BLANK = " ";
StringBuilder str = new StringBuilder("\nVector : "
+ values. length + "\n");
for (int value: values) {
str. append (value). append (BLANK) ;
}
return str. toString();
  }
}