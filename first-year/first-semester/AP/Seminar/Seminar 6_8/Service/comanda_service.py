from Domain.comanda import Comanda
from Repository.file_repository import FileRepository
from ViewModels.comanda_viewmodel import ComandaViewModel


class ComandaService:
    def __init__(self,
                 masina_repository: FileRepository,
                 locatie_repository: FileRepository,
                 comanda_repository: FileRepository):
        self.__masina_repository = masina_repository
        self.__locatie_repository = locatie_repository
        self.__comanda_repository = comanda_repository

    def create(self, id_comanda, id_masina, id_locatie, timp_final):

        comanda = Comanda(id_comanda, id_masina, id_locatie, timp_final)
        if self.__masina_repository.find_by_id(id_masina) is None:
            raise KeyError(f'Nu exista nicio masina cu id-ul {id_masina}')
        if self.__locatie_repository.find_by_id(id_locatie) is None:
            raise KeyError(f'Nu exista nicio locatie cu id-ul {id_locatie}')

        self.__comanda_repository.create(comanda)

    def get_all(self):
        viewmodels = []
        for comanda in self.__comanda_repository.get_all():
            masina = self.__masina_repository.find_by_id(comanda.id_masina)
            locatie = self.__locatie_repository.find_by_id(comanda.id_locatie)
            viewmodels.append(ComandaViewModel(masina, locatie, comanda.timp_final))
        return viewmodels
