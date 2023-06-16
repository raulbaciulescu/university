%Se genera un vector de lungime 5 cu valori aleatore intre 1 si 10
%Se alegeau doua numere X1 si X2 fara returnare 
%Se cerea suma medie dupa repetarea exp 1000 orient
%Probabilitatea ca |X1-X2| > 2
%1,5P 

disp "Subpunctul a"

Suma = 0;
Eveniment = 0;
for i = 1:1000
  v = rand(5, 1)*10;  
  X = randsample(v, 2, replacement = false);
  X1 = X(1);
  X2 = X(2);
  Suma = Suma + X1 + X2;
  if(abs(X1-X2)>2)
    Eveniment = Eveniment + 1;
  endif
endfor

disp "Suma medie: "
Suma = Suma/1000
disp "Probabilitate: "
Prob = Eveniment/1000

disp "Subpunctul b"

binopdf(2, 5, 0.3)