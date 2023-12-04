

function generarTransaccion() {

    let idBanco = document.getElementById("txtBanco").value;
    let tipo = 0;
    let nombreBanco = "";

    if (idBanco === "1") {
        tipo = 1;
        nombreBanco = "CITIBANAMEX";
    } else if (idBanco === "2") {
        tipo = 2;
        nombreBanco = "NUMEXICO";
    } else if (idBanco === "3") {
        tipo = 2;
        nombreBanco = "AZTECA";
    }


    let cuenta = new Object();
    cuenta.noTarjeta = document.getElementById("txtNoTarjeta").value;
    cuenta.nip = document.getElementById("txtNIP").value;

    let transaccion = new Object();
    transaccion.monto = document.getElementById("txtMonto").value;
    transaccion.banco = nombreBanco;
    transaccion.cuenta = cuenta;

    let parametros = new URLSearchParams({datos: JSON.stringify(transaccion)});
    console.log(parametros);

    console.log(idBanco);

    Swal.fire({
        title: '¿Desea generar la transacción?',
        icon: 'question',
        showCancelButton: true,
        showConfirmButton: true,
        confirmButtonText: 'Confirmar',
        cancelButtonText: 'Cancelar'
    }).then((result) => {
        if (result.isConfirmed) {

            if (idBanco === "1") {

                axios.post('api/transaccion/generarTransaccion', parametros).then(function (response) {
                    console.log(response);

                    if (response.data.exception != null) {
                        Swal.fire({
                            toast: true,
                            position: 'top-end',
                            showConfirmButton: false,
                            timer: 3000,
                            timerProgressBar: true,
                            icon: 'error',
                            title: '¡Error:!',
                            text: response.data.exception
                        });

                        return;
                    } else if (response.data != null) {
                        console.log(response.data);
                        Swal.fire({
                            toast: true,
                            position: 'top-end',
                            showConfirmButton: false,
                            timer: 3000,
                            timerProgressBar: true,
                            icon: 'success',
                            title: '¡Se generó correctamente!',
                            text: response.data.codigo
                        });
                    }
                });

            } else if (idBanco === "2") {

                generarTransaccionNu();

            } else if (idBanco === "3") {

                generarTransaccionAzteca();
            }

        }
    });


}

function generarTransaccionNu() {

    let tarjeta = document.getElementById("txtNoTarjeta").value;
    let nip = document.getElementById("txtNIP").value;
    let monto = document.getElementById("txtMonto").value;

    let transaccion = {idTransaccion: 0,
        cuenta: {numCuenta: tarjeta, nip: nip},
        fechaMovimiento: generarFechaAcual(), horaMovimiento: generarHoraActual(), monto: monto};

    let parametros = new URLSearchParams({datos: JSON.stringify(transaccion)});
    console.log(parametros);

    axios.post('http://192.168.137.251:8082/cajero/api/transaccion/ejecutarTrasaccion', parametros).then(function (response) {

        if (response.data.exception != null) {
            Swal.fire({
                toast: true,
                position: 'top-end',
                showConfirmButton: false,
                timer: 3000,
                timerProgressBar: true,
                icon: 'error',
                title: '¡Error:!',
                text: response.data.exception
            });

            return;
        } else if (response.data != null) {
            Swal.fire({
                toast: true,
                position: 'top-end',
                showConfirmButton: false,
                timer: 3000,
                timerProgressBar: true,
                icon: 'success',
                title: '¡Se generó correctamente!',
                text: response.data
            });

        }
    });
}

function generarTransaccionAzteca() {

    let tarjeta = document.getElementById("txtNoTarjeta").value;
    let nip = document.getElementById("txtNIP").value;
    let monto = document.getElementById("txtMonto").value;
    let nombre = "CITIBANAMEX";

    let transaccion = {nombre_banco: nombre, no_tarjeta: tarjeta, nip: nip, monto: monto};

    let parametros = new URLSearchParams({datosTrasaccion: JSON.stringify(transaccion)});
    console.log(parametros);

    axios.post('http://192.168.137.251:8082/cajero/api/transaccion/ejecutarTrasaccion', parametros).then(function (response) {

        if (response.data.exception != null) {
            Swal.fire({
                toast: true,
                position: 'top-end',
                showConfirmButton: false,
                timer: 3000,
                timerProgressBar: true,
                icon: 'error',
                title: '¡Error:!',
                text: response.data.exception
            });

            return;
        } else if (response.data != null) {
            Swal.fire({
                toast: true,
                position: 'top-end',
                showConfirmButton: false,
                timer: 3000,
                timerProgressBar: true,
                icon: 'success',
                title: '¡Se generó correctamente!',
                text: response.data
            });

        }
    });
}

function generarTransaccionExterna(codigo) {

    let idBanco = document.getElementById("txtBanco").value;
    let nombreBanco = "";

    if (idBanco === "2") {
        nombreBanco = "NUMEXICO";
    } else if (idBanco === "3") {
        nombreBanco = "AZTECABANCO";
    }

    let transaccion = new Object();
    transaccion.monto = document.getElementById("txtMonto").value;
    transaccion.banco = nombreBanco;
    transaccion.codigo = codigo;

    let parametros = new URLSearchParams({datos: JSON.stringify(transaccion)});
    console.log(parametros);


    fetch('api/transaccion/generarTransaccionExterno',
            {
                method: 'POST',
                body: parametros,
                headers: {'Content-Type': 'application/x-www-form-urlencoded;charset=UTF-8'}
            })
            .then(response => {
                return response.json();
            })
            .then(function (data) {
                if (data.exception != null) {
                    Swal.fire('Error', 'Error interno del servidor!', 'error');
                }
                if (data.error != null) {
                    Swal.fire('', data.error, 'warning');

                    return;
                } else {
                    Swal.fire({
                        title: '¿Desea realizar la transacción?',
                        icon: 'question',
                        showConfirmButton: true,
                        confirmButtonText: 'Realizar'
                    }).then((result) => {
                        console.log("hecho");
                    });
                }
            });

}

function generarFechaAcual() {
    var fecha = new Date();

    // Obtener día, mes y año
    var dia = fecha.getDate();
    var mes = fecha.getMonth() + 1; // Los meses en JavaScript van de 0 a 11, por lo que sumamos 1
    var año = fecha.getFullYear();

    // Formatear la fecha como dd-mm-yyyy
    if (dia < 10) {
        dia = '0' + dia;
    }

    if (mes < 10) {
        mes = '0' + mes;
    }

    var fechaFormateada = dia + '-' + mes + '-' + año;

    return fechaFormateada;
}

function generarHoraActual() {
    var fecha = new Date();

    var horas = fecha.getHours();
    var minutos = fecha.getMinutes();
    var segundos = fecha.getSeconds();

    // Formatear la hora como hh:mm:ss
    if (horas < 10) {
        horas = '0' + horas;
    }

    if (minutos < 10) {
        minutos = '0' + minutos;
    }

    if (segundos < 10) {
        segundos = '0' + segundos;
    }

    var horaFormateada = horas + ':' + minutos + ':' + segundos;

    return horaFormateada;
}