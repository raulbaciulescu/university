import os
from cmath import sqrt
import matplotlib.pyplot as plt


def parser(filename):
    f = open(filename, "r")
    net = {}
    coords = []
    _ = f.readline()
    _ = f.readline()
    _ = f.readline()
    x = f.readline()

    if filename == "Data/hardE.txt":
        _, _, dim = x.split()
    else:
        _, dim = x.split()
    net['noNodes'] = int(dim)

    x = f.readline()
    x = f.readline()
    x = f.readline()

    while x != "EOF\n":
        _, coordx, coordy = x.split()
        coords.append([float(coordx), float(coordy)])
        x = f.readline()

    mat = []
    for i in range(net['noNodes']):
        mat.append([])
        for j in range(net['noNodes']):
            if i == j:
                mat[i].append(0)
                continue
            x1 = coords[i][0]
            x2 = coords[j][0]
            y1 = coords[i][1]
            y2 = coords[j][1]
            if filename == "Data/hardE.txt":
                mat[i].append(int(sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2)).real))
            else:
                mat[i].append((sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2)).real))

    net['mat'] = mat
    f.close()
    return net

def main():
    from aco import ACO

    params = parser("C:\\Users\\raulb\PycharmProjects\\ai-lab5-raulbaciulescu\\tsp\\berlin52.tsp")

    probParams = {"noAnts": 30}
    params['pheromone'] = []
    params['oldPheromoneRate'] = 0.009
    params['q0'] = 0.01
    params['alpha'] = 5
    params['beta'] = 3

    aco = ACO(params, probParams)
    aco.initialize()
    aco.initialize_pheromone()
    x_axis = []
    y_axis = []
    best_of_best_ant = aco.best_ant()
    delete_rate = 10
    for i in range(1, 1000):
        x_axis.append(i)
        aco.min_max()
        best_ant = aco.best_ant()
        if best_ant.weight() < best_of_best_ant.weight():
            best_of_best_ant = best_ant

        # if i % delete_rate == 0:
        #     aco.delete_edge()
        y_axis.append(best_of_best_ant.weight())
        aco.initialize()
    plt.plot(x_axis, y_axis, 'ro')
    plt.show()
    print("Cea mai inteligenta furnicuta: " + str(best_of_best_ant.weight()) + " with path " + str(best_of_best_ant.path()))



if __name__ == '__main__':
    main()

