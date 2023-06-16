import os
from cmath import sqrt

from ga import Ga
import matplotlib.pyplot as plt

def read_from_tsp(filename):
    # Open input file
    infile = open(filename, 'r')

    # Read instance header
    Name = infile.readline().strip().split()[1]  # NAME
    FileType = infile.readline().strip().split()[1]  # TYPE
    Comment = infile.readline().strip().split()[1]  # COMMENT
    Dimension = infile.readline().strip().split()[1]  # DIMENSION
    EdgeWeightType = infile.readline().strip().split()[1]  # EDGE_WEIGHT_TYPE
    infile.readline()

    # Read node list
    nodelist = []
    N = int(Dimension)
    for i in range(0, int(Dimension)):
        x, y = infile.readline().strip().split()[1:]
        nodelist.append([float(x), float(y)])

    # Close input file
    infile.close()
    return nodelist


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
            mat[i].append(int(sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2)).real))

    net['noNodes'] = net['noNodes'] - 1
    net['mat'] = mat
    f.close()
    return mat

def read(filename):
    network = []
    f = open(filename, "r")
    n = int(f.readline())
    for i in range(n):
        line = f.readline()
        network.append([int(x) for x in line.split(',')])
    source = int(f.readline())
    destination = int(f.readline())
    return network, source, destination

def main():
    crt_dir = os.getcwd()
    network_berlin = parser(os.path.join(crt_dir, 'tsp/', 'berlin52.tsp'))
    ga = Ga(network_berlin)
    ga.initialisation()
    generations = 1000
    all_best_fitness = 9999999
    all_best_repres = 0
    x_axis = []
    y_axis = []
    for i in range(generations):
        x_axis.append(i)
        best_sol_repres = ga.best_chromosome().repres
        best_sol_fitness = ga.best_chromosome().fitness
        ga.oneGenerationElitism()
        if best_sol_fitness < all_best_fitness:
            all_best_fitness = best_sol_fitness
            all_best_repres = best_sol_repres
        y_axis.append(all_best_fitness)

    print('\n\n')
    print(all_best_fitness)
    print(all_best_repres)
    plt.plot(x_axis, y_axis, 'ro')
    plt.show()

if __name__ == '__main__':
    main()
