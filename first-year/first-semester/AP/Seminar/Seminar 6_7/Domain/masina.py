class Masina:
    '''
    Descrie o masina.
    '''

    def __init__(self, id_masina, indicativ, nivel_confort, plata_card, model):
        self.__id_masina = id_masina
        self.__indicativ = indicativ
        self.__nivel_confort = nivel_confort
        self.__plata_card = plata_card
        self.__model = model

    @property
    def id_masina(self):
        return self.__id_masina

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
        return f'{self.__id_masina} - indicativ:{self.__indicativ}, confort:{self.__nivel_confort}, ' \
               f'plata card:{self.__plata_card}; model: {self.__model}'

    def __eq__(self, other):
        return type(self) == type(other) and self.id_masina == other.id_masina

