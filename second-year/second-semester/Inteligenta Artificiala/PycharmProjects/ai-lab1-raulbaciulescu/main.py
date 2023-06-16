import math
from tests import *

"""
Să se determine cuvintele unui text care apar exact o singură dată în acel text. 
De ex. cuvintele care apar o singură dată în ”ana are ana are mere rosii ana" sunt: 'mere' și 'rosii'.
"""
def cerinta4(text):
    text_separat = text.split()
    dict = {}
    for word in text_separat:
        if word in dict:
            dict[word] += 1
        else:
            dict[word] = 1
    return dict
"""
Considerându-se o matrice cu n x m elemente întregi și o listă cu perechi formate din coordonatele a 2 căsuțe din matrice ((p,q) și (r,s)), 
să se calculeze suma elementelor din sub-matricile identificate de fieare pereche.
"""
def cerinta9(matrix, list):
    sum = 0
    for i in range(list[0], list[2] + 1):
        for j in range(list[1], list[3] + 1):
            sum += matrix[i][j]
    return sum
"""
Considerându-se o matrice cu n x m elemente binare (0 sau 1)
 sortate crescător pe linii, să se identifice indexul liniei care conține cele mai multe elemente de 1.
"""
def cerinta10(matrix):
    maxim = -1
    contor = 0
    linie = 0
    for row in matrix:
        sum = row.count(1)
        if sum > maxim:
            maxim = sum
            linie = contor
        contor += 1
    return linie
"""
Să se determine distanța Euclideană între două locații identificate prin perechi de numere. 
De ex. distanța între (1,5) și (4,1) este 5.0
"""
def cerinta2(x1, y1, x2, y2):
    dist = math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2)).real
    return dist


"""
Să se determine produsul scalar a doi vectori rari care conțin numere reale. Un vector este rar atunci când conține multe elemente nule. 
Vectorii pot avea oricâte dimensiuni. De ex. produsul scalar a 2 vectori unisimensionali [1,0,2,0,3] și [1,2,0,3,1] este 4.
"""
def cerinta3(vect1, vect2, dimens):
    prod = 0
    for i in range(dimens):
        prod += vect2[i] * vect1[i]
    return prod


"""
Să se determine ultimul (din punct de vedere alfabetic) cuvânt care poate apărea într-un text care conține mai multe
cuvinte separate prin ” ” 
(spațiu). De ex. ultimul (dpdv alfabetic) cuvânt din ”Ana are mere rosii si galbene” este cuvântul "si".
"""
def cerinta1(text):
    text_split = text.split()
    maxim = ''
    for word in text_split:
        if word > maxim:
            maxim = word
    return maxim

"""
Să se determine al k-lea cel mai mare element al unui șir de numere cu 
n elemente (k < n). De ex. al 2-lea cel mai mare element din șirul [7,4,6,3,9,1] este 7.
"""
def cerinta7(dimens, vect, k):
    vect.sort()
    return vect[dimens - k]


"""
Pentru un șir cu n elemente care conține valori din mulțimea {1, 2, ..., n - 1} astfel încât o singură valoare se repetă de două ori,
 să se identifice acea valoare care se repetă. De ex. în șirul [1,2,3,4,2] valoarea 2 apare de două ori.
"""
def cerinta5(vect):
    dict = {}
    for i in vect:
        if i in dict:
            dict[i] += 1
        else:
            dict[i] = 1
    for key in dict:
        if dict[key] == 2:
            return key



"""
Pentru un șir cu n numere întregi care conține și duplicate, să se determine elementul majoritar 
(care apare de mai mult de n / 2 ori). De ex. 2 este elementul majoritar în șirul [2,8,7,2,2,5,2,3,1,2,2].
"""
def cerinta6(dimens, vect):
    dict = {}
    for elem in vect:
        if elem in dict:
            dict[elem] += 1
        else:
            dict[elem] = 1
    for key in dict:
        if dict[key] >= dimens / 2:
            return key

def menu():
    while True:
        print("""
        1. Cerinta1
        2. Cerinta2
        3. Cerinta3
        4. Cerinta4
        5. Cerinta5
        6. Cerinta6
        7. Cerinta7
        9. Cerinta9
        10. Cerinta10
        """)
        command = int(input('>> '))
        # dict = {9: cerinta9, 4: cerinta4, 10: cerinta10, 2: cerinta2, 3: cerinta3, 1: cerinta1}
        # if command in dict.keys():
        #     dict[command]()
        if command == 1:
            text = input('introdu textul: ')
            print(cerinta1(text))
        if command == 2:
            x1 = int(input('introdu x1: '))
            y1 = int(input('introdu y1: '))
            x2 = int(input('introdu x2: '))
            y2 = int(input('introdu y2: '))
            print(cerinta2(x1, y1, x2, y2))
        if command == 3:
            dimens = int(input('introdu dimensiunea: '))
            vect1 = []
            vect2 = []
            for i in range(dimens):
                vect1.append(int(input(f'{i}: ')))

            for i in range(dimens):
                vect2.append(int(input(f'{i}: ')))
            print(cerinta3(vect1, vect2, dimens))
        if command == 4:
            text = input('Introdu textul pentru cerinta4: ')
            # text = 'ana are ana are mere rosii ana'
            dict = cerinta4(text)
            for key in dict:
                if dict[key] == 1:
                    print(key)
        if command == 9:
            # matrix = [[0, 2, 5, 4, 1],
            #             [4, 8, 2, 3, 7],
            #             [6, 3, 4, 6, 2],
            #             [7, 3, 1, 8, 3],
            #             [1, 5, 7, 9, 4]]
            # list = ((1, 1), (3, 3))
            matrix = []
            n = int(input('nr linii: '))
            m = int(input('nr coloane: '))
            for i in range(n):
                a = []
                for j in range(m):
                    a.append(int(input(f'[{i}][{j}]: ')))
                matrix.append(a)

            list = []
            for i in range(4):
                list.append(int(input(f'{i}: ')))
            print(cerinta9(matrix, list))
        if command == 10:
            matrix = []
            n = int(input('nr linii: '))
            m = int(input('nr coloane: '))
            for i in range(n):
                a = []
                for j in range(m):
                    a.append(int(input(f'[{i}][{j}]: ')))
                matrix.append(a)

            # matrix = [[0,0,0,1,1],
            #             [0,1,1,1,1],
            #             [0,0,1,1,1]]
            print(cerinta10(matrix))
        if command == 7:
            dimens = int(input('introdu dimensiunea: '))
            vect = []
            print('introdu elementele: ')
            for i in range(dimens):
                vect.append(int(input()))
            k = int(input('introdu k: '))
            print(cerinta7(dimens, vect, k))
        if command == 5:
            dimens = int(input('introdu dimensiunea: '))
            vect = []
            print('introdu elementele: ')
            for i in range(dimens):
                vect.append(int(input()))
            print(cerinta5(vect))
        if command == 6:
            dimens = int(input('introdu dimensiunea: '))
            vect = []
            print('introdu elementele: ')
            for i in range(dimens):
                vect.append(int(input()))
            print(cerinta6(dimens, vect))
def teste():
    teste_cerinta1()
    teste_cerinta2()
    teste_cerinta3()
    teste_cerinta4()
    teste_cerinta7()
    teste_cerinta9()
    teste_cerinta10()
    teste_cerinta5()
    teste_cerinta6()
def main():
    teste()
    menu()


if __name__ == '__main__':
    main()

