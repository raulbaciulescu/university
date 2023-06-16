function f(N = 1000)
  result = 0;
  x = 0;
  y = 0;
  prob_a = 0;
  prob_b = 0;
  for i=1:N
    x = exprnd (1);
    if x < 1
      y = geornd(1/2);
    else
      y = hygernd(4, 2, 2);
    endif
    
    if y == 1 || y == 0
      prob_b++;
    endif
    if y == 1
      prob_a++;
    endif
  endfor
  fprintf('Probabilitatea A: ');
  prob_a = prob_a / N
  fprintf('Probabilitatea B: ');
  prob_b = prob_b / N
  
  fprintf('Probabilitatea teoretica a lui B: ');
  %hygepdf(0:1, 4, 2, 2) + geopdf(0:1, 1/2)
  %p (A u B) = P(A) + P(B) - P(A)*P(B)
  prob_teor = sum(geopdf(0:1,1/2)) + sum(hygepdf(0:1, 4, 2, 2)) - sum(geopdf(0:1,1/2)) * sum(hygepdf(0:1, 4, 2, 2))
endfunction
