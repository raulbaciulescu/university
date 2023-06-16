from Domain.masina_validator import MasinaValidator
from Repository.file_repository import FileRepository
from Service.masina_service import MasinaService
from Service.undo_redo_service import UndoRedoService
from Tests.common import clear_file


def test_create_masina():

    filename = 'masini_test.txt'
    clear_file(filename)
    masina_repository = FileRepository(filename)
    masina_validator = MasinaValidator()

    undo_redo_service = UndoRedoService()

    service = MasinaService(masina_repository, masina_validator, undo_redo_service)
    service.create(1, 3, 'ridicat', 'da', 'dsfa')
    assert len(service.get_all()) == 1
    try:
        service.create(1, 3, 'ridicat', 'da', 'dsfa')
        assert False
    except KeyError:
        assert True
    except Exception as ex:
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

    added = masina_repository.find_by_id(1)
    assert added is not None
    assert added.id_entitate == 1
    assert added.indicativ == 3
    assert added.nivel_confort == 'ridicat'
    assert added.plata_card == True
    assert added.model == 'dsfa'