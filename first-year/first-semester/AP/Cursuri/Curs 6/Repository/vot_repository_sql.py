class VotRepository:

    def __init__(self):
        self.__next_id = 0
        self.__storage = {}

    def read(self, id_vot=None): # posibil si find_by_id + get_all
        if id_vot is None:
            return list(self.__storage.values())

        if id_vot in self.__storage:
            return self.__storage[id_vot]

        return None

    def upsert(self, vot): # posibil si insert + update
        if vot.id_vot is None: # TODO: pus breakpoint pe vechea varianta: if self.read(vot.id_vot) is None
            vot._Vot__id_vot = self.__next_id
            self.__next_id += 1
        self.__storage[vot.id_vot] = vot

    def delete(self, id_vot):
        if self.read(id_vot) is None:
            raise KeyError(f'Votul cu ID-ul {id_vot} nu exista!')

        del self.__storage[id_vot]