(defun inaltime(arb nivel nod)
    (cond
        ((and (atom arb) (equal arb nod)) nivel)
        ((atom arb) -1)
        (t (apply #'max(mapcar #'(lambda (v) (inaltime v (+ 1 nivel) nod)) arb)))
    )
)
;(a (b (g)) (c (d (e)) (f)))
(defun in(arb nod)
    (inaltime arb 0 nod)
)

(defun inlocuire (l e e1)
    (cond
        ((and (atom l) (equal l e)) e1)
        ((atom l) l)
        (t (mapcar #'(lambda (v) (inlocuire v e e1)) l))
    )
)