from Domain.prajitura import get_nume, get_calorii, set_calorii


def reducere_calorii_by_string(prajituri, string_de_cautare, reducere):
    '''

    :param prajituri:
    :param string_de_cautare:
    :param reducere:
    :return:
    '''

    for prajitura in prajituri:
        if string_de_cautare in get_nume(prajitura):
            calorii = get_calorii(prajitura) - reducere
            set_calorii(prajitura, calorii)
