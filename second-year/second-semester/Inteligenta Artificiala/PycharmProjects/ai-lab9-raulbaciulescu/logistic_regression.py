import random
from math import exp

import matplotlib.pyplot as plt
import numpy as np


def sigmoid(x):
    return 1 / (1 + exp(-x))


class MyLogisticRegression:
    def __init__(self):
        self.intercept = 0.0
        self.coef = []

    def fit_batch(self, x, y, learning_rate=0.001, no_epochs=5000):
        # x = [[feat1, feat2, feat3], [feat1, feat2, feat3],...]
        # y = [out1, out2,...]
        self.coef = [random.random() for _ in range(len(x[0]))]  # beta or w coefficients
        y = np.array(y)
        xx = np.array(x)
        epoch_list = []
        cost_list = []
        for epoch in range(no_epochs):
            h = self.hypothesis(x)
            self.intercept = self.intercept - (learning_rate / len(x)) * sum(h - y)
            for j in range(0, len(x[0])):
                self.coef[j] = self.coef[j] - (learning_rate / len(x)) * sum((h-y) * xx.transpose()[j])
                cost_list.append((1 / len(x)) * 0.5 * sum(np.square(h - y)))
                #cost_list.append(mean_squared_error(y, h))
                epoch_list.append(epoch)
        plt.xlabel("epochddd")
        plt.ylabel("errorddd")
        plt.plot(epoch_list, cost_list)
        plt.show()

    def fit_stochastic(self, x, y, learning_rate=0.01, no_epochs=1000):
        self.coef = [0.0 for _ in range(1 + len(x[0]))]
        # self.coef = [random.random() for _ in range(len(x[0]) + 1)]    #beta or w coefficients

        epoch_list = []
        error_list = []
        for epoch in range(no_epochs):
            err = 0
            # TBA: shuffle the trainind examples in order to prevent cycles
            for i in range(len(x)):  # for each sample from the training data
                y_computed = sigmoid(self.eval(x[i], self.coef))  # estimate the output
                crt_error = y_computed - y[i]  # compute the error for the current sample
                err += crt_error
                for j in range(0, len(x[0])):  # update the coefficients
                    self.coef[j + 1] = self.coef[j + 1] - learning_rate * crt_error * x[i][j]
                self.coef[0] = self.coef[0] - learning_rate * crt_error * 1

            epoch_list.append(epoch)
            error_list.append(err / len(x))

        self.intercept = self.coef[0]
        self.coef = self.coef[1:]

        plt.plot(epoch_list, error_list)
        plt.show()

    def hypothesis(self, x):
        """
        :param x:
        :return: [[w0 + x11 * w1 + x12 * w2], [w0 + x21 * w1 + x22 * w2]....]
        """
        h = np.ones((len(x), 1))
        for i in range(0, len(x)):
            h[i] = float(np.matmul(self.coef, x[i]))
        h = h.reshape(len(x))
        return h

    def eval(self, xi, coeff):
        yi = coeff[0]
        for j in range(len(xi)):
            yi += coeff[j + 1] * xi[j]
        return yi

    def predict_one_sample(self, sample_features):
        threshold = 0.5
        coefficients = [self.intercept] + [coef for coef in self.coef]
        computed_float_value = self.eval(sample_features, coefficients)
        computed_value = sigmoid(computed_float_value)

        computed_label = 0 if computed_value < threshold else 1
        return computed_label

    def predict(self, inputs):
        computed_labels = [self.predict_one_sample(sample) for sample in inputs]
        return computed_labels