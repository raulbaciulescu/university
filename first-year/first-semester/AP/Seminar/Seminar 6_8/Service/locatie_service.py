from Domain.locatie import Locatie
from Repository.file_repository import FileRepository


class LocatieService:
    def __init__(self, locatie_repository: FileRepository):
        self.__locatie_repository = locatie_repository

    def create(self, id_locatie, nume_strada):
        locatie = Locatie(id_locatie, nume_strada)
        #self.__locatie_validator.validate(locatie)
        self.__locatie_repository.create(locatie)

    def get_all(self):
        return self.__locatie_repository.get_all()
