;sa se defineasca o functie care primeste ca parametru o lista neliniara si returneaza nil daca lista
;are cel putin un atom numeric la nivel superficial si T in caz contrar.
(defun f(l)
    (cond
        ((null l) t)
        (
            ((lambda (v) 
                (cond
                    ((numberp v) t)
                    (t nil)
                )
            )
            (car l)
            ) nil
        )
        (t (f(cdr l)))
    )
)