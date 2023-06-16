import math
from math import sqrt
import matplotlib.pyplot as plt
import numpy as np
import tf as tf

from sklearn.metrics import confusion_matrix, accuracy_score, precision_score, recall_score

def demo1():
    # Problem specification:
    # input: realOutputs, computedOutputs - arrays of the same length containing real numbers
    # output: error - real value

    realOutputs = [7.53, 7.52, 7.5, 7.49, 7.46]
    computedOutputs = [7.8, 7.75, 7.45, 7.6, 7.4]

    # plot the data
    indexes = [i for i in range(len(realOutputs))]
    real, = plt.plot(indexes, realOutputs, 'ro', label='real')
    computed, = plt.plot(indexes, computedOutputs, 'bo', label='computed')
    plt.xlim(-0.5, 5)
    plt.ylim(7, 8)
    plt.legend([real, (real, computed)], ["Real", "Computed"])
    plt.show()

    # compute the prediction error

    # MAE
    errorL1 = sum(abs(r - c) for r, c in zip(realOutputs, computedOutputs)) / len(realOutputs)
    print('Error (L1): ', errorL1)

    # RMSE
    errorL2 = sqrt(sum((r - c) ** 2 for r, c in zip(realOutputs, computedOutputs)) / len(realOutputs))
    print('Error (L2): ', errorL2)

def evalClassificationV1(realLabels, computedLabels, labelNames):
    cm = confusion_matrix(realLabels, computedLabels, labels=labelNames)
    print(cm)
    acc = accuracy_score(realLabels, computedLabels)
    precision = precision_score(realLabels, computedLabels, average=None, labels=labelNames)
    recall = recall_score(realLabels, computedLabels, average=None, labels=labelNames)
    return acc, precision, recall

# version 2 - native code

def evalClassificationV2(realLabels, computedLabels, pos, neg):
    # noCorrect = 0
    # for i in range(0, len(realLabels)):
    #     if (realLabels[i] == computedLabels[i]):
    #         noCorrect += 1
    # acc = noCorrect / len(realLabels)
    acc = sum([1 if realLabels[i] == computedLabels[i] else 0 for i in range(0, len(realLabels))]) / len(realLabels)

    # TP = 0
    # for i in range(0, len(realLabels)):
    #     if (realLabels[i] == 'spam' and computedLabels[i] == 'spam'):
    #         TP += 1
    TP = sum([1 if (realLabels[i] == pos and computedLabels[i] == pos) else 0 for i in range(len(realLabels))])
    FP = sum([1 if (realLabels[i] == neg and computedLabels[i] == pos) else 0 for i in range(len(realLabels))])
    TN = sum([1 if (realLabels[i] == neg and computedLabels[i] == neg) else 0 for i in range(len(realLabels))])
    FN = sum([1 if (realLabels[i] == pos and computedLabels[i] == neg) else 0 for i in range(len(realLabels))])

    precisionPos = TP / (TP + FP)
    recallPos = TP / (TP + FN)
    precisionNeg = TN / (TN + FN)
    recallNeg = TN / (TN + FP)

    return acc, precisionPos, precisionNeg, recallPos, recallNeg

def evalMultiClassV2(realLabels, computedLabels, labelNames):
    confMatrix = [[0 for _ in labelNames] for _ in labelNames]
    for k in range(len(realLabels)):
        for i in range(len(labelNames)):
            for j in range(len(labelNames)):
                if (realLabels[k] == labelNames[i]) and (computedLabels[k] == labelNames[j]):
                    confMatrix[i][j] += 1
    acc = sum([confMatrix[i][i] for i in range(len(labelNames))]) / len(realLabels)
    precision = {}
    recall = {}
    for i in range(len(labelNames)):
        precision[labelNames[i]] = confMatrix[i][i] / sum([confMatrix[j][i] for j in range(len(labelNames))])
        recall[labelNames[i]] = confMatrix[i][i] / sum([confMatrix[i][j] for j in range(len(labelNames))])
    return acc, precision, recall


def demo2_1():
    # consider some real labels and some predicted labels (obtained by the ML algorithm - a classifier)
    # we want ot estimate the error of prediction (classification)

    # Problem specification:
    # input: realLabels, computedLabels - arrays of the same length containing binary labels (some discrete values)
    # output: accuracy, precision, recall - real values in range [0,1]

    # a balanced data set (each class containes the same numer of samples)

    realLabels = ['spam', 'spam', 'ham', 'ham', 'spam', 'ham']
    computedLabels = ['spam', 'ham', 'ham', 'spam', 'spam', 'ham']

    # suppose that 'spam' is the positive class (and 'ham' is the negative class)
    # compute the prediction performance
    acc, prec, recall = evalClassificationV1(realLabels, computedLabels, ['spam', 'ham'])

    # acc, prec, recall = evalClassificationV2(realLabels, computedLabels, 'spam', 'ham')

    print('acc: ', acc, ' precision: ', prec, ' recall: ', recall)

