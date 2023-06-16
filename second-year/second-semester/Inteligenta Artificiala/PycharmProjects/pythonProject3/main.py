import numpy as np

from utils import read_data_2_feat, normalisation


def main():
    inputs, outputs = read_data_2_feat()
    np.random.seed(5)
    indexes = [i for i in range(len(inputs))]
    train_sample = np.random.choice(indexes, int(0.8 * len(inputs)), replace=False)
    test_sample = [i for i in indexes if not i in train_sample]

    train_inputs = [inputs[i] for i in train_sample]
    train_outputs = [outputs[i] for i in train_sample]
    train_outputs1 = [1 if el == "Iris-setosa" else 0 for el in train_outputs]
    train_outputs2 = [1 if el == "Iris-versicolor" else 0 for el in train_outputs]
    train_outputs3 = [1 if el == "Iris-virginica" else 0 for el in train_outputs]
    test_inputs = [inputs[i] for i in test_sample]
    test_outputs = [outputs[i] for i in test_sample]
    test_outputs1 = [1 if el == "Iris-setosa" else 0 for el in test_outputs]
    test_outputs2 = [1 if el == "Iris-versicolor" else 0 for el in test_outputs]
    test_outputs3 = [1 if el == "Iris-virginica" else 0 for el in test_outputs]
    try:
        train_inputs, test_inputs = normalisation(train_inputs, test_inputs)
    except Exception:
        print('only in debug mode')


if __name__ == '__main__':
    main()
