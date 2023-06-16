from cmath import sqrt

import numpy as np


class Evaluation:
    def __init__(self, flowers=None, sport=None):
        self.__confusion_matrix = []
        self.__flowers = flowers
        self.__sport = sport


    def compute_confusion_matrix_flowers(self):
        self.__confusion_matrix = np.zeros((3, 3))
        for i in range(len(self.__flowers)):
            if self.__flowers['Type'][i] == 'Daisy' and self.__flowers['PredictedType'][i] == 'Daisy':
                self.__confusion_matrix[0][0] += 1
            if self.__flowers['Type'][i] == 'Daisy' and self.__flowers['PredictedType'][i] == 'Tulip':
                self.__confusion_matrix[0][1] += 1
            if self.__flowers['Type'][i] == 'Daisy' and self.__flowers['PredictedType'][i] == 'Rose':
                self.__confusion_matrix[0][2] += 1


            if self.__flowers['Type'][i] == 'Tulip' and self.__flowers['PredictedType'][i] == 'Daisy':
                self.__confusion_matrix[1][0] += 1
            if self.__flowers['Type'][i] == 'Tulip' and self.__flowers['PredictedType'][i] == 'Tulip':
                self.__confusion_matrix[1][1] += 1
            if self.__flowers['Type'][i] == 'Tulip' and self.__flowers['PredictedType'][i] == 'Rose':
                self.__confusion_matrix[1][2] += 1

            if self.__flowers['Type'][i] == 'Rose' and self.__flowers['PredictedType'][i] == 'Daisy':
                self.__confusion_matrix[2][0] += 1
            if self.__flowers['Type'][i] == 'Rose' and self.__flowers['PredictedType'][i] == 'Tulip':
                self.__confusion_matrix[2][1] += 1
            if self.__flowers['Type'][i] == 'Rose' and self.__flowers['PredictedType'][i] == 'Rose':
                self.__confusion_matrix[2][2] += 1

    def flowers_evaluation(self):
        fn_daisy = self.__confusion_matrix[0][1] + self.__confusion_matrix[0][2]
        fn_tulip = self.__confusion_matrix[1][0] + self.__confusion_matrix[1][2]
        fn_rose = self.__confusion_matrix[2][0] + self.__confusion_matrix[2][1]

        fp_daisy = self.__confusion_matrix[1][0] + self.__confusion_matrix[2][0]
        fp_tulip = self.__confusion_matrix[0][1] + self.__confusion_matrix[2][1]
        fp_rose = self.__confusion_matrix[1][2] + self.__confusion_matrix[0][2]

        tp_daisy = self.__confusion_matrix[0][0]
        tp_tulip = self.__confusion_matrix[1][1]
        tp_rose = self.__confusion_matrix[2][2]

        precision_daisy = tp_daisy / (tp_daisy + fp_daisy)
        precision_tulip = tp_tulip / (tp_tulip + fp_tulip)
        precision_rose = tp_rose / (tp_rose + fp_rose)

        recall_daisy = tp_daisy / (tp_daisy + fn_daisy)
        recall_tulip = tp_tulip / (tp_tulip + fn_tulip)
        recall_rose = tp_rose / (tp_rose + fn_rose)

        accuracy = sum([self.__confusion_matrix[i][i] for i in range(3)]) / sum(map(sum, self.__confusion_matrix))
        print('acc: ', accuracy, ' precision: ', [precision_daisy, precision_tulip, precision_rose], ' recall: ', [recall_daisy, recall_tulip, recall_rose])

    def sport_evaluation(self):
        error_weight1 = sum(abs(r - c) for r, c in zip(self.__sport['Weight'], self.__sport['PredictedWeight'])) / len(self.__sport)
        print('error_weight1: ', error_weight1)
        error_weight2 = sqrt(sum((r - c) ** 2 for r, c in zip(self.__sport['Weight'], self.__sport['PredictedWeight'])) /  len(self.__sport))
        print('error_weight2: ', error_weight2)
        error_waist1 = sum(abs(r - c) for r, c in zip(self.__sport['Waist'], self.__sport['PredictedWaist'])) / len(
            self.__sport)
        print('error_waist1: ', error_waist1)
        error_waist2 = sqrt(
            sum((r - c) ** 2 for r, c in zip(self.__sport['Waist'], self.__sport['PredictedWaist'])) / len(self.__sport))
        print('error_waist2: ', error_waist2)
        error_pulse1 = sum(abs(r - c) for r, c in zip(self.__sport['Pulse'], self.__sport['PredictedPulse'])) / len(
            self.__sport)
        print('error_pulse1: ', error_pulse1)
        error_pulse2 = sqrt(
            sum((r - c) ** 2 for r, c in zip(self.__sport['Pulse'], self.__sport['PredictedPulse'])) / len(
                self.__sport))
        print('error_pulse2: ', error_pulse2)

