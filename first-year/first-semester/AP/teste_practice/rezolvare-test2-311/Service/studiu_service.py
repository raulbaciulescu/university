import json

from Domain.studiu_clinic import StudiuClinic
from Domain.vaccin import Vaccin


class StudiuService:
    '''
    Service pentru studii clinice.
    '''

    def __init__(self, studiu_repository, vaccin_repository, studiu_validator):
        '''
        Instantiaza service-ul.

        :param studiu_repository: repository-ul de studii clinice.
        :param vaccin_repository: repository-ul de vaccinuri.
        :param studiu_validator: validatorul pentru studii clinice.
        '''

        self.__studiu_repository = studiu_repository
        self.__vaccin_repository = vaccin_repository
        self.__studiu_validator = studiu_validator

    def adaugare(self, id_studiu_clinic, id_vaccin, nr_subiecti, procent_gr_vaccinati, procent_gr_placebo):
        '''
        Adauga un studiu clinic.

        :param id_studiu_clinic: id-ul studiului clinic, unic.
        :param id_vaccin: id-ul vaccinului, trebuie sa existe.
        :param nr_subiecti: int > 0.
        :param procent_gr_vaccinati: int intre 0 si 100
        :param procent_gr_placebo: int intre 0 si 100
        :return: None
        :raises ValueError: daca exista erori de validare
        :raises KeyError: daca id-ul exista deja sau daca id_vaccin nu exista
        '''

        studiu_clinic = StudiuClinic(id_studiu_clinic, id_vaccin, nr_subiecti, procent_gr_vaccinati, procent_gr_placebo)
        if self.__vaccin_repository.find_by_id(id_vaccin) is None:
            raise KeyError(f'Nu exista niciun vaccin cu id-ul {id_vaccin}!')
        self.__studiu_validator.validate(studiu_clinic)
        self.__studiu_repository.create(studiu_clinic)

    def get_vaccinuri_ordonate_eficienta(self):
        '''
        Determina ordonarea vaccinurile dupa eficienta lor din studiile clinice.

        :return: o lista de perechi (vaccin, media eficientelor) ordonata crescator dupa media eficientelor.
        '''

        result = {}
        for studiu in self.get_all():
            eficienta = (studiu.procent_gr_placebo - studiu.procent_gr_vaccinati) / studiu.procent_gr_placebo * 100
            if studiu.id_vaccin in result:
                result[studiu.id_vaccin].append(eficienta)
            else:
                result[studiu.id_vaccin] = [eficienta]

        for id_vaccin in result:
            result[id_vaccin] = sum(result[id_vaccin]) / len(result[id_vaccin])

        return sorted([(self.__vaccin_repository.find_by_id(elem[0]), elem[1]) for elem in result.items()], key=lambda x: x[1])

    def get_with_nr_subiecti_mai_mare_decat(self, min_nr_subiecti):
        '''
        Determina toate studiile clinice cu nr de subiecti mai mare decat un numar dat

        :param min_nr_subiecti: numarul minim de subiecti considerat.

        :return: o lista de perechi (studiu clinic, tehnologia vaccinului studiat)
        '''

        result = []
        for studiu in self.get_all():
            if studiu.nr_subiecti > min_nr_subiecti:
                result.append((studiu, self.__vaccin_repository.find_by_id(studiu.id_vaccin).tehnologie))

        return result

    def export_histograma_tehnologii(self, filename):
        '''
        Exporta nr. de studii clinice pentru fiecare tehnologie intr-un fisier JSON.

        :param filename: numele fisierului in care se face exportul.
        :return:
        '''

        result = {}
        for studiu in self.get_all():
            tehnologie = self.__vaccin_repository.find_by_id(studiu.id_vaccin).tehnologie
            if tehnologie in result:
                result[tehnologie] += 1
            else:
                result[tehnologie] = 1

        with open(filename, 'w') as f:
            json.dump(result, f)

    def get_all(self):
        '''
        Intoarce toate studiile clinice.

        :return: o lista cu toate studiile clinice.
        '''
        return self.__studiu_repository.get_all()
