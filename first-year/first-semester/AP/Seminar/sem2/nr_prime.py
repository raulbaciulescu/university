'''
Să se scrie un program care citește o listă și afișează toate elementele care sunt prime.
Programul va avea un meniu cu trei opțiuni:
1. Citire lista
2. Afisare numere prime
3. Iesire
Tot codul va fi conținut în funcții (minim una specificată și testată, preferabil două specificate și testate
+ funcția main care doar citește, apelează și afișează). Fără variabile globale.
'''

def is_prime(num):
    '''
    Determina daca un numar este prim.
    :param num: int, numarul dat.
    :return: True daca num e prim si False altfel
    '''
    if num < 2:
        return False
    d = 2
    while d*d <= num:
        if num % d == 0:
            return False
        d += 1
    return True
def test_is_prime():
    assert is_prime(2) == True
    assert is_prime(1) == False
    assert is_prime(5) == True
    assert is_prime(6) == False
    assert is_prime(13) == True
    assert is_prime(15) == False
    assert is_prime(666013) == True
test_is_prime()

def get_prime(lst):
    '''
    Determina toate numerele prime dintr-o lista.
    :param lst: lista data.
    :return: o lista cu toate numerele prime din lst.
    '''
    result = []
    for num in lst:
        if is_prime(num):
            result.append(num)
    return result
def test_get_prime():
    assert get_prime([1,3,6,7,4,666013]) == [3, 7, 666013]
    assert get_prime([]) == []
    assert get_prime([2]) == [2]
test_get_prime()

def read_list():
    # citim numerele asa: 3,56,7,1,3, 43, 5 , 54
    given = input('Dati numerele separate prin virgula: ')
    str_list = given.split(',')
    int_list = []
    for str_num in str_list:
        int_list.append(int(str_num))
    return int_list

def print_menu():
    print('1. Citire lista')
    print('2. Afisare numere prime')
    print('x. Iesire')

def main():
    lst = []
    while True:
        print_menu()
        option = input('Alegeti o optiune: ')
        if option == '1':
            lst = read_list()
        elif option == '2':
            result = get_prime(lst)
            print('Numerele prime sunt: ')
            print(result)
        elif option == 'x':
            break
        else:
            print('Optiune invalida, reincearca!')
main()

