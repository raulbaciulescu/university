from Domain.prajitura import create_prajitura, get_id

def get_by_id(prajituri, id):
    '''
    Gaseste o prajitura dupa id.

    :param prajituri:
    :param id:
    :return: prajitura cu id-ul id sau None daca nu exista
    '''

    for prajitura in prajituri:
        if get_id(prajitura) == id:
            return prajitura

    return None

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

def delete_prajitura(prajituri, id):
    '''
    Sterge o prajitura.

    :param prajituri: lista de prajituri.
    :param id: id-ul prajiturii pe care dorim sa o stergem.
    :return: o noua lista din care va lipsi prajitura cu id-ul id.
    '''
    rezultat = []
    for prajitura in prajituri[::-1]:
        if get_id(prajitura) != id:
            rezultat.append(prajitura)
    return rezultat
