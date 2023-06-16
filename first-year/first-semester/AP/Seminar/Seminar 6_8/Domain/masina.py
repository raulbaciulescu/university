from Domain.entity import Entity


class Masina(Entity):
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
    def indicativ(self, value):
        self.__indicativ = value

    @property
    def nivel_confort(self):
        return self.__nivel_confort

    @nivel_confort.setter
    def nivel_confort(self, value):
        self.__nivel_confort = value

    @property
    def plata_card(self):
        return self.__plata_card

    @plata_card.setter
    def plata_card(self, value):
        self.__plata_card = value

    @property
    def model(self):
        return self.__model

    @model.setter
    def model(self, value):
        self.__model = value

    def __str__(self):
        return f'{self.id_entity} - indicativ:{self.__indicativ}, confort:{self.__nivel_confort}, ' \
               f'plata card:{self.__plata_card}; model: {self.__model}'
