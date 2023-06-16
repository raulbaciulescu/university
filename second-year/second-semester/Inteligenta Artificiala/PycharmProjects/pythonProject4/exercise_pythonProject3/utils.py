import itertools
import pandas as pd
import numpy as np
from sklearn.datasets import load_iris
from sklearn.datasets import load_digits
from sklearn.metrics import confusion_matrix
from sklearn.preprocessing import StandardScaler
import matplotlib.pyplot as plt


def load_iris_data():
    data = load_iris()
    inputs = data['data']
    outputs = data['target']
    output_names = data['target_names']
    feature_names = list(data['feature_names'])
    feature1 = [feat[feature_names.index('sepal length (cm)')] for feat in inputs]
    feature2 = [feat[feature_names.index('petal length (cm)')] for feat in inputs]
    inputs = [[feat1, feat2] for feat1, feat2 in zip(feature1, feature2)]
    return inputs, outputs, output_names


def load_digit_data():
    data = load_digits()
    inputs = data.images
    outputs = data['target']
    output_names = data['target_names']
    # shuffle the original data
    no_data = len(inputs)
    permutation = np.random.permutation(no_data)
    inputs = inputs[permutation]
    outputs = outputs[permutation]

    return inputs, outputs, output_names


def split_data(inputs, outputs):
    np.random.seed(5)
    indexes = [i for i in range(len(inputs))]
    train_sample = np.random.choice(indexes, int(0.8 * len(inputs)), replace=False)
    test_sample = [i for i in indexes if not i in train_sample]

    train_inputs = [inputs[i] for i in train_sample]
    train_outputs = [outputs[i] for i in train_sample]
    test_inputs = [inputs[i] for i in test_sample]
    test_outputs = [outputs[i] for i in test_sample]

    return train_inputs, train_outputs, test_inputs, test_outputs


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


def plot_confusion_matrix(cm, class_names, title):
    plt.figure()
    plt.imshow(cm, interpolation='nearest', cmap='Blues')
    plt.title('Confusion Matrix ' + title)
    plt.colorbar()
    tick_marks = np.arange(len(class_names))
    plt.xticks(tick_marks, class_names, rotation=45)
    plt.yticks(tick_marks, class_names)
    text_format = 'd'
    thresh = cm.max() / 2.
    for row, column in itertools.product(range(cm.shape[0]), range(cm.shape[1])):
        plt.text(column, row, format(cm[row, column], text_format),
                 horizontalalignment='center', color='white' if cm[row, column] > thresh else 'black')
    plt.ylabel('True label')
    plt.xlabel('Predicted label')
    plt.tight_layout()

    plt.show()


def eval_multi_class(real_labels, computed_labels, label_names):
    conf_matrix = confusion_matrix(real_labels, computed_labels)
    acc = sum([conf_matrix[i][i] for i in range(len(label_names))]) / len(real_labels)
    precision = {}
    recall = {}
    for i in range(len(label_names)):
        precision[label_names[i]] = conf_matrix[i][i] / sum([conf_matrix[j][i] for j in range(len(label_names))])
        recall[label_names[i]] = conf_matrix[i][i] / sum([conf_matrix[i][j] for j in range(len(label_names))])

    return acc, precision, recall, conf_matrix


def read_data_all_feat():
    data = pd.read_csv('data/iris.csv')
    feature1 = list(data['sepal_length'])
    feature2 = list(data['sepal_width'])
    feature3 = list(data['petal_length'])
    feature4 = list(data['petal_width'])
    inputs = [[feature1[i], feature2[i], feature3[i], feature4[i]] for i in range(len(feature1))]
    outputs = list(data['class'])

    return inputs, outputs


def read_data_2_feat():
    data = pd.read_csv('data/iris.csv')
    feature1 = list(data['sepal_length'])
    feature2 = list(data['petal_width'])
    inputs = [[feature1[i], feature2[i]] for i in range(len(feature1))]
    outputs = list(data['class'])

    return inputs, outputs