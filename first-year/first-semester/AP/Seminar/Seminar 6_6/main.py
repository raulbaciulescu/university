from Domain.masina_validator import MasinaValidator
from Service.masina_service import MasinaService
from Tests.run_all import run_all_tests
from UserInterface.console import Console


def main():

    masina_validator = MasinaValidator()

    masina_service = MasinaService(masina_validator)
    masina_service.create('1', '123', 'ridicat', 'da', 'fdsfsd')
    masina_service.create('2', '321', 'premium', 'da', '2323')
    masina_service.create('3', '55', 'standard', 'nu', 'cvcx')
    user_interface = Console(masina_service)
    user_interface.run_console()


#run_all_tests()
main()