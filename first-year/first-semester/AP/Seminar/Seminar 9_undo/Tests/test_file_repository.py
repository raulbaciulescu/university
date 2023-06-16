from Domain.masina import Masina
from Repository.file_repository import FileRepository
from Tests.common import clear_file


def test_file_repository():

    filename = 'masini_test.txt'
    clear_file(filename)
    masina_repository = FileRepository(filename)
    assert masina_repository.get_all() == []

    m = Masina('1', 23, 'ridicat', True, 'fdsfsd')
    masina_repository.create(m)

    assert masina_repository.get_all() == [m]