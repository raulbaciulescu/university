import numpy as np


class NeuralNetwork:
    def __init__(self):
        pass

    def init_params(self, outputs_number, features):
        w1 = np.random.rand(outputs_number, features)
        b1 = np.random.rand(outputs_number, 1)
        print('b1')
        print(w1)
        w2 = np.random.rand(outputs_number, outputs_number)
        print(w2)
        b2 = np.random.rand(outputs_number, 1)
        return w1, b1, w2, b2

    def relu(self, z):
        return np.maximum(z, 0)

    def softmax(self, z):
        return np.exp(z) / sum(np.exp(z))

    def forward_prop(self, w1, b1, w2, b2, x):
        z1 = w1.dot(x) + b1
        a1 = self.relu(z1)
        z2 = w2.dot(a1) + b2
        a2 = self.softmax(z2)
        return z1, a1, z2, a2

    def ReLU_deriv(self, Z):
        return Z > 0

    def one_hot(self, y):
        # new_y = []
        # for i in range(len(Y)):
        #     if Y[i] == 'Iris-setosa':
        #         new_y.append(0)
        #     if Y[i] == 'Iris-versicolor':
        #         new_y.append(1)
        #     if Y[i] == 'Iris-virginica':
        #         new_y.append(2)
        # y = np.array(new_y)
        one_hot_y = np.zeros((y.size, y.max(initial=0) + 1))
        one_hot_y[np.arange(y.size), y] = 1
        one_hot_y = one_hot_y.T
        return one_hot_y

    def backward_prop(self, Z1, A1, Z2, A2, W1, W2, X, Y, m):
        one_hot_Y = self.one_hot(Y)

        dZ2 = A2 - one_hot_Y

        dW2 = 1 / m * dZ2.dot(A1.T)
        db2 = 1 / m * np.sum(dZ2)
        #----------------------------------
        dZ1 = W2.T.dot(dZ2) * self.ReLU_deriv(Z1)

        dW1 = 1 / m * dZ1.dot(X.T)
        db1 = 1 / m * np.sum(dZ1)
        return dW1, db1, dW2, db2

    def update_params(self, W1, b1, W2, b2, dW1, db1, dW2, db2, alpha):
        W1 = W1 - alpha * dW1
        b1 = b1 - alpha * db1
        W2 = W2 - alpha * dW2
        b2 = b2 - alpha * db2
        return W1, b1, W2, b2

    def get_predictions(self, A2):
        return np.argmax(A2, 0)

    def get_accuracy(self, predictions, Y):
        # new_y = []
        # for i in range(len(Y)):
        #     if Y[i] == 'Iris-setosa':
        #         new_y.append(0)
        #     if Y[i] == 'Iris-versicolor':
        #         new_y.append(1)
        #     if Y[i] == 'Iris-virginica':
        #         new_y.append(2)
        # Y = np.array(new_y)
        print(predictions, Y)
        return np.sum(predictions == Y) / Y.size

    def gradient_descent(self, X, Y, alpha, iterations, n, m, outputs_number):
        W1, b1, W2, b2 = self.init_params(outputs_number, n)
        for i in range(iterations):
            Z1, A1, Z2, A2 = self.forward_prop(W1, b1, W2, b2, X)
            dW1, db1, dW2, db2 = self.backward_prop(Z1, A1, Z2, A2, W1, W2, X, Y, m)
            W1, b1, W2, b2 = self.update_params(W1, b1, W2, b2, dW1, db1, dW2, db2, alpha)
            if i % 10 == 0:
                print("Iteration: ", i)
                predictions = self.get_predictions(A2)
                print(self.get_accuracy(predictions, Y))
        return W1, b1, W2, b2

    def predict(self, x, w1, b1, w2, b2):
        _, _, _, a2 = self.forward_prop(w1, b1, w2, b2, x)
        predictions = self.get_predictions(a2)
        return predictions

    def compute_error(self, computed_outputs, test_outputs):
        error = 0.0
        for t1, t2 in zip(computed_outputs, test_outputs):
            error += (t1 - t2) ** 2
        error = error / len(test_outputs)
        return error


