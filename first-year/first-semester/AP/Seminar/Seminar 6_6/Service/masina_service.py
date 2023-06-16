from copy import deepcopy
from typing import Optional

from Domain.masina import Masina
from Domain.masina_validator import MasinaValidator


class MasinaService:
    def __init__(self, masina_validator: MasinaValidator):
        self.masini = {}
        self.masina_validator = masina_validator

    def find_by_id(self, id_masina) -> Optional[Masina]:
        if id_masina in self.masini:
            return deepcopy(self.masini[id_masina])
        return None

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
        self.masina_validator.validate(masina)
        if plata_card == 'da':
            masina.plata_card = True
        else:
            masina.plata_card = False

        if self.find_by_id(id_masina):
            raise KeyError(f'ID-ul {id_masina} exista deja!')

        self.masini[id_masina] = masina

    def delete(self, id_masina):
        if self.find_by_id(id_masina) is None:
            raise KeyError(f'ID-ul {id_masina} nu exista!')
        del self.masini[id_masina]

    def update(self, id_masina, indicativ, nivel_confort, plata_card, model):
        masina = self.find_by_id(id_masina)
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

        self.masina_validator.validate(masina)
        if plata_card == 'da' or plata_card == '':
            masina.plata_card = True
        else:
            masina.plata_card = False

        self.masini[id_masina] = masina

    def get_all(self):
        return deepcopy(list(self.masini.values()))
