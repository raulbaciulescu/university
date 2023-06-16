import random

from chromosome import Chromosome
from utils import generate_new_value

class GeneticAlgorithm:
    def __init__(self, param=None, probl_param=None):
        self.__param = param
        self.__probl_param = probl_param
        self.__population = []

    @property
    def population(self):
        return self.__population

    def initialisation(self):
        '''
        For chromosome c, we
        randomly pick a vertex random_vertex and assign its cluster to all of its neighbors
        :return:
        '''
        for _ in range(0, self.__param['popSize']):
            c = Chromosome(self.__probl_param)
            self.__population.append(c)

        for _ in range(int(self.__probl_param['network']['noNodes'] * 0.4)):
            for i in range(0, self.__param['popSize']):
                random_vertex = generate_new_value(0, self.__probl_param['network']['noNodes'] - 1)
                cluster = self.__population[i].repres[random_vertex]
                mat = self.__probl_param['network']['mat']
                for j in range(0, self.__probl_param['network']['noNodes']):
                    if int(mat[random_vertex, j]) == 1:
                        self.__population[i].repres[random_vertex] = cluster

    def evaluation(self):
        for c in self.__population:
            c.fitness = self.__probl_param['function'](c.repres, self.__probl_param['network'])

    def best_chromosome(self):
        best = self.__population[0]
        for c in self.__population:
            if c.fitness > best.fitness:
                best = c
        return best

    def worst_chromosome(self):
        best = self.__population[0]
        for c in self.__population:
            if c.fitness < best.fitness:
                best = c
        return best


    def selection_tournir(self, k):
        best = self.__population[0]
        k_random = []
        for i in range(0, k):
            c = random.randint(0, self.__param['popSize'] - 1)
            k_random.append(self.__population[c])
        for c in k_random:
            if c.fitness > best.fitness:
                best = c
        return best

    def selection(self):
        pos1 = random.randint(0, self.__param['popSize'] - 1)
        pos2 = random.randint(0, self.__param['popSize'] - 1)
        if self.__population[pos1].fitness > self.__population[pos2].fitness:
            return pos1
        else:
            return pos2

    def oneGeneration(self):
        newPop = []
        for _ in range(self.__param['popSize']):
            c1 = self.__population[self.selection2(20)]
            c2 = self.__population[self.selection2(20)]
            off = c1.crossover(c2)
            off.mutation()
            newPop.append(off)
        self.__population = newPop
        self.evaluation()

    def loop(self):
        self.evaluation()
        sorted_population = sorted(self.__population, key=lambda x: x.fitness)
        #save for later first p * beta
        save = []
        for i in range(int(0.1 * self.__param['popSize'])):
            save.append(sorted_population[i])

        # crossover for pairs and mutation
        newPop = [self.__population[0]]
        for i in range(1, self.__param['popSize']):
            c1 = self.__population[i - 1]
            c2 = self.__population[i]
            off = c1.crossover(c2)
            off.mutation()
            newPop.append(off)

        #combine sorted with newPop
        for i in range(0, self.__param['popSize']):
            if i <= int(0.1 * self.__param['popSize']):
                self.__population[i] = sorted_population[i]
            else:
                self.__population[i] = newPop[i]
        self.evaluation()

    def oneGenerationElitism(self):
        newPop = [self.best_chromosome()]
        for _ in range(self.__param['popSize'] - 1):
            p1 = self.__population[self.selection()]
            p2 = self.__population[self.selection()]
            off = p1.crossover(p2)
            off.mutation()
            newPop.append(off)
        self.__population = newPop
        self.evaluation()


