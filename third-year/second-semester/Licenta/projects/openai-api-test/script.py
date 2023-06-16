with open('message.txt', 'r') as file:
    # Citeste toate liniile din fișier
    lines = file.readlines()

# Modifică fiecare linie
modified_lines = []
for line in lines:
    # Elimină orice spații suplimentare din jurul liniei
    stripped_line = line.strip()

    # Transformă primul caracter în literă mică
    lowercase_first_char = stripped_line[0].lower() + stripped_line[1:]

    # Adaugă linia modificată în lista rezultat
    modified_lines.append(lowercase_first_char)

# Deschide fișierul de ieșire în modul scriere
with open('message_new.txt', 'w') as file:
    # Scrie liniile modificate în fișierul de ieșire
    file.write('\n'.join(modified_lines))