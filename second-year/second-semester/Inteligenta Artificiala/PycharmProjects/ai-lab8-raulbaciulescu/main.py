import os

import numpy as np
from sklearn import linear_model
from sklearn.metrics import mean_squared_error

from demo import loadData, plotDataHistogram, plotData, univariateRegression
from sgd import MySGDRegression


def univariate_regression():
    crt_dir = os.getcwd()
    file_path = os.path.join(crt_dir, 'data', 'world-happiness-report-2017.csv')

    inputs, outputs = loadData(file_path, 'Economy..GDP.per.Capita.', 'Happiness.Score')
    # split data into training data (80%) and testing data (20%)

    np.random.seed(5)
    indexes = [i for i in range(len(inputs))]
    train_sample = np.random.choice(indexes, int(0.8 * len(inputs)), replace=False)
    test_sample = [i for i in indexes if not i in train_sample]
    train_inputs = [inputs[i] for i in train_sample]
    train_outputs = [outputs[i] for i in train_sample]
    test_inputs = [inputs[i] for i in test_sample]
    test_outputs = [outputs[i] for i in test_sample]

    # training step
    xx = [[el] for el in train_inputs]
    # regressor = linear_model.SGDRegressor(max_iter =  10000)
    # regressor = linear_model.SGDClassifier
    regressor = MySGDRegression()
    regressor.fit4(xx, train_outputs)
    w0, w1 = regressor.intercept_, regressor.coef_[0]
    print(w0,  'ddddd')
    print(w1)

    noOfPoints = 1000
    xref = []
    val = min(train_inputs)
    step = (max(train_inputs) - min(train_inputs)) / noOfPoints
    for i in range(1, noOfPoints):
        xref.append(val)
        val += step
    yref = [w0 + w1 * el for el in xref]
    plotData(train_inputs, train_outputs, xref, yref, [], [], title="train data and model")

    # makes predictions for test data
    # computedTestOutputs = [w0 + w1 * el for el in testInputs]
    # makes predictions for test data (by tool)
    computedTestOutputs = regressor.predict([[x] for x in test_inputs])
    plotData([], [], test_inputs, computedTestOutputs, test_inputs, test_outputs, "predictions vs real test data")

    # compute the differences between the predictions and real outputs
    error = 0.0
    for t1, t2 in zip(computedTestOutputs, test_outputs):
        error += (t1 - t2) ** 2
    error = error / len(test_outputs)
    print("prediction error (manual): ", error)

    error = mean_squared_error(test_outputs, computedTestOutputs)
    print("prediction error (tool): ", error)


def main():
    univariate_regression()
    #univariateRegression()

if __name__ == '__main__':
    main()