def demo2_2():
    # consider some real labels and some predicted labels (obtained by the ML algorithm - a classifier)
    # we want ot estimate the error of prediction (classification)

    # Problem specification:
    # input: realLabels, computedLabels - arrays of the same length containing binary labels (some discrete values)
    # output: accuracy, precision, recall - real values in range [0,1]

    # an unbalanced data set (the numer of samples from each class are not uniform distributed)

    realLabels = ['infected', 'infected', 'infected', 'infected', 'normal', 'normal', 'normal', 'normal', 'normal',
                  'normal', 'normal', 'normal', 'normal', 'normal', 'normal']
    computedLabels = ['infected', 'infected', 'normal', 'normal', 'normal', 'normal', 'normal', 'normal', 'normal',
                      'normal', 'normal', 'normal', 'normal', 'normal', 'infected']

    acc, prec, recall = evalClassificationV1(realLabels, computedLabels, ['infected', 'normal'])
    # acc, prec, recall = evalClassificationV2(realLabels, computedLabels, 'infected', 'normal')

    print('acc: ', acc, ' precision: ', prec, ' recall: ', recall)


def demo3():
    # consider some real labels and, instead of some predicted labels, we have some values (real values representing probabilities asscoaited to our labels) (obtained by the ML algorithm - a classifier)
    # we want ot estimate the error of prediction (classification)
    # reconsider the problem of emails' classification (spam vs ham)

    # Problem specification:
    # input: realLabels, computedOutputs - arrays of the same length containing labels (some discrete values) and real values, respectively
    # output: accuracy, precision, recall - real values in range [0,1]

    # if the rawOutputs of the ML algorithms are probabilities (not labels)
    realLabels = ['spam', 'spam', 'ham', 'ham', 'spam', 'ham']
    computedOutputs = [[0.7, 0.3], [0.2, 0.8], [0.4, 0.6], [0.9, 0.1], [0.7, 0.3], [0.4, 0.6]]
    # computedLabels have to be  ['spam', 'ham', 'ham', 'spam', 'spam', 'ham']

    # step1: transform the raw outputs into labels

    # version 1 - native code
    # computedLabels = []
    # labelNames = list(set(realLabels))
    # for p in computedOutputs:
    #     probMaxPos = p.index(max(p))
    #     label = labelNames[probMaxPos]
    #     computedLabels.append(label)

    # version 2 - by using NumPy library

    labelNames = list(set(realLabels))
    computedLabels = [labelNames[np.argmax(p)] for p in computedOutputs]

    # step2: compute the performance
    acc, prec, recall = evalClassificationV1(realLabels, computedLabels, ['spam', 'ham'])

    print('acc: ', acc, ' precision: ', prec, ' recall: ', recall)


def evalLogLossV1(realLabels, computedOutputs):
    # suppose that 'spam' is the positive class
    realOutputs = [[1, 0] if label == 'spam' else [0, 1] for label in realLabels]
    datasetSize = len(realLabels)
    noClasses = len(set(realLabels))
    datasetCE = 0.0
    for i in range(datasetSize):
        sampleCE = - sum([realOutputs[i][j] * math.log(computedOutputs[i][j]) for j in range(noClasses)])
        datasetCE += sampleCE
    meanCE = datasetCE / datasetSize
    return meanCE


# def evalLogLossV2(realLabels, computedOutputs):
#     g = tf.Graph()
#     session = tf.InteractiveSession(graph=g)
#
#     realOutputsTF = tf.constant(realOutputs, dtype=tf.float32)
#     computedOutputsTF = tf.constant(computedOutputs, dtype=tf.float32)
#     ce = tf.losses.log_loss(realOutputsTF, computedOutputsTF)
#     ceEval = session.run(ce)
#     print(ceEval)
#
#     session.close()


def evalSoftmaxCEsample(targetValues, rawOutputs):
    # apply softmax for all raw outputs
    noClasses = len(targetValues)
    expValues =[math.exp(val) for val in rawOutputs]
    sumExpVal = sum(expValues)
    mapOutputs = [val / sumExpVal for val in expValues]
    print(mapOutputs, ' sum: ', sum(mapOutputs))
    sampleCE = - sum([targetValues[j] * math.log(mapOutputs[j]) for j in range(noClasses)])
    return sampleCE


def evalSigmoidCEsample(targetValues, rawOutputs):
    # apply softmax for all raw outputs
    noClasses = len(targetValues)
    mapOutputs = [1 / (1 + math.exp(-val)) for val in rawOutputs]
    print(mapOutputs, ' sum: ', sum(mapOutputs))
    sampleCE = - sum([targetValues[j] * math.log(mapOutputs[j]) for j in range(noClasses)])
    return sampleCE
