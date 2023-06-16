from Domain.nr_rational import create_rational
from Logic.add_logic import add_rationals

def test_add_rationals():
    r1 = create_rational(2, 3)
    r2 = create_rational(4, 5)
    result = create_rational(22, 15)
    assert add_rationals(r1, r2) == result