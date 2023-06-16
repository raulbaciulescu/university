
;calculeaza suma cifrelor unui numar
;x: numarul caruia ii calculam suma cifrelor





(defun suma_cifre(x)
    (cond
        ((equal x 0) 0)
        (t (+ (mod x 10) (suma_cifre (/ (- x (mod x 10)) 10))))
    )
)




;(A 2 (B 31 F (D 102 5 T (66) E) (D 10 (E R 51)) 99))
;(sol '(A 2 (B 31 F (D 102 5 T (66) E) (D 10 (E R 51)) 99)))
;(A 2 (B 31 F (D 3 5 T (66) E) (D 1 (E R 51)) 99)).
;(A 2 (B 31 F (D 3 5 T (66) E) (D 1 (E R 51)) 99))
(defun solutie(l nivel)
    (cond
        ((and (numberp l) (= (mod l 2) 0) (= (mod nivel 2) 1)) (suma_cifre l))
        ((atom l) l)
        (t (mapcar #'(lambda (v) (solutie v (+ nivel 1))) l))
    )
)

(defun sol(l)
    (solutie l 0)
)




;269
;(a (1 (2 b)) (c (d)))
;a) k=2 => (a (0 (2 b)) (0 (d))) b) k=1 => (0 (1 (2 b)) (c (d))) c) k=4 =>lista nu se modifică
(defun solutie1(l nivel k)
    (cond
        ((and (atom l) (equal nivel k)) 0)
        ((atom l) l)
        (t (mapcar #'(lambda (v) (solutie1 v (+ nivel 1) k)) l))
    )
)

(defun sol1(l k)
    (solutie1 l 0 k)
)