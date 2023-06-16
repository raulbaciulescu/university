from Service.comanda_service import ComandaService
from Service.locatie_service import LocatieService
from Service.masina_service import MasinaService
from Service.undo_redo_service import UndoRedoService


class Console:

    def __init__(self,
                 masina_service: MasinaService,
                 locatie_service: LocatieService,
                 comanda_service: ComandaService,
                 undo_redo_service = UndoRedoService):
        self.__masina_service = masina_service
        self.__locatie_service = locatie_service
        self.__comanda_service = comanda_service
        self.__undo_redo_service = undo_redo_service

    def print_menu(self):
        print('1. CRUD Masini')
        print('2. CRUD Locatii')
        print('3. CRUD Comenzi')
        print('u. Undo')
        print('x. Iesire')

    def run_console(self):

        while True:
            self.print_menu()
            option = input('Alegeti optiunea: ')
            if option == '1':
                self.run_crud_masini()
            elif option == '2':
                self.run_crud_locatii()
            elif option == '3':
                self.run_crud_comenzi()
            elif option == 'u':
                self.__undo_redo_service.do_undo()
            elif option == 'x':
                break
            else:
                print('Comanda invalida!')

    def run_crud_masini(self):
        while True:
            print('1. Create masina.')
            print('2. Delete masina.')
            print('3. Update masina.')
            print('a. Show all masini.')
            print('b. Back')
            option = input('Alegeti optiunea: ')
            if option == '1':
                self.handle_create_masina()
            elif option == '2':
                self.handle_delete_masina()
            elif option == '3':
                self.handle_update_masina()
            elif option == 'a':
                self.handle_show_all_masina()
            elif option == 'b':
                break
            else:
                print('Optiune invalida.')

    def handle_create_masina(self):
        try:
            id_masina = input('ID-ul masinii: ')
            indicativ = input('Indicativul masinii: ')
            nivel_confort = input('Nivelul de confort al masinii (standard, ridicat, premium): ')
            plata_card = input('Suporta plata cu card (da / nu): ')
            model = input('Modelul masinii: ')

            self.__masina_service.create(id_masina, indicativ, nivel_confort, plata_card, model)

            print('Masina a fost adaugata cu succes!')
        except ValueError as ve:
            print(ve)
        except KeyError as ke:
            print(ke)
        except Exception as e:
            print(e)

    def handle_show_all_masina(self):
        for masina in self.__masina_service.get_all():
            print(masina)

    def handle_delete_masina(self):
        try:
            id_masina = input('ID-ul masinii care se va sterge: ')
            self.__masina_service.delete(id_masina)
            print('Masina a fost stearsa!')
        except KeyError as ke:
            print(ke)

    def handle_update_masina(self):
        try:
            id_masina = input('ID-ul masinii: ')
            indicativ = input('Indicativul masinii (gol pt a nu schimba): ')
            nivel_confort = input('Nivelul de confort al masinii (standard, ridicat, premium) (gol pt a nu schimba): ')
            plata_card = input('Suporta plata cu card (da / nu) (gol pt a nu schimba): ')
            model = input('Modelul masinii (gol pt a nu schimba): ')

            self.__masina_service.update(id_masina, indicativ, nivel_confort, plata_card, model)

            print('Masina a fost modificata cu succes!')
        except ValueError as ve:
            print(ve)
        except KeyError as ke:
            print(ke)
        except Exception as e:
            print(e)

    def run_crud_locatii(self):
        while True:
            print('1. Create locatie.')
            print('2. Delete locatie.')
            print('3. Update locatie.')
            print('a. Show all locatii.')
            print('b. Back')
            option = input('Alegeti optiunea: ')
            if option == '1':
                self.handle_create_locatie()
            elif option == '2':
                pass
            elif option == '3':
                pass
            elif option == 'a':
                self.handle_show_all_locatii()
            elif option == 'b':
                break
            else:
                print('Optiune invalida.')

    def handle_create_locatie(self):
        try:
            id_locatie = input('ID-ul locatiei: ')
            nume_strada = input('Numele strazii: ')
            numar = input('Numarul: ')
            bloc = input('Blocul: ')
            scara = input('Scara: ')
            alte_indicatii = input('Alte indicatii: ')
            self.__locatie_service.create(id_locatie, nume_strada, numar, bloc, scara, alte_indicatii)

            print('Locatia a fost adaugata cu succes!')
        except ValueError as ve:
            print(ve)
        except KeyError as ke:
            print(ke)
        except Exception as e:
            print(e)

    def handle_show_all_locatii(self):
        for locatie in self.__locatie_service.get_all():
            print(locatie)

    def run_crud_comenzi(self):
        while True:
            print('1. Create comanda.')
            print('2. Delete comanda.')
            print('3. Update comanda.')
            print('a. Show all comenzi.')
            print('b. Back')
            option = input('Alegeti optiunea: ')
            if option == '1':
                self.handle_create_comanda()
            elif option == '2':
                pass
            elif option == '3':
                pass
            elif option == 'a':
                self.handle_show_all_comenzi()
            elif option == 'b':
                break
            else:
                print('Optiune invalida.')

    def handle_create_comanda(self):
        try:
            id_comanda = input('ID-ul comenzii: ')
            id_masina = input('ID-ul masinii: ')
            id_locatie = input('ID_ul locatiei: ')
            timp_final = input('Timpul final: ')
            cost_km = input('Costul per km: ')
            distanta = input('Distanta parcursa: ')
            status = input('Statusul: ')
            self.__comanda_service.create(id_comanda, id_masina, id_locatie, timp_final, cost_km, distanta, status)

            print('Comanda a fost adaugata cu succes!')
        except ValueError as ve:
            print(ve)
        except KeyError as ke:
            print(ke)
        except Exception as e:
            print(e)

    def handle_show_all_comenzi(self):
        for comanda in self.__comanda_service.get_all():
            print(comanda)
