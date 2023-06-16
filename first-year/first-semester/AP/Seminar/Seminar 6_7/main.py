from Domain.masina_validator import MasinaValidator
from Repository.masina_file_repository import MasinaFileRepository
from Repository.masina_repository import MasinaRepository
from Service.masina_service import MasinaService
from Tests.run_all import run_all_tests
from UserInterface.console import Console


def main():

    masina_repository = MasinaFileRepository('masini.txt') # MasinaRepository()
    #masina_repository = MasinaRepository() # MasinaRepository()
    masina_validator = MasinaValidator()

    masina_service = MasinaService(masina_repository, masina_validator)

    user_interface = Console(masina_service)
    user_interface.run_console()


run_all_tests()
main()
