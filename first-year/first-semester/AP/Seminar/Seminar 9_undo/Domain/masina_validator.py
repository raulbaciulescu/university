from Domain.masina import Masina


class MasinaValidator:

    def validate(self, masina: Masina):

        errors = []
        if masina.nivel_confort not in ['standard', 'ridicat', 'premium']:
            errors.append('Nivelul de confort trebuie sa fie unul dintre standard, ridicat sau premium!')

        if errors != []:
            raise ValueError(errors)