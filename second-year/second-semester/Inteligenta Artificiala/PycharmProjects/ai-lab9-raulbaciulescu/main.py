import numpy as np
import pandas as pd
from sklearn import linear_model
from sklearn.metrics import accuracy_score

from logistic_regression import MyLogisticRegression
from utils import read_data, normalisation, plot_classification_data, plot_train, plot_predictions2


def main():
    inputs, outputs = read_data()

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


    # normalise
    try:
        train_inputs, test_inputs = normalisation(train_inputs, test_inputs)
    except Exception:
        print('only in debug mode')
    # classifier = linear_model.LogisticRegression()
    # classifier.fit(train_inputs, train_outputs)
    # intercept, coef = classifier.intercept_, classifier.coef_
    # computed_test_outputs = classifier.predict(test_inputs)
    # print(intercept)
    # print(coef)


    # computed_test_outputs = classifier.predict(test_inputs)
    # from sklearn.metrics import accuracy_score
    # error = 1 - accuracy_score(test_outputs, computed_test_outputs)
    # print("classification error (tool): ", error)

    classifier1 = MyLogisticRegression()
    classifier2 = MyLogisticRegression()
    classifier3 = MyLogisticRegression()
    train_outputs1 = [1 if flower == 'Iris-setosa' else 0 for flower in train_outputs]
    train_outputs2 = [1 if flower == 'Iris-versicolor' else 0 for flower in train_outputs]
    train_outputs3 = [1 if flower == 'Iris-virginica' else 0 for flower in train_outputs]
    classifier1.fit_batch(train_inputs, train_outputs1)
    classifier2.fit_batch(train_inputs, train_outputs2)
    classifier3.fit_batch(train_inputs, train_outputs3)
    intercept1, coef1 = classifier1.intercept, classifier1.coef
    computed_test_outputs1 = classifier1.predict(test_inputs)
    computed_test_outputs2 = classifier2.predict(test_inputs)
    computed_test_outputs3 = classifier3.predict(test_inputs)
    print(intercept1)
    print(coef1)

    error1 = 0.0
    for t1, t2 in zip(computed_test_outputs1, test_outputs1):
        error1 += (t1 - t2) ** 2
    error1 = error1 / len(test_outputs)
    print("prediction error1 (manual): ", error1)

    error2 = 0.0
    for t1, t2 in zip(computed_test_outputs2, test_outputs2):
        error2 += (t1 - t2) ** 2
    error2 = error2 / len(test_outputs)
    print("prediction error2 (manual): ", error2)

    error3 = 0.0
    for t1, t2 in zip(computed_test_outputs3, test_outputs3):
        error3 += (t1 - t2) ** 2
    error3 = error3 / len(test_outputs)
    print("prediction error3 (manual): ", error3)

    # error = 1 - accuracy_score(test_outputs, computed_test_outputs)
    # print("classification error (tool): ", error)

    #plot_train(train_inputs, train_outputs)
    plot_predictions2(test_inputs, test_outputs, computed_test_outputs1, computed_test_outputs2, computed_test_outputs3)



if __name__ == '__main__':
    main()
