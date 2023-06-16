from Domain.nr_rational import get_denominator, get_numerator, create_rational


def add_rationals(rational1, rational2):
    '''
    Adds two rational numbers.
    :param rational1: the first rational.
    :param rational2: the second rational.
    :return: rational1+rational2
    '''
    denominator = get_denominator(rational1) * get_denominator(rational2)
    numerator = get_numerator(rational1)*get_denominator(rational2) + \
                get_numerator(rational2)*get_denominator(rational1)
    return create_rational(numerator, denominator)

