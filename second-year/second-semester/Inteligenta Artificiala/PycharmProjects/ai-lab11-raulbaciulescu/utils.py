import numpy as np
from scipy.spatial.distance import cdist
from sklearn.datasets import load_digits
from sklearn.decomposition import PCA
from sklearn.cluster import KMeans
import matplotlib.pyplot as plt
import csv
import re

from sklearn.metrics import mean_squared_error


def kmeans(x, k, no_of_iterations):
    idx = np.random.choice(len(x), k, replace=False)
    centroids = x[idx, :]

    # finding the distance between centroids and all the data points
    distances = cdist(x, centroids, 'euclidean')

    # Centroid with the minimum Distance
    points = np.array([np.argmin(i) for i in distances])

    for epoch in range(no_of_iterations):
        centroids = []
        for idx in range(k):
            temp_cent = x[points == idx].mean(axis=0)
            centroids.append(temp_cent)

        centroids = np.vstack(centroids)

        distances = cdist(x, centroids, 'euclidean')
        points = np.array([np.argmin(i) for i in distances])

    return points, centroids


# BAG OF WORDS
def word_extraction(sentences):
    allWords = []
    for sentence in sentences:
        words = re.split('[ \n?,.()-:!&]', sentence)
        cleaned_text = [w.lower() for w in words if w != '']
        for el in cleaned_text:
            allWords.append(el)
    return allWords


def get_vocabulary(sentences):
    allWords = word_extraction(sentences)
    dictionary = {}
    for word in allWords:
        if word not in dictionary.keys():
            dictionary[word] = 1
        else:
            dictionary[word] += 1
    return list(dictionary)


def vectorize_sentences(sentences, vocabulary):
    vocab = vocabulary
    vectorizedSentences = []
    for sentence in sentences:
        vectorizedSentence = [0] * len(vocab)
        for word in word_extraction([sentence]):
            for i, w in enumerate(vocab):
                if word == w:
                    vectorizedSentence[i] += 1
        vectorizedSentences.append(vectorizedSentence)
    return vectorizedSentences
