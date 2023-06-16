from Domain.entitate import Entitate


class Locatie(Entitate):

    def __init__(self, id_locatie, nume_strada, numar, bloc, scara, alte_indicatii):
        # TODO: make these properties
        super().__init__(id_locatie)
        self.nume_strada = nume_strada
        self.numar = numar
        self.bloc = bloc
        self.scara = scara
        self.alte_indicatii = alte_indicatii

    def __str__(self):
        return f'{self.id_entitate} - strada:{self.nume_strada} nr. {self.numar}, bloc {self.bloc}, scara {self.scara}, indicatii: {self.alte_indicatii}'