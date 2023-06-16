from copy import deepcopy

import jsonpickle as jsonpickle

from Domain.entity import Entity

class FileRepository:

    def __init__(self, filename):
        '''
        Instantiates a JSON file repository.

        :param filename: the name of the file that the data will be stored in.
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

    def find_by_id(self, id_entity):
        '''
        Looks for an entity with the given id.

        :param id_entity: the id of the entity to search for.
        :return: the entity whose ID is id_entity, or None if no such entity exists.
        '''
        self.__read_file()
        if str(id_entity) in self.__storage:
            return self.__storage[str(id_entity)]
        return None

    def create(self, entity: Entity): # adaugare
        '''
        Stores an entity in the repository.

        :param entity: the entity to store.
        :return: None.
        :raises KeyError: if an entity with the same ID already exists.
        '''
        if self.find_by_id(entity.id_entity) is not None:
            raise KeyError(f'Entitatea cu id-ul {entity.id_entity} exista deja!')

        self.__storage[entity.id_entity] = entity
        self.__write_file()

    def update(self, entity: Entity):
        '''
        Updates an entity.

        :param entity: the entity that will be updated - the ID must be equal to the ID we wish to update.
        :return: None.
        :raises KeyError: if an entity with the id entity.id_entity does not exist.
        '''
        if self.find_by_id(entity.id_entity) is None:
            raise KeyError(f'Nu exista o entitate cu id-ul {entity.id_entity} pe care sa o actualizam!')

        self.__storage[entity.id_entity] = entity
        self.__write_file()

    def delete(self, id_entity):
        '''
        Deletes an entity.

        :param id_entitate: the ID of the entity we wish to delete.
        :return: None.
        :raises KeyError: if no entity with the given ID exists.

        '''
        if self.find_by_id(id_entity) is None:
            raise KeyError(f'Nu exista o entitate cu id-ul {id_entity} pe care sa o stergem!')

        del self.__storage[id_entity]
        self.__write_file()

    def get_all(self):
        '''
        Gets a list of all the entities.

        :return: a list of Entity (or derived from Entity).
        '''
        self.__read_file()
        return list(self.__storage.values())
