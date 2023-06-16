#!/bin/bash

if [ $# -eq 0 ]; then
     echo Numar insuficient de argumente.
     echo Utilizare: ./practic.sh file1 file2 ...
     exit 1
fi

while read -p "Introdu cuvant: " W; do
    if [ $W == "stop" ];then
         break
     fi

     for F in $*; do
         if file $F | grep -q 'ASCII text'; then
             echo "Fisierul " $F
             NR=$(grep -c ''$W'' $F)
             echo "Total linii " $NR
             if [ $(($NR % 2)) -eq 0 ]; then
                 echo "e par"
                 LINIE=$(grep ''$W'' $F | tail -q -n 1)

                 echo "Ultima linie e " $LINIE
                 echo $LINIE >> $F
                 echo "Am adaugat o linie."
            fi
         else
             echo "Fisier: " $F
             echo "Fisierul dat nu este de tip text"
         fi
     done
done

