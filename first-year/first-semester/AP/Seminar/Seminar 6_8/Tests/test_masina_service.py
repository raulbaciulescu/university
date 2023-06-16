from Domain.masina_validator import MasinaValidator
from Repository.file_repository import FileRepository
from Service.masina_service import MasinaService
from Tests.utils import clear_file


def test_create_masina():
    filename = 'test_masini.txt'
    clear_file(filename)
    repository = FileRepository(filename)
    validator = MasinaValidator()
    service = MasinaService(repository, validator)
    service.create(1, 3, 'ridicat', 'da', 'dsfa')
    assert len(service.get_all()) == 1
    try:
        service.create(1, 3, 'ridicat', 'da', 'dsfa')
        assert False
    except KeyError:
        assert True
    except Exception:
        assert False
    assert len(service.get_all()) == 1

    try:
        service.create(2, 3, 'gdfgd', 'da', 'dsfa')
        assert False
    except ValueError:
        assert True
    except Exception:
        assert False
    assert len(service.get_all()) == 1

    added = repository.find_by_id(1)
    assert added is not None
    assert added.id_entity == 1
    assert added.indicativ == 3
    assert added.nivel_confort == 'ridicat'
    assert added.plata_card == True
    assert added.model == 'dsfa'