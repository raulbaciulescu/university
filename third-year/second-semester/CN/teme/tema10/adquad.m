function I = adquad(f, a, b, tol, met)
    %Aproximare integrală prin cuadratură adaptivă:
    %f - funcția
    %a, b - limite de integrare
    %tol - toleranța
    %met - cuadratura repetată utilizată
    %I - valoarea integralei
    % Constanta convenabilă (4 sau 5):
    m = 4;
    
    I1 = met(f, a, b, tol, m);
    I2 = met(f, a, b, tol, 2 * m);
    
    if (abs(I1 - I2) < tol)
        I = I2;
    else 
        Ia = adquad(f, a, (a + b) / 2, tol, met);
        Ib = adquad(f, (a + b) / 2, b, tol, met);
        I = Ia + Ib;
    end

end
