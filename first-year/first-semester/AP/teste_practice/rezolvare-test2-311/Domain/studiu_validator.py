from Domain.studiu_clinic import StudiuClinic


class StudiuClinicValidator:

    def validate(self, studiu_clinic: StudiuClinic):
        '''
        Valideaza un studiu clinic.

        :param studiu_clinic: studiul clinic care se va valida
        :return: None
        :raises ValidationError: daca studiul clinic nu are campurile completate valid.
        '''

        errors = []
        '''
        if studiu_clinic.nr_subiecti <= 0:
            errors.append('Numarul de subiecti trebuie sa fie > 0!')
        '''
        if studiu_clinic.procent_gr_vaccinati not in range(0, 101):
            errors.append('Procentul de imbolnaviti in grupul vaccinat trebuie sa fie intre 0 si 100!')
        if studiu_clinic.procent_gr_placebo not in range(0, 101):
            errors.append('Procentul de imbolnaviti in grupul placebo trebuie sa fie intre 0 si 100!')

        if errors:
            raise ValueError(errors)