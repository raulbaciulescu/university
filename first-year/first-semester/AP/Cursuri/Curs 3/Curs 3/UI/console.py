from Domain.nr_rational import create_rational, to_str
from Logic.add_logic import add_rationals


def print_menu():
    print('1. Adunare')
    print('2. Scadere')
    print('3. Inmultire')
    print('4. Impartire')
    print('x. Iesire')

def run_add(total):
    numarator = int(input('Dati numaratorul: '))
    numitor = int(input('Dati numitorul: '))

    rational = create_rational(numarator, numitor)
    new_total = add_rationals(total, rational)
    print('Noul total este:', to_str(new_total))
    return new_total

def run_console(total):

    while True:
        print_menu()
        op = input('Alegeti optiunea: ')
        if op == '1':
            total = run_add(total)
            pass
        elif op == 'x':
            break
        else:
            print('Optiune invalida, reincercati!')

    return total