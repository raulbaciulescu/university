from Domain.vaccin import Vaccin


class VaccinService:
    '''
    Service pentru vaccinuri.
    '''

    def __init__(self, vaccin_repository, vaccin_validator):
        '''
        Instantiaza service-ul.

        :param vaccin_repository: repository-ul de vaccinuri.
        :param vaccin_validator: validatorul pentru vaccinuri.
        '''

        self.__vaccin_repository = vaccin_repository
        self.__vaccin_validator = vaccin_validator

    def adaugare(self, id_vaccin, nume, tehnologie):
        '''
        Adauga un vaccin.

        :param id_vaccin: id-ul vaccinului, unic.
        :param nume: numele vaccinului, nenul.
        :param tehnologie: tehnologia folosita, una dintre mRNA, virus inactiv, virus atenuat
        :return: None
        :raises ValueError: daca exista erori de validare
        :raises KeyError: daca id-ul exista deja
        '''

        vaccin = Vaccin(id_vaccin, nume, tehnologie)
        self.__vaccin_validator.validate(vaccin)
        self.__vaccin_repository.create(vaccin)

    def get_all(self):
        '''
        Intoarce toate vaccinurile.

        :return: o lista cu toate vaccinurile.
        '''
        return self.__vaccin_repository.get_all()
