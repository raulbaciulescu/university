from Domain.prajitura import create_prajitura


def add_prajitura(prajituri, id, nume, descriere, pret, calorii, an_introducere):
    '''

    :param prajituri: lista de prajituri
    :param id:
    :param nume:
    :param descriere:
    :param pret:
    :param calorii:
    :param an_introducere:
    :return:
    '''
    prajitura = create_prajitura(id, nume, descriere, pret, calorii, an_introducere)
    prajituri.append(prajitura)
