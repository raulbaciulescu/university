import csv
import os

import gensim
import numpy as np
from sklearn.cluster import KMeans
from sklearn.feature_extraction.text import CountVectorizer, TfidfVectorizer
from sklearn.metrics import accuracy_score

from utils import kmeans

crt_dir = os.getcwd()
file_name = os.path.join(crt_dir, 'data', 'spam.csv')

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

print(inputs[:2])
print(label_names[:2])

np.random.seed(5)
no_samples = len(inputs)
indexes = [i for i in range(no_samples)]
train_sample = np.random.choice(indexes, int(0.8 * no_samples), replace=False)
test_sample = [i for i in indexes if not i in train_sample]

train_inputs = [inputs[i] for i in train_sample]
train_outputs = [outputs[i] for i in train_sample]
test_inputs = [inputs[i] for i in test_sample]
test_outputs = [outputs[i] for i in test_sample]

print(test_inputs[:3])

# extract some features from the raw text


# # representation 1: Bag of Words
# vectorizer = CountVectorizer()
# train_features = vectorizer.fit_transform(train_inputs)
# test_features = vectorizer.transform(test_inputs)
#
# # vocabulary from the train data
# print('vocab: ', vectorizer.get_feature_names_out()[:10])
# # extracted features
# print('features: ', train_features.toarray()[:3][:10])

# representation 2: tf-idf features - word granularity
vectorizer = TfidfVectorizer(max_features=50)
train_features = vectorizer.fit_transform(train_inputs)
test_features = vectorizer.transform(test_inputs)
# vocabbulary from the train data
print('vocab: ', vectorizer.get_feature_names_out()[:10])
# extracted features
print('features: ', train_features.toarray()[:3])

# representation 3: embedded features extracted by a pre-train model (in fact, word2vec pretrained model)
# Load Google's pre-trained Word2Vec
# crt_dir = os.getcwd()
# model_path = os.path.join(crt_dir, 'models', 'GoogleNews-vectors-negative300.bin')
#
# word2vecModel300 = gensim.models.KeyedVectors.load_word2vec_format(model_path, binary=True)
# print(word2vecModel300.most_similar('support'))
# print("vec for house: ", word2vecModel300["house"])

#
# def featureComputation(model, data):
#     features = []
#     phrases = [phrase.split() for phrase in data]
#     for phrase in phrases:
#         # compute the embeddings of all the words from a phrase (words of more than 2 characters) known by the model
#         vectors = [model[word] for word in phrase if (len(word) > 2) and (word in model.vocab.keys())]
#         if len(vectors) == 0:
#             result = [0.0] * model.vector_size
#         else:
#             result = np.sum(vectors, axis=0) / len(vectors)
#         features.append(result)
#     return features
#
#
# trainFeatures = featureComputation(word2vecModel300, train_inputs)
# testFeatures = featureComputation(word2vecModel300, test_inputs)

unsupervisedClassifier = KMeans(n_clusters=2, random_state=0)
unsupervisedClassifier.fit(train_features)

computedTestIndexes = unsupervisedClassifier.predict(test_features)
computedTestOutputs = [label_names[value] for value in computedTestIndexes]
print("acc: ", accuracy_score(test_outputs, computedTestOutputs))

classifier = kmeans(train_features, 2, 1000)
print(classifier)