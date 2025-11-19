import http from 'k6/http';
import { check, sleep } from 'k6';

export let options = {
    vus: 5,           // 5 usuarios virtuales
    duration: '10s',  // durante 10 segundos
};

export default function () {
    let res = http.get('http://localhost:8080/api/products'); // tu endpoint
    check(res, {
        'status is 200': (r) => r.status === 200,
    });
    sleep(1);
}
// Este es un test de carga básico que simula 5 usuarios accediendo al endpoint de productos durante 10 segundos.