import numpy as np
import matplotlib.pyplot as plt
from mpl_toolkits.mplot3d import Axes3D

from dense import Dense
from utils import Tanh


def one_hot(y, digits=True):
    # one_hot_y = np.zeros((y.size, y.max(initial=0) + 1))
    # one_hot_y[np.arange(y.size), y] = 1
    # one_hot_y = one_hot_y.T
    # print(one_hot_y)
    # return one_hot_y

    # if y[0][0] == 0:
    #     return np.array([[1], [0], [0]])
    # if y[0][0] == 1:
    #     return np.array([[0], [1], [0]])
    # if y[0][0] == 2:
    #     return np.array([[0], [0], [1]])
    # return np.array([[0], [0], [1]])
    if y[0][0] == 0:
        return np.array([[1], [0], [0], [0], [0], [0], [0], [0], [0], [0]])
    if y[0][0] == 1:
        return np.array([[0], [1], [0], [0], [0], [0], [0], [0], [0], [0]])
    if y[0][0] == 2:
        return np.array([[0], [0], [1], [0], [0], [0], [0], [0], [0], [0]])
    if y[0][0] == 3:
        return np.array([[0], [0], [0], [1], [0], [0], [0], [0], [0], [0]])
    if y[0][0] == 4:
        return np.array([[0], [0], [0], [0], [1], [0], [0], [0], [0], [0]])
    if y[0][0] == 5:
        return np.array([[0], [0], [0], [0], [0], [1], [0], [0], [0], [0]])
    if y[0][0] == 6:
        return np.array([[0], [0], [0], [0], [0], [0], [1], [0], [0], [0]])
    if y[0][0] == 7:
        return np.array([[0], [0], [0], [0], [0], [0], [0], [1], [0], [0]])
    if y[0][0] == 8:
        return np.array([[0], [0], [0], [0], [0], [0], [0], [0], [1], [0]])
    if y[0][0] == 9:
        return np.array([[0], [0], [0], [0], [0], [0], [0], [0], [0], [1]])


def mse(y_true, y_pred, flag=False):
    if flag:
        return np.mean(np.power(y_true - y_pred, 2))
    else:
        return np.mean(np.power(one_hot(y_true) - y_pred, 2))


def mse_prime(y_true, y_pred, flag=False):
    if flag:
        return 2 * (y_pred - y_true) / np.size(y_true)
    else:
        return 2 * (y_pred - one_hot(y_true)) / np.size(y_true)


X = np.reshape([[0, 0], [0, 1], [1, 0], [1, 1]], (4, 2, 1))
Y = np.reshape([[0], [1], [1], [0]], (4, 1, 1))

# network = [
#     Dense(2, 3),
#     Tanh(),
#     Dense(3, 1),
#     Tanh()
# ]
#
# # train
# train(network, mse, mse_prime, X, Y, epochs=5000, learning_rate=0.01)
#
# # decision boundary plot
# points = []
# for x in np.linspace(0, 1, 20):
#     for y in np.linspace(0, 1, 20):
#         z = predict(network, [[x], [y]])
#         points.append([x, y, z[0,0]])
#
# points = np.array(points)
#
# fig = plt.figure()
# ax = fig.add_subplot(111, projection="3d")
# ax.scatter(points[:, 0], points[:, 1], points[:, 2], c=points[:, 2], cmap="winter")
# plt.show()
