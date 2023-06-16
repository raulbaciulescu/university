from Domain.prajitura import to_str
from Logic.crud import add_prajitura
from Logic.operatiuni import reducere_calorii_by_string


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
        elif op == 'a':
            handle_show_all(prajituri)
        elif op == 'b':
            break
        else:
            print('Comanda invalida, reincearca!')


def run_operatiuni(prajituri):

    def handle_reducere_calorii(prajituri):
        string_dat = input('Dati stringul de cautare: ')
        reducere = float(input('Dati cu cat sa se reduca: '))

        reducere_calorii_by_string(prajituri, string_dat, reducere)

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
            run_crud(prajituri)
        elif op == '2':
            run_operatiuni(prajituri)
        elif op == '3':
            run_undo(prajituri)
        elif op == 'x':
            break
        else:
            print('Comanda invalida')