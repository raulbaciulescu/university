from copy import deepcopy


class MasinaRepository:

    def __init__(self):
        self.__storage = {}

    def find_by_id(self, id_masina):
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

    def update(self, masina):
        '''
        TODO
        :param masina:
        :return:
        '''
        if self.find_by_id(masina.id_masina) is None:
            raise KeyError(f'Nu exista nicio masina cu id-ul {masina.id_masina}')
        self.__storage[masina.id_masina] = masina

    def delete(self, id_masina):
        if self.find_by_id(id_masina) is None:
            raise KeyError(f'Nu exista nicio masina cu id-ul {id_masina}')
        del self.__storage[id_masina]

    def get_all(self):
        return deepcopy(list(self.__storage.values()))