from Domain.entity import Entity


class StudiuClinic(Entity):

    def __init__(self, id_studiu, id_vaccin, nr_subiecti, procent_gr_vaccinati, procent_gr_placebo):
        '''
        Creeaza un studiu clinic.

        :param id_studiu: id-ul studiului.
        :param id_vaccin: id-ul vaccinului, trebuie sa existe.
        :param nr_subiecti: nr de subiecti, int > 0.
        :param procent_gr_vaccinati: int intre 0 si 100.
        :param procent_gr_placebo: int intre 0 si 100.
        '''

        super().__init__(id_studiu)
        self.__id_vaccin = id_vaccin
        self.__nr_subiecti = nr_subiecti
        self.__procent_gr_vaccinati = procent_gr_vaccinati
        self.__procent_gr_placebo = procent_gr_placebo

    @property
    def id_vaccin(self):
        return self.__id_vaccin

    @property
    def nr_subiecti(self):
        return self.__nr_subiecti

    @property
    def procent_gr_vaccinati(self):
        return self.__procent_gr_vaccinati

    @property
    def procent_gr_placebo(self):
        return self.__procent_gr_placebo

    def __str__(self):
        return f'{self.id_entity}: id vaccin: {self.id_vaccin}, nr subiecti: {self.nr_subiecti}, gr vaccinati: {self.procent_gr_vaccinati}, gr placebo: {self.procent_gr_placebo}'