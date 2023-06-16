import random
from random import randint

from utils import generate_new_value


# probl_param = {'network': network_karate,'function': modularity}
# network = {'mat': ..., 'noNodes': ..., 'noEdges': ..., 'degrees': ...}
class Chromosome:
    def __init__(self, probl_param=None):
        self.__probl_param = probl_param
        self.__repres = [generate_new_value(1, probl_param['network']['noNodes']) for _ in
                         range(probl_param['network']['noNodes'])]
        self.__fitness = 0.0

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

    def __str__(self):
        return '\nChromo: ' + str(self.__repres) + ' has fit: ' + str(self.__fitness)

    def __repr__(self):
        return self.__str__()

    def __eq__(self, c):
        return self.__repres == c.__repres and self.__fitness == c.__fitness

    def crossover(self, c):
        # vertex = random.randint(0, self.__probl_param['network']['noNodes'] - 1)
        # label = self.repres[vertex]
        # for i in range(0, self.__probl_param['network']['noNodes']):
        #     if label == self.repres[i]:
        #         destination.repres[i] = label
        # return destination
        r1 = randint(0, len(self.__repres) - 1)
        r2 = randint(0, len(self.__repres) - 1)
        if r1 > r2:
            aux = r1
            r1 = r2
            r2 = aux
        newrepres = []
        for i in range(r1):
            newrepres.append(self.__repres[i])
        for i in range(r1, r2):
            newrepres.append(c.__repres[i])
        for i in range(r2, len(self.__repres)):
            newrepres.append(self.__repres[i])
        offspring = Chromosome(c.__probl_param)
        offspring.repres = newrepres
        return offspring

    def mutation(self):
        '''
        The cluster of vertex1 is set to the cluster of vertex2
        :return:
        '''
        for _ in range(int(self.__probl_param['network']['noNodes'] * 0.5)):
            vertex1 = random.randint(0, self.__probl_param['network']['noNodes'] - 1)
            vertex2 = random.randint(0, self.__probl_param['network']['noNodes'] - 1)
            self.repres[vertex1] = self.repres[vertex2]
