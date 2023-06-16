;atomii nenumerici rămân nemodificaţi iar cei numerici îşi dublează valoarea
;modificarea trebuie făcută la toate nivelurile.

(defun modif(l)
    (cond
        ((numberp l) (* 2 l))
        ((atom l) l)
        (t (mapcar #'modif l))
    )
)

;lungimea celei mai lungi subliste dintr-o lista l
;(LGM '(1 (2 (3 4) (5 (6)) (7)))) va produce 4
(defun lgm(l)
    (cond 
        ((atom l) 0)
        (t (max (length l) (apply #'max(mapcar #'lgm l))))
    )
)


;calculeaza triplul unui nr
(defun triple1(x) (* x 3))

;calculeaza triplul elementelor dintr o lista, neliniara
(defun triple2(x)
    (cond
        ((numberp x) (* x 3))
        ((atom x) x)
        (t (mapcar #'triple x))
    )
)


;produsul elementelor dintr-o lista
;(1 a (2 b) 3 c)
;(suma '(1 a (2 b) 3 c))
(defun product(x)
    (cond
        ((numberp x) x)
        ((atom x) 1)
        (t (apply '* (mapcar #'product x)))
    )
)

;suma numerelor cu mapcar
(defun suma(x)
    (cond
        ((numberp x) x)
        ((atom x) 0)
        (t (apply '+ (mapcar #'suma x)))
    )
)


;sa se returneze nodurile de pe niveluri pare intr un arbore n-ar
;(noduri '(noduri '((A (B (D (G) (H)) (E (I)))) (C (F (J (L)) (K))))))
(defun noduri_pare(arb nivel)
    (cond
        ((and (atom arb) (= (mod nivel 2) 0)) 1)
        ((atom arb) 0)
        (t (apply '+ (mapcar #'(lambda (a) (noduri_pare a (+ 1 nivel))) arb)))
    )
)

(defun noduri(arb)
    (noduri_pare arb 0)
)


;nr sublistelor in care primul atom numeric este 5
;(A 5 (B C D) 2 1 (G (5 H) 7 D) 11 14)
(defun numara(l)
    (cond
        ((atom l) 0)
        ((verif l) (+ 1 (apply '+ (mapcar #'numara l))))
        (t (apply '+ (mapcar #'numara l)))
    )
)
;verifica daca lista contine numar
(defun contine_numar(l)
    (cond
        ((null l) nil)
        ((numberp (car l)) t)
        ((atom (car l)) (contine_numar (cdr l)))
        (t (or (contine_numar (car l)) (contine_numar (cdr l))))
    )
)

;extrage numerele dintr o lista neliniara care are si litere
(defun transform(l)
    (cond
        ((null l) nil)
        ((numberp (car l)) (cons (car l) (transform (cdr l))))
        ((atom (car l)) (transform (cdr l)))
        (t (append (transform (car l)) (transform (cdr l))))
    )
)
;extrage numerele dintr o lista neliniara care are si litere....cu functie map
(defun transform_map (l)
    (cond
        ((numberp l) (list l))
        ((atom l) nil)
        (t (mapcan #'transform_map l))
    )
)
;verifica daca primul element al listei e 5, lista neliniara
(defun verif (l)
    (cond
        ((null (transform_map l)) nil)
        ((equal 5 (car (transform_map l))) t)
        (t nil)
    )
)








