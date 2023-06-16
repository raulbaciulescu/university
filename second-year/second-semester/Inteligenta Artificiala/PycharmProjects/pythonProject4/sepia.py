from cv2 import imread
from keras.api._v2.keras import models, datasets
from keras.applications.densenet import layers

import os
import cv2
from PIL import Image
# from skimage.io import imread
# from skimage.transform import resize
import keras.datasets
# from tensorflow.python.keras import datasets
# from tensorflow.keras import datasets, layers, models
import matplotlib.pyplot as plt
import numpy as np


def plot_sample(X, y, index):
    classes = ['Normal', 'Sepia']
    plt.figure(figsize=(15, 2))
    plt.imshow(X[index])
    plt.xlabel(classes[y[index]])
    plt.show()


def load_images(src, width=32, height=32):
    outputs = []
    inputs = []

    for subdir in os.listdir(src):
        print(subdir)
        current_path = os.path.join(src, subdir)

        for file in os.listdir(current_path):
            if file[-3:] in {'jpg'}:
                #image = Image.open(file)
                #image.resize((32, 32, 3))
                #image = imread(os.path.join(current_path, file))
                image.resize((150, 150, 3), refcheck=False)
                #image = resize(image, (width, height))
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


crt_dir = os.getcwd()
dataPath = os.path.join(crt_dir, 'data/photos')
inputs, outputs = load_images(dataPath, 10)
inputs_raw, outputs_raw = inputs, outputs

inputs = np.array(inputs)

print(inputs.shape)
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

train_inputs = np.array(train_inputs)
test_inputs = np.array(test_inputs)


plot_sample(train_inputs, train_outputs, 0)

# train_inputs /= 255.0
# test_inputs /= 255.0

cnn = models.Sequential([
    layers.Conv2D(filters=150, kernel_size=(3, 3), activation='relu', input_shape=(150, 150, 3)),
    layers.MaxPooling2D((2, 2)),

    layers.Conv2D(filters=300, kernel_size=(3, 3), activation='relu'),
    layers.MaxPooling2D((2, 2)),

    layers.Flatten(),
    layers.Dense(300, activation='relu'),
    layers.Dense(150, activation='softmax')
])

cnn.compile(optimizer='adam',
            loss='sparse_categorical_crossentropy',
            metrics=['accuracy'])
cnn.fit(train_inputs, train_outputs, epochs=2)
cnn.evaluate(train_inputs, train_outputs)
