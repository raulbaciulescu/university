from sklearn import neural_network
from sklearn import linear_model
from sklearn.preprocessing import StandardScaler
from sklearn.model_selection import train_test_split
import numpy as np 
from numpy import *
from sklearn import neural_network
import matplotlib.pyplot as plt 
from sklearn import neural_network
from sklearn import linear_model


class NeuronLayer():
    def __init__(self, number_of_neurons, number_of_inputs_per_neuron):
        self.synaptic_weights = 2 * np.random.random((number_of_inputs_per_neuron, number_of_neurons)) - 1

class NeuralNet:
    def __init__(self, layer1,layer2):
        self.layer1 = layer1
        self.layer2 = layer2

    def sigmoid(self,x):
        return 1 / (1 + np.exp(-x))
    
    def der(self,x):
        return x * (1 - x)

    def ReLU(self, x, der=False):
        if (der == True):
            f = np.heaviside(x, 1)
        else :
            f = np.maximum(x, 0)
        return f

    def fit(self,trainInputs, trainOutputs, epochs, learnRate=0.05):
        for epoch in range(epochs):
            # Pass the training set through our neural network
            output_from_layer_1, output_from_layer_2 = self.think(trainInputs)

            layer2_error = trainOutputs - output_from_layer_2
            layer2_delta = layer2_error * self.ReLU(self.der(output_from_layer_2),der=True)

            layer1_error = layer2_delta.dot(self.layer2.synaptic_weights.T)
            layer1_delta = layer1_error * self.ReLU(self.der(output_from_layer_1),der=True)

            # Calculate how much to adjust the weights by
            layer1_adjustment = learnRate*trainInputs.T.dot(layer1_delta)
            layer2_adjustment = learnRate*output_from_layer_1.T.dot(layer2_delta)

            # Adjust the weights.
            self.layer1.synaptic_weights += layer1_adjustment
            self.layer2.synaptic_weights += layer2_adjustment

    def think(self, inputs):
        output_from_layer1 = self.sigmoid(np.dot(inputs, self.layer1.synaptic_weights))
        output_from_layer2 = self.sigmoid(np.dot(output_from_layer1, self.layer2.synaptic_weights))
        return output_from_layer1, output_from_layer2

    def predict(self, testInputs):
        rez = []
        _, outputs = self.think(testInputs)
        for i in range(len(outputs)):
            if outputs[i][0]==max(outputs[i]):
                rez.append(0)
            elif outputs[i][1]==max(outputs[i]):
                rez.append(1)
            elif outputs[i][2]==max(outputs[i]):
                rez.append(2)
            elif outputs[i][3]==max(outputs[i]):
                rez.append(3)
            elif outputs[i][4]==max(outputs[i]):
                rez.append(4)
            elif outputs[i][5]==max(outputs[i]):
                rez.append(5)
            elif outputs[i][6]==max(outputs[i]):
                rez.append(6)
            elif outputs[i][7]==max(outputs[i]):
                rez.append(7)
            elif outputs[i][8]==max(outputs[i]):
                rez.append(8)
            elif outputs[i][9]==max(outputs[i]):
                rez.append(9)
        return rez


def loadDigitData():
    from sklearn.datasets import load_digits

    data = load_digits()
    inputs = data.images
    outputs = data['target']
    outputNames = data['target_names']
     
    # shuffle the original data
    noData = len(inputs)
    permutation = np.random.permutation(noData)
    inputs = inputs[permutation]
    outputs = outputs[permutation]

    return inputs, outputs, outputNames


def splitData(inputs, outputs):
    np.random.seed(55)
    indexes = [i for i in range(len(inputs))]
    trainSample = np.random.choice(indexes, int(0.8 * len(inputs)), replace = False)
    testSample = [i for i in indexes  if not i in trainSample]

    trainInputs = [inputs[i] for i in trainSample]
    trainOutputs = [outputs[i] for i in trainSample]
    testInputs = [inputs[i] for i in testSample]
    testOutputs = [outputs[i] for i in testSample]
    
    return trainInputs, trainOutputs, testInputs, testOutputs

def normalisation(trainData, testData):
    scaler = StandardScaler()
    if not isinstance(trainData[0], list):
        #encode each sample into a list
        trainData = [[d] for d in trainData]
        testData = [[d] for d in testData]
        
        scaler.fit(trainData)  #  fit only on training data
        normalisedTrainData = scaler.transform(trainData) # apply same transformation to train data
        normalisedTestData = scaler.transform(testData)  # apply same transformation to test data
        
        #decode from list to raw values
        normalisedTrainData = [el[0] for el in normalisedTrainData]
        normalisedTestData = [el[0] for el in normalisedTestData]
    else:
        scaler.fit(trainData)  #  fit only on training data
        normalisedTrainData = scaler.transform(trainData) # apply same transformation to train data
        normalisedTestData = scaler.transform(testData)  # apply same transformation to test data
    return normalisedTrainData, normalisedTestData


