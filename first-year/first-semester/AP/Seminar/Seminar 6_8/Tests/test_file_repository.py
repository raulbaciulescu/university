from Domain.masina import Masina
from Repository.file_repository import FileRepository
from Tests.utils import clear_file


def test_file_repository():

    filename = 'test_masini.txt'
    clear_file(filename)

    repository = FileRepository(filename)
    m1 = Masina('1', 23, 'premium', True, 'fsdfs')
    repository.create(m1)

    all = repository.get_all()
    assert all[-1] == m1