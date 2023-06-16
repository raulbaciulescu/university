function [z, k] = NewtonNecunoscuta(f, fd, x0, err, iter_max)
    %f - functia
    %fd - derivata
    %x0 - aproximatia initiala 
    %err - eroarea
    %iter_max - numarul maxim de iteratii
    %Iesires
    %z - aproximatia radacinii
    %ni - numarul de iteratii
    
    if (nargin < 4); err = 1e-6; end
    if (nargin < 5); iter_max = 500; end

    m = 1;
    x_prev = x0;
    x_prev1 = x0;
    for k = 1 : iter_max
        x_curr = x_prev - m * f(x_prev) / fd(x_prev);
        if (abs(x_curr - x_prev) < err)
            z = x_curr; 
            return
        end
        if (x_prev1 ~= x_prev)
            m = log(abs(f(x_prev)/f(x_prev1)))/log(abs((x_prev-x_curr)/(x_prev1-x_curr)));
        end
        x_prev1 = x_prev;
        x_prev= x_curr; 
    end
    error('Numar maxim de iteratii atins')
end