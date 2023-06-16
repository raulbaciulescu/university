from main import *

def teste_cerinta1():
    assert cerinta1('Ana are mere rosii si galbene') == 'si'



def teste_cerinta2():
    assert cerinta2(1, 5, 4, 1) == 5.0

def teste_cerinta3():
    assert cerinta3([1, 0, 2, 0, 3], [1, 2, 0, 3, 1], 5) == 4

def teste_cerinta4():
    dict = cerinta4('ana are ana are mere rosii ana')
    assert dict['ana'] == 3
    assert dict['are'] == 2

def teste_cerinta7():
    assert(cerinta7(6, [7,4,6,3,9,1], 2) == 7)

def teste_cerinta9():
    matrix = [[0, 2, 5, 4, 1],
                [4, 8, 2, 3, 7],
                [6, 3, 4, 6, 2],
                [7, 3, 1, 8, 3],
                [1, 5, 7, 9, 4]]
    list = (1, 1, 3, 3)
    assert(cerinta9(matrix, list) == 38)


def teste_cerinta10():
    matrix = [[0,0,0,1,1],
                [0,1,1,1,1],
                [0,0,1,1,1]]
    assert(cerinta10(matrix) == 1)

def teste_cerinta5():
    assert cerinta5([1,2,3,4,2]) == 2


def teste_cerinta6():
    assert cerinta6(11, [2,8,7,2,2,5,2,3,1,2,2]) == 2