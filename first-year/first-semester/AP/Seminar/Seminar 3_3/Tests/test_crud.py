from Domain.prajitura import create_prajitura
from Logic.crud import add_prajitura


def test_add_prajitura():
    prajituri = []
    p1 = create_prajitura(3, 'p1', 'yummy', 100, 500, 2019)
    add_prajitura(prajituri, 3, 'p1', 'yummy', 100, 500, 2019)
    assert len(prajituri) == 1
    assert prajituri[-1] == p1

    p2 = create_prajitura(5, 'p2', 'yummier', 110, 300, 2020)
    add_prajitura(prajituri, 5, 'p2', 'yummier', 110, 300, 2020)
    assert len(prajituri) == 2
    assert prajituri[0] == p1
    assert prajituri[1] == p2