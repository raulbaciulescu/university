import numpy as np
import pandas as pd
from matplotlib import pyplot as plt
from math import exp
from utils import read_data_all_feat, normalisation


def init_params():
    w1 = np.random.randn(3, 4)
    b1 = np.random.randn(3, 1)
    w2 = np.random.randn(3, 3)
    b2 = np.random.randn(3, 1)
    return w1, b1, w2, b2

def one_hot(y):
    new_y = []
    for i in range(len(y)):
        if y[i] == 'Iris-setosa':
            new_y.append(0)
        if y[i] == 'Iris-versicolor':
            new_y.append(1)
        if y[i] == 'Iris-virginica':
            new_y.append(2)
    y = np.array(new_y)
    one_hot_y = np.zeros((y.size, y.max(initial=0) + 1))
    one_hot_y[np.arange(y.size), y] = 1
    one_hot_y = one_hot_y.T
    return one_hot_y


def back_propagation(z1, a1, z2, a2, w1, w2, x, y):
    one_hot_y = one_hot(y)
    dz2 = a2 - one_hot_y
    dw2 = 1 / m * dz2.dot(a1.T)
    db2 = 1 / m * np.sum(dz2)
    dz1 = w2.T.dot(dz2) * derivate_relu(z1)
    dw1 = 1 / m * dz1.dot(x.T)
    db1 = 1 / m * np.sum(dz1)
    return dw1, db1, dw2, db2


def update_params(w1, b1, w2, b2, dw1, db1, dw2, db2, learning_rate=0.1):
    w1 = w1 - learning_rate * dw1
    b1 = b1 - learning_rate * db1

    w2 = w2 - learning_rate * dw2
    b2 = b2 - learning_rate * db2

    return w1, b1, w2, b2


def get_predictions(a2):
    return np.argmax(a2, 0)


def get_accuracy(predictions, y):
    print(predictions, y)
    return np.sum(predictions == y) / y.size


def gradient_descent(x, y, epochs=1000):
    w1, b1, w2, b2 = init_params()
    for i in range(epochs):
        z1, a1, z2, a2 = forward_propagation(w1, b1, w2, b2, x)
        dw1, db1, dw2, db2 = back_propagation(z1, a1, z2, a2, w1, w2, x, y)
        w1, b1, w2, b2 = update_params(w1, b1, w2, b2, dw1, db1, dw2, db2)
        if i % 50 == 0:
            print('Interation: ', i)
            print('Accuracy: ', get_accuracy(get_predictions(a2), y))

    return w1, b1, w2, b2

data = pd.read_csv('data/iris.csv')
data = np.array(data)
m, n = data.shape
np.random.shuffle(data)
data_dev = data[0:100].T
Y_dev = data_dev[0]
X_dev = data_dev[0:4]
#X_dev = X_dev / 255.

data_train = data[100:m].T
Y_train = data_train[4]
X_train = data_train[0:4]
X_train, X_train = normalisation(X_train.tolist(), X_train.tolist())
X_train = np.array(X_train)
#X_train = X_train / 255.
_,m_train = X_train.shape

def ReLU(Z):
    return np.maximum(Z, 0)


def softmax1(Z):
    A = np.exp(Z) / sum(np.exp(Z))
    return A


def forward_prop1(W1, b1, W2, b2, X):
    Z1 = W1.dot(X) + b1
    A1 = ReLU(Z1)
    Z2 = W2.dot(A1) + b2
    A2 = softmax1(Z2)
    return Z1, A1, Z2, A2


def ReLU_deriv1(Z):
    return Z > 0

def backward_prop1(Z1, A1, Z2, A2, W1, W2, X, Y):
    one_hot_Y = one_hot(Y)
    dZ2 = A2 - one_hot_Y
    dW2 = 1 / m * dZ2.dot(A1.T)
    db2 = 1 / m * np.sum(dZ2)
    dZ1 = W2.T.dot(dZ2) * ReLU_deriv1(Z1)
    dW1 = 1 / m * dZ1.dot(X.T)
    db1 = 1 / m * np.sum(dZ1)
    return dW1, db1, dW2, db2


def update_params1(W1, b1, W2, b2, dW1, db1, dW2, db2, alpha):
    W1 = W1 - alpha * dW1
    b1 = b1 - alpha * db1
    W2 = W2 - alpha * dW2
    b2 = b2 - alpha * db2
    return W1, b1, W2, b2


def get_predictions1(A2):
    return np.argmax(A2, 0)


def get_accuracy1(predictions, Y):
    new_y = []
    for i in range(len(Y)):
        if Y[i] == 'Iris-setosa':
            new_y.append(0)
        if Y[i] == 'Iris-versicolor':
            new_y.append(1)
        if Y[i] == 'Iris-virginica':
            new_y.append(2)
    Y = np.array(new_y)
    print(predictions, Y)
    return np.sum(predictions == Y) / Y.size


def gradient_descent1(X, Y, alpha, iterations):
    W1, b1, W2, b2 = init_params()
    for i in range(iterations):
        Z1, A1, Z2, A2 = forward_prop1(W1, b1, W2, b2, X)
        dW1, db1, dW2, db2 = backward_prop1(Z1, A1, Z2, A2, W1, W2, X, Y)
        W1, b1, W2, b2 = update_params(W1, b1, W2, b2, dW1, db1, dW2, db2, alpha)
        if i % 10 == 0:
            print("Iteration: ", i)
            predictions = get_predictions1(A2)
            print(get_accuracy1(predictions, Y))
    return W1, b1, W2, b2


W1, b1, W2, b2 = gradient_descent1(X_train, Y_train, 0.10, 2000)