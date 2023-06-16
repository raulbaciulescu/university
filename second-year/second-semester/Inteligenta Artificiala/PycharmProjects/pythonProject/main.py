
# define the function
import math
import os

import numpy as np
import networkx as nx

from genetic_algorithm import GA

MIN = -5
MAX = 5


def read_from_gml(file_name):
    '''
    read a graph from gml file
    :param file_name:
    :return: network = {'mat': ..., 'noNodes': ..., 'noEdges': ..., 'degrees': ...}
    '''
    if 'karate' in file_name:
        graph = nx.read_gml(file_name, label='id')
    else:
        graph = nx.read_gml(file_name)
    network = {"noNodes": graph.number_of_nodes(), "noEdges": graph.number_of_edges()}
    matrix = nx.to_numpy_matrix(graph)
    network["mat"] = matrix
    degrees = []
    noEdges = 0

    for i in range(network['noNodes']):
        d = 0
        for j in range(network['noNodes']):
            if matrix.item(i, j) == 1:
                d += 1
            if j > i:
                noEdges += matrix.item(i, j)
        degrees.append(d)

    network["degrees"] = degrees
    return network


def modularity(communities, param):
    noNodes = param['noNodes']
    mat = param['mat']
    degrees = param['degrees']
    noEdges = param['noEdges']
    M = 2 * noEdges
    Q = 0.0
    for i in range(0, noNodes):
        for j in range(0, noNodes):
            if (communities[i] == communities[j]):
               Q += (mat[i][j] - degrees[i] * degrees[j] / M)
    return Q * 1 / M


def fcEval(x):
    # sphere function
    # val = sum(xi ** 2 for xi in x)

    # Rastrigin function
    term1 = sum(xi ** 2 / 4000 for xi in x)
    cosinus = np.cos([xi for xi in x])
    cosinus = [cosinus[i] / math.sqrt(i + 1) for i in range(len(x))]
    term2 = np.prod([c for c in cosinus], axis = 0)
    val = term1 - term2 + 1
    val = 20 + sum(xi ** 2 - 10 * np.cos(2 * np.pi * xi) for xi in x)

    return val

# define a generator of values
from fcOptimisation.utils import generateNewValue

#  plot the 1D function (n = 1) (see how the search space looks)
import matplotlib.pyplot as plt

def plotAFunction(xref, yref, x, y, xoptimal, yoptimal, message):
    plt.plot(xref, yref, 'b-')
    plt.plot(x, y, 'ro', xoptimal, yoptimal, 'bo')
    plt.title(message)
    plt.show()
    plt.pause(0.9)
    plt.clf()

def girvan(network, nr_of_comunities):
    '''
    divide nodes of the graph in nr_of_comunities comunities using girvan algorithm
    :param network:
    :param nr_of_comunities:
    :return:
    '''
    matrix = np.matrix(network["mat"])
    g = nx.from_numpy_matrix(matrix)

    lena = nx.number_connected_components(g)
    while (lena < nr_of_comunities):
        xy = edge_to_remove(g)
        g.remove_edge(xy[0], xy[1])

        lena = nx.number_connected_components(g)

    components = tuple(nx.connected_components(g))
    communities = [0] * network['noNodes']
    color = 1
    for community in components:
        for node in community:
            communities[node] = color
        color += 1
    return communities


def edge_to_remove(g):
    '''
    find the most betweenness edge
    :param g:
    :return:
    '''
    d1 = nx.edge_betweenness_centrality(g)
    list_of_tuples = list(d1.items())
    list_of_tuples = sorted(list_of_tuples, key=lambda x: x[1], reverse=True)
    return list_of_tuples[0][0]


def main():
    # noDim = 1
    # xref = [[generateNewValue(MIN, MAX) for _ in range(noDim)] for _ in range(0, 1000)]
    # xref.sort()
    # yref = [fcEval(xi) for xi in xref]
    #
    # plt.plot(xref, yref, 'b-')
    # plt.xlabel('x values')
    # plt.ylabel('y = f(x) values')
    # plt.show()
    # plot a 2D function

    # from mpl_toolkits import mplot3d
    # import matplotlib.pyplot as plt
    #
    # MIN = -5
    # MAX = 5
    # noDim = 2
    #
    # x = [np.linspace(MIN, MAX, 10) for _ in range(noDim)]
    # x1, x2 = np.meshgrid(x[0], x[1])
    # y = fcEval([x1, x2])
    # fig = plt.figure(figsize=(8, 6))
    # ax = plt.axes(projection='3d')
    #
    # ax.plot_surface(x1, x2, y, rstride=1, cstride=1, cmap='Greens', linewidth=0.08, antialiased=True)
    #
    # plt.show()

    # from fcOptimisGA.RealChromosome import Chromosome
    # from random import seed
    #
    # seed(1)
    #
    # # plot the function to be optimised
    # noDim = 1
    # xref = [[generateNewValue(MIN, MAX) for _ in range(noDim)] for _ in range(0, 1000)]
    # xref.sort()
    # yref = [fcEval(xi) for xi in xref]
    # plt.ion()
    # plt.plot(xref, yref, 'b-')
    # plt.xlabel('x values')
    # plt.ylabel('y = f(x) values')
    # plt.show()
    #
    # # initialise de GA parameters
    # gaParam = {'popSize': 10, 'noGen': 3, 'pc': 0.8, 'pm': 0.1}
    # # problem parameters
    # problParam = {'min': MIN, 'max': MAX, 'function': fcEval, 'noDim': noDim, 'noBits': 8}
    #
    # # store the best/average solution of each iteration (for a final plot used to anlyse the GA's convergence)
    # allBestFitnesses = []
    # allAvgFitnesses = []
    # generations = []
    #
    # ga = GA(gaParam, problParam)
    # ga.initialisation()
    # ga.evaluation()
    #
    for g in range(gaParam['noGen']):
        # plotting preparation
        allPotentialSolutionsX = [c.repres for c in ga.population]
        allPotentialSolutionsY = [c.fitness for c in ga.population]
        bestSolX = ga.bestChromosome().repres
        bestSolY = ga.bestChromosome().fitness
        allBestFitnesses.append(bestSolY)
        allAvgFitnesses.append(sum(allPotentialSolutionsY) / len(allPotentialSolutionsY))
        generations.append(g)
        plotAFunction(xref, yref, allPotentialSolutionsX, allPotentialSolutionsY, bestSolX, [bestSolY],
                      'generation: ' + str(g))

        # logic alg
        # ga.oneGeneration()
        # ga.oneGenerationElitism()
        # ga.oneGenerationSteadyState()
        #
        # bestChromo = ga.bestChromosome()
        # print('Best solution in generation ' + str(g) + ' is: x = ' + str(bestChromo.repres) + ' f(x) = ' + str(
        #     bestChromo.fitness))

    # plt.ioff()
    # best, = plt.plot(generations, allBestFitnesses, 'ro', label='best')
    # mean, = plt.plot(generations, allAvgFitnesses, 'bo', label='mean')
    # plt.legend([best, (best, mean)], ['Best', 'Mean'])
    # plt.show()


    crt_dir = os.getcwd()
    network_football = read_from_gml(os.path.join(crt_dir, 'real/football/', 'football.gml'))
    network_dolphins = read_from_gml(os.path.join(crt_dir, 'real/dolphins/', 'dolphins.gml'))
    network_karate = read_from_gml(os.path.join(crt_dir, 'real/karate/', 'karate.gml'))
    print(modularity(girvan(network_karate, 2), network_karate))

if __name__ == '__main__':
    main()

