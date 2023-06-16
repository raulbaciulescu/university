from Domain.prajitura import get_nume, get_calorii, set_calorii, get_an_introducere, get_pret


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
def get_suma_per_an(prajituri):
    '''

    :param prajituri:
    :return: un dictionar cu cheile fiind anii si valorile fiind sumele
    '''
    result = {}
    c = 0
    for prajitura in prajituri:
        an = get_an_introducere(prajitura)
        if an in result:
            result[an] += int(get_pret(prajitura))
        else:
            result[an] = int(get_pret(prajitura))
    return result
def get_prajituri_ordonate_dupa_pret_calorii(prajituri):
    '''

    :param prajituri:
    :return:
    '''
    aux = {}
    for i in range(0, len(prajituri) - 1):
        for j in range(i + 1, len(prajituri)):
            raport1 = get_pret(prajituri[i]) / get_calorii(prajituri[i])
            raport2 = get_pret(prajituri[j]) / get_calorii(prajituri[j])
            if raport1 > raport2:
                aux = prajituri[i]
                prajituri[i] = prajituri[j]
                prajituri[j] = aux
    return prajituri