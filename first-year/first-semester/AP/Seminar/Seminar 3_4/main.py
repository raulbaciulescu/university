from Logic.crud import add_prajitura
from Tests.run_all import run_all_tests
from UI.console import run_console


def main():
    prajituri = []
    add_prajitura(prajituri, '1', 'aaab', 'dgs', 325, 1000,  2020)
    add_prajitura(prajituri, '2', 'baaa', 'ggg', 345, 500,  2020)
    add_prajitura(prajituri, '3', 'baaa', 'ggg', 345, 700,  2020)
    add_prajitura(prajituri, '4', 'baaa', 'ggg', 345, 400,  2019)
    add_prajitura(prajituri, '5', 'baaa', 'ggg', 345, 800,  2019)
    add_prajitura(prajituri, '6', 'baaa', 'ggg', 345, 900,  2018)
    add_prajitura(prajituri, '7', 'baaa', 'ggg', 345, 1000,  2021)
    prajituri = run_console(prajituri)

run_all_tests()
main()