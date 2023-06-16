import random

from chromosome import Chromosome


class Ga:
    def __init__(self, network=None, popSize=100, source=None, destination=None):
        self.__network = network
        self.__popSize = popSize
        self.__population = []
        self.__source = source
        self.__destination = destination

    @property
    def population(self):
        return self.__population

    def initialisation(self):
        for _ in range(0, self.__popSize):
            c = Chromosome(self.__network, source=self.__source, destination=self.__destination)
            self.__population.append(c)

    def evaluation(self):
        for c in self.__population:
            c.fitness = c.compute_fitness()


    def selection(self):
        pos1 = random.randint(0, self.__popSize - 1)
        pos2 = random.randint(0, self.__popSize - 1)
        if self.__population[pos1].fitness < self.__population[pos2].fitness:
            return pos1
        else:
            return pos2

    def best_chromosome(self):
        best = self.__population[0]
        for c in self.__population:
            if c.fitness < best.fitness:
                best = c
        return best

    def worst_chromosome(self):
        worst = self.__population[0]
        for c in self.__population:
            if c.fitness > worst.fitness:
                worst = c
        return worst


    def oneGenerationElitism(self):
        newPop = [self.best_chromosome()]
        for _ in range(self.__popSize - 1):
            p1 = self.__population[self.selection()]
            p2 = self.__population[self.selection()]
            off = p1.crossover(p2)
            off.mutation()
            newPop.append(off)
        self.__population = newPop
        self.evaluation()

    def oneGeneration(self):
        newPop = []
        for _ in range(self.__popSize):
            c1 = self.__population[self.selection()]
            c2 = self.__population[self.selection()]
            off = c1.crossover(c2)
            off.mutation()
            newPop.append(off)
        self.__population = newPop
        self.evaluation()