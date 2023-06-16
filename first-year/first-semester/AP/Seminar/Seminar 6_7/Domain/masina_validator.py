class MasinaValidator:

    def validate(self, masina):

        erori = []
        if masina.nivel_confort not in ['standard', 'ridicat', 'premium']:
            erori.append('Nivelul de confort trebuie sa fie unul dintre standard,  ridicat sau premium!')

        if len(erori) > 0:
            raise ValueError(erori)