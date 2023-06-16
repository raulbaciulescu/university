(defun apare(e l)
	(cond
		((null l) nil)
		((equal (car l) e) t)
		(t (apare e (cdr l)))
	)
)

(defun diferenta(a b)
	(cond
		((null a) nil)
		((apare (car a) b) (diferenta (cdr a) b))
		(t (append (list (car a)) (diferenta (cdr a) b)))
	)
)

(defun invers(l)
	(cond
		((null l) nil)
		((atom (car l)) (append (invers (cdr l)) (list (car l))))
		(t (append (invers (cdr l)) (list (invers (car l)))))
	)
)

(defun numara(l)
	(cond
		((null l) 0)
		(t (+ 1 (numara (cdr l))))
	)
)

(defun primele_aux(l)
	(cond
		((null l) nil)
		((and (listp (car l)) (= (mod (numara (car l)) 2) 1)) (append (list (caar l)) (primele_aux (cdr l))))
		(t (primele_aux (cdr l)))
	)
)

(defun primele(l)
	(cond
		((= (mod (numara l) 2) 1) (append (list (car l)) (primele_aux l)))
		(t (primele_aux l))
	)
)

(defun suma(l)
	(cond
		((null l) 0)
		((numberp (car l)) (+ (car l) (suma (cdr l))))
		(t (suma (cdr l)))
	)
)
