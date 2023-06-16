import csv
import os

import gensim
import numpy as np
from sklearn.cluster import KMeans
from sklearn.feature_extraction.text import CountVectorizer, TfidfVectorizer
from sklearn.metrics import accuracy_score

from utils import kmeans, get_vocabulary, vectorize_sentences

# Loading the required modules

import numpy as np
from scipy.spatial.distance import cdist
from sklearn.datasets import load_digits
from sklearn.decomposition import PCA
from sklearn.cluster import KMeans
import matplotlib.pyplot as plt


def to_numbers(arr):
    numbers = []
    for i in arr:
        if i == 'positive':
            numbers.append(1)
        else:
            numbers.append(0)
    return numbers


crt_dir = os.getcwd()
file_name = os.path.join(crt_dir, 'data', 'reviews_mixed.csv')

data = []
with open(file_name) as csv_file:
    csv_reader = csv.reader(csv_file, delimiter=',')
    line_count = 0
    for row in csv_reader:
        if line_count == 0:
            data_names = row
        else:
            data.append(row)
        line_count += 1

inputs = [data[i][0] for i in range(len(data))]
outputs = [data[i][1] for i in range(len(data))]
label_names = list(set(outputs))

# inputs, outputs, label_names = readData()
np.random.seed(5)
no_sample = len(inputs)
indexes = [i for i in range(no_sample)]
train_sample = np.random.choice(indexes, int(0.8 * no_sample), replace=False)
test_sample = [i for i in indexes if not i in train_sample]

train_inputs = [inputs[i] for i in train_sample]
train_outputs = [outputs[i] for i in train_sample]
test_inputs = [inputs[i] for i in test_sample]
test_outputs = [outputs[i] for i in test_sample]

vocab = get_vocabulary(train_inputs)
train_features = vectorize_sentences(train_inputs, vocab)
test_features = vectorize_sentences(test_outputs, vocab)

train_features = np.array(train_features)
train_output_numbers = to_numbers(train_outputs)

computed_train_outputs, centroids = kmeans(train_features, 2, 10)

print("acc train: ", accuracy_score(train_output_numbers, computed_train_outputs))

correct_predictions = 0
test_outputs_numbers = np.array(to_numbers(test_outputs))

for sentence, type in zip(test_features, test_outputs):
    dist_from_centroid1 = 0
    dist_from_centroid2 = 0
    for i in range(len(sentence) - 1):
        dist_from_centroid1 += (sentence[i] - centroids[0][i]) * (sentence[i] - centroids[0][i])
        dist_from_centroid2 += (sentence[i] - centroids[1][i]) * (sentence[i] - centroids[1][i])

    if (dist_from_centroid1 < dist_from_centroid2 and type == 'negative') or \
            (dist_from_centroid2 <= dist_from_centroid1 and type == 'positive'):
        correct_predictions += 1


print("acc: ", correct_predictions / len(test_outputs))
# print("acc: ", accuracy_score(test_outputs_numbers, test_outputs))
