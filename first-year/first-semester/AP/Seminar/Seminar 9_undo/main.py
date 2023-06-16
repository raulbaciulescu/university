from Domain.masina_validator import MasinaValidator
from Repository.file_repository import FileRepository
from Service.comanda_service import ComandaService
from Service.locatie_service import LocatieService
from Service.masina_service import MasinaService
from Service.undo_redo_service import UndoRedoService
from Tests.run_all import run_all_tests
from UserInterface.console import Console


def main():

    masina_repository = FileRepository('masini.txt') #MasinaRepository()
    locatie_repository = FileRepository('locatii.txt')
    comanda_repository = FileRepository('comenzi.txt')

    masina_validator = MasinaValidator()

    undo_redo_service = UndoRedoService()

    masina_service = MasinaService(masina_repository, masina_validator, undo_redo_service)
    locatie_service = LocatieService(locatie_repository)
    comanda_service = ComandaService(comanda_repository, masina_repository, locatie_repository)

    masina_service.create('1', '123', 'ridicat', 'da', 'model 1')
    masina_service.create('2', '456', 'ridicat', 'nu', 'model 2')
    masina_service.create('3', '789', 'premium', 'da', 'model 3')
    masina_service.create('4', '000', 'ridicat', 'nu', 'model 4')

    locatie_service.create('1', 'str 1', '1', '1', 'A', 'indicatii 0')
    locatie_service.create('2', 'str 2', '11', '4', 'B', 'indicatii 1')
    locatie_service.create('3', 'str 3', '12', '2', 'C', 'indicatii 2')
    locatie_service.create('4', 'str 4', '23', '3', 'D', 'indicatii 3')

    comanda_service.create('1', '1', '1', 10, 13, 200, 'in desfasurare')
    comanda_service.create('2', '1', '2', 11, 11, 100, 'finalizata')

    user_interface = Console(masina_service, locatie_service, comanda_service, undo_redo_service)
    user_interface.run_console()


run_all_tests()
main()

for i in range(len(lst)):
    s += lst[i]

