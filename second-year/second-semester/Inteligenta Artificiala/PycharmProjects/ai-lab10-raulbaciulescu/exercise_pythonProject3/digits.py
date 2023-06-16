import numpy as np

from NeuralNetwork import NeuralNetwork
from utils import load_digit_data, normalisation, split_data, eval_multi_class, plot_confusion_matrix


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

train_inputs = np.array(train_inputs).T
test_inputs = np.array(test_inputs).T
n, m = train_inputs.shape
train_outputs = np.array(train_outputs)
test_outputs = np.array(test_outputs)

network = NeuralNetwork()


network.train(mse, mse_prime, train_inputs, train_outputs, epochs=1000, learning_rate=0.01)
computed_outputs = nn.predict(test_inputs, W1, b1, W2, b2)
test_outputs = np.array(test_outputs)
print("prediction error digits (manual): ", nn.compute_error(computed_outputs, test_outputs))

acc, prec, recall, cm = eval_multi_class(np.array(test_outputs), computed_outputs,
                                         [i for i in range(0, 10)])
plot_confusion_matrix(cm, [i for i in range(0, 10)], "digits classification")


