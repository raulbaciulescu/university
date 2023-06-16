from Domain.prajitura import to_str
from Logic.crud import add_prajitura, delete_prajitura
from Logic.operatiuni import reducere_calorii_by_string, get_praji_with_max_calorii_by_year, get_suma_per_an, get_prajituri_ordonate_dupa_pret_calorii


def print_menu():
    print('1. CRUD - Create, Read, Update, Delete')
    print('2. Operatiuni')
    print('3. Undo')
    print('x. Iesire')




def run_crud(prajituri):

    def handle_adaugare(prajituri):
        id = input('Dati id-ul: ')
        nume = input('Dati numele: ')
        descriere = input('Dati descrierea: ')
        pret = float(input('Dati pretul: '))
        calorii = int(input('Dati caloriile: '))
        an_introducere = int(input('Dati anul introducerii: '))

        add_prajitura(prajituri, id, nume, descriere, pret, calorii, an_introducere)

        print('Prajitura a fost adaugata!')

    def handle_stergere(prajituri):
        id = input('Dati id-ul de sters')

        prajituri = delete_prajitura(prajituri, id)
        print('Prajitura a fost stearsa!')
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
            handle_adaugare(prajituri)
        elif op == '2':
            prajituri = handle_stergere(prajituri)
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
    def handle_suma_preturilor_per_an(prajituri):
        rezultat = get_suma_per_an(prajituri)
        for an in rezultat:
            print('Suma din anul {} este: {}'.format(an, rezultat[an]))
    def handle_ordonare_dupa_pret_calorii(prajituri):
        rezultat = get_prajituri_ordonate_dupa_pret_calorii(prajituri)
        print(rezultat)

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
        elif op == '4':
            handle_ordonare_dupa_pret_calorii(prajituri)
        elif op == '5':
            handle_suma_preturilor_per_an(prajituri)
        elif op == 'b':
            break
        else:
            print('Comanda invalida, reincearca!')


def run_undo(prajituri):
    pass


def run_console(prajituri):

    while True:
        print_menu()
        op = input('Alegeti optiunea: ')

        if op == '1':
            prajituri = run_crud(prajituri)
        elif op == '2':
            run_operatiuni(prajituri)
        elif op == '3':
            run_undo(prajituri)
        elif op == 'x':
            break
        else:
            print('Comanda invalida')
    return prajituri