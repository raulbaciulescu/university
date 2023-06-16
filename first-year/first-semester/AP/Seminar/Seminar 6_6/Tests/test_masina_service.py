from Service.masina_service import MasinaService


def test_create_masina():
    service = MasinaService()
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

    added = service.find_by_id(1)
    assert added is not None
    assert added.id_masina == 1
    assert added.indicativ == 3
    assert added.nivel_confort == 'ridicat'
    assert added.plata_card == True
    assert added.model == 'dsfa'