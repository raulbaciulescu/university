from Domain.masina import Masina


def test_masina():

    m = Masina(1, '123', 'standard', True, 'something')
    assert m.id_masina == 1
    assert m.indicativ == '123'
    assert m.nivel_confort == 'standard'
    assert m.plata_card == True
    assert m.model == 'something'
