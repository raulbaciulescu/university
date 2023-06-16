from Domain.vaccin import Vaccin
from Domain.vaccin_validator import VaccinValidator
from Repository.file_repository import FileRepository
from Service.vaccin_service import VaccinService
from Utils.file_utils import clear_file


def test_vaccine_service():

    v1 = Vaccin('1', 'moderna', 'mRNA')
    v2 = Vaccin('2', 'chadox', 'virus atenuat')
    v3 = Vaccin('3', 'pfeizer', 'virus activ')

    filename = 'test_vaccin_service.json'
    clear_file(filename)
    vaccin_repository = FileRepository(filename)
    vaccin_validator = VaccinValidator()
    vaccin_service = VaccinService(vaccin_repository, vaccin_validator)

    try:
        vaccin_service.adaugare('1', 'whatever', 'fdsfs')
        assert False
    except ValueError:
        assert True

    try:
        vaccin_service.adaugare('1', '', 'mRNA')
        assert False
    except ValueError:
        assert True
    assert len(vaccin_service.get_all()) == 0

    vaccin_service.adaugare(v1.id_entity, v1.nume, v1.tehnologie)
    assert len(vaccin_service.get_all()) == 1
    assert vaccin_repository.find_by_id('1') == v1

    # testam ca nu se poate adauga cu acelasi id
    try:
        vaccin_service.adaugare(v1.id_entity, v1.nume, v1.tehnologie)
        assert False
    except KeyError:
        assert True
    assert len(vaccin_service.get_all()) == 1
