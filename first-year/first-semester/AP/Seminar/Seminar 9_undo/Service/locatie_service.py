from Domain.locatie import Locatie
from Repository.file_repository import FileRepository


class LocatieService:
    def __init__(self, locatii_repository: FileRepository):
        self.__locatii_repository = locatii_repository

    def create(self, id_locatie, nume_strada, numar, bloc, scara, alte_indicatii):
        locatie = Locatie(id_locatie, nume_strada, numar, bloc, scara, alte_indicatii)
        # TODO: daca e cazul, validari
        self.__locatii_repository.create(locatie)

        return self.__locatii_repository.get_all()

    def get_all(self):
        return self.__locatii_repository.get_all()
