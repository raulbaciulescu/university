function [z, ni, mult] = NewtonEstimat(f, fd, x0)
   
    er = 0; 
    iter_max =100;
    ea = 1e-6;
    m = 1;
    x_prev = x0;
    x_prev2 = 0.7;
    for k = 1 : iter_max
        x_curr = x_prev - m * f(x_prev) / fd(x_prev);
        if (abs(x_curr - x_prev) < ea)
            z=x_curr; 
            ni=k;
            mult = round(m);
            return
        end
        if (x_prev2~=x0 && x_prev2~=-1)
            m = log(f(x_prev)/f(x_prev2))/log((x_prev-x_curr)/(x_prev2-x_curr))
        end
        x_prev2 = x_prev;
         x_prev= x_curr; 
    end
    error('Numar maxim de iteratii atins')
end