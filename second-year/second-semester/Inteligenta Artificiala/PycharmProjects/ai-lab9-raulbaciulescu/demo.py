import numpy as np
from sklearn.datasets import load_breast_cancer
from sklearn.preprocessing import StandardScaler
import matplotlib.pyplot as plt

from utils import plot_data_histogram, normalisation, plot_classification_data, plot_predictions

data = load_breast_cancer()
inputs = data['data']
outputs = data['target']
output_names = data['target_names']
feature_names = list(data['feature_names'])
feature1 = [feat[feature_names.index('mean radius')] for feat in inputs]
feature2 = [feat[feature_names.index('mean texture')] for feat in inputs]
inputs = [[feat[feature_names.index('mean radius')], feat[feature_names.index('mean texture')]] for feat in inputs]

labels = set(outputs)
no_data = len(inputs)
print(labels)
for crt_label in labels:
    x = [feature1[i] for i in range(no_data) if outputs[i] == crt_label]
    y = [feature2[i] for i in range(no_data) if outputs[i] == crt_label]
    plt.scatter(x, y, label=output_names[crt_label])
plt.xlabel('mean radius')
plt.ylabel('mean texture')
plt.legend()
plt.show()

# plot_data_histogram(feature1, 'mean radius')
# plot_data_histogram(feature2, 'mean texture')
# plot_data_histogram(outputs, 'cancer class')


np.random.seed(5)
indexes = [i for i in range(len(inputs))]
train_sample = np.random.choice(indexes, int(0.8 * len(inputs)), replace=False)
test_sample = [i for i in indexes if not i in train_sample]

train_inputs = [inputs[i] for i in train_sample]
train_outputs = [outputs[i] for i in train_sample]
test_inputs = [inputs[i] for i in test_sample]
test_outputs = [outputs[i] for i in test_sample]

# normalise
train_inputs, test_inputs = normalisation(train_inputs, test_inputs)

# plot the normalised data
feature1train = [ex[0] for ex in train_inputs]
feature2train = [ex[1] for ex in train_inputs]
feature1test = [ex[0] for ex in test_inputs]
feature2test = [ex[1] for ex in test_inputs]
#plot_classification_data(feature1train, feature2train, train_outputs, 'normalised train data')

# # using sklearn
from sklearn import linear_model
classifier2 = linear_model.LogisticRegression()
print(train_outputs)
classifier2.fit(train_inputs, train_outputs)

intercept, coef = classifier2.intercept_, classifier2.coef_
print(intercept)
print(coef)

# using developed code
from logistic_regression import MyLogisticRegression
# model initialisation
classifier = MyLogisticRegression()

classifier.fit(train_inputs, train_outputs)
w0, w1, w2 = classifier.intercept, classifier.coef[0], classifier.coef[1]
# print('classification model: y(feat1, feat2) = ', w0, ' + ', w1, ' * feat1 + ', w2, ' * feat2')

# step4: testare model, plot rezultate, forma outputului si interpretarea lui

# makes predictions for test data
computedTestOutputs = [w0 + w1 * el[0] + w2 * el[1] for el in test_inputs]

# makes predictions for test data (by tool)
computed_test_outputs = classifier.predict(test_inputs)

#plot_predictions(feature1test, feature2test, test_outputs, computed_test_outputs, "real test data", output_names)


# step5: calcul metrici de performanta (acc)

# evalaute the classifier performance
# compute the differences between the predictions and real outputs
# print("acc score: ", classifier.score(testInputs, testOutputs))
error = 0.0
for t1, t2 in zip(computed_test_outputs, test_outputs):
    if t1 != t2:
        error += 1
error = error / len(test_outputs)
print("classification error (manual): ", error)

from sklearn.metrics import accuracy_score
error = 1 - accuracy_score(test_outputs, computed_test_outputs)
print("classification error (tool): ", error)