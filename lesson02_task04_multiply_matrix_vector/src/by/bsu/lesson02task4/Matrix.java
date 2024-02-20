package by.bsu.lesson02task4;

public class Matrix {
    private int[][] a;
    private int n;
     private int m;
public Matrix(int nn, int mm) {
     n = nn;
     m = mm;
//creation and filling with random values
     a = new int[n][m];
     for (int i = 0; i < n; i++) {
        for (int j = 0; j < m; j++) {
          a[i][j] = (int)(Math.random() * 10);
 }
 }
    show();
}
public Matrix(int nn, int mm, int k) {
     n = nn;
     m = mm;
     a = new int[n][m];
    for (int i = 0; i < n; i++) {
       for (int j = 0; j < m; j++) {
       a[i][j] = k;
 }
 }
          if(k != 0) {
        show();
 }
 }
       public void show() {
       System.out.println("matrix : " + a.length + " by " + a[0].length);
     for (int i = 0; i < a.length; i++) {
     for (int j = 0; j < a[0].length; j++) {
    System.out.print(a[i][j] + " ");
     }
System.out.println();
}
}
public static void main(String[] args) {
int n = 2, m = 3, l = 4;
Matrix p = new Matrix(n, m);
Matrix q = new Matrix(m, l);
Matrix r = new Matrix(n, l, 0);
for (int i = 0; i < p.a.length; i++) {
for (int j = 0; j < q.a[0].length; j++) {
for (int k = 0; k < p.a[0].length; k++) {
r.a[i][j] += p.a[i][k] * q.a[k][j];
}
}
}
System.out.println("matrix multiplication result: ");
r.show();
}
}
