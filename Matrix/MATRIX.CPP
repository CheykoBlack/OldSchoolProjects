#include <iostream>
/**
Chelsey Martin
CS 350
Assignment 5
C++ Program that defines a Matrix class, includes a procedure for displaying the Matrix,
    and two overloaded operators for matrix addition and matrix multiplication.
Cleans the created Matrices at the end with the use of the clean method defined in the Matrix class,
    which calls the delete method for the appropriate fields to prevent memory leaks.
**/
using namespace std;

class Matrix {
  public:
    int rows, cols;
    double** values;
    Matrix (double**,int,int);
    void print(){
        for(int row = 0; row < rows; row++){
            for(int col = 0; col < cols; col++){
                cout << "\t" << values[row][col];
            }
            cout << "\n";
        }
    }

    void clean(){
        for(int index = 0; index < rows; index++){
            delete [] values[index];
        }
        delete [] values;
    }

    /**
    Overloads the + operator for Matrix addition.
    **/
    Matrix& operator +(Matrix A){
        double** result;
        if(rows != A.rows || cols != A.cols){
            cout << "Matrix addition not applicable.";
            static Matrix answer(0x0, 0, 0);
            return answer;
        }
        result = new double*[rows];
        for(int index = 0; index < rows; index++){
            result[index] = new double[cols];
        }
        for(int row = 0; row < rows; row++){
            for(int col = 0; col < cols; col++){
                result[row][col] = values[row][col]+A.values[row][col];
            }
        }
        static Matrix answer(result, rows, cols);
        return answer;
    }

    /**
    Overloads the * operator for Matrix multiplication.
    **/
    Matrix& operator *(Matrix A){
        double** result;
        if(cols != A.rows){
            cout << "Matrix multiplication not applicable.";
            static Matrix answer(0x0, 0, 0);
            return answer;
        }
        result = new double*[rows];
        for(int index = 0; index < rows; index++){
            result[index] = new double [A.cols];
        }
        for(int index = 0; index < rows; index++){
            for(int col = 0; col < A.cols; col++){
                result[index][col] = 0.0;
            }
        }
        for(int i = 0; i < rows; i++){
            for(int j = 0; j < A.cols; j++){
                for(int k = 0; k < rows; k++){
                    result[i][j] += values[i][k] * A.values[k][j];
                }
            }
        }
        static Matrix answer(result, rows, A.cols);
        return answer;
    }
};

Matrix::Matrix (double** arr, int r, int c) {
        rows = r;
        cols = c;
        values = new double*[rows];
        for(int index = 0; index < rows; index++){
            values[index] = new double[cols];
        }
        for(int row = 0; row < rows; row++){
            for(int col = 0; col < cols; col++){
                values[row][col] = arr[row][col];
            }
        }
}

int main(){
    int rows, cols;
    cout << "Matrix A\n";
    cout << "Rows: ";
    cin >> rows;
    cout << "Columns: ";
    cin >> cols;
    double** A = new double*[rows];
    for(int index = 0; index < rows; index++){
        A[index] = new double[cols];
    }

    for(int row = 0; row < rows; row++){
        for(int col = 0; col < cols; col++){
            cout << "Matrix A[" << row << "][" << col <<"]: ";
            cin >> A[row][col];
        }
    }

    Matrix matrixA (A, rows, cols);

    cout << "\nMatrix B\n";
    cout << "Rows: ";
    cin >> rows;
    cout << "Columns: ";
    cin >> cols;
    double** B = new double*[rows];
    for(int index = 0; index < rows; index++){
        B[index] = new double[cols];
    }

    for(int row = 0; row < rows; row++){
        for(int col = 0; col < cols; col++){
            cout << "Matrix B[" << row << "][" << col <<"]: ";
            cin >> B[row][col];
        }
    }
    Matrix matrixB (B, rows, cols);

    cout << "\nMatrix A\n";
    matrixA.print();

    cout << "\nMatrix B\n";
    matrixB.print();

    Matrix result = matrixA*matrixB;
    if(result.rows != 0){
        cout << "\nMatrix A * Matrix B\n";
        result.print();
    }
    Matrix result2 = result+matrixB;
    if(result.rows != 0){
        cout << "\n(Matrix A * Matrix B)+ Matrix B\n";
        result2.print();
    }

    matrixA.clean();
    matrixB.clean();
    result.clean();
    result2.clean();
    return 0;
}
