import numpy as np

from dense import Dense
from main import mse, mse_prime
from network import Network
from utils import load_digit_data, normalisation, split_data, eval_multi_class, Sigmoid, Softmax, plot_confusion_matrix


def flatten(mat):
    x = []
    for line in mat:
        for el in line:
            x.append(el)
    return x


inputs, outputs, output_names = load_digit_data()
train_inputs, train_outputs, test_inputs, test_outputs = split_data(inputs, outputs)
train_inputs_flatten = [flatten(el) for el in train_inputs]
test_inputs_flatten = [flatten(el) for el in test_inputs]
train_inputs, test_inputs = normalisation(train_inputs_flatten, test_inputs_flatten)

train_inputs = np.array(train_inputs)
test_inputs = np.array(test_inputs)
n, m = train_inputs.shape
train_outputs = np.array(train_outputs)
test_outputs = np.array(test_outputs)

network = Network()
network.add_layer(Dense(64, 10))
network.add_layer(Sigmoid())
network.add_layer(Dense(10, 10))
network.add_layer(Softmax())

train_inputs = np.reshape(train_inputs, (1437, 64, 1))
test_inputs = np.reshape(test_inputs, (360, 64, 1))

train_outputs = np.reshape(train_outputs, (1437, 1, 1))


network.train(mse, mse_prime, train_inputs, train_outputs, epochs=300, learning_rate=0.01)
test_outputs = np.array(test_outputs)
computed_outputs = []
for x in test_inputs:
    y = network.predict(x).reshape(10)
    maxim = 0
    max_index = 0
    for i in range(len(y)):
        if y[i] > maxim:
            maxim = y[i]
            max_index = i
    computed_outputs.append(max_index)


acc, prec, recall, cm = eval_multi_class(np.array(test_outputs), np.array(computed_outputs),
                                         [i for i in range(0, 10)])
plot_confusion_matrix(cm, [i for i in range(0, 10)], "digits classification")
