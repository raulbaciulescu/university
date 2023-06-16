from Domain.prajitura import get_nume, get_calorii, set_calorii, get_an_introducere


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

def get_praji_with_max_calorii_by_year(prajituri):
    '''


    :param prajituri:
    :return: un dictionar cu cheile fiind anii si valorile prajiturile cu nr maxim de calorii din acel an
    '''

    result = {}
    for prajitura in prajituri:
        an = get_an_introducere(prajitura)
        if an in result:
            if get_calorii(prajitura) > get_calorii(result[an]):
                result[an] = prajitura
        else:
            result[an] = prajitura

    return result