def create_rational(numerator, denominator):
    '''
    Creates a rational number.

    :param numerator: int, the numerator.
    :param denominator: int, the denominator.
    :return: a rational number
    '''
    return [numerator, denominator]
    # return '{0} / {1}'.format(numerator, denominator)

def get_numerator(rational):
    '''
    Gets the numerator of a rational number

    :param rational: the rational number
    :return: the numerator of rational.
    '''
    return rational[0]

def get_denominator(rational):
    '''

    :param rational:
    :return:
    '''
    return rational[1]

def set_numerator(rational, numerator):
    '''

    :param rational:
    :return:
    '''
    rational[0] = numerator

def set_denominator(rational, denominator):
    '''

    :param rational:
    :param denominator:
    :return:
    '''
    rational[1] = denominator

def to_str(rational):
    '''

    :param rational:
    :return:
    '''
    return '{0} / {1}'.format(get_numerator(rational), get_denominator(rational))