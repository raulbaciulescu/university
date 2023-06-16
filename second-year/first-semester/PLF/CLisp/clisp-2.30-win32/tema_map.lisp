
;Sa se construiasca o functie care verifica daca un atom e membru al
;unei liste nu neaparat liniara.

;(load 'tema_map)
;(apare '((1) 2 3 (4 5 (6 7)) 8) '5) 
;(apare '((1) 2 3 (4 5 (6 7)) 8) '7) 
;(apare '((1) 2 3 (4 5 (6 7)) 8) '0) 



(defun apare(l e)
	(cond
		((equal l e) 1)
		((atom l) 0)
		(t (apply #'max (mapcar (lambda(l) (apare l e)) l)))
	)
)