from Domain.entitate import Entitate


class Comanda(Entitate):

    def __init__(self, id_comanda, id_masina, id_locatie, timp_final, cost_km, distanta, status):
        super().__init__(id_comanda)
        self.id_masina = id_masina
        self.id_locatie = id_locatie
        self.timp_final = timp_final
        self.cost_km = cost_km
        self.distanta = distanta
        self.status = status

    def __str__(self):
        return f'{self.id_entitate} - id_masina:{self.id_masina}, id_locatie: {self.id_locatie}'