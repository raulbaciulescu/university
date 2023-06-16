import sqlite3
from Domain.vot import Vot

class VotRepository:

    def __init__(self):
        self.__storage = {}
        self.connection = sqlite3.connect("folder_voturi.db")

        self.connection.execute("""CREATE TABLE IF NOT EXISTS
        voturi(id INTEGER PRIMARY KEY AUTOINCREMENT, loc_alba TEXT, loc_neagra TEXT)""")
        self.connection.commit() #se va da commit dupa fiecare operatiune asupra bazei de date

    def get_all(self):
        cursor = self.connection.execute("SELECT * FROM voturi")
        '''
        id = row[0]     loc_alba = row[1]        loc_neagra = row[2]
    ex: 1                       a                  n 
        '''
        for row in cursor:
            vot = Vot(row[1], row[2])
            self.__storage[row[0]] = vot

    def read(self, id_vot=None):
        self.get_all()

        if id_vot is None:
            return list(self.__storage.values())

        if id_vot in self.__storage:
            return self.__storage[id_vot]

        return None

    def delete(self, id_vot):
        if self.read(id_vot) is None:
            raise KeyError(f"Votul cu ID-ul {id_vot} nu exista!")

        self.connection.execute(f"DELETE FROM voturi WHERE id = {id_vot}")

    def upsert(self, vot):
        self.get_all()

        if vot.id_vot is None:
            self.connection.execute(f"INSERT INTO voturi (loc_alba, loc_neagra) VALUES ('{vot.loc_alba}', '{vot.loc_neagra}')")
            self.connection.commit()

        else:
            self.connection.execute(f"UPDATE voturi SET loc_alba = '{vot.loc_alba}', loc_neagra = '{vot.loc_neagra}' WHERE id = '{vot.id_vot}'")
            self.connection.commit()
    '''
    def reset(self):
        self.connection.execute(f"DELETE FROM voturi")
    '''
