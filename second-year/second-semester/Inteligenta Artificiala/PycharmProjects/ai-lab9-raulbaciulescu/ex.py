import numpy as np
from sklearn import linear_model

from logistic_regression import MyLogisticRegression
from utils import read_data2, normalisation

inputs, outputs = read_data2()

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

train_inputs, test_inputs = normalisation(train_inputs, test_inputs)

# classifier = linear_model.LogisticRegression()
# classifier.fit(train_inputs, train_outputs)
# intercept, coef = classifier.intercept_, classifier.coef_
# computed_test_outputs = classifier.predict(test_inputs)
# print(intercept)
# print(coef)


classifier1 = MyLogisticRegression()
train_outputs1 = [1 if flower == 'Iris-setosa' else 0 for flower in train_outputs]
classifier1.fit(train_inputs, train_outputs1)
intercept1, coef1 = classifier1.intercept, classifier1.coef
computed_test_outputs = classifier1.predict(test_inputs)
print(intercept1)
print(coef1)

error = 0.0
for t1, t2 in zip(computed_test_outputs, test_outputs1):
    error += (t1 - t2) ** 2
error = error / len(test_outputs)
print("prediction error (manual): ", error)