'''
Determinarea celei mai lungi subsecvențe cu toate elementele pare.
Accent pe scrierea a cât mai puțin cod, folosind slicing.
'''


def all_even(lst):
    '''
    Determina daca o lista este formata doar din elemente pare.
    :param lst: lista data.
    :return: True daca lst are doar elemente pare si False altfel
    '''
    for num in lst:
        if num % 2 != 0:
            return False
    return True
def test_all_even():
    pass # TODO
test_all_even()

def get_longest_sublist_all_even(lst):
    '''
    Calculeaza prime cea mai lunga subsecv cu toate numerele pare.
    :param lst: lista data
    :return: O lista reprezentand subsecv ceruta.
    '''

    # [1, 100, 3, 5, 20, 1, 2, 3, 4]

    result = []
    for i in range(len(lst)):
        for j in range(i, len(lst)):
            considered = lst[i:j+1]
            if all_even(considered):
                if len(result) < len(considered):
                    result = considered
    return result
def test_get_longest_sublist_all_even():
    assert get_longest_sublist_all_even([1,2,3,4,5,6,7,8,9]) == [2]
    assert get_longest_sublist_all_even([1,2,4,5,6,8,0,9]) == [6, 8, 0]
test_get_longest_sublist_all_even()

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
    print('2. Afisare subsecv de lungime maxima cu toate elementele pare')
    print('x. Iesire')

def main():
    lst = []
    while True:
        print_menu()
        option = input('Alegeti o optiune: ')
        if option == '1':
            lst = read_list()
        elif option == '2':
            result = get_longest_sublist_all_even(lst)
            print('Cea mai lunga subsecv cu toate numerel pare este: ')
            print(result)
        elif option == 'x':
            break
        else:
            print('Optiune invalida, reincearca!')
main()

