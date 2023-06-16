import random

import matplotlib.pyplot as plt
import numpy as np
from sklearn.metrics import mean_squared_error


class MySGDRegression:
    def __init__(self):
        self.intercept_ = 0.0
        self.coef_ = []

    # simple stochastic GD
    def fit2(self, x, y, learning_rate=0.001, no_epochs=1000):
        self.coef_ = [0.0 for _ in range(len(x[0]) + 1)]  # beta or w coefficients y = w0 + w1 * x1 + w2 * x2 + ...
        # self.coef_ = [random.random() for _ in range(len(x[0]) + 1)]    #beta or w coefficients
        for epoch in range(no_epochs):
            # TBA: shuffle the trainind examples in order to prevent cycles
            for i in range(len(x)):  # for each sample from the training data
                y_computed = self.eval(x[i])  # estimate the output
                crt_error = y_computed - y[i]  # compute the error for the current sample
                for j in range(0, len(x[0])):  # update the coefficients
                    self.coef_[j] = self.coef_[j] - learning_rate * crt_error * x[i][j]
                self.coef_[len(x[0])] = self.coef_[len(x[0])] - learning_rate * crt_error * 1

        self.intercept_ = self.coef_[-1]
        self.coef_ = self.coef_[:-1]

    def fit4(self, x, y, learning_rate=0.001, no_epochs=5000):
        # x = [[feat1, feat2, feat3], [feat1, feat2, feat3],...]
        # y = [out1, out2,...]
        self.coef_ = [random.random() for _ in range(len(x[0]))]  # beta or w coefficients
        y = np.array(y)
        xx = np.array(x)
        epoch_list = []
        cost_list = []
        for epoch in range(no_epochs):
            h = self.hypothesis(x)
            self.intercept_ = self.intercept_ - (learning_rate / len(x)) * sum(h - y)
            for j in range(0, len(x[0])):
                self.coef_[j] = self.coef_[j] - (learning_rate / len(x)) * sum((h-y) * xx.transpose()[j])
                cost_list.append((1 / len(x)) * 0.5 * sum(np.square(h - y)))
                #cost_list.append(mean_squared_error(y, h))
                epoch_list.append(epoch)
        plt.xlabel("epoch")
        plt.ylabel("error")
        plt.plot(epoch_list, cost_list)
        plt.show()

    def hypothesis(self, x):
        """
        :param x:
        :return: [[w0 + x11 * w1 + x12 * w2], [w0 + x21 * w1 + x22 * w2]....]
        """
        h = np.ones((len(x), 1))
        for i in range(0, len(x)):
            h[i] = float(np.matmul(self.coef_, x[i]))
        h = h.reshape(len(x))
        return h

    def eval(self, xi):
        yi = self.intercept_
        for j in range(len(xi)):
            yi += self.coef_[j] * xi[j]
        return yi

    def predict(self, x):
        y_computed = [self.eval(xi) for xi in x]
        return y_computed
