class Vot:
    def __init__(self, loc_alba, loc_neagra):
        '''
        Creeaza un vot.
        :param id_vot: id-ul votului.
        :param loc_alba: locatia bilei albe ('a' sau 'n')
        :param loc_neagra: locatia bilei negru ('a' sau 'n')
        '''
        self.__id_vot = None
        self.__loc_alba = loc_alba
        self.__loc_neagra = loc_neagra

    @property
    def id_vot(self):
        return self.__id_vot

    @property
    def loc_alba(self):
        return self.__loc_alba

    @property
    def loc_neagra(self):
        return self.__loc_neagra

    def get_result(self):
        '''
        Calculeaza rezultatul votului.
        :return: 1 pt vot pozitiv, 0 pt vot nul, -1 pt vot negativ.
        '''
        if self.loc_alba == 'a' and self.loc_neagra == 'n':
            return 1
        elif self.loc_alba == 'n' and self.loc_neagra == 'a':
            return -1
        else:
            return 0

    def __str__(self):
        if self.get_result() == 0:
            return 'Vot nul.'
        elif self.get_result() == 1:
            return 'Vot pozitiv.'
        else:
            return 'Vot negativ.'