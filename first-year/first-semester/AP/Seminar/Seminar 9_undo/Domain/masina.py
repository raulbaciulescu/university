from Domain.entitate import Entitate


class Masina(Entitate):
    '''
    Descrie o masina.
    '''

    def __init__(self, id_masina, indicativ, nivel_confort, plata_card, model):
        super().__init__(id_masina)

        self.__indicativ = indicativ
        self.__nivel_confort = nivel_confort
        self.__plata_card = plata_card
        self.__model = model

    @property
    def indicativ(self):
        return self.__indicativ

    @indicativ.setter
    def indicativ(self, val):
        self.__indicativ = val

    @property
    def nivel_confort(self):
        return self.__nivel_confort

    @nivel_confort.setter
    def nivel_confort(self, val):
        self.__nivel_confort = val

    @property
    def plata_card(self):
        return self.__plata_card

    @plata_card.setter
    def plata_card(self, val):
        self.__plata_card = val

    @property
    def model(self):
        return self.__model

    @model.setter
    def model(self, val):
        self.__model = val

    def __str__(self):
        return f'{self.id_entitate} - indicativ:{self.indicativ}, confort:{self.nivel_confort}, ' \
               f'plata card:{self.plata_card}; model: {self.model}'