def data2FeaturesMoreClasses(inputs, outputs):
    labels = set(outputs)
    noData = len(inputs)
    for crtLabel in labels:
        x = [inputs[i][0] for i in range(noData) if outputs[i] == crtLabel ]
        y = [inputs[i][1] for i in range(noData) if outputs[i] == crtLabel ]
        plt.scatter(x, y, label = outputNames[crtLabel])
    plt.xlabel('feat1')
    plt.ylabel('feat2')
    plt.legend()
    plt.show() 

def training(classifier, trainInputs, trainOutputs):
    # step3: training the classifier
    # identify (by training) the classification model
    classifier.fit(trainInputs, trainOutputs)


def classification(classifier, testInputs):
    # step4: testing (predict the labels for new inputs)
    # makes predictions for test data 
    computedTestOutputs = classifier.predict(testInputs)

    return computedTestOutputs

def plotConfusionMatrix(cm, classNames, title):
    from sklearn.metrics import confusion_matrix
    import itertools 

    classes = classNames
    plt.figure()
    plt.imshow(cm, interpolation = 'nearest', cmap = 'Blues')
    plt.title('Confusion Matrix ' + title)
    plt.colorbar()
    tick_marks = np.arange(len(classNames))
    plt.xticks(tick_marks, classNames, rotation=45)
    plt.yticks(tick_marks, classNames)

    text_format = 'd'
    thresh = cm.max() / 2.
    for row, column in itertools.product(range(cm.shape[0]), range(cm.shape[1])):
        plt.text(column, row, format(cm[row, column], text_format),
                horizontalalignment = 'center',
                color = 'white' if cm[row, column] > thresh else 'black')

    plt.ylabel('True label')
    plt.xlabel('Predicted label')
    plt.tight_layout()

    plt.show()

def evalMultiClass(realLabels, computedLabels, labelNames):
    from sklearn.metrics import confusion_matrix

    confMatrix = confusion_matrix(realLabels, computedLabels)
    acc = sum([confMatrix[i][i] for i in range(len(labelNames))]) / len(realLabels)
    precision = {}
    recall = {}
    for i in range(len(labelNames)):
        precision[labelNames[i]] = confMatrix[i][i] / sum([confMatrix[j][i] for j in range(len(labelNames))])
        recall[labelNames[i]] = confMatrix[i][i] / sum([confMatrix[i][j] for j in range(len(labelNames))])
    return acc, precision, recall, confMatrix

def modify(outputs):
    rez = []
    for output in outputs:
        if output==0:
            rez.append([1,0,0,0,0,0,0,0,0,0])
        elif output==1:
            rez.append([0,1,0,0,0,0,0,0,0,0])
        elif output==2:
            rez.append([0,0,1,0,0,0,0,0,0,0])
        elif output==3:
            rez.append([0,0,0,1,0,0,0,0,0,0])
        elif output==4:
            rez.append([0,0,0,0,1,0,0,0,0,0])
        elif output==5:
            rez.append([0,0,0,0,0,1,0,0,0,0])
        elif output==6:
            rez.append([0,0,0,0,0,0,1,0,0,0])
        elif output==7:
            rez.append([0,0,0,0,0,0,0,1,0,0])
        elif output==8:
            rez.append([0,0,0,0,0,0,0,0,1,0])
        elif output==9:
            rez.append([0,0,0,0,0,0,0,0,0,1])
    return rez

def flatten(mat):
    x = []
    for line in mat:
        for el in line:
            x.append(el)
    return x 

inputs, outputs, outputNames = loadDigitData()
trainInputs, trainOutputs, testInputs, testOutputs = splitData(inputs, outputs)
trainInputsFlatten = [flatten(el) for el in trainInputs]
testInputsFlatten = [flatten(el) for el in testInputs]


# plot the training data distribution on classes
#plt.hist(trainOutputs, 3, rwidth = 0.8)
#plt.xticks(np.arange(len(outputNames)), outputNames)
#plt.show()

# plot the data in order to observe the shape of the classifier required in this problem
#data2FeaturesMoreClasses(trainInputs, trainOutputs)

# normalise the data
trainInputs, testInputs = normalisation(trainInputsFlatten, testInputsFlatten)

# liniar classifier and one-vs-all approach for multi-class
# classifier = linear_model.LogisticRegression()

# non-liniar classifier and softmax approach for multi-class 
classifier = neural_network.MLPClassifier(hidden_layer_sizes=(5,), activation='relu', max_iter=100, solver='sgd', verbose=10, random_state=1, learning_rate_init=.1)


layer1=NeuronLayer(20,64)
layer2=NeuronLayer(10,20)

neuralNet = NeuralNet(layer1,layer2)
trainOutputsModified = modify(trainOutputs)
neuralNet.fit(trainInputs,trainOutputsModified,1000,1)

training(classifier, trainInputs, trainOutputs)
predictedLabels = classification(classifier, testInputs)
#acc, prec, recall, cm = evalMultiClass(np.array(testOutputs), predictedLabels, outputNames)
acc, prec, recall, cm = evalMultiClass(np.array(testOutputs), neuralNet.predict(testInputs), outputNames)
plotConfusionMatrix(cm, outputNames, "digit classification")

print('acc: ', acc)
print('precision: ', prec)
print('recall: ', recall)

