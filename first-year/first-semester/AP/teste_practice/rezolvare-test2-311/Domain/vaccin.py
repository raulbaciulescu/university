from Domain.entity import Entity


class Vaccin(Entity):

    def __init__(self, id_vaccin, nume, tehnologie):
        '''
        Creeaza un vaccin.
        :param id_vaccin: id-ul vaccinului
        :param nume: numele (nenul)
        :param tehnologie: una dintre mRNA, virus inactiv sau virus atenuat
        '''

        super(Vaccin, self).__init__(id_vaccin)
        self.__nume = nume
        self.__tehnologie = tehnologie

    @property
    def nume(self):
        return self.__nume

    @property
    def tehnologie(self):
        return self.__tehnologie

    def __str__(self):
        return f'{self.id_entity} - nume: {self.nume}, tehnologie: {self.tehnologie}'