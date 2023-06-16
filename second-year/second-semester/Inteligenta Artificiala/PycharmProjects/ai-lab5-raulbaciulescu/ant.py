from random import randint, uniform


class Ant:

    def __init__(self, params):
        self.__params = params
        self.__path = [randint(0, self.__params['noNodes'] - 1)]
        self.__visited = [False] * params['noNodes']
        self.__visited[self.__path[0]] = 1
        self.__inexistent_path = False

    def weight(self):
        if self.__inexistent_path:
            return 99999999
        dist = 0
        for i in range(len(self.__path) - 1):
            dist += self.__params['mat'][self.__path[i]][self.__path[i + 1]]
        dist += self.__params['mat'][self.__path[len(self.__path) - 1]][self.__path[0]]
        return dist

    def next(self):

        q = uniform(0, 1)
        current_node = self.__path[len(self.__path) - 1]

        if q < self.__params["q0"]:
            sum = 0
            possible_nodes = []
            for i in range(0, self.__params['noNodes']):
                if self.__visited[i] == 0 and i != current_node and self.__params['mat'][current_node][i]:
                    sum += pow(self.__params['pheromone'][current_node][i], self.__params["alpha"]) * pow(
                        1 / self.__params['mat'][current_node][i], self.__params["beta"])

            for i in range(0, self.__params['noNodes']):
                if self.__visited[i] == 0 and i != current_node and self.__params['mat'][current_node][i]:
                    j = pow(self.__params['pheromone'][current_node][i], self.__params["alpha"]) * pow(
                        1 / self.__params['mat'][current_node][i], self.__params["beta"])
                    possible_nodes.append((j / sum, i))

            possible_nodes.sort()

            prob = uniform(0, 1)

            for i in range(len(possible_nodes) - 1):
                if possible_nodes[i][0] <= prob < possible_nodes[i + 1][0]:
                    return possible_nodes[i][1]

            if len(possible_nodes) == 0:
                raise Exception("")
            return possible_nodes[len(possible_nodes) - 1][1]

        else:
            possible_nodes = []
            for i in range(len(self.__params['pheromone'][current_node])):
                if i != current_node and self.__visited[i] == 0 and self.__params['mat'][current_node][i]:
                    j = pow(self.__params['pheromone'][current_node][i], self.__params["alpha"]) * pow(
                        1 / self.__params['mat'][current_node][i], self.__params["beta"])
                    possible_nodes.append((j, i))

            possible_nodes.sort()

            if len(possible_nodes) == 0:
                raise Exception("")
            return possible_nodes[0][1]

    def append_node(self):
        try:
            next_node = self.next()
        except:
            self.__inexistent_path = True
            return

        current_node = self.__path[len(self.__path) - 1]
        self.__visited[next_node] = True
        self.__path.append(next_node)

        self.__params['pheromone'][current_node][next_node] = (1 - self.__params["oldPheromoneRate"]) * \
                                                              self.__params["pheromone"][current_node][next_node] + \
                                                              self.__params["oldPheromoneRate"] * \
                                                              self.__params['mat'][current_node][next_node]
        self.__params['pheromone'][next_node][current_node] = self.__params['pheromone'][current_node][next_node]

    def path(self):
        return self.__path
