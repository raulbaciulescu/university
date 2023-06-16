function [sinus, cosinus] = sincos(x)
    syms sin_taylor(t) cos_taylor(t)
    sin_taylor(t) = taylor(sin(t), t, 'Order', 8);
    cos_taylor(t) = taylor(cos(t), t, 'Order', 8);
    if x > 0
        while x >= 2* pi
            x = x - 2* pi;
        end
    else
        while x < 0
            x = x + 2* pi;
        end
    end
    if x >= 0 && x< pi/2
        sinus = sin_taylor(x);
        cosinus = cos_taylor(x);
    elseif x >= pi/2 && x < pi
        x = x - pi/2;
        sinus = cos_taylor(x);
        cosinus = -sin_taylor(x);
    elseif x >= pi && x < 3*pi/2
       x = x - pi;
       sinus = -sin_taylor(x);
       cosinus = -cos_taylor(x);
    else
       x = x - 3*pi/2;
       sinus = -cos_taylor(x);
       cosinus = sin_taylor(x);
    end
end