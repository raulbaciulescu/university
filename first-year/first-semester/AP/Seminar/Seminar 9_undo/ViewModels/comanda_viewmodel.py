class ComandaViewModel:

    def __init__(self, id_comanda, masina, locatie, timp_final, cost_km, distanta, status):
        self.id_comanda = id_comanda
        self.masina = masina
        self.locatie = locatie
        self.timp_final = timp_final
        self.cost_km = cost_km
        self.distanta = distanta
        self.status = status

    def __str__(self):
        return f'Comanda cu id-ul {self.id_comanda}, masina: {self.masina}, ' \
               f'locatia: {self.locatie}, timp: {self.timp_final}, cost / km: {self.cost_km}, ' \
               f'distanta: {self.distanta}, status: {self.status}'