from Logic.crud import *
from Logic.operations import *
from UI.console import *
import time

cheltuieli = []

list_of_commands_print = {"adauga": ["Adauga o cheltuiala noua in lista.","adauga,<id>,<nr_apartament>,<suma>,<data>,<tip (ex. intretinere, apa, altele etc.)>"],
                          "sterge": ["Sterge o cheltuiala din lista.", "sterge,<id>"],
                          "modifica": ["Modifica o cheltuiala din lista. Trebuie completate doar campurile care se doresc a fi modificate, dar id este obligatoriu. Exemplu: modifica,3,,150,, Va modifica la cheltuiala 3 suma in 150","modifica,<id>,<nr_apartament>,<suma>,<data>,<tip (ex. intretinere, apa, altele etc.)>"],
                          "sterge_per_apartament": ["Sterge toate cheltuielile pentru un apartament dat.","sterge_per_apartament,<nr_apartament>"],
                          "adauga_valoare_la_data": ["Adauga o valoare la toate cheltuielile din data specificata","adauga_valoare_la_data,<valoare>,<data>"],
                          "determinare_max_cheltuiala_per_tip": ["Determina cheltuielile cele mai mari pentru fiecare tip de cheltuiala.","determinare_max_cheltuiala_per_tip"],
                          "ordonare_descrescatoare_dupa_suma": ["Ordoneaza descrescator cheltuielile dupa suma.", "ordonare_descrescatoare_dupa_suma"],
                          "afisare_sume_lunar_per_apartament": ["Afiseaza suma totala de platit pentru fiecare apartament intr-o luna.", "afisare_sume_lunar_per_apartament"],
                          "undo": ["Undo ultimul set de comenzi date.", "undo"],
                          "x": ["Iesire", "x"]}

def print_greeting():
    print("Bine ați venit la baza de date cu lista de cheltuieli a asociației de proprietari.")
    print("Trebuie sa introduceti una dintre comenzile urmatoare sau poti adauga mai multe comenzi separate prin ; exemplu: <comanda1>;<comanda2>;<comanda3>")
    print("Comenzile trebuie formatate in urmatorul mod:")
    for print_command in list_of_commands_print:
        print(list_of_commands_print[print_command][0])
        print(list_of_commands_print[print_command][1])
        print()


def print_menu():
    print("Introduce.")


def run_terminal():
    while True:
        print_greeting()
        print_menu()
        inp = get_input("Comanda")
        new_inp = inp.split(";")
        for command in new_inp:
            new_command = command.split(",")
            if new_command[0] == "adauga":
                try:
                    id = int(new_command[1])
                    try:
                        nr_apartament = int(new_command[2])
                        suma = int(new_command[3])
                        data = new_command[4]
                        tip = new_command[5]

                        cheltuiala = create_cheltuiala_out_of_data(id, nr_apartament, suma, data, tip)
                        add_cheltuiala(cheltuieli, cheltuiala)
                        print("Cheltuiala adaugată cu succes.")
                    except Exception as e:
                        print(f"Numarul apartamentului introdus pentru <{new_command[0]}> nu este valid.")
                except Exception as e:
                    print(f"Id-ul introdus pentru <{new_command[0]}> nu este valid.")
                time.sleep(1)
            elif new_command[0] == "sterge":
                try:
                    id = int(new_command[1])

                    sters = stergere_cheltuiala(cheltuieli, id)
                    if sters:
                        print("Cheltuiala ștearsă cu succes.")
                    else:
                        print(f"Id-ul introdus pentru <{new_command[0]}> nu exista.")
                except Exception as e:
                    print(f"Id-ul introdus pentru <{new_command[0]}> nu este valid.")
                time.sleep(1)
            elif new_command[0] == "modifica":
                try:
                    id = int(new_command[1])
                    try:
                        nr_apartament_prim = int(new_command[2])
                        if nr_apartament_prim != "":
                            nr_apartament_fin = int(nr_apartament_prim)
                            nr_apartament = nr_apartament_fin
                        else:
                            nr_apartament = nr_apartament_prim
                        suma = int(new_command[3])
                        data = new_command[4]
                        tip = new_command[5]

                        modificare_cheltuiala(cheltuieli, id, nr_apartament, suma, data, tip)
                        print("Cheltuiala modificată cu succes.")
                    except Exception as e:
                        print(f"Numarul apartamentului introdus pentru <{new_command[0]}> nu este valid.")
                except Exception as e:
                    print(f"Id-ul introdus pentru <{new_command[0]}> nu este valid.")
                time.sleep(1)
            elif new_command[0] == "sterge_per_apartament":
                try:
                    nr_apartament = int(new_command[1])

                    sters = sterge_all(cheltuieli, nr_apartament)

                    if sters:
                        print(f"Toate cheltuielile apartamentului {nr_apartament} au fost șterse cu succes.")
                    else:
                        print(f"Numarul apartamentului introdus pentru <{new_command[0]}> nu exista.")
                except Exception as e:
                    print(f"Numarul apartamentului introdus pentru <{new_command[0]}> nu este valid.")
                time.sleep(2)
            elif new_command[0] == "adauga_valoare_la_data":
                try:
                    value = int(new_command[1])
                    date = new_command[2]

                    added_value = adauga_valoare_la_data(cheltuieli, value, date)

                    if added_value:
                        print("Valoarea tuturor sumelor a fost modificata cu succes.")
                    else:
                        print(f"Data introdusa pentru <{new_command[0]}> nu exista.")
                except Exception as e:
                    print(f"Data introdus pentru <{new_command[0]}> nu este valida.")
                time.sleep(2)
            elif new_command[0] == "determinare_max_cheltuiala_per_tip":
                result = get_max_cheltuiala_for_type(cheltuieli)
                print(result)
                time.sleep(2)
            elif new_command[0] == "ordonare_descrescatoare_dupa_suma":
                result = sort_desc_by_sum(cheltuieli)
                print(result)
                time.sleep(2)
            elif new_command[0] == "afisare_sume_lunar_per_apartament":
                result = get_sum_per_apartament(cheltuieli)
                print(result)
                time.sleep(2)
            elif new_command[0] == "undo":
                pass
            elif new_command[0] == "x":
                break
        time.sleep(5)


run_terminal()