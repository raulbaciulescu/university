(defun parcurgere(l val rez)
	(cond
		((null l) nil)
		((equal (CAR l) val) (append rez (list val)))
		(t (append (parcurgere (CADR l) val (append rez (list (CAR l))))
				   (parcurgere (CADDR l) val (append rez (list (CAR l))))
		   )
		)
	)
)

(defun parcurgere_aux(l val)
	(parcurgere l val '())
)




