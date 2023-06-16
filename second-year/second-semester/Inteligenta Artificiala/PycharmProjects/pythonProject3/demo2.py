import numpy as np
from sklearn import neural_network

from utils import load_digit_data, split_data, normalisation, eval_multi_class, plot_confusion_matrix
from matplotlib import pyplot as plt
from PIL import Image



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

# try to play by MLP parameters (e.g. change the HL size from 10 to 20 and see how this modification impacts the accuracy)
classifier = neural_network.MLPClassifier(hidden_layer_sizes=(5, ), activation='relu',
                                          max_iter=100, solver='sgd', verbose=10, random_state=1, learning_rate_init=.1)
classifier.fit(train_inputs, train_outputs)
computed_outputs = classifier.predict(test_inputs)

acc, prec, recall, cm = eval_multi_class(np.array(test_outputs), computed_outputs, output_names)

plot_confusion_matrix(cm, output_names, "iris classification")

print('acc: ', acc)
print('precision: ', prec)
print('recall: ', recall)

# plot the first 50 test images and thier real and computed labels
n = 10
m = 5
fig, axes = plt.subplots(n, m, figsize=(7, 7))
fig.tight_layout()
for i in range(0, n):
    for j in range(0, m):
        axes[i][j].imshow(test_inputs[m * i + j])
        if test_outputs[m * i + j] == computed_outputs[m * i + j]:
            font = 'normal'
        else:
            font = 'bold'
        axes[i][j].set_title('real ' + str(test_outputs[m * i + j]) + '\npredicted ' + str(computed_outputs[m * i + j]),
                             fontweight=font)
        axes[i][j].set_axis_off()

plt.show()