import jsonpickle
from copy import deepcopy


class MasinaFileRepository:

    def __init__(self, filename):
        self.__storage = {}
        self.__filename = filename

    def __write_file(self):
        with open(self.__filename, 'w') as f:
            f.write(jsonpickle.encode(self.__storage))

    def __load_file(self):
        try:
            with open(self.__filename, 'r') as f:
                self.__storage = jsonpickle.decode(f.read())
        except:
            self.__storage = {}

    def find_by_id(self, id_masina):
        self.__load_file()
        if id_masina in self.__storage:
            return deepcopy(self.__storage[id_masina])
        return None

    def create(self, masina):
        '''
        TODO
        :param masina:
        :return:
        '''

        if self.find_by_id(masina.id_masina) is not None:
            raise KeyError(f'Exista deja o masina cu id-ul {masina.id_masina}!')
        self.__storage[masina.id_masina] = masina
        self.__write_file()

    def update(self, masina):
        '''
        TODO
        :param masina:
        :return:
        '''
        if self.find_by_id(masina.id_masina) is None:
            raise KeyError(f'Nu exista nicio masina cu id-ul {masina.id_masina}')
        self.__storage[masina.id_masina] = masina
        self.__write_file()

    def delete(self, id_masina):
        if self.find_by_id(id_masina) is None:
            raise KeyError(f'Nu exista nicio masina cu id-ul {id_masina}')
        del self.__storage[id_masina]
        self.__write_file()

    def get_all(self):
        self.__load_file()
        return list(self.__storage.values())