import numpy as np
from sklearn import neural_network

from utils import load_iris_data, split_data, normalisation, eval_multi_class, plot_confusion_matrix

# def training(classifier, trainInputs, trainOutputs):
#     # step3: training the classifier
#     # identify (by training) the classification model
#     classifier.fit(trainInputs, trainOutputs)

# def classification(classifier, test_inputs):
#     # step4: testing (predict the labels for new inputs)
#     # makes predictions for test data
#     computed_outputs = classifier.predict(test_inputs)
#     return computed_outputs

inputs, outputs, output_names = load_iris_data()
train_inputs, train_outputs, test_inputs, test_outputs = split_data(inputs, outputs)
train_inputs, test_inputs = normalisation(train_inputs, test_inputs)

classifier = neural_network.MLPClassifier()
# liniar classifier and one-vs-all approach for multi-class
# classifier = linear_model.LogisticRegression()

# non-liniar classifier and softmax approach for multi-class
classifier1 = neural_network.MLPClassifier(hidden_layer_sizes=(5,), activation='relu', max_iter=100, solver='sgd', verbose=10, random_state=1, learning_rate_init=.1)
classifier1.fit(train_inputs, train_outputs)
computed_outputs = classifier1.predict(test_inputs)

acc, prec, recall, cm = eval_multi_class(np.array(test_outputs), computed_outputs, output_names)
plot_confusion_matrix(cm, output_names, "iris classification")

print('acc: ', acc)
print('precision: ', prec)
print('recall: ', recall)