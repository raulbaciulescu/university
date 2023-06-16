
USE FotoShop
GO

SELECT * FROM Produse
SELECT * FROM Clienti
SELECT * FROM Categorii
SELECT * FROM Achizitii
SELECT * FROM Recenzii


SELECT * FROM Produse 
WHERE Pret > 9000;

SELECT Cid, AVG(Pret) AS Pret_mediu
FROM Produse 
GROUP BY Cid  
HAVING AVG(Pret) > 20000 OR SUM(Pret) BETWEEN 12000 AND 1200000



select c.Cid, p.Denumire_Produs, p.Pret, c.Denumire_Categorie
from Categorii c, Produse p
where c.Cid = p.Cid


select  c.Cid, p.Denumire_Produs, p.Pret
from Categorii c inner join Produse p on
c.Cid = p.Cid


select r.Rid, r.Numar_Stele, c.Cid, c.Nume_Client
from Recenzii r inner join Clienti c on
c.Cid = r.Cid



--m la n 
select * 
from Produse p inner join Achizitii a on p.Pid = a.Pid
inner join Clienti c on c.Cid = a.Cid


select cc.Nume_Client, Denumire_Categorie 
from Categorii c inner join Produse p on p.Cid = c.Cid
inner join Achizitii a on a.Pid = p.Pid 
inner join Clienti cc on cc.Cid = a.Cid