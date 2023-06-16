def create_prajitura(id, nume, descriere, pret, calorii, an_introducere):
    '''

    :param id:
    :param nume:
    :param descriere:
    :param pret:
    :param calorii:
    :param an_introducere:
    :return:
    '''
    return {
        'id': id,
        'nume': nume,
        'descriere': descriere,
        'pret': pret,
        'calorii': calorii,
        'an_introducere': an_introducere
    }

def get_id(prajitura):
    '''

    :param prajitura:
    :return:
    '''
    return prajitura['id']

def get_nume(prajitura):
    '''

    :param prajitura:
    :return:
    '''
    return prajitura['nume']

def get_descriere(prajitura):
    '''

    :param prajitura:
    :return:
    '''
    return prajitura['descriere']

def get_pret(prajitura):
    '''

    :param prajitura:
    :return:
    '''
    return prajitura['pret']

def get_calorii(prajitura):
    '''

    :param prajitura:
    :return:
    '''
    return prajitura['calorii']
def set_calorii(prajitura, calorii):
    '''

    :param prajitura:
    :param calorii:
    :return:
    '''
    prajitura['calorii'] = calorii

def get_an_introducere(prajitura):
    '''

    :param prajitura:
    :return:
    '''
    return prajitura['an_introducere']

def to_str(prajitura):
    '''

    :param prajitura:
    :return:
    '''
    return f'Prajitura cu id={get_id(prajitura)}, {get_nume(prajitura)}: {get_descriere(prajitura)[:30]}, ' \
           f'pretul={get_pret(prajitura)}, calorii={get_calorii(prajitura)}, introdusa in {get_an_introducere(prajitura)}'