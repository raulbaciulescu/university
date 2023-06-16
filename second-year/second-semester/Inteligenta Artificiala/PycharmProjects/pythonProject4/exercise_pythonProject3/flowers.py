import numpy as np
import pandas as pd

from NeuralNetwork import NeuralNetwork
from utils import normalisation, read_data_all_feat, eval_multi_class, plot_confusion_matrix


def transform_to_digits(outputs):
    if isinstance(outputs[0], int):
        return outputs
    outputs_new = []
    for i in range(len(outputs)):
        if outputs[i] == 'Iris-setosa':
            outputs_new.append(0)
        if outputs[i] == 'Iris-versicolor':
            outputs_new.append(1)
        if outputs[i] == 'Iris-virginica':
            outputs_new.append(2)

    return outputs_new


def transform_from_digits(outputs):
    if isinstance(outputs[0], str):
        return outputs
    outputs_new = []
    for i in range(len(outputs)):
        if outputs[i] == 0:
            outputs_new.append('Iris-setosa')
        if outputs[i] == 1:
            outputs_new.append('Iris-versicolor')
        if outputs[i] == 2:
            outputs_new.append('Iris-virginica')

    return outputs_new


inputs, outputs = read_data_all_feat()
np.random.seed(5)
outputs = transform_to_digits(outputs)
indexes = [i for i in range(len(inputs))]
train_sample = np.random.choice(indexes, int(0.8 * len(inputs)), replace=False)
test_sample = [i for i in indexes if not i in train_sample]

train_inputs = [inputs[i] for i in train_sample]
train_outputs = [outputs[i] for i in train_sample]
test_inputs = [inputs[i] for i in test_sample]
test_outputs = [outputs[i] for i in test_sample]

train_inputs = np.array(train_inputs).T
test_inputs = np.array(test_inputs).T
train_outputs = np.array(train_outputs)
n, m = train_inputs.shape

nn = NeuralNetwork()
W1, b1, W2, b2 = nn.gradient_descent(train_inputs, train_outputs, 0.1, 1000, n, m, 3)
computed_outputs = nn.predict(test_inputs, W1, b1, W2, b2)
print("prediction error flowers (manual): ", nn.compute_error(computed_outputs, test_outputs))
print(np.array(test_outputs))
print(computed_outputs)
acc, prec, recall, cm = eval_multi_class(np.array(test_outputs), computed_outputs,
                                         ['Iris-setosa', 'Iris-versicolor', 'Iris-virginica'])
plot_confusion_matrix(cm, ['Iris-setosa', 'Iris-versicolor', 'Iris-virginica'], "iris classification")
