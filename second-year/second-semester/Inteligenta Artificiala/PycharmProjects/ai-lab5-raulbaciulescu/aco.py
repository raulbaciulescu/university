
from random import randint

from ant import Ant


class ACO:
    def __init__(self, params, prob_params):
        self.__params = params
        self.__probParams = prob_params
        self.__ants = []

    def initialize(self):
        self.__ants = []
        for _ in range(self.__probParams["noAnts"]):
            self.__ants.append(Ant(self.__params))

    def initialize_pheromone(self):
        for i in range(self.__params['noNodes']):
            v = [1] * self.__params['noNodes']
            self.__params['pheromone'].append(v)

    def best_ant(self):
        best_ant = self.__ants[0]
        for ant in self.__ants:
            if ant.weight() < best_ant.weight():
                best_ant = ant
        return best_ant

    def place_pheromone(self, ant):
        path = ant.path()
        for i in range(len(path) - 1):
            x = path[i]
            y = path[i + 1]
            self.__params['pheromone'][x][y] = self.__params['oldPheromoneRate'] * self.__params['pheromone'][x][y] + self.__params['oldPheromoneRate'] * (1 / ant.weight())
            self.__params['pheromone'][y][x] = self.__params['pheromone'][x][y]

    def min_max(self):
        for _ in range(self.__params['noNodes'] - 1):
            for ant in self.__ants:
                ant.append_node()
        self.place_pheromone(self.best_ant())

    def delete_edge(self):
        x = randint(0, self.__params['noNodes'] - 1)
        y = randint(0, self.__params['noNodes'] - 1)

        while x == y and self.__params['mat'][x][y] == 0:
            y = randint(0, self.__params['noNodes'] - 1)

        print("Muchia " + str(x) + " - " + str(y) + " stearsa.")

        self.__params['mat'][x][y] = 0
        self.__params['mat'][y][x] = 0

        self.__params['pheromone'][x][y] = 0
        self.__params['pheromone'][y][x] = 0