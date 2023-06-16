
import math
import numpy as np
import networkx as nx
from chromosome import Chromosome
import os
import networkx as nx
import matplotlib.pyplot as plt
import warnings

from genetic_algorithm import GeneticAlgorithm

warnings.simplefilter('ignore')




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

def plotNetwork(network, communities=None):
    '''
    plot a network
    :param network:
    :param communities:
    :return:
    '''
    if communities is None:
        communities = [1] * network['noNodes']
    np.random.seed(123)
    A = np.matrix(network["mat"])
    G = nx.from_numpy_matrix(A)
    np.random.seed(123)
    pos = nx.spring_layout(G)
    plt.figure(figsize=(10, 10))
    nx.draw_networkx_nodes(G, pos, node_size=80, cmap=plt.cm.RdYlBu, node_color=communities)
    nx.draw_networkx_edges(G, pos, alpha=0.3)
    plt.show()


def modularity(communities, param):
    noNodes = param['noNodes']
    mat = param['mat']
    degrees = param['degrees']
    noEdges = param['noEdges']
    M = 2 * noEdges
    Q = 0.0
    for i in range(0, noNodes):
        for j in range(0, noNodes):
            if communities[i] == communities[j]:
                Q += (mat.item(i, j) - degrees[i] * degrees[j] / M)
    return Q * 1 / M

def main():
    crt_dir = os.getcwd()
    network_football = read_from_gml(os.path.join(crt_dir, 'real/football/', 'football.gml'))
    network_dolphins = read_from_gml(os.path.join(crt_dir, 'real/dolphins/', 'dolphins.gml'))
    network_karate = read_from_gml(os.path.join(crt_dir, 'real/ex/', 'ex1.gml'))
    network_krebs = read_from_gml(os.path.join(crt_dir, 'real/krebs/', 'krebs.gml'))

    param = {'popSize': 200, 'noGen': 350}
    probl_param = {'network': network_karate, 'function': modularity}

    ga = GeneticAlgorithm(param, probl_param)
    ga.initialisation()
    ga.evaluation()

    all_best_fitness = -9999999
    all_best_repres = 0
    for g in range(param['noGen']):
        best_sol_repres = ga.best_chromosome().repres
        best_sol_fitness = ga.best_chromosome().fitness
        # plotNetwork(network_football, best_sol_repres)
        print(best_sol_fitness)


        #ga.oneGeneration()
        ga.oneGenerationElitism()
        #ga.oneGenerationSteadyState()
        #ga.loop()

        if best_sol_fitness > all_best_fitness:
            all_best_fitness = best_sol_fitness
            all_best_repres = best_sol_repres

    plotNetwork(network_karate, all_best_repres)

if __name__ == '__main__':
    main()

