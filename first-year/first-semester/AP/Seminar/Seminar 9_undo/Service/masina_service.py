from copy import deepcopy
from typing import Optional

from Domain.add_operation import AddOperation
from Domain.masina import Masina
from Domain.masina_validator import MasinaValidator
from Repository.file_repository import FileRepository
from Service.undo_redo_service import UndoRedoService


class MasinaService:
    def __init__(self, masini_repository: FileRepository, masina_validator: MasinaValidator, undo_redo_service: UndoRedoService):
        self.__masini_repository = masini_repository
        self.__masina_validator = masina_validator
        self.__undo_redo_service = undo_redo_service

    def create(self, id_masina, indicativ, nivel_confort, plata_card, model):
        '''
        Adauga o masina.

        :param id_masina: ...
        :param indicativ:
        :param nivel_confort:
        :param plata_card:
        :param model:
        :raises ValueError: daca ...
        :raises KeyError: daca id-ul exista deja
        :return: -
        '''
        masina = Masina(id_masina, indicativ, nivel_confort, plata_card, model)
        self.__masina_validator.validate(masina)
        if plata_card == 'da':
            masina.plata_card = True
        else:
            masina.plata_card = False

        self.__masini_repository.create(masina)

        self.__undo_redo_service.add_to_undo(AddOperation(self.__masini_repository, masina))

    def delete(self, id_masina):
        self.__masini_repository.delete(id_masina)

    def update(self, id_masina, indicativ, nivel_confort, plata_card, model):
        masina = self.__masini_repository.find_by_id(id_masina)
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

        self.__masini_repository.update(masina)

    def get_all(self):
        return self.__masini_repository.get_all()
