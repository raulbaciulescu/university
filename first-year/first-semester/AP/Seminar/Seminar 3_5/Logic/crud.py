from Domain.prajitura import create_prajitura, get_id
from Logic.file_ops import write_file


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

def add_prajitura(prajituri, id, nume, descriere, pret, calorii, an_introducere, filename='prajituri.txt'):
    '''

    :param prajituri: lista de prajituri
    :param id:
    :param nume:
    :param descriere:
    :param pret:
    :param calorii:
    :param an_introducere:
    :return:
    :raises: ValueError daca id-ul nu este unic
    '''
    prajitura_existenta = get_by_id(prajituri, id)
    if prajitura_existenta is not None:
        raise ValueError('ID-ul exista deja!')
    prajitura = create_prajitura(id, nume, descriere, pret, calorii, an_introducere)
    prajituri.append(prajitura)
    write_file(prajituri, filename)

def update_prajitura(prajituri, prajitura, filename='prajituri.txt'):
    # v1
    # for i, prajitura_existenta in enumerate(prajituri):
    #     if get_id(prajitura_existenta) == get_id(prajitura):
    #         prajituri[i] = prajitura
    #         break

    # v2
    result = [prajitura_existenta for prajitura_existenta in prajituri if get_id(prajitura) != get_id(prajitura_existenta)] + [prajitura]
    write_file(result, filename)
    return result

    # v3
    # delete_prajitura(prajituri, get_id(prajitura))
    # add_prajitura(...)

def delete_prajitura(prajituri, id, filename='prajituri.txt'):
    '''
    Sterge o prajitura.

    :param prajituri: lista de prajituri.
    :param id: id-ul prajiturii pe care dorim sa o stergem.
    :return: o noua lista din care va lipsi prajitura cu id-ul id.
    :raises: ValueError, daca id-ul nu exista
    '''
    prajitura_existenta = get_by_id(prajituri, id)
    if prajitura_existenta is None:
        raise ValueError('ID-ul dat nu exista!')
    rezultat = []
    for prajitura in prajituri[::-1]:
        if get_id(prajitura) != id:
            rezultat.append(prajitura)
    write_file(prajituri, filename)
    return rezultat