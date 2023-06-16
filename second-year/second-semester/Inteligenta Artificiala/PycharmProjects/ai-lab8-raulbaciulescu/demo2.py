import csv
import os

import numpy as np
from matplotlib import pyplot as plt
from sklearn.preprocessing import StandardScaler
from math import sqrt

from demo import plotDataHistogram
from sgd import MySGDRegression


def plot3d(x_train=None, y_train=None, z_train=None, x_model=None, y_model=None, z_model=None, x_test=None, y_test=None, z_test=None, title=None):
    fig = plt.figure(figsize=(8, 8))
    ax = fig.add_subplot(111, projection='3d')
    if x_train:
        ax.scatter(x_train, y_train, z_train, label='training data')
    if x_model:
        ax.scatter(x_model, y_model, z_model, label='learnt model', marker='^')
    if x_test:
        ax.scatter(x_test, y_test, z_test, label='test data', marker='^')
    ax.set_xlabel("GDP per capita")
    ax.set_ylabel("Freedom")
    ax.set_zlabel("Happiness")
    if title is not None:
        ax.set_title(title)
    ax.legend()
    plt.show()


def loadDataMoreInputs(file_name, input_variab_names, output_variab_name):
    data = []
    data_names = []
    with open(file_name) as csv_file:
        csv_reader = csv.reader(csv_file, delimiter=',')
        line_count = 0
        for row in csv_reader:
            if line_count == 0:
                data_names = row
            else:
                data.append(row)
            line_count += 1
    selected_variable1 = data_names.index(input_variab_names[0])
    selected_variable2 = data_names.index(input_variab_names[1])
    inputs = [[float(data[i][selected_variable1]), float(data[i][selected_variable2])] for i in range(len(data))]
    selected_output = data_names.index(output_variab_name)
    outputs = [float(data[i][selected_output]) for i in range(len(data))]

    return inputs, outputs

# problem hapiness = w0 + w1 * GDPcapita + w2 * freedom
# load data
crt_dir =  os.getcwd()
filePath = os.path.join(crt_dir, 'data', 'world-happiness-report-2017.csv')

inputs, outputs = loadDataMoreInputs(filePath, ['Economy..GDP.per.Capita.', 'Freedom'], 'Happiness.Score')

feature1 = [ex[0] for ex in inputs]
feature2 = [ex[1] for ex in inputs]

# plot the data histograms
plotDataHistogram(feature1, 'capita GDP')
plotDataHistogram(feature2, 'freedom')
plotDataHistogram(outputs, 'Happiness score')

# check the liniarity (to check that a linear relationship exists between the dependent variable (y = happiness) and the independent variables (x1 = capita, x2 = freedom).)
plot3d(feature1, feature2, outputs, [], [], [], [], [], [], 'capita vs freedom vs happiness')


def normalisation(trainData, testData):
    scaler = StandardScaler()
    if not isinstance(trainData[0], list):
        # encode each sample into a list
        trainData = [[d] for d in trainData]
        testData = [[d] for d in testData]

        scaler.fit(trainData)  # fit only on training data
        normalisedTrainData = scaler.transform(trainData)  # apply same transformation to train data
        normalisedTestData = scaler.transform(testData)  # apply same transformation to test data

        # decode from list to raw values
        normalisedTrainData = [el[0] for el in normalisedTrainData]
        normalisedTestData = [el[0] for el in normalisedTestData]
    else:
        scaler.fit(trainData)  # fit only on training data
        normalisedTrainData = scaler.transform(trainData)  # apply same transformation to train data
        normalisedTestData = scaler.transform(testData)  # apply same transformation to test data
    return normalisedTrainData, normalisedTestData

np.random.seed(5)
indexes = [i for i in range(len(inputs))]
trainSample = np.random.choice(indexes, int(0.8 * len(inputs)), replace = False)
testSample = [i for i in indexes if not i in trainSample]

trainInputs = [inputs[i] for i in trainSample]
trainOutputs = [outputs[i] for i in trainSample]
testInputs = [inputs[i] for i in testSample]
testOutputs = [outputs[i] for i in testSample]


trainInputs, testInputs = normalisation(trainInputs, testInputs)
trainOutputs, testOutputs = normalisation(trainOutputs, testOutputs)

feature1train = [ex[0] for ex in trainInputs]
feature2train = [ex[1] for ex in trainInputs]

feature1test = [ex[0] for ex in testInputs]
feature2test = [ex[1] for ex in testInputs]

plot3d(feature1train, feature2train, trainOutputs, [], [], [], feature1test, feature2test, testOutputs, "train and test data (after normalisation)")

# identify (by training) the regressor

# # use sklearn regressor
# from sklearn import linear_model
# regressor = linear_model.SGDRegressor()

# using developed code

# model initialisation
regressor = MySGDRegression()

regressor.fit4(trainInputs, trainOutputs)
# print(regressor.coef_)
# print(regressor.intercept_)

#parameters of the liniar regressor
w0, w1, w2 = regressor.intercept_, regressor.coef_[0], regressor.coef_[1]
print('the learnt model: f(x) = ', w0, ' + ', w1, ' * x1 + ', w2, ' * x2' )

#numerical representation of the regressor model
noOfPoints = 50
xref1 = []
val = min(feature1)
step1 = (max(feature1) - min(feature1)) / noOfPoints
for _ in range(1, noOfPoints):
    for _ in range(1, noOfPoints):
        xref1.append(val)
    val += step1

xref2 = []
val = min(feature2)
step2 = (max(feature2) - min(feature2)) / noOfPoints
for _ in range(1, noOfPoints):
    aux = val
    for _ in range(1, noOfPoints):
        xref2.append(aux)
        aux += step2
yref = [w0 + w1 * el1 + w2 * el2 for el1, el2 in zip(xref1, xref2)]
plot3d(feature1train, feature2train, trainOutputs, xref1, xref2, yref, [], [], [], 'train data and the learnt model')