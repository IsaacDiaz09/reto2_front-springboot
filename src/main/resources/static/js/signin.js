import { mostrarMensaje, campoEstaVacio, urlbase, regExEmail } from "./utils.js";

// Valida los atributos del usuario, entonces lo guarda
$(document).ready(function () {
    $("#btn-signin").click(function () {
        // Se recupera el valor de los campos
        const nombre = $.trim($("#name").val());
        const email = $.trim($("#email").val());
        const identification = $.trim($("#identification").val());
        const cellphone = $.trim($("#cellphone").val());
        const address = $.trim($("#address").val());
        const zone = $.trim($("#zone").val());
        const type = $("#typeUser").val();
        const password = $("#pass").val();
        const confirmar = $("#pass2").val();
        const id = $("#idUser").val();

        if (validaUsuario(nombre, email, password, confirmar, identification, address, cellphone, zone) === false) {
            return;
        } else {
            // Verificar email no en uso
            $.get(urlbase + "/" + email, function (estaEnUso) {
                if (estaEnUso === false) {
                    const user = {
                        id: id,
                        name: nombre,
                        identification: identification,
                        address: address,
                        cellPhone: cellphone,
                        email: email,
                        password: password,
                        zone: zone,
                        type: type
                    };
                    $.ajax({
                        url: `${urlbase}/new`,
                        type: "POST",
                        data: JSON.stringify(user),
                        dataType: "json",
                        headers: {
                            "Content-Type": "application/json",
                        },
                        statusCode: {
                            201: function () {
                                mostrarMensaje("Confirmacion", "Usuario  creado exitosamente", false);
                                limpiarCampos();
                            },
                        },
                    }
                    )
                } else {
                    mostrarMensaje("Error", "El email proporcionado ya se encuentra en uso", true);
                }
            })
        }
    })
})


// Valida que el email sea valido, la obligatoriedad de los campos, que las contraseñas coincidan y su longitud
function validaUsuario(nombre, email, password, confirmar, identification, address, cellphone, zone) {

    if (
        campoEstaVacio(nombre) === true || campoEstaVacio(email) === true ||
        campoEstaVacio(password) === true || campoEstaVacio(confirmar) === true ||
        campoEstaVacio(identification) === true || campoEstaVacio(address) === true ||
        campoEstaVacio(cellphone) === true || campoEstaVacio(zone) === true
    ) {
        mostrarMensaje(
            "Error",
            "Todos los campos son requeridos, verifique e intente nuevamente",
            true
        );
        return false;
    }
    else if (identification < 0) {
        mostrarMensaje(
            "Error",
            "El ID no puede ser negativo",
            true
        );
        return false;
    }
    else if (regExEmail.test(email) === false) {
        mostrarMensaje(
            "Error",
            "El formato de email es inválido, verifiquelo e intente de nuevo",
            true
        );
        return false;
    }
    else if (password.length < 8 || confirmar.length < 8) {
        mostrarMensaje("Error", "La contraseña debe tener minimo 8 caracteres", true);
        return false;
    } else if (password !== confirmar) {
        mostrarMensaje("Error", "La contraseñas no coinciden", true);
        return false;
    }
    return true;
}

// Limpia los campos del formulario
function limpiarCampos() {
    $("#idUser").val("");
    $('#name').val("");
    $('#email').val("");
    $('#pass').val("");
    $('#pass2').val("");
    $('#identification').val("");
    $('#cellphone').val("");
    $('#address').val("");
    $('#zone').val("");
}