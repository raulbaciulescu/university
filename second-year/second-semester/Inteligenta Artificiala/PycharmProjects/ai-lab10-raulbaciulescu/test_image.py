# from keras.api._v2.keras import models, datasets
# from keras.applications.densenet import layers
#
# import os
# import cv2
#
# from skimage.io import imread
# from skimage.transform import resize
# import keras.datasets
# # from tensorflow.python.keras import datasets
# # from tensorflow.keras import datasets, layers, models
# import matplotlib.pyplot as plt
# import numpy as np
#
#
# def plot_sample(X, y, index):
#     plt.figure(figsize = (15,2))
#     plt.imshow(X[index])
#     plt.xlabel(classes[y[index]])
#     plt.show()
#
#
# (X_train, y_train), (X_test,y_test) = datasets.cifar10.load_data()
# y_train = y_train.reshape(-1,)
# y_test = y_test.reshape(-1,)
# classes = ["airplane","automobile","bird","cat","deer","dog","frog","horse","ship","truck"]
# plot_sample(X_train, y_train, 0)
# plot_sample(X_train, y_train, 1)
#
# X_train = X_train / 255.0
# X_test = X_test / 255.0
#
# cnn = models.Sequential([
#     layers.Conv2D(filters=32, kernel_size=(3, 3), activation='relu', input_shape=(32, 32, 3)),
#     layers.MaxPooling2D((2, 2)),
#
#     layers.Conv2D(filters=64, kernel_size=(3, 3), activation='relu'),
#     layers.MaxPooling2D((2, 2)),
#
#     layers.Flatten(),
#     layers.Dense(64, activation='relu'),
#     layers.Dense(10, activation='softmax')
# ])
#
# cnn.compile(optimizer='adam',
#               loss='sparse_categorical_crossentropy',
#               metrics=['accuracy'])
#
# cnn.fit(X_train, y_train, epochs=5)
# cnn.evaluate(X_test,y_test)
# y_pred = cnn.predict(X_test)
# y_classes = [np.argmax(element) for element in y_pred]
# plot_sample(X_test, y_test,3)
#
