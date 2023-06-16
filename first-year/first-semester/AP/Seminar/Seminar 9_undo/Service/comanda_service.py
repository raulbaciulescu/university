from Domain.comanda import Comanda
from Repository.file_repository import FileRepository
from ViewModels.comanda_viewmodel import ComandaViewModel


class ComandaService:
    def __init__(self,
                 comenzi_repository: FileRepository,
                 masini_repository: FileRepository,
                 locatii_repository: FileRepository):
        self.__comenzi_repository = comenzi_repository
        self.__masini_repository = masini_repository
        self.__locatii_repository = locatii_repository

    def create(self, id_comanda, id_masina, id_locatie, timp_final, cost_km, distanta, status):

        comanda = Comanda(id_comanda, id_masina, id_locatie, timp_final, cost_km, distanta, status)
        if self.__masini_repository.find_by_id(id_masina) is None:
            raise KeyError(f'Nu exista nicio masina cu id-ul {id_masina}')
        if self.__locatii_repository.find_by_id(id_locatie) is None:
            raise KeyError(f'Nu exista nicio locatie cu id-ul {id_locatie}')

        self.__comenzi_repository.create(comanda)

    def get_all(self):
        viewmodels = []
        for comanda in self.__comenzi_repository.get_all():
            masina = self.__masini_repository.find_by_id(comanda.id_masina)
            locatie = self.__locatii_repository.find_by_id(comanda.id_locatie)
            viewmodels.append(ComandaViewModel(comanda.id_entitate, masina, locatie, comanda.timp_final, comanda.cost_km, comanda.distanta, comanda.status))
        return viewmodels