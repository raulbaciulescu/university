from Logic.crud import add_prajitura
from Tests.run_all import run_all_tests
from UI.console import run_console


def main():
    prajituri = []
    add_prajitura(prajituri, 1, 'aaab', 'dgs', 325, 1000,  2020)
    add_prajitura(prajituri, 2, 'baaa', 'ggg', 345, 500,  2020)
    run_console(prajituri)

run_all_tests()
main()