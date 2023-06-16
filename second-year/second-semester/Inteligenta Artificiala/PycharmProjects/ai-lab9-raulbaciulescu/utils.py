import matplotlib.pyplot as plt
import numpy as np
import pandas as pd
from sklearn import linear_model
from sklearn.preprocessing import StandardScaler


def plot_data_histogram(x, variable_name):
    n, bins, patches = plt.hist(x, 10)
    plt.title('Histogram of ' + variable_name)
    plt.show()


def normalisation(train_data, test_data):
    scaler = StandardScaler()
    if not isinstance(train_data[0], list):
        # encode each sample into a list
        train_data = [[d] for d in train_data]
        test_data = [[d] for d in test_data]

        scaler.fit(train_data)  # fit only on training data
        normalised_train_data = scaler.transform(train_data)
        normalised_test_data = scaler.transform(test_data)

        # decode from list into raw values
        normalised_train_data = [el[0] for el in normalised_train_data]
        normalised_test_data = [el[0] for el in normalised_test_data]
    else:
        scaler.fit(train_data)  # fit only on training data
        normalised_train_data = scaler.transform(train_data)
        normalised_test_data = scaler.transform(test_data)
    return normalised_train_data, normalised_test_data


def plot_classification_data(feature1, feature2, outputs, title=None):
    labels = set(outputs)
    no_data = len(feature1)
    output_names = ['malignant', 'benign']
    for crt_label in labels:
        x = [feature1[i] for i in range(no_data) if outputs[i] == crt_label]
        y = [feature2[i] for i in range(no_data) if outputs[i] == crt_label]
        plt.scatter(x, y, label=output_names[crt_label])
    plt.xlabel('mean radius')
    plt.ylabel('mean texture')
    plt.legend()
    plt.title(title)
    plt.show()


def plot_predictions(feature1, feature2, real_outputs, computed_outputs, title, label_names):
    labels = [0, 1]
    no_data = len(feature1)

    for crt_label in labels:
        x = [feature1[i] for i in range(no_data) if real_outputs[i] == crt_label and computed_outputs[i] == crt_label]
        y = [feature2[i] for i in range(no_data) if real_outputs[i] == crt_label and computed_outputs[i] == crt_label]
        plt.scatter(x, y, label=label_names[crt_label] + ' (correct)')
    for crt_label in labels:
        x = [feature1[i] for i in range(no_data) if real_outputs[i] == crt_label and computed_outputs[i] != crt_label]
        y = [feature2[i] for i in range(no_data) if real_outputs[i] == crt_label and computed_outputs[i] != crt_label]
        plt.scatter(x, y, label=label_names[crt_label] + ' (incorrect)')
    plt.xlabel('mean radius')
    plt.ylabel('mean texture')
    plt.legend()
    plt.title(title)
    plt.show()


def read_data():
    data = pd.read_csv('data/iris.csv')
    feature1 = list(data['sepal_length'])
    feature2 = list(data['sepal_width'])
    feature3 = list(data['petal_length'])
    feature4 = list(data['petal_width'])
    inputs = [[feature1[i], feature2[i], feature3[i], feature4[i]] for i in range(len(feature1))]
    outputs = list(data['class'])

    return inputs, outputs


def read_data2():
    data = pd.read_csv('data/iris.csv')
    feature1 = list(data['sepal_length'])
    feature2 = list(data['petal_width'])
    inputs = [[feature1[i], feature2[i]] for i in range(len(feature1))]
    outputs = list(data['class'])

    return inputs, outputs


def plot_predictions2(test_inputs, test_outputs, computed_outputs1, computed_outputs2, computed_outputs3):

    print(len(test_inputs))
    print(len(test_outputs))
    print(computed_outputs1)
    print(computed_outputs2)
    print(computed_outputs3)
    for i in range(len(test_outputs)):
        if test_outputs[i] == 'Iris-setosa':
            if computed_outputs1[i] > computed_outputs2[i] and computed_outputs1 > computed_outputs3:
                plt.plot([test_inputs[i][0]], [test_inputs[i][1]], "go")
            else:
                plt.plot([test_inputs[i][0]], [test_inputs[i][1]], "ro")

            if test_outputs[i] == 'Iris-versicolor':
                if computed_outputs1[i] > computed_outputs2[i] and computed_outputs1 > computed_outputs3:
                    plt.plot([test_inputs[i][0]], [test_inputs[i][1]], "go")
                else:
                    plt.plot([test_inputs[i][0]], [test_inputs[i][1]], "ro")

            if test_outputs[i] == 'Iris-virginica':
                if computed_outputs1[i] > computed_outputs2[i] and computed_outputs1 > computed_outputs3:
                    plt.plot([test_inputs[i][0]], [test_inputs[i][1]], "go")
                else:
                    plt.plot([test_inputs[i][0]], [test_inputs[i][1]], "ro")
    # plt.legend()
    plt.show()


def plot_train(train_inputs, train_outputs):
    plt.xlabel("sepal length")
    plt.ylabel("petal length")
    plt.title("normalised train data")
    for i in range(len(train_outputs)):
        if train_outputs[i] == "Iris-setosa":
            plt.plot([train_inputs[i][0]], [train_inputs[i][1]], "ro")
        if train_outputs[i] == "Iris-versicolor":
            plt.plot([train_inputs[i][0]], [train_inputs[i][1]], "go")
        if train_outputs[i] == "Iris-virginica":
            plt.plot([train_inputs[i][0]], [train_inputs[i][1]], "bo")
    plt.show()
