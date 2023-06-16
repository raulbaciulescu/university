class Masina:
    '''
    Descrie o masina.
    '''

    def __init__(self, id_masina, indicativ, nivel_confort, plata_card, model):
        self.id_masina = id_masina
        self.indicativ = indicativ
        self.nivel_confort = nivel_confort
        self.plata_card = plata_card
        self.model = model

    def __str__(self):
        return f'{self.id_masina} - indicativ:{self.indicativ}, confort:{self.nivel_confort}, ' \
               f'plata card:{self.plata_card}; model: {self.model}'


