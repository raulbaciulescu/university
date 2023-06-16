from Domain.entity import Entity
from Repository.file_repository import FileRepository
from Utils.file_utils import clear_file


def test_file_repository():
    e1 = Entity('1')
    e2 = Entity('2')
    e3 = Entity('3')
    e4 = Entity('4')

    filename = 'test_repo.json'
    clear_file(filename)
    repo = FileRepository(filename)

    # create, find by id
    assert repo.get_all() == []
    repo.create(e1)
    assert repo.get_all() == [e1]
    repo.create(e2)
    repo.create(e3)
    assert len(repo.get_all()) == 3

    assert repo.find_by_id(e1.id_entity) == e1
    assert repo.find_by_id(e2.id_entity) == e2
    assert repo.find_by_id(e3.id_entity) == e3
    assert repo.find_by_id(e4.id_entity) is None

    try:
        repo.create(e1)
        assert False
    except KeyError:
        assert True
    assert len(repo.get_all()) == 3

    # update
    try:
        repo.update(Entity('8'))
        assert False
    except KeyError:
        assert True
    assert len(repo.get_all()) == 3

    repo.update(Entity('2'))
    assert repo.find_by_id(e2.id_entity) == e2
    assert len(repo.get_all()) == 3

    # delete
    try:
        repo.delete('10')
        assert False
    except KeyError:
        assert True
    assert len(repo.get_all()) == 3

    repo.delete(e1.id_entity)
    assert repo.find_by_id(e1.id_entity) is None
    assert len(repo.get_all()) == 2