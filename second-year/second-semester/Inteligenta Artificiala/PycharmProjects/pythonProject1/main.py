
from evaluation import Evaluation
import pandas as pd

from example import *


def main():
    flowers = pd.read_csv('C:\\Users\\raulb\PycharmProjects\\pythonProject1\\input\\flowers.csv')
    sport = pd.read_csv('C:\\Users\\raulb\PycharmProjects\\pythonProject1\\input\\sport.csv')


    computed_labels = flowers['Type']
    real_labels = flowers['PredictedType']
    e = Evaluation(flowers, sport)
    e.compute_confusion_matrix_flowers()
    e.flowers_evaluation()
    e.sport_evaluation()

    realLabels = ['spam', 'spam', 'ham', 'ham', 'spam', 'ham']
    # realLabels = [1, 1, 0, 0, 1, 0]
    computedOutputs = [[0.7, 0.3], [0.2, 0.8], [0.4, 0.6], [0.9, 0.1], [0.7, 0.3], [0.4, 0.6]]
    # computedLabels =    [1, 0, 0, 1, 1, 0]


    print('CE Loss: ', evalLogLossV1(realLabels, computedOutputs))
    print(evalSoftmaxCEsample([0, 1, 0, 0, 0], [-0.5, 1.2, 0.1, 2.4, 0.3]))

if __name__ == '__main__':
    main()

