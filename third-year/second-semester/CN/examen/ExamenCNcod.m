

f= @(x) (x^2)/2 + x + 1 - exp(x); 

fd = @(x) x + 1 - exp(x); 
fdd = @(x) 1 - exp(x); 
x0 = 1;

[z, ni, m]=NewtonEstimat(f, fd, x0, 1E-3, 100);
fprintf("Newton estimat:");
fprintf("x = %f", z);
fprintf("Num?rul de itera?ii efectuate: %d", ni);
fprintf("Ordin de multiplicitate: %d", m);
