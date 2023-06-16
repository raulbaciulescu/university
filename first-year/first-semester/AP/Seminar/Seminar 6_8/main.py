from Domain.masina_validator import MasinaValidator
from Repository.file_repository import FileRepository
from Service.comanda_service import ComandaService
from Service.locatie_service import LocatieService
from Service.masina_service import MasinaService
from Tests.run_all import run_all_tests
from UserInterface.console import Console


def main():

    masina_repository = FileRepository('masini.txt') # MasinaRepository()
    locatie_repository = FileRepository('locatii.txt')
    comanda_repository = FileRepository('comenzi.txt')

    masina_validator = MasinaValidator()

    masina_service = MasinaService(masina_repository, masina_validator)
    locatie_service = LocatieService(locatie_repository)
    comanda_service = ComandaService(masina_repository, locatie_repository, comanda_repository)

    # masina_service.create('1', '123', 'premium', 'da', 'asdfa')
    # masina_service.create('2', '223', 'ridicat', 'nu', 'as')
    # masina_service.create('3', '423', 'premium', 'da', 'dfa')
    # masina_service.create('4', '152', 'ridicat', 'da', 'afa')
    #
    # locatie_service.create('1', 'asdfafdsa')
    # locatie_service.create('2', '24332')
    # locatie_service.create('3', 'fdsf234')
    # locatie_service.create('4', '23214bbffd')
    #
    # comanda_service.create('1', '1', '1', 21.21)
    # comanda_service.create('2', '2', '1', 12.33)
    # comanda_service.create('3', '3', '2', 54.31)
    # comanda_service.create('4', '2', '3', 34.56)

    user_interface = Console(masina_service, locatie_service, comanda_service)
    user_interface.run_console()


run_all_tests()
main()
