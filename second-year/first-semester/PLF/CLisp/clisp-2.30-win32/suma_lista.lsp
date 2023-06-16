;suma atomilor numerice de la nivel superficial intr o lista
(defun suma(l)
    ; forma COND – forma condițională: permite ramificarea prelucrărilor
    (cond
        ((null l) 0)
        ; NUMBERP – returnează T dacă argumentul e număr
        ((numberp (car l)) (+ (car l) (suma (cdr l))))
        (t (suma (cdr l)))
    )
)



;suma atomilor numerici de la orice nivel intr-o lista
(defun suma_orice_niv(l)
    (cond 
        ((null l) 0)
        ((numberp l) (+ (car l) (suma_orice_niv(cdr l))))
        ((atom l) suma_orice_niv(cdr l))
        (t (+ (suma_orice_niv(car l)) (suma_orice_niv(cdr l))))
    )
)    


;ultimul element al unei liste
(defun ultim(l)
    (cond
        ((atom l) l)
        ((null (cdr l)) (car l))
        (t (ultim(cdr l)))
    )
)


;adaugarea unui element la sfarsitul unei liste
(defun adaug(e l)
    (cond
        ((null l) (list e))
        (t (cons (car l) (adaug e (cdr l))))
    )
)

;o functie care inverseaza o functie liniara!!! ---- complexitate prea mare
(defun invers1(l)
    (cond
        ((null l) ())
        (t (append(invers1(cdr l)) (list(car l))))
    )
)

;inversul cu variabila colectoare
(defun invers_aux(l col)
    (cond 
        ((null l) col)
        (t (invers_aux(cdr l) (cons(car l) col)))
    )
)

(defun invers(l)
    (invers_aux l ())
)


;sa se determine lista perechilor strict crescatoare care se pot forma, intr o lista cu elemente numerice.(se va pastra ordinea elementelor)
;functia auxiliara care va da perechile pentru un element dat
(defun per(e l)
    (cond
        ((null l) ())
        ((< e (car l)) (cons (list e (car l)) (per e (cdr l))))
        (t (per e (cdr l)))
    )
)
(defun perechi(l)
    (cond
        ((null l) ())
        (t (append (per (car l) (cdr l)) (perechi (cdr l))))
    )
)


;se cere sa se dubleze valorile numerice de la orice nivel al listei,pastrand structura ierarhica a acesteia
(defun dublare(l)
    (cond
        ((null l) ())
        ((numberp (car l)) (cons (* 2 (car l)) (dublare (cdr l))))
        ((atom (car l)) (cons (car l) (dublare (cdr l))))
        (t (cons (dublare (car l)) (dublare (cdr l))))
    )
)




