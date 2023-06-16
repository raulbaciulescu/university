class ComandaViewModel:
    def __init__(self, masina, locatie, timp_final):
        self.masina = masina
        self.locatie = locatie
        self.timp_final = timp_final

    def __str__(self):
        return f'Comanda cu masina {self.masina} si locatia {self.locatie} cu timpul final {self.timp_final}'