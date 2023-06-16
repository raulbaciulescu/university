class VaccinValidator:

    def validate(self, vaccin):
        '''
        Valideaza un vaccin.

        :param vaccin: vaccinul care se va valida
        :return: None
        :raises ValidationError: daca vaccinul nu are campurile completate valid.
        '''

        errors = []
        if vaccin.nume == '':
            errors.append('Numele vaccinului nu poate sa fie gol!')
        if vaccin.tehnologie not in ['mRNA', 'virus inactiv', 'virus atenuat']:
            errors.append('Tehnologia vaccinului trebuie sa fie una dintre: mrNA, virus inactiv, virus atenuat!')

        if errors:
            raise ValueError(errors)