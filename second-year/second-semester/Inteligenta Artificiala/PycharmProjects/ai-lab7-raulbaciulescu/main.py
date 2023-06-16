import csv
import os
from turtle import pd

import numpy as np
from matplotlib import pyplot as plt
from pyparsing import results
from sklearn import linear_model
from sklearn.linear_model import LinearRegression
from sklearn.metrics import mean_squared_error

from regressor import Regressor
from all_steps import plotData
from example import plotDataHistogram


def load_data(file_name, input_variable_name, output_variable_name):
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
    calc_inputs = []
    calc_outputs = []
    Hindex = data_names.index("Happiness.Score")
    Windex = data_names.index("Whisker.high")
    Eindex = data_names.index("Economy..GDP.per.Capita.")
    for i in range(30):
        if data[i][Hindex] != '' and data[i][Eindex] != '' and data[i][Windex] != '':
            calc_inputs.append([float(data[i][Hindex]), float(data[i][Windex])])
            calc_outputs.append(float(data[i][Eindex]))
    regressor = linear_model.LinearRegression()
    regressor.fit(calc_inputs, calc_outputs)
    w0, w1, w2 = regressor.intercept_, regressor.coef_[0], regressor.coef_[1]

    inputs = []
    for i in range(len(data)):
        row_values = []
        for feature in input_variable_name:
            feature_index = data_names.index(feature)
            if data[i][feature_index] == '':
                rez = w0 + float(data[i][Hindex]) * w1 + float(data[i][Windex]) * w2
                row_values.append(rez)
            else:
                row_values.append(float(data[i][feature_index]))
        inputs.append(row_values)

    selected_output = data_names.index(output_variable_name)
    outputs = [float(data[i][selected_output]) for i in range(len(data))]

    return inputs, outputs


# def load_data2(file_name, input_variable_name1, input_variable_name2, output_variable_name):
#     data = []
#     data_names = []
#     with open(file_name) as csv_file:
#         csv_reader = csv.reader(csv_file, delimiter=',')
#         line_count = 0
#         for row in csv_reader:
#             if line_count == 0:
#                 data_names = row
#             else:
#                 data.append(row)
#             line_count += 1
#     selected_variable1 = data_names.index(input_variable_name1)
#     selected_variable2 = data_names.index(input_variable_name2)
#     selected_output = data_names.index(output_variable_name)
#     print(selected_variable1)
#     inputs = [[float(data[i][selected_variable1]), float(data[i][selected_variable2])] for i in range(len(data))]
#     outputs = [float(data[i][selected_output]) for i in range(len(data))]
#
#     return inputs, outputs


def plot_train(train_inputs, train_outputs):
    fig = plt.figure(figsize=(8, 8))
    ax = fig.add_subplot(111, projection='3d')
    ax.scatter([x[0] for x in train_inputs], [x[1] for x in train_inputs], train_outputs, color='red')
    ax.set_xlabel("GDP capital")
    ax.set_ylabel("Freedom")
    ax.set_zlabel("Happiness")
    ax.set_title("GDP capital and freedom vs. happiness")
    plt.show()


def plot_validation(validation_inputs, validation_outputs, computed_validation_outputs2):
    fig = plt.figure(figsize=(8, 8))
    ax = fig.add_subplot(111, projection='3d')
    ax.scatter([x[0] for x in validation_inputs], [x[1] for x in validation_inputs], validation_outputs, color='red',
               label="real")
    ax.scatter([x[0] for x in validation_inputs], [x[1] for x in validation_inputs], computed_validation_outputs2,
               color='green', label="computed", marker="^")
    ax.set_xlabel("GDP capital")
    ax.set_ylabel("Freedom")
    ax.set_zlabel("Happiness")
    ax.set_title("computed, real data")
    ax.legend()
    plt.show()


def main():
    crt_dir = os.getcwd()
    file_path = os.path.join(crt_dir, 'data', 'v3_world-happiness-report-2017.csv')
    inputs, outputs = load_data(file_path, ['Economy..GDP.per.Capita.', 'Freedom'], 'Happiness.Score')
    # inputs2, output2 = load_data2(file_path, 'Whisker.high', 'Whisker.low', 'Economy..GDP.per.Capita.')
    np.random.seed(5)
    indexes = [i for i in range(len(inputs))]
    train_sample = np.random.choice(indexes, int(0.8 * len(inputs)), replace=False)
    validation_sample = [i for i in indexes if not i in train_sample]

    train_inputs = [inputs[i] for i in train_sample]
    train_input_with1 = [([1] + inputs[i]) for i in train_sample]

    train_outputs = [outputs[i] for i in train_sample]
    train_outputs_with_list = [[outputs[i]] for i in train_sample]

    validation_inputs = [inputs[i] for i in validation_sample]
    validation_outputs = [outputs[i] for i in validation_sample]

    regressor = LinearRegression()
    regressor.fit(train_inputs, train_outputs)
    regressor_manual = Regressor()
    regressor_manual.fit(train_input_with1, train_outputs_with_list)
    w0, w1, w2 = regressor_manual.intercept, regressor_manual.coef[0], regressor_manual.coef[1]

    # #new
    # regressor.fit(inputs, outputs)
    # w0, w1, w2 = regressor.intercept_, regressor.coef_[0], regressor.coef_[1]
    # regressor.fit(train_inputs, train_outputs)
    #
    # plot_train(train_inputs, train_outputs)

    computed_validation_outputs = regressor.predict([[x[0], x[1]] for x in validation_inputs])
    computed_validation_outputs2 = regressor_manual.predict(validation_inputs)

    plot_validation(validation_inputs, validation_outputs, computed_validation_outputs2)

    error = mean_squared_error(validation_outputs, computed_validation_outputs)
    error2 = regressor_manual.error_mse(validation_outputs, computed_validation_outputs2)
    print("prediction error (tool): ", error)
    print("prediction error (manual): ", error2)


if __name__ == '__main__':
    main()
