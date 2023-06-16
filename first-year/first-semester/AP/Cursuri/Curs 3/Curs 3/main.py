from Domain.nr_rational import create_rational
from Tests.run_tests import run_all_tests
from UI.console import run_console


def main():

    total = create_rational(0, 1)
    run_console(total)

    run_all_tests()

main()