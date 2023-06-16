from Domain.prajitura import to_str, create_prajitura, get_nume, get_descriere, get_pret, get_calorii, \
    get_an_introducere
from Logic.crud import add_prajitura, delete_prajitura, update_prajitura, get_by_id
from Logic.file_ops import write_file
from Logic.operatiuni import reducere_calorii_by_string, get_praji_with_max_calorii_by_year


def print_menu():
    print('1. CRUD - Create, Read, Update, Delete')
    print('2. Operatiuni')
    print('u. Undo')
    print('x. Iesire')



def run_crud(prajituri, undo_list):

    def handle_adaugare(prajituri, undo_list):
        try:
            id = input('Dati id-ul: ')
            nume = input('Dati numele: ')
            descriere = input('Dati descrierea: ')
            pret = float(input('Dati pretul: '))
            calorii = int(input('Dati caloriile: '))
            an_introducere = int(input('Dati anul introducerii: '))

            before_add = prajituri[:] # s-ar putea sa fie nevoie de deepcopy in unele cazuri!
            add_prajitura(prajituri, id, nume, descriere, pret, calorii, an_introducere)

            undo_list.append(before_add)
            print('Prajitura a fost adaugata!')
        except ValueError as ve:
            print('Eroare:', ve, ', reincearca!')

    def handle_modificare(prajituri):
        id = input('Dati id-ul: ')
        prajitura_existenta = get_by_id(prajituri, id)

        nume = input('Dati numele (lasati gol pentru a nu se schimba): ')
        if nume == '':
            nume = get_nume(prajitura_existenta)

        descriere = input('Dati descrierea (lasati gol pentru...): ')
        if descriere == '':
            descriere = get_descriere(prajitura_existenta)

        pret = input('Dati pretul: ')
        if pret == '':
            pret = get_pret(prajitura_existenta)
        else:
            pret = float(pret)

        calorii = input('Dati caloriile: ')
        if calorii == '':
            calorii = get_calorii(prajitura_existenta)
        else:
            calorii = int(calorii)

        an_introducere = input('Dati anul introducerii: ')
        if an_introducere == '':
            an_introducere = get_an_introducere(prajitura_existenta)
        else:
            an_introducere = int(an_introducere)

        prajitura_noua = create_prajitura(id, nume, descriere, pret, calorii, an_introducere)
        prajituri = update_prajitura(prajituri, prajitura_noua)
        print('Prajitura a fost modificata!')
        return prajituri

    def handle_stergere(prajituri):
        try:
            id = input('Dati id-ul de sters: ')

            new_prajituri = delete_prajitura(prajituri, id)
            undo_list.append(prajituri) # s-ar putea sa fie nevoie de deepcopy
            prajituri = new_prajituri
            print('Prajitura a fost stearsa!')
        except ValueError as ve:
            print('Eroare:', ve, ', reincearca!')
        return prajituri

    def handle_show_all(prajituri):
        for prajitura in prajituri:
            print(to_str(prajitura))

    while True:
        print('1. Adaugare')
        print('2. Stergere')
        print('3. Modificare')
        print('a. Afisare toate')
        print('b. Back')
        op = input('Alegeti optiunea: ')
        if op == '1':
            handle_adaugare(prajituri, undo_list)
        elif op == '2':
            prajituri = handle_stergere(prajituri)
        elif op == '3':
            prajituri = handle_modificare(prajituri)
        elif op == 'a':
            handle_show_all(prajituri)
        elif op == 'b':
            break
        else:
            print('Comanda invalida, reincearca!')
    return prajituri


def run_operatiuni(prajituri):

    def handle_reducere_calorii(prajituri):
        string_dat = input('Dati stringul de cautare: ')
        reducere = float(input('Dati cu cat sa se reduca: '))

        reducere_calorii_by_string(prajituri, string_dat, reducere)

    def handle_calorii_max_per_an(prajituri):
        rezultat = get_praji_with_max_calorii_by_year(prajituri)
        for an in rezultat:
            print('Prajitura cu caloriile maxime din anul {} este: {}'.format(an, to_str(rezultat[an])))

    while True:
        print('1. Reducere calorii')
        print('2. Afisare introduse dupa un an')
        print('3. Afisare cu calorii maxime per an')
        print('4. Afisare ordonate dupa raport pret / calorii')
        print('5. Afisarea sumei preturilor pentru fiecare an')
        print('b. Back')
        op = input('Alegeti optiunea: ')
        if op == '1':
            handle_reducere_calorii(prajituri)
        elif op == '2':
            pass
        elif op == '3':
            handle_calorii_max_per_an(prajituri)
        elif op == 'b':
            break
        else:
            print('Comanda invalida, reincearca!')


def run_undo(undo_list):
    if len(undo_list) > 0:
        return undo_list.pop()


def run_console(prajituri, undo_list):

    while True:
        print_menu()
        op = input('Alegeti optiunea: ')

        if op == '1':
            prajituri = run_crud(prajituri, undo_list)
        elif op == '2':
            run_operatiuni(prajituri)
        elif op == 'u':
            undone = run_undo(undo_list)
            if undone is not None:
                prajituri = undone
                write_file(prajituri) # TODO: move somewhere to Logic
        elif op == 'x':
            break
        else:
            print('Comanda invalida')
    return prajituri