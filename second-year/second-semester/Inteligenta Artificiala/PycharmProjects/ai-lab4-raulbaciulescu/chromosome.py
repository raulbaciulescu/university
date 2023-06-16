import random


class Chromosome:
    def __init__(self, network=None, source=None, destination=None, mutation_param=0.2):
        self.__network = network
        self.__repres = random.sample(range(1, len(network)), len(network) - 1)
        self.__source = source
        self.__flag = False
        self.__destination = destination
        self.__fitness = self.compute_fitness()
        self.__mutation_param = mutation_param

    @property
    def repres(self):
        return self.__repres

    @property
    def fitness(self):
        return self.__fitness

    @repres.setter
    def repres(self, l=None):
        self.__repres = l

    @fitness.setter
    def fitness(self, fit=0.0):
        self.__fitness = fit

    def compute_fitness(self):
        fitness1 = 0
        fitness2 = self.__network[0][self.__repres[0]] + self.__network[0][self.__repres[len(self.__repres) - 1]]
        nr = 0
        if self.__source is not None:
            for i in range(0, len(self.__repres) - 1):
                if self.__repres[i] == self.__source - 1 or self.__repres[i] == self.__destination - 1:
                    nr += 1
                if nr == 1:
                    fitness1 += self.__network[self.__repres[i + 1]][self.__repres[i]]
                if nr != 1:
                    fitness2 += self.__network[self.__repres[i + 1]][self.__repres[i]]
            if fitness1 < fitness2:
                self.__flag = True
                return fitness1
            else:
                return fitness2


        fitness = self.__network[0][self.__repres[0]] + self.__network[0][self.__repres[len(self.__repres) - 1]]
        for i in range(1, len(self.__repres)):
            fitness = fitness + self.__network[self.__repres[i - 1]][self.__repres[i]]
        return fitness

    def __str__(self):
        if self.__source is None:
            return '\nChromo: ' + str(self.__repres) + ' has fit: ' + str(self.__fitness)
        return '\nChromo: ' + str(self.repres_with_source_and_dest()) + ' has fit: ' + str(self.__fitness)

    def __repr__(self):
        return self.__str__()

    def __eq__(self, c):
        return self.__repres == c.__repres and self.__fitness == c.__fitness

    def crossover(self, c):
        '''

        :param c:
        :return:
        '''
        pos = random.randint(0, len(self.__repres) - 1)
        repres = []
        for i in range(0, len(self.__repres)):
            if i <= pos:
                repres.append(self.__repres[i])
            else:
                repres.append(c.__repres[i])
        off = Chromosome(self.__network, source=self.__source, destination=self.__destination)
        off.repres = repres
        if len(set(repres)) == len(repres):
            return off
        return self


    def mutation(self):
        pos = random.random()
        if pos < self.__mutation_param:
            pos1 = random.randint(0, len(self.__repres) - 1)
            pos2 = random.randint(0, len(self.__repres) - 1)
            self.__repres[pos1], self.repres[pos2] = self.repres[pos2], self.__repres[pos1]

    def repres_with_source_and_dest(self):
        repres = []
        repres2 = [0]
        nr = 0
        for i in range(0, len(self.__repres)):
            if nr != 1:
                repres2.insert(0, self.__repres[i])
            if self.__repres[i] == self.__source - 1 or self.__repres[i] == self.__destination - 1:
                nr += 1
            if nr == 1:
                repres.append(self.__repres[i])
            if nr != 1:
                repres2.append(self.__repres[i])

        if self.__flag is True:
            return repres
        else:
            return repres2


