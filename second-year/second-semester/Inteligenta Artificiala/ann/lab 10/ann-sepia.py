from cProfile import label
from xml import dom
import matplotlib.pyplot as plt
import numpy as np
import os
import pprint
import cv2
import turtle
from collections import Counter
from sklearn.model_selection import train_test_split
from sklearn.neural_network import *
pp = pprint.PrettyPrinter(indent=4)

import joblib
from skimage.io import imread
from skimage.transform import resize

data = dict()

def load_all(src, width=150, height=None):
    #incarcam toate imaginile in memorie (le dam resize, memoram filename-ul si labelul(numele folderului in care se afla))
     
    height = height if height is not None else width
     
    data['description'] = 'resized ({0}x{1})images in rgb'.format(int(width), int(height))
    data['label'] = []
    data['filename'] = []
    data['data'] = []   
     
 
    # read all images in PATH, resize 
    for subdir in os.listdir(src):
        print(subdir)
        current_path = os.path.join(src, subdir)
 
        for file in os.listdir(current_path):
            #if file[-3:] in {'jpg', 'png'}:
            if file[-3:] in {'jpg'}:
                im = imread(os.path.join(current_path, file))
                im = resize(im, (width, height))
                data['label'].append(subdir)
                data['filename'].append(file)
                data['data'].append(im)


def transf(images):
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

        #plt.imshow([[(dominant[0], dominant[1], dominant[2])]])
        #plt.show()
    return transformed


def plot_points(colors,labels):
    fig = plt.figure()
    ax = plt.axes(projection='3d')
    sepias = []
    normals = []
    for i in range(len(colors)):
        if labels[i]=="Sepia":
            sepias.append(colors[i])
        else:
            normals.append(colors[i])
    ax.plot3D([sepias[i][0] for i in range(len(sepias))],[sepias[i][1] for i in range(len(sepias))],[sepias[i][2] for i in range(len(sepias))],"go",label="Sepia")
    ax.plot3D([normals[i][0] for i in range(len(normals))],[normals[i][1] for i in range(len(normals))],[normals[i][2] for i in range(len(normals))],"bo",label="Normal")
    plt.legend()
    plt.show()
 
crtDir = os.getcwd()
dataPath = os.path.join(crtDir,"Pictures")


load_all(dataPath,100)


print('number of samples: ', len(data['data']))
print('keys: ', list(data.keys()))
print('description: ', data['description'])
print('image shape: ', data['data'][0].shape)
print('labels:', np.unique(data['label']))
 
print(Counter(data['label']))


X = np.array(data['data'], dtype=object)
y = np.array(data['label'])

#impartim datele in train si test dupa shuffle (80% / 20%)
X_train, X_test, y_train, y_test = train_test_split(X, y, test_size=0.2, shuffle=True, random_state=4242)

X_train_transformed = transf(X_train)
X_test_transformed = transf(X_test)

plot_points(X_train_transformed,y_train)

#trebuie sa transformam imaginile
#pentru asta am ales ca "in loc" de imagine sa iau culoarea dominanta

classifier = MLPClassifier(max_iter=1000)

classifier.fit(X_train_transformed,y_train)

y_pred = classifier.predict(X_test_transformed)
print(np.array(y_pred == y_test))
print('')
print('Percentage correct: ', 100*np.sum(y_pred == y_test)/len(y_test))
