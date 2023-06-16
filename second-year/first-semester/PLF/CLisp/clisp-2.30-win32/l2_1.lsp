(defun parcurg_st(arb nv nm)
    (cond
        ((null arb) nil)
        ((= nv (+ 1 nm)) nil)
        (t (cons (car arb) (cons (cadr arb) (parcurg_st (cddr arb) (+ 1 nv) (+ (cadr arb) nm)))))
    )
)
(defun stang(arb)
    (parcurg_st (cddr arb) 0 0)
)


(defun parcurg_dr(arb nv nm)
    (cond
        ((null arb) nil)
        ((= nv (+ 1 nm)) arb)
        (t (parcurg_dr (cddr arb) (+ 1 nv) (+ (cadr arb) nm)))
    )
)
(defun drept(arb)
    (parcurg_dr (cddr arb) 0 0)
)


(defun parcurgere_aux(l nod rez)
    (cond
        ((null l) nil)
        ((equal (car l) nod) (append rez (list nod)))
        (t (append (parcurgere_aux (stang l) nod (append rez (list (car l))))
				   (parcurgere_aux (drept l) nod (append rez (list (car l))))
            )
        )
    )   
)

(defun parcurgere(l nod)
	(parcurgere_aux l nod '())
)
;(A 2 B 1 D 2 F 0 G 0 C 1 E 1 H 0)
;(A 2 B 2 C 1 I 0 F 1 G 0 D 2 E 0 H 0)