; (car l) - radacina arborelui
; (cadr l) - subarborele stang
; (caddr l) - subarborele drept


;parcurgerea inordine
;(inordine '(a (b () (f)) (d (e)))) = (b f a e d)
(defun inordine(l)
    (cond
        ((null l) ())
        (t (append (inordine (cadr l)) (list (car l)) (inordine (caddr l))))
    )
)

;sa se determine calea de la radacina la un nod dat
;(setq arb '(a (b () (f)) (d (e () (k)) (l))))
;(cale 'm arb) → NIL
;(cale 'f arb) → (a b f)
;(cale 'k arb) → (a d e k)

(defun cale(e l)
    (cond
        ((null l) ())
        ((equal e (car l)) (list e))
        ((apare e (extrageAtomi (cadr l))) (cons (car l) (cale e (cadr l))))
        ((apare e (extrageAtomi (caddr l))) (cons (car l) (cale e (caddr l))))
        (t ())
    )
)
;(a (b () (f)) (d (e () (k)) (l)))) -> (a b f d e k l)
(defun extrageAtomi(x)
		(cond 
			((atom x) (list x))
			(t (MAPCAN #'extrageAtomi x))
		)
)
(defun apare(e l)
	(cond
		((null l) nil)
		((equal e (car l)) t)
		(t (apare e (cdr l)))
	)
)



