(defun f(l)
	(car l)
)

;suma atomilor de la nivelul superficial al unei liste neliniare
;(suma '(1 (2 (3 4) 5) 1)) -> 2
(defun suma(l)
	(cond
		((null l ) 0)
		((numberp (car 1)) (+ (car l) (suma (cdr l))))
		(t (suma (cdr l)))
	)
)  

;suma atomilor de la orice nivel al unei liste neliniare
(defun suma2(l)
    (cond
        ((null l) 0)
        ((numberp (car l)) (+ (car l) (suma2(cdr l))))
        ((atom (car l)) (suma2(cdr l)))
        (t (+(suma2(car l)) (suma2(cdr l))))
    )
)


;Metoda variabilei colectoare. Să se definească o funcție care inversează o listă liniară.
(defun invers(l)
    (cond
        ((null l)())
        (t(append (invers(cdr l)) (list(car l))))
    )
)