class VotValidator:

    def validate(self, vot):

        erori = []
        if vot.loc_neagra not in ['a', 'n']:
            erori.append('Locatia bilei negre este incorecta')
        if vot.loc_alba not in ['a', 'n']:
            erori.append('Locatia bilei albe este incorecta')

        if len(erori) > 0:
            raise ValueError(erori)