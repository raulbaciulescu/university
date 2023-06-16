from Domain.entity import Entity


class Comanda(Entity):

    def __init__(self, id_comanda, id_masina, id_locatie, timp_final):
        super().__init__(id_comanda)
        self.id_masina = id_masina
        self.id_locatie = id_locatie
        self.timp_final = timp_final

    def __str__(self):
        return f'{self.id_entity} - {self.id_masina}, {self.id_locatie}, {self.timp_final}'
