import numpy as np

from dense import Dense
from main import mse, mse_prime
from network import Network
from utils import read_data_all_feat, Tanh, eval_multi_class, plot_confusion_matrix, Softmax, Sigmoid


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

train_inputs = np.array(train_inputs)
test_inputs = np.array(test_inputs)
train_outputs = np.array(train_outputs)
test_outputs = np.array(test_outputs)

train_inputs = np.reshape(train_inputs, (120, 4, 1))
test_inputs = np.reshape(test_inputs, (30, 4, 1))

train_outputs = np.reshape(train_outputs, (120, 1, 1))
# test_outputs = np.reshape(test_outputs, (30, 1, 1))

network = Network()
network.add_layer(Dense(4, 3))
network.add_layer(Sigmoid())
network.add_layer(Dense(3, 3))
network.add_layer(Softmax())


def compute_error(computed_outputs, test_outputs):
    error = 0.0
    for t1, t2 in zip(computed_outputs, test_outputs):
        error += (t1 - t2) ** 2
    error = error / len(test_outputs)
    return error


network.train(mse, mse_prime, train_inputs, train_outputs, epochs=1000, learning_rate=0.01)

computed_outputs = []
for x in test_inputs:
    y = network.predict(x).reshape(3)
    if y[0] > y[1] and y[0] > y[2]:
        computed_outputs.append(0)

    if y[1] > y[0] and y[1] > y[2]:
        computed_outputs.append(1)

    if y[2] > y[1] and y[2] > y[0]:
        computed_outputs.append(2)

acc, prec, recall, cm = eval_multi_class(test_outputs, computed_outputs,
                                         ['Iris-setosa', 'Iris-versicolor', 'Iris-virginica'])
plot_confusion_matrix(cm, ['Iris-setosa', 'Iris-versicolor', 'Iris-virginica'], "iris classification")

