from Domain.entity import Entity


class Locatie(Entity):

    def __init__(self, id_locatie, nume_strada):
        super().__init__(id_locatie)
        self.nume_strada = nume_strada # TODO: make me a property

    def __str__(self):
        return f'{self.id_entity} - {self.nume_strada}'