const secventaAcceptata = (secventa) => {
    // citim din fisier!!
    const tranzitii = {
        'q0': [['a', 'q0'], ['b', 'q1']],
        'q1': [['b', 'q1']],
    }

    const stareInitiala = 'q0';
    const stariFinale = ['q1'];
    let stareCurenta = stareInitiala;
    let t = 0;

    for (let ch of secventa) {
        if (tranzitii[stareCurenta].some(el => el[0] === ch)) {
            stareCurenta = tranzitii[stareInitiala].find(el => el[0] === ch);
            console.log(stareCurenta);
        }
        else {
            console.log("secventa nu este acceptata");
            t = 1;
            break; 
        }
    }
}