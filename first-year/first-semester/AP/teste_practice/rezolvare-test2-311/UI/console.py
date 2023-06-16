class Console:

    def __init__(self, vaccin_service, studiu_service):
        self.__vaccin_service = vaccin_service
        self.__studiu_service = studiu_service

    def run_console(self):
        while True:
            self.__print_menu()
            opt = input('Optiunea: ')

            if opt == '1':
                self.__handle_add_vaccin()
            elif opt == 'av':
                self.__show_all(self.__vaccin_service.get_all())
            elif opt == '2':
                self.__handle_add_studiu_clinic()
            elif opt == 'as':
                self.__show_all(self.__studiu_service.get_all())
            elif opt == '3':
                self.__handle_vaccinuri_ordonate_eficienta()
            elif opt == '4':
                self.__handle_studii_nr_min_subiecti()
            elif opt == '5':
                self.__handle_export_json()
            elif opt == 'x':
                break
            else:
                print('Optiune invalida, reincearca!')

    def __print_menu(self):
        print('1. Adaugare vaccin')
        print('av. Afisare vaccinuri')
        print('2. Adaugare studiu clinic')
        print('as. Afisare studii clinice')
        print('3. Afisare vaccinuri ordonate cresc dupa eficienta')
        print('4. Afisare studii clinice cu nr subiecti > nr citit')
        print('5. Export statistici tehnologii')
        print('x. Iesire')
        print('x. Iesire')

    def __handle_add_vaccin(self):
        try:
            id_vaccin = input('Id-ul vaccinului: ')
            nume = input('Numele vaccinului, nenul: ')
            tehnologie = input('Tehnologia vaccinului (mRNA, virus inactiv, virus atenuat): ')

            self.__vaccin_service.adaugare(id_vaccin, nume, tehnologie)
        except Exception as ex:
            print('Eroare, reincercati:', ex)

    def __show_all(self, entitati):
        for e in entitati:
            print(e)

    def __handle_add_studiu_clinic(self):
        try:
            id_studiu = input('Id-ul studiului clinic: ')
            id_vaccin = input('Id-ul vaccinului, trebuie sa existe: ')
            nr_subiecti = int(input('Numarul de subiecti, intreg > 0: '))
            procent_gr_vaccinati = int(input('Procentul de imbolnaviti in grupul vaccinat, intreg intre 0 si 100: '))
            procent_gr_placebo = int(input('Procentul de imbolnaviti in grupul placebo, intreg intre 0 si 100: '))

            self.__studiu_service.adaugare(id_studiu, id_vaccin, nr_subiecti, procent_gr_vaccinati, procent_gr_placebo)
        except Exception as ex:
            print('Eroare, reincercati:', ex)

    def __handle_vaccinuri_ordonate_eficienta(self):
        for result in self.__studiu_service.get_vaccinuri_ordonate_eficienta():
            print(f'Vaccinul: {result[0]}, Media eficientelor: {result[1]}.')

    def __handle_studii_nr_min_subiecti(self):
        nr_min_subiecti = int(input('Dati numarul minim de subiecti: '))
        for result in self.__studiu_service.get_with_nr_subiecti_mai_mare_decat(nr_min_subiecti):
            print(f'Studiul clinic {result[0]} cu tehnologia vaccinului studiat={result[1]}')

    def __handle_export_json(self):
        filename = input('Dati fisierul in care se va face exportul: ')
        self.__studiu_service.export_histograma_tehnologii(filename)