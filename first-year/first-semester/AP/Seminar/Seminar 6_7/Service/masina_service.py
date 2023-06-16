from copy import deepcopy
from typing import Optional

from Domain.masina import Masina
from Domain.masina_validator import MasinaValidator
from Repository.masina_repository import MasinaRepository


class MasinaService:
    def __init__(self, masina_repository: MasinaRepository, masina_validator: MasinaValidator):
        self.__masina_repository = masina_repository
        self.__masina_validator = masina_validator

    def create(self, id_masina, indicativ, nivel_confort, plata_card, model):
        '''
        Adauga o masina.
        :param id_masina: ...
        :param indicativ:
        :param nivel_confort:
        :param plata_card:
        :param model:
        :return: -
        '''
        masina = Masina(id_masina, indicativ, nivel_confort, plata_card, model)
        self.__masina_validator.validate(masina)
        if plata_card == 'da':
            masina.plata_card = True
        else:
            masina.plata_card = False

        self.__masina_repository.create(masina)

    def delete(self, id_masina):
        self.__masina_repository.delete(id_masina)

    def update(self, id_masina, indicativ, nivel_confort, plata_card, model):
        masina = self.__masina_repository.find_by_id(id_masina)
        if masina is None:
            raise KeyError(f'Masina cu id-ul {id_masina} nu exista!')

        if indicativ != '':
            masina.indicativ = indicativ
        if nivel_confort != '':
            masina.nivel_confort = nivel_confort
        if plata_card != '':
            masina.plata_card = plata_card
        if model != '':
            masina.model = model

        self.__masina_validator.validate(masina)
        if plata_card == 'da' or plata_card == '':
            masina.plata_card = True
        else:
            masina.plata_card = False

        self.__masina_repository.update(masina)

    def get_all(self):
        return self.__masina_repository.get_all()
