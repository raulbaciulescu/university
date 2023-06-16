from copy import deepcopy

from Domain.entitate import Entitate
from Domain.masina import Masina
import jsonpickle # poate fi folosit si pickle

class FileRepository:

    def __init__(self, filename):
        '''
        TODO
        '''
        self.__storage = {}
        self.__filename = filename

    def __read_file(self):
        try:
            with open(self.__filename, 'r') as f:
                self.__storage = jsonpickle.decode(f.read())
        except:
            self.__storage = {}

    def __write_file(self):
        with open(self.__filename, 'w') as f:
            f.write(jsonpickle.encode(self.__storage))

    def find_by_id(self, id_entitate):
        '''
        TODO
        :param id_entitate:
        :return:
        '''
        self.__read_file()
        if str(id_entitate) in self.__storage:
            return self.__storage[str(id_entitate)]
        return None

    def create(self, entitate: Entitate): # adaugare
        '''
        TODO
        :param entitate:
        :return:
        '''
        if self.find_by_id(entitate.id_entitate) is not None:
            raise KeyError(f'Entitatea cu id-ul {entitate.id_entitate} exista deja!')

        self.__storage[entitate.id_entitate] = entitate
        self.__write_file()

    def update(self, entitate: Entitate):
        '''
        TODO
        :param entitate:
        :return:
        '''
        if self.find_by_id(entitate.id_entitate) is None:
            raise KeyError(f'Nu exista o entitate cu id-ul {entitate.id_entitate} pe care sa o actualizam!')

        self.__storage[entitate.id_entitate] = entitate
        self.__write_file()

    def delete(self, id_entitate):
        '''
        TODO
        :param id_entitate:
        :return:
        '''
        if self.find_by_id(id_entitate) is None:
            raise KeyError(f'Nu exista o entitate cu id-ul {id_entitate} pe care sa o stergem!')

        del self.__storage[id_entitate]
        self.__write_file()

    def get_all(self):
        '''
        TODO
        :return:
        '''
        self.__read_file()
        return list(self.__storage.values())
