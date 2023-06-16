
import os
import cv2

from skimage.io import imread
from skimage.transform import resize


import tensorflow as tf
from tensorflow.keras import datasets, layers, models
import matplotlib.pyplot as plt
import numpy as np

def load_images(src, width=150, height=150):
    outputs = []
    inputs = []

    for subdir in os.listdir(src):
        print(subdir)
        current_path = os.path.join(src, subdir)

        for file in os.listdir(current_path):
            if file[-3:] in {'jpg'}:
                image = imread(os.path.join(current_path, file))
                image = resize(image, (width, height))
                outputs.append(subdir)
                inputs.append(image)

    return inputs, outputs


def transform_image(images):
    transformed = []
    for img in images:
        pixels = np.float32(img.reshape(-1, 3))

        n_colors = 2
        criteria = (cv2.TERM_CRITERIA_EPS + cv2.TERM_CRITERIA_MAX_ITER, 200, .1)
        flags = cv2.KMEANS_RANDOM_CENTERS

        _, labels, palette = cv2.kmeans(pixels, n_colors, None, criteria, 10, flags)
        _, counts = np.unique(labels, return_counts=True)
        dominant = palette[np.argmax(counts)]
        transformed.append(dominant)

    return transformed


def rgb_to_integer(inputs):
    inputs_new = []
    for i in range(len(inputs)):
        matrix_new = []
        for j in range(len(inputs[i])):
            line = []
            for k in range(len(inputs[i][j])):
                line.append(np.sum(inputs[i][j][k]) / 3)
            matrix_new.append(line)
        inputs_new.append(matrix_new)
    return inputs_new

def flatten(mat):
    x = []
    for line in mat:
        for el in line:
            x.append(el)
    return x

crt_dir = os.getcwd()
dataPath = os.path.join(crt_dir, 'data/photos')
inputs, outputs = load_images(dataPath, 10)
inputs_raw, outputs_raw = inputs, outputs
#inputs = [inputs[i].flatten().reshape(45, 100).reshape(-1) for i in range(len(inputs))]
# # pixels = inputs[0].flatten().reshape(300, 150)
# print('-------------------------')
# print(np.array(inputs).shape)



inputs = np.array(transform_image(inputs))
print(outputs)
outputs = [1 if i == 'Normal' else 0 for i in outputs]
print(outputs)
outputs = np.array(outputs)

np.random.seed(5)
indexes = [i for i in range(len(inputs))]
train_sample = np.random.choice(indexes, int(0.8 * len(inputs)), replace=False)
test_sample = [i for i in indexes if not i in train_sample]
train_inputs = [inputs[i] for i in train_sample]
train_outputs = [outputs[i] for i in train_sample]
test_inputs = [inputs[i] for i in test_sample]
test_outputs = [outputs[i] for i in test_sample]


train_inputs /= 255.0
test_inputs /= 255.0

cnn = models.Sequential([
    layers.Conv2D(filters=32, kernel_size=(3, 3), activation='relu', input_shape=(32, 32, 3)),
    layers.MaxPooling2D((2, 2)),

    layers.Conv2D(filters=64, kernel_size=(3, 3), activation='relu'),
    layers.MaxPooling2D((2, 2)),

    layers.Flatten(),
    layers.Dense(64, activation='relu'),
    layers.Dense(10, activation='softmax')
])

cnn.compile(optimizer='adam',
              loss='sparse_categorical_crossentropy',
              metrics=['accuracy'])



