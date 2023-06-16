
;(a (b (g)) (c (d (e)) (f)))
(defun solutie(arb nivel)
    (cond
        ((and (atom arb) (= (mod nivel 2) 0)) (list arb))
        ((atom arb) nil)
        (t (mapcan #'(lambda (v) (solutie v (+ 1 nivel))) arb))
    )
)

(defun sol (arb)
    (solutie arb 1)
)