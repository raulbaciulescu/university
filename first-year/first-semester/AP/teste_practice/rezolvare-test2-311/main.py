from Domain.studiu_validator import StudiuClinicValidator
from Domain.vaccin_validator import VaccinValidator
from Repository.file_repository import FileRepository
from Service.studiu_service import StudiuService
from Service.vaccin_service import VaccinService
from Tests.test_repository import test_file_repository
from Tests.test_studiu_service import test_studiu_service
from Tests.test_vaccin_service import test_vaccine_service
from UI.console import Console


def main():
    test_file_repository()
    test_vaccine_service()
    test_studiu_service()

    vaccin_repository = FileRepository('vaccinuri.json')
    studiu_repository = FileRepository('studii clinice.json')

    vaccin_validator = VaccinValidator()
    studiu_validator = StudiuClinicValidator()

    vaccin_service = VaccinService(vaccin_repository, vaccin_validator)
    studiu_service = StudiuService(studiu_repository, vaccin_repository, studiu_validator)
    '''
    vaccin_service.adaugare('1', 'nume1', 'virus atenuat')
    vaccin_service.adaugare('2', 'nume1', 'mRNA')
    vaccin_service.adaugare('3', 'nume1', 'virus inactiv')
    vaccin_service.adaugare('4', 'nume1', 'mRNA')
    vaccin_service.adaugare('5', 'nume1', 'virus atenuat')
    vaccin_service.adaugare('6', 'nume1', 'mRNA')
    vaccin_service.adaugare('7', 'nume1', 'mRNA')
    vaccin_service.adaugare('8', 'nume1', 'virus inactiv')

    studiu_service.adaugare('1', '1', 122, 45, 21)
    studiu_service.adaugare('2', '2', 122, 42, 23)
    studiu_service.adaugare('3', '1', 321, 67, 76)
    studiu_service.adaugare('4', '3', 122, 45, 23)
    studiu_service.adaugare('5', '2', 45, 45, 23)
    studiu_service.adaugare('6', '1', 122, 12, 3)
    studiu_service.adaugare('7', '8', 565, 5, 23)
    studiu_service.adaugare('8', '5', 565, 47, 13)
    studiu_service.adaugare('9', '6', 565, 48, 23)
    '''
    console = Console(vaccin_service, studiu_service)
    console.run_console()


if __name__ == '__main__':
    main()