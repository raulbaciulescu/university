from Service.vot_service import VotService


class Console:
    def __init__(self, vot_service: VotService):
        self.__vot_service = vot_service

    def __show_menu(self):
        print('1. Adauga vot')
        print('2. Rezultat vot')
        print('3. Resetare voturi')
        print('x. Iesire')



    def run_console(self):
        while True:
            self.__show_menu()
            option = input('Alegeti optiunea: ')
            if option == '1':
                self.__handle_add_vot()
            elif option == '2':
                self.__handle_rezultat_vot()
            elif option == '3':
                self.__hanfle_reset()
            elif option == 'x':
                break
            else:
                print('Optiune invalida!')

    def __handle_rezultat_vot(self):
        nr_pozitive = 0
        nr_negative = 0
        nr_nule = 0
        for vot in self.__vot_service.get_all():
            if vot.get_result() == 0:
                nr_nule += 1
            elif vot.get_result() == 1:
                nr_pozitive += 1
            else:
                nr_negative += 1

        print(f'Pozitive: {nr_pozitive}, negative: {nr_negative}, nule: {nr_nule}')

    def __handle_add_vot(self):
        try:
            loc_alba = input('Locatia bilei albe: ')
            loc_neagra = input('Locatia bilei negre: ')
            self.__vot_service.add_vot(loc_alba, loc_neagra)
            print('Vot adaugat cu succes!')
        except Exception as e:
            print(e)

    def __hanfle_reset(self):
        self.__vot_service.rese