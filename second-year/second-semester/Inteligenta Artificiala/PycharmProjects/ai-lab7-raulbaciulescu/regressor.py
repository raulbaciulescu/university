import numpy as np


class Regressor:
    def __init__(self):
        self.intercept = 0.0
        self.coef = []

    def multiply(self, matrix1, matrix2):
        matrix = [[0 for _ in range(len(matrix2[0]))] for _ in range(len(matrix1))]
        for i in range(len(matrix1)):
            for j in range(len(matrix2[0])):
                for k in range(len(matrix1[0])):
                    matrix[i][j] += matrix1[i][k] * matrix2[k][j]
        return matrix

    def transpose(self, matrix):
        return [[matrix[j][i] for j in range(len(matrix))] for i in range(len(matrix[0]))]


    def inverse_np(self, matrix):
        return np.linalg.inv(matrix)

    def minor(self, matrix, i, j):
        return [row[:j] + row[j + 1:] for row in (matrix[:i] + matrix[i + 1:])]

    def determinant(self, matrix):
        if len(matrix) == 2:
            return matrix[0][0] * matrix[1][1] - matrix[0][1] * matrix[1][0]

        determinant = 0
        for c in range(len(matrix)):
            determinant += ((-1) ** c) * matrix[0][c] * self.determinant(self.minor(matrix, 0, c))
        return determinant

    def inverse(self, matrix):
        determinant = self.determinant(matrix)
        if determinant == 0:
            raise Exception("determinant 0")
        # special case for 2x2 matrix:
        if len(matrix) == 2:
            return [[matrix[1][1] / determinant, -1 * matrix[0][1] / determinant],
                    [-1 * matrix[1][0] / determinant, matrix[0][0] / determinant]]

        # find matrix of cofactors
        cofactors = []
        for r in range(len(matrix)):
            cofactor_row = []
            for c in range(len(matrix)):
                minor = self.minor(matrix, r, c)
                cofactor_row.append(((-1) ** (r + c)) * self.determinant(minor))
            cofactors.append(cofactor_row)
        cofactors = self.transpose(cofactors)
        for r in range(len(cofactors)):
            for c in range(len(cofactors)):
                cofactors[r][c] = cofactors[r][c] / determinant
        return cofactors

    def fit(self, input, output):
        transpose = self.transpose(input)
        self.coef = self.multiply(self.multiply(self.inverse(self.multiply(transpose, input)), transpose), output)
        self.intercept = self.coef[0][0]
        self.coef.pop(0)
        self.coef = [item for sublist in self.coef for item in sublist]

    def predict(self, inputs):
        rez = []
        for x in inputs:
            rez.append(self.coef[0] * float(x[0]) + self.coef[1] * float(x[1]) + float(self.intercept))
        return rez

    def error_mse(self, computed_outputs, validation_outputs):
        error = 0.0
        for t1, t2 in zip(computed_outputs, validation_outputs):
            error += (t1 - t2) ** 2
        error = error / len(validation_outputs)
        return error